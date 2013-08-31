/**
 * 
 */
package com.pedroalmir.athena.impl.nsga.algorithm;

import java.util.List;

import com.pedroalmir.athena.core.algorithm.AbstractAlgorithm;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.solution.Solution;

/** 
 *  Implementation of NSGA-II.
 *  This implementation of NSGA-II makes use of a QualityIndicator object
 *  to obtained the convergence speed of the algorithm. This version is used
 *  in the paper:
 *     A.J. Nebro, J.J. Durillo, C.A. Coello Coello, F. Luna, E. Alba 
 *     "A Study of Convergence Speed in Multi-Objective Metaheuristics." 
 *     To be presented in: PPSN'08. Dortmund. September 2008.
 * 
 * @author Pedro Almir
 */
public class NSGAIIAlgorithm extends AbstractAlgorithm {
	
	/**
	 * 
	 */
	//private NSGAII nsgaii;
	
	protected NSGAIIAlgorithm(List<Input> inputs, List<Output> outputs, List<Setting> settings) {
		super(inputs, outputs, settings);
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.impl.team.athena.core.configuration.ConfigurableAlgorithm#addSetting(com.pedroalmir.athena.impl.team.athena.core.put.Setting)
	 */
	@Override
	public void addSetting(Setting setting) {

	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.impl.team.athena.core.configuration.ConfigurableAlgorithm#removeSetting(com.pedroalmir.athena.impl.team.athena.core.put.Setting)
	 */
	@Override
	public void removeSetting(Setting setting) {

	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.impl.team.athena.core.algorithm.AbstractAlgorithm#getClone()
	 */
	@Override
	public AbstractAlgorithm getClone() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.impl.team.athena.core.algorithm.AbstractAlgorithm#algorithmIteration()
	 */
	@Override
	protected void algorithmIteration() {

	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.impl.team.athena.core.algorithm.AbstractAlgorithm#algorithmInitialisation()
	 */
	@Override
	public void algorithmInitialisation() {

	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.impl.team.athena.core.algorithm.AbstractAlgorithm#getBestSolution()
	 */
	@Override
	public Solution getBestSolution() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.impl.team.athena.core.algorithm.AbstractAlgorithm#getSolutions()
	 */
	@Override
	public <T extends Solution> Iterable<T> getSolutions() {
		return null;
	}

}
