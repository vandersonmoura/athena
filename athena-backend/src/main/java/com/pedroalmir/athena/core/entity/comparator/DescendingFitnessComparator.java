package com.pedroalmir.athena.core.entity.comparator;

import java.io.Serializable;
import java.util.Comparator;

import com.pedroalmir.athena.core.entity.Entity;
import com.pedroalmir.athena.core.solution.Fitness;

/**
 * Comparator to order {@linkplain Entity} instances based on fitness values.
 * This comparator results in a descending order. This ordering is effectively
 * an ordering from the "most fit" entity to the "least fit" entity.
 *
 * @param <E> The {@code Entity} type.
 */
public class DescendingFitnessComparator<E extends Entity> implements Comparator<E>, Serializable {
    private static final long serialVersionUID = 6320495046411962803L;

    /**
     * Compare the {@linkplain Entity} objects returning the desired ordering.
     * @param e1 The first {@linkplain Entity} to be used in the comparison.
     * @param e2 The second {@linkplain Entity} to be used in the comparison.
     * @return -1 if e1 is less than e2;
     *         0 if e1 and e2 are equal
     *         1 if e2 is greater than e2
     */
    public int compare(E e1, E e2) {
        Fitness f1 = e1.getFitness();
        Fitness f2 = e2.getFitness();

        return -f1.compareTo(f2);
    }

}
