/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.galleries.listener;



import org.inbio.m3s.gwt.client.widgets.galleries.SearchCriteriaTriplet;

import com.google.gwt.user.client.EventListener;

/**
 * @author jgutierrez
 * 
 */
public interface SearchFilterListener extends EventListener {

	public void filterSelected(SearchCriteriaTriplet triplete);

	public void invalidFilter(String cause);
}
