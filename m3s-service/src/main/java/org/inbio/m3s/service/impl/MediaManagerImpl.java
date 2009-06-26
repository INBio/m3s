/**
 * 
 */
package org.inbio.m3s.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.m3s.model.core.GatheringMedia;
import org.inbio.m3s.model.core.GatheringMediaId;
import org.inbio.m3s.model.core.Media;
import org.inbio.m3s.model.core.MediaKeyword;
import org.inbio.m3s.model.core.MediaKeywordId;
import org.inbio.m3s.model.core.MediaProject;
import org.inbio.m3s.model.core.MediaProjectId;
import org.inbio.m3s.model.core.MediaType;
import org.inbio.m3s.model.core.MediaUse;
import org.inbio.m3s.model.core.MediaUseMedia;
import org.inbio.m3s.model.core.MediaUseMediaId;
import org.inbio.m3s.model.core.ObservedTaxonMediaId;
import org.inbio.m3s.model.core.ObservedTaxonMedia;
import org.inbio.m3s.model.core.Project;
import org.inbio.m3s.model.core.SpecimenMedia;
import org.inbio.m3s.model.core.SpecimenMediaId;
import org.inbio.m3s.model.core.TaxonMedia;
import org.inbio.m3s.model.core.TaxonMediaId;
import org.inbio.m3s.model.core.UsePolicy;
import org.inbio.m3s.dao.core.GatheringMediaDAO;
import org.inbio.m3s.dao.core.KeywordDAO;
import org.inbio.m3s.dao.core.MediaAttributeDAO;
import org.inbio.m3s.dao.core.MediaAttributeValueDAO;
import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.dao.core.MediaKeywordDAO;
import org.inbio.m3s.dao.core.MediaProjectDAO;
import org.inbio.m3s.dao.core.MediaTypeDAO;
import org.inbio.m3s.dao.core.MediaUseDAO;
import org.inbio.m3s.dao.core.MediaUseMediaDAO;
import org.inbio.m3s.dao.core.ObservationMediaDAO;
import org.inbio.m3s.dao.core.ProjectDAO;
import org.inbio.m3s.dao.core.SpecimenMediaDAO;
import org.inbio.m3s.dao.core.TaxonMediaDAO;
import org.inbio.m3s.dao.core.UsePolicyDAO;
import org.inbio.m3s.dto.GeneralMetadataDTO;
import org.inbio.m3s.dto.metadata.TechnicalMetadataDTO;
import org.inbio.m3s.dto.UsesAndCopyrightsDTO;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.lite.MediaUseLite;
import org.inbio.m3s.dto.lite.ProjectLite;
import org.inbio.m3s.dto.message.KeywordLiteDTO;
import org.inbio.m3s.dto.taxonomy.GatheringLiteDTO;
import org.inbio.m3s.dto.taxonomy.ObservationLiteDTO;
import org.inbio.m3s.dto.taxonomy.SpecimenLiteDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.MediaAttributeManager;
import org.inbio.m3s.service.MediaManager;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.service.MetadataManager;
import org.inbio.m3s.service.TaxonomyManager;

/**
 * @author jgutierrez
 *
 */
public class MediaManagerImpl implements MediaManager {

	private static Logger logger = Logger.getLogger(MediaManagerImpl.class);
	
	private MediaDAO mediaDAO;
	private ProjectDAO projectDAO;
	private MediaAttributeDAO mediaAttributeDAO;
	private MediaAttributeValueDAO mediaAttributeValueDAO;
	private MediaProjectDAO mediaProjectDAO;
	private MediaKeywordDAO mediaKeywordDAO;
	private MediaUseDAO mediaUseDAO;
	private MediaUseMediaDAO mediaUseMediaDAO;
	private MediaTypeDAO mediaTypeDAO;
	private TaxonMediaDAO taxonMediaDAO;
	private GatheringMediaDAO gatheringMediaDAO; 
	private SpecimenMediaDAO specimenMediaDAO;
	private ObservationMediaDAO observationMediaDAO;
	private UsePolicyDAO usePolicyDAO;
	private MediaAttributeManager mediaAttributeManager;
	private KeywordDAO keywordDAO;
	
