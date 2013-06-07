package com.pedroalmir.athena.common.handler;

import br.com.caelum.vraptor.http.FormatResolver;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.view.DefaultPathResolver;


/**
 * This class changes the default VRaptor behavior to render JSP in
 * /views/ instead of /WEB-INF/views/
 * 
 * @author EasyTeam, created by Pedro Almir
 *
 */
@Component
public class SiteMeshPathResolver extends DefaultPathResolver {

	/**
	 * Site mesh path resolver
	 * 
	 * @param resolver
	 */
	public SiteMeshPathResolver(FormatResolver resolver) {
		super(resolver);
	}

	
	@Override
	protected String getPrefix() {
		return "/views/";
	}
	
	@Override
	protected String getExtension() {
		return "jsp";
	}
}
