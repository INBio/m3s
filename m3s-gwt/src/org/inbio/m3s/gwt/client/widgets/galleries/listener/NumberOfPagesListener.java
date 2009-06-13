/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.galleries.listener;

import com.google.gwt.user.client.EventListener;

/**
 * @author jgutierrez
 * 
 */
public interface NumberOfPagesListener extends EventListener {

	/**
	 * number of total pages available for the query, based on a calculation
	 * done in the SearchResultsPanel when gets te total results of a query and
	 * number of results per page.
	 * 
	 * @param int
	 *            the number of pages
	 * 
	 * 
	 * 
	 */
	void notifyNumberOfPages(int pages);

}
