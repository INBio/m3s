/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.metadata.listener;

import com.google.gwt.user.client.EventListener;

/**
 * @author jgutierrez
 * 
 */
public interface CategoryAndTypeListener extends EventListener {

	/**
	 * Fires when the selected type changes
	 * 
	 */
	void onTypeChange(String type);

}
