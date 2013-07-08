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
	 * Default constructor
	 */
	public Setting() {
		super();
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
	}
	
}
