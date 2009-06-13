package org.inbio.m3s.gwt.client;

import org.inbio.m3s.gwt.client.config.ClientProperties;
import org.inbio.m3s.gwt.client.rpcinterface.MediaUtilRPC;
import org.inbio.m3s.gwt.client.rpcinterface.MediaUtilRPCAsync;
import org.inbio.m3s.gwt.client.widgets.login.LoginManager;
import org.inbio.m3s.gwt.client.widgets.metadata.MetadataContainer;
import org.inbio.m3s.gwt.client.widgets.metadata.listener.MetadataListener;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * TODO: all this texts must be loaded from a config file
 */
public class InsertMedia extends Composite implements MetadataListener {

	private HTMLPanel main;

	// Title: label, string & Div
	private String title = "Insertar Im\u00E1genes";// this has to be delete

	private Label theTitle;

	private String titleDiv = HTMLPanel.createUniqueId();

	// Metadata Container
	private MetadataContainer metadataPanel;

	private String MetadataPanelDiv = HTMLPanel.createUniqueId();

	// Right side of the panel

	// preview Text label&div
	private String previewText = "Vista previa"; // this has to be delete

	private Label thePreviewText;

	private String previewTextDiv = HTMLPanel.createUniqueId();

	// MultimediaThumb Image&Div
	private Image multimediaThumb;

	private String multimediaThumbDiv = HTMLPanel.createUniqueId();

	// MultimediaPath Label&Div
	private Label theMultimediaPath;

	private String multimediaPathDiv = HTMLPanel.createUniqueId();

	// upload or browse again Button&Div
	private String uploadBrowseAgainDiv = HTMLPanel.createUniqueId();

	private Button cancelAndBrowseAgainButton;

	// upload stuff: formpanel, verticalpanel, fileUpload,
	private Button uploadButton;

	private FormPanel uploadForm;

	private VerticalPanel uploadInternalPanel;

	private FileUpload uploadWidget;

	// save Button&Div
	private String saveButtonDiv = HTMLPanel.createUniqueId();

	private Button saveButton;

	// file identifier
	private String tempFileId;

	private Integer mediaId;

	// rpc
	private MediaUtilRPCAsync rpc;

	private String username;

	/**
	 * Constructor method
	 * 
	 */
	public InsertMedia() {
		// the MainPanel
		main = new HTMLPanel(
				"<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >"
						+ "<tr>"
						+ "<td width=\"85%\" align=\"left\" valign=\"top\" bgcolor=\"#4B619A\" class=\"MainPanel\">"

						+ "<!-- Titulo de la Pagina-->"
						+ "<table width=\"100%\" height=\"424\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\">"
						+ "<tr>"
						+ "<td colspan=\"2\">"
						+ "<div id=\""
						+ titleDiv
						+ "\"></div>"
						+ "</td>"
						+ "</tr>"
						+

						"<!-- Zona del panel principal -->"
						+ "<tr>"
						+ "<!-- Zona Izquierda -->"
						+ "<td width=\"69%\">"
						+ "<div id=\""
						+ MetadataPanelDiv
						+ "\"></div>"
						+ "</td>"

						+ "<!-- Panel derecho -->"
						+ "<td width=\"31%\" align=\"left\" valign=\"top\"> "
						+ "<p>&nbsp;</p>"

						+ "<!-- Parte Superior Derecha-->"
						+ "<table width=\"200\"  border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"bordeCeleste\">"
						+ "<tr>"
						+ "<td height=\"228\"> "
						+ "<p> "
						+ "<div id=\""
						+ previewTextDiv
						+ "\"></div>"
						+ "<div id=\""
						+ multimediaThumbDiv
						+ "\"></div>"
						+ "<div id=\""
						+ multimediaPathDiv
						+ "\"></div>"
						+ "</p>"
						+ "<div id=\""
						+ uploadBrowseAgainDiv
						+ "\"></div>"

						+ "</td> </tr>"
						+ "</table> <p>&nbsp;</p>"

						+ "<!-- Parte Inferior Derecha -->"
						+ "<table width=\"200\"  border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"bordeCeleste\">"
						+ "<tr> <td align=\"center\"> " + "<div id=\""
						+ saveButtonDiv + "\"></div></p>"
						+ "</td></tr></table>"

						+ "<!-- Panel derecho <FIN> -->" + "</td>"
						+ "<!-- Cerrando la tabla de todo -->" + "</tr>"

						+ "<!-- Cerrando la tabla de todo -->" + "</td>"
						+ "</tr>" + "</table>" + "</td> </tr> </table>");

		initWidget(main);

		setUsername(LoginManager.getUserName());

		initRPC();

		initContents();
	}

