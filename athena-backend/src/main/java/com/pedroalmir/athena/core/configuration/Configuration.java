/**
 * 
 */
package com.pedroalmir.athena.core.configuration;

import java.util.List;

import com.pedroalmir.athena.core.configuration.put.PutConfiguration;
import com.pedroalmir.athena.core.configuration.put.base.Put;

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
	 * 
	 * @return list of settings
	 */
	List<Put> getSettings();
	/**
	 * This method should add any setting to
	 * this configuration.
	 * 
	 * @param setting
	 */
	void addSetting(Put setting);
}