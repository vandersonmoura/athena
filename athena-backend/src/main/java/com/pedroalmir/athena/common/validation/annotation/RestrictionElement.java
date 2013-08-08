package com.pedroalmir.athena.common.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.pedroalmir.athena.common.controller.base.ControllerBase;

/**
 * 
 * Represents a restriction element for validation, 
 * reporting on what Controllers and validation methods will be called
 * 
 * @author Pedro Oliveira
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface RestrictionElement {

	/**
	 * Controller where the validation is called
	 * 
	 * @return controller base
	 */
	@SuppressWarnings("rawtypes")
	Class<? extends ControllerBase> controller();

	/**
	 * Methods where specific validation is called
	 * 
	 * @return the methods
	 */
	String[] methods() default {};

}
