package com.pedroalmir.athena.core.solution;

import com.pedroalmir.athena.core.solution.fitness.Fitness;
import com.pedroalmir.athena.core.type.base.Type;


/**
 * This class represents a solution to an {@linkplain Problem Optimization problem}.
 * It is responsible for keeping track of the optimization problem and position
 * of the solution within the search space.
 */
public final class OptimizationSolution implements Solution, Comparable<OptimizationSolution> {

    private final Type position;
    private final Fitness fitness;

    /**
     * Constructs a new instance of {@code OptimisationSolution}.
     *
     * @param position The position of the solution within the search space of the problem.
     * @param fitness The {@linkplain Fitness} of the optimization solution.
     */
    public OptimizationSolution(Type position, Fitness fitness) {
        this.position = position.getClone();
        this.fitness = fitness;
    }

    public OptimizationSolution(OptimizationSolution copy) {
        this.position = copy.position.getClone();
        this.fitness = copy.fitness;
    }

    public OptimizationSolution getClone() {
        return new OptimizationSolution(this);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if ((other == null) || (this.getClass() != other.getClass())) {
            return false;
        }

        OptimizationSolution otherSolution = (OptimizationSolution) other;
        return this.position.equals(otherSolution.position)
                && this.fitness.equals(otherSolution.fitness);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.position == null ? 0 : this.position.hashCode());
        hash = 31 * hash + (this.position == null ? 0 : this.fitness.hashCode());
        return hash;
    }

    /**
     * Returns the position of this solution within the search space of the problem.
     *
     * @return The position of this solution in search space.
     */
    public Type getPosition() {
        return this.position;
    }

    /**
     * Returns the fitness of this solution according to
     * {@linkplain Problem optimization problem}.
     * Calling this function does not contribute to the number of fitness
     * evaluations maintained by the
     * {@link Problem optimization problem}.
     *
     * @return The fitness of this solution.
     */
    public Fitness getFitness() {
        return this.fitness;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(OptimizationSolution other) {
        return this.fitness.compareTo(other.fitness);
    }
}
