package com.pedroalmir.athena.common.validation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import br.com.caelum.vraptor.core.Localization;
import br.com.caelum.vraptor.ioc.Container;

import com.pedroalmir.athena.common.annotation.MainDAOInterface;
import com.pedroalmir.athena.common.repository.GenericDAO;
import com.pedroalmir.athena.common.session.UserSession;
import com.pedroalmir.athena.common.util.ContainerUtil;
import com.pedroalmir.athena.common.util.StringUtil;
import com.pedroalmir.athena.common.validation.annotation.RestrictionElement;
import com.pedroalmir.athena.common.validation.annotation.ValidationMethod;
import com.pedroalmir.athena.common.validation.enums.TemporalExecution;

/**
 * Responsible for calling methods of validation of each entity
 * 
 * @author Pedro Oliveira
 */
@SuppressWarnings("rawtypes")
public abstract class ValidatorBase<E extends GenericDAO> implements
		MainDAOInterface<E> {

	/**
	 * executedResource
	 */
	private ExecutedResource executedResource;
	/**
	 * temporalExecution
	 */
	private TemporalExecution temporalExecution;
	/**
	 * stack
	 */
	private List<Object> stack;
	/**
	 * initObject
	 */
	private Object initObject;
	/**
	 * container
	 */
	private Container container;
	/**
	 * localization
	 */
	private Localization localization;
	/**
	 * userSession
	 */
	private UserSession userSession;
	/**
	 * mainDAO
	 */
	protected E mainDAO;
	/**
	 * messageResult
	 */
	protected MessageResult messageResult;
	/**
	 * targetObject
	 */
	protected Object targetObject;

	/**
	 * Validator base
	 */
	public ValidatorBase() {
		initObject = null;
		stack = new ArrayList<Object>();
	}

	/**
	 * 
	 * Method which can be used to facilitate the validation check fields required
	 * 
	 * @param object
	 *           Object to be tested
	 * @param viewName
	 *            Name that represents the key to error if the field collapses
	 */
	protected void validRequiredField(Object object, String viewName) {
		if (object == null || (object instanceof String && StringUtil.isEmpty((String) object))) {
			addError(viewName, "common.required");
		}
	}

	/**
	 * Performs validation of a particular object
	 * 
	 * @param object
	 * @return true if validate
	 */
	@SuppressWarnings("unchecked")
	public final boolean validate(Object object) {
		if (object == null || !hasAnnotation(object.getClass())) {
			return false;
		}

		Class<? extends Object> klass = object.getClass();

		if ((object instanceof String) || (object instanceof Number)
				|| klass.isPrimitive() || klass.isEnum() || klass.isInterface()
				|| stack.contains(object)) {
			return false;
		}

		if (object instanceof Collection) {

			for (Object objectFromMap : ((Collection<Object>) object)) {
				validate(objectFromMap);
			}

			return false;
		}

		stack.add(object);

		if (initObject == null) {
			initObject = object;
		}

		this.targetObject = object;

		performValidation(klass);

		this.targetObject = null;

		for (Field field : klass.getDeclaredFields()) {
			field.setAccessible(true);
			try {
				validate(field.get(object));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (initObject == object) {
			initObject = null;
			stack.clear();
		}

		return !messageResult.hasMessages();
	}

	/**
	 * Perform validation for a particular class, seeking validation class corresponding
	 * 
	 * @param klassObject
	 */
	private void performValidation(Class<? extends Object> klassObject) {
		ValidationClass annotation = klassObject
				.getAnnotation(ValidationClass.class);

		Class<? extends ValidatorBase>[] listOfValidations = annotation.value();

		for (Class<? extends ValidatorBase> validationClass : listOfValidations) {

			try {

				ValidatorBase validationInstance = validationClass
						.newInstance();
				init(validationInstance);

				Method[] validationMethods = validationClass.getMethods();

				for (Method method : validationMethods) {
					if (method.isAnnotationPresent(ValidationMethod.class)) {
						ValidationMethod validationMethod = method
								.getAnnotation(ValidationMethod.class);
						boolean toContinue = verifyRestriction(validationMethod);
						if (toContinue) {
							try {
								method.invoke(validationInstance, targetObject);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Send the necessary dependencies to other Validators
	 * 
	 * @param other
	 */
	private void init(ValidatorBase other) {
		ContainerUtil.setMainDAO(container, other);
		other.setContainer(container);
		other.setExecutedResource(getExecutedResource());
		other.setMessageResult(getMessageResult());
		other.setTemporalExecution(temporalExecution);
		other.setLocalization(getLocalization());
	}

	/**
	 * Checks if the class has the annotation ValidationClass
	 * 
	 * @see com.s4me.core.validation.ValidationClass
	 * @param klassObject
	 * @return true if has annotation
	 */
	private boolean hasAnnotation(Class<? extends Object> klassObject) {
		ValidationClass annotation = klassObject
				.getAnnotation(ValidationClass.class);
		return annotation != null;
	}

	/**
	 * Checks the constraints of the given method validator, checking constraints
	 * 
	 * @param validationMethod
	 *            Annotation of the validation method to be executed
	 * @return true if verify restriction
	 */
	private boolean verifyRestriction(ValidationMethod validationMethod) {

		if (executedResource == null) {
			return true;
		}

		if (temporalExecution == null) {
			temporalExecution = TemporalExecution.DEFAULT_TEMPORAL;
		}

		if (!validationMethod.when().equals(TemporalExecution.BOTH)
				&& !validationMethod.when().equals(temporalExecution)) {
			return false;
		}

		if (validationMethod.restriction() == null
				|| validationMethod.restriction().length == 0) {
			return true;
		}

		for (RestrictionElement elem : validationMethod.restriction()) {
			if (elem.controller() == null
					|| elem.controller().equals(
							executedResource.getControllerClass())) {
				if ((elem.methods() == null || elem.methods().length == 0)
						|| (Arrays.asList(elem.methods())
								.contains(executedResource.getMethod()))) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Adds an error to return the request
	 * 
	 * @param viewName
	 *            Field name to be referenced in the view
	 * @param messageKey
	 *            Key messages in the file message.properties
	 * @param params
	 *            Parameters to be used with string.format
	 */
	protected void addError(String viewName, String messageKey,
			Object... params) {
		messageResult.add(viewName, messageKey, params);
	}

	/**
	 * @param key
	 * @return message
	 */
	protected String getMessage(String key) {
		return localization.getMessage(key);
	}

	/**
	 * @return executedResource
	 */
	public ExecutedResource getExecutedResource() {
		return executedResource;
	}

	/**
	 * @param executedResource
	 */
	public void setExecutedResource(ExecutedResource executedResource) {
		this.executedResource = executedResource;
	}

	/**
	 * @return temporalExecution
	 */
	public TemporalExecution getTemporalExecution() {
		return temporalExecution;
	}

	/**
	 * @param temporalExecution
	 */
	public void setTemporalExecution(TemporalExecution temporalExecution) {
		this.temporalExecution = temporalExecution;
	}

	/**
	 * @return messageResult
	 */
	public MessageResult getMessageResult() {
		return messageResult;
	}

	/**
	 * @param messageResult
	 */
	public void setMessageResult(MessageResult messageResult) {
		this.messageResult = messageResult;
	}

	public E getMainDAO() {
		return mainDAO;
	}

	public void setMainDAO(E mainDAO) {
		this.mainDAO = mainDAO;
	}

	/**
	 * @return container
	 */
	public Container getContainer() {
		return container;
	}

	/**
	 * @param container
	 */
	public void setContainer(Container container) {
		this.container = container;
	}

	/**
	 * @return localization
	 */
	public Localization getLocalization() {
		return localization;
	}

	/**
	 * @param localization
	 */
	public void setLocalization(Localization localization) {
		this.localization = localization;
	}

	/**
	 * @return userSession
	 */
	public UserSession getUserSession() {
		return userSession;
	}

	/**
	 * @param userSession
	 */
	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

}
