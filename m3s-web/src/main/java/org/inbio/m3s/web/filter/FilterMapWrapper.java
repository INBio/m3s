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

import java.util.LinkedList;
import java.util.List;

/**
 * A class necessary for spring to load a list of filters.
 * 
 * @author Dave Martin
 */
public class FilterMapWrapper {

	/** all the filters for this context */
	protected List<FilterDTO> filters;

	/**
	 * Retrieves a Filter by its display name.
	 * @param displayName
	 * @return the filter
	 */
	public FilterDTO getFilterByDisplayName(List<FilterDTO> filters, String displayName){
		
		if(displayName==null)
			return null;
		
		for(FilterDTO filterDTO: filters){
			if (displayName.equals(filterDTO.getDisplayName() ))
				return filterDTO;
		}
		return null;
	}

	/**
	 * Retrieves a list Filter by category and subcategory.
	 * @param category
	 * @param subcategory
	 * @return the list of filters
	public List<FilterDTO> getFiltersByCategory(String category, String subcategory){
		
		if (category==null)
			return filters;
		
		List<FilterDTO> categoryFilters = new LinkedList<FilterDTO>();
		
		for(FilterDTO filterDTO: filters){
			if (category!=null && category.equals(filterDTO.getCategory() )){
				if (subcategory!=null && subcategory.equals(filterDTO.getSubcategory()))
					categoryFilters.add(filterDTO);
				else if (subcategory==null)
					categoryFilters.add(filterDTO);	
			}
		}
		return categoryFilters;
	}
	*/
	
	/**
	 * Retrieves a list Filter by category.
	 * @param category
	 * @return the list of filters
	public List<FilterDTO> getFiltersByCategory(String category){
		return getFiltersByCategory(category, null);
	}
		 */		

	/**
	 * @return the filters
	 */
	public List<FilterDTO> getFilters() {
		return filters;
	}

	/**
	 * @param filters the filters to set
	 */
	public void setFilters(List<FilterDTO> filters) {
		this.filters = filters;
	}	
}