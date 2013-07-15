/**
 * 
 */
package com.pedroalmir.athena.core.component;

import java.util.List;

import com.pedroalmir.athena.core.algorithm.Algorithm;
import com.pedroalmir.athena.core.configuration.Configuration;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.solution.Solution;

/**
 * This interface is designed to represent the modules of the tool.
 * 
 * @author Pedro Almir
 *
 */
public interface GenericModule extends AthenaBundle{
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
	 * Use Factory Pattern to create Algorithm object
	 * @return algorithm
	 */
	Algorithm getAlgorithm();
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
	 * Load and prepare this module for execution.
	 * 
	 * @param inputs
	 * @param outputs
	 * @param settings
	 */
	void load(List<Input> inputs, List<Output> outputs, List<Setting> settings);
	/**
	 * Verify if this module is loaded.
	 * 
	 * @return <code>true</code> if the module is loaded.
	 */
	boolean isLoaded();
	/**
	 * Verify if this module is public.
	 * @return <code>true</code> if the module is public.
	 */
	boolean isPublic();
	/**
	 * Run the algorithm and return a list of solution to the problem.
	 * 
	 * @return solutions
	 */
	List<Solution> run();
}