	private AgentManager agentManager;
	private TaxonomyManager taxonomyManager;
	private MetadataManager metadataManager;

	
	/**
	 * Loads the uac of the storedMedia and compares each element to update only
	 * what has changed
	 * 
	 * @param newUAC
	 */
	public void updateUACM(UsesAndCopyrightsDTO newUAC) {
		logger.info("updating the UACM... mediaId [" + newUAC.getMediaId()
				+ "]");

		UsesAndCopyrightsDTO DBUAC = getUACM(newUAC.getMediaId());
	
		logger.debug("updating the UACM... Iguales? " + DBUAC.equals(newUAC));
		if (DBUAC.equals(newUAC)) {
			logger.debug("updating the UACM... nothing to update");
			logger.info("updating the UACM... done");
			return;
		}

		Media theMedia;		

		try {
			theMedia = (Media) mediaDAO.findById(Media.class, newUAC.getMediaId());

			if (DBUAC.getAuthorId().equals(newUAC.getAuthorId()) == false) {
				logger.debug("updating the UACM... updating authorId");
				theMedia.setAuthorPersonId(newUAC.getAuthorId());
			}
			logger.debug("updating the UACM... updating author DONE");

			logger.debug("updating the UACM... updating Owners");
			theMedia.setOwnerInstitutionId(newUAC.getInstitutionOwnerId());
			theMedia.setOwnerPersonId(newUAC.getPersonOwnerId());
			logger.debug("updating the UACM... updating Owners DONE");

			if (DBUAC.getUsePolicyID().equals(newUAC.getUsePolicyID()) == false) {
				logger.debug("updating the UACM... updating use policy");
				UsePolicy theUsePolicy = (UsePolicy) usePolicyDAO.findById(UsePolicy.class, newUAC.getUsePolicyID());
				theMedia.setUsePolicy(theUsePolicy);
			}
			logger.debug("updating the UACM... updating use policy DONE");

			// list of integers
			if(DBUAC.getMediaUsesList().equals(newUAC.getMediaUsesList()) == false) {
						
				logger.debug("updating the UACM... updating media uses");

				deleteMediaUses(newUAC.getMediaId(), newUAC.getMediaUsesList());

				if (newUAC.getMediaUsesList() != null) {
					addMediaUses(newUAC.getMediaId(), newUAC.getMediaUsesList());
				}
			}
			logger.debug("updating the UACM... updating media uses DONE");

			// isBackup;
			if (DBUAC.getIsBackup().equals(newUAC.getIsBackup()) == false) {
				logger.debug("updating the UACM... updating is backup");
				theMedia.setIsBackup(newUAC.getIsBackup());
			}
			logger.debug("updating the UACM... updating is backup DONE");

			// isPublic;
			if (DBUAC.getIsPublic().equals(newUAC.getIsPublic()) == false) {
				logger.debug("updating the UACM... updating is public");
				theMedia.setIsPublic(newUAC.getIsPublic());
			}
			logger.debug("updating the UACM... updating is public DONE");

			// updates the Media Object in the database
			mediaDAO.update(theMedia);

		} catch (Exception he) {
			logger.error("There was a hibernate exeption in updateUACM");
			logger.error(he.getMessage());
			throw new IllegalArgumentException("fails on updateUACM", he);
		} 

		logger.info("updating the UAC... done");

	}
	
	/**
	 * Loads the gm of the storedMedia and compares each element to update only
	 * what has changed
	 * 
	 * @param newGm
	 */
	public void updateGM(GeneralMetadataDTO newGm)
			throws IllegalArgumentException {
		logger.info("updating the GM... mediaId [" + newGm.getMediaId() + "]");
		GeneralMetadataDTO DBGm = getGM(newGm.getMediaId());

		logger.debug("updating the GM... Iguales? " + DBGm.equals(newGm));
		if (DBGm.equals(newGm)) {
			logger.debug("updating the GM... nothing to update");
			logger.info("updating the GM... done");
			return;
		}
		logger.debug("updating the GM... init the update");
	
		Media theMedia;

		try {
			theMedia = (Media) mediaDAO.findById(Media.class, newGm.getMediaId());

			logger.debug("updating the GM... 00");
			if (DBGm.getTitle() == null
					|| (DBGm.getTitle().equals(newGm.getTitle()) == false)) {
				logger.debug("updating the GM... updating title");
				theMedia.setTitle(newGm.getTitle());
			}

			logger.debug("updating the GM... 01");
			if (DBGm.getDescription() == null
					|| (DBGm.getDescription().equals(newGm.getDescription()) == false)) {
				logger.debug("updating the GM... updating description");
				theMedia.setDescription(newGm.getDescription());
			}

			logger.debug("updating the GM... 02");
			if (DBGm.getMediaTypeId().equals(newGm.getMediaTypeId()) == false) {
				logger.debug("updating the GM... updating mediaType");
				MediaType theMediaType = (MediaType) mediaTypeDAO.findById(MediaType.class, newGm.getMediaTypeId());
				theMedia.setMediaType(theMediaType);
			}

			logger.debug("updating the GM... 03");
			if (DBGm.getAssociatedSpecimensList().equals(
					newGm.getAssociatedSpecimensList()) == false) {
				logger.debug("updating the GM... updating AssociatedSpecimens");
				deleteSpecimens(newGm.getMediaId(), DBGm.getAssociatedSpecimensList());
				if (newGm.getAssociatedSpecimensList() != null) {
					addSpecimens(newGm.getMediaId(), newGm.getAssociatedSpecimensList());
				}
			}

			logger.debug("updating the GM... 04");
			if (DBGm.getAssociatedObservationsList().equals(
					newGm.getAssociatedObservationsList()) == false) {
				logger
						.debug("updating the GM... updating AssociatedObservations");
				deleteObservations(newGm.getMediaId(), DBGm.getAssociatedObservationsList());
				if (newGm.getAssociatedObservationsList() != null) {
					addObservations(newGm.getMediaId(), newGm.getAssociatedObservationsList());
				}
			}

			logger.debug("updating the GM... 04.1");
			if(DBGm.getAssociatedGatheringsList().equals(newGm.getAssociatedGatheringsList()) == false){
				logger.debug("updating the GM... updating getAssociatedGatherings");
				deleteGatherings(newGm.getMediaId(), DBGm.getAssociatedGatheringsList());
				if (newGm.getAssociatedGatheringsList() != null) {
					addGatherings(newGm.getMediaId(), newGm.getAssociatedGatheringsList());
				}
			}

			logger.debug("updating the GM... 05");
			if (DBGm.getTaxonsList().equals(newGm.getTaxonsList()) == false) {
				logger.debug("updating the GM... updating taxonomy");

				deleteTaxons(newGm.getMediaId(), DBGm.getTaxonsList());
				if (newGm.getTaxonsList() != null) {
					addTaxons(newGm.getMediaId(), newGm.getTaxonsList());
				}

			}

			// TODO: set the series

			logger.debug("updating the GM... 06 - keywords");
			// keywords
			if(DBGm.getKeywordsList().equals(newGm.getKeywordsList()) == false) {
				logger.debug("updating the GM... updating keywords");
				deleteKeywords(newGm.getMediaId(), DBGm.getKeywordsList());
				if (newGm.getKeywordsList() != null) {
					addKeywordsList(newGm.getMediaId(), newGm.getKeywordsList());
				}
			}

			// TODO: how to deal with the siteID
			// theMedia.setSiteId(gm.getSiteId());
			// logger.info("seting GeneralMetadata... SiteId set");

			logger.debug("updating the GM... 07");
			if (DBGm.getSiteDescription() == null
					|| (DBGm.getSiteDescription().equals(
							newGm.getSiteDescription()) == false)) {
				logger.debug("updating the GM... updating site description");
				theMedia.setSiteDescription(newGm.getSiteDescription());
			}

			logger.debug("updating the GM... execute the update");
			// updates the Media Object in the database
			mediaDAO.update(theMedia);

		} catch (Exception he) {
			logger.error("There was a hibernate exeption in updateGM");
			logger.error(he.getMessage());
			throw new IllegalArgumentException("fails on updateGM", he);
		}

		logger.info("updating the GM... done");
	}
	
	
	/**
	 * Load an objecto of usesAndCopyrigths type
	 * 
	 * @return UsesAndCopyrightsDTO
	 */
	public UsesAndCopyrightsDTO getUACM(Integer mediaId) throws IllegalArgumentException {
		logger.info("getting UACM... for media [" + mediaId + "].");
		UsesAndCopyrightsDTO uac = new UsesAndCopyrightsDTO();
		uac = mediaDAO.getUsesAndCopyrightsDTO(mediaId);
		uac.setMediaId(mediaId);

		
		try {
			// makes the other queries to complete the metadata			
			uac.setMediaUsesList(mediaUseDAO.getMediaUsesLite(mediaId, MessageManager.DEFAULT_LANGUAGE));

			logger.info("getting UACM... done");
			return uac;

		} catch (Exception he) {
			logger.error("There was a hibernate exeption in the query");
			throw new IllegalArgumentException("Query fails on getUACM", he);
		}
	}
	
