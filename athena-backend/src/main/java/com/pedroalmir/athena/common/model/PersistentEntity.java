package com.pedroalmir.athena.common.model;

import java.io.Serializable;

/**
 * Represents all persistent entity
 * 
 * @author Pedro Oliveira
 *
 */
public interface PersistentEntity extends Serializable {

	/**
	 * @return user identification
	 */
	Long getId();
	
	/**
	 * @param id
	 */
	void setId(Long id);
}
