/**
 * 
 */
package com.pedroalmir.athena.impl.fuzzy.fitness;

import com.pedroalmir.athena.core.solution.fitness.Fitness;
import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.web.model.vo.type.TypeVO;

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
	
	public Type getClone(String object) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.type.base.Type#setValue(java.lang.Object)
	 */
	
	public void setValue(Object object) {
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.type.base.Type#setValue(java.lang.String)
	 */
	
	public void setValue(String object) {
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.type.base.Type#getRepresentation()
	 */
	
	public Object getRepresentation() {
		return "real";
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	
	public int compareTo(Fitness o) {
		return this.compareTo(o);
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.solution.Fitness#getClone()
	 */
	
	public Fitness getClone() {
		return new FuzzySolutionFitness(this.value);
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.solution.Fitness#getValue()
	 */
	
	public Double getValue() {
		return this.value;
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.solution.Fitness#newInstance(java.lang.Double)
	 */
	
	public Fitness newInstance(Double value) {
		return new FuzzySolutionFitness(this.value);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TypeVO getTypeVO() {
		// TODO Auto-generated method stub
		return null;
	}

}
