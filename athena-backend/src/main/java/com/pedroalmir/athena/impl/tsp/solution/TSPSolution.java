/**
 * 
 */
package com.pedroalmir.athena.impl.tsp.solution;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.solution.Solution;
import com.pedroalmir.athena.core.solution.fitness.Fitness;

/**
 * @author Pedro Almir
 *
 */
public class TSPSolution implements Solution{
	
	private List<Integer> solution;
	/**
     * Sum of all output values
     */
    private Double fitness;
    
	/**
	 * @param solution
	 * @param fitness
	 */
	public TSPSolution(List<Integer> solution, Double fitness) {
		super();
		this.solution = solution;
		this.fitness = fitness;
	}
	
	/**
	 * Default constructor
	 */
	public TSPSolution() {
		this.solution = new LinkedList<Integer>();
		this.fitness = 0.0;
	}
	/**
	 * @return the solution
	 */
	public List<Integer> getSolution() {
		return solution;
	}
	/**
	 * @param solution the solution to set
	 */
	public void setSolution(List<Integer> solution) {
		this.solution = solution;
	}
	/**
	 * @return the fitness
	 */
	public Double getFitnesss() {
		return fitness;
	}
	/**
	 * @param fitness the fitness to set
	 */
	public void setFitness(Double fitness) {
		this.fitness = fitness;
	}
	@Override
	public Fitness getFitness() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
