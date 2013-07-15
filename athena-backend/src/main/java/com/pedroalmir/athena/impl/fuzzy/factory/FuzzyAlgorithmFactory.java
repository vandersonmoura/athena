/**
 * 
 */
package com.pedroalmir.athena.impl.fuzzy.factory;

import java.util.List;

import com.pedroalmir.athena.core.factory.AbstractAlgorithmFactory;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.impl.fuzzy.algorithm.FuzzyAlgorithm;
import com.pedroalmir.athena.impl.fuzzy.stoppingCondition.FuzzyCompleteEvaluationStoppingCondition;

/**
 * Fuzzy Algorithm Factory
 * 
 * @author Pedro Almir
 *
 */
public class FuzzyAlgorithmFactory extends AbstractAlgorithmFactory{

	
	/**
	 * @param inputs
	 * 				List of inputs
	 * @param outputs
	 * 				List of outputs
	 * @param settings
	 * 				List of settings
	 * @return fuzzy algorithm
	 */
	public FuzzyAlgorithm createAlgorithm(List<Input> inputs, List<Output> outputs, List<Setting> settings) {
		FuzzyAlgorithm fuzzyAlgorithm = new FuzzyAlgorithm(inputs, outputs, settings);
		fuzzyAlgorithm.addStoppingCondition(new FuzzyCompleteEvaluationStoppingCondition(inputs));
		
		return fuzzyAlgorithm;
	}

}
