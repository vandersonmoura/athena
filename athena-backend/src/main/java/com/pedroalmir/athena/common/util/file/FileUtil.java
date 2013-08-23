package com.pedroalmir.athena.common.util.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.ioc.Component;

/**
 * 
 * Utility methods for working with local files
 * 
 * @author Pedro Oliveira
 */
@Component
public class FileUtil {

	/**
	 * request
	 */
	private HttpServletRequest request;
	/**
	 * 
	 */
	private static final String USER_FILE_FOLDER = "user/";

	/**
	 * @param request
	 */
	public FileUtil(HttpServletRequest request) {
		this.request = request;
	}
	
	/**
	 * @param fileStream
	 * @param directory
	 *            
	 * @return the generated file name
	 */
	public String saveUserFile(InputStream fileStream) {
		return createFile(fileStream, USER_FILE_FOLDER, "default.txt");
	}
	
	/**
	 * @param fileStream
	 * @param directory
	 *            
	 * @return the generated file name
	 */
	public String saveUserFile(InputStream fileStream, String directory) {
		return createFile(fileStream, directory, "default.txt");
	}
	
	/**
	 * @param fileReturn
	 * @return
	 */
	public String saveUserFile(FileReturn fileReturn) {
		return createFile(fileReturn.getInputStream(), USER_FILE_FOLDER, fileReturn.getFileName());
	}
	
	/**
	 * @param fileReturn
	 * @param directory
	 * @return
	 */
	public String saveUserFile(FileReturn fileReturn, String directory) {
		return createFile(fileReturn.getInputStream(), directory, fileReturn.getFileName());
	}

	/**
	 * @param dir
	 *            	Directory
	 * @return Complete real directory server
	 */
	public String getRealPath(String dir) {
		return request.getServletContext().getRealPath(dir);
	}
	
	/**
	 * @return
	 */
	public String getRealPathOfUserDir() {
		return request.getServletContext().getRealPath(USER_FILE_FOLDER);
	}
	
	public String getRealPathOfRootDir() {
		return request.getServletContext().getRealPath("/");
	}

	/**
	 * @param fileStream
	 *            Input stream from file
	 * @param dir
	 *            Directory to be saved
	 * @return Filename generated
	 */
	private String createFile(InputStream fileStream, String dir, String fileName) {

		dir = getRealPath(dir);

		ByteArrayOutputStream bos = null;

		try {
			bos = new ByteArrayOutputStream();
			IOUtils.copy(fileStream, bos);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bos.close();
				fileStream.close();
			} catch (IOException ignored) {
			}
		}

		byte[] byteArray = bos.toByteArray();

		FileOutputStream fos = null;

		if (byteArray == null) {
			throw new RuntimeException("Byte Array Nulo");
		}

		String newFileName = "file_" + new Date().getTime() + "." +  FilenameUtils.getExtension(fileName);

		File file = new File(dir, newFileName);
		
		try {
			file.createNewFile();
			file.mkdirs();
			fos = new FileOutputStream(file);
			fos.write(byteArray);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return USER_FILE_FOLDER + newFileName;

	}
}
