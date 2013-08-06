/**
 * 
 */
package com.pedroalmir.athena.core.system.link;

import com.pedroalmir.athena.common.model.EntityIdFactory;
import com.pedroalmir.athena.common.model.GenericEntity;
import com.pedroalmir.athena.core.component.AthenaBundle;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.type.base.Type;

/**
 * This class represents the connection (link) between two modules.
 * It's responsible for propagating the data given output for a given input.
 * 
 * @author Pedro Almir
 *
 */
public class Link extends GenericEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6363116009107899362L;
	/**
	 * Description label
	 */
	private String description;
	/**
	 * Source module
	 */
	private AthenaBundle srcModule;
	/**
	 * Destination module
	 */
	private AthenaBundle dstModule;
	/**
	 * Source output
	 */
	private Output srcOutput;
	/**
	 * Destination input
	 */
	private Input dstInput;
	
	/**
	 * @param description
	 * @param srcModule
	 * @param dstModule
	 * @param srcOutput
	 * @param dstInput
	 */
	public Link(String description, AthenaBundle srcModule, AthenaBundle dstModule, Output srcOutput, Input dstInput) {
		/* TODO: Change to hibernate generate ID */
		this.id = EntityIdFactory.getNextId();
		
		this.description = description;
		this.srcModule = srcModule;
		this.dstModule = dstModule;
		this.srcOutput = srcOutput;
		this.dstInput = dstInput;
	}
	
	/**
	 * 
	 */
	public void propagate(){
		/* TODO: Implements it! */
		for(Type value : this.srcOutput.getValues()){
			this.dstInput.addValue(value);
		}
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the srcModule
	 */
	public AthenaBundle getSrcModule() {
		return srcModule;
	}
	/**
	 * @param srcModule the srcModule to set
	 */
	public void setSrcModule(AthenaBundle srcModule) {
		this.srcModule = srcModule;
	}
	/**
	 * @return the dstModule
	 */
	public AthenaBundle getDstModule() {
		return dstModule;
	}
	/**
	 * @param dstModule the dstModule to set
	 */
	public void setDstModule(AthenaBundle dstModule) {
		this.dstModule = dstModule;
	}
	/**
	 * @return the srcOutput
	 */
	public Output getSrcOutput() {
		return srcOutput;
	}
	/**
	 * @param srcOutput the srcOutput to set
	 */
	public void setSrcOutput(Output srcOutput) {
		this.srcOutput = srcOutput;
	}
	/**
	 * @return the dstInput
	 */
	public Input getDstInput() {
		return dstInput;
	}
	/**
	 * @param dstInput the dstInput to set
	 */
	public void setDstInput(Input dstInput) {
		this.dstInput = dstInput;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dstInput == null) ? 0 : dstInput.hashCode());
		result = prime * result + ((dstModule == null) ? 0 : dstModule.hashCode());
		result = prime * result + ((srcModule == null) ? 0 : srcModule.hashCode());
		result = prime * result + ((srcOutput == null) ? 0 : srcOutput.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Link other = (Link) obj;
		if (dstInput == null) {
			if (other.dstInput != null)
				return false;
		} else if (!dstInput.equals(other.dstInput))
			return false;
		if (dstModule == null) {
			if (other.dstModule != null)
				return false;
		} else if (!dstModule.equals(other.dstModule))
			return false;
		if (srcModule == null) {
			if (other.srcModule != null)
				return false;
		} else if (!srcModule.equals(other.srcModule))
			return false;
		if (srcOutput == null) {
			if (other.srcOutput != null)
				return false;
		} else if (!srcOutput.equals(other.srcOutput))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Link [description=" + description + ", srcModule=" + srcModule + ", dstModule=" + dstModule + ", srcOutput="
				+ srcOutput + ", dstInput=" + dstInput + "]";
	}
}
