/**
 * 
 */
package com.pedroalmir.athena.core.util;

import java.util.List;

import com.pedroalmir.athena.core.put.Setting;

/**
 * Put Utils
 * 
 * @author Pedro Almir
 *
 */
public class PutUtils {
	/**
	 * Find Setting by identifier
	 * 
	 * @param identifier
	 * @param settings
	 * @return the setting
	 */
	public static Setting findSetting(String identifier, List<Setting> settings){
		for(Setting s : settings){
			if(s.getIdentifier().equals(identifier)){
				return s;
			}
		}
		return null;
	}
}
