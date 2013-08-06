/**
 * 
 */
package com.pedroalmir.athena.core.system.simulation;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.pedroalmir.athena.common.model.GenericEntity;
import com.pedroalmir.athena.core.component.AthenaBundle;
import com.pedroalmir.athena.core.component.GenericConverter;
import com.pedroalmir.athena.core.component.GenericModule;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.solution.Solution;
import com.pedroalmir.athena.core.system.AthenaSystem;
import com.pedroalmir.athena.core.system.link.Link;
import com.pedroalmir.athena.core.system.result.ResultFormatter;
import com.pedroalmir.athena.core.type.base.Type;

/**
 * Sequence of actions to create a computer system and run your simulation:
 * <br><br>
 * 1. Define modules and their connections;<br>
 * 2. Inform the input data and possible settings;<br>
 * 3. Trigger the execution of the simulation;<br>
 * 4. Analyze the results;<br>
 * <br><br>
 * 
 * @author Pedro Almir
 *
 */
public class Simulation extends GenericEntity implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 383391393330882188L;
	/**
	 * Simulation Description
	 */
	private String description;
	/**
	 * AthenaSystem without simulation data
	 */
	private AthenaSystem system;
	/**
	 * List of simulation data to execute this simulation
	 */
	private List<SimulationData> info;
	/**
	 * Execution date
	 */
	private Date executionDate;
	/**
	 * List of results
	 */
	private List<Type> results;
	/**
	 * List of solutions
	 */
	private List<Solution> solutions;
	
	/**
	 * @param description
	 * @param system
	 */
	public Simulation(String description, AthenaSystem system) {
		super();
		this.description = description;
		
		this.system = system;
		this.info = new LinkedList<SimulationData>();
		this.executionDate = new Date();
	}
	
	/**
	 * @param description
	 * @param system
	 * @param info
	 */
	public Simulation(String description, AthenaSystem system, List<SimulationData> info) {
		super();
		this.description = description;
		
		this.system = system;
		this.info = info;
		this.executionDate = new Date();
	}
	
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
	 * @param bundle
	 * @param inputs
	 * @param outputs
	 * @param settings
	 */
	public void addSimulationData(AthenaBundle bundle, List<Input> inputs, List<Output> outputs, List<Setting> settings){
		this.info.add(new SimulationData(bundle, inputs, outputs, settings));
	}
	
	/**
	 * @param bundle
	 * @param inputs
	 * @param outputs
	 * @param settings
	 */
	public void addSimulationData(AthenaBundle bundle){
		this.info.add(separateProcedureOfData(bundle));
	}
	
	/**
	 * @param bundle
	 * @return simulation data
	 */
	private SimulationData separateProcedureOfData(AthenaBundle bundle){
		return null;
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
	
	/**
	 * 
	 */
	public void checkAndValidateResults() {
		
	}

	public void run() {
		for (AthenaBundle bundle : this.system.getBundles()) {
			if(bundle instanceof GenericConverter){
				populateInputs(bundle);
				populateSettings(bundle);
				
				SimulationData info = findSimulationData(bundle);
				List<Output> outputs = ((GenericConverter) bundle).convert();
				info.setOutputs(outputs);
				
				propagateResults(bundle);
			}else if(bundle instanceof GenericModule){
				populateInputs(bundle);
				populateSettings(bundle);
				
				SimulationData info = findSimulationData(bundle);
				((GenericModule) bundle).load();
				
				Preconditions.checkArgument(((GenericModule) bundle).isLoaded(), "Problems loading the module. Make sure that the entries are correct.");
				
				((GenericModule) bundle).run();
				info.setOutputs(bundle.getOutputs());
				
				propagateResults(bundle);
			}
		}
	}

	/**
	 * Propagate the results
	 * @param bundle
	 * 			Source bundle
	 */
	private void propagateResults(AthenaBundle srcBundle) {
		List<Link> links = findLinks(srcBundle);
		if(links != null && !links.isEmpty()){
			for (Link link : links) {
				link.propagate();
			}
		}
	}

	/**
	 * @param srcBundle
	 * @return list of links
	 */
	private List<Link> findLinks(AthenaBundle srcBundle) {
		List<Link> links = new LinkedList<Link>();
		for(Link l : this.system.getLinks()){
			/* TODO: bad smell ??? */
			if(l.getSrcModule().getId().equals(srcBundle.getId())){
				links.add(l);
			}
		}
		return links;
	}
	
	/**
	 * @param id
	 * @return system link or <code>null</code> if not found link with this id.
	 */
	@SuppressWarnings("unused")
	private Link findLink(Long id){
		for(Link l : this.system.getLinks()){
			if(l.getId().equals(id)){
				return l;
			}
		}
		return null;
	}
	
	/**
	 * @param dstInput
	 * @return system link or <code>null</code> if not found link with this id.
	 */
	private Link findLink(Input dstInput){
		for(Link l : this.system.getLinks()){
			if(l.getDstInput().getId().equals(dstInput.getId())){
				return l;
			}
		}
		return null;
	}

	/**
	 * Populate Settings
	 * 
	 * @param bundle
	 */
	private void populateSettings(AthenaBundle bundle) {
		SimulationData info = findSimulationData(bundle);
		Preconditions.checkNotNull(info, "Cannot find settings informations for %s module.", bundle.getName());
		if(bundle.getConfiguration().hasSettings()){
			for(Setting setting : info.getSettings()){
				bundle.addSetting(setting);
			}
		}
	}

	/**
	 * Populate bundle
	 * 
	 * @param bundle
	 */
	private void populateInputs(AthenaBundle bundle) {
		SimulationData info = findSimulationData(bundle);
		Preconditions.checkNotNull(info, "Cannot find input informations for %s module.", bundle.getName());
		for(Input input : info.getInputs()){
			if(input.isLinked()){
				System.out.println("");
				Link link = findLink(input);
				input.setValues(link.getDstInput().getValues());
			}
			bundle.addInput(input);
		}
	}

	/**
	 * Find simulation data
	 * 
	 * @param bundle
	 * @return simulation data
	 */
	private SimulationData findSimulationData(AthenaBundle bundle) {
		for(SimulationData sd : this.info){
			if(sd.getBundle().getName().equals(bundle.getName())){
				return sd;
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((system == null) ? 0 : system.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Simulation other = (Simulation) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (system == null) {
			if (other.system != null)
				return false;
		} else if (!system.equals(other.system))
			return false;
		return true;
	}

	/**
	 * @return the results
	 */
	public List<Type> getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(List<Type> results) {
		this.results = results;
	}

	/**
	 * @return the solutions
	 */
	public List<Solution> getSolutions() {
		return solutions;
	}

	/**
	 * @param solutions the solutions to set
	 */
	public void setSolutions(List<Solution> solutions) {
		this.solutions = solutions;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Simulation [description=" + description + ", system=" + system
				+ ", executionDate=" + executionDate + "]";
	}
}
