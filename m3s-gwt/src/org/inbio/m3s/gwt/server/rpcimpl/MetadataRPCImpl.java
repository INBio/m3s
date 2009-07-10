/**
 * 
 */
package org.inbio.m3s.gwt.server.rpcimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.config.UserProfile;
import org.inbio.m3s.converters.impl.InstitutionConverter;
import org.inbio.m3s.converters.MetadataConverter;
import org.inbio.m3s.converters.impl.PersonConverter;
import org.inbio.m3s.converters.impl.TechnicalMetadataConverter;
import org.inbio.m3s.dao.core.SiteDAO;
import org.inbio.m3s.gwt.client.dto.metadata.TechnicalMetadataGWTDTO;
import org.inbio.m3s.gwt.client.dto.util.InstitutionLiteGWTDTO;
import org.inbio.m3s.gwt.client.dto.util.PersonGWTDTO;
import org.inbio.m3s.gwt.client.exception.RPCIllegalArgumentException;
import org.inbio.m3s.gwt.client.rpcinterface.MetadataRPC;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.GeneralMetadataTV;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.UsesAndCopyrightsTV;
import org.inbio.m3s.gwt.client.widgets.metadata.ui.UsesAndCopyrightsPanel;
import org.inbio.m3s.dto.GeneralMetadataDTO;
import org.inbio.m3s.dto.UsesAndCopyrightsDTO;
import org.inbio.m3s.dto.agent.InstitutionLiteDTO;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.metadata.TechnicalMetadataDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.dao.DataCache;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.MediaManager;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.service.MetadataManager;
import org.inbio.m3s.service.SiteManager;
import org.inbio.m3s.service.TaxonomyManager;
import org.inbio.m3s.util.ServiceUtil;
import org.inbio.m3s.util.StringUtil;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
/**
 * @author jgutierrez
 * 
 */
public class MetadataRPCImpl extends RemoteServiceServlet implements MetadataRPC {

	private static Logger logger = Logger.getLogger(MetadataRPCImpl.class);

	private MetadataManager metadataManager = (MetadataManager) ServiceUtil.appContext.getBean(Properties.METADATA_MANAGER);
	private MediaManager mediaManager = (MediaManager) ServiceUtil.appContext.getBean(Properties.MEDIA_MANAGER);
	private MessageManager messageManager = (MessageManager) ServiceUtil.appContext.getBean(Properties.MESSAGE_MANAGER);
	private AgentManager agentManager = (AgentManager) ServiceUtil.appContext.getBean(Properties.AGENT_MANAGER);


	/**
	 * 
	 */
	private static final long serialVersionUID = -4875187521492942148L;

	/**
	 * Looks for the textual values that have to be display.
	 * 
	 * @param mediaId
	 * @return a GeneralMetadataTV object ready to be used on the GUI
	 * 
	 */
	public GeneralMetadataTV getGeneralMetadataTV(Integer mediaId) {
		logger.debug("getting General Metadata...");
		GeneralMetadataDTO gm =  mediaManager.getGM(mediaId);
		logger.debug("getting General Metadata... done");
		return MetadataConverter.toTextualValues(gm);
	}

	/**
	 * Gets all the uses and copyrigths metadata of a media in a display useful
	 * type
	 * 
	 * @param mediaId
	 *            database id of the media
	 * @return a UsesAndCopyrightsTV object ready to be used in the GUI
	 */
	public UsesAndCopyrightsTV getUsesAndCopyrigthsMetadataTV(Integer mediaId) {
		logger.debug("getting Uses And Copyrigths Metadata...");
		UsesAndCopyrightsDTO uacm = mediaManager.getUACM(mediaId);
		logger.debug("getting Uses And Copyrigths Metadata... done.");
		return MetadataConverter.toTextualValue(uacm);
	}

	/**
	 * 
	 * @param mediaId
	 * @return
	 */
	public TechnicalMetadataGWTDTO getTechnicalMetadataTV(Integer mediaId) {
		logger.debug("getting Technical Metadata...");
		TechnicalMetadataDTO tmDTO = metadataManager.getTechMetadataByMedia(String.valueOf(mediaId));
		logger.debug("getting Technical Metadata... done.");
		//return MetadataConverter.toTextualValues(tm);
		TechnicalMetadataConverter tmc = new TechnicalMetadataConverter();
		return (TechnicalMetadataGWTDTO) tmc.toGWTDTO(tmDTO);
	}

