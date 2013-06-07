package com.pedroalmir.athena.common.repository;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.util.jpa.JPATransactionInterceptor;

import com.pedroalmir.athena.common.repository.annotation.Transactional;

/**
 * My Transaction Intercepter class
 * @author EasyTeam, created by Pedro Almir
 *
 */
@Component
@Intercepts
public class MyTransactionInterceptor extends JPATransactionInterceptor {

	/**
	 * @param manager
	 * @param validator
	 */
	public MyTransactionInterceptor(EntityManager manager, Validator validator) {
		super(manager, validator);
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		return method.getMethod().isAnnotationPresent(Transactional.class);
	}

}
