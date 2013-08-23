package com.pedroalmir.athena.web.controller;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

import com.pedroalmir.athena.AthenaEnvironment;
import com.pedroalmir.athena.common.annotation.MainDAO;
import com.pedroalmir.athena.common.annotation.PublicResource;
import com.pedroalmir.athena.common.controller.base.ControllerBase;
import com.pedroalmir.athena.common.exception.AthenaException;
import com.pedroalmir.athena.common.repository.GenericDAO;
import com.pedroalmir.athena.common.util.factory.SystemFactory;
import com.pedroalmir.athena.common.util.file.FileReturn;
import com.pedroalmir.athena.common.util.file.FileUtil;
import com.pedroalmir.athena.core.component.GenericConverter;
import com.pedroalmir.athena.core.component.GenericModule;
import com.pedroalmir.athena.core.system.AthenaSystem;
import com.pedroalmir.athena.core.type.file.FileType;
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
	 * 
	 */
	private Result result;
	
	/**
	 * File Util
	 */
	private FileUtil fileUtil;
	
	/**
	 * Constructor
	 * 
	 * @param fileUtil
	 */
	public SystemEditorController(Result result, FileUtil fileUtil) {
		this.fileUtil = fileUtil;
		this.result = result;
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
	
	/**
	 * @param csvFile
	 * @param fclFile
	 * @return
	 */
	@PublicResource
	@Post("/executeFuzzySystem")
	public void executeFuzzySystem(UploadedFile csvFile, UploadedFile fclFile){
		
		String csvFileRealPath = null;
		String fclFileRealPath = null;
		
		try{
			if (ServletFileUpload.isMultipartContent(request) && csvFile != null && fclFile != null) {
				csvFileRealPath = fileUtil.saveUserFile(new FileReturn(csvFile.getFileName(), csvFile.getFile()));
				fclFileRealPath = fileUtil.saveUserFile(new FileReturn(fclFile.getFileName(), fclFile.getFile()));
				
				String basePath = fileUtil.getRealPathOfRootDir();
				
				csvFileRealPath = basePath + "\\" + csvFileRealPath;
				fclFileRealPath = basePath + "\\" + fclFileRealPath;
				
				AthenaSystem fuzzySystem = SystemFactory.createFuzzySystem(fclFileRealPath, csvFileRealPath, "");
	
				long begin = System.currentTimeMillis();
				fuzzySystem.createAndExecuteSimulation("Default Fuzzy System Simulation");
				long end = System.currentTimeMillis();
				
				long diff = end - begin;
				
				String executionTime = new SimpleDateFormat("mm:ss SSS").format(diff);
				String executionDate = new SimpleDateFormat("MMMMM dd, yyyy", Locale.ENGLISH).format(new Date()) + " at " + new SimpleDateFormat("HH:mm:ss").format(new Date());
				
				int iterations = ((GenericModule) fuzzySystem.findBundleByName("Fuzzy Module").get(0)).getAlgorithm().getIterations();
				String resultFilePath = ((FileType) ((GenericConverter) fuzzySystem.findBundleByName("ToCSV Converter").get(0)).getOutputs().get(0).getType()).getFilePath();
				
				String resultFileURL = resultFilePath.replace(AthenaEnvironment.ATHENA_ROOT_PATH, AthenaEnvironment.ATHENA_BASE_URL);
				System.out.println(resultFileURL);
				
				result.include("execution", true);
				result.include("executionDate", executionDate);
				result.include("iterations", iterations);
				result.include("executionTime", executionTime);
				result.include("linkToFile", resultFileURL);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			result.include("execution", false);
		}
		
		result.redirectTo(QuickStartController.class).quickStart();
	}
}
