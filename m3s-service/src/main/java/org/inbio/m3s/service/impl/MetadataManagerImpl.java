/**
 * 
 */
package org.inbio.m3s.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.m3s.dao.core.GatheringMediaDAO;
import org.inbio.m3s.dao.core.KeywordDAO;
import org.inbio.m3s.dao.core.MediaAttributeDAO;
import org.inbio.m3s.dao.core.MediaAttributeTypeDAO;
import org.inbio.m3s.dao.core.MediaAttributeValueDAO;
import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.dao.core.MediaKeywordDAO;
import org.inbio.m3s.dao.core.MediaProjectDAO;
import org.inbio.m3s.dao.core.MediaUseDAO;
import org.inbio.m3s.dao.core.MediaUseMediaDAO;
import org.inbio.m3s.dao.core.ObservationMediaDAO;
import org.inbio.m3s.dao.core.ProjectDAO;
import org.inbio.m3s.dao.core.SpecimenMediaDAO;
import org.inbio.m3s.dao.core.TaxonMediaDAO;
import org.inbio.m3s.dao.core.TextTranslationDAO;
import org.inbio.m3s.dao.multimedia.MetadataExtractorDAO;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.dto.message.ProjectDTO;
import org.inbio.m3s.dto.message.ProjectDTOFactory;
import org.inbio.m3s.dto.metadata.MediaUseDTO;
import org.inbio.m3s.dto.metadata.MetadataDTO;
import org.inbio.m3s.dto.metadata.TechnicalMetadataDTO;
import org.inbio.m3s.dto.metadata.TechnicalMetadataItemDTO;
import org.inbio.m3s.dto.metadata.util.MediaAttributeEntity;
import org.inbio.m3s.dto.metadata.util.MetadataStandardEntity;
import org.inbio.m3s.dto.taxonomy.GatheringLiteDTO;
import org.inbio.m3s.dto.taxonomy.ObservationLiteDTO;
import org.inbio.m3s.dto.taxonomy.SpecimenLiteDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.model.core.GatheringMedia;
import org.inbio.m3s.model.core.GatheringMediaId;
import org.inbio.m3s.model.core.Keyword;
import org.inbio.m3s.model.core.Media;
import org.inbio.m3s.model.core.MediaAttribute;
import org.inbio.m3s.model.core.MediaAttributeType;
import org.inbio.m3s.model.core.MediaAttributeValue;
import org.inbio.m3s.model.core.MediaAttributeValueId;
import org.inbio.m3s.model.core.MediaKeyword;
import org.inbio.m3s.model.core.MediaKeywordId;
import org.inbio.m3s.model.core.MediaProject;
import org.inbio.m3s.model.core.MediaProjectId;
import org.inbio.m3s.model.core.MediaUse;
import org.inbio.m3s.model.core.MediaUseMedia;
import org.inbio.m3s.model.core.MediaUseMediaId;
import org.inbio.m3s.model.core.ObservedTaxonMedia;
import org.inbio.m3s.model.core.ObservedTaxonMediaId;
import org.inbio.m3s.model.core.Project;
import org.inbio.m3s.model.core.SpecimenMedia;
import org.inbio.m3s.model.core.SpecimenMediaId;
import org.inbio.m3s.model.core.TaxonMedia;
import org.inbio.m3s.model.core.TaxonMediaId;
import org.inbio.m3s.model.core.TextTranslation;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.service.MetadataManager;
import org.inbio.m3s.service.TaxonomyManager;

/**
 * @author jgutierrez
 * 
 */
public class MetadataManagerImpl implements MetadataManager {

	private static Logger logger = Logger.getLogger(MetadataManagerImpl.class);

	private MediaDAO mediaDAO;
	private MediaProjectDAO mediaProjectDAO;
	private MediaKeywordDAO mediaKeywordDAO;
	private MediaUseDAO mediaUseDAO;
	private MediaUseMediaDAO mediaUseMediaDAO;
	private TaxonMediaDAO taxonMediaDAO;
	private GatheringMediaDAO gatheringMediaDAO; 
	private SpecimenMediaDAO specimenMediaDAO;
	private ObservationMediaDAO observationMediaDAO;	
	private MediaAttributeDAO mediaAttributeDAO;
	private TextTranslationDAO textTranslationDAO;
	private MediaAttributeTypeDAO mediaAttributeTypeDAO;
	private MediaAttributeValueDAO mediaAttributeValueDAO;	
	private ProjectDAO projectDAO;
	private ProjectDTOFactory projectDTOFactory;
	private KeywordDAO keywordDAO;
	/* Este dao no se inicializa con inyeccion de dependencia */
	/* This dao is not initialized using dependency injection */
	private MetadataExtractorDAO metadataExtractorDAO;
	
