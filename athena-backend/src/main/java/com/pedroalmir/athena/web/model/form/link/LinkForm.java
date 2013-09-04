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
	
	/*##################################################################*/
	/*  					  Front-end Variables						*/
	/*##################################################################*/
	/**
	 * TODO: Melhorar esses nomes
	 */
	private String bundleDst;
	private String bundleSrc;
	private String inputId;
	private String outputId;
	private String frontIdentifier;
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


	/**
	 * @return the inputId
	 */
	public String getInputId() {
		return inputId;
	}

	/**
	 * @param inputId the inputId to set
	 */
	public void setInputId(String inputId) {
		this.inputId = inputId;
	}

	/**
	 * @return the outputId
	 */
	public String getOutputId() {
		return outputId;
	}

	/**
	 * @param outputId the outputId to set
	 */
	public void setOutputId(String outputId) {
		this.outputId = outputId;
	}

	/**
	 * @return the frontIdentifier
	 */
	public String getFrontIdentifier() {
		return frontIdentifier;
	}

	/**
	 * @param frontIdentifier the frontIdentifier to set
	 */
	public void setFrontIdentifier(String frontIdentifier) {
		this.frontIdentifier = frontIdentifier;
	}

	/**
	 * @return the bundleDst
	 */
	public String getBundleDst() {
		return bundleDst;
	}

	/**
	 * @param bundleDst the bundleDst to set
	 */
	public void setBundleDst(String bundleDst) {
		this.bundleDst = bundleDst;
	}

	/**
	 * @return the bundleSrc
	 */
	public String getBundleSrc() {
		return bundleSrc;
	}

	/**
	 * @param bundleSrc the bundleSrc to set
	 */
	public void setBundleSrc(String bundleSrc) {
		this.bundleSrc = bundleSrc;
	}
}
