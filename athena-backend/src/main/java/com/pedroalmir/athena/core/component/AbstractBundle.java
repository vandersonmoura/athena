/**
 * 
 */
package com.pedroalmir.athena.core.component;

import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.pedroalmir.athena.common.model.EntityIdFactory;
import com.pedroalmir.athena.common.model.GenericEntity;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.report.ExecutionLog;
import com.pedroalmir.athena.core.type.base.Type;

/**
 * @author Pedro Almir
 *
 */
public abstract class AbstractBundle extends GenericEntity implements AthenaBundle {
	
	/**
	 * 
	 */
	private String frontIdentifier;
	/**
	 * List of inputs
	 */
	protected List<Input> inputs;
	/**
	 * List of outputs
	 */
	protected List<Output> outputs;
	/**
	 * List of settings
	 */
	protected List<Setting> settings;
	/**
	 * 
	 */
	protected ExecutionLog executionLog;
	
	public AbstractBundle() {
		/* TODO: Change to hibernate generate ID */
		this.id = EntityIdFactory.getNextId();
		/* puts */
		this.inputs = new LinkedList<Input>();
		this.outputs = new LinkedList<Output>();
		this.settings = new LinkedList<Setting>();
		/* buffer */
		this.executionLog = new ExecutionLog();
	}
	
	@Override
	public ExecutionLog getExecutionLog() {
		return this.executionLog;
	}
	

	public Setting findSetting(String identifier) {
		if(this.getConfiguration().hasSettings()){
			for(Setting s : this.getConfiguration().getSettings()){
				if(s.getIdentifier().equals(identifier)){
					return s;
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6024225477857346757L;
	
	public String getFrontIdentifier() {
		return this.frontIdentifier;
	}
	
	public void setFrontIdentifier(String frontIdentifier) {
		this.frontIdentifier = frontIdentifier;
	}

	/*********************************************************************************************************/
	/*********************************************      Input      *******************************************/
	/*********************************************************************************************************/
	
	public void addInput(Input input) {
		boolean ok = false;
		for(Type t : this.getConfiguration().getInputConfiguration().getAvailableTypes()){
			if(input.getType().getClass().equals(t.getClass())){
				ok = true;
				break;
			}
		}
		if(!ok){
			throw new IllegalArgumentException("Mismatched types.");
		}
		/* Verify equality */
		ok = false;
		for(Input i : this.getInputs()){
			if(i.equals(input)){
				i.clear();
				i.setValues(input.getValues());
				ok = true;
				break;
			}
		}
		if(!ok){
			//System.out.println(getInputs().size() + ">=" + this.getConfiguration().getInputConfiguration().getMaximum());
			Preconditions.checkArgument(getInputs().size() < this.getConfiguration().getInputConfiguration().getMaximum(), 
					"Operation invalid. The maximum number of inputs was exceeded.");
			this.getInputs().add(input);
		}
	}
	
	public void addAllInput(List<Input> inputs) {
		this.inputs.addAll(inputs);
	}
	
	public void removeInput(Input input) {
		Preconditions.checkNotNull(input);
		this.getInputs().remove(input);
	}

	public void removeInput(int index) {
		Preconditions.checkArgument(index > 0 && index < this.getConfiguration().getInputConfiguration().getMaximum() - 1, "Invalid position.");
		this.getInputs().remove(index);
	}
	
	/*********************************************************************************************************/
	/*********************************************     Output      *******************************************/
	/*********************************************************************************************************/

	public void addOutput(Output output) {
		boolean ok = false;
		for(Type t : this.getConfiguration().getOutputConfiguration().getAvailableTypes()){
			if(output.getType().getClass().equals(t.getClass())){
				ok = true;
				break;
			}
		}
		if(!ok){
			throw new IllegalArgumentException("Mismatched types.");
		}
		/* Verify equality */
		ok = false;
		for(Output o : this.getOutputs()){
			if(o.equals(output)){
				o.setValues(output.getValues());
				ok = true;
				break;
			}
		}
		if(!ok){
			Preconditions.checkArgument(getOutputs().size() < this.getConfiguration().getOutputConfiguration().getMaximum(), 
					"Operation invalid. The maximum number of outputs was exceeded.");
			this.getOutputs().add(output);
		}
	}
	
	public void addAllOutput(List<Output> outputs) {
		this.outputs.addAll(outputs);
	}
	
	public void removeOutput(Output output) {
		Preconditions.checkNotNull(output);
		this.getOutputs().remove(output);
	}

	public void removeOutput(int index) {
		Preconditions.checkArgument(index > 0 && index < this.getConfiguration().getOutputConfiguration().getMaximum() - 1, "Invalid position.");
		this.getOutputs().remove(index);
	}
	
	/*********************************************************************************************************/
	/********************************************     Setting      *******************************************/
	/*********************************************************************************************************/

	public void addSetting(Setting setting) {
		if(getConfiguration().hasSettings() && this.getSettings() != null){
			
			for(Setting s : this.getSettings()){
				if(s.equals(setting)){
					s.getType().setValue(setting.getType().getValue());
					return;
				}
			}
			
			this.getSettings().add(setting);
		}
	}
	
	public void addAllSetting(List<Setting> settings) {
		this.settings.addAll(settings);
	}
	
}
