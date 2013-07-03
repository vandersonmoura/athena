package com.pedroalmir.athena.core.stoppingCondition;

import com.google.common.base.Preconditions;

/**
 * A stopping predicate used to stop an algorithm when a measurement is greater than or equal to a value.
 */
public class Maximum implements CompletionCalculator {
    
    private double percentage;
    
    public Maximum() {
        this.percentage = 0.0;
    }

    public double getPercentage(double actualValue, double targetValue) {
        Preconditions.checkArgument(targetValue != 0.0, "targetValue cannot be zero.");
        percentage = Math.max(percentage, actualValue / targetValue);
        return Math.max(Math.min(percentage, 1.0), 0.0);
    }

    public boolean apply(double actualValue, double targetValue) {
        return actualValue >= targetValue;
    }
}
