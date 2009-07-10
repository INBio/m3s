/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.metadata.ui;

import org.inbio.m3s.gwt.client.dto.metadata.TechnicalMetadataGWTDTO;
import org.inbio.m3s.gwt.client.dto.metadata.TechnicalMetadataItemGWTDTO;
import org.inbio.m3s.gwt.client.rpcinterface.MetadataRPC;
import org.inbio.m3s.gwt.client.rpcinterface.MetadataRPCAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author jgutierrez
 * 
 */
public class TechnicalMetadataPanel extends VerticalPanel {

	// rpc service
	private MetadataRPCAsync rpc;

	//private String mediaKey = null;

	//private String mediaTypeKey = "";

	private TechnicalMetadataGWTDTO tmGWTDTO; 
	
	private FlexTable main = new FlexTable();

	private static String DEFAULT_TEXTBOX_WIDTH = "250px";

	/**
	 * Class constructor, creates an empty table with all the text in the
	 * selected language
	 * 
	 * @param language
	 *            of the info to be display -ignored
	 * @param mediaTypeName
	 *            the atributes of that media type are the ones that are going
	 *            to be shown
	public TechnicalMetadataPanel(Integer language, String mediaTypeName) {
		initRPC();

		//mediaTypeKey = mediaTypeName;

		// the result of the rpc is the mediaAttributesNames to be display and
		// with that list the panel do its initialization
		rpc.getTechnicalMetadataNames(mediaTypeName, new AsyncCallback<TechnicalMetadataGWTDTO>(){

			public void onFailure(Throwable caught) {
				try {
					throw caught;
				} catch (InvocationException e) {
					System.out.println("Error conectando con el servidor @ technicalMetadataPanel");
					Window.alert("Error conectando con el servidor");
				} catch (IllegalArgumentException e) {
					System.out.println("Uno de los argumentos introducidos no existe");
				} catch (Throwable e) {
					System.out.println("Error en el RPC @ technicalMetadataPanel");
				}
				
			}

			public void onSuccess(TechnicalMetadataGWTDTO techMetadataResult) {
				tmGWTDTO = techMetadataResult;
				setTechMetadataGWTDTO(tmGWTDTO);				
			}
			
		});

	}
		 */
	
	/**
	 * 
	 * @param tmGWTDTO
	 */
	public TechnicalMetadataPanel(TechnicalMetadataGWTDTO tmGWTDTO){
		setTechMetadataGWTDTO(tmGWTDTO);
	}


	/**
	 * Sets the values in the rows as they come, param in position 0 will be in
	 * row 0...
	 * 
	 * @param tmtv
	 *            a TechnicalMetadataTV object
	 */
	public void setTechMetadataGWTDTO(TechnicalMetadataGWTDTO tmGWTDTO) {
		this.tmGWTDTO = tmGWTDTO;		

		main.removeFromParent();
		main = new FlexTable();
		TextBox textBox;
		System.out.println("setting attribute names");
		main.setCellSpacing(5);
		System.out.println("total de atributos> " + tmGWTDTO.getItems().size());
		
		int row = 0;
		for(TechnicalMetadataItemGWTDTO tmiGWTDTO : tmGWTDTO.getItems()){
		// creates the textbox where the data should be
			main.setText(row, TEXT, tmiGWTDTO.getMediaAttributeName());
			textBox = new TextBox();
			textBox.setText(tmiGWTDTO.getValue());
			textBox.setWidth(DEFAULT_TEXTBOX_WIDTH);
			main.setWidget(row, WIDGET, textBox);
			row++;
		}

		this.add(main);
		
	}

	/**
	 * Gets the values of the rows as they are, value in row 0 will be in
	 * position 0 of the returnig object...
	 * 
	 * 
	 * @return TechnicalMetadataTV object
	 */
	public TechnicalMetadataGWTDTO getTechMetadataGWTDTO() {
		

		int row = 0;
		for(TechnicalMetadataItemGWTDTO tmiGWTDTO : tmGWTDTO.getItems()){
			tmiGWTDTO.setValue(((TextBox) main.getWidget(row, WIDGET)).getText());
			row++;
		}

		return tmGWTDTO;

	}

	/**
	 * Looks in the DB for the metadataValues to be shown on the Panel
	 * 
	 * @param mediaFileId
	 * 
	 */
	public void getTechnicalMetadataTV(String mediaFileId) {
		rpc.getTechnicalMetadataTV(mediaFileId, this.tmGWTDTO.getMediaTypeKey(), new AsyncCallback<TechnicalMetadataGWTDTO>() {

			public void onFailure(Throwable caught) {
				onFailure(caught);
				System.out.println("Exceción obteniendo los metadatos técnicos "
								+ "para el archivo tempId=''");
			}

			public void onSuccess(TechnicalMetadataGWTDTO tmGWTDTO) {
				setTechMetadataGWTDTO(tmGWTDTO);
			}
		});
	}

	/***************************************************************************
	 * RPC methods
	 **************************************************************************/

	/**
	 * Init the RPC that all the class use
	 * 
	 */
	private void initRPC() {
		// (1) Initialize the RPC service.
		rpc = (MetadataRPCAsync) GWT.create(MetadataRPC.class);

		// (2) Specify the URL at which our service implementation is running.
		// Note that the target URL must reside on the same domain and port from
		// which the host page was served.
		ServiceDefTarget endpoint = (ServiceDefTarget) rpc;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "metadataRPC";
		endpoint.setServiceEntryPoint(moduleRelativeURL);
	}

	/***************************************************************************
	 * Constants
	 **************************************************************************/
	// CONSTANTS
	// COLUMN TYPE INDEX
	private static int TEXT = 0;

	private static int WIDGET = 1;

}
