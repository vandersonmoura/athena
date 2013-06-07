package com.pedroalmir.athena.common.handler;

import java.util.Arrays;
import java.util.Collection;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.interceptor.ParametersInstantiatorInterceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

import com.pedroalmir.athena.common.annotation.Permission;
import com.pedroalmir.athena.common.annotation.PublicResource;
import com.pedroalmir.athena.common.model.enums.EnumProfile;
import com.pedroalmir.athena.common.session.UserSession;


/**
 * 
 * It intercepts all requests to verify that the User has permission to access the requested resource
 * 
 * @author Pedro Oliveira
 */
@Intercepts(after = { ParametersInstantiatorInterceptor.class })
public class PermissionInterceptor implements Interceptor {

	/**
	 * PERMISSION_DENIED_FILE
	 */
	private static final String PERMISSION_DENIED_FILE = "/permission-denied.jsp";

	/**
	 * userSession
	 */
	private UserSession userSession;

	/**
	 * result
	 */
	private Result result;

	/**
	 * Default constructor
	 * 
	 * @param userSession
	 * @param result
	 */
	public PermissionInterceptor(UserSession userSession, Result result) {
		this.userSession = userSession;
		this.result = result;
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance) throws InterceptionException {
		/*
		 * Verifica se o usu�rio est� logado
		 * */
		if(userSession.isLogged()){
			/* Recolhe as informa��es da anota��o Permission presente no m�todo. */
			Permission methodPermission = method.getMethod().getAnnotation(Permission.class);
			/* Recolhe as informa��es da anota��o Permission presente no controller. */
			Permission controllerPermission = method.getResource().getType().getAnnotation(Permission.class);
			/* Verifica se realmente o usu�rio tem essa permiss�o. */
			if (!this.hasAccess(methodPermission) && !this.hasAccess(controllerPermission)) {
				/* Redireciona o usu�rio para a p�gina de permiss�o negada. */
				result.redirectTo(PERMISSION_DENIED_FILE);
			}else{
				stack.next(method, resourceInstance);
			}
		}else{
			/* Redireciona o usu�rio para a p�gina de permiss�o negada. */
			result.redirectTo(PERMISSION_DENIED_FILE);
		}
		
	}
	
	/**
	 * Deletes all class that has the annotation
	 * PublicResource, so they will not be intercepted.
	 * @param method
	 * @return the for not PublicResource
	 * */
	public boolean accepts(ResourceMethod method) {
		 return !(method.getMethod().isAnnotationPresent(PublicResource.class) || method.getResource().getType().isAnnotationPresent(PublicResource.class));

	}
	
	/**
	 * Checks if the user has permission passed as parameter.
	 * 
	 * @param permission
	 * @return true if the user has permission
	 */
	private boolean hasAccess(Permission permission) {
	    if (permission == null) {
	        return true;
	    }

	    Collection<EnumProfile> profileList = Arrays.asList(permission.value());

	    return profileList.contains(userSession.getUser().getProfile());
	}

}
