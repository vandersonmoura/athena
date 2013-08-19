/**
 * 
 */
package com.pedroalmir.athena.core.system;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.pedroalmir.athena.common.model.EntityIdFactory;
import com.pedroalmir.athena.common.model.GenericEntity;
import com.pedroalmir.athena.common.util.copy.DeepCopy;
import com.pedroalmir.athena.core.component.AthenaBundle;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.system.link.Link;
import com.pedroalmir.athena.core.system.simulation.Simulation;
import com.pedroalmir.athena.core.system.simulation.SimulationData;

/**
 * This class represents the whole idea behind the AthenaService. Now it'll
 * be possible to create computational intelligence systems that integrate
 * different areas of IA.
 *  
 * @author Pedro Almir
 *
 */
@Entity
public class AthenaSystem extends GenericEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6344664230867517592L;
	/**
	 * System name
	 */
	private String name;
	/**
	 * System description
	 */
	@Column(columnDefinition="LONGTEXT")
	private String description;
	/**
	 * List of bundles
	 */
	@Transient
	private List<AthenaBundle> bundles;
	/**
	 * List of links
	 */
	@Transient
	private List<Link> links;
	/**
	 * List of simulations
	 */
	@OneToMany(mappedBy = "system", targetEntity = Simulation.class)
	private List<Simulation> simulation;
	
	/**
	 * Default constructor
	 */
	public AthenaSystem() {
		this.bundles = new LinkedList<AthenaBundle>();
		this.links = new LinkedList<Link>();
	}
	
	/**
	 * @param name
	 * @param description
	 */
	public AthenaSystem(String name, String description) {
		super();
		/* TODO: Change to hibernate generate ID */
		this.id = EntityIdFactory.getNextId();
		this.name = name;
		this.description = description;
		
		this.bundles = new LinkedList<AthenaBundle>();
		this.links = new LinkedList<Link>();
	}
	
	/**
	 * @param bundle
	 */
	public void addModule(AthenaBundle bundle){
		this.bundles.add(bundle);
	}
	
	/**
	 * @param label
	 * @param srcBundle
	 * @param dstBundle
	 * @param srcOutput
	 * @param dstInput
	 */
	public void addLink(String label, AthenaBundle srcBundle, AthenaBundle dstBundle, Output srcOutput, Input dstInput){
		dstInput.setLinked(true);
		srcOutput.setLinked(true);
		this.links.add(new Link(label, srcBundle, dstBundle, srcOutput, dstInput));
	}
	
	/**
	 * Create and execute a simulation for this system.
	 * <ol>
	 *   <li>Create means extract data from inputs and outputs then make a simulation entity for each bundle of system.</li>
	 *   <li>Add to list of simulations</li>
	 *   <li>Execute created simulation</li>
	 * </ol>
	 */
	public void createAndExecuteSimulation(String simulationDescription) {
		Simulation simulation = extractSimulationData(simulationDescription);
		
		//if(!checkIfExist(simulation))
		simulation.run();
		
		simulation.checkAndValidateResults();
	}
	

	/**
	 * Extract simulation data
	 * 
	 * @return simulation
	 */
	private Simulation extractSimulationData(String simulationDescription) {
		
		List<SimulationData> info = new LinkedList<SimulationData>();
		for (AthenaBundle bundle : this.getBundles()) {
			
			List<Input> inputsWithData = null;
			List<Output> outputsWithData = null;
			List<Setting> settingsWithData = null;
			
			
			if(bundle.getInputs() != null){
				inputsWithData = (List<Input>) DeepCopy.copy(bundle.getInputs(), Input.class);
				
				for(Input in : bundle.getInputs()){
					in.clear();
				}
			}
			
			if(bundle.getOutputs() != null){
				outputsWithData = (List<Output>) DeepCopy.copy(bundle.getOutputs(), Output.class);
				
				for(Output out : bundle.getOutputs()){
					out.clear();
				}
			}
			
			if(bundle.getSettings() != null){
				settingsWithData = (List<Setting>) DeepCopy.copy(bundle.getSettings(), Setting.class);
				
				for(Setting set : bundle.getSettings()){
					set.getType().clear();
				}
			}
			
			info.add(new SimulationData(bundle, inputsWithData, outputsWithData, settingsWithData));
		}
		
		return new Simulation(simulationDescription, this, info);
	}


	/**
	 * Execute specified simulation
	 * @param simulation
	 */
	public void executeSimulation(Simulation simulation) {
		/* TODO: Implements it! */
	}
	
	/**
	 * Execute all simulations
	 */
	public void executeAllSimulations(){
		/* TODO: Implements it! */
	}
	
	/**#################################################################################################*/
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the bundles
	 */
	public List<AthenaBundle> getBundles() {
		return bundles;
	}
	/**
	 * @param bundles the bundles to set
	 */
	public void setBundles(List<AthenaBundle> bundles) {
		this.bundles = bundles;
	}
	/**
	 * @return the links
	 */
	public List<Link> getLinks() {
		return links;
	}
	/**
	 * @param links the links to set
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "System [name=" + name + ", bundles=" + bundles + ", links=" + links + "]";
	}

	/**
	 * @return the simulation
	 */
	public List<Simulation> getSimulation() {
		return simulation;
	}

	/**
	 * @param simulation the simulation to set
	 */
	public void setSimulation(List<Simulation> simulation) {
		this.simulation = simulation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bundles == null) ? 0 : bundles.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((links == null) ? 0 : links.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		AthenaSystem other = (AthenaSystem) obj;
		if (bundles == null) {
			if (other.bundles != null)
				return false;
		} else if (!bundles.equals(other.bundles))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (links == null) {
			if (other.links != null)
				return false;
		} else if (!links.equals(other.links))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
