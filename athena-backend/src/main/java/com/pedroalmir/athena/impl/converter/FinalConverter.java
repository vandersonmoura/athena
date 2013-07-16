/**
 * 
 */
package com.pedroalmir.athena.impl.converter;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.component.AbstractBundle;
import com.pedroalmir.athena.core.component.GenericConverter;
import com.pedroalmir.athena.core.configuration.Configuration;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.PutConfiguration;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.type.file.FileType;
import com.pedroalmir.athena.core.type.numeric.Int;
import com.pedroalmir.athena.core.type.numeric.Real;
import com.pedroalmir.athena.core.type.string.StringType;

/**
 * @author Pedro Almir
 *
 */
public class FinalConverter extends AbstractBundle implements GenericConverter{
	
	/**
	 * This field represents the list of inputs
	 */
	private List<Input> inputs;
	/**
	 * This field represents the list of outputs
	 */
	private List<Output> outputs;
	
	/**
	 * Default constructor
	 */
	public FinalConverter() {
		this.inputs = new LinkedList<Input>();
		this.outputs = new LinkedList<Output>();
	}
	
	@Override
	public String getName() {
		return "Final converter";
	}

	@Override
	public String getDescription() {
		return "Convert the final results in an specified format.";
	}

	@Override
	public String getImagePath() {
		return "";
	}

	@Override
	public List<Input> getInputs() {
		return this.inputs;
	}

	@Override
	public List<Output> getOutputs() {
		return this.outputs;
	}

	@Override
	public void addSetting(Setting setting) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Setting> getSettings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Configuration getConfiguration() {
		return new Configuration() {
			
			@Override
			public boolean hasSettings() {
				return true;
			}
			
			@Override
			public List<Setting> getSettings() {
				List<Setting> settings = new LinkedList<Setting>();
				Setting formatter = new Setting("Formatador", "formatter", new StringType(""), "select[]", true, null, true);
				settings.add(formatter);
				
				return settings;
			}
			
			@Override
			public PutConfiguration getOutputConfiguration() {
				PutConfiguration putConfiguration = new PutConfiguration();
				
				/* Set the minimum of outputs */
				putConfiguration.setMinimum(1);
				/* Set the maximum of outputs */
				putConfiguration.setMaximum(1);
				
				putConfiguration.addAvailableType(new FileType());
				putConfiguration.addAvailableType(new StringType(""));
				return putConfiguration;
			}
			
			@Override
			public PutConfiguration getInputConfiguration() {
				PutConfiguration putConfiguration = new PutConfiguration();
				
				/* Set the minimum of outputs */
				putConfiguration.setMinimum(1);
				/* Set the maximum of outputs */
				putConfiguration.setMaximum(Integer.MAX_VALUE);
				
				putConfiguration.addAvailableType(Int.valueOf(0));
				putConfiguration.addAvailableType(Real.valueOf(0));
				putConfiguration.addAvailableType(new StringType(""));
				
				return putConfiguration;
			}
		};
	}

	@Override
	public List<Output> convert() {
		// TODO Auto-generated method stub
		return null;
	}

}
