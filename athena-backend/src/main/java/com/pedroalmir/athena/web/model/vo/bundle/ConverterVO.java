/**
 * 
 */
package com.pedroalmir.athena.web.model.vo.bundle;

import java.util.List;

import com.pedroalmir.athena.core.component.AthenaBundle;
import com.pedroalmir.athena.core.component.GenericConverter;
import com.pedroalmir.athena.web.model.vo.bundle.base.AthenaBundleVO;
import com.pedroalmir.athena.web.model.vo.configuration.ConfigurationVO;
import com.pedroalmir.athena.web.model.vo.put.InputVO;
import com.pedroalmir.athena.web.model.vo.put.OutputVO;
import com.pedroalmir.athena.web.model.vo.put.SettingVO;

/**
 * @author Pedro Almir
 * 
 */
public class ConverterVO extends AthenaBundleVO {

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
	public ConverterVO(String uniqueKey, String name, String description, String imagePath, 
			ConfigurationVO configurationVO, List<InputVO> inputs, List<OutputVO> outputs, 
			List<SettingVO> settings) {
		
		super(uniqueKey, name, description, imagePath, configurationVO, inputs, outputs, settings);
	}
	
	/**
	 * @param converter
	 */
	public ConverterVO(GenericConverter converter){
		super(converter);
	}
	
	/**
	 * @param bundle
	 */
	public ConverterVO(AthenaBundle bundle){
		super(bundle);
	}

}