	/**
	 * Creates a new General Metadata object based on the information of the
	 * mediaId that was given in the constructor
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 */
	public GeneralMetadataDTO getGM(Integer mediaId)
			throws IllegalArgumentException {
		logger.info("getting GM... mediaId [" + mediaId + "]");
		
		
		GeneralMetadataDTO gm = new GeneralMetadataDTO();
		
		try {
			gm = mediaDAO.getGeneralMetadataDTO(mediaId);
			gm.setMediaId(mediaId);

			// makes the other queries to complete the metadata
			List<SpecimenLiteDTO> slDTOList = taxonomyManager.getAllSpecimensLiteForMedia(mediaId.toString());
			gm.setAssociatedSpecimensList(slDTOList);
			List<ObservationLiteDTO> observationsList = taxonomyManager.getAllObservationsLiteForMedia(mediaId.toString());
			gm.setAssociatedObservationsList(observationsList);
			
			List<GatheringLiteDTO> glDTOList = taxonomyManager.getAllGatheringsLiteForMedia(mediaId.toString());
			gm.setAssociatedGatheringsList(glDTOList);
	
			// projects
			List<ProjectLite> projectLiteList = null;
			projectLiteList = projectDAO.getAllProjectLiteForMedia(mediaId);
			gm.setProjectsList(projectLiteList);

			// TODO: setSeries
			// TODO: setSynapticCollections
			
			// setKeyWords
			List<KeywordLiteDTO> keywordLiteList = null;
			keywordLiteList = keywordDAO.getAllKeywordLiteForMedia(mediaId, new Integer(MessageManager.DEFAULT_LANGUAGE));
			gm.setKeywordsList(keywordLiteList);
				
			//taxons
			List<TaxonLiteDTO> taxonLiteList = taxonomyManager.getTaxonLiteForMediaId(mediaId.toString());
			gm.setTaxonsList(taxonLiteList);

			logger.info("getting GM... done");
			return gm;

		} catch (Exception he) {
			logger.error("There was a hibernate exeption in the query");
			throw new IllegalArgumentException("Query fails on getGM", he);
		}

	}
	
