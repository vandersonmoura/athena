package com.pedroalmir.athena.core.fitness;

import com.pedroalmir.athena.core.algorithm.Algorithm;
import com.pedroalmir.athena.core.solution.Fitness;
import com.pedroalmir.athena.core.type.container.StructuredType;

/**
 * A fitness calculator that determines the fitness value of a StructuredType.
 *
 * @param <T> The structured type to determine the fitness of.
 */
public class StructuredTypeFitnessCalculator<T extends StructuredType<?>> implements FitnessCalculator<T> {

    /**
     * {@inheritDoc}
     */
    public FitnessCalculator<T> getClone() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public Fitness getFitness(Algorithm algorithm, T structure) {
        return algorithm.getOptimisationProblem().getFitness(structure);
    }

}
