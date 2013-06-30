/**
 * 
 */
package com.pedroalmir.athena.core.component;

import java.util.List;

import com.pedroalmir.athena.core.configuration.Configuration;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;

/**
 * This interface was designed to support the creation of converters.
 * 
 * @author Pedro Almir
 *
 */
public interface GenericConverter extends AthenaBundle{
	/**
	 * @return converter name
	 */
	String getName();
	/**
	 * @return converter description
	 */
	String getDescription();
	/**
	 * @return converter image path
	 */
	String getImagePath();
	/**
	 * This method should add an input in this converter.
	 * 
	 * @param input
	 */
	void addInput(Input input);
	/**
	 * This method should add an output in this converter.
	 * 
	 * @param output
	 */
	void addOutput(Output output);
	/**
	 * @return converter configuration
	 */
	Configuration getConfiguration();
	/**
	 * Convert inputs to specified outputs.
	 * 
	 * For example:
	 * CSV Converter: CSV -> List\<Object\>
	 * 
	 * This method only receives the input data, performs the conversion 
	 * and prepare them for the exit, returning a list of Output. 
	 * The method responsible for propagating this information to the 
	 * following module is in the class Link.
	 *  
	 * @param inputs
	 * @return list of outputs
	 */
	List<Output> convert(List<Input> inputs);
}
