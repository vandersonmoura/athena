/**
 * 
 */
package com.pedroalmir.athena.common.controller;

import br.com.caelum.vraptor.Get;
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
@MainDAO(GenericDAO.class)
@SuppressWarnings("rawtypes")
public class IndexController extends ControllerBase<GenericDAO>{
	/**
	 * Redirect to index.jsp
	 */
	@Get("/")
	@NoJson
	@PublicResource
	public void index(){
		
	}
	
	@Get("/test")
	@NoJson
	@PublicResource
	public void test(){
		
	}
}
