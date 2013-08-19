package com.pedroalmir.athena.common.handler;

import java.util.Arrays;
import java.util.Collection;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
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
	@SuppressWarnings("unused")
	private static final String PERMISSION_DENIED_FILE = "/permission-denied.jsp";

	/**
	 * userSession
	 */
	private UserSession userSession;
	/**
	 * outputHandler
	 */
	private OutputHandler outputHandler;

	/**
	 * Default constructor
	 * 
	 * @param userSession
	 * @param result
	 */
	public PermissionInterceptor(UserSession userSession, OutputHandler outputHandler) {
		this.userSession = userSession;
		this.outputHandler = outputHandler;
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance) throws InterceptionException {
		/*
		 * Verifica se o usuúrio está logado
		 * */
		if(userSession.isLogged()){
			/* Recolhe as informações da anotação Permission presente no método. */
			Permission methodPermission = method.getMethod().getAnnotation(Permission.class);
			/* Recolhe as informações da anotação Permission presente no controller. */
			Permission controllerPermission = method.getResource().getType().getAnnotation(Permission.class);
			/* Verifica se realmente o usuário tem essa permissão. */
			if (!this.hasAccess(methodPermission) && !this.hasAccess(controllerPermission)) {
				/* Redireciona o usuário para a página de permissão negada. */
				outputHandler.responsePermissionDenied();
			}else{
				stack.next(method, resourceInstance);
			}
		}else{
			/* Redireciona o usuário para a página de permissão negada. */
			outputHandler.responsePermissionDenied();
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
	        return false;
	    }

	    Collection<EnumProfile> profileList = Arrays.asList(permission.value());

	    return profileList.contains(userSession.getUser().getProfile());
	}

}
