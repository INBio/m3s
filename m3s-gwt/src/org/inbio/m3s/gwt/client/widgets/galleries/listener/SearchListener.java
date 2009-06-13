/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.galleries.listener;

import java.util.List;

import com.google.gwt.user.client.EventListener;

/**
 * @author jgutierrez
 * 
 */
public interface SearchListener extends EventListener {

	/**
	 * 
	 * @param querySummary
	 *            a list of SearchFilterTriplets
	 */
	public void executeSearch(List querySummary);

}
