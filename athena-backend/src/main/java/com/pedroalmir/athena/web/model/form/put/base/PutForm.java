/**
 * 
 */
package com.pedroalmir.athena.web.model.form.put.base;

import java.util.List;

/**
 * @author Pedro Almir
 *
 */
public class PutForm {
	protected boolean frontCanDelete;
	/**
	 * TODO: Remover esse campo. Nem ser pra que ele serve.
	 */
	protected String inoutputtype;
	/**
	 * Name in lower case
	 */
	protected String frontIdentifier;
	/**
	 * Front name
	 */
	protected String frontName;
	/**
	 * Selected type
	 */
	protected String frontType;
	/**
	 * TODO: Alterar o nome desse campo para values
	 */
	protected List<String> frontComponents;
	/**
	 * Put type
	 * private String type;
	 * 
	 * Put representation
	 * For example:
	 * List[valueA, valueB]
	 * private String representation;
	 * 
	 * TODO: Remover esse campo e adicionar esses dois.
	 */
	@Deprecated
	//protected TypeForm type;
	/**
	 * TODO: Incluir no front-end
	 */
	protected boolean linked;
	/**
	 * TODO: Incluir no front-end
	 * 
	 * If this field is <code>true</code> so this
	 * Put may have multiple values.
	 */
	protected boolean multipleValues;
	
	/**
	 * Default constructor
	 */
	public PutForm() {
		
	}
	
	/**
	 * @param frontCanDelete
	 * @param inoutputtype
	 * @param frontIdentifier
	 * @param frontName
	 * @param frontType
	 * @param frontComponents
	 * @param type
	 * @param linked
	 * @param multipleValues
	 */
	public PutForm(String inoutputtype, String frontIdentifier, String frontName, String frontType,
			List<String> frontComponents, boolean linked, boolean multipleValues) {
		super();
		this.inoutputtype = inoutputtype;
		this.frontIdentifier = frontIdentifier;
		this.frontName = frontName;
		this.frontType = frontType;
		this.frontComponents = frontComponents;
		this.linked = linked;
		this.multipleValues = multipleValues;
	}
	/**
	 * @return the frontCanDelete
	 */
	/**
	 * @return the inoutputtype
	 */
	public String getInoutputtype() {
		return inoutputtype;
	}
	/**
	 * @param inoutputtype the inoutputtype to set
	 */
	public void setInoutputtype(String inoutputtype) {
		this.inoutputtype = inoutputtype;
	}
	/**
	 * @return the frontIdentifier
	 */
	public String getFrontIdentifier() {
		return frontIdentifier;
	}
	/**
	 * @param frontIdentifier the frontIdentifier to set
	 */
	public void setFrontIdentifier(String frontIdentifier) {
		this.frontIdentifier = frontIdentifier;
	}
	/**
	 * @return the frontName
	 */
	public String getFrontName() {
		return frontName;
	}
	/**
	 * @param frontName the frontName to set
	 */
	public void setFrontName(String frontName) {
		this.frontName = frontName;
	}
	/**
	 * @return the frontType
	 */
	public String getFrontType() {
		return frontType;
	}
	/**
	 * @param frontType the frontType to set
	 */
	public void setFrontType(String frontType) {
		this.frontType = frontType;
	}
	/**
	 * @return the frontComponents
	 */
	public List<String> getFrontComponents() {
		return frontComponents;
	}
	/**
	 * @param frontComponents the frontComponents to set
	 */
	public void setFrontComponents(List<String> frontComponents) {
		this.frontComponents = frontComponents;
	}
	/**
	 * @return the linked
	 */
	public boolean isLinked() {
		return linked;
	}
	/**
	 * @param linked the linked to set
	 */
	public void setLinked(boolean linked) {
		this.linked = linked;
	}
	/**
	 * @return the multipleValues
	 */
	public boolean isMultipleValues() {
		return multipleValues;
	}
	/**
	 * @param multipleValues the multipleValues to set
	 */
	public void setMultipleValues(boolean multipleValues) {
		this.multipleValues = multipleValues;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PutForm [inoutputtype=" + inoutputtype + ", frontIdentifier="
				+ frontIdentifier + ", frontName=" + frontName + ", frontType=" + frontType + ", frontComponents="
				+ frontComponents + ", linked=" + linked + ", multipleValues=" + multipleValues + "]";
	}
	
}
