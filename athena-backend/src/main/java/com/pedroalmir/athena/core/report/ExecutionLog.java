/**
 * 
 */
package com.pedroalmir.athena.core.report;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Pedro Almir
 *
 */
public class ExecutionLog {
	/**
	 * Execution Time
	 */
	private Long executionTime;
	/**
	 * Number of Iterations
	 */
	private Integer iterations;
	/**
	 * Execution Report
	 */
	private StringBuffer executionReport;
	/**
	 * Generated Files Path: User Path
	 */
	private List<String> generatedFilesUserPath;
	/**
	 * Generated Files Path: Real Path
	 */
	private List<String> generatedFilesRealPath;
	
	private String template;
	
	/**
	 * Default constructor
	 */
	public ExecutionLog() {
		this.executionReport = new StringBuffer();
		this.generatedFilesRealPath = new LinkedList<String>();
		this.generatedFilesUserPath = new LinkedList<String>();
	}
	
	/**
	 * @param log
	 */
	public void appendLog(String log){
		this.executionReport.append(log);
	}
	
	public void appendLogLine(String log){
		this.executionReport.append(log + "\n");
	}
	
	/**
	 * @param filePath
	 */
	public void addGeneratedFileWithRealPath(String filePath){
		this.generatedFilesRealPath.add(filePath);
	}
	
	/**
	 * @param filePath
	 */
	public void addGeneratedFileWithUserPath(String filePath){
		this.generatedFilesUserPath.add(filePath);
	}
	
	/**
	 * @param executionTime
	 * @param iterations
	 * @param executionReport
	 */
	public ExecutionLog(Long executionTime, Integer iterations, StringBuffer executionReport) {
		super();
		this.executionTime = executionTime;
		this.iterations = iterations;
		this.executionReport = executionReport;
		this.generatedFilesRealPath = new LinkedList<String>();
		this.generatedFilesUserPath = new LinkedList<String>();
	}
	/**
	 * @return the executionTime
	 */
	public Long getExecutionTime() {
		return executionTime;
	}
	/**
	 * @param executionTime the executionTime to set
	 */
	public void setExecutionTime(Long executionTime) {
		this.executionTime = executionTime;
	}
	/**
	 * @return the iterations
	 */
	public Integer getIterations() {
		return iterations;
	}
	/**
	 * @param iterations the iterations to set
	 */
	public void setIterations(Integer iterations) {
		this.iterations = iterations;
	}
	/**
	 * @return the executionReport
	 */
	public StringBuffer getExecutionReport() {
		return executionReport;
	}
	/**
	 * @param executionReport the executionReport to set
	 */
	public void setExecutionReport(StringBuffer executionReport) {
		this.executionReport = executionReport;
	}
	/**
	 * @return the generatedFilesUserPath
	 */
	public List<String> getGeneratedFilesUserPath() {
		return generatedFilesUserPath;
	}
	/**
	 * @param generatedFilesUserPath the generatedFilesUserPath to set
	 */
	public void setGeneratedFilesUserPath(List<String> generatedFilesUserPath) {
		this.generatedFilesUserPath = generatedFilesUserPath;
	}
	/**
	 * @return the generatedFilesRealPath
	 */
	public List<String> getGeneratedFilesRealPath() {
		return generatedFilesRealPath;
	}
	/**
	 * @param generatedFilesRealPath the generatedFilesRealPath to set
	 */
	public void setGeneratedFilesRealPath(List<String> generatedFilesRealPath) {
		this.generatedFilesRealPath = generatedFilesRealPath;
	}

	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}
}
