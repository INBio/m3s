/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.galleries.listener;

import com.google.gwt.user.client.EventListener;

/**
 * @author jgutierrez
 * 
 */
public interface ResultsPerPageSelectedListener extends EventListener {

	/**
	 * @param int
	 *            number of results per page
	 */
	void resultsPerPageSelected(int results);
}
