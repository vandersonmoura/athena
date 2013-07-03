package com.pedroalmir.athena.core.stoppingCondition;

import com.pedroalmir.athena.core.algorithm.Algorithm;
import com.pedroalmir.athena.core.measurement.Measurement;
import com.pedroalmir.athena.core.measurement.generic.Iterations;
import com.pedroalmir.athena.core.stoppingCondition.base.StoppingCondition;
import com.pedroalmir.athena.core.type.numeric.base.Numeric;

/**
 * A stopping condition which uses measurements to determine if an algorithm has completed e.g.
 * MeasuredStoppingCondition(new Iterations(), new Maximum(), 1000) will stop an algorithm once the number of
 * iterations is greater than or equal to 1000, MeasuredStoppingCondition(new Diversity(), new Minimum(), 0.01)
 * stops it when the population's diversity is less than or equal to 0.01, etc.
 */
public class MeasuredStoppingCondition implements StoppingCondition<Algorithm> {
    
    /**
	 * 
	 */
	private CompletionCalculator predicate;
    private double target;
    private Measurement<? extends Numeric> measurement;
    
    public MeasuredStoppingCondition() {
        this(new Iterations(), new Maximum(), 1000);
    }
    
    public MeasuredStoppingCondition(MeasuredStoppingCondition rhs) {
        this.measurement = rhs.measurement.getClone();
        this.predicate = rhs.predicate;
        this.target = rhs.target;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public MeasuredStoppingCondition(Measurement measurement, CompletionCalculator predicate, double target) {
        this.measurement = measurement;
        this.predicate = predicate;
        this.target = target;
    }

    /**
     * {@inheritDoc}
     */
    public MeasuredStoppingCondition getClone() {
        return new MeasuredStoppingCondition(this);
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
