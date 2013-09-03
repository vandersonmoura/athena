/**
 * 
 */
package com.pedroalmir.athena.impl.teamAllocation.model;

import java.util.List;
import java.util.Map;

/**
 * @author Pedro Almir
 *
 */
public class NSGAIIResult {
	/**
	 * 
	 */
	private final List<Equipe> equipes;
	/**
	 * 
	 */
	private long executionTime;
	/**
	 * 
	 */
	private final Map<String, List<Double>> objetivos;
	/**
	 * @param equipes
	 * @param executionTime
	 */
	public NSGAIIResult(List<Equipe> equipes, long executionTime, Map<String, List<Double>> objetivos) {
		super();
		this.equipes = equipes;
		this.executionTime = executionTime;
		this.objetivos = objetivos;
	}

	/**
	 * @return the equipes
	 */
	public List<Equipe> getEquipes() {
		return equipes;
	}

	/**
	 * @return the executionTime
	 */
	public long getExecutionTime() {
		return executionTime;
	}

	/**
	 * @return the objetivos
	 */
	public Map<String, List<Double>> getObjetivos() {
		return objetivos;
	}

	/**
	 * @param executionTime the executionTime to set
	 */
	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}
	
	
}
