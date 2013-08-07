/**
 * 
 */
package com.pedroalmir.athena.common.util.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Inspired by the quality and variety of XML tooling available for the Java platform (StAX, JAXB, etc.), 
 * the Jackson is a multi-purpose Java library for processing JSON data format. Jackson aims to be the best 
 * possible combination of fast, correct, lightweight, and ergonomic for developers.
 * 
 * @author Pedro Almir
 *
 */
public class JacksonUtil {
	/**
	 * Serialize the object. Convert Java object to JSON format
	 * 
	 * @param object
	 * @return object in JSON format
	 */
	public static String serializeWithWriter(Object object){
		try {
			Writer stringWriter = new StringWriter();
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(stringWriter, object);
			
			return stringWriter.toString();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Serialize the object. Convert Java object to JSON format
	 * 
	 * @param object
	 * @return object in JSON format
	 */
	public static String serialize(Object object){
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(object);
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Convert JSON to Java object
	 * 
	 * @param objectInJSON
	 * 			object in JSON format
	 * @return java object
	 */
	public static Object deserialize(String objectInJSON, Class<?> klass){
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(objectInJSON, klass);
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
