package org.inbio.m3s.gwt.client.widgets.verticalmenu.listener;

import java.util.EventListener;

/**
 * 
 * This class extends from java.util.EventListener
 */
public interface MenuItemSelectedListener extends EventListener {

	/**
	 * @param int
	 *            representing the index of the menu item selected
	 */
	void itemSelected(int itemIndex);

}