	private TaxonomyManager taxonomyManager;
	private AgentManager agentManager;

	
	/**


	 */

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.MetadataManager#getMetadataByMedia(java.lang.String)
	 */
	public MetadataDTO getMetadataByMedia(String mediaKey) throws IllegalArgumentException {
		logger.debug("getting Metadata... mediaKey [" + mediaKey + "]");
		
		MetadataDTO mDTO = new MetadataDTO();
		
		try {
			Media m = mediaDAO.findById(Media.class, Integer.valueOf(mediaKey));
			
			mDTO.setMediaKey(String.valueOf(m.getMediaId()));
			mDTO.setTitle(m.getTitle());
			mDTO.setDescription(m.getDescription());
			mDTO.setMediaTypeKey(String.valueOf(m.getMediaTypeId()));
			mDTO.setSiteKey(String.valueOf(m.getSiteId()));
			mDTO.setSiteDescription(m.getDescription());
			mDTO.setAuthorKey(String.valueOf(m.getAuthorPersonId()));
			
			if(m.getOwnerPersonId() == null){
				mDTO.setPersonOwnerKey(null);
				mDTO.setInstitutionOwnerKey(String.valueOf(m.getOwnerInstitutionId()));
			} else{
				mDTO.setPersonOwnerKey(String.valueOf(m.getOwnerPersonId()));
				mDTO.setInstitutionOwnerKey(null);
			}
						
			mDTO.setUsePolicyKey(String.valueOf(m.getUsePolicyId()));
			mDTO.setIsPublic(m.getIsPublic());
	
			// projects
			List<Project> projects = projectDAO.findAllByMedia(m.getMediaId());
			List<ProjectDTO> projectsList = projectDTOFactory.createDTOList(projects);
			mDTO.setProjectsList(projectsList);
			
			// setKeyWords
			List<Keyword> keywords = keywordDAO.findAllByMedia(m.getMediaId());
			List<KeywordDTO> keywordsDTOList = new ArrayList<KeywordDTO>();
			TextTranslation tt;
			for(Keyword k : keywords){
				tt = textTranslationDAO.finByIdAndLanguage(k.getNameTextId(), MessageManager.DEFAULT_LANGUAGE);
				keywordsDTOList.add(new KeywordDTO(k.getKeywordId(),tt.getName()));
			}
			mDTO.setKeywordsList(keywordsDTOList);

/* TODO:
	+/- private List<SpecimenLiteDTO> associatedSpecimensList;
	+/- private List<ObservationLiteDTO> associatedObservationsList;
	+/- private List<GatheringLiteDTO> associatedGatheringsList;
	+/- private List<TaxonLiteDTO> taxonsList;
*/
						

			// makes the other queries to complete the metadata
			List<SpecimenLiteDTO> slDTOList = taxonomyManager.getAllSpecimensLiteForMedia(mediaKey);
			mDTO.setAssociatedSpecimensList(slDTOList);
			
			List<ObservationLiteDTO> observationsList = taxonomyManager.getAllObservationsLiteForMedia(mediaKey);
			mDTO.setAssociatedObservationsList(observationsList);
			
			List<GatheringLiteDTO> glDTOList = taxonomyManager.getAllGatheringsLiteForMedia(mediaKey);
			mDTO.setAssociatedGatheringsList(glDTOList);
				
			//taxons
			List<TaxonLiteDTO> taxonLiteList = taxonomyManager.getTaxonLiteForMediaId(mediaKey);
			mDTO.setTaxonsList(taxonLiteList);

			
			TechnicalMetadataDTO tmDTO = getTechMetadataByMedia(mediaKey);
			mDTO.setItems(tmDTO.getItems());
			
			logger.info("getting Metadata... done");
			return mDTO;

		} catch (Exception he) {
			logger.error("There was a hibernate exeption in the query");
			throw new IllegalArgumentException("Query fails on getMetadata", he);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.MetadataManager#updateMetadata(org.inbio.m3s.dto.metadata.MetadataDTO)
	 */
	public void updateMetadata(MetadataDTO metadataDTO) throws IllegalArgumentException {
		logger.info("updating Metadata... mediaId [" + metadataDTO.getMediaKey() + "]");
		MetadataDTO dbMetadataDTO = this.getMetadataByMedia(metadataDTO.getMediaKey());

		logger.debug("updating Metadata... equals? " + dbMetadataDTO.equals(metadataDTO));
		if (dbMetadataDTO.equals(metadataDTO)) {
			logger.debug("updating Metadata... nothing to update");
			logger.info("updating Metadata... done");
			return;
		}
		logger.debug("updating Metadata... init the update");
	
		Media theMedia;

		try {
			theMedia = (Media) mediaDAO.findById(Media.class, Integer.valueOf(metadataDTO.getMediaKey()));

			logger.debug("updating Metadata... 00");
			if (dbMetadataDTO.getTitle() == null
					|| (dbMetadataDTO.getTitle().equals(metadataDTO.getTitle()) == false)) {
				logger.debug("updating Metadata... updating title");
				theMedia.setTitle(metadataDTO.getTitle());
			}

			logger.debug("updating Metadata... 01");
			if (dbMetadataDTO.getDescription() == null
					|| (dbMetadataDTO.getDescription().equals(metadataDTO.getDescription()) == false)) {
				logger.debug("updating Metadata... updating description");
				theMedia.setDescription(metadataDTO.getDescription());
			}

			logger.debug("updating Metadata... 02");
			if (dbMetadataDTO.getMediaTypeKey().equals(metadataDTO.getMediaTypeKey()) == false) {
				logger.debug("updating Metadata... updating mediaType");
				theMedia.setMediaTypeId(Integer.valueOf(metadataDTO.getMediaTypeKey()));
			}

			logger.debug("updating Metadata... 03");
			if (dbMetadataDTO.getAssociatedSpecimensList().equals(
					metadataDTO.getAssociatedSpecimensList()) == false) {
				logger.debug("updating Metadata... updating AssociatedSpecimens");
				deleteSpecimens(metadataDTO.getMediaKey(), dbMetadataDTO.getAssociatedSpecimensList());
				if (metadataDTO.getAssociatedSpecimensList() != null) {
					addSpecimens(metadataDTO.getMediaKey(), metadataDTO.getAssociatedSpecimensList());
				}
			}

			logger.debug("updating Metadata... 04");
			if (dbMetadataDTO.getAssociatedObservationsList().equals(
					metadataDTO.getAssociatedObservationsList()) == false) {
				logger
						.debug("updating Metadata... updating AssociatedObservations");
				deleteObservations(metadataDTO.getMediaKey(), dbMetadataDTO.getAssociatedObservationsList());
				if (metadataDTO.getAssociatedObservationsList() != null) {
					addObservations(metadataDTO.getMediaKey(), metadataDTO.getAssociatedObservationsList());
				}
			}

			logger.debug("updating Metadata... 04.1");
			if(dbMetadataDTO.getAssociatedGatheringsList().equals(metadataDTO.getAssociatedGatheringsList()) == false){
				logger.debug("updating Metadata... updating getAssociatedGatherings");
				deleteGatherings(metadataDTO.getMediaKey(), dbMetadataDTO.getAssociatedGatheringsList());
				if (metadataDTO.getAssociatedGatheringsList() != null) {
					addGatherings(metadataDTO.getMediaKey(), metadataDTO.getAssociatedGatheringsList());
				}
			}

			logger.debug("updating Metadata... 05");
			if (dbMetadataDTO.getTaxonsList().equals(metadataDTO.getTaxonsList()) == false) {
				logger.debug("updating Metadata... updating taxonomy");

				deleteTaxons(metadataDTO.getMediaKey(), dbMetadataDTO.getTaxonsList());
				if (metadataDTO.getTaxonsList() != null) {
					addTaxons(metadataDTO.getMediaKey(), metadataDTO.getTaxonsList());
				}

			}

			// TODO: set the series

			logger.debug("updating Metadata... 06 - keywords");
			// keywords
			if(dbMetadataDTO.getKeywordsList().equals(metadataDTO.getKeywordsList()) == false) {
				logger.debug("updating Metadata... updating keywords");
				deleteKeywords(metadataDTO.getMediaKey(), dbMetadataDTO.getKeywordsList());
				if (metadataDTO.getKeywordsList() != null) {
					addKeywordsList(metadataDTO.getMediaKey(), metadataDTO.getKeywordsList());
				}
			}

			// TODO: how to deal with the siteID
			// theMedia.setSiteId(gm.getSiteId());
			// logger.info("seting GeneralMetadata... SiteId set");

			logger.debug("updating Metadata... 07");
			if (dbMetadataDTO.getSiteDescription() == null
					|| (dbMetadataDTO.getSiteDescription().equals(
							metadataDTO.getSiteDescription()) == false)) {
				logger.debug("updating Metadata... updating site description");
				theMedia.setSiteDescription(metadataDTO.getSiteDescription());
			}
			
			logger.debug("updating Metadata... 08 - projects");
			// Projects
			if(dbMetadataDTO.getProjectsList().equals(metadataDTO.getProjectsList()) == false) {
				logger.debug("updating Metadata... updating projects");
				deleteProjects(metadataDTO.getMediaKey(), dbMetadataDTO.getProjectsList());
				if (metadataDTO.getProjectsList() != null) {
					addProjectsList(metadataDTO.getMediaKey(), metadataDTO.getProjectsList());
				}
			}
			
			logger.debug("updating Metadata... 09 - author");
			if (dbMetadataDTO.getAuthorKey().equals(metadataDTO.getAuthorKey()) == false) {
				logger.debug("updating Metadata... updating authorKey");
				theMedia.setAuthorPersonId(Integer.valueOf(metadataDTO.getAuthorKey()));
			}

			logger.debug("updating Metadata... 10 - Owners");
			if(metadataDTO.getInstitutionOwnerKey() != null){
				theMedia.setOwnerInstitutionId(Integer.valueOf(metadataDTO.getInstitutionOwnerKey()));
				theMedia.setOwnerPersonId(null);
			}
			if(metadataDTO.getPersonOwnerKey() != null){
				theMedia.setOwnerPersonId(Integer.valueOf(metadataDTO.getPersonOwnerKey()));	
				theMedia.setOwnerInstitutionId(null);
			}	
			logger.debug("updating Metadata... updating Owners DONE");
			
			logger.debug("updating Metadata... 11 - Use Policy");
			if (dbMetadataDTO.getUsePolicyKey().equals(metadataDTO.getUsePolicyKey()) == false) {
				theMedia.setUsePolicyId(Integer.valueOf(metadataDTO.getUsePolicyKey()));
			}
			logger.debug("updating Metadata... updating use policy DONE");

			// isPublic;
			logger.debug("updating Metadata... 12 - Is Public");
			if (dbMetadataDTO.getIsPublic().equals(metadataDTO.getIsPublic()) == false) {
				logger.debug("updating Metadta... updating is public");
				theMedia.setIsPublic(metadataDTO.getIsPublic());
			}
			logger.debug("updating Metadata... updating is public DONE");

			logger.debug("updating Metadata... execute the update");
			// updates the Media Object in the database
			mediaDAO.update(theMedia);

		} catch (Exception he) {
			logger.error("There was a hibernate exeption in updateMetadata");
			logger.error(he.getMessage());
			throw new IllegalArgumentException("fails on updateMetadata", he);
		}

		logger.info("updating Metadata... done");

	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.MetadataManager#saveMetadata(org.inbio.m3s.dto.metadata.MetadataDTO)
	 */
	public Integer saveMetadata(MetadataDTO metadataDTO) throws IllegalArgumentException {
		Media theMedia = new Media();

		
		// set public property of the media
		theMedia.setIsPublic(metadataDTO.getIsPublic());
		logger.debug("saving MetadataDTO... is public set ["+theMedia.getIsPublic()+"]");
		//media Type
		theMedia.setMediaTypeId(Integer.valueOf(metadataDTO.getMediaTypeKey()));
		logger.debug("saving MetadataDTO... media Type set ["+theMedia.getMediaTypeId()+"]");
		//use Policy
		theMedia.setUsePolicyId(Integer.valueOf(metadataDTO.getUsePolicyKey()));
		logger.debug("saving MetadataDTO... use policy set ["+theMedia.getUsePolicyId()+"]");		
		//log values
		theMedia.setCreatedBy(metadataDTO.getUsername());
		logger.debug("saving MetadataDTO... created by set ["+theMedia.getCreatedBy()+"]");
		theMedia.setLastModificationBy(metadataDTO.getUsername());
		logger.debug("saving MetadataDTO... last modification by set ["+theMedia.getLastModificationBy()+"]");
		//title
		theMedia.setTitle(metadataDTO.getTitle());
		logger.debug("saving MetadataDTO... title set ["+theMedia.getTitle()+"]");
		//description
		theMedia.setDescription(metadataDTO.getDescription());
		logger.info("saving MetadataDTO... description set["+theMedia.getDescription()+"]");
		// sets the Media type
		theMedia.setMediaTypeId(Integer.valueOf(metadataDTO.getMediaTypeKey()));
		logger.info("saving MetadataDTO... media type set["+theMedia.getMediaTypeId()+"]");
		//Author
		theMedia.setAuthorPersonId(Integer.valueOf(metadataDTO.getAuthorKey()));
		logger.debug("Saving MetadataDTO... Author set["+theMedia.getAuthorPersonId()+"]");
		//Owner
		if(metadataDTO.getPersonOwnerKey() == null)
			theMedia.setOwnerPersonId(null);
		else
			theMedia.setOwnerPersonId(Integer.valueOf(metadataDTO.getPersonOwnerKey()));
			
		if(metadataDTO.getInstitutionOwnerKey() == null)
			theMedia.setOwnerInstitutionId(null);
		else
			theMedia.setOwnerInstitutionId(Integer.valueOf(metadataDTO.getInstitutionOwnerKey()));
		logger.debug("Saving MetadataDTO... Owner value set");		
		
		// Site information
		if(metadataDTO.getSiteKey()== null)
			theMedia.setSiteId(null);
		else
			theMedia.setSiteId(Integer.valueOf(metadataDTO.getSiteKey()));
		logger.debug("Saving MetadataDTO... Site Id set");
		theMedia.setSiteDescription(metadataDTO.getSiteDescription());
		logger.debug("Saving MetadataDTO... Site Description set");
		
	// sets the Media elements that need DB conection
		try {
			// saves the Media Object in the database
			mediaDAO.create(theMedia);			
			logger.debug("The new Media will have the Id: >" + theMedia.getMediaId() + "<");

			
			// set the associatedSpecimens
			if(metadataDTO.getAssociatedSpecimensList()!=null)
				addSpecimens(metadataDTO.getMediaKey(), metadataDTO.getAssociatedSpecimensList());
			logger.debug("Saving MetadataDTO... AsociatedSpecimens set");
			
			// set the associatedObservations
			if(metadataDTO.getAssociatedObservationsList()!=null)
				addObservations(metadataDTO.getMediaKey(), metadataDTO.getAssociatedObservationsList());
			logger.debug("Saving MetadataDTO... AsociatedObservations set");
			
			// set the associatedCollections
			if(metadataDTO.getAssociatedGatheringsList()!=null)
				addGatherings(metadataDTO.getMediaKey(), metadataDTO.getAssociatedGatheringsList());
			logger.debug("Saving MetadataDTO... AsociatedCollects set");
			
			//projects
			if(metadataDTO.getProjectsList()!=null)
				addProjectsList(metadataDTO.getMediaKey(), metadataDTO.getProjectsList());
			logger.debug("Saving MetadataDTO... Projects set");
			
			// set the associatedTaxonomy
			if(metadataDTO.getTaxonsList()!=null)
				addTaxons(metadataDTO.getMediaKey(), metadataDTO.getTaxonsList());
			logger.debug("Saving MetadataDTO... Taxonomy set");

			// keyWords
			if(metadataDTO.getKeywordsList()!=null)
				addKeywordsList(metadataDTO.getMediaKey(), metadataDTO.getKeywordsList());
			logger.debug("Saving MetadataDTO... Keywords set");

			
	    this.saveTechnicalMetadata(metadataDTO.getMediaKey(), metadataDTO.getItems());
	    
			/* is not necessary 
			// saves the Media Object in the database
			mediaDAO.update(theMedia);
			 */
			
		} catch (Exception he) {
			logger.error("There was a hibernate exeption in the insertMedia");
			logger.error(he.getMessage());
			throw new IllegalArgumentException("Query fails on insertMedia", he);
		}/* catch (IllegalArgumentException iae) {
			logger.error("The insert was involved in erorrs");
			throw new IllegalArgumentException("Not valid arguments.", iae);
		}*/

		logger.info("Todo listo y pura vida con el multimedio>"+ theMedia.getMediaId() + "<");

		return theMedia.getMediaId();

	}
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.inbio.m3s.service.MetadataManager#getTechMetadataByMedia(java.lang.
	 * String)
	 */
	public TechnicalMetadataDTO getTechMetadataByMedia(String mediaKey)
			throws IllegalArgumentException {
		logger.debug("getTechnicalMetadata with mediaId [" + mediaKey + "]");

		Media media = (Media) getMediaDAO().findById(Media.class,
				new Integer(mediaKey));
		if (media == null)
			throw new IllegalArgumentException("Invalid mediaKey[" + mediaKey + "]");

		String mediaTypeKey = String.valueOf(media.getMediaTypeId());

		TechnicalMetadataDTO tmDTO = getTechMetadataByMediaType(mediaTypeKey);
		tmDTO.setMediaKey(mediaKey);

		MediaAttributeValue mav;
		MediaAttributeValueId mavId;
		MediaAttributeEntity mae;
		
		for (TechnicalMetadataItemDTO tmiDTO : tmDTO.getItems()) {
			mavId = new MediaAttributeValueId(new Integer(tmiDTO.getMediaAttributeKey()), media.getMediaId());
			mav = (MediaAttributeValue) mediaAttributeValueDAO.findById(
					MediaAttributeValue.class, mavId);
			mae = MediaAttributeEntity.getById(mavId.getMediaAttributeId());
			switch ( mae.getMediaAttributeValueType()) {
			case 'V':
				tmiDTO.setValue(mav.getValueVarchar());
				break;
			case 'N':
				if (mav.getValueNumber() != null)
					tmiDTO.setValue(mav.getValueNumber().toString());
				else
					tmiDTO.setValue(null);
				break;
			case 'D':
				tmiDTO.setValue(mav.getValueDate().toString());
				break;
			default:
				tmiDTO.setValue("");
			}

		}

		return tmDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.inbio.m3s.service.MetadataManager#getTechMetadataByMediaType(java.lang
	 * .String)
	 */
	public TechnicalMetadataDTO getTechMetadataByMediaType(String mediaTypeKey) {

		logger.debug("getTechMetadataByMediaType with mediaId [" + mediaTypeKey + "]");

		TechnicalMetadataDTO tmDTO = new TechnicalMetadataDTO(null, mediaTypeKey);
		TechnicalMetadataItemDTO tmiDTO;

		for (MediaAttribute ma : mediaAttributeDAO.findAllByMediaType(mediaTypeKey)) {
			tmiDTO = new TechnicalMetadataItemDTO(String.valueOf(ma
					.getMediaAttributeId()), ma.getNameTextKey(), null);
			tmDTO.addItem(tmiDTO);
		}
		return tmDTO;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.inbio.m3s.service.MetadataManager#getTechMetadataFromFile(java.lang
	 * .String, java.lang.String)
	 */
	public TechnicalMetadataDTO getTechMetadataFromFile(String mediaTypeKey,
			String fileAddress) {

		logger.debug("getTechMetadataFromFile with mediaTypeKey [" + mediaTypeKey
				+ "] for:" + fileAddress);

		TechnicalMetadataDTO tmDTO = new TechnicalMetadataDTO(null, mediaTypeKey);
		TechnicalMetadataItemDTO tmiDTO;
		MetadataStandardEntity metadataStandardEntity;
		MediaAttribute ma;
		String value;
		String id;

		try {

			for (MediaAttributeType mat : mediaAttributeTypeDAO
					.findAllByMediaType(mediaTypeKey)) {

				metadataStandardEntity = MetadataStandardEntity.getById(mat.getMetadataStandardId().intValue());
				logger.debug("Metadata Standard Implementation Class: "
						+ metadataStandardEntity.getImplementingClass());
				metadataExtractorDAO = metadataStandardEntity
						.getMetadataExtractorDAOImpl();
				metadataExtractorDAO.init(fileAddress);
				value = metadataExtractorDAO.getAttributeValue(mat
						.getStandardAttributeId());

				ma = (MediaAttribute) mediaAttributeDAO.findById(MediaAttribute.class,
						mat.getMediaAttributeId());
				id = String.valueOf(ma.getMediaAttributeId());


				tmiDTO = new TechnicalMetadataItemDTO(id, ma.getNameTextKey(), value);
				tmDTO.addItem(tmiDTO);
			}

			logger.info(tmDTO.toString());
			return tmDTO;

		} catch (Exception e) {
			return null;

		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.MetadataManager#saveTechnicalMetadata(java.lang.String, java.util.List)
	 */
	public void saveTechnicalMetadata(String mediaKey, List<TechnicalMetadataItemDTO> mediaAttributesList) {
		logger.debug("saveTechnicalMetadata for mediaId = "+ mediaKey);
		logger.debug(mediaAttributesList.toString());

		MediaAttributeEntity mae;
		MediaAttributeValue mav;
		MediaAttributeValueId mavId;

		for (TechnicalMetadataItemDTO tmiDTO : mediaAttributesList) {
			logger.info(tmiDTO.toString());
			mavId = new MediaAttributeValueId(tmiDTO.getMediaAttributeKey(), mediaKey);
			mav = (MediaAttributeValue) mediaAttributeValueDAO.findById(
					MediaAttributeValue.class, mavId);

			if (mav == null)
				mav = saveEmptyMediaAttributeValue(tmiDTO.getMediaAttributeKey(),mediaKey);
			
			mae = MediaAttributeEntity.getById(mavId.getMediaAttributeId());

			switch (mae.getMediaAttributeValueType()) {
			case 'V': {
				logger.info("varchar");
				mav.setValueVarchar(tmiDTO.getValue());
				logger.info("salvando: " + mav.getId() + " con el valor:"
						+ mav.getValueVarchar());
				break;
			}
			case 'N': {
				logger.info("number");
				try {
					mav.setValueNumber(Integer.valueOf(tmiDTO.getValue()));
				} catch (Exception e) {
					mav.setValueNumber(null);
				}
				logger.info("salvando: " + mav.getId() + " con el valor:"
						+ mav.getValueNumber());
				break;
			}
			case 'D':
				logger.info("date");
				// dateFormat for metadata from the camara
				SimpleDateFormat dateFormat;
				Date date = null;
				try {
					dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
					date = dateFormat.parse(tmiDTO.getValue());
				} catch (Exception e) {
					logger.debug("date viene directamente de la camara");
				}

				try {
					// dateFormat for info from the DB
					dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if (date == null)
						date = dateFormat.parse(tmiDTO.getValue());
				} catch (Exception e) {
					logger.error(e.getMessage());
					logger.info("salvando: " + mav.getId() + " con el valor:"
							+ mav.getValueDate());
					mav.setValueDate(null);
				}

				if (date == null) {
					mav.setValueDate(new Date());
					logger.info("salvando: " + mav.getId() + " con el valor:"
							+ mav.getValueDate());
				} else {
					mav.setValueDate(new Timestamp(date.getTime()));
					logger.info("salvando: " + mav.getId() + " con el valor:"
							+ mav.getValueDate());
				}
				break;
			}

			mediaAttributeValueDAO.update(mav);
		}

		logger.info("saveTechnicalMetadata done!");
	}

	/**
	 * 
	 * @param mediaAttributeKey
	 * @param mediaKey
	 * @return
	 */
	private MediaAttributeValue saveEmptyMediaAttributeValue(
			String mediaAttributeKey, String mediaKey) {
		Media media = (Media) mediaDAO.findById(Media.class, Integer
				.valueOf(mediaKey));
		MediaAttribute ma = (MediaAttribute) mediaAttributeDAO.findById(
				MediaAttribute.class, Integer.valueOf(mediaAttributeKey));
		MediaAttributeValueId mavId = new MediaAttributeValueId(ma
				.getMediaAttributeId(), media.getMediaId());
		MediaAttributeValue mav = new MediaAttributeValue(mavId, null,
				null, null, null, null, null, null, null);
		// saves the Media Object in the database
		mediaAttributeValueDAO.create(mav);
		return mav;
	}
	
	/** Are this methods private?*/
	

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#addProjectsList(java.lang.Integer, java.util.List)
	 */
	public void addProjectsList(String mediaKey, List<ProjectDTO> projectLiteList)
			throws IllegalArgumentException {
		logger.debug("addProjectsList, params: projectList.size["
				+ projectLiteList.size() + "], mediaId[" + mediaKey + "].");
		
		int successful = 0; // for local count of errors
		Media theMedia = null;
		Project theProject = null;
		MediaProjectId theMediaProjectId = null;
		MediaProject theMediaProject = null;

		theMedia = (Media) mediaDAO.findById(Media.class, Integer.valueOf(mediaKey));

		try{ 
		for (ProjectDTO pl : projectLiteList) {

			theProject = (Project) projectDAO.findById(Project.class, Integer.valueOf(pl.getProjectKey()));

			theMediaProjectId = new MediaProjectId(Integer.valueOf(mediaKey), (Integer) theProject
					.getProjectId());

			theMediaProject = new MediaProject(theMediaProjectId, theMedia,
					theProject);

			mediaProjectDAO.create(theMediaProject);
			successful++;
		}
		
		
		} catch(Exception e){
			throw new IllegalArgumentException("mal");
		}
		
		
		logger.debug("SetMediaProjects finish with " + successful
				+ " successful results.");

	}
	
	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#addKeywordsList(java.lang.Integer, java.util.List)
	 */
	public void addKeywordsList(String mediaKey, List<KeywordDTO> keywordsList)
			throws IllegalArgumentException {
		logger.debug("addKeywordsList, params: keywordsList.size["
				+ keywordsList.size() + "], mediaId[" + mediaKey + "].");

		int successful = 0; // for local count of errors
		// Taxon theTaxon;
		MediaKeywordId theMediaKeywordId;
		MediaKeyword theMediaKeyword;

		// sets the taxons of the MEDIA HibernateUtil.closeSession(attaSession);
		for (KeywordDTO klDTO : keywordsList) {
			logger.debug("Asociandolo con el keywordId=" + klDTO.getKeywordKey());
			theMediaKeyword = null;
			theMediaKeywordId = null;

			try {

				theMediaKeywordId = new MediaKeywordId(Integer.valueOf(mediaKey), new Integer(klDTO.getKeywordKey()));

				// sets the Taxon to the media
				theMediaKeyword = new MediaKeyword();
				theMediaKeyword.setId(theMediaKeywordId);

				mediaKeywordDAO.create(theMediaKeyword);

				successful = successful + 1;
				logger.info("The keyword id '" + klDTO.getKeywordKey()
						+ "'is set with the media with id '" + mediaKey + "'.");

			} catch (Exception he) {
				logger.error("Couldn't set the keyword id '" + klDTO.getKeywordKey()
						+ "' to the media with id '" + mediaKey + "'.");
			}

		} // for
		logger.info("SetMediaKeywords finish with " + successful
				+ " successful results.");

	}


	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#deleteKeywords(java.lang.Integer, java.util.List)
	 */
	public void deleteKeywords(String mediaKey, List<KeywordDTO> keywordsList)
			throws IllegalArgumentException {
		logger.debug("deleteKeywords, params: keywordsList.size["
				+ keywordsList.size() + "], mediaId[" + mediaKey + "].");

		int successful = 0; // for local count of errors

		// Taxon theTaxon;
		MediaKeywordId theMediaKeywordId;
		MediaKeyword theMediaKeyword;

		for (KeywordDTO klDTO : keywordsList) {
			logger.debug("deleting associated keywords...  deleting keyword:"+ klDTO.getKeywordKey());

			// theTaxon = null;
			theMediaKeyword = null;
			theMediaKeywordId = null;

			try {

				theMediaKeywordId = new MediaKeywordId(Integer.valueOf(mediaKey), new Integer(klDTO.getKeywordKey()));

				// sets the keywords to the media
				theMediaKeyword = new MediaKeyword();
				theMediaKeyword.setId(theMediaKeywordId);

				// saves changes and closes session
				mediaKeywordDAO.delete(theMediaKeyword);


				successful = successful + 1;
				logger.debug("deleting associated keywords... The keyword id '"
						+ klDTO.getKeywordKey() + "' is now deleted from media '" + mediaKey
						+ "'.");

			} catch (Exception e) {
				logger
						.error("deleting associated keywords... Couldn't delete the taxon id '"
								+ klDTO.getKeywordKey()
								+ "' to the media with id '"
								+ mediaKey
								+ "'.");
			}

		} // for
		logger.debug("deleting associated keywords... delete finish finish with "
				+ successful + " successful results.");
	}
	
	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#deleteProjects(java.lang.Integer, java.util.List)
	 */
	private void deleteProjects(String mediaKey, List<ProjectDTO> projectsList)
			throws IllegalArgumentException {
		logger.debug("deleteProjects, params: projectsList.size["
				+ projectsList.size() + "], mediaId[" + mediaKey + "].");

		int successful = 0; // for local count of errors

		MediaProjectId theMediaProjectId;
		MediaProject theMediaProject;

		for (ProjectDTO pDTO : projectsList) {
			logger.debug("deleting associated projects...  deleting project:"+ pDTO.getProjectKey());

			theMediaProject = null;
			theMediaProjectId = null;

			try {

				theMediaProjectId = new MediaProjectId(Integer.valueOf(mediaKey), new Integer(pDTO.getProjectKey()));

				// sets the keywords to the media
				theMediaProject = new MediaProject();
				theMediaProject.setId(theMediaProjectId);

				// saves changes and closes session
				mediaProjectDAO.delete(theMediaProject);


				successful = successful + 1;
				logger.debug("deleting associated projects... The project id '"
						+ pDTO.getProjectKey() + "' is now deleted from media '" + mediaKey
						+ "'.");

			} catch (Exception e) {
				logger
						.error("deleting associated projects... Couldn't delete the taxon id '"
								+ pDTO.getProjectKey()
								+ "' to the media with id '"
								+ mediaKey
								+ "'.");
			}

		} // for
		logger.debug("deleting associated projects... delete finish finish with "
				+ successful + " successful results.");


	}
	

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#addMediaUses(java.lang.Integer, java.util.List)
	 */
	public void addMediaUses(String mediaKey, List<MediaUseDTO> mediaUsesList)
			throws IllegalArgumentException {
		logger.debug("setting " + mediaUsesList.size() + " media uses for media:"
				+ mediaKey + ".");

		int successful = 0; // for local count of errors


		Media theMedia = null;
		MediaUse theMediaUse = null;
		MediaUseMedia theMediaUseMedia = null;
		MediaUseMediaId theMediaUseMediaId = null;

		try {
			theMedia = (Media) mediaDAO.findById(Media.class, Integer.valueOf(mediaKey));

			for (MediaUseDTO muDTO : mediaUsesList) {

				
				theMediaUse = (MediaUse) mediaUseDAO.findById(MediaUse.class, Integer.valueOf(muDTO.getMediaUseKey()));

				theMediaUseMediaId = new MediaUseMediaId(Integer.valueOf(mediaKey), (Integer) theMediaUse
						.getMediaUseId());

				theMediaUseMedia = new MediaUseMedia(theMediaUseMediaId, theMedia,
						theMediaUse, 'N');
				// theMediaUseMedia.setId(theMediaUseMediaId);
				// theMediaUseMedia.setMedia(theMedia);
				// theMediaUseMedia.setMediaUse(theMediaUse);
				// theMediaUseMedia.setApproved('N');

				mediaUseMediaDAO.create(theMediaUseMedia);

			} // for
			successful++;

		} catch (Exception e) {
			logger.error("Couldn't set the mediaUse id '"
					+ (Integer) theMediaUse.getMediaUseId() + "' to the media with id '"
					+ mediaKey + "'.");
		}

		logger.info("SetMediaUses finish with " + successful
				+ " successful results.");
	}
	
	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#addGatherings(java.lang.Integer, java.util.List)
	 */
	public void addGatherings(String mediaKey, List<GatheringLiteDTO> gatheringsList)
			throws IllegalArgumentException {
		logger.debug("addGatherings for media:" + mediaKey + ".");

		int successful = 0;
		GatheringMedia theGatheringMedia;
		GatheringMediaId theGatheringMediaId;
		PersonLiteDTO pLite;

		for (GatheringLiteDTO glDTO : gatheringsList) {
			pLite = agentManager.getGatheringResposibleLiteByName(glDTO.getResponsiblePersonName());
			logger.debug("GatheringNumber= " + glDTO.getGatheringKey()
					+ " y GatheringDetailPersonId= " + pLite.getPersonKey());

			// empty variables
			theGatheringMedia = null;
			theGatheringMediaId = null;

			try {

				theGatheringMediaId = new GatheringMediaId(Integer.valueOf(mediaKey), new Integer (pLite
						.getPersonKey()), new Integer(glDTO.getGatheringKey()));

				// sets the gatherings to the media
				theGatheringMedia = new GatheringMedia();
				theGatheringMedia.setId(theGatheringMediaId);

				// saves changes and closes session
				gatheringMediaDAO.create(theGatheringMedia);


				successful = successful + 1;
				logger.info("The gatheringNumber '" + glDTO.getGatheringKey()
						+ "' of the INBioPerson ID'" + pLite.getPersonKey()
						+ "'is set with the media with id '" + mediaKey + "'.");

			} catch (Exception he) {
				logger.error("Couldn't set The gatheringNumber '" + glDTO.getGatheringKey()
						+ "' of the INBioPerson ID'" + pLite.getPersonKey()
						+ "' to the media [id" + mediaKey + "].");
			}
		} // for
		logger.info("setAssociatedGatherings finish with " + successful
				+ " successful results.");


	}
	

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#addObservations(java.lang.Integer, java.util.List)
	 */
	public void addObservations(String mediaKey,
			List<ObservationLiteDTO> observationsList) throws IllegalArgumentException {
		logger.debug("setting " + observationsList.size()
				+ " associated observations for media:" + mediaKey + ".");

		int successful = 0;
		ObservedTaxonMedia theOTM;
		ObservedTaxonMediaId theOTMId;

		// sets the ObservedTaxons of the MEDIA
		for(ObservationLiteDTO olDTO : observationsList) {
			logger.debug("con observationsList=" + olDTO.getObservationKey());

			// empty variables
			theOTM = null;
			theOTMId = null;

			try {

				// observationTaxonMediaId
				theOTMId = new ObservedTaxonMediaId(new Integer(olDTO.getObservationKey()), Integer.valueOf(mediaKey));

				theOTM = new ObservedTaxonMedia();
				theOTM.setId(theOTMId);

				observationMediaDAO.create(theOTM);

				successful = successful + 1;

			} catch (Exception he) {
				logger.error("Couldn't set the Observation["
						+ olDTO.getObservationKey()	+ "] to the media [id" + mediaKey + "].");
			}

		} // for
		logger.info("setObservationMedia finish with " + successful
				+ " successful results.");


	}


	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#addSpecimens(java.lang.Integer, java.util.List)
	 */
	public void addSpecimens(String mediaKey, List<SpecimenLiteDTO> specimensList)
			throws IllegalArgumentException {
		logger.debug("setting associated specimens for media:" + mediaKey + ".");

		int successful = 0;
		SpecimenMedia theSpecimenMedia;
		SpecimenMediaId theSpecimenMediaId;

		for (SpecimenLiteDTO slDTO : specimensList) {

			logger.debug("con SpecimenId=" + slDTO.getSpecimenKey());
			// empty variables
			theSpecimenMedia = null;
			theSpecimenMediaId = null;

			try {

				theSpecimenMediaId = new SpecimenMediaId(new Integer(slDTO.getSpecimenKey()), Integer.valueOf(mediaKey));

				// sets the Specimen to the media
				theSpecimenMedia = new SpecimenMedia();
				theSpecimenMedia.setId(theSpecimenMediaId);

				// saves changes and closes session

				specimenMediaDAO.create(theSpecimenMedia);


				successful = successful + 1;
				logger.debug("The Specimen id '" + slDTO.getSpecimenKey()
						+ "'is set with the media with id '" + mediaKey + "'.");

			} catch (Exception e) {
				logger.error("Couldn't set the Speciemen [id=" + slDTO.getSpecimenKey()
						+ "] to the media [id" + mediaKey + "].");
			}

		} // for
		logger.debug("SetSpecimenMedia finish with " + successful
				+ " successful results.");


	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#addTaxons(java.lang.Integer, java.util.List)
	 */
	public void addTaxons(String mediaKey, List<TaxonLiteDTO> taxonsList)
			throws IllegalArgumentException {
		logger.debug("setting " + taxonsList.size()
				+ " associated taxons for media:" + mediaKey + ".");

		int successful = 0; // for local count of errors

		// Taxon theTaxon;
		TaxonMedia theTaxonMedia;
		TaxonMediaId theTaxonMediaId;

		// sets the taxons of the MEDIA HibernateUtil.closeSession(attaSession);
		// for (int i = 0; i < taxonsList.size(); i++) {
		for (TaxonLiteDTO tlDTO : taxonsList) {
			logger.debug("Asociandolo con el TaxonId=" + tlDTO.getTaxonKey());
			theTaxonMedia = null;
			theTaxonMediaId = null;

			try {
				theTaxonMediaId = new TaxonMediaId(new Integer(tlDTO.getTaxonKey()), Integer.valueOf(mediaKey));

				// sets the Taxon to the media
				theTaxonMedia = new TaxonMedia();
				theTaxonMedia.setId(theTaxonMediaId);

				// saves changes and closes session
				taxonMediaDAO.create(theTaxonMedia);

				successful = successful + 1;
				logger.debug("The taxon id '" + tlDTO.getTaxonKey()
						+ "'is set with the media with id '" + mediaKey + "'.");

			} catch (Exception he) {
				logger.error("Couldn't set the taxon id '" + tlDTO.getTaxonKey()
						+ "' to the media with id '" + mediaKey + "'.");
			}
		} // for
		logger.info("SetTaxonMedias finish with " + successful
				+ " successful results.");

	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#deleteGatherings(java.lang.Integer, java.util.List)
	 */
	public void deleteGatherings(String mediaKey,
			List<GatheringLiteDTO> gatheringsList) throws IllegalArgumentException {
		logger.debug("deleteAssociatedGatherings for media:" + mediaKey + ".");

		int successful = 0;
		GatheringMedia oldGatheringMedia;
		GatheringMediaId oldGatheringMediaId;
		PersonLiteDTO pLite;

		// deleteds the previoulsy associated gatherings of the MEDIA
		for (GatheringLiteDTO glDTO : gatheringsList) {
			logger.debug("deleteAssociatedGatherings... GatheringNumber="
					+ glDTO.getGatheringKey());
			pLite = agentManager.getGatheringResposiblePersonLite(glDTO.getResponsiblePersonName());
			// empty variables
			oldGatheringMedia = null;
			oldGatheringMediaId = null;

			try {
				
				oldGatheringMediaId = new GatheringMediaId(Integer.valueOf(mediaKey), new Integer(pLite
						.getPersonKey()), new Integer(glDTO.getGatheringKey()));

				// sets the Gathering to the media
				oldGatheringMedia = new GatheringMedia();
				oldGatheringMedia.setId(oldGatheringMediaId);

				// saves changes and closes session
				gatheringMediaDAO.delete(oldGatheringMedia);

				successful = successful + 1;
				logger.info("The GatheringNumber '" + glDTO.getGatheringKey()
						+ "' of userId '" + pLite.getPersonKey()
						+ "' was deleted from the media '" + mediaKey + "'.");

			} catch (Exception he) {
				logger.error("Couldn't delet The GatheringNumber '"
						+ glDTO.getGatheringKey() + "' of userId '" + pLite.getPersonKey()
						+ "' from the media [id" + mediaKey + "].");

			}
		} // for
		logger.info("deleteAssociatedGatherings finish with " + successful
				+ " successful deletes.");
	}


	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#deleteMediaUses(java.lang.Integer, java.util.List)
	 */
	public void deleteMediaUses(String mediaKey, List<MediaUseDTO> mediaUsesList)
			throws IllegalArgumentException {
		logger.debug("deleting media uses... " + mediaUsesList.size()
				+ " media uses for media:" + mediaKey + ".");

		int successful = 0; // for local count of errors

		Media m = null;
		MediaUse mu = null;
		MediaUseMedia mum = null;
		MediaUseMediaId mumId = null;

		try {
			m = (Media) mediaDAO.findById(Media.class, Integer.valueOf(mediaKey));

			// deletes the uses of the MEDIA
			for (MediaUseDTO muDTO : mediaUsesList) {

				mu = (MediaUse) mediaUseDAO.findById(MediaUse.class, Integer.valueOf(muDTO.getMediaUseKey()));

				mumId = new MediaUseMediaId(m.getMediaId(), mu.getMediaUseId());

				mum = (MediaUseMedia) mediaUseMediaDAO.findById(MediaUseMedia.class, mumId);

				mediaUseMediaDAO.delete(mum);
				successful++;

			} // for

		} catch (Exception he) {
			logger.error(he.getMessage());
			logger.error("deleting media uses... Couldn't delete the mediaUse id '"
					+ (Integer) mu.getMediaUseId() + "' to the media with id '" + mediaKey
					+ "'.");
		}
		logger.info("deleting media uses... finish with " + successful
				+ " successfully deleted registries.");
	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#deleteObservations(java.lang.Integer, java.util.List)
	 */
	public void deleteObservations(String mediaKey,
			List<ObservationLiteDTO> observationsList) throws IllegalArgumentException {
		logger.debug("deleting associated observations for media:" + mediaKey
				+ ".");

		int successful = 0;

		ObservedTaxonMedia oldOTM;
		ObservedTaxonMediaId oldOTMId;

		for(ObservationLiteDTO olDTO : observationsList) {
			logger
					.debug("deleting associated observations... con observationsList="
							+ olDTO.getObservationKey());

			// empty variables
			oldOTM = null;
			oldOTMId = null;

			try {

				// observationTaxonMediaId
				oldOTMId = new ObservedTaxonMediaId(new Integer(olDTO.getObservationKey()), Integer.valueOf(mediaKey));

				oldOTM = new ObservedTaxonMedia();
				oldOTM.setId(oldOTMId);

				observationMediaDAO.delete(oldOTM);


				successful = successful + 1;

			} catch (Exception he) {
				logger.error("deleting associated observations... "
						+ "Couldn't delete the Observation["
						+ olDTO.getObservationKey()
						+ "] from the media [id" + mediaKey + "].");
			}

		} // for
		logger.debug("deleting associated observations... finish with "
				+ successful + " successful deleted registries.");


	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#deleteSpecimens(java.lang.Integer, java.util.List)
	 */
	public void deleteSpecimens(String mediaKey, List<SpecimenLiteDTO> specimensList)
			throws IllegalArgumentException {
		logger.debug("deleting associated specimens for media:" + mediaKey + ".");

		int successful = 0;
		SpecimenMedia oldSpecimenMedia;
		SpecimenMediaId oldSpecimenMediaId;

		// deleteds the previoulsy associated specimens of the MEDIA
		for(SpecimenLiteDTO slDTO : specimensList) {
			logger.debug("deleting associated specimens... SpecimenId=" + slDTO.getSpecimenKey());
			// empty variables
			oldSpecimenMedia = null;
			oldSpecimenMediaId = null;

			try {

				oldSpecimenMediaId = new SpecimenMediaId(
						new Integer(slDTO.getSpecimenKey()), Integer.valueOf(mediaKey));

				// sets the Specimen to the media
				oldSpecimenMedia = new SpecimenMedia();
				oldSpecimenMedia.setId(oldSpecimenMediaId);

				// saves changes and closes session
				specimenMediaDAO.delete(oldSpecimenMedia);

				successful = successful + 1;
				logger.debug("The Specimen id '" + slDTO.getSpecimenKey()
						+ "' was deleted from the media '" + mediaKey + "'.");

			} catch (Exception he) {
				logger.error("Couldn't delet the Speciemen [id="
						+ slDTO.getSpecimenKey() + "] from the media [id" + mediaKey	+ "].");

			}
		} // for
		logger.info("deleteAssociatedSpecimens finish with " + successful
				+ " successful deletes.");


	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#deleteTaxons(java.lang.Integer, java.util.List)
	 */
	public void deleteTaxons(String mediaKey, List<TaxonLiteDTO> taxonsList)
			throws IllegalArgumentException {
		logger.debug("deleting [" + taxonsList.size()
				+ "] associated taxons for media:" + mediaKey + ".");

		int successful = 0; // for local count of errors

		// Taxon theTaxon;
		TaxonMediaId theTaxonMediaId;
		TaxonMedia theTaxonMedia;

		// deleteds the previoulsy associated taxons of the MEDIA
		// for (int i = 0; i < oldAssociatedTaxonomy.size(); i++) {
		for (TaxonLiteDTO tlDTO : taxonsList) {
			logger.debug("deleting associated taxons...  deleting taxon:"
					+ tlDTO.getTaxonKey());

			// theTaxon = null;
			theTaxonMedia = null;
			theTaxonMediaId = null;

			try {

				theTaxonMediaId = new TaxonMediaId(new Integer(tlDTO.getTaxonKey()), Integer.valueOf(mediaKey));

				// sets the Taxon to the media
				theTaxonMedia = new TaxonMedia();
				theTaxonMedia.setId(theTaxonMediaId);

				// saves changes and closes session
				taxonMediaDAO.delete(theTaxonMedia);

				successful = successful + 1;
				logger.info("deleting associated taxons... The taxon id '"
						+ tlDTO.getTaxonKey() + "' is now deleted from media '" + mediaKey
						+ "'.");

			} catch (Exception he) {
				logger
						.error("deleting associated taxons... Couldn't delete the taxon id '"
								+ tlDTO.getTaxonKey() + "' to the media with id '" + mediaKey + "'.");
			}
		} // for
		logger.info("deleting associated taxons... delete finish finish with "
				+ successful + " successful results.");
		// return successful;


	}
	

	/**
	 * @param mediaDAO
	 *          the mediaDAO to set
	 */
	public void setMediaDAO(MediaDAO mediaDAO) {
		this.mediaDAO = mediaDAO;
	}

	/**
	 * @return the mediaDAO
	 */
	public MediaDAO getMediaDAO() {
		return mediaDAO;
	}

	/**
	 * @param mediaAttributeDAO
	 *          the mediaAttributeDAO to set
	 */
	public void setMediaAttributeDAO(MediaAttributeDAO mediaAttributeDAO) {
		this.mediaAttributeDAO = mediaAttributeDAO;
	}

	/**
	 * @return the mediaAttributeDAO
	 */
	public MediaAttributeDAO getMediaAttributeDAO() {
		return mediaAttributeDAO;
	}

	/**
	 * @param textTranslationDAO
	 *          the textTranslationDAO to set
	 */
	public void setTextTranslationDAO(TextTranslationDAO textTranslationDAO) {
		this.textTranslationDAO = textTranslationDAO;
	}

	/**
	 * @return the textTranslationDAO
	 */
	public TextTranslationDAO getTextTranslationDAO() {
		return textTranslationDAO;
	}

	/**
	 * @return the mediaAttributeTypeDAO
	 */
	public MediaAttributeTypeDAO getMediaAttributeTypeDAO() {
		return mediaAttributeTypeDAO;
	}

	/**
	 * @param mediaAttributeTypeDAO
	 *          the mediaAttributeTypeDAO to set
	 */
	public void setMediaAttributeTypeDAO(
			MediaAttributeTypeDAO mediaAttributeTypeDAO) {
		this.mediaAttributeTypeDAO = mediaAttributeTypeDAO;
	}

	/**
	 * @return the metadataExtractorDAO
	 */
	public MetadataExtractorDAO getMetadataExtractorDAO() {
		return metadataExtractorDAO;
	}

	/**
	 * @param metadataExtractorDAO
	 *          the metadataExtractorDAO to set
	 */
	public void setMetadataExtractorDAO(MetadataExtractorDAO metadataExtractorDAO) {
		this.metadataExtractorDAO = metadataExtractorDAO;
	}

	/**
	 * @return the mediaAttributeValueDAO
	 */
	public MediaAttributeValueDAO getMediaAttributeValueDAO() {
		return mediaAttributeValueDAO;
	}

	/**
	 * @param mediaAttributeValueDAO
	 *          the mediaAttributeValueDAO to set
	 */
	public void setMediaAttributeValueDAO(
			MediaAttributeValueDAO mediaAttributeValueDAO) {
		this.mediaAttributeValueDAO = mediaAttributeValueDAO;
	}

	/**
	 * @return the projectDAO
	 */
	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	/**
	 * @param projectDAO the projectDAO to set
	 */
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	/**
	 * @return the keywordDAO
	 */
	public KeywordDAO getKeywordDAO() {
		return keywordDAO;
	}

	/**
	 * @param keywordDAO the keywordDAO to set
	 */
	public void setKeywordDAO(KeywordDAO keywordDAO) {
		this.keywordDAO = keywordDAO;
	}

	/**
	 * @return the projectDTOFactory
	 */
	public ProjectDTOFactory getProjectDTOFactory() {
		return projectDTOFactory;
	}

	/**
	 * @param projectDTOFactory the projectDTOFactory to set
	 */
	public void setProjectDTOFactory(ProjectDTOFactory projectDTOFactory) {
		this.projectDTOFactory = projectDTOFactory;
	}

	/**
	 * @return the taxonomyManager
	 */
	public TaxonomyManager getTaxonomyManager() {
		return taxonomyManager;
	}

	/**
	 * @param taxonomyManager the taxonomyManager to set
	 */
	public void setTaxonomyManager(TaxonomyManager taxonomyManager) {
		this.taxonomyManager = taxonomyManager;
	}

	/**
	 * @return the mediaProjectDAO
	 */
	public MediaProjectDAO getMediaProjectDAO() {
		return mediaProjectDAO;
	}

	/**
	 * @param mediaProjectDAO the mediaProjectDAO to set
	 */
	public void setMediaProjectDAO(MediaProjectDAO mediaProjectDAO) {
		this.mediaProjectDAO = mediaProjectDAO;
	}

	/**
	 * @return the mediaKeywordDAO
	 */
	public MediaKeywordDAO getMediaKeywordDAO() {
		return mediaKeywordDAO;
	}

	/**
	 * @param mediaKeywordDAO the mediaKeywordDAO to set
	 */
	public void setMediaKeywordDAO(MediaKeywordDAO mediaKeywordDAO) {
		this.mediaKeywordDAO = mediaKeywordDAO;
	}

	/**
	 * @return the mediaUseDAO
	 */
	public MediaUseDAO getMediaUseDAO() {
		return mediaUseDAO;
	}

	/**
	 * @param mediaUseDAO the mediaUseDAO to set
	 */
	public void setMediaUseDAO(MediaUseDAO mediaUseDAO) {
		this.mediaUseDAO = mediaUseDAO;
	}

	/**
	 * @return the mediaUseMediaDAO
	 */
	public MediaUseMediaDAO getMediaUseMediaDAO() {
		return mediaUseMediaDAO;
	}

	/**
	 * @param mediaUseMediaDAO the mediaUseMediaDAO to set
	 */
	public void setMediaUseMediaDAO(MediaUseMediaDAO mediaUseMediaDAO) {
		this.mediaUseMediaDAO = mediaUseMediaDAO;
	}

	/**
	 * @return the taxonMediaDAO
	 */
	public TaxonMediaDAO getTaxonMediaDAO() {
		return taxonMediaDAO;
	}

	/**
	 * @param taxonMediaDAO the taxonMediaDAO to set
	 */
	public void setTaxonMediaDAO(TaxonMediaDAO taxonMediaDAO) {
		this.taxonMediaDAO = taxonMediaDAO;
	}

	/**
	 * @return the gatheringMediaDAO
	 */
	public GatheringMediaDAO getGatheringMediaDAO() {
		return gatheringMediaDAO;
	}

	/**
	 * @param gatheringMediaDAO the gatheringMediaDAO to set
	 */
	public void setGatheringMediaDAO(GatheringMediaDAO gatheringMediaDAO) {
		this.gatheringMediaDAO = gatheringMediaDAO;
	}

	/**
	 * @return the specimenMediaDAO
	 */
	public SpecimenMediaDAO getSpecimenMediaDAO() {
		return specimenMediaDAO;
	}

	/**
	 * @param specimenMediaDAO the specimenMediaDAO to set
	 */
	public void setSpecimenMediaDAO(SpecimenMediaDAO specimenMediaDAO) {
		this.specimenMediaDAO = specimenMediaDAO;
	}

	/**
	 * @return the observationMediaDAO
	 */
	public ObservationMediaDAO getObservationMediaDAO() {
		return observationMediaDAO;
	}

	/**
	 * @param observationMediaDAO the observationMediaDAO to set
	 */
	public void setObservationMediaDAO(ObservationMediaDAO observationMediaDAO) {
		this.observationMediaDAO = observationMediaDAO;
	}

	/**
	 * @return the agentManager
	 */
	public AgentManager getAgentManager() {
		return agentManager;
	}

	/**
	 * @param agentManager the agentManager to set
	 */
	public void setAgentManager(AgentManager agentManager) {
		this.agentManager = agentManager;
	}


}
