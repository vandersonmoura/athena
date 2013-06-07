package com.pedroalmir.athena.common.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * JPA Util
 * 
 * @author Pedro Almir
 * 
 * Singleton Pattern
 *  
 * In many cases, it is important for some classes having at most one Instance. 
 * There might exist many printers in the system, but there should be only one printer spooler.
 * 
 * But how can we ensure, that there is only one Instance and that this instance is easily accessible? 
 * A global variable would make the Instance accessible, but it can't keep you from instantiating more than on object.
 * 
 * A better solution is to make the class itself responsible for watching over its status and its sole instance. 
 * The class can ensure that only one instance is created by hiding the constructors, and it can provide a way to access the instance. 
 * This is called the Singleton pattern, it consists of just one class, all methods which provide a way to access the instance are usually static.
 * */
public class JPAUtil {

	/**
	 * Entity Manager Factory
	 * */
	private static EntityManagerFactory emf = null;
	

	/**
	 * Creates only one entity manager
	 * 
	 * @return emf Entity Manager
	 * */
	public static EntityManager getEntityManager() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("default");
		}
		return emf.createEntityManager();
	}
	
	/**
	 * Creates only one entity manager
	 * 
	 * @return emf Entity Manager
	 * */
	public static EntityManager getEntityManagerForTest() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("default");
		}
		return emf.createEntityManager();
	}
	

	/**
	 * Close the entity manager
	 * */
	public static void close() {
		if (emf != null) {
			emf.close();
		}
	}
}