/**
 * 
 */
package com.pedroalmir.athena.web.model.form.bundle;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.component.AthenaBundle;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.web.model.form.put.InputForm;
import com.pedroalmir.athena.web.model.form.put.OutputForm;
import com.pedroalmir.athena.web.model.form.put.SettingForm;

/**
 * @author Pedro Almir
 *
 */
public class AthenaBundleForm {
	/**
	 * Unique Key that represent this module
	 */
	private String uniqueKey;
	
	/*##################################################################*/
	/*  					  Front-end Variables						*/
	/*##################################################################*/
	/**
	 * TODO: 
	 */
	private String name;
	/**
	 * TODO: Desnecessário
	 */
	private String description;
	/**
	 * TODO: Desnecessário
	 */
	private String imgPath;
	/**
	 * 
	 */
	private String type;
	/**
	 * TODO: Desnecessário
	 */
	private String shortName;
	/**
	 * Ex.: fuzzyModule_1
	 */
	private String frontIdentifier;
	/**
	 * Started with 1.
	 */
	private Integer frontOrder;
	/**
	 * This field represents the list of inputs
	 */
	private List<InputForm> inputs;
	/**
	 * This field represents the list of outputs
	 */
	private List<OutputForm> outputs;
	/**
	 * List of settings
	 */
	private List<SettingForm> settings;
	
	/**
	 * @param bundle
	 */
	public AthenaBundleForm(AthenaBundle bundle) {
		InputForm inputForm = null;
		this.inputs = new LinkedList<InputForm>();
		
		if(bundle.getInputs() != null){
			for(Input in : bundle.getInputs()){
				inputForm = new InputForm(in);
				this.inputs.add(inputForm);
			}
		}
		
		OutputForm outputForm = null;
		this.outputs = new LinkedList<OutputForm>();
		
		if(bundle.getOutputs() != null){
			for(Output out : bundle.getOutputs()){
				outputForm = new OutputForm(out);
				this.outputs.add(outputForm);
			}
		}
		
		SettingForm settingForm = null;
		this.settings = new LinkedList<SettingForm>();
		
		if(bundle.getSettings() != null){
			for(Setting set : bundle.getSettings()){
				settingForm = new SettingForm(set);
				this.settings.add(settingForm);
			}
		}
		
	}
	
	/**
	 * @param inputs
	 * @param outputs
	 * @param settings
	 */
	public AthenaBundleForm(List<InputForm> inputs, List<OutputForm> outputs, List<SettingForm> settings) {
		super();
		this.inputs = inputs;
		this.outputs = outputs;
		this.settings = settings;
	}
	
	/**
	 * Default constructor
	 */
	public AthenaBundleForm() {
		this.inputs = new LinkedList<InputForm>();
		this.outputs = new LinkedList<OutputForm>();
		this.settings = new LinkedList<SettingForm>();
	}
	
	/*##################################################################*/
	/*##################################################################*/

	/**
	 * @return the inputs
	 */
	public List<InputForm> getInputs() {
		return inputs;
	}

	/**
	 * @param inputs the inputs to set
	 */
	public void setInputs(List<InputForm> inputs) {
		this.inputs = inputs;
	}

	/**
	 * @return the outputs
	 */
	public List<OutputForm> getOutputs() {
		return outputs;
	}

	/**
	 * @param outputs the outputs to set
	 */
	public void setOutputs(List<OutputForm> outputs) {
		this.outputs = outputs;
	}

	/**
	 * @return the settings
	 */
	public List<SettingForm> getSettings() {
		return settings;
	}

	/**
	 * @param settings the settings to set
	 */
	public void setSettings(List<SettingForm> settings) {
		this.settings = settings;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AthenaBundleForm [inputs=" + inputs + ", outputs=" + outputs
				+ ", settings=" + settings + "]";
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
	 * @return the frontOrder
	 */
	public Integer getFrontOrder() {
		return frontOrder;
	}

	/**
	 * @param frontOrder the frontOrder to set
	 */
	public void setFrontOrder(Integer frontOrder) {
		this.frontOrder = frontOrder;
	}

	/**
	 * @return the uniqueKey
	 */
	public String getUniqueKey() {
		return uniqueKey;
	}

	/**
	 * @param uniqueKey the uniqueKey to set
	 */
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the imgPath
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * @param imgPath the imgPath to set
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
}
