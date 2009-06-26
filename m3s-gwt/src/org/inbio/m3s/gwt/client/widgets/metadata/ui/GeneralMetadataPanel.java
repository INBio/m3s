/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.metadata.ui;

import java.util.List;

import org.inbio.gwt.associatedto.client.dto.AssociatedToConstants;
import org.inbio.gwt.associatedto.client.listener.AssociatedToListener;
import org.inbio.gwt.associatedto.client.ui.AssociatedTo;
import org.inbio.gwt.controlledtext.client.ui.TextSelector;
import org.inbio.gwt.taxonomy.client.widget.TaxonSelector;
import org.inbio.m3s.gwt.client.config.ClientProperties;
import org.inbio.m3s.gwt.client.rpcinterface.MetadataRPC;
import org.inbio.m3s.gwt.client.rpcinterface.MetadataRPCAsync;
import org.inbio.m3s.gwt.client.widgets.login.LoginManager;
import org.inbio.m3s.gwt.client.widgets.metadata.CategoryAndTypeSelector;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.GeneralMetadataTV;
import org.inbio.m3s.gwt.client.widgets.metadata.listener.CategoryAndTypeListener;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This panel that manages all the general metadata of every multimedia, this
 * are: title, description, category, type, associated to, projects, series,
 * taxonomy, synaptic collection, keywords, geographic site, date and hour of
 * the picture (when the multimedia was taken). This widget has to know how to
 * display or not dis info.
 * 
 * 
 * @author jgutierrez
 * 
 */
