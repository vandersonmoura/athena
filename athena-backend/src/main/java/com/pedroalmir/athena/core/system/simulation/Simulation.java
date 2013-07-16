/**
 * 
 */
package com.pedroalmir.athena.core.system.simulation;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.pedroalmir.athena.core.component.AthenaBundle;
import com.pedroalmir.athena.core.component.GenericConverter;
import com.pedroalmir.athena.core.component.GenericModule;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.system.AthenaSystem;
import com.pedroalmir.athena.core.system.link.Link;
import com.pedroalmir.athena.core.system.result.ResultFormatter;

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
	private List<SimulationData> info;
	/**
	 * 
	 */
	private final Date executionDate;
	
	
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
			/* TODO: bad smell */
			if(l.getSrcModule().getName().equals(srcBundle.getName())){
				links.add(l);
			}
		}
		return links;
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
}
