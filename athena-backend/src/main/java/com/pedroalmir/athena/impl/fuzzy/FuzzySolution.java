package com.pedroalmir.athena.impl.fuzzy;

import java.util.List;

import com.pedroalmir.athena.core.solution.Solution;
import com.pedroalmir.athena.core.solution.fitness.Fitness;
import com.pedroalmir.athena.core.type.base.Type;


/**
 * This class represents a solution to an Fuzzy logic Problem.
 */
public final class FuzzySolution implements Solution, Comparable<FuzzySolution> {

    /**
     * List of inputs variables
     */
    private final List<Type> inputs;
    /**
     * List of output variables
     */
    private final List<Type> outputs;
    /**
     * Sum of all output values
     */
    private final Fitness fitness;
    
    /**
     * @param inputs
     * @param outputs
     * @param fitness
     */
    public FuzzySolution(List<Type> inputs, List<Type> outputs, Fitness fitness) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.fitness = fitness;
    }
    
    /**
     * @param inputs
     * @param outputs
     */
    public FuzzySolution(List<Type> inputs, List<Type> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.fitness = calcFitness(this.outputs);
    }

    /**
     * Calculate the fitness of solution
     * 
     * @param outputs
     * @return fitness
     */
    private Fitness calcFitness(List<Type> outputs) {
    	double amount = 0.0;
    	for(Type t : outputs){
    		amount += (Double) t.getValue();
    	}
		return new FuzzySolutionFitness(amount);
	}

	/**
     * @param copy
     */
    public FuzzySolution(FuzzySolution copy) {
    	this.inputs = copy.inputs;
        this.outputs = copy.outputs;
        this.fitness = copy.fitness;
    }

    /**
     * @return
     */
    public FuzzySolution getClone() {
        return new FuzzySolution(this);
    }

    /**
     * Returns the fitness of this solution according to Fuzzy Problem.
     * Calling this function does not contribute to the number of fitness
     * evaluations maintained by the Fuzzy Problem.
     *
     * @return The fitness of this solution.
     */
    public Fitness getFitness() {
        return this.fitness;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(FuzzySolution other) {
        return this.fitness.compareTo(other.fitness);
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fitness == null) ? 0 : fitness.hashCode());
		result = prime * result + ((inputs == null) ? 0 : inputs.hashCode());
		result = prime * result + ((outputs == null) ? 0 : outputs.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuzzySolution other = (FuzzySolution) obj;
		if (fitness == null) {
			if (other.fitness != null)
				return false;
		} else if (!fitness.equals(other.fitness))
			return false;
		if (inputs == null) {
			if (other.inputs != null)
				return false;
		} else if (!inputs.equals(other.inputs))
			return false;
		if (outputs == null) {
			if (other.outputs != null)
				return false;
		} else if (!outputs.equals(other.outputs))
			return false;
		return true;
	}
}
