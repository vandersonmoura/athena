package com.pedroalmir.athena.common.handler;

import br.com.caelum.vraptor.View;

/**
 * 
 * Classes that implement this interface are able to answer a request, according to their specificities
 * 
 * @author Pedro Oliveira
 */
public interface DefaultOutputHandler {

	/**
	 * Get output type
	 * @return the type
	 */
	Class<? extends View> getOutputType();

	/**
	 * Response
	 * @param entity
	 */
	void response(Object entity);

	/**
	 * Response error
	 */
	void responseError();

}
