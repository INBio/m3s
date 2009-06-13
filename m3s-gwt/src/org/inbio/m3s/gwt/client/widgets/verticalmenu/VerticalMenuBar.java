package org.inbio.m3s.gwt.client.widgets.verticalmenu;

import java.util.ArrayList;

import org.inbio.m3s.gwt.client.widgets.verticalmenu.listener.MenuItemSelectedListener;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/*
 * A small menu bar
 */
public class VerticalMenuBar extends Composite implements ClickListener,
		SourcesMenuItemSelectedEvents {

	// MenuItems list
	private ArrayList<MenuItem> items = new ArrayList<MenuItem>();

	// array index of the actual menu item selected
	private int actualItem;

	private MenuItemSelectedListener itemSelectedListener;

	private VerticalPanel panel = new VerticalPanel();

	// creates new MenuBar with the properties asigned in the CSS
	public VerticalMenuBar() {
		actualItem = -1;
		panel.addStyleName("VerticalMenu");
		initWidget(panel);
	}

	/**
	 * Add's a new item to the Menu. Explicity indicating that this new item is
	 * going to be the actual active item
	 * 
	 * @param displayText
	 */
	public void addActiveItem(String displayText) {
		// int number = addItem(displayText);
		// TODO: fix this shit!.
	}

	/**
	 * Add's a new item to the Menu. If the new item is the first in the menu
	 * its going to be the actual active item.
	 * 
	 * @param displayText
	 * @return new added item position
	 */
	public int addItem(String displayText) {
		MenuItem mi = new MenuItem(displayText);
		mi.addClickListener(this);
		items.add(mi);

		if (actualItem == -1) {
			// mi.setActiveItem();
			mi.setStyleName("VerticalMenuItem-navActive");
			actualItem = 0;
			// TODO: send a event notifing that theres a new active widget
		}

		panel.add(mi);

		return items.size() - 1;
	}

	/*
	 * public void addItemSelectedkListener(ItemSelectedListener listener) {
	 * itemSelectedListener = listener; }
	 * 
	 * public void removeItemSelectedkListener(ItemSelectedListener listener) {
	 * itemSelectedListener = null; }
	 */

	public void onClick(Widget sender) {

		MenuItem mi = (MenuItem) items.get(actualItem);
		mi.setStyleName("VerticalMenuItem-nav");
		// mi.setNonActiveItem();

		actualItem = items.indexOf(sender);
		sender.setStyleName("VerticalMenuItem-navActive");
		// ((MenuItem) sender).setActiveItem();

		// TODO: send a event notifing that theres a new active widget
		itemSelectedListener.itemSelected(items.indexOf(sender));
	}

	public void addItemSelectedListener(MenuItemSelectedListener listener) {
		itemSelectedListener = listener;

	}

	public void removeItemSelectedListener(MenuItemSelectedListener listener) {
		itemSelectedListener = null;

	}

}
