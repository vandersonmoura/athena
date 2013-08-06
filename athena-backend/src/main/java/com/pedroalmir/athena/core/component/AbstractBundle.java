/**
 * 
 */
package com.pedroalmir.athena.core.component;

import com.google.common.base.Preconditions;
import com.pedroalmir.athena.common.model.EntityIdFactory;
import com.pedroalmir.athena.common.model.GenericEntity;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.type.base.Type;

/**
 * @author Pedro Almir
 *
 */
public abstract class AbstractBundle extends GenericEntity implements AthenaBundle{
	
	public AbstractBundle() {
		/* TODO: Change to hibernate generate ID */
		this.id = EntityIdFactory.getNextId();	
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
	
	public void removeOutput(Output output) {
		Preconditions.checkNotNull(output);
		this.getOutputs().remove(output);
	}

	public void removeOutput(int index) {
		Preconditions.checkArgument(index > 0 && index < this.getConfiguration().getOutputConfiguration().getMaximum() - 1, "Invalid position.");
		this.getOutputs().remove(index);
	}
	
}
