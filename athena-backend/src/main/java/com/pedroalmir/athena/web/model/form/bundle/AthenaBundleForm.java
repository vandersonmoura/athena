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
}
