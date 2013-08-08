/**
 * 
 */
package com.pedroalmir.athena.web.model.vo.put;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.web.model.vo.put.base.PutVO;
import com.pedroalmir.athena.web.model.vo.type.TypeVO;
import com.pedroalmir.athena.web.validation.FrontEndValidation;

/**
 * @author Pedro Almir
 *
 */
public class OutputVO extends PutVO{
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
	public OutputVO(String name, String identifier, TypeVO type,
			boolean multipleValue, List<FrontEndValidation> validations,
			List<TypeVO> components, boolean linked) {
		super(name, identifier, type, multipleValue, validations);
		this.components = components;
		this.linked = linked;
	}
	
	/**
	 * @param out
	 */
	public OutputVO(Output out) {
		super(out.getName(), out.getIdentifier(), out.getType().getTypeVO(), out.isMultipleValues(), null);
		
		if(out.getValues() != null){
			this.components = new LinkedList<TypeVO>();
			for(Type type : out.getValues()){
				this.components.add(type.getTypeVO());
			}
		}else{
			this.components = null;
		}
		this.linked = out.isLinked();
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
