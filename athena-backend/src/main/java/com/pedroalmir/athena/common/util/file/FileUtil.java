package com.pedroalmir.athena.common.util.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
	public String saveUserImage(InputStream fileStream, String directory) {
		return createFile(fileStream, directory);
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
	 * @param fileStream
	 *            Input stream from file
	 * @param dir
	 *            Directory to be saved
	 * @return Filename generated
	 */
	private String createFile(InputStream fileStream, String dir) {

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

		String fileName = "file_" + new Date().getTime();

		File file = new File(dir, fileName);
		try {
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

		return fileName;

	}
}
