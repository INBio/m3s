/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.importation;

import java.util.List;

import org.inbio.m3s.gwt.client.config.ClientProperties;
import org.inbio.m3s.gwt.client.rpcinterface.ImportRPC;
import org.inbio.m3s.gwt.client.rpcinterface.ImportRPCAsync;
import org.inbio.m3s.gwt.client.widgets.importation.dto.ImportInfo;
import org.inbio.m3s.gwt.client.widgets.login.LoginManager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.RowFormatter;
//import com.thapar.gwt.user.ui.client.widget.RowData;
//import com.thapar.gwt.user.ui.client.widget.SortableTable;

/**
 * @author jgutierrez
 * 
 */
public class ImportManager extends VerticalPanel implements FormHandler {

	// rpc service
	private ImportRPCAsync rpc;

	private HorizontalPanel fileUploadPanel = new HorizontalPanel();

	private VerticalPanel upload;

	// upload stuff: formpanel, verticalpanel, fileUpload,
	private Button uploadButton;

	private FormPanel uploadForm;

	private FileUpload uploadWidget;

	// textual information on the right side of the panel
	private Label uploadHelp;

	private HorizontalPanel middlePanel = new HorizontalPanel();

	private Label tableHelp;

	private Button refresh;

	private FlexTable table;
	//private SortableTable sortableTable;

	// column constants for the table
	private static int FILE_NAME_COLUMN = 0;

	private String FILE_NAME_TEXT = "Nombre del Archivo";

	private static int STATUS_COLUMN = 1;

	private String STATUS_TEXT = "Estado";

	private static int DATE_COLUMN = 2;

	private String DATE_TEXT = "Fecha";

	private static int DOWNLOAD_COLUMN = 3;

	private String DOWNLOAD_TEXT = "Bajar";

	private String usernameImportOwner = null;

	/**
	 * Class Constructor
	 * 
	 */
	public ImportManager() {
		super();
		//initRPC
		rpc = (ImportRPCAsync) GWT.create(ImportRPC.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) rpc;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "importRPC");

		initFileUploadPanel();
		add(fileUploadPanel);

		middlePanel.setSpacing(10);
		tableHelp = new Label("Si desea actualizar los datos de click "
				+ "en el boton etiquetado como 'refrescar'");
		middlePanel.add(tableHelp);
		refresh = new Button("refrescar", new ClickListener() {
			public void onClick(Widget sender) {
				asynRefreshTableData();
			}
		});
		middlePanel.add(refresh);
		add(middlePanel);

