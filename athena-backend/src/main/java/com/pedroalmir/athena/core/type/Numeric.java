package com.pedroalmir.athena.core.type;

/**
 * Definition of the <tt>Numeric</tt> type.
 *
 */
public interface Numeric extends Type, BoundedType, Comparable<Numeric>, Randomisable {

    /**
     * {@inheritDoc}
     */
    Numeric getClone();

    /**
     * Get the value of this {@linkplain Numeric}.
     * @return The value of this {@linkplain Numeric} as a {@literal boolean}.
     */
    boolean booleanValue();

    /**
     * Get the value of this {@linkplain Numeric}.
     * @return The value of this {@linkplain Numeric} as a {@literal int}.
     */
    int intValue();

    /**
     * Get the value of this {@linkplain Numeric}.
     * @return The value of this {@linkplain Numeric} as a {@literal double}.
     */
    double doubleValue();

    String getRepresentation();
}
