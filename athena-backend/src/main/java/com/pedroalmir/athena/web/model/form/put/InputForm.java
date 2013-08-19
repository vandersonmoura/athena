/**
 * 
 */
package com.pedroalmir.athena.web.model.form.put;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.core.validation.Validation;
import com.pedroalmir.athena.web.model.form.put.base.PutForm;
import com.pedroalmir.athena.web.model.form.type.TypeForm;

/**
 * @author Pedro Almir
 *
 */
public class InputForm extends PutForm{
	
	/**
	 * Name in lower case
	 */
	private String frontIdentifier;
	/**
	 * Front name
	 */
	private String frontName;
	/**
	 * Selected type
	 */
	private String frontType;
	/**
	 * 
	 */
	private List<TypeForm> components;
	
	/**
	 * 
	 */
	private boolean linked;
	
	/**
	 * @param name
	 * @param identifier
	 * @param type
	 * @param representation
	 * @param multipleValues
	 * @param validations
	 * @param components
	 * @param linked
	 */
	public InputForm(String name, String identifier, String type, String representation, boolean multipleValues,
			List<Validation> validations, List<TypeForm> components, boolean linked) {
		super(name, identifier, type, representation, multipleValues, validations);
		this.components = components;
		this.linked = linked;
	}

	/**
	 * @param in
	 */
	public InputForm(Input in) {
		super(in.getName(), in.getIdentifier(), in.getType().toString(), in.getRepresentation(), 
				in.isMultipleValues(), in.getValidations());
		
		this.components = new LinkedList<TypeForm>();
		for(Type t : in.getValues()){
			this.components.add(new TypeForm(t.getValue().toString()));
		}
		
		this.linked = in.isLinked();
	}

	/**
	 * @return the components
	 */
	public List<TypeForm> getComponents() {
		return components;
	}

	/**
	 * @param components the components to set
	 */
	public void setComponents(List<TypeForm> components) {
		this.components = components;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InputForm [components=" + components + ", linked=" + linked + "]";
	}

	/**
	 * @return the frontIdentifier
	 */
	public String getFrontIdentifier() {
		return frontIdentifier;
	}

	/**
	 * @param frontIdentifier the frontIdentifier to set
	 */
	public void setFrontIdentifier(String frontIdentifier) {
		this.frontIdentifier = frontIdentifier;
	}

	/**
	 * @return the frontName
	 */
	public String getFrontName() {
		return frontName;
	}

	/**
	 * @param frontName the frontName to set
	 */
	public void setFrontName(String frontName) {
		this.frontName = frontName;
	}

	/**
	 * @return the frontType
	 */
	public String getFrontType() {
		return frontType;
	}

	/**
	 * @param frontType the frontType to set
	 */
	public void setFrontType(String frontType) {
		this.frontType = frontType;
	}
	
	
}
