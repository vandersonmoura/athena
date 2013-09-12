/**
 * 
 */
package com.pedroalmir.athena.web.model.vo.log;

import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.athena.AthenaEnvironment;
import com.pedroalmir.athena.core.report.ExecutionLog;

/**
 * @author Pedro Almir
 *
 */
public class ExecutionLogVO {
	private final Long executionTime;
	private final String executionReport;
	private final List<String> generatedFilesUserPath;
	
	/**
	 * @param executionLog
	 */
	public ExecutionLogVO(ExecutionLog executionLog) {
		this.executionTime = executionLog.getExecutionTime();
		/* Just for debug */
		//System.out.println(executionLog.getTemplate());
		this.executionReport = executionLog.getTemplate();
		this.generatedFilesUserPath = new LinkedList<String>();
		for(String path : executionLog.getGeneratedFilesRealPath()){
			String[] split = path.split("athena-backend");
			String userPath = AthenaEnvironment.ATHENA_BASE_URL_II + split[1];
			generatedFilesUserPath.add(userPath);
			//System.out.println(userPath);
		}
	}
	
	/**
	 * @return the executionTime
	 */
	public Long getExecutionTime() {
		return executionTime;
	}
	/**
	 * @return the executionReport
	 */
	public String getExecutionReport() {
		return executionReport;
	}
	/**
	 * @return the generatedFilesUserPath
	 */
	public List<String> getGeneratedFilesUserPath() {
		return generatedFilesUserPath;
	}
	
}
