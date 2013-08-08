package com.pedroalmir.athena.common.validation;

import com.pedroalmir.athena.common.controller.base.ControllerBase;

/**
 * Maintains information of the method of the controller that is running
 * 
 * @author Pedro Oliveira
 */
public class ExecutedResource {

	/**
	 * Controller required
	 */
	@SuppressWarnings("rawtypes")
	private Class<? extends ControllerBase> controllerClass;

	/**
	 * Method required
	 */
	private String method;

	/**
	 * @param controllerClass
	 * @param method
	 */
	@SuppressWarnings("rawtypes")
	private ExecutedResource(Class<? extends ControllerBase> controllerClass, String method) {
		this.controllerClass = controllerClass;
		this.method = method;
	}

	/**
	 * @param controllerClass
	 * @param method
	 * @return executed resource
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ExecutedResource getInstance(Class controllerClass, String method) {
		return new ExecutedResource(controllerClass, method);
	}

	/**
	 * @return controllerClass
	 */
	@SuppressWarnings("rawtypes")
	public Class<? extends ControllerBase> getControllerClass() {
		return controllerClass;
	}

	/**
	 * @param controllerClass
	 */
	@SuppressWarnings("rawtypes")
	public void setControllerClass(Class<? extends ControllerBase> controllerClass) {
		this.controllerClass = controllerClass;
	}

	/**
	 * @return method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method
	 */
	public void setMethod(String method) {
		this.method = method;
	}

}
