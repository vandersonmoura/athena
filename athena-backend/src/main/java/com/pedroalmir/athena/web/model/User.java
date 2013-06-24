/**
 * 
 */
package com.pedroalmir.athena.web.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.pedroalmir.athena.common.model.PersistentEntity;
import com.pedroalmir.athena.common.model.enums.EnumProfile;
import com.pedroalmir.athena.common.validation.ValidationClass;
import com.pedroalmir.athena.web.model.validation.UserValidation;

/**
 * @author Pedro Almir
 *
 */
@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = {"email"}) })
@ValidationClass(UserValidation.class)
public class User implements PersistentEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5673031951517905827L;
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * email
	 */
	private String email;
	/**
	 * password
	 * Used to access the system
	 */
	private String password;
	@Transient
	private String passwordConfirmation;
	/**
	 * profile
	 */
	@Enumerated(EnumType.STRING)
	private EnumProfile profile;
	
	/**
	 * Default constructor
	 */
	public User() {
		
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param profile the profile to set
	 */
	public void setProfile(EnumProfile profile) {
		this.profile = profile;
	}

	public EnumProfile getProfile() {
		return this.profile;
	}

	/**
	 * @return the passwordConfirmation
	 */
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	/**
	 * @param passwordConfirmation the passwordConfirmation to set
	 */
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

}
