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
 * This class represents an output in Athena Service.
 * 
 * @author Pedro Almir
 *
 */
public class Output extends Put{
	/**
	 * This field represents a list of values that this
	 * output contains.
	 */
	private TypeList output;
	
	/**
	 * Default constructor
	 */
	public Output() {
		this.output = new TypeList();
	}
	
	/**
	 * @param output
	 */
	public Output(String name, String identifier, Type type, String representation, boolean multipleValues,
			List<Validation> validations) {
		super(name, identifier, type, representation, multipleValues, validations);
		this.output = new TypeList();
	}
	
	/**
	 * @param type
	 */
	public void addValue(Type type){
		this.output.add(type);
	}

	/**
	 * @return the values
	 */
	public List<Type> getValues() {
		return output.getComponents();
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(List<Type> values) {
		this.output.addAll(values);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Output [values=" + output + "]";
	}
}
