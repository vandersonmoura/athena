package com.pedroalmir.athena.core.fitness;

import com.pedroalmir.athena.core.algorithm.Algorithm;
import com.pedroalmir.athena.core.solution.fitness.Fitness;


/**
 * Perform the calculation of the fitness for the given <code>Entity</code>, decoupling the
 * <code>Entity</code> from the <code>Problem</code>.
 * @param <T> The type to which a fitness calculation is to be performed.
 */
public interface FitnessCalculator<T> extends Cloneable {

    /**
     * {@inheritDoc}
     */
    FitnessCalculator<T> getClone();

    /**
     * Get the fitness, given the <code>position</code>.
     * @param entity The <code>Type</code> to base the calculation on.
     * @return A <code>Fitness</code> object representing the fitness of the <code>position</code>.
     */
    Fitness getFitness(Algorithm algorithm, T entity);

}
