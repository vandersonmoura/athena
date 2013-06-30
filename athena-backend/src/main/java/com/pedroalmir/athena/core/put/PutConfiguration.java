/**
 * 
 */
package com.pedroalmir.athena.core.put;

import java.util.List;

import com.pedroalmir.athena.core.type.Type;

/**
 * This class represents the minimum and maximum number of input/output module may have, 
 * as well as the associated types.
 * 
 * @author Pedro Almir
 *
 */
public class PutConfiguration {
	/**
	 * 
	 */
	private int minimum;
	/**
	 * 
	 */
	private int maximum;
	/**
	 * 
	 */
	private List<Type> types;
	/**
	 * @return the minimum
	 */
	public int getMinimum() {
		return minimum;
	}
	/**
	 * @param minimum the minimum to set
	 */
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	/**
	 * @return the maximum
	 */
	public int getMaximum() {
		return maximum;
	}
	/**
	 * @param maximum the maximum to set
	 */
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	/**
	 * @return the types
	 */
	public List<Type> getTypes() {
		return types;
	}
	/**
	 * @param types the types to set
	 */
	public void setTypes(List<Type> types) {
		this.types = types;
	}
}
