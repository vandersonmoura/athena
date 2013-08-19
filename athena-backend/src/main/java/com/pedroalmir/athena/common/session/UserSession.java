package com.pedroalmir.athena.common.session;

import java.io.Serializable;

import com.pedroalmir.athena.web.model.User;
import com.pedroalmir.athena.web.model.vo.UserVO;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

/**
 * 
 * Keep the User logged into the system
 * 
 * @author Pedro Oliveira
 */
@Component
@SessionScoped
public class UserSession implements Serializable {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -81958405003847158L;

	/**
	 * USER_SESSION_PARAM
	 */
	public static final String USER_SESSION_PARAM = "userSession";

	/**
	 * User logged in
	 */
	private UserVO user = null;
	/**
	 * @return user
	 */
	public UserVO getUser() {
		return user;
	}

	/**
	 * 
	 * Performs user login
	 * 
	 * @param user
	 *            User
	 */
	public void login(User user) {
		if (user != null) {
			this.user = new UserVO(user);
		}
	}
	
	/**
	 * Performs logout User
	 */
	public void logout() {
		this.user = null;
	}

	/**
	 * @return True if the User is logged
	 */
	public boolean isLogged() {
		return user != null;
	}

}
