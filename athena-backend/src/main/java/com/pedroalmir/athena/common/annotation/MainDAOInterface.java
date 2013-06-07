package com.pedroalmir.athena.common.annotation;

import com.pedroalmir.athena.common.repository.GenericDAO;

/**
 * 
 * Classes that implement the interface will be able to have the MainDAO injected, 
 * working together with the notation MainDAO
 * 
 * 
 * @author Pedro Oliveira
 */
@SuppressWarnings("rawtypes")
public interface MainDAOInterface<E extends GenericDAO> {

	/**
	 * Sets the corresponding GenericDAO
	 * 
	 * @param mainDAO
	 */
	void setMainDAO(E mainDAO);

	/**
	 * @return o GenericDAO
	 */
	E getMainDAO();

}
