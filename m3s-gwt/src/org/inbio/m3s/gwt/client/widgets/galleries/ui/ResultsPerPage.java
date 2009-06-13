/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.galleries.ui;

import org.inbio.m3s.gwt.client.widgets.galleries.listener.ResultsPerPageSelectedListener;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author jgutierrez
 * 
 */
public class ResultsPerPage extends Composite implements ClickListener {

	private ResultsPerPageSelectedListener resultsPerPageSelectedListener;

	private VerticalPanel main;

	private Label text;

	/**
	 * Creates a Windget the shows the posible number of results per page, the
	 * constructor needs the posible values as a String and a Listener that will
	 * receive the events of change beetwen values
	 * 
	 * @param value1
	 * @param value2
	 * @param value3
	 * @param resultsPerPageSelectedListener
	 */
	public ResultsPerPage(String value1, String value2, String value3,
			ResultsPerPageSelectedListener resultsPerPageSelectedListener) {

		main = new VerticalPanel();
		main.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);

		text = new Label("Resultados por página:");
		text.addStyleName("resultsPerPage-Text");
		main.add(text);

		// radio buttons
		RadioButton rb1 = new RadioButton("myRadioGroup", value1);
		rb1.addClickListener(this);
		RadioButton rb2 = new RadioButton("myRadioGroup", value2);
		rb2.addClickListener(this);
		RadioButton rb3 = new RadioButton("myRadioGroup", value3);
		rb3.addClickListener(this);

		// Check 'baz' by default.
		rb1.setChecked(true);

		// Add them to the root panel.
		FlowPanel panel = new FlowPanel();
		panel.add(rb1);
		panel.add(rb2);
		panel.add(rb3);
		main.add(panel);

		this.resultsPerPageSelectedListener = resultsPerPageSelectedListener;

		initWidget(main);

	}

	/**
	 * 
	 */
	public void onClick(Widget sender) {
		RadioButton selected = (RadioButton) sender;
		resultsPerPageSelectedListener.resultsPerPageSelected(Integer
				.parseInt(selected.getText()));
	}

}
