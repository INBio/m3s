/**
 * 
 */
package org.inbio.m3s.dto.search;

import java.io.Serializable;


/**
 * Stores the basic info of a filer/criteria for a search
 * 
 * @author jgutierrez
 *
 * 
 */
public class SearchCriteriaTripletDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer filter;

	private Integer criteria;

	private String value;

	/**
	 * 
	 * Empty Constructor requiered for being serializable
	 * 
	 */
	public SearchCriteriaTripletDTO() {

	}

	/**
	 * Constructor
	 * 
	 * @param filter
	 * @param criteria
	 * @param value
	 */
	public SearchCriteriaTripletDTO(Integer filter, Integer criteria, String value) {
		this.setFilter(filter);
		this.setCriteria(criteria);
		this.setValue(value);
	}

	@Override
	public String toString(){
		return "El Search Criteria Triplet DTO tiene:" +
		"\n\tFilter : " + this.getFilter() +
		"\n\tCriteria : " + this.getCriteria() +
		"\n\tValue: "+ this.getValue() +
		"";
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
