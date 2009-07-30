/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.metadata.ui;

import java.util.ArrayList;
import java.util.List;

import org.inbio.m3s.gwt.client.config.ClientProperties;
import org.inbio.m3s.gwt.client.dto.UsePolicyGWTDTO;
import org.inbio.m3s.gwt.client.dto.metadata.MediaUseGWTDTO;
import org.inbio.m3s.gwt.client.dto.metadata.UsesAndCopyrightsGWTDTO;
import org.inbio.m3s.gwt.client.dto.util.PersonGWTDTO;
import org.inbio.m3s.gwt.client.rpcinterface.MetadataRPC;
import org.inbio.m3s.gwt.client.rpcinterface.MetadataRPCAsync;
import org.inbio.m3s.gwt.client.widgets.metadata.MediaOwnerSelector;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.MediaOwnerConstants;

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

	//default values
	private String defaultAuthorKey = "13798";
	private int defaultOwnerType = MediaOwnerConstants.OWNER_INSTITUTION;
	private String defaultOwnerKey = "1";
	private String defaultUsePolicyKey = "2";
	
	UsesAndCopyrightsGWTDTO uacGWTDTO = null;
	

	/**
	 * Constructor
	 * 
	 */
	public UsesAndCopyrightsPanel() {
		initRPC();
		this.uacGWTDTO = null;
		initTable();
	}
	
	/**
	 * Constructor
	 * 
	 */
	public UsesAndCopyrightsPanel(UsesAndCopyrightsGWTDTO uacGWTDTO) {
		initRPC();
		this.uacGWTDTO = uacGWTDTO;
		initTable();
	}
	

	/***************************************************************************
	 * Methods
	 **************************************************************************/

	/**
	 * Constructor he --private UsesAndCopyrigths usesAndCopyrigths;lper
	 */
	private void initTable() {
		System.out.println("Init table... inicia @ UACMetadataPanel");

		main.setCellSpacing(5);

		// author
		main.setText(AUTHOR, TEXT, "Autor:");
		main.setWidget(AUTHOR, WIDGET, new ListBox());

		rpc.getPeople(new AsyncCallback<List<PersonGWTDTO>>() {
			public void onFailure(Throwable caught) {
				RPCFailureManager(caught);
			}

			public void onSuccess(List<PersonGWTDTO> pList) {
				ListBox listBox = (ListBox) main.getWidget(AUTHOR, WIDGET);
				listBox.clear();
				
				for(PersonGWTDTO pLite : pList)
					listBox.addItem(pLite.getName(), pLite.getPersonKey());
				
				if(uacGWTDTO != null){
					System.out.println("Esta seteando como default person Id: "+uacGWTDTO.getAuthorKey());
					setListBoxValue(uacGWTDTO.getAuthorKey(), AUTHOR);
				} else {
					System.out.println("Esta seteando como default person Id: "+defaultAuthorKey);
					setListBoxValue(defaultAuthorKey, AUTHOR);
				}
			}
		});

		// owner
		main.setText(OWNER, TEXT, "Propietario:");
		//System.out.println("Esta seteando propietario con UAC > "+uacGWTDTO.get);
		if(uacGWTDTO != null){
			// set owner value
			if(uacGWTDTO.getInstitutionOwnerKey() != null)
				main.setWidget(OWNER, WIDGET, new MediaOwnerSelector(MediaOwnerConstants.OWNER_INSTITUTION, uacGWTDTO.getInstitutionOwnerKey()));
			else
				main.setWidget(OWNER, WIDGET, new MediaOwnerSelector(MediaOwnerConstants.OWNER_PERSON, uacGWTDTO.getPersonOwnerKey()));
		} else{
			main.setWidget(OWNER, WIDGET, new MediaOwnerSelector(defaultOwnerType,defaultOwnerKey));
		}

		// use policy
		main.setText(USE_POLICY, TEXT, "Política de Uso:");
		main.setWidget(USE_POLICY, WIDGET, new ListBox());
		rpc.getUsePolicies(new AsyncCallback<List<UsePolicyGWTDTO>>() {
			public void onFailure(Throwable caught) {
				RPCFailureManager(caught);
			}

			public void onSuccess(List<UsePolicyGWTDTO> upGWTDTOList) {
				ListBox listBox = (ListBox) main.getWidget(USE_POLICY, WIDGET);
				listBox.clear();

				for(UsePolicyGWTDTO upGWTDTO : upGWTDTOList)
					listBox.addItem(upGWTDTO.getName(), upGWTDTO.getUsePolicyKey());
				
				if(uacGWTDTO != null){
					System.out.println("Esta seteando como default use policy Id: "+uacGWTDTO.getUsePolicyKey());
					setListBoxValue(uacGWTDTO.getUsePolicyKey(), USE_POLICY);
				} else{
					setListBoxValue(defaultUsePolicyKey, USE_POLICY);
				}
		}});

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
	public void setUACMetadata(UsesAndCopyrightsGWTDTO uacGWTDTO) {
		
		this.uacGWTDTO = uacGWTDTO;
		setDefaultUsePolicyKey(uacGWTDTO.getUsePolicyKey());
		
		this.initTable();

		// set media Uses
		//((TextBox) main.getWidget(MULTIMEDIA_USE, WIDGET)).setText(uac.getMultimediaUses());

		// sets the is backup value
		((CheckBox) main.getWidget(IS_BACKUP, WIDGET)).setChecked(uacGWTDTO.getIsBackup());

		// set is Public
		((CheckBox) main.getWidget(IS_PUBLIC, WIDGET)).setChecked(uacGWTDTO.getIsPublic());
		
	}


	/**
	 * 
	 * @return
	 */
	public UsesAndCopyrightsGWTDTO getUsesAndCopyrightsGWTDTO() {
		ListBox temporal;
		int selectedIndex;

		if(uacGWTDTO==null){
			uacGWTDTO = new UsesAndCopyrightsGWTDTO();
			uacGWTDTO.setMediaKey(null);
		}
		
		// set author
		temporal = (ListBox) main.getWidget(AUTHOR, WIDGET);
		selectedIndex = temporal.getSelectedIndex();
		//Window.alert("temporal.getValue(selectedIndex):"+temporal.getValue(selectedIndex));
		uacGWTDTO.setAuthorKey(String.valueOf(temporal.getValue(selectedIndex)));
		//Window.alert("getAuthorKey:"+uacGWTDTO.getAuthorKey());

		// set owerValue
		MediaOwnerSelector mos = (MediaOwnerSelector) main.getWidget(OWNER,WIDGET);
		if(mos.getOwnerType() == MediaOwnerConstants.OWNER_INSTITUTION){
			uacGWTDTO.setPersonOwnerKey(null);
			uacGWTDTO.setInstitutionOwnerKey(mos.getOwnerKey());
		} else {
			uacGWTDTO.setPersonOwnerKey(mos.getOwnerKey());
			uacGWTDTO.setInstitutionOwnerKey(null);
		}
		//Window.alert("getPersonOwnerKey:"+uacGWTDTO.getPersonOwnerKey()
		//		+"\n getInstitutionOwnerKey:"+uacGWTDTO.getInstitutionOwnerKey());
		

		// use policy
		temporal = (ListBox) main.getWidget(USE_POLICY, WIDGET);
		selectedIndex = temporal.getSelectedIndex();
		//uacTV.setUsePolicy(temporal.getItemText(selectedIndex));
		uacGWTDTO.setUsePolicyKey(String.valueOf(temporal.getValue(selectedIndex)));
		//Window.alert("getUsePolicyKey:"+uacGWTDTO.getUsePolicyKey());

		// mediaUses
		//uacTV.setMultimediaUses(((TextBox) main.getWidget(MULTIMEDIA_USE,
		//		WIDGET)).getText());
		List<MediaUseGWTDTO> muGWTDTOList = new ArrayList<MediaUseGWTDTO>();
		uacGWTDTO.setMediaUsesList(muGWTDTOList);

		// backup value
		if (((CheckBox) main.getWidget(IS_BACKUP, WIDGET)).isChecked())
			uacGWTDTO.setIsBackup(true);
		else
			uacGWTDTO.setIsBackup(false);
		//Window.alert("getIsBackup:"+uacGWTDTO.getIsBackup());

		// set is Public
		if (((CheckBox) main.getWidget(IS_PUBLIC, WIDGET)).isChecked())
			uacGWTDTO.setIsPublic(true);
		else
			uacGWTDTO.setIsPublic(false);
		//Window.alert("getIsPublic:"+uacGWTDTO.getIsPublic());

		//Window.alert(uacGWTDTO.toString());
		
		return uacGWTDTO;
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

	/**
	 * @param defaultUsePolicyKey the defaultUsePolicyKey to set
	 */
	public void setDefaultUsePolicyKey(String defaultUsePolicyKey) {
		this.defaultUsePolicyKey = defaultUsePolicyKey;
	}

	/**
	 * @return the defaultUsePolicyKey
	 */
	public String getDefaultUsePolicyKey() {
		return defaultUsePolicyKey;
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
