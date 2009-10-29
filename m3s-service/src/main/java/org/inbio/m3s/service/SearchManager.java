package org.inbio.m3s.service;

import java.util.List;

import org.inbio.m3s.dto.search.SearchCriteriaTripletDTO;

public interface SearchManager {

	/**
	 * Returns the total of elements that could be retrived using that search
	 * criteria
	 * 
	 * @return number of results
	 */
	public abstract Integer getTotalResults(
			List<SearchCriteriaTripletDTO> searchCriteria)
			throws IllegalArgumentException;

	/**
	 * 
	 * @param searchCriteria
	 * @param first
	 * @param last
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	public abstract List<Integer> getResults(List searchCriteria, int first,
			int last) throws IllegalArgumentException;

}