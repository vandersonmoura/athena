package com.pedroalmir.athena;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 
 */

/**
 * @author Pedro Almir
 *
 */
public class AthenaStartup extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6606636086933445697L;
	
	@Override
	public void init() throws ServletException {
		super.init();
		String realPath = this.getServletContext().getRealPath(AthenaEnvironment.ATHENA_RESULT_FOLDER);
		/* Just for debug */
		System.out.println(realPath);
		AthenaEnvironment.setATHENA_RESULT_FOLDER_FULL_PATH(realPath);
		AthenaEnvironment.IN_TOMCAT = true;
		AthenaEnvironment.ATHENA_ROOT_PATH =  this.getServletContext().getRealPath("/");
	}
	
	
}
