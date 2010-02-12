/***************************************************************************
 * Copyright (C) 2005 Global Biodiversity Information Facility Secretariat.  
 * All Rights Reserved.
 *
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 ***************************************************************************/
package org.inbio.m3s.web.filter;

import java.util.Map;

//import org.gbif.portal.web.content.filter.FilterHelper;
//import org.gbif.portal.web.content.filter.PicklistHelper;

/**
 * A DTO containing details of a filter.
 *
 * TODO Should be split out into an abstract class with 3 implementations
 * 1) StringFilterDTO
 * 2) ComboFilterDTO
 * 3) WizardFilterDTO
 * 
 * Spring config would then reference the correct implementation.
 *
 * @author Dave Martin
 */
public class FilterDTO {

	public static final int STRING_FILTER = 0;
	public static final int COMBO_FILTER = 1;
	public static final int WIZARD_FILTER = 2;
	
	/** This id is used to tally up with a set of critieria */
	private String id;
	/** A name value - used for picking a filter out by name */
	private String name;
	/** Corresponds to a i18n key usually */
	private String displayName;
	/** The property store subject key this filter corresponds to */
	private String subject;
	/** The category this filter falls into. e.g. Taxonomy */
	//private String categoryId;	 --> no se ocupa
	/** The category this filter falls into. e.g. Taxonomy */
	//private String category; --> no se ocupa
	/** The subcategory this filter falls into */
	//private String subcategory; --> no se ocupa
	/** A list of String predicates */
	//private List<PredicateDTO> predicates;
	/** The type of the value object associated with this filter e.g. date  */
	private String valueType;
	/** The filter type. Default of normal string type */
	private int filterType = STRING_FILTER;
	/** The id of the default predicate to use */ 
	private String defaultPredicateId = "0";
	/** Indicates whether a filter takes wildcard characters */ 
	private boolean wildcardFriendly;
	/** A preprocessor to be called on a criterion before a query is executed. Optional */
	//private FilterHelper filterHelper;
	
	/** Drop down values in use - optional, for use with a combo filter */
	private Map<String, String> dropDownValues;
	/** A helper used to populate the combo box. Optional */
	//private PicklistHelper picklistHelper;
	
	/** Wizard view to use for this filter - optional, for use with a combo filter */
	private String wizardView;

	/** Optional, for use with a combo filter or string filter */
	private String helpView;	
	
	/** Whether multiple filters of this type should be allowed in the same query */
	private boolean allowMultiple = true;

	/** For string type filter that support autocomplete */
	private String autoCompleteUrl;

	/** whether or not this filter should be editable by the user i.e. through the front end */
	private boolean editable = true;
	
	/** Multiple condition i18n key */ 
	private String i18nMultipleConditionKey = "or";	
	
	/**
	 * @return the allowMultiple
	 */
	public boolean isAllowMultiple() {
		return allowMultiple;
	}

	/**
	 * @param allowMultiple the allowMultiple to set
	 */
	public boolean getAllowMultiple() {
		return this.allowMultiple;
	}	
	
	/**
	 * @param allowMultiple the allowMultiple to set
	 */
	public void setAllowMultiple(boolean allowMultiple) {
		this.allowMultiple = allowMultiple;
	}

	/**
	 * @return the defaultPredicateId
	 */
	public String getDefaultPredicateId() {
		return defaultPredicateId;
	}

	/**
	 * @param defaultPredicateId the defaultPredicateId to set
	 */
	public void setDefaultPredicateId(String defaultPredicateId) {
		this.defaultPredicateId = defaultPredicateId;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the predicates
	 */
	//public List<PredicateDTO> getPredicates() {
	//	return predicates;
	//}

	/**
	 * @param predicates the predicates to set
	 */
	//public void setPredicates(List<PredicateDTO> predicates) {
	//	this.predicates = predicates;
	//}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the valueType
	 */
	public String getValueType() {
		return valueType;
	}

	/**
	 * @param valueType the valueType to set
	 */
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	/**
	 * @return the wildcardFriendly
	 */
	public boolean isWildcardFriendly() {
		return wildcardFriendly;
	}

	/**
	 * @param wildcardFriendly the wildcardFriendly to set
	 */
	public void setWildcardFriendly(boolean wildcardFriendly) {
		this.wildcardFriendly = wildcardFriendly;
	}

	/**
	 * @return the filterPreQueryProcessor
	 */
	//public FilterHelper getFilterHelper() {
	//	return filterHelper;
	//}

	/**
	 * @param filterPreQueryProcessor the filterPreQueryProcessor to set
	 */
	//public void setFilterHelper(
	//		FilterHelper filterPreQueryProcessor) {
	//	this.filterHelper = filterPreQueryProcessor;
	//}

	/**
	 * @return the dropDownValues
	 */
	public Map<String, String> getDropDownValues() {
		return dropDownValues;
	}

	/**
	 * @param dropDownValues the dropDownValues to set
	 */
	public void setDropDownValues(Map<String, String> dropDownValues) {
		this.dropDownValues = dropDownValues;
	}

	/**
	 * @return the filterType
	 */
	public int getFilterType() {
		return filterType;
	}

	/**
	 * @param filterType the filterType to set
	 */
	public void setFilterType(int filterType) {
		this.filterType = filterType;
	}

	/**
	 * @return the wizardView
	 */
	public String getWizardView() {
		return wizardView;
	}

	/**
	 * @param wizardView the wizardView to set
	 */
	public void setWizardView(String wizardView) {
		this.wizardView = wizardView;
	}

	/**
	 * @return the helpText
	 */
	public String getHelpView() {
		return helpView;
	}

	/**
	 * @param helpText the helpText to set
	 */
	public void setHelpView(String helpText) {
		this.helpView = helpText;
	}

	/**
	 * @return the autoCompleteUrl
	 */
	public String getAutoCompleteUrl() {
		return autoCompleteUrl;
	}

	/**
	 * @param autoCompleteUrl the autoCompleteUrl to set
	 */
	public void setAutoCompleteUrl(String autoCompleteUrl) {
		this.autoCompleteUrl = autoCompleteUrl;
	}

	/**
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * @param editable the editable to set
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * @return the picklistHelper
	 */
	//public PicklistHelper getPicklistHelper() {
	//	return picklistHelper;
	//}

	/**
	 * @param picklistHelper the picklistHelper to set
	 */
	//public void setPicklistHelper(PicklistHelper picklistHelper) {
	//	this.picklistHelper = picklistHelper;
	//}

	/**
	 * @return the i18nMultipleConditionKey
	 */
	public String getI18nMultipleConditionKey() {
		return i18nMultipleConditionKey;
	}

	/**
	 * @param multipleConditionKey the i18nMultipleConditionKey to set
	 */
	public void setI18nMultipleConditionKey(String multipleConditionKey) {
		i18nMultipleConditionKey = multipleConditionKey;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		if(object==null)
			return false;
		if(object instanceof FilterDTO){
			FilterDTO filter = (FilterDTO) object;
			if(filter.getId()==null && this.getId()==null)
				return true;
			if(filter.getId()==null || this.getId()==null)
				return true;
			return this.getId().equals(filter.getId());
		}
		return false;
	}	
}