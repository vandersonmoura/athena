/**
 * 
 */
package com.pedroalmir.athena.core.put.base;

import java.io.Serializable;
import java.util.List;

import com.pedroalmir.athena.common.model.EntityIdFactory;
import com.pedroalmir.athena.common.model.GenericEntity;
import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.core.validation.Validation;

/**
 * This class represents any put to any Athena Bundle.
 * 
 * @author Pedro Almir
 *
 */
public abstract class Put extends GenericEntity implements Cloneable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8803610574525928897L;
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
		/* TODO: Change to hibernate generate ID */
		this.id = EntityIdFactory.getNextId();
		this.name = name;
		this.identifier = identifier;
		this.type = type;
		this.representation = representation;
		this.multipleValues = multipleValues;
		this.validations = validations;
	}
	
	/**
	 * @param copy
	 */
	public Put(Put copy) {
		this.id = copy.getId();
		this.name = copy.getName();
		this.identifier = copy.getIdentifier();
		this.type = copy.getType();
		this.representation = copy.getRepresentation();
		this.multipleValues = copy.isMultipleValues();
		this.validations = copy.getValidations();
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((identifier == null) ? 0 : identifier.hashCode());
		result = prime * result + (multipleValues ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((representation == null) ? 0 : representation.hashCode());
		result = prime * result
				+ ((validations == null) ? 0 : validations.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Put other = (Put) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		if (multipleValues != other.multipleValues)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (representation == null) {
			if (other.representation != null)
				return false;
		} else if (!representation.equals(other.representation))
			return false;
		if (validations == null) {
			if (other.validations != null)
				return false;
		} else if (!validations.equals(other.validations))
			return false;
		return true;
	}
}
