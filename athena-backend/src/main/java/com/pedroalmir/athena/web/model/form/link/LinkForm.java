/**
 * 
 */
package com.pedroalmir.athena.web.model.form.link;

import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.system.link.Link;
import com.pedroalmir.athena.web.model.form.bundle.AthenaBundleForm;

/**
 * @author Pedro Almir
 *
 */
public class LinkForm {
	/**
	 * Description label
	 */
	private String description;
	/**
	 * Source module
	 */
	private AthenaBundleForm srcModule;
	/**
	 * Destination module
	 */
	private AthenaBundleForm dstModule;
	/**
	 * Source output
	 */
	private Output srcOutput;
	/**
	 * Destination input
	 */
	private Input dstInput;
	
	/**
	 * @param l
	 */
	public LinkForm(Link l) {
		this.description = l.getDescription();
		this.srcModule = new AthenaBundleForm(l.getSrcModule());
		this.dstModule = new AthenaBundleForm(l.getDstModule());
		
		this.srcOutput = l.getSrcOutput();
		this.dstInput = l.getDstInput();
	}
	
	/*##################################################################*/
	/*##################################################################*/
	
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
	public AthenaBundleForm getSrcModule() {
		return srcModule;
	}
	/**
	 * @param srcModule the srcModule to set
	 */
	public void setSrcModule(AthenaBundleForm srcModule) {
		this.srcModule = srcModule;
	}
	/**
	 * @return the dstModule
	 */
	public AthenaBundleForm getDstModule() {
		return dstModule;
	}
	/**
	 * @param dstModule the dstModule to set
	 */
	public void setDstModule(AthenaBundleForm dstModule) {
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LinkForm [description=" + description + ", srcModule="
				+ srcModule + ", dstModule=" + dstModule + ", srcOutput="
				+ srcOutput + ", dstInput=" + dstInput + "]";
	}
}
