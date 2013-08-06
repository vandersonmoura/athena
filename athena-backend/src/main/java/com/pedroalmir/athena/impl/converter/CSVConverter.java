/**
 * 
 */
package com.pedroalmir.athena.impl.converter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import au.com.bytecode.opencsv.CSVReader;

import com.google.common.base.Preconditions;
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
 * This class represents a CSV Converter. <br>It implements
 * the interface {@code GenericConverter} and works as
 * follow:
 * <br>
 * 1. Get CSV file;<br>
 * 2. Convert CSV content to output definitions;<br>
 * <br>
 * TODO: Embed the internationalization.<br>
 * <br>
 * CSV Format:
 * <br>
 * For only one output variable:<br>
 * atitude.csv<br>
 * <br>
 * 3.91<br>
 * 6.3<br>
 * 7.22<br>
 * <br>
 * OR<br>
 * <br>
 * For more than one output variable:<br>
 * input.csv<br>
 * <br>
 * atitude;conhecimento;habilidade<br>
 * 3.92;4.89;4.52<br>
 * 6.3;4.73;5.17<br>
 * 7.22;5.85;5.72<br>
 * 
 * @author Pedro Almir
 *
 */
public class CSVConverter extends AbstractBundle implements GenericConverter{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4806972317210521788L;
	/**
	 * StringBuffer
	 */
	private StringBuffer buffer;
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
	public CSVConverter() {
		super();
		this.inputs = new LinkedList<Input>();
		this.outputs = new LinkedList<Output>();
	}
	
	public String getName() {
		return "CSV Converter";
	}

	public String getDescription() {
		buffer = new StringBuffer();
		buffer.append("This class represents a CSV Converter. \n");
		buffer.append("It implements the interface {@code GenericConverter} and works as follow:\n\n");
		buffer.append("1. Get CSV file;\n");
		buffer.append("2. Convert CSV content to output definitions;\n\n");
		buffer.append("TODO: Embed the internationalization and improving this description.");
		return buffer.toString();
	}

	public String getImagePath() {
		/* TODO: Define image path for this converter. */
		return "";
	}

	public Configuration getConfiguration() {
		return new Configuration() {
			
			public List<Setting> getSettings() {
				/*
				 * This method return null because this converter
				 * not have settings.
				 * */
				return null;
			}
			
			public PutConfiguration getOutputConfiguration() {
				PutConfiguration putConfiguration = new PutConfiguration();
				/* Set the minimum of outputs */
				putConfiguration.setMinimum(1);
				/* Set the maximum of outputs */
				putConfiguration.setMaximum(Integer.MAX_VALUE);
				/* 
				 * This line add Int as an available type to output configuration.
				 * In this case the value will not be considered.
				 *  
				 * */
				putConfiguration.addAvailableType(Int.valueOf(0));
				putConfiguration.addAvailableType(Real.valueOf(0));
				putConfiguration.addAvailableType(new StringType(""));
				return putConfiguration;
			}
			
			public PutConfiguration getInputConfiguration() {
				PutConfiguration putConfiguration = new PutConfiguration();
				/* Set the minimum of outputs */
				putConfiguration.setMinimum(1);
				/* Set the maximum of outputs */
				putConfiguration.setMaximum(1);
				/* 
				 * This line add FileType as an available type to input configuration.
				 * In this case the value will not be considered.
				 *  
				 * */
				putConfiguration.addAvailableType(new FileType());
				return putConfiguration;
			}

			public boolean hasSettings() {
				return false;
			}
			
		};
	}

	public List<Output> convert() {
		Input input = Preconditions.checkNotNull(inputs).get(0);
		/* Verify initial conditions */
		Preconditions.checkArgument(inputs.size() == 1
				&& input.getType() instanceof FileType
				&& input.getValues().size() == 1, "This converter works only with an input file type.");
		/* Get input file */
		File file = (File) input.getValues().get(0).getValue();
		/* Verify if extension is csv */
		Preconditions.checkArgument(FilenameUtils.getExtension(file.getAbsolutePath()).equals("csv"), 
				"This converter works only with CSV files.");
		
		try {
			CSVReader reader = new CSVReader(new FileReader(file));
			/**/
			List<String[]> myEntries = reader.readAll();
			if(this.outputs.size() > 1){
				/* CSV header */
				String[] firstLine = myEntries.get(0);
				firstLine = firstLine[0].split(";");
				Preconditions.checkArgument(this.outputs.size() == firstLine.length, 
						"The number of columns in the csv file must be equal to the number of outputs defined.");
				int count = 0;
				boolean format = false;
				for(Output out : this.outputs){
					format = !out.getIdentifier().equals(firstLine[count++]);
					if (format)
						break;
				}
				Preconditions.checkArgument(!format, "The identifier present in the output must correspond to a column in the CSV file.");
				/* Check all rest of lines */
				for(int i = 1; i < myEntries.size(); i++){
					String[] nextLine = myEntries.get(i);
					nextLine = nextLine[0].split(";");
					for(int j = 0; j < this.outputs.size(); j++){
						/* Create the list of outputs */
						this.outputs.get(j).getValues().add(this.outputs.get(j).getType().getClone(nextLine[j]));
					}
				}
				reader.close();
				return this.outputs;
			}else if(this.outputs.size() == 1){
				/* Check all lines */
				for(int i = 0; i < myEntries.size(); i++){
					String[] nextLine = myEntries.get(i);
					/* Create the list of outputs */
					this.outputs.get(0).getValues().add(this.outputs.get(0).getType().getClone(nextLine[0]));
				}
				reader.close();
				return this.outputs;
			}else{
				reader.close();
				throw new IllegalArgumentException("This converter must contain at least one output.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Input> getInputs() {
		return this.inputs;
	}

	public List<Output> getOutputs() {
		return this.outputs;
	}

	public void addSetting(Setting setting) {
		/**/
	}

	public List<Setting> getSettings() {
		/* This method return null because this converter not have settings. */
		return null;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CSVConverter [getName()=" + getName() + ", getInputs()="+ getInputs() + ", getOutputs()=" + getOutputs() + "]";
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
		CSVConverter other = (CSVConverter) bundle;
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
		
	}

}
