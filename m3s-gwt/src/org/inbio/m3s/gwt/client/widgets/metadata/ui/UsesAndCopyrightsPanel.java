/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.metadata.ui;

import java.util.List;

import org.inbio.m3s.gwt.client.config.ClientProperties;
import org.inbio.m3s.gwt.client.dto.util.PersonGWTDTO;
import org.inbio.m3s.gwt.client.rpcinterface.MetadataRPC;
import org.inbio.m3s.gwt.client.rpcinterface.MetadataRPCAsync;
import org.inbio.m3s.gwt.client.widgets.metadata.MediaOwnerSelector;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.MediaOwnerConstants;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.UsesAndCopyrightsTV;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author jgutierrez
 * 
 */
public class UsesAndCopyrightsPanel extends VerticalPanel {

	// rpc service
	private MetadataRPCAsync rpc;

	private FlexTable main = new FlexTable();

	// this two variables are for be used in the edit mode
	private Integer mediaId = null;

	//private String defaultAuthorName = "";
	//"Cristian Granados"

	private String defaultUsePolicy = "Uso institucional";
	
	UsesAndCopyrightsTV editUAC = null;
	

	/**
	 * Constructor
	 * 
	 * @param language
	 *            this param its ignored!!!! always its using the
	 *            default_language constant\
	 */
	public UsesAndCopyrightsPanel(Integer language) {
		initRPC();
		
		initTable();
	}
	
	/**
	 * Constructor
	 * 
	 * @param language
	 *            this param its ignored!!!! always its using the
	 *            default_language constant\
	 */
	public UsesAndCopyrightsPanel(Integer language, UsesAndCopyrightsTV uactv) {
		initRPC();
		this.editUAC = uactv;
		initTable();
	}
	

	/***************************************************************************
	 * Methods
	 **************************************************************************/

