/**
 * 
 */
package com.pedroalmir.athena.impl.antSystem.module;

import java.util.List;

import com.pedroalmir.athena.core.algorithm.Algorithm;
import com.pedroalmir.athena.core.component.AbstractBundle;
import com.pedroalmir.athena.core.component.AthenaBundle;
import com.pedroalmir.athena.core.component.GenericModule;
import com.pedroalmir.athena.core.configuration.Configuration;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.solution.Solution;
import com.pedroalmir.athena.impl.antSystem.configuration.AntSystemConfiguration;

/**
 * @author Pedro Almir
 *
 */
public class AntSystemModule extends AbstractBundle implements GenericModule {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6303466590064606317L;
	/**
	 * List of inputs
	 */
	private List<Input> inputs;
	/**
	 * List of outputs
	 */
	private List<Output> outputs;
	/**
	 * List of settings
	 */
	private List<Setting> settings;
	/**
	 * 
	 */
	private boolean loaded;
	
	public String getName() {
		return "Ant System Algorithm Module";
	}

	public String getShortName() {
		return "Ant";
	}

	public String getDescription() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("");
		/* TODO: Create NSGA II description */
		return buffer.toString();
	}

	public String getImagePath() {
		return "";
	}

	public List<Input> getInputs() {
		return this.inputs;
	}

	
	public List<Output> getOutputs() {
		return this.outputs;
	}

	
	public List<Setting> getSettings() {
		return this.settings;
	}

	@Override
	public void setSettings(List<Setting> settings) {
		this.settings = settings;
	}

	@Override
	public Configuration getConfiguration() {
		return new AntSystemConfiguration();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(AthenaBundle bundle) {
		if (this == bundle)
			return true;
		if (bundle == null)
			return false;
		if (getClass() != bundle.getClass())
			return false;
		AntSystemModule other = (AntSystemModule) bundle;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inputs == null) {
			if (other.inputs != null)
				return false;
		} else if (!inputs.equals(other.inputs))
			return false;
		if (outputs == null) {
			if (other.outputs != null)
				return false;
		} else if (!outputs.equals(other.outputs))
			return false;
		if (settings == null) {
			if (other.settings != null)
				return false;
		} else if (!settings.equals(other.settings))
			return false;
		return true;
	}

	@Override
	public Algorithm getAlgorithm() {
		return null;
	}

	@Override
	public boolean isOptimizationAlgorithm() {
		return true;
	}

	@Override
	public void load(List<Input> inputs, List<Output> outputs, List<Setting> settings) {
		// TODO Auto-generated method stub
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isLoaded() {
		return this.loaded;
	}

	@Override
	public boolean isPublic() {
		return true;
	}

	@Override
	public List<Solution> run() {
		return null;
	}

}
