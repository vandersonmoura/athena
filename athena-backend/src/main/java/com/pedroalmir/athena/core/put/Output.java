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
	 * 
	 */
	private static final long serialVersionUID = -5213206602913321699L;
	/**
	 * This field represents a list of values that this
	 * output contains.
	 */
	private TypeList output;
	/**
	 * 
	 */
	private boolean linked;
	
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
		this.linked = false;
	}
	
	/**
	 * @param input
	 */
	public Output(Output copy) {
		super(copy.getName(), copy.getIdentifier(), copy.getType(), copy.getRepresentation(), 
				copy.isMultipleValues(), copy.getValidations());
		this.id = copy.getId();
		this.output = copy.output.getClone();
		this.linked = copy.isLinked();
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
	
	/**
	 * 
	 */
	public void clear(){
		this.output.clear();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Output [identifier=" + this.getIdentifier() + ", values=" + output + "]";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Output clone() throws CloneNotSupportedException {
		return new Output(this);
	}

	/**
	 * @return the linked
	 */
	public boolean isLinked() {
		return linked;
	}

	/**
	 * @param linked the linked to set
	 */
	public void setLinked(boolean linked) {
		this.linked = linked;
	}
}
