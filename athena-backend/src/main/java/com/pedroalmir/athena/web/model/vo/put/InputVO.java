/**
 * 
 */
package com.pedroalmir.athena.web.model.vo.put;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.web.model.vo.put.base.PutVO;
import com.pedroalmir.athena.web.model.vo.type.TypeVO;
import com.pedroalmir.athena.web.validation.FrontEndValidation;

/**
 * @author Pedro Almir
 *
 */
public class InputVO extends PutVO{
	/**
	 * 
	 */
	private final List<TypeVO> components;
	/**
	 * 
	 */
	private final boolean linked;

	/**
	 * @param name
	 * @param identifier
	 * @param type
	 * @param multipleValue
	 * @param validations
	 * @param components
	 * @param linked
	 */
	public InputVO(String name, String identifier, TypeVO type,
			boolean multipleValue, List<FrontEndValidation> validations,
			List<TypeVO> components, boolean linked) {
		super(name, identifier, type, multipleValue, validations);
		this.components = components;
		this.linked = linked;
	}
	/**
	 * @param in
	 */
	public InputVO(Input in) {
		super(in.getName(), in.getIdentifier(), in.getType().getTypeVO(), in.isMultipleValues(), null);
		
		if(in.getValues() != null){
			this.components = new LinkedList<TypeVO>();
			for(Type type : in.getValues()){
				this.components.add(type.getTypeVO());
			}
		}else{
			this.components = null;
		}
		this.linked = in.isLinked();
	}
	/**
	 * @return the components
	 */
	public List<TypeVO> getComponents() {
		return components;
	}
	/**
	 * @return the linked
	 */
	public boolean isLinked() {
		return linked;
	}
}
