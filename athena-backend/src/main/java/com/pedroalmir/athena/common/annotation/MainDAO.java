package com.pedroalmir.athena.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.pedroalmir.athena.common.repository.GenericDAO;


/**
 * 
 * Note that aims to inform about the main DAO class annotated
 * 
 * @author Pedro Oliveira
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MainDAO {

	/**
	 * @return entity DAO
	 */
	@SuppressWarnings("rawtypes")
	Class<? extends GenericDAO> value();

}