	/**
	 * Gets the technicalMetadata for a file and not from the database. Also
	 * loads the expected Names every technical metadata attribute
	 * 
	 * @param mediaTempFileId
	 * @param mediaTypeName
	 * @return
	 * @throws IllegalArgumentException
	 */
	public TechnicalMetadataGWTDTO getTechnicalMetadataTV(String mediaTempFileId, String mediaTypeName) throws IllegalArgumentException {

		String fileAddress = Properties.REAL_TEMP_FILES_DIR + mediaTempFileId;

		// TODO: this method should be fixed, the getTechnicalMetadataTVFromFile
		// change to a getTechnicalMetadataFromFile in the
		// usecases.admin.AdminmediaInfo.java then the result of that method is
		// a TM and the metadata caonverter should convert it to the appropiated
		// values as technicalmetadataTV
		
		TechnicalMetadataDTO tmDTO = metadataManager.getTechMetadataFromFile(mediaTypeName, fileAddress);
		TechnicalMetadataConverter tmc = new TechnicalMetadataConverter();
		return (TechnicalMetadataGWTDTO) tmc.toGWTDTO(tmDTO);
	}

	/**
	 * Gets the textual values of each tecnical metadata row, this is the value
	 * to be shown on the left column of the table, is NOT the value of the
	 * attribute!!
	 * 
	 * @param mediaTypeName
	 * @throws if
	 *             the MediaTypeName has no associated technical Metadata
	 */
	public TechnicalMetadataGWTDTO getTechnicalMetadataNames(String mediaTypeKey)
			throws IllegalArgumentException {
		logger.debug("getting Technical Metadata Names... done in metadata converter");
		try {
			TechnicalMetadataDTO tmDTO = metadataManager.getTechMetadataByMediaType(mediaTypeKey);
			TechnicalMetadataConverter tmc = new TechnicalMetadataConverter();
			return (TechnicalMetadataGWTDTO) tmc.toGWTDTO(tmDTO);
		} catch (IllegalArgumentException iae) {
			logger.debug("gettin Technical metadata Names... ilegal MediaTypeName");
			iae.getMessage();
			throw iae;
		}
	}

	/**
	 * Gets the tecnical metadata texts to be display in each row of the
	 * felxtable. This method uses the Properties.DEFAULT_LANGUAGE as parameter
	 * for the query
	 * 
	 * @param mediaTypeName
	 *            the name of the media attribute
	 * @return a list of string values, get(N) should be display on row N.
	 * 
	 */
	private List<String> getTMRowTexts(String mediaTypeName) {
		logger.debug("getting TM row texts...");
		
		return messageManager.getTMRowTexts(mediaTypeName);
		
	}

	/**
	 * @param gmtv
	 *            the GeneralMetadataTV from the GUI
	 * @param uactv
	 *            the UsesAndCopyrightsTV from the GUI
	 * @param tmGWTDTO
	 *            the TechnicalMetadataTV from the GUI
	 * @param username
	 * @return the mediaId of the element the was saved or null in case of error
	 * 
	 */
	public Integer saveMetadata(GeneralMetadataTV gmtv,
			UsesAndCopyrightsTV uactv, TechnicalMetadataGWTDTO tmGWTDTO, String username)
			throws RPCIllegalArgumentException {

		//boolean isPublicBefore;
		//boolean isPublicAfter;
		GeneralMetadataDTO gm = null;

		UsesAndCopyrightsDTO uac = null;

		TechnicalMetadataDTO tmDTO = null;
		TechnicalMetadataConverter tmc = new TechnicalMetadataConverter();

		// Sets the UserProfile
		UserProfile.setUsername(username);

		
		try {
			logger.debug("iniciando conversion de datos");
			
			gm = MetadataConverter.toDBValues(gmtv);
			logger.debug("listos los general metadata");
			
			uac = MetadataConverter.toDBValues(uactv);
			logger.debug("listos los uses and copyrigth metadata");
			
			tmDTO = tmc.toDTO(tmGWTDTO);
			logger.debug("listos los technical metadata");

		} catch (Exception iae) {
			logger.error("no se puede guardar el multimedio");
			logger.error(iae.getMessage());
			throw new RPCIllegalArgumentException(iae.getMessage());
		}

		logger.debug("iniciando el guardado de los datos");
		// if no media Id, this is a new reccord
		if (gmtv.getMediaId() == null && uactv.getMediaId() == null
				&& tmGWTDTO.getMediaKey() == null) {
			logger.debug("saving Metadata... insert");
			Integer mediaId = getMediaManager().insertNewMedia(gm, uac, tmDTO);

			logger.debug("saving Metadata... done");
			return mediaId;

		} else {
			// no new reccord, just update! If the public value of the
			// UsesAndCopyRigths changes the media has to be moved to the
			// corresponding folder
			logger.debug("saving Metadata... update");

			getMediaManager().updateGM(gm);
			logger.debug("saving Metadata... general metadata saved");

			getMediaManager().updateUACM(uac);
			logger.debug("saving Metadata... "
					+ "uses and copyrigths metadata saved");
			
			metadataManager.saveTechnicalMetadata(tmDTO);
			logger.debug("saving Metadata... technical metadata saved");

			logger.debug("saving Metadata... done");

			return gmtv.getMediaId();
		}

	}

