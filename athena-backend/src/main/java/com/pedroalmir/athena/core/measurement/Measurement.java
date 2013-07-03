package com.pedroalmir.athena.core.measurement;

import com.pedroalmir.athena.core.algorithm.Algorithm;
import com.pedroalmir.athena.core.type.base.Type;


/**
 * All measurements must implement this interface. The measurement must return
 * the value of what it is measuring given the algorithm that it is measuring.
 *
 * @param <E> The return {@code Type}.
 */
public interface Measurement<E extends Type> extends Cloneable {

    /**
     * {@inheritDoc}
     */
    Measurement<E> getClone();

    /**
     * Gets the value of the measurement. The representation of the measurement will be based
     * on the domain string defined.
     *
     * @param algorithm The algorithm to obtain the measurement from.
     * @return The <tt>Type</tt> representing the value of the measurement.
     */
    E getValue(Algorithm algorithm);
}
