/**
 * 
 */
package com.pedroalmir.athena.web.repository;

import java.io.Serializable;

import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;

import com.pedroalmir.athena.common.repository.GenericDAO;
import com.pedroalmir.athena.web.model.User;


/**
 * User DAO
 * 
 * @author EasyTeam
 * 
 */
@Component
public class UserDAO extends GenericDAO<User> implements Serializable {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -6232887663249252099L;

	/**
	 * Constructor
	 * 
	 */
	public UserDAO() {
		super(User.class);
	}
	
	/**
	 * Get user by email and password
	 * 
	 * @param email
	 * @param pass
	 * @return user
	 */
	public User getUserByEmailAndPass(String email, String pass) {
		if (email != null && pass != null) {
			Query query = em.createQuery("FROM Usuario u WHERE u.email = :email AND u.password = :password");
			query.setParameter("email", email);
			query.setParameter("password", pass);

			try {
				return (User) query.getSingleResult();
			} catch (Exception e) {
			}
		}
		return null;
	}

	

}
