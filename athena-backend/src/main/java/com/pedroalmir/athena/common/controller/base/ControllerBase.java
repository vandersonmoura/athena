package com.pedroalmir.athena.common.controller.base;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.core.Localization;

import com.pedroalmir.athena.common.annotation.MainDAOInterface;
import com.pedroalmir.athena.common.repository.GenericDAO;
import com.pedroalmir.athena.common.session.UserSession;
import com.pedroalmir.athena.common.validation.MessageResult;


/**
 * Controllers of the parent class, providing the basis for the injection of 
 * primary attributes such as DAO and the User Session
 * 
 * @author Pedro Oliveira
 */
@SuppressWarnings("rawtypes")
public class ControllerBase<E extends GenericDAO> implements MainDAOInterface<E> {

	/**
	 * GenericDAO principal
	 */
	protected E mainDAO;
	/**
	 * Object that is the vision of the messages returned
	 */
	protected MessageResult messageResult;
	/**
	 * Responsible for maintaining the user session
	 */
	protected UserSession userSession;
	/**
	 * Representa o objeto da requisição
	 */
	protected HttpServletRequest request;
	/**
	 * Usada para pegar mensagens do arquivo de configuração
	 */
	protected Localization localization;
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
	protected void addError(String viewName, String messageKey, Object... params) {
		messageResult.add(viewName, messageKey, params);
	}

	/**
	 * @return message result
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
	 * @return user session
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
	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @param request
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
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

}
