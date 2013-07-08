package com.pedroalmir.athena.impl.fuzzy.stoppingCondition;

import java.util.List;

import com.pedroalmir.athena.core.algorithm.Algorithm;
import com.pedroalmir.athena.core.measurement.Measurement;
import com.pedroalmir.athena.core.measurement.generic.Iterations;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.stoppingCondition.CompletionCalculator;
import com.pedroalmir.athena.core.stoppingCondition.Maximum;
import com.pedroalmir.athena.core.stoppingCondition.base.StoppingCondition;
import com.pedroalmir.athena.core.type.numeric.base.Numeric;

/**
 * A stopping condition which uses measurements to determine if an algorithm has completed e.g.
 * MeasuredStoppingCondition(new Iterations(), new Maximum(), 1000) will stop an algorithm once the number of
 * iterations is greater than or equal to 1000, MeasuredStoppingCondition(new Diversity(), new Minimum(), 0.01)
 * stops it when the population's diversity is less than or equal to 0.01, etc.
 */
public class FuzzyCompleteEvaluationStoppingCondition implements StoppingCondition<Algorithm> {
    
    /**
	 * Predicate
	 */
	private CompletionCalculator predicate;
    /**
     * Target value
     */
    private double target;
    /**
     * Measurement
     */
    private Measurement<? extends Numeric> measurement;
    
    /**
     * Fuzzy Complete Evaluation Stopping Condition
     * Measurement: Iterations
     * Predicate: Maximum
     * Target: number of value in first input
     * 
     * @param inputs
     * 			List of inputs to algorithm
     */
    public FuzzyCompleteEvaluationStoppingCondition(List<Input> inputs) {
        this(new Iterations(), new Maximum(), inputs.get(0).getValues().size());
    }
    
    private FuzzyCompleteEvaluationStoppingCondition(FuzzyCompleteEvaluationStoppingCondition rhs) {
        this.measurement = rhs.measurement.getClone();
        this.predicate = rhs.predicate;
        this.target = rhs.target;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private FuzzyCompleteEvaluationStoppingCondition(Measurement measurement, CompletionCalculator predicate, double target) {
        this.measurement = measurement;
        this.predicate = predicate;
        this.target = target;
    }

    /**
     * {@inheritDoc}
     */
    public FuzzyCompleteEvaluationStoppingCondition getClone() {
        return new FuzzyCompleteEvaluationStoppingCondition(this);
    }

    public double getPercentageCompleted(Algorithm algorithm) {
        return predicate.getPercentage(measurement.getValue(algorithm).doubleValue(), target);
    }

    public boolean apply(Algorithm algorithm) {
        return predicate.apply(measurement.getValue(algorithm).doubleValue(), target);
    }

    public double getTarget() {
        return target;
    }

    public CompletionCalculator getPredicate() {
        return predicate;
    }

    public Measurement<? extends Numeric> getMeasurement() {
        return measurement;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public void setPredicate(CompletionCalculator predicate) {
        this.predicate = predicate;
    }

    public void setMeasurement(Measurement<? extends Numeric> measurement) {
        this.measurement = measurement;
    }
}
