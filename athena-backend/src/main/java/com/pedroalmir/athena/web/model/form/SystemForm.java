/**
 * 
 */
package com.pedroalmir.athena.web.model.form;

import java.util.List;

/**
 * @author Pedro Almir
 *
 */
public class SystemForm {
	/**
	 * 
	 */
	private Long id;
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
	private List<AthenaBundleForm> bundles;
	/**
	 * List of links
	 */
	private List<LinkForm> links;
	
	/**
	 * Default constructor
	 */
	public SystemForm() {
		
	}
	
	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param bundles
	 * @param links
	 */
	public SystemForm(Long id, String name, String description,
			List<AthenaBundleForm> bundles, List<LinkForm> links) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.bundles = bundles;
		this.links = links;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	public List<AthenaBundleForm> getBundles() {
		return bundles;
	}
	/**
	 * @param bundles the bundles to set
	 */
	public void setBundles(List<AthenaBundleForm> bundles) {
		this.bundles = bundles;
	}
	/**
	 * @return the links
	 */
	public List<LinkForm> getLinks() {
		return links;
	}
	/**
	 * @param links the links to set
	 */
	public void setLinks(List<LinkForm> links) {
		this.links = links;
	}
}