	/**
	 * Constructor he --private UsesAndCopyrigths usesAndCopyrigths;lper
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void initTable() {
		System.out.println("Init table... inicia @ UACMetadataPanel");

		main.setCellSpacing(5);

		// author
		main.setText(AUTHOR, TEXT, "Autor:");
		main.setWidget(AUTHOR, WIDGET, new ListBox());

		rpc.getPeople(new AsyncCallback() {
			public void onFailure(Throwable caught) {
				RPCFailureManager(caught);
			}

			public void onSuccess(Object result) {
				ListBox listBox = (ListBox) main.getWidget(AUTHOR, WIDGET);
				listBox.clear();
				
				List<PersonGWTDTO> pList = (List<PersonGWTDTO>) result; 
				
				for(PersonGWTDTO pLite : pList)
					//listBox.addItem(pLite.getName(), pLite.getPersonKey());
					listBox.addItem(pLite.getName());
				
				if(editUAC != null){
					//Window.alert("Esta seteando como default person> "+editUAC.getAuthor().toString());
					System.out.println("Esta seteando como default person> "+editUAC.getAuthor().toString());
					setListBoxValue(editUAC.getAuthor().getName(), AUTHOR);
					//listBox.setSelectedIndex(new Integer(editUAC.getAuthor().getPersonKey()).intValue());
				}
			}
		});

		// owner
		main.setText(OWNER, TEXT, "Propietario:");
		System.out.println("Esta seteando propietario con UAC > "+editUAC);
		if(editUAC != null){
			// set owner value
			main.setWidget(OWNER, WIDGET, new MediaOwnerSelector(editUAC.getOwnerType(), editUAC.getOwner()));
		} else{
		main.setWidget(OWNER, WIDGET, new MediaOwnerSelector(
				MediaOwnerConstants.OWNER_INSTITUTION));
		}

		// use policy
		main.setText(USE_POLICY, TEXT, "Política de Uso:");
		main.setWidget(USE_POLICY, WIDGET, new ListBox());
		rpc.getUsePolicies(new AsyncCallback() {
			public void onFailure(Throwable caught) {
				RPCFailureManager(caught);
			}

			public void onSuccess(Object result) {
			// this.setListBoxValues((List) result, USE_POLICY);
				ListBox listBox = (ListBox) main.getWidget(USE_POLICY, WIDGET);
				for (int i = 0; i < ((List) result).size(); i++) {
					listBox.addItem((String) ((List) result).get(i));
				}
				if (defaultUsePolicy.compareTo("") != 0) {
					setListBoxValue(defaultUsePolicy, USE_POLICY);
				}
			}
		});

		// multimediaUses
		main.setText(MULTIMEDIA_USE, TEXT, "Usos del Multimedio:");
		TextBox multimediaUseTB = new TextBox();
		multimediaUseTB.setWidth(ClientProperties.DEFAULT_TEXTBOX_WIDTH);
		multimediaUseTB.setEnabled(false);
		main.setWidget(MULTIMEDIA_USE, WIDGET, multimediaUseTB);

		// this.setIsBackupRow("Solo Respaldo:");
		CheckBox isBackupCB = new CheckBox();
		CheckBox isPublicCB = new CheckBox();

		main.setText(IS_BACKUP, TEXT, "Solo Respaldo:");
		isBackupCB.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				// if isBackup is == yes
				if (((CheckBox) sender).isChecked()) {
					((CheckBox) main.getWidget(IS_PUBLIC, WIDGET))
							.setChecked(false);
					((CheckBox) main.getWidget(IS_PUBLIC, WIDGET))
							.setEnabled(false);
				} else {
					((CheckBox) main.getWidget(IS_PUBLIC, WIDGET))
							.setEnabled(true);
				}
			}
		});
		main.setWidget(IS_BACKUP, WIDGET, isBackupCB);

		// ispublic
		main.setText(IS_PUBLIC, TEXT, "Uso Público:");
		isPublicCB.setChecked(true);
		main.setWidget(IS_PUBLIC, WIDGET, isPublicCB);

		this.add(main);
		System.out.println("Init table... cool @ UACMetadataPanel");
	}

	/**
	 * Shows the uses and copyrigts metadata given as parameter
	 * 
	 * @param usesAndCopyrigths
	 */
	public void setUACMetadata(UsesAndCopyrightsTV uac) {
		
		editUAC = uac;
		mediaId = uac.getMediaId();
		//defaultAuthorName = uac.getAuthor().getName();
		defaultUsePolicy = uac.getUsePolicy();
		
		this.initTable();

		// set media Uses
		((TextBox) main.getWidget(MULTIMEDIA_USE, WIDGET)).setText(uac
				.getMultimediaUses());

		// sets the is backup value
		((CheckBox) main.getWidget(IS_BACKUP, WIDGET)).setChecked(uac
				.getIsBackup());

		// set is Public
		((CheckBox) main.getWidget(IS_PUBLIC, WIDGET)).setChecked(uac
				.getIsPublic());
		
/*
		// set author
		this.defaultAuthor = uac.getAuthor();
		rpc.getPeople(new AsyncCallback() {
			public void onFailure(Throwable caught) {
				RPCFailureManager(GET_PEOPLE, caught);
			}

			public void onSuccess(Object result) {
				RPCSuccessManager(GET_PEOPLE, result);
			}
		});
		//this.setListBoxValue(uac.getAuthor(), AUTHOR);

		// set owner value
		((MediaOwnerSelector) main.getWidget(OWNER, WIDGET)).setValue(uac
				.getOwnerType(), uac.getOwner());

		// set use policy
		this.setListBoxValue(uac.getUsePolicy(), USE_POLICY);

		// set media Uses
		((TextBox) main.getWidget(MULTIMEDIA_USE, WIDGET)).setText(uac
				.getMultimediaUses());

		// sets the is backup value
		((CheckBox) main.getWidget(IS_BACKUP, WIDGET)).setChecked(uac
				.getIsBackup());

		// set is Public
		((CheckBox) main.getWidget(IS_PUBLIC, WIDGET)).setChecked(uac
				.getIsPublic());
				*/
	}


