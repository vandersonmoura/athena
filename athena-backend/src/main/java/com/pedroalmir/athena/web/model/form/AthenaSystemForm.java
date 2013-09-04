/**
 * 
 */
package com.pedroalmir.athena.web.model.form;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.core.component.AthenaBundle;
import com.pedroalmir.athena.core.system.AthenaSystem;
import com.pedroalmir.athena.core.system.link.Link;
import com.pedroalmir.athena.web.model.form.bundle.AthenaBundleForm;
import com.pedroalmir.athena.web.model.form.link.LinkForm;

/**
 * @author Pedro Almir
 *
 */
public class AthenaSystemForm {
	/**
	 * 
	 */
	private Long id;
	/**
	 * FrontIdentifer
	 */
	private String identifier;
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
	public AthenaSystemForm() {
		
	}
	
	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param bundles
	 * @param links
	 */
	public AthenaSystemForm(Long id, String name, String description,
			List<AthenaBundleForm> bundles, List<LinkForm> links) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.bundles = bundles;
		this.links = links;
	}
	
	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param bundles
	 * @param links
	 */
	public AthenaSystemForm(AthenaSystem athenaSystem) {
		this.id = athenaSystem.getId();
		this.name = athenaSystem.getName();
		this.description = athenaSystem.getDescription();
		
		AthenaBundleForm athenaBundleForm = null;
		this.bundles = new LinkedList<AthenaBundleForm>();
		
		for(AthenaBundle bundle: athenaSystem.getBundles()){
			athenaBundleForm = new AthenaBundleForm(bundle);
			this.bundles.add(athenaBundleForm);
		}
		
		LinkForm linkForm = null;
		this.links = new LinkedList<LinkForm>();
		for(Link l : athenaSystem.getLinks()){
			linkForm = new LinkForm(l);
			this.links.add(linkForm);
		}
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AthenaSystemForm [id=" + id + ", name=" + name + ", description=" + description + ", bundles=" + bundles + ", links=" + links + "]";
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
}
