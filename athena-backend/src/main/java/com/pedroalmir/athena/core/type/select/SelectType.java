/**
 * 
 */
package com.pedroalmir.athena.core.type.select;

import java.util.LinkedList;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;

import com.google.common.base.Preconditions;
import com.pedroalmir.athena.core.type.base.Type;

/**
 * @author Pedro Almir
 *
 */
public class SelectType implements Type{
	/**
	 * 
	 */
	private SelectTypeItem selectedItem;
	/**
	 * 
	 */
	private List<SelectTypeItem> availableTypes;
	
	/**
	 * 
	 */
	public SelectType() {
		super();
		this.selectedItem = null;
		this.availableTypes = new LinkedList<SelectType.SelectTypeItem>();
	}
	
	/**
	
	/**
	 * @param copy
	 */
	public SelectType(SelectType copy) {
		super();
		this.selectedItem = copy.getSelectedItem();
		this.availableTypes = copy.getAvailableTypes();
	}
	
	/**
	 * @param identifier
	 * @param name
	 * @param description
	 */
	public void addSelectTypeItemAvailable(String identifier, String name, String description){
		/* TODO: Execute Validation */
		this.availableTypes.add(new SelectTypeItem(identifier, name, description));
	}
	
	/**
	 * @param identifier
	 */
	public boolean selectItem(String identifier){
		for(SelectTypeItem item : this.getAvailableTypes()){
			if(item.getIdentifier().equals(identifier)){
				this.selectedItem = item;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return the select type item identifier
	 */
	public String getSelectTypeItemIdentifier(SelectTypeItem selectTypeItem) {
		return selectTypeItem.getIdentifier();
	}
	/**
	 * @return the select type item name
	 */
	public String getSelectTypeItemName(SelectTypeItem selectTypeItem) {
		return selectTypeItem.getName();
	}
	/**
	 * @return the select type item description
	 */
	public String getSelectTypeItemDescription(SelectTypeItem selectTypeItem) {
		return selectTypeItem.getDescription();
	}

	@Override
	public Type getClone() {
		return new SelectType(this);
	}

	@Override
	public Type getClone(String object) {
		return new SelectType();
	}

	@Override
	public void clear() {
		this.selectedItem = null;
	}

	@Override
	public Object getValue() {
		return this.selectedItem;
	}

	@Override
	public void setValue(Object object) {
		this.selectedItem = (SelectTypeItem) object;
	}

	@Override
	public void setValue(String object) {
		Preconditions.checkArgument(selectItem(object), "Could not find the option informed. Please read the module documentation.");
	}

	@Override
	public Object getRepresentation() {
		return "Select[" + StringUtils.join(this.availableTypes.iterator(), "," ) + "]";
	}

	/**
	 * @return the availableTypes
	 */
	public List<SelectTypeItem> getAvailableTypes() {
		return availableTypes;
	}

	/**
	 * @param availableTypes the availableTypes to set
	 */
	public void setAvailableTypes(List<SelectTypeItem> availableTypes) {
		this.availableTypes = availableTypes;
	}
	
	private class SelectTypeItem{
		/**
		 * This field represents the select type item identifier
		 */
		private final String identifier;
		/**
		 * This field represents the select type item name
		 * to show for user
		 */
		private final String name;
		/**
		 * This field represents the select type item description
		 * to show for user
		 */
		private final String description;
		
		/**
		 * @param identifier
		 * @param name
		 * @param description
		 * @param value
		 */
		public SelectTypeItem(String identifier, String name, String description) {
			super();
			this.identifier = identifier;
			this.name = name;
			this.description = description;
		}
		/**
		 * @return the identifier
		 */
		public String getIdentifier() {
			return identifier;
		}
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return name ;
		}
	}

	/**
	 * @return the selectedItem
	 */
	public SelectTypeItem getSelectedItem() {
		return selectedItem;
	}
	
	/**
	 * @return the selectedItem
	 */
	public String getSelectedItemIdentifier() {
		return selectedItem.getIdentifier();
	}

	/**
	 * @param selectedItem the selectedItem to set
	 */
	public void setSelectedItem(SelectTypeItem selectedItem) {
		this.selectedItem = selectedItem;
	}
}
