package com.pedroalmir.athena.web.controller;

import java.util.Map;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

import com.pedroalmir.athena.AthenaEnvironment;
import com.pedroalmir.athena.common.annotation.MainDAO;
import com.pedroalmir.athena.common.annotation.PublicResource;
import com.pedroalmir.athena.common.controller.base.ControllerBase;
import com.pedroalmir.athena.common.repository.GenericDAO;
import com.pedroalmir.athena.core.component.AthenaBundle;

@Resource
@Path("/editor")
@MainDAO(GenericDAO.class)
@SuppressWarnings("rawtypes")
public class SystemEditorController extends ControllerBase<GenericDAO>{
	/**
	 * @return
	 */
	@PublicResource
	@Get("/availableBundles")
	public Map<String, Class<AthenaBundle>> listAvailableBundles(){
		return AthenaEnvironment.getAvailableBundles();
	}
}