public class GeneralMetadataPanel extends VerticalPanel implements
		AssociatedToListener, CategoryAndTypeListener {

	// categoryAndTypeListener for recording the media type change...
	CategoryAndTypeListener listener;

	FlexTable main = new FlexTable();

	// rpc service
	private MetadataRPCAsync rpc;

	private Integer language;

	private Integer mediaId = null;

	/**
	 * Class constructor, creates an empty table with all the text in the selected
	 * language
	 * 
	 * @param language
	 */
	public GeneralMetadataPanel(Integer language,	CategoryAndTypeListener catListener) {
		initRPC();
		listener = catListener;
		this.language = language;
		initPanel();
	}

	/*****************************************************************************
	 * Methods
	 ****************************************************************************/

	public GeneralMetadataPanel(Integer language, CategoryAndTypeListener catListener, GeneralMetadataTV gmtv) {
		this(language,	catListener);
		this.setGeneralMetadataTV(gmtv);
	}

	/**
	 * This is the core of the class constructor.
	 * 
	 * Inits the rpc mechanism used for the retraival of metadata.
	 * 
	 * Inits all the rows of the table. Inserts the texts and sets the correct
	 * widget that manages the metadata.
	 * 
	 */
	private void initPanel() {

		main.setCellSpacing(5);

		// this.setMediaTitleRow("Título:");
		main.setText(TITLE, TEXT, "Título");
		TextBox titleTB = new TextBox();
		titleTB.setWidth(ClientProperties.DEFAULT_TEXTBOX_WIDTH);
		main.setWidget(TITLE, WIDGET, titleTB);

		// this.setDescriptionRow("Descripción:");
		TextArea descriptionTA = new TextArea();
		descriptionTA.setWidth(ClientProperties.DEFAULT_TEXTAREA_WIDTH);
		descriptionTA.setVisibleLines(ClientProperties.DEFAULT_TEXT_AREA_LINES);
		main.setText(DESCRIPTION, TEXT, "Descripción:");
		main.setWidget(DESCRIPTION, WIDGET, descriptionTA);

		// this.setMediaCategoryAndTypeRow("Categoría de Multimedio:");
		main.setText(CATEGORY_AND_TYPE, TEXT, "Categoría de Multimedio:");
		main
				.setWidget(CATEGORY_AND_TYPE, WIDGET, new CategoryAndTypeSelector(this));

		// this.setAssotiatedToRow("Asociado a:", language);
		main.setText(ASSOCIATED_TO, TEXT, "Asociado a:");
		// main.setWidget(ASSOCIATED_TO, WIDGET, new AssociatedToSelector(
		// language, this));
		main.setWidget(ASSOCIATED_TO, WIDGET, new AssociatedTo(language, this,
				AssociatedToConstants.NO_ASSOCIATION, ""));

		// this.setProjectsRow("Proyectos:");
		main.setText(PROJECT, TEXT, "Proyectos:");
		TextBox projectTB = new TextBox();
		projectTB.setWidth(ClientProperties.DEFAULT_TEXTBOX_WIDTH);
		projectTB.setText(LoginManager.getUserName());
		main.setWidget(PROJECT, WIDGET, projectTB);

		// this.setSeriesRow("Series:");
		main.setText(SERIES, TEXT, "Series:");
		TextBox seriesTB = new TextBox();
		seriesTB.setWidth(ClientProperties.DEFAULT_TEXTBOX_WIDTH);
		// FIXME
		seriesTB.setEnabled(false);
		main.setWidget(SERIES, WIDGET, seriesTB);

		// this.setTaxonomyRow("Taxonomía");
		main.setText(TAXONOMY, TEXT, "Taxonomía");
		TaxonSelector ts = new TaxonSelector("taxonomyRPC");
		main.setWidget(TAXONOMY, WIDGET, ts);

		// this.setSynapticCollectionRow("Colección Sinoptica:");
		main.setText(SYNOPTIC_COLLECTION, TEXT, "Colección Sinoptica:");
		TextBox synapticCollectionTB = new TextBox();
		synapticCollectionTB.setWidth(ClientProperties.DEFAULT_TEXTBOX_WIDTH);
		// FIXME
		synapticCollectionTB.setEnabled(false);
		main.setWidget(SYNOPTIC_COLLECTION, WIDGET, synapticCollectionTB);

		// this.setKeyWordsRow("Palabras Clave:");
		main.setText(KEY_WORDS, TEXT, "Palabras Clave:");
		TextSelector keywords = new TextSelector("keywordsRPC");
		main.setWidget(KEY_WORDS, WIDGET, keywords);
		//TextBox keyWordsTB = new TextBox();
		//keyWordsTB.setWidth(ClientProperties.DEFAULT_TEXTBOX_WIDTH);
		//keyWordsTB.setEnabled(true);
		//main.setWidget(KEY_WORDS, WIDGET, keyWordsTB);

		// this.setSiteRow("Sitio");
		TextArea site = new TextArea();
		site.setWidth(ClientProperties.DEFAULT_TEXTAREA_WIDTH);
		site.setVisibleLines(ClientProperties.DEFAULT_TEXT_AREA_LINES);
		main.setText(SITE, TEXT, "Sitio");
		main.setWidget(SITE, WIDGET, site);

		this.add(main);
	}

	/**
	 * return the generalmetadataTV that is in the widget
	 * 
	 * @return generarlMetadataTV object
	 */
	public GeneralMetadataTV getGeneralMetadataTV() {
		GeneralMetadataTV gmtv = new GeneralMetadataTV();

		gmtv.setMediaId(mediaId);
		gmtv.setTitle(((TextBox) main.getWidget(TITLE, WIDGET)).getText());
		gmtv.setDescription(((TextArea) main.getWidget(DESCRIPTION, WIDGET))
				.getText());

		// media category and type
		gmtv.setMediaCategory(((CategoryAndTypeSelector) main.getWidget(
				CATEGORY_AND_TYPE, WIDGET)).getMediaCategory());
		gmtv.setMediaType(((CategoryAndTypeSelector) main.getWidget(
				CATEGORY_AND_TYPE, WIDGET)).getMediaType());

		// asociation
		gmtv.setAssociatedToInfo(((AssociatedTo) main
				.getWidget(ASSOCIATED_TO, WIDGET)).getAssociatedToInfo());

		// projects
		gmtv.setProjects(((TextBox) main.getWidget(PROJECT, WIDGET)).getText());

		// taxonomy
		gmtv.setTaxonomyInfo(((TaxonSelector) main.getWidget(TAXONOMY, WIDGET))
				.getTaxonomy());

		// this.setSynapticCollectionListValue(generalMetadata
		// .getSynopticColletion());

		// keywords
		gmtv.setKeyWords(((TextSelector) main.getWidget(KEY_WORDS, WIDGET)).getSelecetedTexts());
		//gmtv.setKeyWords(((TextBox) main.getWidget(KEY_WORDS, WIDGET)).getText());

		// TODO: has to fix the siteId stuff
		gmtv.setSiteDescription(((TextArea) main.getWidget(SITE, WIDGET)).getText());

		return gmtv;
	}

	/**
	 * Shows the general metadata given as parameter, the widget its suponse to be
	 * allready created.
	 * 
	 * @param gmtv
	 *          with the elements to be displayed
	 */
	public void setGeneralMetadataTV(GeneralMetadataTV gmtv) {

		mediaId = gmtv.getMediaId();

		// this.setMediaTitleValue(gmtv.getTitle());
		((TextBox) main.getWidget(TITLE, WIDGET)).setText(gmtv.getTitle());

		// this.setDescriptionValue(gmtv.getDescription());
		((TextArea) main.getWidget(DESCRIPTION, WIDGET)).setText(gmtv
				.getDescription());

		// media category and media type
		((CategoryAndTypeSelector) main.getWidget(CATEGORY_AND_TYPE, WIDGET))
				.setMediaCategoryAndType(gmtv.getMediaCategory(), gmtv.getMediaType());

		//associated to
		((AssociatedTo) main.getWidget(ASSOCIATED_TO, WIDGET))
			  .setAssociatedToInfo(gmtv .getAssociatedToInfo());

		// projects
		((TextBox) main.getWidget(PROJECT, WIDGET)).setText(gmtv.getProjects());

		// taxonomy
		((TaxonSelector) main.getWidget(TAXONOMY, WIDGET)).setTaxonomy(gmtv
				.getTaxonomyInfo());

		// keywords
		//((TextBox) main.getWidget(KEY_WORDS, WIDGET)).setText(gmtv.getKeyWords());
		((TextSelector) main.getWidget(KEY_WORDS, WIDGET)).setSelectedTexts(gmtv.getKeyWords());

		// site description
		if (gmtv.getSiteId() == null) {
			this.setSiteValue(gmtv.getSiteDescription());
		}
		// else
		//

	}

	/**
	 * 
	 * @param site
	 */
	private void setSiteValue(String site) {
		TextArea textArea = (TextArea) main.getWidget(SITE, WIDGET);
		textArea.setText(site);
	}

	/**
	 * 
	 * @param taxons
	 *          a list of Strings, where each one is the taxonId of the taxon to
	 *          be added to the taxonSelector widget
	 */
	private void setTaxonomy(List<String> taxons) {
		TaxonSelector ts = (TaxonSelector) main.getWidget(TAXONOMY, WIDGET);
		ts.setTaxonomy(taxons);
	}

	/**
	 * 
	 * @return the current value of the mediaType
	 */
	public String getCurrentMediaTypeText() {
		return ((CategoryAndTypeSelector) main.getWidget(CATEGORY_AND_TYPE, WIDGET))
				.getMediaType();
	}

	/*****************************************************************************
	 * Events Handling methods
	 ****************************************************************************/
	/**
	 * /** Fired when a new a change is recorded in the AssociatedToSelector
	 * widget receive as parameter the asscociatedToCode a constant of the
	 * AssociatedToSelector class and the int value of association
	 * 
	 */
	public void onAssociationChange(Integer associatedToCode,
			String associationValue) {

		if (associationValue == null && associatedToCode == null) {
			return;
		}

		try {
			// if (associatedToCode == AssociatedToSelector.SPECIMEN_NUMBER) {
			if (associatedToCode == AssociatedToConstants.SPECIMEN_NUMBER) {
				getInfoFromSpecimenNumber(new Integer(associationValue));

				// } else if (associatedToCode ==
				// AssociatedToSelector.OBSERVATION_NUMBER) {
			} else if (associatedToCode == AssociatedToConstants.OBSERVATION_NUMBER) {
				getInfoFromObservationNumber(new Integer(associationValue));

				// } else if (associatedToCode == AssociatedToSelector.GATHERING_CODE) {
			} else if (associatedToCode == AssociatedToConstants.GATHERING_CODE) {
				getInfoFromGatheringCode(associationValue);
			}
		} catch (NumberFormatException nfe) {
			Window.alert("Error: se espera un número para la asociación.");
		}
	}

	/**
	 * Gets the asociated taxonomical and site information of a given specimen
	 * number using RPC request's to the server, has error verification using
	 * exceptions
	 * 
	 * @param SpecimenNumber
	 */
	@SuppressWarnings("unchecked")
	private void getInfoFromSpecimenNumber(Integer SpecimenNumber) {
		System.out.println("getInfoFromSpecimenNumber CON ID DE SPECIMEN="
				+ SpecimenNumber);

		// rpc.getTaxonomyBySpecimenNumber(SpecimenNumber, new AsyncCallback() {
		rpc.getTaxonIdsBySpecimenNumber(SpecimenNumber, new AsyncCallback() {

			public void onFailure(Throwable caught) {
				Window.alert("Error: " + caught.getMessage());
			}

			public void onSuccess(Object result) {
				// setTaxonomyValue((String) result);
				// this result value must be a list of strings, each one is a
				// taxonId
				setTaxonomy((List<String>) result);
			}
		});

		rpc.getSiteFromSpecimenNumber(SpecimenNumber, new AsyncCallback() {

			public void onFailure(Throwable caught) {
				Window.alert("Error: " + caught.getMessage());
			}

			public void onSuccess(Object result) {
				setSiteValue((String) result);
			}
		});

	}

	/**
	 * Gets the asociated taxonomical and site information of a given observation
	 * number using RPC request's to the server, has error verification using
	 * exceptions
	 * 
	 * @param observationNumber
	 */
	@SuppressWarnings("unchecked")
	private void getInfoFromObservationNumber(Integer observationNumber) {

		rpc.getTaxonIdsByObservationNumber(observationNumber, new AsyncCallback() {
			// rpc.getTaxonomyByObservationNumber(observationNumber,
			// new AsyncCallback() {
			public void onFailure(Throwable caught) {
				Window.alert("Error: " + caught.getMessage());
			}

			public void onSuccess(Object result) {
				// this result value must be a list of strings, each one
				// is a
				// taxonId
				setTaxonomy((List<String>) result);

			}
		});

		rpc.getSiteFromObservationNumber(observationNumber, new AsyncCallback() {

			public void onFailure(Throwable caught) {
				Window.alert("Error: " + caught.getMessage());
			}

			public void onSuccess(Object result) {
				setSiteValue((String) result);
				// RPCSuccessManager(GET_SITE_FROM_OBSERVATION_NUMBER,
				// result);
			}
		});
	}

	/**
	 * Gets the asociated taxonomical and site information of a given lisst of
	 * specimen numbers asociated with a gathering number using RPC request's to
	 * the server, has error verification using exceptions
	 * 
	 * @param associationValue
	 */
	@SuppressWarnings("unchecked")
	private void getInfoFromGatheringCode(String code) {
		System.out.println("getInfoFromGatheringCode para '" + code + "' .");

		rpc.getTaxonIdsByGatheringCode(code, new AsyncCallback() {

			// rpc.getTaxonomyFromGatheringCode(code, new AsyncCallback() {
			public void onFailure(Throwable caught) {
				Window.alert("Error: " + caught.getMessage());
			}

			public void onSuccess(Object result) {
				// setTaxonomyValue((String) result);
				setTaxonomy((List<String>) result);

			}
		});

		rpc.getSiteFromGatheringCode(code, new AsyncCallback() {

			public void onFailure(Throwable caught) {
				Window.alert("Error: " + caught.getMessage());
			}

			public void onSuccess(Object result) {
				setSiteValue((String) result);
			}
		});
	}

	/*****************************************************************************
	 * RPC methods
	 ****************************************************************************/

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

	/*****************************************************************************
	 * Constants
	 ****************************************************************************/
	// CONSTANTS FOR ROW USES
	private static int TITLE = 0;

	private static int DESCRIPTION = 1;

	private static int CATEGORY_AND_TYPE = 2;

	private static int ASSOCIATED_TO = 3;

	private static int PROJECT = 4;

	private static int SERIES = 5;

	private static int TAXONOMY = 6;

	private static int SYNOPTIC_COLLECTION = 7;

	private static int KEY_WORDS = 8;

	private static int SITE = 9;

	// COLUMN TYPE INDEX
	private static int TEXT = 0;

	private static int WIDGET = 1;

	/**
	 * Fired when the type on the type and category widgets changed. Throws the
	 * event to be implemented by another widget
	 * 
	 * @param type
	 */
	public void onTypeChange(String type) {
		listener.onTypeChange(type);
	}

}
