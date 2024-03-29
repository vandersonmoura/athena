/**
 * 
 */
package com.pedroalmir.athena;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.pedroalmir.athena.common.util.file.FileUtil;
import com.pedroalmir.athena.core.component.AthenaBundle;

/**
 * 
 * @author Pedro Almir
 *
 */
public class AthenaEnvironment {
	
	/**
	 * Athena properties file path. Attention: Don't change this file!!!
	 */
	private static final String ATHENA_PROPERTIES_PATH = "src/main/resources/athena.properties";
	private static final String ATHENA_PROPERTIES_PATH_IN_WAR = "WEB-INF/classes/athena.properties";
	public static final String ATHENA_RESULT_FOLDER = "user/system/results";
	public static String ATHENA_RESULT_FOLDER_FULL_PATH = null;
	public static String ATHENA_ROOT_PATH = null;
	public static boolean IN_TOMCAT = false;
	public static final String ATHENA_BASE_URL = "http://pedroalmir.com/athena-backend/";
	public static final String ATHENA_BASE_URL_II = "http://pedroalmir.com/athena-backend";
	public static final String LOG_SEPARATOR_I = "###########################################################################################################";
	public static final String LOG_SEPARATOR_II = "-----------------------------------------------------------------------------------------------------------";
	
	public static String SECTION_TEMPLATE = null;
	public static String REPORT_TEMPLATE = null;
	
	public static void main(String[] args) {
		Map<String, Class<AthenaBundle>> availableModules = AthenaEnvironment.getAvailableBundles(null);
		for(String key : availableModules.keySet()){
			try {

				Class<AthenaBundle> klass = availableModules.get(key);
				AthenaBundle bundle = klass.newInstance();
				System.out.println(bundle.getName());
				
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param bundle
	 * @return the unique key of the specified module or <code>null</code>
	 * if module isn't registered in the system or not implements AthenaBundle;
	 */
	public static <T extends AthenaBundle> String findBundleUniqueKey(Class<T> bundle, HttpServletRequest request){
		Map<String, Class<AthenaBundle>> availableModules = AthenaEnvironment.getAvailableBundles(request);
		for(String key : availableModules.keySet()){
			if(bundle.equals(availableModules.get(key))){
				return key;
			}
		}
		return null;
	}
	
	/**
	 * @return available modules
	 */
	@SuppressWarnings("unchecked")
	public static <T extends AthenaBundle> Map<String, Class<T>> getAvailableBundles(HttpServletRequest request){
		Properties athenaProperties = loadProperties(request);
		String listOfKeys = athenaProperties.getProperty("listOfKeys");
		
		Map<String, Class<T>> availableModules = new LinkedHashMap<String, Class<T>>();
		
		for(String key : listOfKeys.split(",")){
			try {
				key = key.trim();
				String klass = athenaProperties.getProperty(key);
				
				Class<T> bundle = (Class<T>) Class.forName(klass);
				availableModules.put(key, bundle);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return availableModules;
	}
	
	/**
	 * Find bundle class from unique key
	 * 
	 * @param uniqueKey
	 * @param request
	 * @return athena bundle class
	 */
	@SuppressWarnings("unchecked")
	public static <T extends AthenaBundle> Class<T> getBundleFromUniqueKey(String uniqueKey, HttpServletRequest request){
		
		try {
			Properties athenaProperties = loadProperties(request);
			
			String klass = athenaProperties.getProperty(uniqueKey);
			
			Class<T> bundle = (Class<T>) Class.forName(klass);
			
			return bundle;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Load Athena Properties
	 */
	private static Properties loadProperties(HttpServletRequest request) {
        try {
        	InputStream inputStream = null;
        	if(request != null){
        		FileUtil fileUtil = new FileUtil(request);
        		inputStream = new FileInputStream(fileUtil.getRealPath(ATHENA_PROPERTIES_PATH_IN_WAR));
        	}else{
        		inputStream = new FileInputStream(ATHENA_PROPERTIES_PATH);
        	}
        	Properties athenaProperties = new Properties();
        	/* load properties */
        	athenaProperties.load(inputStream);
        	return athenaProperties;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}

	/**
	 * @return the aTHENA_RESULT_FOLDER_FULL_PATH
	 */
	public static String getATHENA_RESULT_FOLDER_FULL_PATH() {
		return ATHENA_RESULT_FOLDER_FULL_PATH;
	}

	/**
	 * @param aTHENA_RESULT_FOLDER_FULL_PATH the aTHENA_RESULT_FOLDER_FULL_PATH to set
	 */
	public static void setATHENA_RESULT_FOLDER_FULL_PATH(
			String aTHENA_RESULT_FOLDER_FULL_PATH) {
		ATHENA_RESULT_FOLDER_FULL_PATH = aTHENA_RESULT_FOLDER_FULL_PATH;
	}

	/**
	 * @param path
	 * @return
	 */
	public static String addRootPath(String path) {
		return ATHENA_ROOT_PATH + path;
	}

	/**
	 * @return the sECTION_TEMPLATE
	 */
	public static String getSECTION_TEMPLATE() {
		return SECTION_TEMPLATE;
	}

	/**
	 * @param sECTION_TEMPLATE the sECTION_TEMPLATE to set
	 */
	public static void setSECTION_TEMPLATE(String sECTION_TEMPLATE) {
		SECTION_TEMPLATE = sECTION_TEMPLATE;
	}

	/**
	 * @return the rEPORT_TEMPLATE
	 */
	public static String getREPORT_TEMPLATE() {
		return REPORT_TEMPLATE;
	}

	/**
	 * @param rEPORT_TEMPLATE the rEPORT_TEMPLATE to set
	 */
	public static void setREPORT_TEMPLATE(String rEPORT_TEMPLATE) {
		REPORT_TEMPLATE = rEPORT_TEMPLATE;
	}

}
