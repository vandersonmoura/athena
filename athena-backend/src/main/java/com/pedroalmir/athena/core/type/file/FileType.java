/**
 * 
 */
package com.pedroalmir.athena.core.type.file;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.pedroalmir.athena.core.type.base.Type;

/**
 * This class represents a file type in Athena Service.
 * It implements {@code Type} interface.
 * 
 * @author Pedro Almir
 *
 */
public class FileType implements Type {
	
	/**
	 * File path
	 */
	private String filePath;
	/**
	 * File
	 */
	private File file;
	/**
	 * File Reader
	 */
	private FileReader reader;
	
	/**
	 * @param filePath
	 */
	public FileType(String filePath) {
		try {
			this.file = new File(checkNotNull(filePath));
			this.filePath = filePath;
			this.reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param file
	 */
	public FileType(File file) {
		try {
			this.file = checkNotNull(file);
			this.filePath = file.getAbsolutePath();
			this.reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Default constructor
	 */
	public FileType() {
		this.file = null;
		this.filePath = null;
		this.reader = null;
	}
	
	public Type getClone() {
		return new FileType(this.filePath);
	}

	public Object getValue() {
		return this.file;
	}

	public void setValue(Object object) {
		try {
			checkArgument(object instanceof File, "The Argument is not instance of File");
			this.file = (File) checkNotNull(object);
			this.filePath = file.getAbsolutePath();
			this.reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Object getRepresentation() {
		return "file";
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return the reader
	 */
	public FileReader getReader() {
		return reader;
	}

	/**
	 * @param reader the reader to set
	 */
	public void setReader(FileReader reader) {
		this.reader = reader;
	}

	public void setValue(String object) {
		try {
			this.file = new File(checkNotNull(object));
			this.filePath = file.getAbsolutePath();
			this.reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Type getClone(String object) {
		return new FileType(checkNotNull(object));
	}

	@Override
	public void clear() {
		this.file = null;
		this.filePath = null;
		this.reader = null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileType [filePath=" + filePath + "]";
	}

}