	/**
	 * gets mediaTypes textual values
	 */
	public List<String> getMediaTypes(String categoryName) {
		logger.debug("getting Media Types...");

		logger.debug("DataCache.mediaType4Category = '"
				+ DataCache.mediaType4Category + "'.");
		logger.debug("categoryName = '" + categoryName + "'.");

		// info in cache?
		if (DataCache.mediaTypesInCache) {

			if (DataCache.mediaType4Category.compareTo(categoryName) == 0) {
				logger.debug("getting Media Types... were in cache.");
			} else {
				logger.debug("getting Media Types... weren't in cache.");
				DataCache.initMediaTypesInfo(categoryName);
			}

		} else {
			logger
					.debug("getting Media Types... requiered info weren't in cache!.");
			DataCache.initMediaTypesInfo(categoryName);
		}

		logger.debug("getting Media Types... done.");
		return DataCache.mediaTypesNames;
	}

	/**
	 * Gets the textual values of the mediaCategories
	 * 
	 * @return
	 */
	public List<String> getMediaCategories() {
		logger.debug("getting Media Categories...");
		if (DataCache.mediaCategoriesInCache) {
			logger.debug("getting Media Categories... were in cache.");
		} else {
			logger.debug("getting Media Categories... weren't in cache!.");
			DataCache.initMediaCategoriesInfo();
		}

		logger.debug("getting Media Categories... done.");

		return DataCache.mediaCategoriesNames;
	}

	/****************************************************************************/
	/* GET TAXONOMY */
	/****************************************************************************/

	/**
	 * Used in the GeneralMetadataTable class
	 * 
	 * @see GeneralMetadataTable
	 * 
	 * Gets the Complete Taxon Name of Specimen using the specimen number Uses a
	 * conection the local atta database
	 * 
	 * @param int -
	 *            the specimen id number
	 * @return String - Name of the specimen, or null is there was no match
	 * @deprecated
	 */
	public String getTaxonomyBySpecimenNumber(Integer specimenNumber)
			throws RPCIllegalArgumentException {
		TaxonLiteDTO tlDTO = null;
		//Integer taxonId = null;
		String name = null;
		String errorMsj = "No se puede obtener la taxonomía del especimen #"
				+ specimenNumber + ".";

		try {
			TaxonomyManager taxonomyManager = (TaxonomyManager) ServiceUtil.appContext.getBean(Properties.TAXONOMY_MANAGER);
			tlDTO = taxonomyManager.getTaxonLiteFromSpecimenId(specimenNumber.toString());
			name = tlDTO.getDefaultName();
			
			//taxonId = TaxonomyDAO.getTaxonDBIdFromSpecimenDBId(new Integer(
			//		specimenNumber));
			//name = TaxonomyDAO.getTaxonDefaultName(taxonId);

		} catch (IllegalArgumentException iae) {
			throw new RPCIllegalArgumentException(errorMsj + " "
					+ iae.getMessage());
		}

		// adds the text delimiter for compability with other methods
		name = name.concat(StringUtil.TEXT_DELIMITER + " ");
		return name;
	}

	/**
	 * 
	 * @param specimenNumber
	 * @return a list of taxonids as strings
	 * 
	 * @throws RPCIllegalArgumentException
	 */
	public List<String> getTaxonIdsBySpecimenNumber(Integer specimenNumber)
			throws RPCIllegalArgumentException {

		String errorMsj = "No se puede obtener la taxonomía asociada al especimen #"
				+ specimenNumber + ".";
		List<String> result = new ArrayList<String>();
		TaxonomyManager taxonomyManager = (TaxonomyManager) ServiceUtil.appContext.getBean(Properties.TAXONOMY_MANAGER);
		TaxonLiteDTO tlDTO = null;

		try {
			tlDTO = taxonomyManager.getTaxonLiteFromSpecimenId(specimenNumber.toString());
			result.add(tlDTO.getTaxonKey());
			return result;

		} catch (IllegalArgumentException iae) {
			throw new RPCIllegalArgumentException(errorMsj + " "
					+ iae.getMessage());
		}

	}


