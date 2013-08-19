/**
 * 
 */
package com.pedroalmir.athena.web.model.vo;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.pedroalmir.athena.AthenaEnvironment;
import com.pedroalmir.athena.core.component.AthenaBundle;
import com.pedroalmir.athena.core.component.GenericConverter;
import com.pedroalmir.athena.core.component.GenericModule;
import com.pedroalmir.athena.web.model.vo.bundle.ConverterVO;
import com.pedroalmir.athena.web.model.vo.bundle.ModuleVO;

/**
 * @author Pedro Almir
 *
 */
/**
 * @author Pedro Almir
 *
 */
public class SystemEditorVO {
	/**
	 * 
	 */
	private final List<ModuleVO> availableModules;
	/**
	 * 
	 */
	private final List<ConverterVO> availableConverters;
	
	/**
	 * Default constructor
	 */
	public SystemEditorVO(HttpServletRequest request) {
		Map<String, Class<AthenaBundle>> availableBundles = AthenaEnvironment.getAvailableBundles(request);
		this.availableModules = new LinkedList<ModuleVO>();
		this.availableConverters = new LinkedList<ConverterVO>();
		
		for(String key : availableBundles.keySet()){
			try {
				Class<AthenaBundle> bundleKlass = availableBundles.get(key);
				AthenaBundle bundle = bundleKlass.newInstance();
				
				if(GenericModule.class.isAssignableFrom(bundle.getClass())){
					this.availableModules.add(new ModuleVO(bundle, request));
				}else if(GenericConverter.class.isAssignableFrom(bundle.getClass())){
					this.availableConverters.add(new ConverterVO(bundle, request));
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * @param availableModules
	 * @param availableConverters
	 */
	public SystemEditorVO(List<ModuleVO> availableModules, List<ConverterVO> availableConverters) {
		super();
		this.availableModules = availableModules;
		this.availableConverters = availableConverters;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new Gson().toJson(new SystemEditorVO(null)));
	}
	
}
