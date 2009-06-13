/**
 * 
 */
package org.inbio.m3s.gwt.client;

import org.inbio.gwt.galleries.client.dto.DisplayInfo;
import org.inbio.gwt.galleries.client.dto.DisplayType;
import org.inbio.gwt.galleries.client.widget.ImageThumbnail;
import org.inbio.m3s.gwt.client.config.ClientProperties;
import org.inbio.m3s.gwt.client.widgets.login.LoginManager;
import org.inbio.m3s.gwt.client.widgets.metadata.MetadataContainer;
import org.inbio.m3s.gwt.client.widgets.metadata.listener.MetadataListener;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author jgutierrez
 * 
 */
public class EditMediaInfo extends Composite implements ClickListener,
		MetadataListener {

	private HTMLPanel main;

	// Title: label, string & Div
	private String title = "Editar Infomación de un Multimedio";

	private Label theTitle;

	private String titleDiv = HTMLPanel.createUniqueId();

	// preview media
	private ImageThumbnail imagesThumbnail;

	private String previewDiv = HTMLPanel.createUniqueId();

	// upperPanel widget> con el mensaje de si conoce el id de la imagen y
	// eso...
	private String upperPanelDiv = HTMLPanel.createUniqueId();

	private HorizontalPanel findMedia;

	private Label findMediaText;

	private TextBox textBox;

	private Button find;

	private HorizontalPanel editingMedia;

	private Label mediaIdLabel;

	private Button saveInfo;

	private String upperInfoDiv = HTMLPanel.createUniqueId();

	private Label infoText;

	private Button editOther;

	// Metadata Container
	private MetadataContainer metadataPanel;

	private String centralPanelDiv = HTMLPanel.createUniqueId();

	private String username;

	/**
	 * Constructor method
	 * 
	 * Instancia la clases desde un comienzo
	 * 
	 */
	public EditMediaInfo() {
		// the MainPanel
		main = new HTMLPanel(
				"<table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"
						+ "<!--Upper side (row) of the window -->"
						+ "<tr>"
						+ "<!-- left side coponents -->"
						+ "<td width=\"75%\" align=\"left\" valign=\"top\""
						+ "<!-- Zona Superior de la Pagina...-->"
						+ "<table width=\"100%\"  border=\"0\" cellpadding=\"10\" cellspacing=\"0\">"

						+ "<!-- Titulo de la Pagina-->"
						+ "<tr valign=\"top\" align=\"left\">"
						+ "<td width=\"100%\">"
						+ "<div id=\""
						+ titleDiv
						+ "\"></div>"
						+ "</td>"
						+ "</tr>"

						+ "<!-- panel donde se indica el medio que se esta editando-->"
						+ "<tr>"
						+ "<td align=\"left\" valign=\"top\">"
						+ "<div id=\""
						+ upperPanelDiv
						+ "\"></div>"
						+ "</td>"
						+ "</tr>"

						+ "<!-- panel donde se indica el medio que se esta editando-->"
						+ "<tr>"
						+ "<td align=\"left\" valign=\"top\">"
						+ "<div id=\""
						+ upperInfoDiv
						+ "\"></div>"
						+ "</td>"
						+ "</tr>"

						+ "</table>"
						+ "</td>"

						+ "<!-- Rigth side coponents -->"
						+ "<!-- Preview del medio cuya info se esta editando-->"
						+ "<td width=\"25%\" align=\"center\" valign=\"top\" cellpadding=\"10\" >"
						+ "<div id=\""
						+ previewDiv
						+ "\"></div>"
						+ "</td>"

						+ "</tr> <!-- Upper Row-->"

						+ "<tr>"
						+ "<!--Bottom side (row) of the window -->"
						+ "<!-- Zona del panel principal -->"
						+ "<table width=\"100%\"  border=\"0\" cellpadding=\"10\" cellspacing=\"0\" >"
						+ "<tr>"
						+ "<td>"
						+ "<!-- instead of the following code should be a div that is a the end of this lines -->"
						+ "<div id=\"" + centralPanelDiv + "\"></div>"

						+ "</tr>"

						+ "</table>" + "</td>" + "</tr>" + "</table>");

		initWidget(main);

		setUsername(LoginManager.getUserName());

		// Sets the tittle of the page
		theTitle = new Label(title);
		theTitle.setStyleName("MainPanel-Title");
		main.add(theTitle, titleDiv);

		initContents();
	}

	/**
	 * Constructor que de una vez carga la informacion del medio a editar
	 * 
	 * @param mediaId
	 * 
	 */
	public EditMediaInfo(Integer mediaId) {
		new EditMediaInfo();
		InitSaveUI(mediaId);
		initMediaPreview(mediaId);
		initMetadataPanel(mediaId);
	}

	/**
	 * Inicializa la clase
	 * 
	 */
	public void initContents() {

		findMedia = new HorizontalPanel();
		findMedia.setSpacing(10);

		findMediaText = new Label("Número de Indentificación del Multimedio ");
		findMedia.add(findMediaText);
		textBox = new TextBox();
		findMedia.add(textBox);
		find = new Button("Buscar", new ClickListener() {
			public void onClick(Widget sender) {
				String textualValue = textBox.getText();
				Integer value = new Integer(textualValue);
				InitSaveUI(value);
				initMediaPreview(value);
				initMetadataPanel(value);
			}

		});
		findMedia.add(find);

		main.add(findMedia, upperPanelDiv);

		infoText = new Label(
				"Si no conoce el número de indentificación puede hacer"
						+ " una búsqueda y en la vista de 'estampillas' dar click en el ícono"
						+ " de editar medio");
		infoText.setWidth("300px");
		main.add(infoText, upperInfoDiv);

	}

	/**
	 * Inits the save information of the media widgets
	 * 
	 * @param mediaId
	 * 
	 */
	public void InitSaveUI(Integer mediaId) {
		findMedia.removeFromParent();
		infoText.removeFromParent();

		editingMedia = new HorizontalPanel();
		editingMedia.setSpacing(10);

		mediaIdLabel = new Label(
				"Editando el multimedio con Identificador Número: '"
						+ mediaId.toString() + "'.");
		editingMedia.add(mediaIdLabel);

		saveInfo = new Button("Guardar Cambios", this);
		editingMedia.add(saveInfo);
		// main.add(editingMedia, upperPanelDiv);

		editOther = new Button("Editar otro/a", this);
		editingMedia.add(editOther);
		main.add(editingMedia, upperPanelDiv);
	}

	/**
	 * Muestra el preview de arriba a la derecha en la pantalla
	 * 
	 * @param mediaId
	 */
	public void initMediaPreview(Integer mediaId) {
		// imagesThumbnail = new ImagesThumbnail(GWT.getModuleBaseURL()
		// + "/images/12344567890_170.jpg", mediaId.toString(), "Dos",
		// "Tres", "Cuatro");
		DisplayInfo di= new DisplayInfo(mediaId.toString(), DisplayType.EDIT, 
				GWT.getModuleBaseURL()+"getImage?size=thumb&id=" + mediaId.toString(),
				"en edicion", "en edicion","en edicion","en edicion","en edicion","en edicion",
				"en edicion","en edicion","en edicion");
				
		imagesThumbnail = new ImageThumbnail(di);
		
		//TODO: eventually could be nice to add the information after being loaded using RPC 
		
		//imagesThumbnail = new ImagesThumbnailOld(mediaId);
		main.add(imagesThumbnail, previewDiv);
	}

	/**
	 * Muestra e inicializa el MetadataContainer con sus respectivos paneles de
	 * metadatos
	 * 
	 * @param mediaId
	 */
	public void initMetadataPanel(Integer mediaId) {
		metadataPanel = new MetadataContainer(this);
		metadataPanel.setWidth("100%");
		metadataPanel.setHeight("500px");
		main.add(metadataPanel, centralPanelDiv);
		metadataPanel.initGeneralMetadata(ClientProperties.DEFAULT_LANGUAGE,
				true);
		metadataPanel.initUsesAndCopyrightsTab(
				ClientProperties.DEFAULT_LANGUAGE, false);
		metadataPanel.initTechMetadataTab(ClientProperties.DEFAULT_LANGUAGE,
				ClientProperties.DEFAULT_MEDIA_TYPE, false);

		metadataPanel.setMetadata(mediaId);
	}

	/**
	 * Clicklistener methods
	 */
	public void onClick(Widget sender) {

		if (sender.equals(saveInfo)) {
			metadataPanel.saveMetadata();
		} else if (sender.equals(editOther)) {
			reloadComposite();
		}

	}

	public void errorSavingMetadata(Throwable caught) {
		Window.alert("ocurrio un error guardarndo la "
				+ "información del multimedio.");
		reloadComposite();
	}

	public void metadataSaved(Integer mediaId) {
		Window.alert("información del medio [" + mediaId
				+ "] guardada con exito");
		reloadComposite();
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

	/**
	 * Reload the compsite for a new edit
	 */
	private void reloadComposite() {
		metadataPanel.removeFromParent();
		imagesThumbnail.removeFromParent();
		editingMedia.removeFromParent();
		initContents();
		// Window.alert("todo");
	}
}
