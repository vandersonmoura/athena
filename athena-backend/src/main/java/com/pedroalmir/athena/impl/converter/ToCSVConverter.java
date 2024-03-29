/**
 * 
 */
package com.pedroalmir.athena.impl.converter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import au.com.bytecode.opencsv.CSVWriter;

import com.pedroalmir.athena.AthenaEnvironment;
import com.pedroalmir.athena.core.component.AbstractBundle;
import com.pedroalmir.athena.core.component.AthenaBundle;
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
public class ToCSVConverter extends AbstractBundle implements GenericConverter{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3494925689765011306L;
	/**
	 * Default constructor
	 */
	public ToCSVConverter() {
		super();
	}
	
	public List<Output> convert() {
		try {
			this.executionLog.appendLogLine("Convertendo inputs...");
			List<String> inputs = new LinkedList<String>();
			
			/* TODO: Use HttpRequest !!!*/
			File file = null;
			/* 
			 * Verify if ATHENA_RESULT_FOLDER_FULL_PATH is different of null, ie,
			 * Athena is running in Tomcat.
			 * */
			if(AthenaEnvironment.ATHENA_RESULT_FOLDER_FULL_PATH != null){
				file = new File(AthenaEnvironment.ATHENA_RESULT_FOLDER_FULL_PATH 
						+ "/" + this.getSettingWithIdentifier("file_name").getType().getValue() 
						+ "_" + new Date().getTime() + ".csv");
			}else{
				file = new File("src/test/resources/results/" + this.getSettingWithIdentifier("file_name").getType().getValue() + ".csv");
			}
			
			System.out.println(file.getAbsolutePath());
			
			file.createNewFile();
			CSVWriter csvWriter = new CSVWriter(new FileWriter(file), ';');
			
			List<String[]> data = new ArrayList<String[]>();
			
			/* Write header */
			String[] header = new String[this.inputs.size()];
			int i = 0;
			for(Input in : this.inputs){
				header[i++] = in.getName();
				inputs.add(in.getName());
			}
			
			data.add(header);
			
			for(int j = 0; j < this.inputs.get(0).getValues().size(); j++){
				
				String[] body = new String[this.inputs.size()];
				i = 0;
				for(Input in : this.inputs){
					body[i++] = in.getValues().get(j).getValue() + "";
				}
				data.add(body);
			}
			
			csvWriter.writeAll(data);
			csvWriter.close();
			
			this.outputs.get(0).getType().setValue(file);
			this.executionLog.appendLogLine("Input(s): " + StringUtils.join(inputs, ", ") + " convertido(s) no arquivo " + file.getName() + ".");
			this.executionLog.addGeneratedFileWithRealPath(file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param identifier
	 * @return
	 */
	public Setting getSettingWithIdentifier(String identifier){
		for(Setting s : this.settings){
			if(s.getIdentifier().equals(identifier)){
				return s;
			}
		}
		return null;
	}
	
	public String getName() {
		return "ToCSV Converter";
	}
	
	public String getShortName(){
		return "ToCSV";
	}
	
	public String getDescription() {
		return "Convert the final results in an specified format.";
	}
	
	public String getImagePath() {
		return "";
	}
	
	public List<Input> getInputs() {
		return this.inputs;
	}
	
	public List<Output> getOutputs() {
		return this.outputs;
	}
	
	public List<Setting> getSettings() {
		return this.settings;
	}

	public Configuration getConfiguration() {
		return new Configuration() {
			
			public boolean hasSettings() {
				return true;
			}
			
			public List<Setting> getSettings() {
				List<Setting> settings = new LinkedList<Setting>();
				
				Setting fileName = new Setting("File Name", "file_name", new StringType(""), "string", false, null, true);
				settings.add(fileName);
				
				return settings;
			}
			
			public PutConfiguration getOutputConfiguration() {
				PutConfiguration putConfiguration = new PutConfiguration();
				
				/* Set the minimum of outputs */
				putConfiguration.setMinimum(1);
				/* Set the maximum of outputs */
				putConfiguration.setMaximum(1);
				
				putConfiguration.addAvailableType(new FileType());
				return putConfiguration;
			}
			
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inputs == null) ? 0 : inputs.hashCode());
		result = prime * result + ((outputs == null) ? 0 : outputs.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(AthenaBundle bundle) {
		if (this == bundle)
			return true;
		if (bundle == null)
			return false;
		if (getClass() != bundle.getClass())
			return false;
		ToCSVConverter other = (ToCSVConverter) bundle;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inputs == null) {
			if (other.inputs != null)
				return false;
		} else if (!inputs.equals(other.inputs))
			return false;
		if (outputs == null) {
			if (other.outputs != null)
				return false;
		} else if (!outputs.equals(other.outputs))
			return false;
		return true;
	}


	@Override
	public void setSettings(List<Setting> settings) {
		this.settings = settings;
	}

}
