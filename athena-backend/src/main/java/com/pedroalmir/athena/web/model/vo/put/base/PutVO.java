/**
 * 
 */
package com.pedroalmir.athena.web.model.vo.put.base;

import java.util.List;

import com.pedroalmir.athena.web.model.vo.type.TypeVO;
import com.pedroalmir.athena.web.validation.FrontEndValidation;

/**
 * @author Pedro Almir
 *
 */
public class PutVO {
	/**
	 * 
	 */
	private final String name;
	/**
	 * 
	 */
	private final String identifier;
	/**
	 * 
	 */
	private final TypeVO type;
	/**
	 * 
	 */
	private final boolean multipleValue;
	/**
	 * 
	 */
	private final List<FrontEndValidation> validations;
	
	/**
	 * @param name
	 * @param identifier
	 * @param type
	 * @param multipleValue
	 * @param validations
	 */
	public PutVO(String name, String identifier, TypeVO type,
			boolean multipleValue, List<FrontEndValidation> validations) {
		super();
		this.name = name;
		this.identifier = identifier;
		this.type = type;
		this.multipleValue = multipleValue;
		this.validations = validations;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}
	/**
	 * @return the type
	 */
	public TypeVO getType() {
		return type;
	}
	/**
	 * @return the multipleValue
	 */
	public boolean isMultipleValue() {
		return multipleValue;
	}
	/**
	 * @return the validations
	 */
	public List<FrontEndValidation> getValidations() {
		return validations;
	}
}
