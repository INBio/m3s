/**
 * 
 */
package org.inbio.m3s.gwt.client;

import org.inbio.m3s.gwt.client.widgets.importation.ImportManager;
import org.inbio.m3s.gwt.client.widgets.login.LoginManager;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * @author jgutierrez
 * 
 */
public class ImportMedia extends Composite {

	private HTMLPanel main;

	// Title: label, string & Div
	private String title = "Importar Grupo de Im\u00E1genes";// this has to

	// be delete

	private Label theTitle;

	private String titleDiv = HTMLPanel.createUniqueId();

	private ImportManager importPanel;

	// Tabs: Container, tabpanel and div

	private String tabPanelDiv = HTMLPanel.createUniqueId();

	private String username;

	// Right side of the panel

	/**
	 * Constructor method
	 * 
	 */
	public ImportMedia() {
		// the MainPanel
		main = new HTMLPanel(
				"<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"
						+ "<tr>"
						+ "<td width=\"85%\" align=\"left\" valign=\"top\" bgcolor=\"#4B619A\" class=\"MainPanel\">"
						+ "<tr>"
						+ "<td height=\"18\">"

						+ "<!-- Zona Superior de la Pagina...-->"
						+ "<table width=\"100%\"  border=\"0\" cellpadding=\"10\" cellspacing=\"0\">"

						+ "<!-- Titulo de la Pagina-->"
						+ "<tr>"
						+ "<td>"
						+ "<div id=\""
						+ titleDiv
						+ "\"></div>"
						+ "</td>"
						+ "</tr>"
						+ "</table>"

						+ "<!-- Zona del panel principal -->"
						+ "<table width=\"100%\"  border=\"0\" cellpadding=\"10\" cellspacing=\"0\" >"
						+ "<tr>" + "<td  align=\"center\" valign=\"top\">"
						+ "<div id=\"" + tabPanelDiv + "\"></div>" + "</td>"
						+ "</tr>" + "</table>"

						+ "<!-- Fin-->" + "</td>" + "</tr>" + "</td>" + "</tr>"
						+ "</table>");

		initWidget(main);

		setUsername(LoginManager.getUserName());

		// initImportImagesRPC();

		initContents();
	}

	public void initContents() {

		// Sets the tittle of the page
		theTitle = new Label(title);
		theTitle.setStyleName("MainPanel-Title");
		main.add(theTitle, titleDiv);

		importPanel = new ImportManager();
		main.add(importPanel, tabPanelDiv);

	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

}