		initSortableTable();
		asynRefreshTableData();

	}

	/**
	 * Inits the widget SortableTable, by Parvinder Thapa
	 * 
	 * @link<http://psthapar.googlepages.com/simplesortabletable>
	 * 
	 * 
	 */
	private void initSortableTable() {
		//sortableTable = new SortableTable();
		
		table = new FlexTable();
		
		table.setWidth(500 + "px");
		table.setStyleName("sortableTable");
		table.setBorderWidth(1);
		table.setCellPadding(4);
		table.setCellSpacing(1);

		//sortableTable.setWidth(500 + "px");
		//sortableTable.setStyleName("sortableTable");
		//sortableTable.setBorderWidth(1);
		//sortableTable.setCellPadding(4);
		//sortableTable.setCellSpacing(1);
		
		//header
		table.setHTML(0, FILE_NAME_COLUMN, FILE_NAME_TEXT);
		table.setHTML(0, STATUS_COLUMN, STATUS_TEXT);
		table.setHTML(0, DATE_COLUMN, DATE_TEXT);
		table.setHTML(0, DOWNLOAD_COLUMN, DOWNLOAD_TEXT);
		//sortableTable.addColumnHeader(FILE_NAME_TEXT, FILE_NAME_COLUMN);
		//sortableTable.addColumnHeader(STATUS_TEXT, STATUS_COLUMN);
		//sortableTable.addColumnHeader(DATE_TEXT, DATE_COLUMN);
		//sortableTable.addColumnHeader(DOWNLOAD_TEXT, DOWNLOAD_COLUMN);
		
		
		// COSMETIC PART OF THE WIDGET
		// Set Style Name for the header
		//RowFormatter rowFormatter = sortableTable.getRowFormatter();
		RowFormatter rowFormatter = table.getRowFormatter();
		rowFormatter.setStyleName(0, "tableHeader");

		// Set the Styles for the Data Rows and Columns
		//CellFormatter cellFormatter = sortableTable.getCellFormatter();
		CellFormatter cellFormatter = table.getCellFormatter();
		// Set the styles for the headers
		for (int colIndex = 0; colIndex < 4; colIndex++) {
			cellFormatter.setStyleName(0, colIndex, "headerStyle");
			cellFormatter.setAlignment(0, colIndex,
					HasHorizontalAlignment.ALIGN_CENTER,
					HasVerticalAlignment.ALIGN_MIDDLE);
		}

		for (int rowIndex = 1; rowIndex < 21; rowIndex++) {
			if (rowIndex % 2 == 0) {
				rowFormatter.setStyleName(rowIndex, "customRowStyle");
			} else {
				rowFormatter.setStyleName(rowIndex, "tableRow");
			}
			for (int colIndex = 0; colIndex < 4; colIndex++) {
				cellFormatter.setStyleName(rowIndex, colIndex, "customFont");
				if (colIndex == 1 || colIndex == 3) {
					cellFormatter.setAlignment(rowIndex, colIndex,
							HasHorizontalAlignment.ALIGN_RIGHT,
							HasVerticalAlignment.ALIGN_MIDDLE);
				} else if (colIndex == 0) {
					cellFormatter.setAlignment(rowIndex, colIndex,
							HasHorizontalAlignment.ALIGN_LEFT,
							HasVerticalAlignment.ALIGN_MIDDLE);
				}
				if (colIndex == 2) {
					cellFormatter.setAlignment(rowIndex, colIndex,
							HasHorizontalAlignment.ALIGN_CENTER,
							HasVerticalAlignment.ALIGN_MIDDLE);
				}
			}
		}
		//add(sortableTable);
		add(table);
	}

	/**
	 * Makes the call for the info needed to apply the refreshTableData
	 * 
	 */
	private void asynRefreshTableData() {
		if (LoginManager.isUserLogged()) {

			rpc.getResultTableData(LoginManager.getUserName(),
					new AsyncCallback() {
						public void onFailure(Throwable caught) {
							// TODO a better message
							Window.alert("Error accediendo a base de datos");
							System.out
									.println("cromo el rpc.getResultTableData");
						}

						@SuppressWarnings("unchecked")
						public void onSuccess(Object result) {
							// TODO Auto-generated method stub
							refreshTableData((List<ImportInfo>) result);
						}
					});
		} else {
			Window.alert("Debe iniciar sesion en el sistema.");
		}
	}

	/**
	 * Geths a list of a least 20 elements and inserts them on the table
	 * 
	 * @param tableData
	 *            a List of ImportResultData objects, the size must be 20 or
	 *            less
	 * 
	 */
	private void refreshTableData(List<ImportInfo> tableData) {
		ImportInfo pivote;

		for (int actualRow = 1; actualRow <= tableData.size(); actualRow++) {
			pivote = tableData.get(actualRow - 1);
			//sortableTable.setValue(actualRow, FILE_NAME_COLUMN, pivote.getFileName());
			table.setHTML(actualRow, FILE_NAME_COLUMN, pivote.getFileName());
			//sortableTable.setValue(actualRow, STATUS_COLUMN, pivote.getStatus());
			table.setHTML(actualRow, STATUS_COLUMN, pivote.getStatus());
			//sortableTable.setValue(actualRow, DATE_COLUMN, pivote.getDate());
			table.setHTML(actualRow, DATE_COLUMN, pivote.getDate());
			
			//ComparableHTML bla = new ComparableHTML("<a href= '"
			//		+ pivote.getDownloadLink() + "'>click aca</a>");
			//sortableTable.setValue(actualRow, DOWNLOAD, bla);
			table.setHTML(actualRow, DOWNLOAD_COLUMN, 
					"<a href= '" + pivote.getDownloadLink() + "'>click aca</a>");
			
		}

	}
	

	/**
	 * Inits the fileUpload Widget and the help text besaides the uploadForm
	 * 
	 */
	private void initFileUploadPanel() {
		this.setSpacing(10);

		// upload widget
		uploadForm = new FormPanel();
		uploadForm.setAction(GWT.getModuleBaseURL() + "uploadImportFile");

		uploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
		uploadForm.setMethod(FormPanel.METHOD_POST);

		upload = new VerticalPanel();
		upload.setSpacing(10);
		uploadForm.setWidget(upload);

		// Create a FileUpload widget.
		uploadWidget = new FileUpload();
		uploadWidget.setName("uploadFormElement");
		upload.add(uploadWidget);
		fileUploadPanel.add(uploadForm);

		// Add a 'upload' button.
		uploadButton = new Button("subir", new ClickListener() {
			public void onClick(Widget sender) {
				if (LoginManager.isUserLogged()) {
					usernameImportOwner = LoginManager.getUserName();
					uploadForm.submit();
				} else {
					Window.alert("Debe iniciar sesion en el sistema.");
				}
			}
		});
		upload.add(uploadButton);

		// uploadForm.addFormHandler(this);
		uploadForm.addFormHandler(this);

		// uploadhelp label
		uploadHelp = new Label(
				"Busque el archivo de importación y de click en 'subir', "
						+ "seguidamente el archivo se cargara en el serrvidor y la "
						+ "importación comenzará una vez el archivo se haya subido "
						+ "correctamente");
		fileUploadPanel.add(uploadHelp);
	}

	// upload stuff
	public void onSubmit(FormSubmitEvent event) {
		// TODO Auto-generated method stub
		// This is what happens when the file starts the upload
		// submitingMediaFile();
		// TODO: somthing that shows graphically that the file is going to the
		// server
	}

	// upload stuff
	// This is what happens when the file finish uploading
	public void onSubmitComplete(FormSubmitCompleteEvent event) {
		/*
		 * event.getResults is a String with a special format before the dot is
		 * the result of the uploadWidget, where 0 == ok and 1 == error. After
		 * the dot is the id of the file or the explanation of the error
		 */
		Integer result = null;
		// Window.alert((String) event.getResults());
		try {
			// Window.alert((String) event.getResults().subSequence(0, 1));
			result = new Integer((String) event.getResults().subSequence(0, 1));
		} catch (Exception e) {
			// Window.alert("exepcion parseando resultado del servidor");

		}
		// Window.alert((String) event.getResults().substring(2));
		String tempFileId = (String) event.getResults().substring(2);

		if (result.equals(new Integer(ClientProperties.OK))) { // OK
			rpc.executeImport(usernameImportOwner, tempFileId,
					new AsyncCallback() {

						public void onFailure(Throwable caught) {
							Window.alert("Error invocando la ejeción del archivo de importacion");
							System.out.println("cromo la invocacion de ejecucion del archivo de importacion");
						}

						public void onSuccess(Object result) {
							asynRefreshTableData();
						}

					});
			usernameImportOwner = null;
		} else {
			// TODO a better message
			Window.alert("Error accediendo a base de datos");
			System.out.println("cromo el Submit del archivo");
		}

	}

}
