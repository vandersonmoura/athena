/**
 * 
 */
package com.pedroalmir.athena.impl.fuzzy.module;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.component.AbstractBundle;
import com.pedroalmir.athena.core.component.AthenaBundle;
import com.pedroalmir.athena.core.component.GenericModule;
import com.pedroalmir.athena.core.configuration.Configuration;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.solution.Solution;
import com.pedroalmir.athena.impl.fuzzy.algorithm.FuzzyAlgorithm;
import com.pedroalmir.athena.impl.fuzzy.configuration.FuzzyConfiguration;
import com.pedroalmir.athena.impl.fuzzy.factory.FuzzyAlgorithmFactory;

/**
 * This class represents the Fuzzy Module.
 * <br><br>
 * Fuzzy logic algorithm
 * <br><br>
 * 	<ol>
 * 		<li>Define the linguistic variables and terms (initialization)
 * 		<li>Construct the membership functions (initialization)
 * 		<li>Construct the rule base (initialization)
 * 		<li>Convert crisp input data to fuzzy values using membership functions (fuzzification)
 * 		<li>Evaluate the rules in the rule base (inference)
 * 		<li>Combine the results of each rule (inference)
 * 		<li>Convert the output data to non-fuzzy values (defuzzification)
 * 	</ol>
 * 
 * @author Pedro Almir
 *
 */
public class FuzzyModule extends AbstractBundle implements GenericModule {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3575860159082162952L;

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
	/**
	 * 
	 */
	private FuzzyAlgorithm fuzzyAlgorithm;
	/**
	 * Fuzzy Algorithm Factory 
	 */
	private FuzzyAlgorithmFactory fuzzyFactory;
	/**
	 * Default constructor
	 */
	public FuzzyModule() {
		super();
		/* puts */
		this.inputs = new LinkedList<Input>();
		this.outputs = new LinkedList<Output>();
		this.settings = new LinkedList<Setting>();
		/* is loaded */
		this.loaded = false;
		this.fuzzyAlgorithm = null;
		/* Factory */
		this.fuzzyFactory = new FuzzyAlgorithmFactory();
	}

	
	public String getName() {
		return "Fuzzy Module";
	}

	
	public String getDescription() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("");
		/* TODO: Create fuzzy description */
		return buffer.toString();
	}

	
	public String getImagePath() {
		/* TODO: Choose image path */
		return null;
	}

	
	public FuzzyAlgorithm getAlgorithm() {
		return this.fuzzyAlgorithm;
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
	
	public Configuration getConfiguration() {
		return new FuzzyConfiguration();
	}

	
	public void load(List<Input> inputs, List<Output> outputs, List<Setting> settings) {
		this.inputs =  inputs;
		this.outputs = outputs;
		this.settings = settings;
		
		this.fuzzyAlgorithm = this.fuzzyFactory.createAlgorithm(inputs, outputs, settings);
		
		this.loaded = true;
	}
	
	
	public void load() {
		this.fuzzyAlgorithm = this.fuzzyFactory.createAlgorithm(this.inputs, this.outputs, this.settings);
		this.loaded = true;
	}

	
	public boolean isLoaded() {
		return this.loaded;
	}

	
	public boolean isPublic() {
		return true;
	}

	
	public List<Solution> run() {
		if(this.fuzzyAlgorithm != null){
			this.fuzzyAlgorithm.run();
			return this.fuzzyAlgorithm.getSolutions();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	public String toString() {
		return "FuzzyModule [inputs=" + inputs + ", outputs=" + outputs + ", settings=" + settings + "]";
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inputs == null) ? 0 : inputs.hashCode());
		result = prime * result + ((outputs == null) ? 0 : outputs.hashCode());
		result = prime * result
				+ ((settings == null) ? 0 : settings.hashCode());
		return result;
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
		FuzzyModule other = (FuzzyModule) bundle;
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
	public void setSettings(List<Setting> settings) {
		this.settings = settings;
	}

}
