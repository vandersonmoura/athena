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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime
				* result
				+ ((passwordConfirmation == null) ? 0 : passwordConfirmation
						.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (passwordConfirmation == null) {
			if (other.passwordConfirmation != null)
				return false;
		} else if (!passwordConfirmation.equals(other.passwordConfirmation))
			return false;
		if (profile != other.profile)
			return false;
		return true;
	}

}
