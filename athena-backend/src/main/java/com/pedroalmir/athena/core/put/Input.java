/**
 * 
 */
package com.pedroalmir.athena.core.put;

import java.util.List;

import com.pedroalmir.athena.core.put.base.Put;
import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.core.type.container.TypeList;
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
	private TypeList input;
	
	/**
	 * Default constructor
	 */
	public Input() {
		this.input = new TypeList();
	}
	
	/**
	 * @param values
	 */
	public Input(String name, String identifier, Type type, String representation, boolean multipleValues,
			List<Validation> validations) {
		super(name, identifier, type, representation, multipleValues, validations);
		this.input = new TypeList();
	}
	
	/**
	 * @param type
	 */
	public void addValue(Type type){
		this.input.add(type);
	}

	/**
	 * @return the values
	 */
	public List<Type> getValues() {
		return input.getComponents();
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(List<Type> values) {
		this.input.addAll(values);
	}

}
