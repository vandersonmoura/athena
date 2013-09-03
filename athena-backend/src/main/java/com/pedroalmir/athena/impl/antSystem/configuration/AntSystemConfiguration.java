/**
 * 
 */
package com.pedroalmir.athena.impl.antSystem.configuration;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.configuration.Configuration;
import com.pedroalmir.athena.core.put.PutConfiguration;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.type.numeric.Int;
import com.pedroalmir.athena.core.type.numeric.Real;
import com.pedroalmir.athena.core.type.string.StringType;

/**
 * @author Pedro Almir
 *
 */
public class AntSystemConfiguration implements Configuration {

	public PutConfiguration getInputConfiguration() {
		PutConfiguration putConfiguration = new PutConfiguration();
		/* Define minimum and maximum of inputs */
		putConfiguration.setMinimum(1);
		putConfiguration.setMaximum(Integer.MAX_VALUE);
		/* Define available types */
		putConfiguration.addAvailableType(Int.valueOf(0));
		putConfiguration.addAvailableType(Real.valueOf(0));
		
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
		
		return putConfiguration;
	}

	/* (non-Javadoc)
	 * @see com.pedroalmir.athena.core.configuration.Configuration#getSettings()
	 */
	
	public List<Setting> getSettings() {
		List<Setting> settings = new LinkedList<Setting>();
		
        try {
        	
        	Setting antNumber = new Setting("Número de Formigas", "antNumber", Int.valueOf(0), "int", false, null, true);
        	Setting alfaValue = new Setting("Valor do Alfa", "alfaValue", Real.valueOf(0), "double", false, null, true);
        	Setting evaporation = new Setting("Evaporação", "evaporation", Real.valueOf(0), "double", false, null, true);
        	
        	Setting maxOfIterations = new Setting("Número Máximo de Iterações", "maxOfIterations", Real.valueOf(0), "double", false, null, true);
        	
        	Setting beginNode = new Setting("Nó inicial", "beginNode", new StringType(""), "string", false, null, true);
        	Setting finalNode = new Setting("Nó final", "finalNode", new StringType(""), "string", false, null, true);
        	
        	settings.add(antNumber);
        	settings.add(alfaValue);
        	settings.add(evaporation);
        	settings.add(maxOfIterations);
        	settings.add(beginNode);
        	settings.add(finalNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return settings;
	}

	
	public boolean hasSettings() {
		return true;
	}
}
