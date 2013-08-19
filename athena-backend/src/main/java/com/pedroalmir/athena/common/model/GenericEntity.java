/**
 * 
 */
package com.pedroalmir.athena.common.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author Pedro Almir
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class GenericEntity implements PersistentEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4953838946917995521L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
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
