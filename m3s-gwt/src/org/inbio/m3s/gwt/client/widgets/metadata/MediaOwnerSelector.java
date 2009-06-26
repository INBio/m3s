/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.metadata;

import java.util.List;

import org.inbio.m3s.gwt.client.dto.util.InstitutionLiteGWTDTO;
import org.inbio.m3s.gwt.client.dto.util.PersonGWTDTO;
import org.inbio.m3s.gwt.client.rpcinterface.MetadataRPC;
import org.inbio.m3s.gwt.client.rpcinterface.MetadataRPCAsync;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.MediaOwnerConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author jgutierrez
 * 
 */
public class MediaOwnerSelector extends Composite {

	// rpc service
	private MetadataRPCAsync rpc;

	private VerticalPanel main = new VerticalPanel();

	private HorizontalPanel radioButtonPanel = new HorizontalPanel();

	private RadioButton institutionRB;

	private RadioButton personRB;

	private ListBox listBox = new ListBox();

	// variables
	// defaults -- ..:wired:..
	private int selectedOwnerType = MediaOwnerConstants.OWNER_INSTITUTION;

	private String ownerValue = "INB - Instituto Nacional de Biodiversidad";


	/**
	 * Constructor
	 * 
	 * @param ownerType
	 *            class constant
	 */
	public MediaOwnerSelector(int ownerType) {
		selectedOwnerType = ownerType;
		this.initMediaOwnerSelector();
	}

	/**
	 * Constructor
	 * 
	 * @param ownerType
	 *            class constant
	 * @param ownerValue
	 *            String value to be shown
	 */
	public MediaOwnerSelector(int ownerType, String ownerValue) {

		selectedOwnerType = ownerType;
		this.ownerValue = ownerValue;

		this.initMediaOwnerSelector();
	}

	/**
	 * Core of the constructors
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void initMediaOwnerSelector() {
		initRPC();

		// owner institution radio button
		institutionRB = new RadioButton("associationRadioGroup", "Institución");
		institutionRB.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				selectedOwnerType = MediaOwnerConstants.OWNER_INSTITUTION;
				rpc.getInstitutions(new AsyncCallback() {
					public void onFailure(Throwable caught) {
						RPCFailureManager(caught);
					}

					public void onSuccess(Object result) {
						setInstitutionsOnListBox(result);
					}
				});
			}
		});

		// owner person radio button
		personRB = new RadioButton("associationRadioGroup", "Persona");
		personRB.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				selectedOwnerType = MediaOwnerConstants.OWNER_PERSON;
				rpc.getPeople(new AsyncCallback() {
					public void onFailure(Throwable caught) {
						RPCFailureManager(caught);
					}

					public void onSuccess(Object result) {
						setPeopleOnListBox(result);
					}
				});
			}
		});

		// inits the widget
		setValue(selectedOwnerType, ownerValue);

		// radiobutton to the top and in the bottom
		radioButtonPanel.setSpacing(15);
		radioButtonPanel
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		radioButtonPanel.add(institutionRB);
		radioButtonPanel.add(personRB);

		main.setSpacing(10);
		main.add(radioButtonPanel);
		main.add(listBox);

		initWidget(main);
	}

	/**
	 * 
	 * 
	 * @return the owner type code that is a constant of the class
	 */
	public int getOwnerType() {
		return selectedOwnerType;
	}

	/**
	 * 
	 * @return thse selected textual owner value
	 */
	public String getOwner() {
		int selectedIndex = listBox.getSelectedIndex();
		return listBox.getItemText(selectedIndex);
	}

	/**
	 * Sets new values to the widget
	 * 
	 * @param ownerType
	 * @param owner
	 */
	@SuppressWarnings("unchecked")
	public void setValue(int ownerType, String owner) {
		selectedOwnerType = ownerType;
		ownerValue = owner;

		// sets the default functionality
		if (selectedOwnerType == MediaOwnerConstants.OWNER_INSTITUTION) {
			institutionRB.setChecked(true);
			rpc.getInstitutions(new AsyncCallback() {
				public void onFailure(Throwable caught) {
					RPCFailureManager(caught);
				}

				public void onSuccess(Object result) {
					setInstitutionsOnListBox(result);
				}
			});

		} else if (selectedOwnerType == MediaOwnerConstants.OWNER_PERSON) {
			personRB.setChecked(true);
			rpc.getPeople(new AsyncCallback() {
				public void onFailure(Throwable caught) {
					RPCFailureManager(caught);
				}

				public void onSuccess(Object result) {
					setPeopleOnListBox(result);
				}
			});
		}

		setWidgetValue();
	}

	/**
	 * Sets the widgets apeareance using the selectedOwnerType and value that is
	 * predefined in the class. This methos does not insert elements in the
	 * listBox, just set selected the corresponding value
	 * 
	 */
	private void setWidgetValue() {

		if (selectedOwnerType == MediaOwnerConstants.OWNER_INSTITUTION) {
			institutionRB.setChecked(true);
			personRB.setChecked(false);
		} else if (selectedOwnerType == MediaOwnerConstants.OWNER_PERSON) {
			institutionRB.setChecked(false);
			personRB.setChecked(true);
		}
		
		if (ownerValue.compareTo("") != 0) {
			String pivoteValue;
			for (int i = 0; i < listBox.getItemCount(); i++) {
				pivoteValue = (String) listBox.getValue(i);
				if (pivoteValue.compareTo(ownerValue) == 0) {
					listBox.setSelectedIndex(i);
					break;
				}
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
	 * Called by the the method that receives the asyncCallback result
	 */
	@SuppressWarnings("unchecked")
	private void setInstitutionsOnListBox(Object result){
		
		List<InstitutionLiteGWTDTO> iList = (List<InstitutionLiteGWTDTO>) result; 
		listBox.clear();
		
		for(InstitutionLiteGWTDTO iLite : iList)
			listBox.addItem(iLite.getName());

		setWidgetValue();
	}
	
	@SuppressWarnings("unchecked")
	private void setPeopleOnListBox(Object result){
		
		List<PersonGWTDTO> pList = (List<PersonGWTDTO>) result; 
		listBox.clear();
		
		for(PersonGWTDTO pLite : pList)
			listBox.addItem(pLite.getName(), pLite.getPersonKey());

		setWidgetValue();
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

}
