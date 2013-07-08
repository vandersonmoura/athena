package com.pedroalmir.athena.core.problem;

import com.pedroalmir.athena.core.problem.objective.Minimise;
import com.pedroalmir.athena.core.problem.objective.Objective;
import com.pedroalmir.athena.core.solution.fitness.Fitness;
import com.pedroalmir.athena.core.solution.fitness.InferiorFitness;
import com.pedroalmir.athena.core.type.base.Type;


/**
 * This is a convenience class that keeps track of the number of fitness
 * evaluations. This class can be extended instead of implementing
 * {@link Problem} directly.
 * <p>
 * The contract of returning an instance of {@link InferiorFitness} for solutions
 * outside  the problem search space is implemented by {@link #getFitness(Type)}.
 */
public abstract class AbstractProblem implements Problem {

    protected Objective objective;

    protected AbstractProblem() {
        this.objective = new Minimise();
    }

    protected AbstractProblem(AbstractProblem copy) {
        this.objective = copy.objective;
    }

    public abstract AbstractProblem getClone();

    /**
     * Determine the {@code Fitness} of the current {@link Problem} instance
     * based on the provided {@code solution}.
     *
     * @param solution  The {@linkplain Type} representing the candidate solution.
     * @return          The {@linkplain Fitness} of the {@code solution} in the
     *                  current {@linkplain Problem}.
     */
    protected abstract Fitness calculateFitness(Type solution);

    /**
     * {@inheritDoc}
     */
    public final Fitness getFitness(Type solution) {
        return calculateFitness(solution);
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public Objective getObjective() {
        return objective;
    }
}
