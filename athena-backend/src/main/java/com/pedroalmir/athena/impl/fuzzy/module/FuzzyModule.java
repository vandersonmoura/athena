/**
 * 
 */
package com.pedroalmir.athena.impl.fuzzy.module;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.component.AbstractBundle;
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
public class FuzzyModule extends AbstractBundle implements GenericModule{
	
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

	@Override
	public String getName() {
		return "Fuzzy Module";
	}

	@Override
	public String getDescription() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("");
		/* TODO: Create fuzzy description */
		return buffer.toString();
	}

	@Override
	public String getImagePath() {
		/* TODO: Choose image path */
		return null;
	}

	@Override
	public FuzzyAlgorithm getAlgorithm() {
		return this.fuzzyAlgorithm;
	}

	@Override
	public List<Input> getInputs() {
		return this.inputs;
	}

	@Override
	public List<Output> getOutputs() {
		return this.outputs;
	}

	@Override
	public List<Setting> getSettings() {
		return this.settings;
	}
	
	@Override
	public void addSetting(Setting setting) {
		for(Setting s: this.settings){
			if(s.equals(setting)){
				return;
			}
		}
		this.getSettings().add(setting);
	}

	@Override
	public Configuration getConfiguration() {
		return new FuzzyConfiguration();
	}

	@Override
	public void load(List<Input> inputs, List<Output> outputs, List<Setting> settings) {
		this.inputs =  inputs;
		this.outputs = outputs;
		this.settings = settings;
		
		this.fuzzyAlgorithm = this.fuzzyFactory.createAlgorithm(inputs, outputs, settings);
		
		this.loaded = true;
	}
	
	@Override
	public void load() {
		this.fuzzyAlgorithm = this.fuzzyFactory.createAlgorithm(this.inputs, this.outputs, this.settings);
		this.loaded = true;
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
		if(this.fuzzyAlgorithm != null){
			this.fuzzyAlgorithm.run();
			return this.fuzzyAlgorithm.getSolutions();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FuzzyModule [inputs=" + inputs + ", outputs=" + outputs + ", settings=" + settings + "]";
	}

}