	/**
	 * 
	 * @param observationNumber
	 * @return
	 * @throws RPCIllegalArgumentException
	 */
	public List<String> getTaxonIdsByObservationNumber(Integer observationNumber)
			throws RPCIllegalArgumentException {

		String errorMsj = "No se puede obtener los taxonones asociados con la observacion  #"
				+ observationNumber + ".";
		List<String> result = new ArrayList<String>();
		TaxonomyManager taxonomyManager = (TaxonomyManager) ServiceUtil.appContext.getBean(Properties.TAXONOMY_MANAGER);
		List<TaxonLiteDTO> tlDTOList = null;

		try {
			tlDTOList = taxonomyManager.getTaxonLiteFromObservationId(observationNumber.toString());

			for(TaxonLiteDTO tlDTO : tlDTOList)
				result.add(tlDTO.getTaxonKey());
			
			return result;

		} catch (IllegalArgumentException iae) {
			throw new RPCIllegalArgumentException(errorMsj + " "
					+ iae.getMessage());
		}

	}

	/**
	 * 
	 * @param gatheringCode
	 * @return
	 * @throws RPCIllegalArgumentException
	 */
	public List<String> getTaxonIdsByGatheringCode(String gatheringCode)
			throws RPCIllegalArgumentException {
		logger.debug("INICIO getTaxonIdsByGatheringCode");

		String errorMsj = "No se puede obtener taxonomía asociada a la recolecta  #"
				+ gatheringCode + ".";
		List<String> result = new ArrayList<String>();
		List<TaxonLiteDTO> tlDTOList = null;
		TaxonomyManager taxonomyManger = (TaxonomyManager) ServiceUtil.appContext.getBean(Properties.TAXONOMY_MANAGER); 

		try {
			tlDTOList = taxonomyManger.getTaxonLiteFromGatheringCode(gatheringCode);

			for(TaxonLiteDTO tlDTO : tlDTOList){ 
				result.add(tlDTO.getTaxonKey());
			}
			
			return result;

		} catch (IllegalArgumentException iae) {
			throw new RPCIllegalArgumentException(errorMsj + iae.getMessage());
		}

	}

	/** ************************************************************************* */
	/* GET SITE */
	/** ************************************************************************* */

	/**
	 * Used in the GeneralMetadataTable class
	 * 
	 * @see GeneralMetadataTable
	 * 
	 * Gets the site or gathering label of a specimen Uses a conection the local
	 * atta database, invokes an atta function
	 * 
	 * @param specimenId
	 *            the database identification of the specimen
	 * @return a literal value of the site, gathering label of a specimen
	 * @throws RPCIllegalArgumentException
	 */
	public String getSiteFromSpecimenNumber(Integer specimenId)
			throws RPCIllegalArgumentException {
		logger.debug("getSiteFromSpecimenNumber... start");
		String errorMsj = "No se puede obtener la descripcion del sitio para el especimen #"
				+ specimenId + ".";
		SiteDAO siteDAO = (SiteDAO) ServiceUtil.appContext.getBean("INBioSiteDAO");

		try {
			logger.debug("getSiteFromSpecimenNumber... done");
			return siteDAO.getSiteDBIdFromSpecimenNumber(new Integer(specimenId));

		} catch (IllegalArgumentException iae) {
			logger.error("getSiteFromSpecimenNumber... error");
			logger.error(errorMsj);
			logger.error(iae.getMessage());
			throw new RPCIllegalArgumentException(errorMsj + " "
					+ iae.getMessage());
		}
	}

	/**
	 * Used in the GeneralMetadataTable class
	 * 
	 * @see GeneralMetadataTable
	 * 
	 * Gets the site of an observation: site description, observation
	 * surrounding description, observation site description and coordinates.
	 * Uses a conection the local atta database, invokes an atta function
	 * 
	 * @param observationNumber
	 *            the database identifier of the observation
	 * @return literal string value of the site of the observation
	 * @throws RPCIllegalArgumentException
	 */
	public String getSiteFromObservationNumber(Integer observationNumber)
			throws RPCIllegalArgumentException {

		logger.debug("getSiteFromObservationNumber... start");
		String errorMsj = "No se puede obtener la descripcion del sitio para la observacion #"
				+ observationNumber + ".";
		SiteDAO siteDAO = (SiteDAO) ServiceUtil.appContext.getBean("INBioSiteDAO");

		try {
			logger.debug("getSiteFromObservationNumber... done");
			return siteDAO.getiteDBIdFromObservationNumber(observationNumber);

		} catch (IllegalArgumentException iae) {
			logger.debug("getSiteFromObservationNumber... error");
			logger.debug(iae.getMessage());
			throw new RPCIllegalArgumentException(errorMsj + " "
					+ iae.getMessage());
		}
	}

