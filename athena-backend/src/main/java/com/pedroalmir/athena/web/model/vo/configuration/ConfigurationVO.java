/**
 * 
 */
package com.pedroalmir.athena.web.model.vo.configuration;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.configuration.Configuration;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.web.model.vo.put.SettingVO;

/**
 * Configuration Visual Object Representation
 * 
 * @author Pedro Almir
 */
public class ConfigurationVO {
	/**
	 * 
	 */
	private final PutConfigurationVO inputConfiguration;
	/**
	 * 
	 */
	private final PutConfigurationVO outputConfiguration;
	/**
	 * 
	 */
	private final List<SettingVO> settings;
	/**
	 * 
	 */
	private final boolean hasSetting;
	
	/**
	 * @param inputConfiguration
	 * @param outputConfiguration
	 * @param settings
	 * @param hasSetting
	 */
	public ConfigurationVO(PutConfigurationVO inputConfiguration,
			PutConfigurationVO outputConfiguration, List<SettingVO> settings,
			boolean hasSetting) {
		super();
		this.inputConfiguration = inputConfiguration;
		this.outputConfiguration = outputConfiguration;
		this.settings = settings;
		this.hasSetting = hasSetting;
	}
	/**
	 * @param configuration
	 */
	public ConfigurationVO(Configuration configuration) {
		this.inputConfiguration = new PutConfigurationVO(configuration.getInputConfiguration());
		this.outputConfiguration = new PutConfigurationVO(configuration.getOutputConfiguration());
		
		List<Setting> sets = configuration.getSettings();
		if(sets != null){
			this.settings = new LinkedList<SettingVO>();
			for(Setting set : sets){
				this.settings.add(new SettingVO(set));
			}
		}else{
			this.settings = null;
		}
		
		this.hasSetting = configuration.hasSettings();	
	}
	/**
	 * @return the inputConfiguration
	 */
	public PutConfigurationVO getInputConfiguration() {
		return inputConfiguration;
	}
	/**
	 * @return the outputConfiguration
	 */
	public PutConfigurationVO getOutputConfiguration() {
		return outputConfiguration;
	}
	/**
	 * @return the settings
	 */
	public List<SettingVO> getSettings() {
		return settings;
	}
	/**
	 * @return the hasSetting
	 */
	public boolean isHasSetting() {
		return hasSetting;
	}
}
