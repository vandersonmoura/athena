/**
 * 
 */
package com.pedroalmir.athena.core.system;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.component.AthenaBundle;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.system.link.Link;

/**
 * This class represents the whole idea behind the AthenaService. Now it'll
 * be possible to create computational intelligence systems that integrate
 * different areas of IA.
 *  
 * @author Pedro Almir
 *
 */
public class AthenaSystem {
	/**
	 * System name
	 */
	private String name;
	/**
	 * System description
	 */
	private String description;
	/**
	 * List of bundles
	 */
	private List<AthenaBundle> bundles;
	/**
	 * List of links
	 */
	private List<Link> links;
	
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
		this.links.add(new Link(label, srcBundle, dstBundle, srcOutput, dstInput));
	}

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
}
