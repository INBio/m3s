/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.metadata;

import org.inbio.m3s.gwt.client.dto.metadata.TechnicalMetadataGWTDTO;
import org.inbio.m3s.gwt.client.rpcinterface.MetadataRPC;
import org.inbio.m3s.gwt.client.rpcinterface.MetadataRPCAsync;
import org.inbio.m3s.gwt.client.widgets.login.LoginManager;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.GeneralMetadataTV;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.UsesAndCopyrightsTV;
import org.inbio.m3s.gwt.client.widgets.metadata.listener.CategoryAndTypeListener;
import org.inbio.m3s.gwt.client.widgets.metadata.listener.MetadataListener;
import org.inbio.m3s.gwt.client.widgets.metadata.ui.GeneralMetadataPanel;
import org.inbio.m3s.gwt.client.widgets.metadata.ui.TechnicalMetadataPanel;
import org.inbio.m3s.gwt.client.widgets.metadata.ui.UsesAndCopyrightsPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * The main purpose of these class is to be the manager of all the metadata of
 * the medias, this widget has to be used (and reused) in the insert/edit mode
 * of visualization for every media (images, videos, audios)
 * 
 * @author jgutierrez
 * 
 */
public class MetadataContainer extends VerticalPanel implements
		CategoryAndTypeListener {

	// rpc service
	private MetadataRPCAsync rpc;

	private MetadataListener listener;

	// container of the diferent types of information
	private TabPanel tabPanel;

	// private GeneralMetadataTable generalMetadata;
	private GeneralMetadataPanel gmPanel;

	// private UsesAndCopyrightsTable usesAndCopyrights;
	private UsesAndCopyrightsPanel uacPanel;

	private TechnicalMetadataPanel tmPanel;


	/**
	 * Class Constructor
	 * 
	 */
	public MetadataContainer(MetadataListener metadataListener) {
		super();
		// init RPC
		rpc = (MetadataRPCAsync) GWT.create(MetadataRPC.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) rpc;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "metadataRPC");
		
		listener = metadataListener;
		tabPanel = new TabPanel();
		add(tabPanel);
	}
	

	/**
	 * Sends all the metadata to DB to be persist
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void saveMetadata() {
		System.out.println("Guardando metadatos - INICIO");
		// try {
		GeneralMetadataTV gmtv = gmPanel.getGeneralMetadataTV();

		UsesAndCopyrightsTV uactv = uacPanel.getUsesAndCopyrightsTV();

		TechnicalMetadataGWTDTO tmtv = tmPanel.getTechMetadataGWTDTO();
		// } catch (Exception e) {
		// System.out
		// .println("Ocurrio un error inesperado, por favor intente luego.");
		// }

		System.out.println("hasta aca todo bien");

		rpc.saveMetadata(gmtv, uactv, tmtv, LoginManager.getUserName(),
				new AsyncCallback() {
					public void onFailure(Throwable caught) {
						// RPCFailureManager(SAVE_METADATA, caught);
						Window.alert("Error: " + caught.getMessage());
					}

					public void onSuccess(Object result) {
						// RPCSuccessManager(SAVE_METADATA, result);
						/**
						 * TODO: Debe retornarse un popup donde diga el
						 * resultado de la operacion en este se debe indicar el
						 * codigo con el cual se guardara la imagen, el volumen
						 * y el archivo donde queda almacenada.
						 */
						listener.metadataSaved((Integer) result);
					}

				});

		// TODO a graphical representation, so the user could know what its
		// going on... and when the asyncCalback returns the graphical
		// representation disapears or informs about the result of the method

		System.out.println("Guardando metadatos - FIN");
	}


	/**
	 * Inits the General Metadata tab
	 * 
	 * @param language
	 * @param mainTab
	 *            if true will set this tab as the
	 */
	public void initGeneralMetadata(Integer language, boolean mainTab) {
		gmPanel = new GeneralMetadataPanel(language, this);
		tabPanel.add(gmPanel, "Información General");
		if (mainTab) {
			tabPanel.selectTab(tabPanel.getWidgetIndex(gmPanel));
		}
	}
	
	@SuppressWarnings("unchecked")
	public void initGeneralMetadata(final Integer language, final boolean mainTab, Integer mediaId) {
		// loading generalMetadata
		System.out.println("initGeneralMetadata - INICIO");
		rpc.getGeneralMetadataTV(mediaId, new AsyncCallback() {

			public void onFailure(Throwable caught) {
				RPCFailureManager(caught);
			}

			public void onSuccess(Object result) {
				initGeneralMetadata(language, mainTab);
				gmPanel.setGeneralMetadataTV((GeneralMetadataTV) result);
				System.out.println("initGeneralMetadata - RPC on Success");
			}

		});		
	}

	/**
	 * Inits the Uses and Copyrights tab
	 * 
	 * @param language
	 * @param mainTab
	 *            if true will set this tab as the
	 */
	public void initUsesAndCopyrightsTab(Integer language, boolean mainTab, UsesAndCopyrightsTV uacTV) {
		uacPanel = new UsesAndCopyrightsPanel(language, uacTV);
		tabPanel.add(uacPanel, "Usos y Derechos de Uso");
		if (mainTab) {
			tabPanel.selectTab(tabPanel.getWidgetIndex(uacPanel));
		}
	}
	
	public void initUsesAndCopyrightsTab(Integer language, boolean mainTab) {
		uacPanel = new UsesAndCopyrightsPanel(language, null);
		tabPanel.add(uacPanel, "Usos y Derechos de Uso");
		if (mainTab) {
			tabPanel.selectTab(tabPanel.getWidgetIndex(uacPanel));
		}
	}
	
	/**
	 * 
	 * @param language
	 * @param mainTab
	 * @param mediaId
	 */
	@SuppressWarnings("unchecked")
	public void initUsesAndCopyrightsTab(final Integer language, final boolean mainTab, Integer mediaId) {
		System.out.println("initUsesAndCopyrightsTab - INICIO");
		rpc.getUsesAndCopyrigthsMetadataTV(mediaId, new AsyncCallback() {

			public void onFailure(Throwable caught) {
				RPCFailureManager(caught);
			}

			public void onSuccess(Object result) {
				initUsesAndCopyrightsTab(language, mainTab, (UsesAndCopyrightsTV) result);
				
				System.out.println("initUsesAndCopyrightsTab - RPC on Success");
			}
		});
	}

	/**
	 * Inits the TechnicalMetadata tab, the General Metadata Tab has to be
	 * already up and runnig or will be an error here
	 * 
	 * @param language
	 * @param mediaTypeName
	 * @param mainTab
	 *            if true will set this tab as the
	 */
	public void initTechMetadataTab(Integer language, boolean mainTab) {
		String mediaTypeName = gmPanel.getCurrentMediaTypeText();
		tmPanel = new TechnicalMetadataPanel(language, mediaTypeName);
		tabPanel.add(tmPanel, "Información Técnica");
		if (mainTab) {
			tabPanel.selectTab(tabPanel.getWidgetIndex(tmPanel));
		}
	}	

	/**
	 * Inits the TechnicalMetadata tab
	 * 
	 * @param language
	 * @param mediaTypeName
	 * @param mainTab
	 *            if true will set this tab as the
	 */
	public void initTechMetadataTab(Integer language, String mediaTypeName, boolean mainTab) {
		tmPanel = new TechnicalMetadataPanel(language, mediaTypeName);
		tabPanel.add(tmPanel, "Información Técnica");
		if (mainTab) {
			tabPanel.selectTab(tabPanel.getWidgetIndex(tmPanel));
		}
	}
	
	@SuppressWarnings("unchecked")
	public void initTechMetadataTab(final Integer language, final boolean mainTab, Integer mediaId) {
		System.out.println("initTechMetadataTab - INICIO");
		// The technical Metadata its loaded when the General Metadata
			// arrives, because needs the mediaType value
			rpc.getTechnicalMetadataTV(mediaId, new AsyncCallback() {

				public void onFailure(Throwable caught) {
					RPCFailureManager(caught);
				}

				public void onSuccess(Object result) {
					TechnicalMetadataGWTDTO tmGWTDTO = (TechnicalMetadataGWTDTO) result;
					initTechMetadataTab(language, tmGWTDTO.getMediaTypeKey(), mainTab);
					tmPanel.setTechMetadataGWTDTO(tmGWTDTO);
					
					System.out.println("initTechMetadataTab - RPC on Success");
				}
			});
		}

	/***************************************************************************
	 * RPC methods
	 **************************************************************************/
	/**
	 * Called by the the method that receives the asyncCallback result
	 * 
	 * @param caught
	 * 
	 */
	private void RPCFailureManager(Throwable caught) {
			try {
				throw caught;
			} catch (InvocationException e) {
				System.out
						.println("Error conectando con el servidor @ MetadataContainer");
				Window.alert("Error conectando con el servidor");
			} catch (IllegalArgumentException e) {
				System.out
						.println("Uno de los argumentos introducidos no existe");
			} catch (Throwable e) {
				System.out.println("Error en el RPC @ MetadataContainer");
			}
	}


	/**
	 * Throwed by the general Metadata Panel when the media type selected
	 * changes so the techmetadata to be shown has to changed to
	 * 
	 * @param type
	 */
	public void onTypeChange(String type) {
		// TODO Auto-generated method stub
		// TODO: make the tech metadata panel show the changes
		// String mediaTypeName = gmPanel.getCurrentMediaTypeText();
		// tmPanel.getTechnicalMetadataTV(fileId);
		// tmPanel = new TechnicalMetadataPanel(language, mediaTypeName);
		// tabPanel.add(tmPanel, "Información Técnica");
		// if (mainTab) {
		// tabPanel.selectTab(tabPanel.getWidgetIndex(tmPanel));
		// }
	}

	/**
	 * 
	 * @param fileId
	 */
	public void setTechnicalMetadataInfo(String fileId) {
		tmPanel.getTechnicalMetadataTV(fileId);

	}

}
