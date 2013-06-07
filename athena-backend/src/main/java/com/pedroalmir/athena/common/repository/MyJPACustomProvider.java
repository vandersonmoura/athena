package com.pedroalmir.athena.common.repository;

import br.com.caelum.vraptor.ComponentRegistry;
import br.com.caelum.vraptor.ioc.spring.SpringProvider;
import br.com.caelum.vraptor.util.jpa.EntityManagerCreator;
import br.com.caelum.vraptor.util.jpa.EntityManagerFactoryCreator;


/**
 * De forma similar a classe {JPACustomProvider}, registro as entidades que se encarregarão de me fornecer o entityManagerFactory e entityManager. <br/>
 * Além disso, adiciono o nosso próprio transaction interceptor, que só irá interceptar métodos anotados com arroba Transactional.<br/>
 * Para que ela seja utilizada pelo vraptor, adicionei uma configuração no web.xml que aponta para essa classe.
 * 
 * Ver comentários na classe {Transactional}
 * 
 * @author Willame
 *
 */
public class MyJPACustomProvider extends SpringProvider {

	@Override
	protected void registerCustomComponents(ComponentRegistry registry) {
		registry.register(EntityManagerCreator.class, EntityManagerCreator.class);
		registry.register(EntityManagerFactoryCreator.class, EntityManagerFactoryCreator.class);
		registry.register(MyTransactionInterceptor.class, MyTransactionInterceptor.class);
	}
}
