/**
 * 
 */
package com.pedroalmir.athena.web.model.vo.type;

/**
 * @author Pedro Almir
 *
 */
public class TypeVO {
	/**
	 * This field represents the object type value.
	 */
	private final Object value;
	/**
	 * This field represents the object representation.
	 * For example: int, float, double, List\<int\>, String, etc.
	 */
	private final String representation;
	
	/**
	 * @param value
	 * @param representation
	 */
	public TypeVO(Object value, String representation) {
		super();
		this.value = value;
		this.representation = representation;
	}
	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
	/**
	 * @return the representation
	 */
	public String getRepresentation() {
		return representation;
	}
}
