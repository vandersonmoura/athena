/**
 * 
 */
package com.pedroalmir.athena.web.model.form.put;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.core.validation.Validation;
import com.pedroalmir.athena.web.model.form.put.base.PutForm;
import com.pedroalmir.athena.web.model.form.type.TypeForm;

/**
 * @author Pedro Almir
 *
 */
public class OutputForm extends PutForm{

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
	public OutputForm(String name, String identifier, String type, String representation, boolean multipleValues,
			List<Validation> validations, List<TypeForm> components, boolean linked) {
		super(name, identifier, type, representation, multipleValues, validations);
		this.components = components;
		this.linked = linked;
	}
	
	/**
	 * @param out
	 */
	public OutputForm(Output out) {
		super(out.getName(), out.getIdentifier(), out.getType().toString(), out.getRepresentation(), 
				out.isMultipleValues(), out.getValidations());
		
		this.components = new LinkedList<TypeForm>();
		for(Type t : out.getValues()){
			this.components.add(new TypeForm(t.getValue().toString()));
		}
		
		this.linked = out.isLinked();
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
		return "OutputForm [components=" + components + ", linked=" + linked + "]";
	}
}