	/**
	 * This method should save the metadata asociated with every Media Also, is
	 * parte of their functionality to set everything ok in the server in order
	 * to be ready to serve the images using the ImageDispatcherServelet
	 * 
	 * This method first try to insert the General and UAC Metadata that
	 * contains some obligatory elements of the table 'MEDIA', that's why this
	 * method needs the data from the GeneralMetadataDTO and the UsesAndCopyrightsDTO
	 * objects. Then when the core elements are saved, invokes the setGM, setUAC
	 * and setTM methods to complete the insertion. the not null elements to be
	 * inserted here are: mediaType, usePolicy, isPublic and isBackup
	 * 
	 * @return the mediaId
	 * @throws IllegalArgumentException
	 */
	public Integer insertNewMedia(GeneralMetadataDTO gm,
			UsesAndCopyrightsDTO uac, TechnicalMetadataDTO tmDTO)
			throws IllegalArgumentException {

		Media theMedia = new Media();

		// sets backup and public properties of the media
		theMedia.setIsBackup(uac.getIsBackup());
		theMedia.setIsPublic(uac.getIsPublic());

		try {

			// sets the Media elements that need DB conection
			MediaType theMediaType = (MediaType) mediaTypeDAO.findById(MediaType.class,gm.getMediaTypeId());
			theMedia.setMediaType(theMediaType);

			UsePolicy theUsePolicy = (UsePolicy) usePolicyDAO.findById(UsePolicy.class,uac.getUsePolicyID());
			theMedia.setUsePolicy(theUsePolicy);

			// saves the Media Object in the database
			mediaDAO.create(theMedia);


		} catch (Exception he) {
			logger.error("There was a hibernate exeption in the insertMedia");
			logger.error(he.getMessage());
			throw new IllegalArgumentException("Query fails on insertMedia", he);
		}

		logger.info("El multimedio nuevo tendria id de>" + theMedia.getMediaId() + "<");

		// saves the rest of metadata
		try {
			gm.setMediaId(theMedia.getMediaId());
			insertGM(gm);
			uac.setMediaId(theMedia.getMediaId());
			insertUACM(uac);
			tmDTO.setMediaKey(String.valueOf(theMedia.getMediaId()));
	    metadataManager.saveTechnicalMetadata(tmDTO);
		} catch (IllegalArgumentException iae) {
			logger.error("The insert was involved in erorrs");
			throw new IllegalArgumentException("Not valid arguments.", iae);
		}

		logger.info("Todo listo y pura vida con el multimedio>"
				+ theMedia.getMediaId() + "<");
		return theMedia.getMediaId();

	}
	
	/**
	 * Sets all the general metadata of the media.
	 * 
	 * @param gm
	 */
	private Integer insertGM(GeneralMetadataDTO gm) throws IllegalArgumentException {
		logger.info("inserting the GeneralMetadataDTO Info for the media>"
				+ gm.getMediaId() + "<");

		Media theMedia;

		try {

			theMedia = (Media) mediaDAO.findById(Media.class, gm.getMediaId());
			// sets the Media elements that need DB conection
			logger.info("seting GeneralMetadataDTO... Media loaded");

			theMedia.setTitle(gm.getTitle());
			logger.info("seting GeneralMetadataDTO... title set ["+theMedia.getTitle()+"]");
			theMedia.setDescription(gm.getDescription());
			logger.info("seting GeneralMetadataDTO... description set["+theMedia.getDescription()+"]");

			// sets the Media type
			MediaType theMediaType = (MediaType) mediaTypeDAO.findById(MediaType.class,gm.getMediaTypeId());
			theMedia.setMediaType(theMediaType);
			logger.info("seting GeneralMetadataDTO... mediaType set");

			// set the associatedSpecimens
			if(gm.getAssociatedSpecimensList()!=null)
				addSpecimens(gm.getMediaId(), gm.getAssociatedSpecimensList());
			logger.info("seting GeneralMetadataDTO... AsociatedSpecimens set");

			// set the associatedObservations
			if(gm.getAssociatedObservationsList()!=null)
				addObservations(gm.getMediaId(), gm.getAssociatedObservationsList());
			logger.info("seting GeneralMetadataDTO... AsociatedObservations set");

			// set the associatedCollections
			if(gm.getAssociatedGatheringsList()!=null)
				addGatherings(gm.getMediaId(), gm.getAssociatedGatheringsList());
			logger.info("seting GeneralMetadataDTO... AsociatedCollects set");

			if(gm.getProjectsList()!=null)
				addProjectsList(gm.getMediaId(), gm.getProjectsList());
			logger.info("seting GeneralMetadataDTO... Projects set");

			// TODO: set the series

			// set the associatedTaxonomy
			if(gm.getTaxonsList()!=null)
				addTaxons(gm.getMediaId(), gm.getTaxonsList());
			logger.info("seting GeneralMetadataDTO... Taxonomy set");

			// TODO set the synapticCollections

			// keyWords
			if(gm.getKeywordsList()!=null)
				addKeywordsList(gm.getMediaId(), gm.getKeywordsList());
			logger.info("seting GeneralMetadataDTO... Keywords set");

			// Site information
			theMedia.setSiteId(gm.getSiteId());
			logger.info("seting GeneralMetadataDTO... SiteId set");
			theMedia.setSiteDescription(gm.getSiteDescription());
			logger.info("seting GeneralMetadataDTO... Site Description set");

			// saves the Media Object in the database
			// session.save(theMedia);
			mediaDAO.update(theMedia);
			return theMedia.getMediaId();

		} catch (Exception he) {
			logger.error("There was a hibernate exeption in the setGM");
			logger.error(he.getMessage());
			throw new IllegalArgumentException("fails on setGM", he);
		}

	}
	
