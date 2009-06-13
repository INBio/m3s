package org.inbio.m3s.gwt.client;

import java.util.List;

import org.inbio.gwt.galleries.client.dto.DisplayInfo;
import org.inbio.gwt.galleries.client.widget.ImageThumbnail;
import org.inbio.m3s.gwt.client.rpcinterface.SearchMediaRPC;
import org.inbio.m3s.gwt.client.rpcinterface.SearchMediaRPCAsync;
import org.inbio.m3s.gwt.client.widgets.info.SystemStatistics;
import org.inbio.m3s.gwt.client.widgets.info.UserStatistics;
import org.inbio.m3s.gwt.client.widgets.login.LoginManager;
import org.inbio.m3s.gwt.client.widgets.login.listener.LoginWidgetListener;
import org.inbio.m3s.gwt.client.widgets.login.ui.LoginWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;

public class Home extends Composite implements LoginWidgetListener, AsyncCallback {

	private HTMLPanel main;

	// private LoginManager login;
	private LoginWidget loginWidget;

	// TODO: all this texts must be loaded from a inteernationalizacion file
	private String title = "P\u00E1gina Principal de la aplicacion";

	private String titleDiv = HTMLPanel.createUniqueId();

	private String rightPanelTitle = "Ultimas im\u00E1genes ingresadas:";

	private String rightPanelTitleDiv = HTMLPanel.createUniqueId();

	// private String leftPanelTitle = "Ultimas im\u00E1genes ingresadas:";

	private String leftPanelContentDiv = HTMLPanel.createUniqueId();

	private UserStatistics userStats;

	private SystemStatistics systemStats;
	
	//rpc service
	private SearchMediaRPCAsync rpc;


	public Home(LoginManager login) {

		// the MainPanel
		main = new HTMLPanel(
				"<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"
						+ "<tr>"
						+ "<td width=\"85%\" align=\"left\" valign=\"top\" bgcolor=\"#4B619A\" class=\"MainPanel\">"

						+ "<!-- Titulo de la Pagina-->"
						+ "<table width=\"100%\"  border=\"0\" cellpadding=\"10\" cellspacing=\"0\">"
						+ "<tr>"
						+ "<td height=\"28\" colspan=\"2\">"
						+ "<div id=\""
						+ titleDiv
						+ "\"></div>"
						+ "</td>"
						+ "</tr>"
						+

						"<!-- Zona de los paneles centrales -->"
						+ "<tr>"

						+ "<!-- Panel Izquierdo -->"
						+ "<td width=\"51%\" height=\"65\" align=\"left\" valign=\"top\">"
						+ "<div class=\"contenido\" id=\"layer1\">"
						+ "<p>"
						+ "<a href=\"#\">Ayuda*</a>"
						+ "<br><a href=\"#\">Pol&iacute;ticas de uso*</a><br>"
						+ "<a href=\"#\">Protocolo de captura de informaci&oacute;n*</a>"
						+ "</p>" + "<div id=\"" + leftPanelContentDiv
						+ "\"></div>" + "</div>" + "</td>" +

						"<!-- Panel derecho -->"
						+ "<td width=\"49%\" align=\"left\" valign=\"top\">"
						+ "<div class=\"contenido\" id=\"layer1\">"
						+ "<div id=\"" + rightPanelTitleDiv + "\"></div>"
						+ "</div>" + "</td>" +

						"</tr> <!-- FIN de Zona de los paneles centrales -->"
						+ "</table>"

						+ "<!-- Cerrando la tabla de todo -->" + "</td>"
						+ "</tr>" + "</table>");

		initWidget(main);

		// Adding widgets to the divs defined in the mainPanel:
		Label theTitle = new Label(title);
		theTitle.setStyleName("MainPanel-Title");
		main.add(theTitle, titleDiv);

		// Left Panel
		if (LoginManager.getUserName() == null) {
			loginWidget = new LoginWidget(login, this);
			main.add(loginWidget, leftPanelContentDiv);
		}

		loadSystemStatistics();

		// Right Panel
		Label theRightPanelTitle = new Label(rightPanelTitle);
		theRightPanelTitle.setStyleName("Home-RightPanelTitle");
		main.add(theRightPanelTitle, rightPanelTitleDiv);

		loadGenericImagesGallery();
	}

	/**
	 * Login Widget is telling: please close me!!!
	 * 
	 * @param reason
	 *            why the widget wants to be closed
	 */
	public void closeWidget(String reason) {
		loginWidget.removeFromParent();
		loadUserStatistics(LoginManager.getUserName());
	}

	/**
	 * Inserts a user statistics widget on the Left Panel
	 * 
	 * @param username
	 */
	private void loadUserStatistics(String username) {
		userStats = new UserStatistics();
		userStats.addInfoRow("Imagenes Ingresadas:", "-");
		userStats.addInfoRow("Videos Ingresad	os:", "-");
		userStats.addInfoRow("Audios Ingresados:", "-");
		main.add(userStats, leftPanelContentDiv);
	}

	/**
	 * 
	 * 
	 */
	public void removeUserStatistics() {
		userStats.removeFromParent();
	}

	/**
	 * Inserts a system statistics widget on the Left Panel
	 * 
	 */
	private void loadSystemStatistics() {
		systemStats = new SystemStatistics();
		main.add(systemStats, leftPanelContentDiv);
		System.out.println("loadSystemStatistics");
	}

	/**
	 * Creates a generic images gallery in the rigth panel
	 * 
	 */
	private void loadGenericImagesGallery() {
		
		//(1) Initialize the RPC service.
		rpc = (SearchMediaRPCAsync) GWT.create(SearchMediaRPC.class);

		//(2) Specify the URL at which our service implementation is running.
		// Note that the target URL must reside on the same domain and port from
		// which the host page was served.
		ServiceDefTarget endpoint = (ServiceDefTarget) rpc;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "searchMediaRPC";
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		
		rpc.getLastPublicMedia(8, this);

	}

	public void onFailure(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onSuccess(Object arg0) {
		
		List<DisplayInfo> fromServer = (List<DisplayInfo>) arg0;
		DisplayInfo dib;
		ImageThumbnail it;
		System.out.println("\nCantiad de resultados>"+fromServer.size());
		
		for(int i= 0; i < fromServer.size(); i++ ){
			dib = (DisplayInfo) fromServer.get(i);
			dib.setUrl(GWT.getModuleBaseURL()+"getImage?size=thumb&id=" + dib.getId());
			it = new ImageThumbnail(dib);
			main.add(it, rightPanelTitleDiv);
			System.out.println("Se insertó la imagen con id: "+dib.getId());

		}
		
		
	}
}
