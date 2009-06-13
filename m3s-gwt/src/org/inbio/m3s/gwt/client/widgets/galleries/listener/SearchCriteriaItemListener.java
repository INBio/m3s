/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.galleries.listener;



import org.inbio.m3s.gwt.client.widgets.galleries.SearchCriteriaItem;

import com.google.gwt.user.client.EventListener;

/**
 * @author jgutierrez
 * 
 */
public interface SearchCriteriaItemListener extends EventListener {

	/**
	 * @param event
	 *            the SearchCriteriaItem who generates the action
	 */
	void removeCriteriaItem(SearchCriteriaItem eventObject);

}
