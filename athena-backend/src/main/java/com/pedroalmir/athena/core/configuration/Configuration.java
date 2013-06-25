/**
 * 
 */
package com.pedroalmir.athena.core.configuration;

import java.util.List;

import com.pedroalmir.athena.core.configuration.put.PutConfiguration;
import com.pedroalmir.athena.core.configuration.put.base.Put;

/**
 * @author Pedro Almir
 *
 */
public interface Configuration {
	PutConfiguration getInputConfiguration();
	PutConfiguration getOutputConfiguration();
	List<Put> getSettings();
	void addSetting(Put setting);
}