	/**
	 * sets all the UsesAndCopyrigths Metadata
	 * 
	 * @param mediaId
	 *            media owner of the new metadata
	 */
	private void insertUACM(UsesAndCopyrightsDTO uac) throws IllegalArgumentException {
		logger.info("inserting UACMetadata... for media: " + uac.getMediaId());

		Media theMedia;
		UsePolicy theUsePolicy;

		try {

			theMedia = (Media) mediaDAO.findById(Media.class, uac.getMediaId());

			logger.info("checking insertGM... for media: " + theMedia.getMediaId());
			logger.info("checking insertGM... for title: " + theMedia.getTitle());
			logger.info("checking insertGM... for description: " + theMedia.getDescription());
			
			theMedia.setAuthorPersonId(uac.getAuthorId());
			theMedia.setOwnerInstitutionId(uac.getInstitutionOwnerId());
			theMedia.setOwnerPersonId(uac.getPersonOwnerId());

			// set usePolicyID
			theUsePolicy = (UsePolicy) usePolicyDAO.findById(UsePolicy.class, uac.getUsePolicyID());
			theMedia.setUsePolicy(theUsePolicy);

			// set mediaUsesIds
			if(uac.getMediaUsesList()!=null)
				addMediaUses(uac.getMediaId(), uac.getMediaUsesList());

			theMedia.setIsBackup(uac.getIsBackup());
			theMedia.setIsPublic(uac.getIsPublic());

			// saves the Media Object in the database
			mediaDAO.update(theMedia);

		} catch (Exception he) {
			logger.error("There was a hibernate exeption in the setUACM");
			logger.error(he.getMessage());
			throw new IllegalArgumentException("fails on setUACM", he);
		}

	}	
	
	
	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#addProjectsList(java.lang.Integer, java.util.List)
	 */
	public void addProjectsList(Integer mediaId, List<ProjectLite> projectLiteList)
			throws IllegalArgumentException {
		logger.debug("addProjectsList, params: projectList.size["
				+ projectLiteList.size() + "], mediaId[" + mediaId + "].");
		
		int successful = 0; // for local count of errors
		Media theMedia = null;
		Project theProject = null;
		MediaProjectId theMediaProjectId = null;
		MediaProject theMediaProject = null;

		theMedia = (Media) mediaDAO.findById(Media.class, mediaId);

		try{ 
		for (ProjectLite pl : projectLiteList) {

			theProject = (Project) projectDAO.findById(Project.class, pl.getProjectId());

			theMediaProjectId = new MediaProjectId(mediaId, (Integer) theProject
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
	public void addKeywordsList(Integer mediaId, List<KeywordLiteDTO> keywordsList)
			throws IllegalArgumentException {
		logger.debug("addKeywordsList, params: keywordsList.size["
				+ keywordsList.size() + "], mediaId[" + mediaId + "].");

		int successful = 0; // for local count of errors
		// Taxon theTaxon;
		MediaKeywordId theMediaKeywordId;
		MediaKeyword theMediaKeyword;

		// sets the taxons of the MEDIA HibernateUtil.closeSession(attaSession);
		for (KeywordLiteDTO klDTO : keywordsList) {
			logger.debug("Asociandolo con el keywordId=" + klDTO.getKeywordKey());
			theMediaKeyword = null;
			theMediaKeywordId = null;

			try {

				theMediaKeywordId = new MediaKeywordId(mediaId, new Integer(klDTO.getKeywordKey()));

				// sets the Taxon to the media
				theMediaKeyword = new MediaKeyword();
				theMediaKeyword.setId(theMediaKeywordId);

				mediaKeywordDAO.create(theMediaKeyword);

				successful = successful + 1;
				logger.info("The keyword id '" + klDTO.getKeywordKey()
						+ "'is set with the media with id '" + mediaId + "'.");

			} catch (Exception he) {
				logger.error("Couldn't set the keyword id '" + klDTO.getKeywordKey()
						+ "' to the media with id '" + mediaId + "'.");
			}

		} // for
		logger.info("SetMediaKeywords finish with " + successful
				+ " successful results.");

	}


	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#deleteKeywords(java.lang.Integer, java.util.List)
	 */
	public void deleteKeywords(Integer mediaId, List<KeywordLiteDTO> keywordsList)
			throws IllegalArgumentException {
		logger.debug("deleteKeywords, params: keywordsList.size["
				+ keywordsList.size() + "], mediaId[" + mediaId + "].");

		int successful = 0; // for local count of errors

		// Taxon theTaxon;
		MediaKeywordId theMediaKeywordId;
		MediaKeyword theMediaKeyword;

		for (KeywordLiteDTO klDTO : keywordsList) {
			logger.debug("deleting associated keywords...  deleting keyword:"+ klDTO.getKeywordKey());

			// theTaxon = null;
			theMediaKeyword = null;
			theMediaKeywordId = null;

			try {

				theMediaKeywordId = new MediaKeywordId(mediaId, new Integer(klDTO.getKeywordKey()));

				// sets the keywords to the media
				theMediaKeyword = new MediaKeyword();
				theMediaKeyword.setId(theMediaKeywordId);

				// saves changes and closes session
				mediaKeywordDAO.delete(theMediaKeyword);


				successful = successful + 1;
				logger.debug("deleting associated keywords... The keyword id '"
						+ klDTO.getKeywordKey() + "' is now deleted from media '" + mediaId
						+ "'.");

			} catch (Exception e) {
				logger
						.error("deleting associated keywords... Couldn't delete the taxon id '"
								+ klDTO.getKeywordKey()
								+ "' to the media with id '"
								+ mediaId
								+ "'.");
			}

		} // for
		logger.debug("deleting associated keywords... delete finish finish with "
				+ successful + " successful results.");


	}
	

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#addMediaUses(java.lang.Integer, java.util.List)
	 */
	public void addMediaUses(Integer mediaId, List<MediaUseLite> mediaUsesList)
			throws IllegalArgumentException {
		logger.debug("setting " + mediaUsesList.size() + " media uses for media:"
				+ mediaId + ".");

		int successful = 0; // for local count of errors


		Media theMedia = null;
		MediaUse theMediaUse = null;
		MediaUseMedia theMediaUseMedia = null;
		MediaUseMediaId theMediaUseMediaId = null;

		try {
			theMedia = (Media) mediaDAO.findById(Media.class, mediaId);

			for (MediaUseLite mul : mediaUsesList) {

				
				theMediaUse = (MediaUse) mediaUseDAO.findById(MediaUse.class, mul.getMediaUseId());

				theMediaUseMediaId = new MediaUseMediaId(mediaId, (Integer) theMediaUse
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
					+ mediaId + "'.");
		}

		logger.info("SetMediaUses finish with " + successful
				+ " successful results.");
	}
	
	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#addGatherings(java.lang.Integer, java.util.List)
	 */
	public void addGatherings(Integer mediaId, List<GatheringLiteDTO> gatheringsList)
			throws IllegalArgumentException {
		logger.debug("addGatherings for media:" + mediaId + ".");

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

				theGatheringMediaId = new GatheringMediaId(mediaId, new Integer (pLite
						.getPersonKey()), new Integer(glDTO.getGatheringKey()));

				// sets the gatherings to the media
				theGatheringMedia = new GatheringMedia();
				theGatheringMedia.setId(theGatheringMediaId);

				// saves changes and closes session
				gatheringMediaDAO.create(theGatheringMedia);


				successful = successful + 1;
				logger.info("The gatheringNumber '" + glDTO.getGatheringKey()
						+ "' of the INBioPerson ID'" + pLite.getPersonKey()
						+ "'is set with the media with id '" + mediaId + "'.");

			} catch (Exception he) {
				logger.error("Couldn't set The gatheringNumber '" + glDTO.getGatheringKey()
						+ "' of the INBioPerson ID'" + pLite.getPersonKey()
						+ "' to the media [id" + mediaId + "].");
			}
		} // for
		logger.info("setAssociatedGatherings finish with " + successful
				+ " successful results.");


	}
	

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#addObservations(java.lang.Integer, java.util.List)
	 */
	public void addObservations(Integer mediaId,
			List<ObservationLiteDTO> observationsList) throws IllegalArgumentException {
		logger.debug("setting " + observationsList.size()
				+ " associated observations for media:" + mediaId + ".");

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
				theOTMId = new ObservedTaxonMediaId(new Integer(olDTO.getObservationKey()), mediaId);

				theOTM = new ObservedTaxonMedia();
				theOTM.setId(theOTMId);

				observationMediaDAO.create(theOTM);

				successful = successful + 1;

			} catch (Exception he) {
				logger.error("Couldn't set the Observation["
						+ olDTO.getObservationKey()	+ "] to the media [id" + mediaId + "].");
			}

		} // for
		logger.info("setObservationMedia finish with " + successful
				+ " successful results.");


	}


	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#addSpecimens(java.lang.Integer, java.util.List)
	 */
	public void addSpecimens(Integer mediaId, List<SpecimenLiteDTO> specimensList)
			throws IllegalArgumentException {
		logger.debug("setting associated specimens for media:" + mediaId + ".");

		int successful = 0;
		SpecimenMedia theSpecimenMedia;
		SpecimenMediaId theSpecimenMediaId;

		for (SpecimenLiteDTO slDTO : specimensList) {

			logger.debug("con SpecimenId=" + slDTO.getSpecimenKey());
			// empty variables
			theSpecimenMedia = null;
			theSpecimenMediaId = null;

			try {

				theSpecimenMediaId = new SpecimenMediaId(new Integer(slDTO.getSpecimenKey()), mediaId);

				// sets the Specimen to the media
				theSpecimenMedia = new SpecimenMedia();
				theSpecimenMedia.setId(theSpecimenMediaId);

				// saves changes and closes session

				specimenMediaDAO.create(theSpecimenMedia);


				successful = successful + 1;
				logger.debug("The Specimen id '" + slDTO.getSpecimenKey()
						+ "'is set with the media with id '" + mediaId + "'.");

			} catch (Exception e) {
				logger.error("Couldn't set the Speciemen [id=" + slDTO.getSpecimenKey()
						+ "] to the media [id" + mediaId + "].");
			}

		} // for
		logger.debug("SetSpecimenMedia finish with " + successful
				+ " successful results.");


	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#addTaxons(java.lang.Integer, java.util.List)
	 */
	public void addTaxons(Integer mediaId, List<TaxonLiteDTO> taxonsList)
			throws IllegalArgumentException {
		logger.debug("setting " + taxonsList.size()
				+ " associated taxons for media:" + mediaId + ".");

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
				theTaxonMediaId = new TaxonMediaId(new Integer(tlDTO.getTaxonKey()), mediaId);

				// sets the Taxon to the media
				theTaxonMedia = new TaxonMedia();
				theTaxonMedia.setId(theTaxonMediaId);

				// saves changes and closes session
				taxonMediaDAO.create(theTaxonMedia);

				successful = successful + 1;
				logger.debug("The taxon id '" + tlDTO.getTaxonKey()
						+ "'is set with the media with id '" + mediaId + "'.");

			} catch (Exception he) {
				logger.error("Couldn't set the taxon id '" + tlDTO.getTaxonKey()
						+ "' to the media with id '" + mediaId + "'.");
			}
		} // for
		logger.info("SetTaxonMedias finish with " + successful
				+ " successful results.");

	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#deleteGatherings(java.lang.Integer, java.util.List)
	 */
	public void deleteGatherings(Integer mediaId,
			List<GatheringLiteDTO> gatheringsList) throws IllegalArgumentException {
		logger.debug("deleteAssociatedGatherings for media:" + mediaId + ".");

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
				
				oldGatheringMediaId = new GatheringMediaId(mediaId, new Integer(pLite
						.getPersonKey()), new Integer(glDTO.getGatheringKey()));

				// sets the Gathering to the media
				oldGatheringMedia = new GatheringMedia();
				oldGatheringMedia.setId(oldGatheringMediaId);

				// saves changes and closes session
				gatheringMediaDAO.delete(oldGatheringMedia);

				successful = successful + 1;
				logger.info("The GatheringNumber '" + glDTO.getGatheringKey()
						+ "' of userId '" + pLite.getPersonKey()
						+ "' was deleted from the media '" + mediaId + "'.");

			} catch (Exception he) {
				logger.error("Couldn't delet The GatheringNumber '"
						+ glDTO.getGatheringKey() + "' of userId '" + pLite.getPersonKey()
						+ "' from the media [id" + mediaId + "].");

			}
		} // for
		logger.info("deleteAssociatedGatherings finish with " + successful
				+ " successful deletes.");
	}


	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#deleteMediaUses(java.lang.Integer, java.util.List)
	 */
	public void deleteMediaUses(Integer mediaId, List<MediaUseLite> mediaUsesList)
			throws IllegalArgumentException {
		logger.debug("deleting media uses... " + mediaUsesList.size()
				+ " media uses for media:" + mediaId + ".");

		int successful = 0; // for local count of errors

		Media m = null;
		MediaUse mu = null;
		MediaUseMedia mum = null;
		MediaUseMediaId mumId = null;

		try {
			m = (Media) mediaDAO.findById(Media.class, mediaId);

			// deletes the uses of the MEDIA
			for (MediaUseLite mul : mediaUsesList) {

				mu = (MediaUse) mediaUseDAO.findById(MediaUse.class, mul.getMediaUseId());

				mumId = new MediaUseMediaId(m.getMediaId(), mu.getMediaUseId());

				mum = (MediaUseMedia) mediaUseMediaDAO.findById(MediaUseMedia.class, mumId);

				mediaUseMediaDAO.delete(mum);
				successful++;

			} // for

		} catch (Exception he) {
			logger.error(he.getMessage());
			logger.error("deleting media uses... Couldn't delete the mediaUse id '"
					+ (Integer) mu.getMediaUseId() + "' to the media with id '" + mediaId
					+ "'.");
		}
		logger.info("deleting media uses... finish with " + successful
				+ " successfully deleted registries.");
	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#deleteObservations(java.lang.Integer, java.util.List)
	 */
	public void deleteObservations(Integer mediaId,
			List<ObservationLiteDTO> observationsList) throws IllegalArgumentException {
		logger.debug("deleting associated observations for media:" + mediaId
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
				oldOTMId = new ObservedTaxonMediaId(new Integer(olDTO.getObservationKey()), mediaId);

				oldOTM = new ObservedTaxonMedia();
				oldOTM.setId(oldOTMId);

				observationMediaDAO.delete(oldOTM);


				successful = successful + 1;

			} catch (Exception he) {
				logger.error("deleting associated observations... "
						+ "Couldn't delete the Observation["
						+ olDTO.getObservationKey()
						+ "] from the media [id" + mediaId + "].");
			}

		} // for
		logger.debug("deleting associated observations... finish with "
				+ successful + " successful deleted registries.");


	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#deleteSpecimens(java.lang.Integer, java.util.List)
	 */
	public void deleteSpecimens(Integer mediaId, List<SpecimenLiteDTO> specimensList)
			throws IllegalArgumentException {
		logger.debug("deleting associated specimens for media:" + mediaId + ".");

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
						new Integer(slDTO.getSpecimenKey()), mediaId);

				// sets the Specimen to the media
				oldSpecimenMedia = new SpecimenMedia();
				oldSpecimenMedia.setId(oldSpecimenMediaId);

				// saves changes and closes session
				specimenMediaDAO.delete(oldSpecimenMedia);

				successful = successful + 1;
				logger.debug("The Specimen id '" + slDTO.getSpecimenKey()
						+ "' was deleted from the media '" + mediaId + "'.");

			} catch (Exception he) {
				logger.error("Couldn't delet the Speciemen [id="
						+ slDTO.getSpecimenKey() + "] from the media [id" + mediaId	+ "].");

			}
		} // for
		logger.info("deleteAssociatedSpecimens finish with " + successful
				+ " successful deletes.");


	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MediaManager#deleteTaxons(java.lang.Integer, java.util.List)
	 */
	public void deleteTaxons(Integer mediaId, List<TaxonLiteDTO> taxonsList)
			throws IllegalArgumentException {
		logger.debug("deleting [" + taxonsList.size()
				+ "] associated taxons for media:" + mediaId + ".");

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

				theTaxonMediaId = new TaxonMediaId(new Integer(tlDTO.getTaxonKey()), mediaId);

				// sets the Taxon to the media
				theTaxonMedia = new TaxonMedia();
				theTaxonMedia.setId(theTaxonMediaId);

				// saves changes and closes session
				taxonMediaDAO.delete(theTaxonMedia);

				successful = successful + 1;
				logger.info("deleting associated taxons... The taxon id '"
						+ tlDTO.getTaxonKey() + "' is now deleted from media '" + mediaId
						+ "'.");

			} catch (Exception he) {
				logger
						.error("deleting associated taxons... Couldn't delete the taxon id '"
								+ tlDTO.getTaxonKey() + "' to the media with id '" + mediaId + "'.");
			}
		} // for
		logger.info("deleting associated taxons... delete finish finish with "
				+ successful + " successful results.");
		// return successful;


	}

	/**
	 * @param mediaDAO the mediaDAO to set
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
	 * @param projectDAO the projectDAO to set
	 */
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	/**
	 * @return the projectDAO
	 */
	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	/**
	 * @param mediaProjectDAO the mediaProjectDAO to set
	 */
	public void setMediaProjectDAO(MediaProjectDAO mediaProjectDAO) {
		this.mediaProjectDAO = mediaProjectDAO;
	}

	/**
	 * @return the mediaProjectDAO
	 */
	public MediaProjectDAO getMediaProjectDAO() {
		return mediaProjectDAO;
	}

	/**
	 * @return the mediaAttributeDAO
	 */
	public MediaAttributeDAO getMediaAttributeDAO() {
		return mediaAttributeDAO;
	}

	/**
	 * @param mediaAttributeDAO the mediaAttributeDAO to set
	 */
	public void setMediaAttributeDAO(MediaAttributeDAO mediaAttributeDAO) {
		this.mediaAttributeDAO = mediaAttributeDAO;
	}

	/**
	 * @return the mediaAttributeValueDAO
	 */
	public MediaAttributeValueDAO getMediaAttributeValueDAO() {
		return mediaAttributeValueDAO;
	}

	/**
	 * @param mediaAttributeValueDAO the mediaAttributeValueDAO to set
	 */
	public void setMediaAttributeValueDAO(
			MediaAttributeValueDAO mediaAttributeValueDAO) {
		this.mediaAttributeValueDAO = mediaAttributeValueDAO;
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
	 * @return the mediaTypeDAO
	 */
	public MediaTypeDAO getMediaTypeDAO() {
		return mediaTypeDAO;
	}

	/**
	 * @param mediaTypeDAO the mediaTypeDAO to set
	 */
	public void setMediaTypeDAO(MediaTypeDAO mediaTypeDAO) {
		this.mediaTypeDAO = mediaTypeDAO;
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
	 * @return the usePolicyDAO
	 */
	public UsePolicyDAO getUsePolicyDAO() {
		return usePolicyDAO;
	}

	/**
	 * @param usePolicyDAO the usePolicyDAO to set
	 */
	public void setUsePolicyDAO(UsePolicyDAO usePolicyDAO) {
		this.usePolicyDAO = usePolicyDAO;
	}

	/**
	 * @return the mediaAttributeManager
	 */
	public MediaAttributeManager getMediaAttributeManager() {
		return mediaAttributeManager;
	}

	/**
	 * @param mediaAttributeManager the mediaAttributeManager to set
	 */
	public void setMediaAttributeManager(MediaAttributeManager mediaAttributeManager) {
		this.mediaAttributeManager = mediaAttributeManager;
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

	/**
	 * @param taxonomyManager the taxonomyManager to set
	 */
	public void setTaxonomyManager(TaxonomyManager taxonomyManager) {
		this.taxonomyManager = taxonomyManager;
	}

	/**
	 * @return the taxonomyManager
	 */
	public TaxonomyManager getTaxonomyManager() {
		return taxonomyManager;
	}

	/**
	 * @return the metadataManager
	 */
	public MetadataManager getMetadataManager() {
		return metadataManager;
	}

	/**
	 * @param metadataManager the metadataManager to set
	 */
	public void setMetadataManager(MetadataManager metadataManager) {
		this.metadataManager = metadataManager;
	}

}
