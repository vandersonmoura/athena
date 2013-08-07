package com.pedroalmir.athena.web.model.form.put;

import java.util.List;

import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.validation.Validation;
import com.pedroalmir.athena.web.model.form.put.base.PutForm;

/**
 * @author Pedro Almir
 *
 */
public class SettingForm extends PutForm{
	
	/**
	 * 
	 */
	private boolean required;
	
	/**
	 * @param name
	 * @param identifier
	 * @param type
	 * @param representation
	 * @param multipleValues
	 * @param validations
	 */
	public SettingForm(String name, String identifier, String type,
			String representation, boolean multipleValues,
			List<Validation> validations) {
		super(name, identifier, type, representation, multipleValues, validations);
		this.required = false;
	}

	/**
	 * @param set
	 */
	public SettingForm(Setting set) {
		super(set.getName(), set.getIdentifier(), set.getType().toString(), set.getRepresentation(), 
				set.isMultipleValues(), set.getValidations());
		this.required = set.isRequired();
	}

	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * @param required the required to set
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SettingForm [required=" + required + "]";
	}

}
