package com.pedroalmir.athena.common.session;

import java.io.Serializable;

import com.pedroalmir.athena.web.model.User;

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

	public boolean isLogged() {
		return false;
	}

	public User getUser() {
		return null;
	}

}
