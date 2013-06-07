package com.pedroalmir.athena.common.handler;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.Component;

import com.pedroalmir.athena.common.handler.result.ResultEntity;
import com.pedroalmir.athena.common.validation.MessageResult;


/**
 * 
 * Class used to treat the return of the request, using a JSON format as standard
 * 
 * @author Pedro Oliveira
 */
@Component
public class OutputHandler implements DefaultOutputHandler {

	/**
	 * result
	 */
	private Result result;
	/**
	 * messageResult
	 */
	private MessageResult messageResult;

	/**
	 * Default constructor
	 * 
	 * @param result
	 * @param messageResult
	 */
	public OutputHandler(Result result, MessageResult messageResult) {
		this.result = result;
		this.messageResult = messageResult;
	}

	public void response(Object entity) {

		ResultEntity resultEntity = new ResultEntity();
		resultEntity.setResult(entity);

		result.use(getOutputType()).from(resultEntity);
	}

	public Class<CustomJsonSerialization> getOutputType() {
		return CustomJsonSerialization.class;
	}

	public void responseError() {
		ResultEntity resultEntity = new ResultEntity();
		resultEntity.setErrors(messageResult.getMessages());
		resultEntity.returnError();

		result.use(getOutputType()).from(resultEntity);
	}
}
