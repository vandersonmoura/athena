/**
 * 
 */
package com.pedroalmir.athena.core.configuration;

import java.util.List;

import com.pedroalmir.athena.core.put.PutConfiguration;
import com.pedroalmir.athena.core.put.Setting;

/**
 * This interface was designed to represent the configurations
 * of modules.
 * 
 * @author Pedro Almir
 *
 */
public interface Configuration {
	/**
	 * Get input configuration.
	 * @return putConfiguration object
	 */
	PutConfiguration getInputConfiguration();
	/**
	 * Get output configuration.
	 * @return putConfiguration object
	 */
	PutConfiguration getOutputConfiguration();
	/**
	 * This method should return the list of settings
	 * available to any module.
	 * 
	 * For example:
	 * 1. Maximum number of iterations
	 * 2. Configuration file
	 * 3. Number of population
	 * 4. Error
	 * 
	 * @return list of settings
	 */
	List<Setting> getSettings();
	/**
	 * @return
	 */
	boolean hasSettings();
}
