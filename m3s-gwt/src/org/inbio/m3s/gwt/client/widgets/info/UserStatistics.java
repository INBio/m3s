package org.inbio.m3s.gwt.client.widgets.info;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UserStatistics extends Composite {
	private VerticalPanel panel;
	//username or user_DB_Id
	private Label widgetTitle;

	public UserStatistics() {
		widgetTitle = new Label("Estadisticas del Usuario:");
		widgetTitle.setStyleName("infoWidgets-Title");
		
		panel = new VerticalPanel();
		panel.setStyleName("infoWidgets-Panel");
		
		panel.add(widgetTitle);
		
		initWidget(panel);
	}

	/**
	 * Inserts a information row in the widget, the text is show normal and the
	 * value in bold.
	 * 
	 * @param text
	 * @param value
	 */
	public void addInfoRow(String text, String value) {
		Label theText = new Label(text);
		theText.addStyleName("infoWidget-RowText");
		Label theValue = new Label(value);
		theValue.addStyleName("infoWidget-RowValue");
		HorizontalPanel row = new HorizontalPanel();
		
		
		row.add(theText);
		row.add(theValue);
		
		panel.add(row);
		
	}

}

/**
 * User Information widget with: -Total de multimedios insertados por el usuario
 * -Etc
 */
