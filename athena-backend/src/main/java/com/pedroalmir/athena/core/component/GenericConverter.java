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
	 * This method should remove an input
	 * @param input
	 * 			input to remove
	 */
	void removeInput(Input input);
	/**
	 * This method should remove an input.
	 * 
	 * @param index
	 * 			index of input to remove.
	 */
	void removeInput(int index);
	/**
	 * This method should add an output in this converter.
	 * 
	 * @param output
	 */
	void addOutput(Output output);
	/**
	 * This method should remove an output.
	 * 
	 * @param output
	 * 			output to remove
	 */
	void removeOutput(Output output);
	/**
	 * This method should remove an output.
	 * 
	 * @param index
	 * 			index of output to remove
	 */
	void removeOutput(int index);
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
	 * @return list of outputs
	 */
	List<Output> convert();
}
