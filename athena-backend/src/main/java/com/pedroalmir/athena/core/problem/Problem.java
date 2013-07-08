/**
 * 
 */
package com.pedroalmir.athena.core.problem;

import com.pedroalmir.athena.core.problem.objective.Objective;
import com.pedroalmir.athena.core.solution.fitness.Fitness;
import com.pedroalmir.athena.core.type.base.Type;


/**
 * Optimization problems are characterized by a domain that specifies the search
 * space and a fitness given a potential solution. This interface ensures that
 * an {@linkplain Algorithm} has all the information it needs to find a solution 
 * to a given optimization problem.
 * 
 * In addition, it is the responsibility of an optimization problem to keep
 * track of the number of times the fitness has been evaluated.
 * <p>
 * All optimization problems must implement this interface.
 * 
 * @author Pedro Almir
 */
public interface Problem extends Cloneable{
	/**
     * {@inheritDoc}
     */
    Problem getClone();

    /**
     * Returns the fitness of a potential solution to this problem. The solution
     * object is described by the domain of this problem, see
     * {@link #getDomain()}. An instance of {@link InferiorFitness} should be
     * returned if the solution falls outside the search space of this problem.
     *
     * @param solution  the potential solution found by the optimization algorithm.
     * @return          the fitness of the solution.
     */
    Fitness getFitness(Type solution);
    /**
     * @return problem objective
     */
    Objective getObjective();
    /**
     * Returns the number of times the underlying fitness function has been
     * evaluated.
     *
     * @return The number fitness evaluations.
     */
    int getFitnessEvaluations();
}
