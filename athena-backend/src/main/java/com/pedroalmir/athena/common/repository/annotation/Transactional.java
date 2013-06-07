package com.pedroalmir.athena.common.repository.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.pedroalmir.athena.common.repository.MyJPACustomProvider;

/**
 * 
 * Os m�todos dos controllers que estiverem anotados com essa anota��o ser�o executados
 * dentro de uma transa��o JPA com commit ao final. <br/>
 * 
 * A qualquer momento podemos desativar isso e voltar a usar JPACustomProvider ao inves da {@link MyJPACustomProvider}. <br/>
 * Isso porque este modo exige que sempre lembremos de adicionar arroba transactional nos m�todos persistentes. <br/>
 * 
 * N�o sei ao certo os ganhos de usar isso em termos de performance, ent�o talvez seja melhor n�o utilizar e n�o correr o risco de
 * adicionar erro por esquecimento de adicionar essa anota��o.
 * 
 * @author Willame
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Transactional {

}
