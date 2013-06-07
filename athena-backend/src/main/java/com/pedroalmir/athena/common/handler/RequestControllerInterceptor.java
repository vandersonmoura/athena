package com.pedroalmir.athena.common.handler;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.core.Localization;
import br.com.caelum.vraptor.core.MethodInfo;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.interceptor.ParametersInstantiatorInterceptor;
import br.com.caelum.vraptor.ioc.Container;
import br.com.caelum.vraptor.resource.ResourceMethod;

import com.pedroalmir.athena.common.annotation.NoJson;
import com.pedroalmir.athena.common.controller.ControllerBase;
import com.pedroalmir.athena.common.session.UserSession;
import com.pedroalmir.athena.common.util.ContainerUtil;
import com.pedroalmir.athena.common.validation.DefaultValidation;
import com.pedroalmir.athena.common.validation.ExecutedResource;
import com.pedroalmir.athena.common.validation.MessageResult;
import com.pedroalmir.athena.common.validation.enums.TemporalExecution;


/**
 * 
 * Intercepts of calls to the controllers, making calls and validation of
 * injecting the dependencies necessary
 * 
 * @author Pedro Oliveira
 */
@Intercepts(after = { PermissionInterceptor.class, ParametersInstantiatorInterceptor.class })
public class RequestControllerInterceptor implements Interceptor {

	/**
	 * info
	 */
	private MethodInfo info;
	/**
	 * outputHandler
	 */
	private OutputHandler outputHandler;
	/**
	 * messageResult
	 */
	private MessageResult messageResult;
	/**
	 * container
	 */
	private Container container;
	/**
	 * userSession
	 */
	private UserSession userSession;
	/**
	 * executedResource
	 */
	private ExecutedResource executedResource;
	/**
	 * localization
	 */
	private Localization localization;
	/**
	 * request
	 */
	private HttpServletRequest request;

	/**
	 * Default constructor
	 * 
	 * @param info
	 * @param outputHandler
	 * @param messageResult
	 * @param container
	 * @param userSession
	 * @param localization
	 * @param request
	 */
	public RequestControllerInterceptor(MethodInfo info,
			OutputHandler outputHandler, MessageResult messageResult,
			Container container, UserSession userSession,
			Localization localization, HttpServletRequest request) {
		this.info = info;
		this.outputHandler = outputHandler;
		this.messageResult = messageResult;
		this.container = container;
		this.userSession = userSession;
		this.localization = localization;
		this.request = request;
		this.executedResource = ExecutedResource.getInstance(info
				.getResourceMethod().getResource().getType(), info
				.getResourceMethod().getMethod().getName());
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance) throws InterceptionException {

		try {
			fillController(resourceInstance);

			callValidation(TemporalExecution.BEFORE, info.getParameters());

			Object result = null;

			if (!messageResult.hasMessages()) {

				result = method.getMethod().invoke(resourceInstance,
						info.getParameters());

				if (result != null) {
					callValidation(TemporalExecution.AFTER, result);
				}

			}

			if (messageResult.hasMessages()) {
				outputHandler.responseError();
			} else {
				// if has @NoJson annotation do not serialize the result
				if (method.getMethod().isAnnotationPresent(NoJson.class)) {
					stack.next(method, resourceInstance);
				} else {
					// else, serialize
					outputHandler.response(result);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			outputHandler.responseError();
		}

	}

	/**
	 * 
	 * Call the validations for each parameter of the method required
	 * 
	 * @param execution
	 *            Runtime (Before or After)
	 * @param params
	 *            An array containing the parameters of the methods
	 */
	private void callValidation(TemporalExecution execution, Object... params) {
		if (params != null) {
			for (Object param : params) {
				performValidation(execution, param);
			}
		}
	}

	/**
	 * 
	 * Draws validation for a specific object
	 * 
	 * @param execution
	 *            Runtime (Before or After)
	 * @param entity
	 *            Object to be validated
	 */
	public void performValidation(TemporalExecution execution, Object entity) {
		DefaultValidation validator = container.instanceFor(DefaultValidation.class);
		validator.setContainer(container);
		validator.setLocalization(localization);
		validator.setMessageResult(messageResult);
		validator.setExecutedResource(executedResource);
		validator.setTemporalExecution(execution);
		validator.setUserSession(userSession);

		validator.validate(entity);
	}

	/**
	 * 
	 * Injects the dependencies necessary for the operation of the controller
	 * 
	 * @param resourceInstance
	 *            Controller
	 */
	@SuppressWarnings({ "rawtypes" })
	private void fillController(Object resourceInstance) {
		ControllerBase controller = (ControllerBase) resourceInstance;
		controller.setMessageResult(messageResult);
		controller.setUserSession(userSession);
		controller.setRequest(request);
		controller.setLocalization(localization);
		ContainerUtil.setMainDAO(container, controller);
	}

	public boolean accepts(ResourceMethod method) {
		return ControllerBase.class.isAssignableFrom(method.getResource().getType());
	}

}
