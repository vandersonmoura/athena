package com.pedroalmir.athena.web.model.vo;

import java.io.Serializable;

import com.pedroalmir.athena.common.model.enums.EnumProfile;
import com.pedroalmir.athena.web.model.User;

/**
 * Logged user visual object
 * 
 * @author Pedro Oliveira
 *
 */
public class UserVO implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2525290947231275727L;

	/**
	 * id
	 */
	private final Long id;
	
	/**
	 * user name
	 */
	private final String name;
	
	/**
	 * email
	 */
	private final String email;
	
	/**
	 * profile
	 */
	private final EnumProfile profile;
	
	/**
	 * profileImage
	 */
	private final String profileImage;
	
	/**
	 * Constructor
	 * 
	 * @param user
	 */
	public UserVO(User user){
		this.id = user.getId();
		this.name = user.getName();
		this.profile = user.getProfile();
		this.email = user.getEmail();
		this.profileImage = user.getProfileImage();
	}
	
	/**
	 * @return the profileImage
	 */
	public String getProfileImage() {
		return profileImage;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the profile
	 */
	public EnumProfile getProfile() {
		return profile;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
