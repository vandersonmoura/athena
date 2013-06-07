package com.pedroalmir.athena.common.handler.result;

import java.util.Map;

/**
 * Object that is returned in the request
 * 
 * @author Pedro Oliveira
 */
public class ResultEntity {

	/**
	 * Response code used to indicate whether or not there was error in the request
	 */
	private int code;

	/**
	 * Map containing any of the requests
	 */
	private Map<String, String> errors;

	/**
	 * Entity to be returned
	 */
	private Object result;

	public ResultEntity() {
		returnSuccess();
	}

	/**
	 * Updates the code to a request made ​​no errors
	 */
	public void returnSuccess() {
		this.code = ResultType.SUCCESS.getCode();
	}

	/**
	 * Updates the code stating that there were errors in the request
	 */
	public void returnError() {
		this.code = ResultType.ERROR.getCode();
	}

	/**
	 * @return code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return errors
	 */
	public Map<String, String> getErrors() {
		return errors;
	}

	/**
	 * @param errors
	 */
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	/**
	 * @return result
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * @param result
	 */
	public void setResult(Object result) {
		this.result = result;
	}

}
