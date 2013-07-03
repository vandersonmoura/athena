package com.pedroalmir.athena.core.measurement;

import com.pedroalmir.athena.core.algorithm.Algorithm;
import com.pedroalmir.athena.core.type.numeric.Real;

/**
 *
 */
public class Fitness implements Measurement<Real> {
    
    /**
     * {@inheritDoc}
     */
    public Fitness getClone() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public Real getValue(Algorithm algorithm) {
        double fitness = algorithm.getBestSolution().getFitness().getValue();
        return Real.valueOf(fitness);
    }
}
