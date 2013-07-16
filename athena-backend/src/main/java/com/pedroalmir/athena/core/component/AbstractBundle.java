/**
 * 
 */
package com.pedroalmir.athena.core.component;

import com.google.common.base.Preconditions;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.type.base.Type;

/**
 * @author Pedro Almir
 *
 */
public abstract class AbstractBundle implements AthenaBundle{
	
	/*********************************************************************************************************/
	/*********************************************      Input      *******************************************/
	/*********************************************************************************************************/
	
	@Override
	public void addInput(Input input) {
		Preconditions.checkArgument(getInputs().size() < this.getConfiguration().getInputConfiguration().getMaximum(), 
				"Operation invalid. The maximum number of inputs was exceeded.");
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
				i.setValues(input.getValues());
				ok = true;
				break;
			}
		}
		if(!ok){
			this.getInputs().add(input);
		}
	}
	
	@Override
	public void removeInput(Input input) {
		Preconditions.checkNotNull(input);
		this.getInputs().remove(input);
	}

	@Override
	public void removeInput(int index) {
		Preconditions.checkArgument(index > 0 && index < this.getConfiguration().getInputConfiguration().getMaximum() - 1, "Invalid position.");
		this.getInputs().remove(index);
	}
	
	/*********************************************************************************************************/
	/*********************************************     Output      *******************************************/
	/*********************************************************************************************************/

	@Override
	public void addOutput(Output output) {
		Preconditions.checkArgument(getInputs().size() < this.getConfiguration().getOutputConfiguration().getMaximum(), 
				"Operation invalid. The maximum number of outputs was exceeded.");
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
			this.getOutputs().add(output);
		}
	}
	
	@Override
	public void removeOutput(Output output) {
		Preconditions.checkNotNull(output);
		this.getOutputs().remove(output);
	}

	@Override
	public void removeOutput(int index) {
		Preconditions.checkArgument(index > 0 && index < this.getConfiguration().getOutputConfiguration().getMaximum() - 1, "Invalid position.");
		this.getOutputs().remove(index);
	}
	
}
