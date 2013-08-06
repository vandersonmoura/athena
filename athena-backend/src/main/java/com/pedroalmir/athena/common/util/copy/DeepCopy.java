/**
 * 
 */
package com.pedroalmir.athena.common.util.copy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.put.base.Put;


/**
 * Utility for making deep copies (vs. clone()'s shallow copies) of objects. 
 * Objects are first serialized and then deserialized. Error checking is fairly minimal in this implementation. 
 * If an object is encountered that cannot be serialized (or that references an object that cannot be serialized) 
 * an error is printed to System.err and null is returned. Depending on your specific application, it might make 
 * more sense to have copy(...) re-throw the exception.
 * 
 * @author Pedro Almir
 */
public class DeepCopy {
	
	/**
     * Returns a copy of the object, or null if the object cannot
     * be serialized.
     */
    public static Object copy(Object orig) {
        Object obj = null;
        try {
            // Write the object out to a byte array
            FastByteArrayOutputStream fbos = new FastByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(fbos);
            out.writeObject(orig);
            out.flush();
            out.close();

            // Retrieve an input stream from the byte array and read
            // a copy of the object back in. 
            ObjectInputStream in = new ObjectInputStream(fbos.getInputStream());
            obj = in.readObject();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return obj;
    }
    
    /**
     * @param puts
     * @param klass
     * @return new list of puts
     */
    @SuppressWarnings("unchecked")
	public static <T extends Put> List<T> copy(List<T> puts, Class<T> klass){
    	if(klass.equals(Input.class)){
    		
    		List<Input> result = new LinkedList<Input>();
        	Input tmpInput = null;
        	
        	for (Input input : (List<Input>) puts) {
    			tmpInput = new Input(input);
    			result.add(tmpInput);
    		}
        	
        	return (List<T>) result;
    	}else if(klass.equals(Output.class)){
    		
    		List<Output> result = new LinkedList<Output>();
        	Output tmpOutput = null;
        	
        	for (Output output : (List<Output>) puts) {
    			tmpOutput = new Output(output);
    			result.add(tmpOutput);
    		}
        	
        	return (List<T>) result;
    	}else if(klass.equals(Setting.class)){
    		List<Setting> result = new LinkedList<Setting>();
    		Setting tmpSetting = null;
        	
        	for (Setting Setting : (List<Setting>) puts) {
        		tmpSetting = new Setting(Setting);
    			result.add(tmpSetting);
    		}
        	
        	return (List<T>) result;
    	}else{
    		return null;
    	}
    	
    }
    
    
}
