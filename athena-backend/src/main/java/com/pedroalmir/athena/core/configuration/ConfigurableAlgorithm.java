/**
 * 
 */
package com.pedroalmir.athena.core.configuration;

import com.pedroalmir.athena.core.put.Setting;

/**
 * @author Pedro Almir
 *
 */
public interface ConfigurableAlgorithm {
	
	/**
     * Add a setting in algorithm
     * @param setting
     * 			The setting representation
     */
    void addSetting(Setting setting);

    /**
     * Remove the specified setting.
     * @param setting
     * 			The setting representation
     */
    void removeSetting(Setting setting);
	
}
