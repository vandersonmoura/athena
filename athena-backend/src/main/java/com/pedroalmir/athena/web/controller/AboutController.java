/**
 * 
 */
package com.pedroalmir.athena.web.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

import com.pedroalmir.athena.common.annotation.MainDAO;
import com.pedroalmir.athena.common.annotation.NoJson;
import com.pedroalmir.athena.common.annotation.PublicResource;
import com.pedroalmir.athena.common.controller.base.ControllerBase;
import com.pedroalmir.athena.common.repository.GenericDAO;

/**
 * @author Pedro Almir
 *
 */
@Resource
@Path("/about")
@MainDAO(GenericDAO.class)
@SuppressWarnings("rawtypes")
public class AboutController extends ControllerBase<GenericDAO> {
	
	/**
	 * Redirect to about.jsp
	 */
	@Get("")
	@NoJson
	@PublicResource
	public void about(){
		
	}
}
