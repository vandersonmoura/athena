package com.pedroalmir.athena.core.measurement;

import com.pedroalmir.athena.core.algorithm.Algorithm;
import com.pedroalmir.athena.core.type.container.Vector;
import com.pedroalmir.athena.core.type.string.StringType;

/**
 */
public class Solution implements Measurement<StringType> {
    
    /**
     * {@inheritDoc}
     */
    public Solution getClone() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public StringType getValue(Algorithm algorithm) {
        Vector solution = (Vector) algorithm.getBestSolution().getPosition();
        return new StringType(solution.toString());
    }

}
