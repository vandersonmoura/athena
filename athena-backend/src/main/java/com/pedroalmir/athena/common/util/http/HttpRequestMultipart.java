/**
 * 
 */
package com.pedroalmir.athena.common.util.http;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.pedroalmir.athena.common.util.file.FileReturn;

/**
 * @author Pedro Almir
 * 
 */
public class HttpRequestMultipart {

	/**
	 * Process request and create file
	 * 
	 * @param request
	 * @return file
	 */
	public static FileReturn processRequest(HttpServletRequest request) {
		ServletFileUpload upload = new ServletFileUpload();
		FileItemIterator iter;
		/* Return */
		InputStream inputStream = null;
		String fileName = null;

		try {
			iter = upload.getItemIterator(request);
			while (iter.hasNext()) {
				FileItemStream fileItem = iter.next();
				if (!fileItem.isFormField()) {

					inputStream = fileItem.openStream();
					fileName = fileItem.getName();

					// OutputStream outputStream = new FileOutputStream(file);
					// IOUtils.copy(inputStream, outputStream);
					// outputStream.close();
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new FileReturn(fileName, inputStream);
	}

}
