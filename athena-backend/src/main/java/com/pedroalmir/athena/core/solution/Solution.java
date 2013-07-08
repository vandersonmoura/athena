/**
 * 
 */
package com.pedroalmir.athena.core.solution;

import com.pedroalmir.athena.core.solution.fitness.Fitness;

/**
 * This  interface represents a solution to an algorithm,
 * when executed to solve any problem.
 * 
 * @author Pedro Almir
 *
 */
public interface Solution {
	public Fitness getFitness();
}