	/**
	 * sets things on the GUI
	 * 
	 */
	private void initContents() {

		// Sets the tittle of the page
		theTitle = new Label(title);
		theTitle.setStyleName("MainPanel-Title");
		main.add(theTitle, titleDiv);

		initUploadWidget();

		// inits the save buton
		saveButton = new Button("Salvar Registro", new ClickListener() {
			public void onClick(Widget sender) {
				metadataPanel.saveMetadata();
			}
		});
		main.add(saveButton, saveButtonDiv);
		saveButton.setEnabled(false);
	}

	/**
	 * 
	 * Inits the upload images servlet service and the graphical elements
	 */
	private void initUploadWidget() {

		uploadForm = new FormPanel();
		uploadForm.setAction(GWT.getModuleBaseURL() + "uploadFile");

		uploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
		uploadForm.setMethod(FormPanel.METHOD_POST);

		uploadInternalPanel = new VerticalPanel();
		uploadForm.setWidget(uploadInternalPanel);

		// Create a FileUpload widget.
		uploadWidget = new FileUpload();
		uploadWidget.setName("uploadFormElement");
		uploadInternalPanel.add(uploadWidget);
		main.add(uploadForm, multimediaPathDiv);

		// Add a 'upload' button.
		uploadButton = new Button("Subir", new ClickListener() {
			public void onClick(Widget sender) {
				uploadForm.submit();
			}
		});
		main.add(uploadButton, uploadBrowseAgainDiv);

		// uploadForm.addFormHandler(this);
		uploadForm.addFormHandler(new FormHandler() {

			public void onSubmit(FormSubmitEvent event) {
				// This is what happens when the file starts the upload
				submitingMediaFile();
			}

			public void onSubmitComplete(FormSubmitCompleteEvent event) {
				// This is what happens when the file finish uploading
				mediaFileSubmited(event);
			}
		});
	}

	/**
	 * Handles the start of the submit, the media its starting its way to the
	 * server
	 * 
	 */
	private void submitingMediaFile() {

		// disabled the upload button
		uploadButton.setEnabled(false);
		main.remove(uploadButton);

		// multimedia Thumbnail preview
		thePreviewText = new Label(previewText);
		thePreviewText.setStyleName("thumbPreviewText");
		main.add(thePreviewText, previewTextDiv);

		// add an loading image
		multimediaThumb = new Image(GWT.getModuleBaseURL()
				+ "/images/loading.gif");
		main.add(multimediaThumb, multimediaThumbDiv);

		// add the path of the multimedia
		// theMultimediaPath = new Label("Subiendo: " +
		// uploadFile.getFilename());
		theMultimediaPath = new Label("Subiendo...");
		// theMultimediaPath = new Label("Subiendo: algo");
		theMultimediaPath.setStyleName("thumbPreviewText");
		main.add(theMultimediaPath, multimediaPathDiv);

		// inits the metadata tab panel
		initMetadataPanel();

	}

	/**
	 * Handles what happends when the submit of the file ends
	 * 
	 * @param event
	 */
	private void mediaFileSubmited(FormSubmitCompleteEvent event) {

		// removes the upload form widget
		main.remove(uploadForm);

		// enabled the save button
		saveButton.setEnabled(true);

		// Add a cancel button (Browse again)
		cancelAndBrowseAgainButton = new Button("Cancelar Proceso",
				new ClickListener() {
					public void onClick(Widget sender) {
						restartComposite();
					}
				});

		main.add(cancelAndBrowseAgainButton, uploadBrowseAgainDiv);

		/*
		 * event.getResults is a String with a special format before the dot is
		 * the result of the uploadWidget, where 0 == ok and 1 == error. After
		 * the dot is the id of the file or the explanation of the error
		 */
		int result = Integer.parseInt((String) event.getResults().subSequence(
				0, 1));
		tempFileId = event.getResults().substring(2);

		if (result == 0) { // OK

			rpc.createTempThumbnail(tempFileId, thumbnailCallback);

			// adds the path of the multimedia
			main.remove(theMultimediaPath);
			String multimediaPath = uploadWidget.getFilename();
			String visibleMultimediaPath = "";
			int lineLength = 0;
			for (int i = 0; i < multimediaPath.length(); i = i + 30) {
				if (multimediaPath.length() > i + 30)
					lineLength = 30;
				else
					lineLength = multimediaPath.length() - i;

				visibleMultimediaPath = visibleMultimediaPath
						.concat(multimediaPath.substring(i, i + lineLength)
								+ " ");
			}

			theMultimediaPath = new Label(visibleMultimediaPath);
			theMultimediaPath.setStyleName("thumbPreviewText");
			main.add(theMultimediaPath, multimediaPathDiv);

			// Inits the tech metadata tab
			metadataPanel.initTechMetadataTab(
					ClientProperties.DEFAULT_LANGUAGE, false);

			metadataPanel.setTechnicalMetadataInfo(tempFileId);

		} else { // ERROR
			Window.alert(tempFileId); // the explanation of the error
			saveButton.setEnabled(false);
		}
	}

