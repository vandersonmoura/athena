package com.pedroalmir.athena.web.controller;

import java.io.FileNotFoundException;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

import com.pedroalmir.athena.common.annotation.MainDAO;
import com.pedroalmir.athena.common.annotation.PublicResource;
import com.pedroalmir.athena.common.controller.base.ControllerBase;
import com.pedroalmir.athena.common.exception.AthenaException;
import com.pedroalmir.athena.common.repository.GenericDAO;
import com.pedroalmir.athena.common.util.file.FileReturn;
import com.pedroalmir.athena.common.util.file.FileUtil;
import com.pedroalmir.athena.web.model.form.AthenaSystemForm;
import com.pedroalmir.athena.web.model.vo.AthenaSystemVO;
import com.pedroalmir.athena.web.model.vo.SystemEditorVO;

/**
 * System Editor Controller
 * 
 * @author Pedro Almir
 *
 */
@Resource
@Path("/editor")
@MainDAO(GenericDAO.class)
@SuppressWarnings("rawtypes")
public class SystemEditorController extends ControllerBase<GenericDAO>{
	
	/**
	 * File Util
	 */
	private FileUtil fileUtil;
	
	/**
	 * Constructor
	 * 
	 * @param fileUtil
	 */
	public SystemEditorController(FileUtil fileUtil) {
		this.fileUtil = fileUtil;
	}
	/**
	 * @return system editor visual object
	 */
	@PublicResource
	@Get("/availableBundles")
	public SystemEditorVO listAvailableBundles(){
		return new SystemEditorVO(this.request);
	}
	
	/**
	 * Upload File
	 * 
	 * @param uploadedFile
	 * @return uploaded file path
	 * @throws FileNotFoundException
	 * @throws AthenaException
	 */
	@PublicResource
	@Post("/uploadFile")
	public String uploadFile(UploadedFile uploadedFile) throws FileNotFoundException, AthenaException{
		if (ServletFileUpload.isMultipartContent(request) && uploadedFile != null) {
			String uploadedUserFile = fileUtil.saveUserFile(new FileReturn(uploadedFile.getFileName(), uploadedFile.getFile()));
			return this.request.getContextPath() + "/" + uploadedUserFile;
		}
		throw new AthenaException();
	}
	
	/**
	 * Create System
	 * 
	 * @param name
	 * @param description
	 * @return AthenaSystem visual object
	 */
	@PublicResource
	@Post("/system/new")
	public AthenaSystemVO createSystem(String name, String description){
		return null;
	}
	
	/**
	 * Execute AthenaSystem
	 * @param system
	 * @return
	 */
	@PublicResource
	@Post("/system/execute")
	public String execute(AthenaSystemForm system){
		return "log of execution";
	}
	
	/**
	 * @param id
	 * @return
	 */
	@PublicResource
	@Get("/system/{id}/simulation/status")
	public String simulationStatus(Long id){
		return "Em execução, Erro, Sucesso";
	}
	
}
