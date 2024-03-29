package com.pedroalmir.athena.core.type.base;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * This class describes the boundaries of a particular type.
 */
public final class Bounds {
    /**
     * This field represents the lower bound
     */
    private final double lowerBound;
    /**
     * This field represents the upper bound
     */
    private final double upperBound;

    /**
     * @param lowerBound
     * @param upperBound
     */
    public Bounds(double lowerBound, double upperBound) {
        checkArgument(lowerBound <= upperBound, "Bounds range violation, " + lowerBound + " should be less than " + upperBound);

        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    /**
     * @return lowerBound
     */
    public double getLowerBound() {
        return this.lowerBound;
    }

    /**
     * @return upperBound
     */
    public double getUpperBound() {
        return this.upperBound;
    }

    /**
     * @return range
     */
    public double getRange() {
        return Math.abs(upperBound - lowerBound);
    }

    /**
     * @param value
     * @return <code>true</code> if value is inside bounds
     */
    public boolean isInsideBounds(double value) {
        if (Double.compare(value, upperBound) <= 0 && Double.compare(value, lowerBound) >= 0) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bounds other = (Bounds) obj;
        if (this.lowerBound != other.lowerBound) {
            return false;
        }
        if (this.upperBound != other.upperBound) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.lowerBound) ^ (Double.doubleToLongBits(this.lowerBound) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.upperBound) ^ (Double.doubleToLongBits(this.upperBound) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("(").append(lowerBound).append(":").append(upperBound).append(")")
            .toString();
    }

}
