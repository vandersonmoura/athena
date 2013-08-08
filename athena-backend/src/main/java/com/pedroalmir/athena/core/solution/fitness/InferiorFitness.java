package com.pedroalmir.athena.core.solution.fitness;

import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.web.model.vo.type.TypeVO;

/**
 * This class is used to represent a fitness value that is always inferior.
 * <p />
 * This class is a singleton.
 *
 */
public final class InferiorFitness implements Fitness {

	private InferiorFitness() {
    }

    /**
     * Get the cloned instance of this object. Due to this object being a
     * Singleton, the same instance is returned and is not cloned.
     */
    public InferiorFitness getClone() {
        return instance;
    }

    /**
     * Always returns null. <code>InferiorFitness</code> does not have a value. The
     * most sensible value to return is Double.NaN as it is still a value, however,
     * it represents something that is not a number (effectively null). Returning
     * Double.NaN will ensure that some of the Measurements do get an value, even if
     * the value is Double.NaN
     *
     * @return Double.NaN as the value is always inferior.
     */
    public Double getValue() {
        return Double.NaN;
    }

    /**
     * {@inheritDoc}
     */
    public final Fitness newInstance(Double value) {
        return instance();
    }

    /**
     * Returns -1, unless other is also the <code>InferiorFitness</code> instance.
     *
     * @param other The {@code Fitness} to compare.
     * @return 0 if other is <code>InferiorFitness</code> instance, -1 otherwise.
     */
    public int compareTo(Fitness other) {
        return (this == other) ? 0 : -1;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if ((object == null) || (this.getClass() != object.getClass()))
            return false;

        Fitness otherFitness = (Fitness) object;
        return getValue().equals(otherFitness.getValue());
    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + getValue().hashCode();
        return hash;
    }

    /**
     * Obtain a reference to the <code>InferiorFitness</code> instance.
     *
     * @return the <code>InferiorFitness</code> instance.
     */
    public static Fitness instance() {
        return instance;
    }

    private static InferiorFitness instance = new InferiorFitness();

	public Type getClone(String object) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setValue(Object object) {
		// TODO Auto-generated method stub
		
	}

	public void setValue(String object) {
		// TODO Auto-generated method stub
		
	}

	public Object getRepresentation() {
		// TODO Auto-generated method stub
		return null;
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
