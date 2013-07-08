/**
 * 
 */
package com.pedroalmir.athena.impl.fuzzy.fitness;

import com.pedroalmir.athena.core.solution.fitness.Fitness;
import com.pedroalmir.athena.core.type.base.Type;

/**
 * @author Pedro Almir
 *
 */
public class FuzzySolutionFitness implements Fitness {
	
	private final double value;
	
	/**
	 * @param value
	 */
	public FuzzySolutionFitness(double value) {
		super();
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.type.base.Type#getClone(java.lang.String)
	 */
	@Override
	public Type getClone(String object) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.type.base.Type#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(Object object) {
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.type.base.Type#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String object) {
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.type.base.Type#getRepresentation()
	 */
	@Override
	public Object getRepresentation() {
		return "real";
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Fitness o) {
		return this.compareTo(o);
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.solution.Fitness#getClone()
	 */
	@Override
	public Fitness getClone() {
		return new FuzzySolutionFitness(this.value);
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.solution.Fitness#getValue()
	 */
	@Override
	public Double getValue() {
		return this.value;
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.solution.Fitness#newInstance(java.lang.Double)
	 */
	@Override
	public Fitness newInstance(Double value) {
		return new FuzzySolutionFitness(this.value);
	}

}
