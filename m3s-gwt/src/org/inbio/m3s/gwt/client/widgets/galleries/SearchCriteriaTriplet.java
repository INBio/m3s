/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.galleries;

// import java.util.List;

import org.inbio.m3s.gwt.client.dto.util.SearchFilterEntity;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Stores the basic info of a filer/criteria for a search
 * 
 * @author jgutierrez
 * @deprecated
 * @use SearchCriteriaTripletDTO
 * 
 */
public class SearchCriteriaTriplet implements IsSerializable {
	
	private Integer filter;

	private Integer criteria;

	private String value;

	/**
	 * 
	 * Empty Constructor requiered for being serializable
	 * 
	 */
	public SearchCriteriaTriplet() {

	}

	/**
	 * Constructor
	 * 
	 * @param filter
	 * @param criteria
	 * @param value
	 */
	public SearchCriteriaTriplet(Integer filter, Integer criteria, String value) {
		this.setFilter(filter);
		this.setCriteria(criteria);
		this.setValue(value);
	}

	/**
	 * @param filter
	 *            the filter to set
	 */
	public void setFilter(Integer filter) {
		this.filter = filter;
	}

	/**
	 * @return filter
	 */
	public Integer getFilter() {
		return filter;
	}

	/**
	 * @param criteria
	 *            the criteria to set
	 */
	public void setCriteria(Integer criteria) {
		this.criteria = criteria;
	}

	/**
	 * @return the criteria
	 */
	public Integer getCriteria() {
		return criteria;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

}
