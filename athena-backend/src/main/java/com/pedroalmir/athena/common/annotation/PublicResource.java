package com.pedroalmir.athena.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * The methods of the controllers that are annotated with 
 * this annotation can be accessed without the user being logged
 * 
 * @author Pedro Oliveira
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface PublicResource {

}
