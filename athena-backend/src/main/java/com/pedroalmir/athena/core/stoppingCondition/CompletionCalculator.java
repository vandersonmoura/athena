package com.pedroalmir.athena.core.stoppingCondition;

/**
 * An interface defining the predicate to use with a MeasuredStoppingCondition.
 */
public interface CompletionCalculator {
    
    public double getPercentage(double actualValue, double targetValue);
    
    public boolean apply(double actualValue, double targetValue);
}
