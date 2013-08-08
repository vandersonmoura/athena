/**
 * 
 */
package com.pedroalmir.athena.web.model.vo.configuration;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.put.PutConfiguration;
import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.web.model.vo.type.TypeVO;

/**
 * @author Pedro Almir
 *
 */
public class PutConfigurationVO {
	/**
	 * Minimum number of input/output
	 */
	private final int minimum;
	/**
	 * Maximum number of input/output
	 */
	private final int maximum;
	/**
	 * List of available types
	 */
	private final List<TypeVO> availableTypes;
	/**
	 * @param minimum
	 * @param maximum
	 * @param availableTypes
	 */
	public PutConfigurationVO(int minimum, int maximum,
			List<TypeVO> availableTypes) {
		super();
		this.minimum = minimum;
		this.maximum = maximum;
		this.availableTypes = availableTypes;
	}
	/**
	 * @param inputConfiguration
	 */
	public PutConfigurationVO(PutConfiguration putConfiguration) {
		this.minimum = putConfiguration.getMinimum();
		this.maximum = putConfiguration.getMaximum();
		
		List<Type> availableTypes = putConfiguration.getAvailableTypes();
		
		if(availableTypes != null){
			this.availableTypes = new LinkedList<TypeVO>();
			for(Type t : availableTypes){
				this.availableTypes.add(t.getTypeVO());
			}
		}else{
			this.availableTypes = null;
		}
		
	}
	/**
	 * @return the minimum
	 */
	public int getMinimum() {
		return minimum;
	}
	/**
	 * @return the maximum
	 */
	public int getMaximum() {
		return maximum;
	}
	/**
	 * @return the availableTypes
	 */
	public List<TypeVO> getAvailableTypes() {
		return availableTypes;
	}
}
