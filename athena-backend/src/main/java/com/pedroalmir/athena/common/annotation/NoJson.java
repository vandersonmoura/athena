package com.pedroalmir.athena.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * The controller methods that contains this annotation
 * will not serialize the result
 * @author EasyTeam, created by Pedro Almir
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface NoJson {

}
