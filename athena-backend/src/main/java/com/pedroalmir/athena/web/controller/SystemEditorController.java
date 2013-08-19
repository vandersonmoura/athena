package com.pedroalmir.athena.web.controller;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;

import com.pedroalmir.athena.common.annotation.MainDAO;
import com.pedroalmir.athena.common.annotation.PublicResource;
import com.pedroalmir.athena.common.controller.base.ControllerBase;
import com.pedroalmir.athena.common.repository.GenericDAO;
import com.pedroalmir.athena.web.model.form.AthenaSystemForm;
import com.pedroalmir.athena.web.model.vo.AthenaSystemVO;
import com.pedroalmir.athena.web.model.vo.SystemEditorVO;

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
	public SystemEditorVO listAvailableBundles(){
		return new SystemEditorVO(this.request);
	}
	
	@PublicResource
	@Post("/uploadFile")
	public String uploadFile(HttpServletRequest request){
		//TODO
		return null;
	}
	
	@PublicResource
	@Post("/system/new")
	public AthenaSystemVO createSystem(String name, String description){
		return null;
	}
	
	@PublicResource
	@Post("/system/execute")
	public String execute(AthenaSystemForm system){
		return "log of execution";
	}
	
	@PublicResource
	@Get("/system/{id}/simulation/status")
	public String simulationStatus(Long id){
		return "Em execução, Erro, Sucesso";
	}
	
}
