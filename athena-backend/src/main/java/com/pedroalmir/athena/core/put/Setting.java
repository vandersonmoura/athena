/**
 * 
 */
package com.pedroalmir.athena.core.put;

import java.util.List;

import com.pedroalmir.athena.core.put.base.Put;
import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.core.validation.Validation;

/**
 * This class represents a setting for any algorithm or module.
 * <br>
 * In this case, the field type store the real value of setting.
 * <br>
 * @author Pedro Almir
 *
 */
public class Setting extends Put {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1896325039622417404L;
	private boolean required;

	/**
	 * Default constructor
	 */
	public Setting() {
		super();
		this.required = true;
	}

	/**
	 * Setting constructor
	 * 
	 * @param name
	 * @param identifier
	 * @param type
	 * @param representation
	 * @param multipleValues
	 * @param validations
	 */
	public Setting(String name, String identifier, Type type, String representation, boolean multipleValues,
			List<Validation> validations) {
		super(name, identifier, type, representation, multipleValues, validations);
		this.required = true;
	}
	
	/**
	 * @param name
	 * @param identifier
	 * @param type
	 * @param representation
	 * @param multipleValues
	 * @param validations
	 * @param required
	 */
	public Setting(String name, String identifier, Type type, String representation, boolean multipleValues,
			List<Validation> validations, boolean required) {
		super(name, identifier, type, representation, multipleValues, validations);
		this.required = required;
	}
	
	/**
	 * @param setting
	 */
	public Setting(Setting setting) {
		super(setting.getName(), setting.getIdentifier(), setting.getType().getClone(), setting.getRepresentation(), 
				setting.isMultipleValues(), setting.getValidations());
		this.required = setting.isRequired();
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
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Setting clone() throws CloneNotSupportedException {
		return new Setting(this);
	}
	
}
