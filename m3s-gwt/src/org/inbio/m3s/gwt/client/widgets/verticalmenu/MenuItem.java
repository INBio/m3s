package org.inbio.m3s.gwt.client.widgets.verticalmenu;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.SourcesClickEvents;
import com.google.gwt.user.client.ui.Widget;

/*
 * The menu item widget, its suponse to be inserted in a VerticalMenuBar.
 * In the css must be 
 */
public class MenuItem extends Composite implements MouseListener,
		ClickListener, SourcesClickEvents {

	private Label item;

	private ClickListener clickListener;

	// global variable used to maintain the appearance of the widget
	private String previousStyle;

	// Creates the MenuItem...
	public MenuItem(String text) {
		item = new Label(text);
		item.addMouseListener(this);
		item.addClickListener(this);
		item.setStyleName("VerticalMenuItem-nav");

		initWidget(item);
	}


	/**set's the active menu item style (from the css) 
	 * @deprecated
	 * */
	public void setActiveItem() {
		item.setStyleName("VerticalMenuItem-navActive");
	}

	
	/**set's the NON Active menu item style (from the css) 
	 * @deprecated
	 * */
	public void setNonActiveItem() {
		item.setStyleName("VerticalMenuItem-nav");
	}

	// actions:

	public void onClick(Widget sender) {
		clickListener.onClick(this);
	}

	public void onMouseDown(Widget sender, int x, int y) {
		;
	}

	public void onMouseEnter(Widget sender) {
		setPreviousStyle(sender.getStyleName());
		sender.setStyleName("VerticalMenuItem-navMouseOver");
	}

	public void onMouseLeave(Widget sender) {
		sender.setStyleName(getPreviousStyle());
	}

	public void onMouseMove(Widget sender, int x, int y) {
		;
	}

	public void onMouseUp(Widget sender, int x, int y) {
		;
	}

	public void addClickListener(ClickListener listener) {
		clickListener = listener;
	}

	public void removeClickListener(ClickListener listener) {
		clickListener = null;
	}

	private void setPreviousStyle(String previousStyle) {
		this.previousStyle = previousStyle;
	}

	private String getPreviousStyle() {
		return previousStyle;
	}

}
