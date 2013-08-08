/**
 * 
 */
package com.pedroalmir.athena.web.model.vo.put;

import java.util.List;

import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.web.model.vo.put.base.PutVO;
import com.pedroalmir.athena.web.model.vo.type.TypeVO;
import com.pedroalmir.athena.web.validation.FrontEndValidation;

/**
 * @author Pedro Almir
 *
 */
public class SettingVO extends PutVO{
	/**
	 * This field represents if this setting is required.
	 */
	private final boolean required;

	/**
	 * @param name
	 * @param identifier
	 * @param type
	 * @param multipleValue
	 * @param validations
	 * @param required
	 */
	public SettingVO(String name, String identifier, TypeVO type,
			boolean multipleValue, List<FrontEndValidation> validations,
			boolean required) {
		super(name, identifier, type, multipleValue, validations);
		this.required = required;
	}

	/**
	 * @param setting
	 */
	public SettingVO(Setting setting) {
		super(setting.getName(), setting.getIdentifier(), setting.getType().getTypeVO(), setting.isMultipleValues(), null);
		this.required = setting.isRequired();
	}

	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}
}
