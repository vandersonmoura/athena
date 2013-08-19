/**
 * 
 */
package com.pedroalmir.athena.web.model.vo.bundle.base;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pedroalmir.athena.AthenaEnvironment;
import com.pedroalmir.athena.core.component.AthenaBundle;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.web.model.vo.configuration.ConfigurationVO;
import com.pedroalmir.athena.web.model.vo.put.InputVO;
import com.pedroalmir.athena.web.model.vo.put.OutputVO;
import com.pedroalmir.athena.web.model.vo.put.SettingVO;

/**
 * @author Pedro Almir
 *
 */
public class AthenaBundleVO {
	/**
	 * Unique Key that represent this module
	 */
	private final String uniqueKey;
	/**
	 * Module Name
	 */
	private final String name;
	/**
	 * Module Description
	 */
	private final String description;
	/**
	 * Module Image Path
	 */
	private final String imagePath;
	/**
	 * Configuration
	 */
	private final ConfigurationVO configurationVO;
	/**
	 * Inputs
	 */
	private final List<InputVO> inputs;
	/**
	 * Outputs
	 */
	private final List<OutputVO> outputs;
	/**
	 * Settings
	 */
	private final List<SettingVO> settings;
	
	/**
	 * 
	 */
	public <T extends AthenaBundle> AthenaBundleVO(T module, HttpServletRequest request){
		this.uniqueKey = AthenaEnvironment.findBundleUniqueKey(module.getClass(), request);
		this.name = module.getName();
		this.description = module.getDescription();
		this.imagePath = module.getImagePath();
		this.configurationVO = new ConfigurationVO(module.getConfiguration());
		
		List<Input> ins = module.getInputs();
		List<Output> outs = module.getOutputs();
		List<Setting> sets = module.getSettings();
		
		if(ins != null){
			this.inputs = new LinkedList<InputVO>();
			for(Input in : ins){
				this.inputs.add(new InputVO(in));
			}
		}else{
			this.inputs = null;
		}
		
		if(outs != null){
			this.outputs = new LinkedList<OutputVO>();
			for(Output out : outs){
				this.outputs.add(new OutputVO(out));
			}
		}else{
			this.outputs = null;
		}
		
		if(sets != null){
			this.settings = new LinkedList<SettingVO>();
			for(Setting set : sets){
				this.settings.add(new SettingVO(set));
			}
		}else{
			this.settings = null;
		}
	}
	
	/**
	 * @param uniqueKey
	 * @param name
	 * @param description
	 * @param imagePath
	 * @param configurationVO
	 * @param inputs
	 * @param outputs
	 * @param settings
	 */
	public AthenaBundleVO(String uniqueKey, String name, String description,
			String imagePath, ConfigurationVO configurationVO,
			List<InputVO> inputs, List<OutputVO> outputs,
			List<SettingVO> settings) {
		super();
		this.uniqueKey = uniqueKey;
		this.name = name;
		this.description = description;
		this.imagePath = imagePath;
		this.configurationVO = configurationVO;
		this.inputs = inputs;
		this.outputs = outputs;
		this.settings = settings;
	}
	/**
	 * @return the uniqueKey
	 */
	public String getUniqueKey() {
		return uniqueKey;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * @return the configurationVO
	 */
	public ConfigurationVO getConfigurationVO() {
		return configurationVO;
	}
	/**
	 * @return the inputs
	 */
	public List<InputVO> getInputs() {
		return inputs;
	}
	/**
	 * @return the outputs
	 */
	public List<OutputVO> getOutputs() {
		return outputs;
	}
	/**
	 * @return the settings
	 */
	public List<SettingVO> getSettings() {
		return settings;
	}
}
