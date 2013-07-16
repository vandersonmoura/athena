/**
 * 
 */
package com.pedroalmir.athena.core.component;

import java.util.List;

import com.pedroalmir.athena.core.configuration.Configuration;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;

/**
 * This interface was designed to represent any component to be integrated 
 * into the tool. Thus we can work in a general way, for example, in the case 
 * of links. A link may be a link from one module to another module or the 
 * connection between a module and a converter. 
 * 
 * Initially this interface has no methods defined.
 * 
 * @author Pedro Almir
 * @since 25/06/2013
 */
public interface AthenaBundle {
	
	/**
	 * This method should return the name of the module.
	 * @return the name of the module
	 */
	String getName();
	/**
	 * This method should return the description of how
	 * the module works.
	 * This will help the user to understand the workings
	 * of the algorithm encapsulated by this module.
	 *  
	 * @return the description
	 */
	String getDescription();
	/**
	 * This method should return a link to an image
	 * that represents the module.
	 * 
	 * @return the image path
	 */
	String getImagePath();
	/**
	 * This method should add an input to this module.
	 * For example:
	 * 
	 * Fuzzy Module can contain numerous entries. Using this
	 * method the user can add input to this module.
	 * 
	 * @param input object
	 */
	void addInput(Input input);
	/**
	 * This method should return the list of inputs
	 * of this module.
	 * 
	 * @return list of inputs
	 */
	List<Input> getInputs();
	/**
	 * This method should add an output to this module.
	 * 
	 * @param output object
	 */
	void addOutput(Output output);
	/**
	 * This method should return the list of outputs
	 * of this module.
	 * 
	 * @return list of outputs
	 */
	List<Output> getOutputs();
	/**
	 * This method should add an setting to this module.
	 * 
	 * For example:
	 * Fuzzy Module need FCL config file.
	 * 
	 * @param setting object
	 */
	void addSetting(Setting setting);
	/**
	 * This method should return the list of settings
	 * of this module.
	 * 
	 * @return list of settings
	 */
	List<Setting> getSettings();
	/**
	 * All module settings are stored in the Configuration object.
	 * So, this method should return the module configuration.
	 * 
	 * @return configuration
	 */
	Configuration getConfiguration();
	
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
}
