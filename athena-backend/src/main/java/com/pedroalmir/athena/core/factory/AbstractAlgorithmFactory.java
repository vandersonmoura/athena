/**
 * 
 */
package com.pedroalmir.athena.core.factory;

import com.pedroalmir.athena.core.algorithm.Algorithm;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;


/**
 * @author Pedro Almir
 *
 */
public abstract class AbstractAlgorithmFactory implements AlgorithmFactory {
	
	/**
	 * @param algorithm
	 * @param input
	 */
	void addInput(Algorithm algorithm, Input input){
		
	}
	/**
	 * @param algorithm
	 * @param output
	 */
	void addOutput(Algorithm algorithm, Output output){
		
	}
	/**
	 * @param algorithm
	 * @param input
	 */
	void removeInput(Algorithm algorithm, Input input){
		
	}
	/**
	 * @param algorithm
	 * @param output
	 */
	void removeOutput(Algorithm algorithm, Output output){
		
	}
	/**
	 * @param algorithm
	 * @param setting
	 */
	void addSetting(Algorithm algorithm, Setting setting){
		
	}
	/**
	 * @param algorithm
	 * @param setting
	 */
	void removeSetting(Algorithm algorithm, Setting setting){
		
	}
}
