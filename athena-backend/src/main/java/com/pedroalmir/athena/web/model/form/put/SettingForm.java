package com.pedroalmir.athena.web.model.form.put;

import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.web.model.form.type.TypeForm;

/**
 * @author Pedro Almir
 *
 */
public class SettingForm {
	/**
	 * 
	 */
	private boolean required;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String identifier;
	/**
	 * 
	 */
	private TypeForm type;
	/**
	 * 
	 */
	private boolean multipleValue;

	/**
	 * @param required
	 * @param name
	 * @param identifier
	 * @param type
	 * @param multipleValue
	 */
	public SettingForm(boolean required, String name, String identifier, TypeForm type, boolean multipleValue) {
		super();
		this.required = required;
		this.name = name;
		this.identifier = identifier;
		this.type = type;
		this.multipleValue = multipleValue;
	}

	public SettingForm(Setting set) {
		// TODO Auto-generated constructor stub
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
	public TypeForm getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TypeForm type) {
		this.type = type;
	}

	/**
	 * @return the multipleValue
	 */
	public boolean isMultipleValue() {
		return multipleValue;
	}

	/**
	 * @param multipleValue the multipleValue to set
	 */
	public void setMultipleValue(boolean multipleValue) {
		this.multipleValue = multipleValue;
	}
	

}
