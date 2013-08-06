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
	 * 
	 */
	private static final long serialVersionUID = -3685429284838660600L;
	/**
	 * This field represents a list of values that this
	 * input contains.
	 */
	private TypeList input;
	/**
	 * 
	 */
	private boolean linked;
	
	/**
	 * Default constructor
	 */
	public Input() {
		this.input = new TypeList();
	}
	
	/**
	 * @param input
	 */
	public Input(Input copy) {
		super(copy.getName(), copy.getIdentifier(), copy.getType(), copy.getRepresentation(), 
				copy.isMultipleValues(), copy.getValidations());
		this.id = copy.getId();
		this.input = copy.input.getClone();
		this.linked = copy.isLinked();
	}
	
	/**
	 * @param values
	 */
	public Input(String name, String identifier, Type type, String representation, boolean multipleValues,
			List<Validation> validations) {
		super(name, identifier, type, representation, multipleValues, validations);
		this.input = new TypeList();
		this.linked = false;
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
	
	/**
	 * 
	 */
	public void clear(){
		this.input.clear();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Input clone() throws CloneNotSupportedException {
		return new Input(this);
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
