/**
 * 
 */
package com.pedroalmir.athena.core.component;

import java.util.List;

import com.pedroalmir.athena.core.algorithm.Algorithm;
import com.pedroalmir.athena.core.configuration.Configuration;
import com.pedroalmir.athena.core.configuration.put.Input;
import com.pedroalmir.athena.core.configuration.put.Output;
import com.pedroalmir.athena.core.solution.Solution;

/**
 * This interface is designed to represent the modules of the tool.
 * 
 * @author Pedro Almir
 *
 */
public interface GenericModule {
	/**
	 * @return
	 */
	String getName();
	/**
	 * @return
	 */
	String getDescription();
	/**
	 * @return
	 */
	String getImagePath();
	/**
	 * @return
	 */
	Algorithm getAlgorithm();
	/**
	 * @param input
	 */
	void addInput(Input input);
	/**
	 * @return
	 */
	List<Input> getInputs();
	/**
	 * @param output
	 */
	void addOutput(Output output);
	/**
	 * @return
	 */
	List<Output> getOutputs();
	/**
	 * @return
	 */
	Configuration getConfiguration();
	/**
	 * @param inputs
	 * @param outputs
	 * @param setting
	 */
	void load(List<Input> inputs, List<Output> outputs, Configuration setting);
	/**
	 * @return
	 */
	boolean isLoaded();
	/**
	 * @return
	 */
	boolean isPublic();
	/**
	 * @param inputs
	 * @param outputs
	 * @return
	 */
	Solution run(List<Input> inputs, List<Output> outputs);
}
