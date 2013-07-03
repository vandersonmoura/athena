package com.pedroalmir.athena.core.measurement.generic;

import com.pedroalmir.athena.core.algorithm.Algorithm;
import com.pedroalmir.athena.core.measurement.Measurement;
import com.pedroalmir.athena.core.type.numeric.Int;

/**
 *
 */
public class Iterations implements Measurement<Int> {

    /**
     * {@inheritDoc}
     */
    public Iterations getClone() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public Int getValue(Algorithm algorithm) {
        return Int.valueOf(algorithm.getIterations());
    }


}
