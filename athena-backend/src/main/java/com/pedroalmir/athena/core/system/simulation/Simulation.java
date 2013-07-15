/**
 * 
 */
package com.pedroalmir.athena.core.system.simulation;

import java.util.Date;
import java.util.List;

import com.pedroalmir.athena.core.system.AthenaSystem;
import com.pedroalmir.athena.core.system.result.ResultFormatter;

/**
 * @author Pedro Almir
 *
 */
public class Simulation implements Runnable {
	/**
	 * 
	 */
	private final String description;
	/**
	 * 
	 */
	private final AthenaSystem system;
	/**
	 * 
	 */
	private final List<SimulationData> info;
	/**
	 * 
	 */
	private final Date executionDate;
	
	/**
	 * @param description
	 * @param system
	 * @param info
	 * @param executionDate
	 */
	public Simulation(String description, AthenaSystem system, List<SimulationData> info, Date executionDate) {
		super();
		this.description = description;
		this.system = system;
		this.info = info;
		this.executionDate = executionDate;
	}

	/**
	 * @return
	 */
	public String getResult(){
		return "";
	}
	
	/**
	 * @param formatter
	 * @return
	 */
	public String getDetailedResult(ResultFormatter formatter){
		return formatter.format(this.system);
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the system
	 */
	public AthenaSystem getSystem() {
		return system;
	}

	/**
	 * @return the info
	 */
	public List<SimulationData> getInfo() {
		return info;
	}

	/**
	 * @return the executionDate
	 */
	public Date getExecutionDate() {
		return executionDate;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
