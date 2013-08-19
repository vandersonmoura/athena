package com.pedroalmir.athena.web.model.vo.link;

import javax.servlet.http.HttpServletRequest;

import com.pedroalmir.athena.core.system.link.Link;
import com.pedroalmir.athena.web.model.vo.bundle.base.AthenaBundleVO;
import com.pedroalmir.athena.web.model.vo.put.InputVO;
import com.pedroalmir.athena.web.model.vo.put.OutputVO;

/**
 * @author Pedro Almir
 *
 */
public class LinkVO {
	/**
	 * 
	 */
	private final Long id;
	/**
	 * Description label
	 */
	private final String description;
	/**
	 * Source module
	 */
	private final AthenaBundleVO srcModule;
	/**
	 * Destination module
	 */
	private final AthenaBundleVO dstModule;
	/**
	 * Source output
	 */
	private final OutputVO srcOutput;
	/**
	 * Destination input
	 */
	private final InputVO dstInput;
	
	/**
	 * @param link
	 */
	public LinkVO(Link link, HttpServletRequest request) {
		super();
		this.id = link.getId();
		this.description = link.getDescription();
		this.srcModule = new AthenaBundleVO(link.getSrcModule(), request);
		this.dstModule = new AthenaBundleVO(link.getDstModule(), request);
		this.srcOutput = new OutputVO(link.getSrcOutput());
		this.dstInput = new InputVO(link.getDstInput());
	}
	/**
	 * @param id
	 * @param description
	 * @param srcModule
	 * @param dstModule
	 * @param srcOutput
	 * @param dstInput
	 */
	public LinkVO(Long id, String description, AthenaBundleVO srcModule,
			AthenaBundleVO dstModule, OutputVO srcOutput, InputVO dstInput) {
		super();
		this.id = id;
		this.description = description;
		this.srcModule = srcModule;
		this.dstModule = dstModule;
		this.srcOutput = srcOutput;
		this.dstInput = dstInput;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @return the srcModule
	 */
	public AthenaBundleVO getSrcModule() {
		return srcModule;
	}
	/**
	 * @return the dstModule
	 */
	public AthenaBundleVO getDstModule() {
		return dstModule;
	}
	/**
	 * @return the srcOutput
	 */
	public OutputVO getSrcOutput() {
		return srcOutput;
	}
	/**
	 * @return the dstInput
	 */
	public InputVO getDstInput() {
		return dstInput;
	}
}
