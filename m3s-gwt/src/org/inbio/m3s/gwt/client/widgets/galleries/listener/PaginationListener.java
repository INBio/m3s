/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.galleries.listener;

import com.google.gwt.user.client.EventListener;

/**
 * @author jgutierrez
 *
 */
public interface PaginationListener extends EventListener {
	
	/**
	 * @param int
	 *            number of page clicked
	 */
	void pageSelected(int page);

}
