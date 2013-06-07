/**
 * 
 */
package com.pedroalmir.athena.common.model.enums;

/**
 * This Enumeration represents the permission levels of users.
 * 
 * @author EasyTeam, created by Pedro Oliveira
 *
 */
public enum EnumProfile {

	CLIENTE("Cliente"), CHEFE_MESA("Chefe da Mesa"), GARCOM("Garçom"), ADMIN("Administrador");
	
	/**
	 * description
	 */
	private String description;
	
	/**
	 * @param description
	 */
	private EnumProfile(String description) {
		this.description = description;
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
	
	
}
