/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.galleries.ui;

import org.inbio.m3s.gwt.client.widgets.galleries.listener.LayoutSelectedListener;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author jgutierrez
 * 
 */
public class LayoutSelector extends Composite implements ClickListener {

	public static int THUMP_LAYOUT = 0;

	public static int BIG_LAYOUT = 1;

	private LayoutSelectedListener layoutSelectedListener;

	private HorizontalPanel main;

	private Image thumbLayout;

	private Image bigLayout;

	public LayoutSelector(LayoutSelectedListener layoutSelectedListener) {
		main = new HorizontalPanel();
		main.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		main.setSpacing(10);

		thumbLayout = new Image("images/thumbLayout.gif");
		thumbLayout.addClickListener(this);
		thumbLayout.addStyleName("layoutImages");

		bigLayout = new Image("images/bigLayout.gif");
		bigLayout.addClickListener(this);
		bigLayout.addStyleName("layoutImages");

		main.add(thumbLayout);
		main.add(bigLayout);

		this.layoutSelectedListener = layoutSelectedListener;

		initWidget(main);

	}

	/**
	 * on Click its called a layoutSelectedListener in order to attend this
	 * event on the class that is using the widget
	 */
	public void onClick(Widget sender) {
		// TODO Auto-generated method stub
		if (sender.equals(bigLayout)) {
			layoutSelectedListener.layoutSelected(LayoutSelector.BIG_LAYOUT);
		} else if (sender.equals(thumbLayout)) {
			layoutSelectedListener.layoutSelected(LayoutSelector.THUMP_LAYOUT);
		}

	}

}
