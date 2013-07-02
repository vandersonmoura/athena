/**
 * 
 */
package com.pedroalmir.athena.core.put;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.put.base.Put;
import com.pedroalmir.athena.core.type.Type;
import com.pedroalmir.athena.core.validation.Validation;


/**
 * This class represents an input in Athena Service.
 * 
 * @author Pedro Almir
 *
 */
public class Input extends Put {
	/**
	 * This field represents a list of values that this
	 * input contains.
	 */
	private List<Type> values;
	
	/**
	 * Default constructor
	 */
	public Input() {
		this.values = new LinkedList<Type>();
	}
	
	/**
	 * @param values
	 */
	public Input(String name, String identifier, Type type, String representation, boolean multipleValues,
			List<Validation> validations) {
		super(name, identifier, type, representation, multipleValues, validations);
		this.values = new LinkedList<Type>();
	}
	
	/**
	 * @param type
	 */
	public void addValue(Type type){
		this.values.add(type);
	}

	/**
	 * @return the values
	 */
	public List<Type> getValues() {
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(List<Type> values) {
		this.values = values;
	}

}
