package com.pedroalmir.athena.core.fitness;

import com.pedroalmir.athena.core.algorithm.Algorithm;
import com.pedroalmir.athena.core.entity.Entity;
import com.pedroalmir.athena.core.solution.Fitness;


/**
 * A fitness calculator that is specialized to determine the fitness of
 * an Entity instance.
 */
public class EntityBasedFitnessCalculator implements FitnessCalculator<Entity> {

    /**
     * {@inheritDoc}
     */
    public EntityBasedFitnessCalculator getClone() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public Fitness getFitness(Algorithm algorithm, Entity entity) {
        return algorithm.getOptimisationProblem().getFitness(entity.getCandidateSolution());
    }

}
