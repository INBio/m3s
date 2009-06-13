/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.galleries.listener;

import com.google.gwt.user.client.EventListener;

/**
 * @author jgutierrez
 * 
 */
public interface LayoutSelectedListener extends EventListener {

	/**
	 * @param int
	 *            representing a constant of which layout was selected
	 */
	void layoutSelected(int layoutConstant);
}
