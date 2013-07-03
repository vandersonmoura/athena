/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package com.pedroalmir.athena.core.measurement.generic;

import com.pedroalmir.athena.core.algorithm.AbstractAlgorithm;
import com.pedroalmir.athena.core.algorithm.Algorithm;
import com.pedroalmir.athena.core.measurement.Measurement;
import com.pedroalmir.athena.core.type.numeric.Real;


/**
 *
 */
public class PercentageComplete implements Measurement<Real> {

    public PercentageComplete getClone() {
        return this;
    }

    public Real getValue(Algorithm algorithm) {
        AbstractAlgorithm alg = (AbstractAlgorithm) algorithm;
        return Real.valueOf(alg.getPercentageComplete());
    }

}
