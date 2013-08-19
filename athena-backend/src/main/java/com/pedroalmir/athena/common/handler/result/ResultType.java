package com.pedroalmir.athena.common.handler.result;

/**
 * 
 * Contains the possible types of results of the request
 * 
 * @author Pedro Oliveira
 */
public enum ResultType {

	/**
	 * Request without errors
	 */
	SUCCESS(200),
	/**
	 * Request with an error
	 */
	ERROR(500),
	/**
	 * Request with PERMISSION_DENIED
	 */
	PERMISSION_DENIED(403);

	/**
	 * code
	 */
	private int code;

	/**
	 * @param code
	 */
	private ResultType(int code) {
		this.code = code;
	}

	/**
	 * @return code
	 */
	public int getCode() {
		return this.code;
	}

}
