/**
 * 
 */
package com.pedroalmir.athena.core.put;

import java.util.ArrayList;
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
	private List<Type> availableTypes;
	
	/**
	 * Default constructor 
	 */
	public PutConfiguration() {
		availableTypes = new ArrayList<Type>();
	}
	
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
	public List<Type> getAvailableTypes() {
		return availableTypes;
	}
	/**
	 * @param types the types to set
	 */
	public void setAvailableTypes(List<Type> types) {
		this.availableTypes = types;
	}
	/**
	 * Add available type
	 * @param type
	 */
	public void addAvailableType(Type type){
		this.availableTypes.add(type);
	}
}