	/**
	 * Used in the GeneralMetadataTable class
	 * 
	 * @see GeneralMetadataTable
	 * 
	 * Gets the site of an observation: site description, observation
	 * surrounding description, observation site description and coordinates.
	 * Uses a conection the local atta database, invokes an atta function
	 * 
	 * @param observationNumber
	 *            the database identifier of the observation
	 * @return literal string value of the site of the observation
	 * @throws RPCIllegalArgumentException
	 */
	public String getSiteFromGatheringCode(String gatheringCode)
			throws RPCIllegalArgumentException {

		logger.debug("getSiteFromGatheringCode... start");
		String errorMsj = "No se puede obtener la descripcion del sitio para la recolecta #"
				+ gatheringCode + ".";
		SiteManager siteManger = (SiteManager) ServiceUtil.appContext.getBean("siteManger");
		try {
			return siteManger.getSiteFromGatheringCode(gatheringCode);

		} catch (IllegalArgumentException iae) {
			logger.debug("getSiteFromGatheringCode... error");
			logger.debug(iae.getMessage());
			throw new RPCIllegalArgumentException(errorMsj + " "
					+ iae.getMessage());
		} catch (Exception e) {
			logger.debug("getSiteFromGatheringCode... error");
			logger.debug(e.getMessage());
			throw new RPCIllegalArgumentException(errorMsj);
		}
	}

	/**
	 * Used in the UsesAndCopyrightsPanel class
	 * 
	 * @see UsesAndCopyrightsTable
	 * 
	 * Gets all the People first and last name avaliables.
	 * 
	 * @return List of TextAndValue items.
	 */
	public List<PersonGWTDTO> getPeople() {
		logger.debug("getting people names...");
		
		PersonConverter plc = new PersonConverter();
		
		List<PersonLiteDTO> pLiteList =  agentManager.getAllPersonLite();
		
		return plc.toGWTDTOList(pLiteList);
		
	}

	public List<InstitutionLiteGWTDTO> getInstitutions() {
		
		InstitutionConverter ilc = new InstitutionConverter();
		
		List<InstitutionLiteDTO> ILiteDTO =  agentManager.getAllInstitutionLite();
		
		return ilc.toGWTDTOList(ILiteDTO);
	}

	/**
	 * Used in the UsesAndCopyrightsPanel class
	 * 
	 * @see UsesAndCopyrightsPanel
	 * 
	 * Gets all the Media Uses avaliable
	 * 
	 * @param language
	 *            is the id of the media uses, example 1 for espa�ol
	 * @return List of TextAndValue items.
	 * 
	 * The media uses have to be filtered by any thing?, like category?
	 */
	public List<String> getMediaUses() {
		logger.debug("getting media uses...");
		if (DataCache.mediaUsesInCache) {
			logger.debug("getting media uses... were in cache.");
		} else {
			logger.debug("getting media uses... weren't in cache!.");
			DataCache.initMediaUsesInfo();
		}

		logger.debug("getting media uses... done");

		return DataCache.mediaUsesNames;

	}

	/**
	 * Used in the UsesAndCopyrightsPanel
	 * 
	 * 
	 * Gets all the Use Policies avaliable.
	 * 
	 * @param language
	 *            is the id of the media uses, example 1 for espa�ol
	 * @return List of TextAndValue items.
	 */

	public List<String> getUsePolicies() {
		logger.debug("getting use policies...");
		if (DataCache.usePoliciesInCache) {
			logger.debug("use policies... were in cache.");
		} else {
			logger.debug("use policies... weren't in cache!.");
			DataCache.initUsePoliciesInfo();
		}

		logger.debug("getting use policies... done");

		return DataCache.usePoliciesNames;
	}
	
	/**
	 * @return the messageManager
	 */
	public MessageManager getMessageManager() {
		return messageManager;
	}

	/**
	 * @param messageManager the messageManager to set
	 */
	public void setMessageManager(MessageManager messageManager) {
		this.messageManager = messageManager;
	}

	/**
	 * @param mediaManager the mediaManager to set
	 */
	public void setMediaManager(MediaManager mediaManager) {
		this.mediaManager = mediaManager;
	}

	/**
	 * @return the mediaManager
	 */
	public MediaManager getMediaManager() {
		return mediaManager;
	}

	/**
	 * @param agentManager the agentManager to set
	 */
	public void setAgentManager(AgentManager agentManager) {
		this.agentManager = agentManager;
	}

	/**
	 * @return the agentManager
	 */
	public AgentManager getAgentManager() {
		return agentManager;
	}


}