package com.pedroalmir.athena.core.solution.fitness;

import com.pedroalmir.athena.core.type.base.Type;


/**
 * This interface is an abstraction for the fitness of a solution to an
 * optimization problem.
 * <p>
 * The actual fitness value (as determined by the
 * {@link Problem} in question) can be obtained by
 * calling {@link #getValue()} while fitnesses can be compared using the
 * standard Java {@link Comparable} interface.
 * <p>
 * <b>Example:</b> <br />
 * <code>
 * Fitness a = ...; <br />
 * Fitness b = ...; <br />
 * <br />
 * int result = a.compareTo(b); <br />
 * if (result > 0) { <br />
 *   // a is a superior fitness to b <br />
 * } <br />
 * else if (result < 0) { <br />
 *   // b is a superior fitness to a <br />
 * } <br />
 * else { <br />
 *  // a and b are equally fit <br />
 * } <br />
 * </code> <br />
 *
 */
public interface Fitness extends Type, Comparable<Fitness> {

    /**
     * Returns the underlying fitness value.
     * @return the actual fitness value.
     */
    Double getValue();

    /**
     * Creation method that maintains Fitness object immutability by returning
     * a new instance of the current class type.
     * @param value The desired value of the {@code Fitness} object.
     */
    Fitness newInstance(Double value);

}
