/**
 * 
 */
package com.pedroalmir.athena.web.controller;

import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;

import com.pedroalmir.athena.common.annotation.MainDAO;
import com.pedroalmir.athena.common.annotation.NoJson;
import com.pedroalmir.athena.common.annotation.Permission;
import com.pedroalmir.athena.common.annotation.PublicResource;
import com.pedroalmir.athena.common.controller.base.ControllerBase;
import com.pedroalmir.athena.common.model.enums.EnumProfile;
import com.pedroalmir.athena.common.repository.annotation.Transactional;
import com.pedroalmir.athena.web.model.User;
import com.pedroalmir.athena.web.model.vo.UserVO;
import com.pedroalmir.athena.web.repository.UserDAO;

/**
 * @author Pedro Almir
 *
 */
@Resource
@Path("/user")
@MainDAO(UserDAO.class)
public class UserController extends ControllerBase<UserDAO> {
	/**
	 * Redirect to user/list.jsp
	 */
	@NoJson
	public void list() {
		
	}
	
	/**
	 * Method to retrieve a list of all users 
	 * @return list of all users
	 */
	@Permission(value = EnumProfile.ADMIN)
	public List<UserVO> listAll() {
		return mainDAO.listAll();
	}
	
	/**
	 * This method compare the database user email and password
	 * with the user name and password passed in parameter
	 * 
	 * @param user
	 */
	@Post
	@PublicResource
	public void login(User user) {
		User userDB = mainDAO.getUserByEmailAndPass(user.getEmail(), user.getPassword());

		if (userDB != null) {
			userSession.login(userDB);
		} else {
			addError("user_email", "login.credentials-fail");
			addError("user_password", "login.credentials-fail");
		}
	}
	
	/**
	 * @param user
	 */
	@Post
	@PublicResource
	public void loginWithFB(User user) {
		user.setProfile(EnumProfile.USER);
		user.setFacebookUser(true);
		
		User userDB = mainDAO.getUserByEmail(user.getEmail());

		if (userDB == null) {
			try {
				userDB = mainDAO.save(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		userSession.login(userDB);
	}
	
	/**
	 * This method save the user on database
	 * @param user
	 * @param profile
	 */
	@Post
	@Transactional
	@PublicResource
	public void save(User user) {
		try {
			if(user != null){
				user.setProfile(EnumProfile.USER);
				User userDB = mainDAO.save(user);
				userSession.login(userDB);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method logout the user from the system
	 */
	@Post
	@PublicResource
	public void logout() {
		userSession.logout();
	}
	
	/**
	 * Method to remove user
	 */
	@Transactional
	@Permission(value = EnumProfile.ADMIN)
	public void remove(Long userId) {
		User user = mainDAO.findById(userId);
		if(user == null)
			addError("list_user", "user.notFound");
		try {
			/* Remove user */
			if(mainDAO.delete(userId)) {
				System.out.println("Usuário removido com sucesso.");
			}else{
				System.out.println("Não foi possível remover o usuário.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
