package com.pedroalmir.athena.core.system.simulation;

import java.util.List;

import com.pedroalmir.athena.core.component.AthenaBundle;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;

/**
 * @author Pedro Almir
 *
 */
public class SimulationData {
	/**
	 * 
	 */
	private AthenaBundle bundle;
	/**
	 * 
	 */
	private final List<Input> inputs;
	/**
	 * 
	 */
	private final List<Output> outputs;
	/**
	 * 
	 */
	private final List<Setting> settings;
	/**
	 * @param bundle
	 * @param inputs
	 * @param outputs
	 */
	public SimulationData(AthenaBundle bundle, List<Input> inputs, List<Output> outputs, List<Setting> settings) {
		super();
		this.bundle = bundle;
		this.inputs = inputs;
		this.outputs = outputs;
		this.settings = settings;
	}
	/**
	 * @return the bundle
	 */
	public AthenaBundle getBundle() {
		return bundle;
	}
	/**
	 * @param bundle the bundle to set
	 */
	public void setBundle(AthenaBundle bundle) {
		this.bundle = bundle;
	}
	/**
	 * @return the inputs
	 */
	public List<Input> getInputs() {
		return inputs;
	}
	/**
	 * @return the outputs
	 */
	public List<Output> getOutputs() {
		return outputs;
	}
	/**
	 * @return the settings
	 */
	public List<Setting> getSettings() {
		return settings;
	}
}
