package com.pedroalmir.athena.common.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.View;
import br.com.caelum.vraptor.ioc.Component;

import com.google.gson.Gson;

/**
 * 
 * Serialize objects to JSON
 * 
 * @author Pedro Oliveira
 */
@Component
public class CustomJsonSerialization implements View {

	/**
	 * response
	 */
	private HttpServletResponse response;

	/**
	 * @param response
	 */
	public CustomJsonSerialization(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * Receives and responds to an object in your request JSON equivalent
	 * 
	 * @param object
	 */
	public void from(Object object) {
		response.setContentType("application/json");
		String parseResult = new Gson().toJson(object);

		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.write(parseResult);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
