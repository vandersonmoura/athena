/**
 * 
 */
package com.pedroalmir.athena.impl.fuzzy.configuration;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.configuration.Configuration;
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
public class FuzzyConfiguration implements Configuration {
	
	@SuppressWarnings("unused")
	private final String FUZZY_PROPERTIES_PATH = "src/main/resources/algorithms/fuzzy.properties";

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.configuration.Configuration#getInputConfiguration()
	 */
	
	public PutConfiguration getInputConfiguration() {
		PutConfiguration putConfiguration = new PutConfiguration();
		/* Define minimum and maximum of inputs */
		putConfiguration.setMinimum(1);
		putConfiguration.setMaximum(Integer.MAX_VALUE);
		/* Define available types */
		putConfiguration.addAvailableType(Int.valueOf(0));
		putConfiguration.addAvailableType(Real.valueOf(0));
		putConfiguration.addAvailableType(new StringType(""));
		
		return putConfiguration;
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.configuration.Configuration#getOutputConfiguration()
	 */
	
	public PutConfiguration getOutputConfiguration() {
		PutConfiguration putConfiguration = new PutConfiguration();
		/* Define minimum and maximum of inputs */
		putConfiguration.setMinimum(1);
		putConfiguration.setMaximum(Integer.MAX_VALUE);
		/* Define available types */
		putConfiguration.addAvailableType(Int.valueOf(0));
		putConfiguration.addAvailableType(Real.valueOf(0));
		putConfiguration.addAvailableType(new StringType(""));
		
		return putConfiguration;
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.configuration.Configuration#getSettings()
	 */
	
	public List<Setting> getSettings() {
		List<Setting> settings = new LinkedList<Setting>();
		
        try {
        	/* TODO: Problem with file location */
        	//InputStream inputStream = new FileInputStream(this.FUZZY_PROPERTIES_PATH);
        	//Properties fuzzyProperties = new Properties();
        	/* load properties */
        	//fuzzyProperties.load(inputStream);
        	
        	Setting fclFile = new Setting("Arquivo de Configuração FCL", "fcl_file", new FileType(), "file", false, null, true);
        	
        	settings.add(fclFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return settings;
	}

	
	public boolean hasSettings() {
		return true;
	}
}
