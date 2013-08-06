package com.pedroalmir.athena.core.type.numeric;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Random;

import com.pedroalmir.athena.core.type.base.Bounds;
import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.core.type.numeric.base.Numeric;

public class Real implements Numeric {

    /**
	 * 
	 */
	private static final Bounds DEFAULT_BOUND = new Bounds(-Double.MAX_VALUE, Double.MAX_VALUE);
    private double value;
    private final Bounds bounds;

    public static Real valueOf(double value) {
        return new Real(value);
    }

    public static Real valueOf(double value, Bounds bounds) {
        return new Real(value, bounds);
    }

    /**
     * Create the instance with the given value.
     * @param value The value of the {@linkplain Real}.
     */
    private Real(double value) {
        this.value = value;
        this.bounds = DEFAULT_BOUND;
    }

    /**
     * Create the <code>Real</code> instance with the defined {@code Bounds}.
     * @param value The initial value.
     * @param bounds The defined {@code Bounds}.
     */
    private Real(double value, Bounds bounds) {
        this.value = value;
        this.bounds = checkNotNull(bounds);
    }

    /**
     * Copy constructor.
     * @param copy The instance to copy.
     */
    private Real(Real copy) {
        this.value = copy.value;
        this.bounds = copy.bounds;
    }

    /**
     * {@inheritDoc}
     */
    
    public Real getClone() {
        return Real.valueOf(value, bounds);
    }

    /**
     * {@inheritDoc}
     */
    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }

        Real otherReal = (Real) obj;
        return Double.compare(this.value, otherReal.value) == 0
                && this.bounds.equals(otherReal.bounds);
    }

    /**
     * {@inheritDoc}
     */
    
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.bounds.hashCode();
        hash = 31 * hash + Double.valueOf(this.value).hashCode();
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    
    public boolean booleanValue() {
        return Double.compare(this.value, 0.0) == 0 ? false : true;
    }

    /**
     * {@inheritDoc}
     */
    
    public int intValue() {
        int result = Double.compare(value, 0.0);
        return (result >= 0)
                ? Double.valueOf(Math.ceil(value)).intValue()
                : Double.valueOf(Math.floor(value)).intValue();
    }

    /**
     * {@inheritDoc}
     */
    
    public double doubleValue() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    
    public int compareTo(Numeric o) {
        final Real otherReal = (Real) o;
        return Double.compare(this.value, otherReal.value);
    }

    /**
     * Re-randomize the <code>Real</code> object based on the upper and lower bounds.
     */
    
    public void randomise() {
    	Random rand = new Random();
        this.value = rand.nextDouble() * (bounds.getUpperBound() - bounds.getLowerBound()) + bounds.getLowerBound();
    }

    /**
     * Return a <code>String</code> representation of the <code>Real</code> object.
     * @return A <code>String</code> representing the object instance.
     */
    
    public String toString() {
        return String.valueOf(this.value);
    }

    /**
     * Get the type representation of this <tt>Real</tt> object as a string.
     *
     * @return The String representation of this <tt>Type</tt> object.
     */
    
    public String getRepresentation() {
        return "R" + this.bounds.toString();
    }

    
    public Bounds getBounds() {
        return this.bounds;
    }

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object object) {
		this.value = (Double) object;
	}

	public void setValue(String object) {
		this.value = Double.valueOf(checkNotNull(object));
	}

	public Type getClone(String object) {
		return new Real(Double.valueOf(checkNotNull(object)));
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
