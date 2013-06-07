package com.pedroalmir.athena.common.repository.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.pedroalmir.athena.common.repository.MyJPACustomProvider;

/**
 * 
 * Os métodos dos controllers que estiverem anotados com essa anotação serão executados
 * dentro de uma transação JPA com commit ao final. <br/>
 * 
 * A qualquer momento podemos desativar isso e voltar a usar JPACustomProvider ao inves da {@link MyJPACustomProvider}. <br/>
 * Isso porque este modo exige que sempre lembremos de adicionar arroba transactional nos métodos persistentes. <br/>
 * 
 * Não sei ao certo os ganhos de usar isso em termos de performance, então talvez seja melhor não utilizar e não correr o risco de
 * adicionar erro por esquecimento de adicionar essa anotação.
 * 
 * @author Willame
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Transactional {

}
