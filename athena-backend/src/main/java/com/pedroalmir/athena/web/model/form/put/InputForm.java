/**
 * 
 */
package com.pedroalmir.athena.web.model.form.put;

import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.web.model.form.put.base.PutForm;


/**
 * @author Pedro Almir
 *
 */
public class InputForm extends PutForm {

	/**
	 * @param in
	 */
	public InputForm(Input in) {
		super("input", in.getIdentifier(), in.getName(), 
				in.getType().getRepresentation().toString(), null, 
				in.isLinked(), in.isMultipleValues());
	}
	
	
}
