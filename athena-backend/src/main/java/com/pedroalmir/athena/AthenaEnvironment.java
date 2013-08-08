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
	
	public static void main(String[] args) {
		Map<String, Class<AthenaBundle>> availableModules = AthenaEnvironment.getAvailableBundles();
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
	public static <T extends AthenaBundle> String findBundleUniqueKey(Class<T> bundle){
		Map<String, Class<AthenaBundle>> availableModules = AthenaEnvironment.getAvailableBundles();
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
	public static <T extends AthenaBundle> Map<String, Class<T>> getAvailableBundles(){
		Properties athenaProperties = loadProperties();
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
	 * Load Athena Properties
	 */
	private static Properties loadProperties() {
        try {
        	;
        	InputStream inputStream = new FileInputStream(ATHENA_PROPERTIES_PATH);
        	Properties athenaProperties = new Properties();
        	/* load properties */
        	athenaProperties.load(inputStream);
        	return athenaProperties;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	
}
