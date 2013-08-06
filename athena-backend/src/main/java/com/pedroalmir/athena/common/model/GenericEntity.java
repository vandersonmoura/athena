/**
 * 
 */
package com.pedroalmir.athena.common.model;

/**
 * @author Pedro Almir
 *
 */
public abstract class GenericEntity implements PersistentEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4953838946917995521L;
	/**
	 * 
	 */
	protected Long id;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

}
