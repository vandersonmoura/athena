package com.pedroalmir.athena.core.solution;

import com.pedroalmir.athena.core.type.Type;


/**
 * This class represents a solution to an
 * {@linkplain net.sourceforge.cilib.problem.Problem optimisation problem}.
 * It is responsible for keeping track of the optimisation problem and position
 * of the solution within the search space.
 */
public final class OptimisationSolution implements Solution, Comparable<OptimisationSolution> {

    private final Type position;
    private final Fitness fitness;

    /**
     * Constructs a new instance of {@code OptimisationSolution}.
     *
     * @param position The position of the solution within the search space of the problem.
     * @param fitness The {@linkplain Fitness} of the optimisation solution.
     */
    public OptimisationSolution(Type position, Fitness fitness) {
        this.position = position.getClone();
        this.fitness = fitness.getClone();
    }

    public OptimisationSolution(OptimisationSolution copy) {
        this.position = copy.position.getClone();
        this.fitness = copy.fitness.getClone();
    }

    public OptimisationSolution getClone() {
        return new OptimisationSolution(this);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if ((other == null) || (this.getClass() != other.getClass())) {
            return false;
        }

        OptimisationSolution otherSolution = (OptimisationSolution) other;
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
     * {@linkplain net.sourceforge.cilib.problem.Problem optimisation problem}.
     * Calling this function does not contribute to the number of fitness
     * evaluations maintained by the
     * {@link net.sourceforge.cilib.problem.Problem optimisation problem}.
     *
     * @return The fitness of this solution.
     */
    public Fitness getFitness() {
        return this.fitness;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(OptimisationSolution other) {
        return this.fitness.compareTo(other.fitness);
    }
}
