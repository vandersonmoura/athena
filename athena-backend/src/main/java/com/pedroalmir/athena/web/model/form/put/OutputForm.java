/**
 * 
 */
package com.pedroalmir.athena.web.model.form.put;

import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.web.model.form.put.base.PutForm;

/**
 * @author Pedro Almir
 *
 */
public class OutputForm extends PutForm{

	public OutputForm(Output out) {
		super("output", out.getIdentifier(), out.getName(), 
				out.getType().getRepresentation().toString(), null, 
				out.isLinked(), out.isMultipleValues());
	}

}
