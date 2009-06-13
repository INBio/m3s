/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.metadata.ui;

import java.util.ArrayList;
import java.util.List;

import org.inbio.m3s.gwt.client.rpcinterface.MetadataRPC;
import org.inbio.m3s.gwt.client.rpcinterface.MetadataRPCAsync;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.TechnicalMetadataTV;

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
// public class TechnicalMetadataPanel extends FlexTable {
public class TechnicalMetadataPanel extends VerticalPanel implements
		AsyncCallback {

	// rpc service
	private MetadataRPCAsync rpc;

	private Integer mediaId = null;

	private String mediaType = "";

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
	 */
	public TechnicalMetadataPanel(Integer language, String mediaTypeName) {
		initRPC();

		mediaType = mediaTypeName;

		// the result of the rpc is the mediaAttributesNames to be display and
		// with that list the panel do its initialization
		rpc.getTechnicalMetadataNames(mediaTypeName, this);

	}

	/**
	 * Sets the table with the given names for the rows. Also creates a new Flex
	 * table this because this method could be called after a creation of a
	 * Flextable based on meditaTypeX but if media Type change the techmetadata
	 * changed to, so that's why the creation of a new Flex table.
	 * 
	 * @param attributeNames
	 */
	private void setAttributeNamesColumn(List attributeNames) {
		main.removeFromParent();
		main = new FlexTable();
		TextBox textBox;
		System.out.println("setting attribute names");
		main.setCellSpacing(5);
		System.out
				.println("total de attribute names> " + attributeNames.size());
		for (int row = 0; row < attributeNames.size(); row++) {
			// creates the textbox where the data should be
			main.setText(row, TEXT, (String) attributeNames.get(row));
			textBox = new TextBox();
			textBox.setWidth(DEFAULT_TEXTBOX_WIDTH);
			main.setWidget(row, WIDGET, textBox);
		}

		this.add(main);

	}

	/**
	 * Sets the table with the given values for the rows. Supose that the method
	 * setAttributeNamesColumn was already called and the row attribute names
	 * all set.
	 * 
	 * @param attributeValues
	 */
	private void setAttibutesValuesColumn(List attributeValues) {
		// sets the values of the rows
		for (int row = 0; row < attributeValues.size(); row++) {

			((TextBox) main.getWidget(row, WIDGET))
					.setText((String) attributeValues.get(row));
		}

	}

	/**
	 * Sets the values in the rows as they come, param in position 0 will be in
	 * row 0...
	 * 
	 * @param tmtv
	 *            a TechnicalMetadataTV object
	 */
	public void setTechnicalMetadataTV(TechnicalMetadataTV tmtv) {
		mediaId = tmtv.getMediaId();
		mediaType = tmtv.getMediaType();

		List names = tmtv.getNames();
		setAttributeNamesColumn(names);
		List values = tmtv.getValues();
		setAttibutesValuesColumn(values);
	}

	/**
	 * Gets the values of the rows as they are, value in row 0 will be in
	 * position 0 of the returnig object...
	 * 
	 * 
	 * @return TechnicalMetadataTV object
	 */
	public TechnicalMetadataTV getTechnicalMetadataTV() {
		TechnicalMetadataTV tmtv = new TechnicalMetadataTV();

		tmtv.setMediaId(mediaId);
		tmtv.setMediaType(mediaType);

		List<String> names = new ArrayList<String>();
		List<String> values = new ArrayList<String>();

		int row = 0;
		try {
			// gets the values of the row
			while (true) {
				names.add( main.getText(row, TEXT));
				values.add(((TextBox) main.getWidget(row, WIDGET)).getText());
				row++;
			}
		} catch (IndexOutOfBoundsException iobe) {
			// this means the while reaches the end of the flex table
			tmtv.setNames(names);
			tmtv.setValues(values);
		}

		return tmtv;

	}

	/**
	 * Looks in the DB for the metadataValues to be shown on the Panel
	 * 
	 * @param mediaFileId
	 * 
	 */
	public void getTechnicalMetadataTV(String mediaFileId) {
		rpc.getTechnicalMetadataTV(mediaFileId, mediaType, new AsyncCallback() {

			public void onFailure(Throwable caught) {
				onFailure(caught);
				System.out
						.println("Exceción obteniendo los metadatos técnicos "
								+ "para el archivo tempId=''");
			}

			public void onSuccess(Object result) {
				TechnicalMetadataTV tmtv = (TechnicalMetadataTV) result;
				setTechnicalMetadataTV(tmtv);
			}
		});
	}

	/***************************************************************************
	 * RPC methods
	 **************************************************************************/
	/**
	 * rpc failure manager
	 * 
	 * @param caught
	 */
	public void onFailure(Throwable caught) {
		try {
			throw caught;
		} catch (InvocationException e) {
			System.out
					.println("Error conectando con el servidor @ technicalMetadataPanel");
			Window.alert("Error conectando con el servidor");
		} catch (IllegalArgumentException e) {
			System.out.println("Uno de los argumentos introducidos no existe");
		} catch (Throwable e) {
			System.out.println("Error en el RPC @ technicalMetadataPanel");
		}

	}

	/**
	 * rpc success manager
	 * 
	 * @param result
	 *            the result of the rpc is expected to be the names list to be
	 *            shown on the left column of the table, that is the input of
	 *            the initPanel method
	 */
	public void onSuccess(Object result) {
		setAttributeNamesColumn((List) result);

	}

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