	/**
	 * 
	 * @return
	 */
	public UsesAndCopyrightsTV getUsesAndCopyrightsTV() {
		UsesAndCopyrightsTV uacTV = new UsesAndCopyrightsTV();
		ListBox temporal;
		int selectedIndex;

		uacTV.setMediaId(mediaId);

		// set author
		temporal = (ListBox) main.getWidget(AUTHOR, WIDGET);
		selectedIndex = temporal.getSelectedIndex();
		PersonGWTDTO plDTOGWT = new PersonGWTDTO(String.valueOf(selectedIndex),temporal.getItemText(selectedIndex));
		//uacTV.setAuthor(temporal.getItemText(selectedIndex));
		uacTV.setAuthor(plDTOGWT);

		// set owerValue
		// TODO
		MediaOwnerSelector mos = (MediaOwnerSelector) main.getWidget(OWNER,
				WIDGET);
		uacTV.setOwnerType(mos.getOwnerType());
		uacTV.setOwner(mos.getOwner());

		// use policy
		temporal = (ListBox) main.getWidget(USE_POLICY, WIDGET);
		selectedIndex = temporal.getSelectedIndex();
		uacTV.setUsePolicy(temporal.getItemText(selectedIndex));

		// mediaUses
		uacTV.setMultimediaUses(((TextBox) main.getWidget(MULTIMEDIA_USE,
				WIDGET)).getText());

		// backup value
		if (((CheckBox) main.getWidget(IS_BACKUP, WIDGET)).isChecked())
			uacTV.setIsBackup(true);
		else
			uacTV.setIsBackup(false);

		// set is Public
		if (((CheckBox) main.getWidget(IS_PUBLIC, WIDGET)).isChecked())
			uacTV.setIsPublic(true);
		else
			uacTV.setIsPublic(false);

		return uacTV;
	}

	/**
	 * 
	 * @param value
	 *            String to be set as default
	 * @param row
	 *            the row number where the ListBox is
	 */
	private void setListBoxValue(String value, int row) {
		ListBox listBox = (ListBox) main.getWidget(row, WIDGET);
		String pivoteValue;

		for (int i = 0; i < listBox.getItemCount(); i++) {
			pivoteValue = (String) listBox.getValue(i);
			if (pivoteValue.equals(value)) {
				listBox.setSelectedIndex(i);
				//Window.alert("setListBoxValue["+value+"] con el Index>"+i);
				break;
			}
		}

	}

	/***************************************************************************
	 * RPC methods
	 **************************************************************************/
	/**
	 * Called by the the method that receives the asyncCallback result
	 * 
	 * @param caught
	 */
	private void RPCFailureManager(Throwable caught) {
		try {
			throw caught;
		} catch (InvocationException e) {
			System.out
					.println("Error conectando con el servidor @ usesAndCopyrigtsPanel");
			Window.alert("Error conectando con el servidor");
		} catch (IllegalArgumentException e) {
			System.out.println("Uno de los argumentos introducidos no existe");
		} catch (Throwable e) {
			System.out.println("Error en el RPC @ usesAndCopyrigtsPanel");
		}

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
	 * ConstantsHibernate: select media0_.author_person_id as col_0_0_, media
	 **************************************************************************/
	// ROWS OF INFORMATION
	public static int TABLE_TITLE = 0;

	public static int AUTHOR = 1;

	public static int OWNER = 2;

	public static int USE_POLICY = 3;

	public static int MULTIMEDIA_USE = 4;

	public static int IS_BACKUP = 5;

	public static int IS_PUBLIC = 6;

	public static int OWNER_TYPE = 7;

	public static int OWNER_PERSON = 7;

	public static int OWNER_INSTITUTION = 8;

	// COLUMN TYPE INDEX -> internal use
	public static int TEXT = 0;

	public static int WIDGET = 1;

}
