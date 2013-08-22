/**
 * 
 */
package com.pedroalmir.athena.common.util.file;

import java.io.InputStream;

/**
 * @author Pedro Almir
 *
 */
public class FileReturn {
	/**
	 * 
	 */
	private final String fileName;
	/**
	 * 
	 */
	private final InputStream inputStream;

	/**
	 * @param fileName
	 * @param inputStream
	 */
	public FileReturn(String fileName, InputStream inputStream) {
		super();
		this.fileName = fileName;
		this.inputStream = inputStream;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}
}
