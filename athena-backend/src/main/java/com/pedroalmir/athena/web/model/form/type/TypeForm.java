/**
 * 
 */
package com.pedroalmir.athena.web.model.form.type;

/**
 * @author Pedro Almir
 *
 */
public class TypeForm {

	/**
	 * 
	 */
	private String value;
	
	/**
	 * @param value
	 */
	public TypeForm(String value) {
		super();
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TypeForm [value=" + value + "]";
	}
	
	
}
