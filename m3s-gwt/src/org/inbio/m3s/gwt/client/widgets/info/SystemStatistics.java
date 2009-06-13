package org.inbio.m3s.gwt.client.widgets.info;

import org.inbio.m3s.gwt.client.rpcinterface.InfoRPC;
import org.inbio.m3s.gwt.client.rpcinterface.InfoRPCAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SystemStatistics extends Composite {
	private VerticalPanel panel;

	private Label widgetTitle;

	// rpc service
	private InfoRPCAsync rpc;

	public SystemStatistics() {
		initRPC();
		widgetTitle = new Label("Estadisticas del Sistema:");
		widgetTitle.setStyleName("infoWidgets-Title");

		panel = new VerticalPanel();
		panel.setStyleName("infoWidgets-Panel");

		panel.add(widgetTitle);

		initWidget(panel);

		rpc.countMultimedia(new AsyncCallback() {
			public void onFailure(Throwable caught) {
				System.out.println("Error en el RPC countMultimedia en la "
						+ "clase SystemStatistics");
			}

			public void onSuccess(Object result) {
				addInfoRow("Total de multimedios en B.D:", ((Integer) result)
						.toString());
			}
		});

		rpc.countDigitalPhotos(new AsyncCallback() {
			public void onFailure(Throwable caught) {
				System.out.println("Error en el RPC countMultimedia en la "
						+ "clase SystemStatistics");
			}

			public void onSuccess(Object result) {
				addInfoRow("Fotografias Digitales:", ((Integer) result)
						.toString());
			}
		});

		rpc.countVideos(new AsyncCallback() {
			public void onFailure(Throwable caught) {
				System.out.println("Error en el RPC countVideo en la "
						+ "clase SystemStatistics");
			}

			public void onSuccess(Object result) {
				addInfoRow("Videos:", ((Integer) result)
						.toString());
			}
		});
		
		// addInfoRow("Recursos Sin taxonomia:", "23");
		// addInfoRow("Recursos Sin descripcion:", "3");
		// addInfoRow("Imagenes de Escaner:", "3236");
	}

	/**
	 * Inserts a information row in the widget, the text is show normal and the
	 * value in bold.
	 * 
	 * @param text
	 * @param value
	 */
	private void addInfoRow(String text, String value) {
		Label theText = new Label(text);
		theText.addStyleName("infoWidget-RowText");
		Label theValue = new Label(value);
		theValue.addStyleName("infoWidget-RowValue");
		HorizontalPanel row = new HorizontalPanel();

		row.add(theText);
		row.add(theValue);

		panel.add(row);

	}

	/**
	 * Init the RPC that all the class use
	 * 
	 */
	private void initRPC() {
		// (1) Initialize the RPC service.
		rpc = (InfoRPCAsync) GWT.create(InfoRPC.class);

		// (2) Specify the URL at which our service implementation is running.
		// Note that the target URL must reside on the same domain and port from
		// which the host page was served.
		ServiceDefTarget endpoint = (ServiceDefTarget) rpc;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "infoRPC";
		endpoint.setServiceEntryPoint(moduleRelativeURL);
	}

}
/**
 * Information widget with: -Total of multimedias -Multimedia with taxonomy:
 * -Multimedia with description -Estadisticas segun Tipo de multimedio -Etc
 */