	/**
	 * Fired by the metadataContainer when the save method its invoked and the
	 * result goes bad
	 */
	public void errorSavingMetadata(Throwable caught) {
		Window.alert("Error guardando el medio");
		// the explanation of the error
	}

	/**
	 * Fired by the metadataContainer when the save method its invoked and every
	 * thing goes nice
	 * 
	 * FIXME only its working with jpg images
	 * 
	 * TODO: Debe retornarse un popup donde diga el resultado de la operacion en
	 * este se debe indicar el codigo con el cual se guardara la imagen, el
	 * volumen y el archivo donde queda almacenada.
	 */
	public void metadataSaved(Integer theMediaId) {
		// tempFileId, DBFileName, mediaId
		mediaId = theMediaId;
		String DBFileName = mediaId.toString() + ".jpg";
		
		rpc.organizeAndCleanFiles(tempFileId, DBFileName, mediaId,
				new AsyncCallback() {
					public void onFailure(Throwable caught) {
						Window.alert("problemas guardando la información");
						restartComposite();
					}

					public void onSuccess(Object result) {
						Window.alert("información del medio[" + mediaId
								+ "]guardada con exito");
						restartComposite();
					}
				});
		// TODO: return to the home or stay in the same window???
	}

	/**
	 * Muestra e inicializa el MetadataContainer con sus respectivos paneles de
	 * metadatos
	 * 
	 * @param mediaId
	 */
	private void initMetadataPanel() {
		metadataPanel = new MetadataContainer(this);
		metadataPanel.setWidth("95%");
		metadataPanel.setHeight("500px");
		main.add(metadataPanel, MetadataPanelDiv);
		metadataPanel.initGeneralMetadata(ClientProperties.DEFAULT_LANGUAGE,
				true);
		metadataPanel.initUsesAndCopyrightsTab(
				ClientProperties.DEFAULT_LANGUAGE, false);
	}

	/**
	 * Init the RPC that all the class use
	 * 
	 */

	private void initRPC() {
		// (1) Initialize the RPC service.
		rpc = (MediaUtilRPCAsync) GWT.create(MediaUtilRPC.class);

		// (2) Specify the URL at which our service implementation is running.
		// Note that the target URL must reside on the same domain and port from
		// which the host page was served.
		ServiceDefTarget endpoint = (ServiceDefTarget) rpc;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "mediaUtilRPC";
		endpoint.setServiceEntryPoint(moduleRelativeURL);
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
	 * Use to restart all the composite, when the user cancels the process or
	 * when the upload is finish.
	 * 
	 */
	private void restartComposite() {
		metadataPanel.removeFromParent();
		multimediaThumb.removeFromParent();
		saveButton.setEnabled(false);
		uploadButton.removeFromParent();
		cancelAndBrowseAgainButton.removeFromParent();
		theMultimediaPath.removeFromParent();
		thePreviewText.removeFromParent();

		initUploadWidget();

	}

	/**
	 * AsyncCallback's for the RPC createTempThumbnail method
	 */
	AsyncCallback thumbnailCallback = new AsyncCallback() {
		public void onSuccess(Object fromServer) {
			// removes the loading image
			main.remove(multimediaThumb);

			// sets the thumb image of the file
			String thumbWebAddress = (String) fromServer;
			multimediaThumb = new Image(thumbWebAddress);
			main.add(multimediaThumb, multimediaThumbDiv);
		}

		public void onFailure(Throwable caught) {
			Window.alert("Problemas obteniendo la "
					+ "imagen en tamaño estampilla");
		}
	};

}
