package com.pedroalmir.athena.core.pattern;

/**
 * Abstract class defining the general structure of a <tt>Visitor</tt>.
 *
 * @param <E> The type object.
 */
public interface Visitor<E> {

    /**
     * Visit the provided object.
     * @param o The object to visit.
     */
    void visit(E o);

    /**
     * Determine if the visitor has completed its visit operation.
     * @return <code>true</code> if the visit operation is complete,
     *         <code>false</code> otherwise.
     */
    boolean isDone();
}
