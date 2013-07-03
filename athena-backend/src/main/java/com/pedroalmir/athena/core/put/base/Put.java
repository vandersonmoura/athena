/**
 * 
 */
package com.pedroalmir.athena.core.put.base;

import java.util.List;

import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.core.validation.Validation;

/**
 * This class represents any put to any Athena Bundle.
 * 
 * @author Pedro Almir
 *
 */
public class Put {
	/**
	 * Put name
	 * This field represents the name to show in view
	 */
	private String name;
	/**
	 * Put identifier
	 * This field must be unique in same module
	 */
	private String identifier;
	/**
	 * Put type
	 */
	private Type type;
	/**
	 * Put representation
	 * For example:
	 * List[valueA, valueB]
	 */
	private String representation;
	/**
	 * If this field is <code>true</code> so this
	 * Put may have multiple values.
	 */
	private boolean multipleValues;
	/**
	 * List of view validations
	 */
	private List<Validation> validations;
	
	/**
	 * Default Constructor
	 */
	public Put() {
		
	}
	
	/**
	 * @param name
	 * @param identifier
	 * @param type
	 * @param representation
	 * @param multipleValues
	 * @param validations
	 */
	public Put(String name, String identifier, Type type, String representation, boolean multipleValues,
			List<Validation> validations) {
		this.name = name;
		this.identifier = identifier;
		this.type = type;
		this.representation = representation;
		this.multipleValues = multipleValues;
		this.validations = validations;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}
	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}
	/**
	 * @return the representation
	 */
	public String getRepresentation() {
		return representation;
	}
	/**
	 * @param representation the representation to set
	 */
	public void setRepresentation(String representation) {
		this.representation = representation;
	}
	/**
	 * @return the multipleValues
	 */
	public boolean isMultipleValues() {
		return multipleValues;
	}
	/**
	 * @param multipleValues the multipleValues to set
	 */
	public void setMultipleValues(boolean multipleValues) {
		this.multipleValues = multipleValues;
	}
	/**
	 * @return the validations
	 */
	public List<Validation> getValidations() {
		return validations;
	}
	/**
	 * @param validations the validations to set
	 */
	public void setValidations(List<Validation> validations) {
		this.validations = validations;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Put [name=" + name + ", identifier=" + identifier + ", type=" + type + ", representation=" + representation
				+ ", multipleValues=" + multipleValues + ", validations=" + validations + "]";
	}
}
