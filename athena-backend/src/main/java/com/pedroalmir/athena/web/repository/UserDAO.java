/**
 * 
 */
package com.pedroalmir.athena.web.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;

import com.pedroalmir.athena.common.repository.GenericDAO;
import com.pedroalmir.athena.web.model.User;
import com.pedroalmir.athena.web.model.vo.UserVO;


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
			Query query = em.createQuery("FROM User u WHERE u.email = :email AND u.password = :password");
			query.setParameter("email", email);
			query.setParameter("password", pass);

			try {
				return (User) query.getSingleResult();
			} catch (Exception e) {
			}
		}
		return null;
	}
	
	/**
	 * Get user by email
	 * 
	 * @param email
	 * @return user
	 */
	public User getUserByEmail(String email) {
		if (email != null) {
			Query query = em.createQuery("FROM User u WHERE u.email = :email");
			query.setParameter("email", email);

			try {
				return (User) query.getSingleResult();
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * List all
	 * 
	 * @return list of UserVO
	 */
	@SuppressWarnings("unchecked")
	public List<UserVO> listAll() {
		Query createQuery = em.createQuery("select new com.pedroalmir.athena.web.model.vo.UserVO(user) from User as user");
		try {
			return (List<UserVO>) createQuery.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
