package org.inbio.m3s.gwt.client.widgets.verticalmenu;

import org.inbio.m3s.gwt.client.widgets.verticalmenu.listener.MenuItemSelectedListener;


/**
 * A widget that implements this interface sources the events defined by the
 * ItemSelectedListener interface.
 */
public interface SourcesMenuItemSelectedEvents {

	  /**
	   * Adds a listener interface to item selected events.
	   * 
	   * @param listener the listener interface to add
	   */
	  void addItemSelectedListener(MenuItemSelectedListener listener);

	  /**
	   * Removes a previously added listener interface.
	   * 
	   * @param listener the listener interface to remove
	   */
	  void removeItemSelectedListener(MenuItemSelectedListener listener);
}
