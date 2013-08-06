package com.pedroalmir.athena.core.type.numeric;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Random;

import com.pedroalmir.athena.core.type.base.Bounds;
import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.core.type.numeric.base.Numeric;

public class Int implements Numeric {

	/**
	 * 
	 */
	private static final Bounds DEFAULT_BOUND = new Bounds(Integer.MIN_VALUE, Integer.MAX_VALUE);
	private int value;
	private final Bounds bounds;

	public static Int valueOf(int value) {
		return new Int(value);
	}

	public static Int valueOf(int value, Bounds bounds) {
		return new Int(value, bounds);
	}

	public static Int valueOf(double value, Bounds bounds) {
		int result = 0;
		// value is bigger or is equal
		if (Double.compare(0, value) <= 0) {
			result = Double.valueOf(Math.ceil(value)).intValue();
		} else {
			result = Double.valueOf(Math.floor(value)).intValue();
		}

		return new Int(result, bounds);
	}

	/**
	 * Create an {@linkplain Int} with the specified value.
	 * 
	 * @param value
	 *            The value of the {@linkplain Int}.
	 */
	private Int(int value) {
		this.value = value;
		this.bounds = DEFAULT_BOUND;
	}

	/**
	 * Create an instance of {@linkplain Int} with the defined bounds.
	 * 
	 * @param bounds
	 *            The defined {@code Bounds}.
	 */
	private Int(int value, Bounds bounds) {
		this.value = value;
		this.bounds = checkNotNull(bounds);
	}

	/**
	 * Create a copy of the provided instance.
	 * 
	 * @param copy
	 *            The instance to copy.
	 */
	private Int(Int copy) {
		this.value = copy.value;
		this.bounds = copy.bounds;
	}

	/**
	 * {@inheritDoc}
	 */
	public Int getClone() {
		return new Int(this);
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

		Int otherInt = (Int) obj;
		return (this.value == otherInt.value) && this.bounds.equals(otherInt.bounds);
	}

	/**
	 * Return the value of the object itself. This is accordance to the manner
	 * in which {@link java.lang.Integer#hashCode()} operates.
	 * 
	 * @return The value of this Int representation.
	 */

	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + this.bounds.hashCode();
		hash = 31 * hash + Integer.valueOf(this.value).hashCode();
		return hash;
	}

	/**
	 * {@inheritDoc}
	 */

	public boolean booleanValue() {
		return (this.value == 0) ? false : true;
	}

	/**
	 * {@inheritDoc}
	 */

	public int intValue() {
		return this.value;
	}

	/**
	 * {@inheritDoc}
	 */

	public double doubleValue() {
		return Integer.valueOf(value).doubleValue();
	}

	/**
	 * {@inheritDoc}
	 */

	public int compareTo(Numeric other) {
		if (this.value == other.intValue()) {
			return 0;
		} else {
			return (other.intValue() < this.value) ? 1 : -1;
		}
	}

	/**
	 * {@inheritDoc}
	 */

	public void randomise() {
		Random rand = new Random();
		double tmp = rand.nextDouble() * (getBounds().getUpperBound() - getBounds().getLowerBound()) + getBounds().getLowerBound();
		this.value = Double.valueOf(tmp).intValue();
	}

	/**
	 * {@inheritDoc}
	 */

	public String toString() {
		return String.valueOf(this.value);
	}

	/**
	 * Get the type representation of this <tt>Int</tt> object as a string.
	 * 
	 * @return The String representation of this <tt>Type</tt> object.
	 */

	public String getRepresentation() {
		return "Z" + this.bounds.toString();
	}

	public Bounds getBounds() {
		return this.bounds;
	}

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object object) {
		this.value = (Integer) object;
	}

	public void setValue(String object) {
		this.value = Integer.valueOf(checkNotNull(object));
	}

	public Type getClone(String object) {
		return new Int(Integer.valueOf(checkNotNull(object)));
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
