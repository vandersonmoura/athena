/**
 * 
 */
package com.pedroalmir.athena.impl.nsga.algorithm;

import java.util.List;
import java.util.Properties;

import jmetal.metaheuristics.nsgaII.NSGAII;

import com.google.common.base.Preconditions;
import com.pedroalmir.athena.core.algorithm.AbstractAlgorithm;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.solution.Solution;
import com.pedroalmir.athena.impl.fuzzy.algorithm.FuzzyAlgorithm;

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
	private NSGAII nsgaii;
	
	protected NSGAIIAlgorithm(List<Input> inputs, List<Output> outputs, List<Setting> settings) {
		super(inputs, outputs, settings);
	}

	/**
	 * @param copy
	 */
	public NSGAIIAlgorithm(AbstractAlgorithm copy) {
		super(copy);
	}

	@Override
	public void addSetting(Setting setting) {
		this.settings.add(Preconditions.checkNotNull(setting));
	}

	@Override
	public void removeSetting(Setting setting) {
		this.settings.remove(Preconditions.checkNotNull(setting));
	}

	@Override
	public AbstractAlgorithm getClone() {
		return new NSGAIIAlgorithm(this);
	}

	@Override
	protected void algorithmIteration() {

	}

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
