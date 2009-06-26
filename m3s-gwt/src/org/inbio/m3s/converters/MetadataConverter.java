/**
 * Converts info from GUI oriented values -mostly textual value- to DB useful
 * value that could be Integers, Characters, String
 * 
 * 
 */
package org.inbio.m3s.converters;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.gwt.associatedto.client.dto.AssociatedToConstants;
import org.inbio.gwt.associatedto.client.dto.AssociatedToInfo;
import org.inbio.gwt.controlledtext.client.dto.TextInfo;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.converters.impl.PersonConverter;
import org.inbio.m3s.dao.core.MediaAttributeDAO;
import org.inbio.m3s.dao.core.MediaCategoryDAO;
import org.inbio.m3s.dao.core.MediaTypeDAO;
import org.inbio.m3s.dao.core.MediaUseDAO;
import org.inbio.m3s.dao.core.ProjectDAO;
import org.inbio.m3s.dao.core.UsePolicyDAO;
import org.inbio.m3s.dto.taxonomy.GatheringLiteDTO;
import org.inbio.m3s.dto.taxonomy.ObservationLiteDTO;
import org.inbio.m3s.dto.taxonomy.SpecimenLiteDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.gwt.client.dto.util.PersonGWTDTO;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.GeneralMetadataTV;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.MediaOwnerConstants;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.UsesAndCopyrightsTV;
import org.inbio.m3s.dto.GeneralMetadataDTO;
import org.inbio.m3s.dto.UsesAndCopyrightsDTO;
import org.inbio.m3s.dto.agent.InstitutionLiteDTO;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.full.MediaAttributeFull;
import org.inbio.m3s.dto.lite.MediaCategoryLite;
import org.inbio.m3s.dto.lite.MediaTypeLite;
import org.inbio.m3s.dto.lite.MediaUseLite;
import org.inbio.m3s.dto.lite.ProjectLite;
import org.inbio.m3s.dto.lite.UsePolicyLite;
import org.inbio.m3s.dto.message.KeywordLiteDTO;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.service.TaxonomyManager;
import org.inbio.m3s.util.ServiceUtil;
import org.inbio.m3s.util.StringUtil;

/**
 * @author jgutierrez
 * 
 */
public class MetadataConverter {

	private static Logger logger = Logger.getLogger(MetadataConverter.class);

	/**
	 * Converts the General Metada object that is Database ready to a General
	 * Metadata Text Values object that is ready to be used in the view widgets
	 * 
	 * @param gm
	 *          is the source GeneralMetadata Object
	 * @return a equivalent widgets display ready GeneralMetadatTV Object
	 * 
	 */
	public static GeneralMetadataTV toTextualValues(GeneralMetadataDTO gm) {
		logger.debug("Translating GMDBValues to GMTextValues...");
		GeneralMetadataTV gmtv = new GeneralMetadataTV();
		MediaCategoryDAO mediaCategoryDAO = (MediaCategoryDAO) ServiceUtil.appContext.getBean("mediaCategoryDAO");
		MediaCategoryLite mediaCategoryLite = null;
		MediaTypeDAO mediaTypeDAO = (MediaTypeDAO) ServiceUtil.appContext.getBean("mediaTypeDAO");
		MediaTypeLite mediaTypeLite = null;

		gmtv.setMediaId(gm.getMediaId());
		logger.debug("Translating GMDBValues to GMTextValues... mediaId: "
				+ gmtv.getMediaId());

		if (gm.getTitle() == null) {
			gmtv.setTitle("");
		} else {
			gmtv.setTitle(gm.getTitle());
		}
		logger.debug("Translating GMDBValues to GMTextValues... title: "
				+ gmtv.getTitle());

		if (gm.getDescription() == null) {
			gmtv.setDescription("");
		} else {
			gmtv.setDescription(gm.getDescription());
		}
		logger.debug("Translating GMDBValues to GMTextValues... description: "
				+ gmtv.getDescription());

		// mediaType
		mediaTypeLite = mediaTypeDAO.getMediaTypeLite(gm.getMediaTypeId(), MessageManager.DEFAULT_LANGUAGE);
		gmtv.setMediaType(mediaTypeLite.getMediaTypeName());
		logger.debug("Translating GMDBValues to GMTextValues... mediaType: "
				+ gmtv.getMediaType());
		// category
		mediaCategoryLite = mediaCategoryDAO.getMediaCategoryLiteFromMediaType(gm.getMediaTypeId(), MessageManager.DEFAULT_LANGUAGE);
		gmtv.setMediaCategory(mediaCategoryLite.getMediaCategoryName());
		logger.debug("Translating GMDBValues to GMTextValues... category: "
				+ gmtv.getMediaCategory());

		// associations of the media:
		// FIXME: By now the mechanism of associations is not working good,
		// because you can only asociate a media to one thing (specimen,
		// observation, collectNumber, etc), this has to be improved to fix this
		// bug. Maybe the bug fix should be letting gmtv carry more than 1
		// textual value, Right now carries only 1 Integer value
		Integer associatedToType;
		String associatedToValue;
		
		logger
				.debug("Translating GMDBValues to GMTextValues... Discovering the association type:");
		if (gm.getAssociatedSpecimensList() != null
				&& gm.getAssociatedSpecimensList().size() != 0) {
			logger.debug("Translating GMDBValues to GMTextValues... AssociatedSpecimen number> "
					+ gm.getAssociatedSpecimensList().size());
			
			//associatedTo
			associatedToType = AssociatedToConstants.SPECIMEN_NUMBER;
			associatedToValue = gm.getAssociatedSpecimensList().get(0).getSpecimenKey();
			gmtv.setAssociatedToInfo(new AssociatedToInfo(associatedToType, associatedToValue));
			logger
					.debug("Translating GMDBValues to GMTextValues... AssociatedSpecimen: "
							+ gmtv.getAssociatedToInfo().getType());
		} else if (gm.getAssociatedObservationsList() != null
				&& gm.getAssociatedObservationsList().size() != 0) {
			associatedToType = AssociatedToConstants.OBSERVATION_NUMBER;
			associatedToValue = gm.getAssociatedObservationsList().get(0).getObservationKey();
			gmtv.setAssociatedToInfo(new AssociatedToInfo(associatedToType, associatedToValue));
			logger
					.debug("Translating GMDBValues to GMTextValues... AssociatedObservation: "
							+ gmtv.getAssociatedToInfo().getType());
		} else if (gm.getAssociatedGatheringsList() != null
				&& gm.getAssociatedGatheringsList().size() != 0) {
			
			GatheringLiteDTO glDTO = gm.getAssociatedGatheringsList().get(0);		
			String gatheringPersonName = glDTO.getResponsiblePersonName();
	
			associatedToType = AssociatedToConstants.GATHERING_CODE;
			associatedToValue = gatheringPersonName + StringUtil.TEXT_DELIMITER
			+ glDTO.getGatheringKey();
				
			gmtv.setAssociatedToInfo(new AssociatedToInfo(associatedToType, associatedToValue));
			
			logger
					.debug("Translating GMDBValues to GMTextValues... AssociatedCollection: "
							+ gmtv.getAssociatedToInfo().getType());
		} else {
			gmtv.setAssociatedToInfo(new AssociatedToInfo(AssociatedToConstants.NO_ASSOCIATION, ""));
			logger.debug("Translating GMDBValues to GMTextValues... No asociation.");
		}

		// Taxonomy
		logger.debug("Translating GMDBValues to GMTextValues... "
				+ "Looking for taxonomy:");
		List<String> taxonomyTV = new ArrayList<String>();
		if(gm.getTaxonsList() != null) {
			for(TaxonLiteDTO tlDTO  : gm.getTaxonsList()){
				taxonomyTV.add(tlDTO.getTaxonKey());
			}
		}
		gmtv.setTaxonomyInfo(taxonomyTV);

		logger.debug("Translating GMDBValues to GMTextValues... "
				+ "taxonomy elements: " + gmtv.getTaxonomyInfo().size());

		// keyWords
		logger.debug("Translating GMDBValues to GMTextValues... Looking for keywords:");

		List<TextInfo> keywordsTV = new ArrayList<TextInfo>();
		for(KeywordLiteDTO kl : gm.getKeywordsList()){
				logger.debug("Translating GMDBValues to GMTextValues... "
						+ "adding keyword: '" +kl.getName()+"");
				keywordsTV.add(new TextInfo(new Integer(kl.getKeywordKey()), kl.getName()));
		}
		gmtv.setKeyWords(keywordsTV);

		logger.debug("Translating GMDBValues to GMTextValues... keywords: "
				+ gmtv.getKeyWords());

		// textual site description
		if (gm.getSiteDescription() == null) {
			gmtv.setSiteDescription("");
		} else {
			gmtv.setSiteDescription(gm.getSiteDescription());
		}
		logger.debug("Translating GMDBValues to GMTextValues... site description: "
				+ gmtv.getSiteDescription());
		gmtv.setSiteId(gm.getSiteId());
		logger.debug("Translating GMDBValues to GMTextValues... siteId: "
				+ gmtv.getSiteId());

		// projects;
		logger
				.debug("Translating GMDBValues to GMTextValues... Looking for projects:");
		if (gm.getProjectsList() != null) {
			String projectTV = "";
			for(ProjectLite pl : gm.getProjectsList()){
				projectTV = projectTV.concat(pl.getName()
						+ StringUtil.TEXT_DELIMITER);
				logger
				.debug("Translating GMDBValues to GMTextValues... adding project: '"
						+ pl.getName() + "'");
			}			
			gmtv.setProjects(projectTV);
		} else {
			gmtv.setProjects("");
		}
		logger.debug("Translating GMDBValues to GMTextValues... project: "
				+ gmtv.getProjects());

		// GeneralMetadaTV >parameters of returning object
		// TODO
		// private List series;
		// private List synopticColletion;

		// GeneralMetadata >parameters of param object
		// TODO
		// private Set series;
		// private Set synapticCollections;

		return gmtv;
	}

	/**
	 * Converts the General Metada Text Values that is ready to be used in the
	 * view widgets to a object that is Database ready, a General Metadata Object
	 * 
	 * @param gmtv
	 *          is the source GenerealMetadataTV object
	 * @return a equivalent DB ready GeneralMetadata Object
	 */
	@SuppressWarnings("unchecked")
	public static GeneralMetadataDTO toDBValues(GeneralMetadataTV gmtv)
			throws IllegalArgumentException {

		// variables
		logger.debug("Translating GMTV to DBValues...");
		GeneralMetadataDTO gm = new GeneralMetadataDTO();
		List<SpecimenLiteDTO> associatedSpecimensList = new ArrayList<SpecimenLiteDTO>();
		List<ObservationLiteDTO> associatedObservationsList =  new ArrayList<ObservationLiteDTO>();
		List<GatheringLiteDTO> associatedGatheringsList = new ArrayList<GatheringLiteDTO>();
		String gatheringPersonName;
		String gatheringNumber;
		List<KeywordLiteDTO> keywordsList = new ArrayList<KeywordLiteDTO>();
		KeywordLiteDTO keywordLite = null;
		AgentManager agentManager = (AgentManager) ServiceUtil.appContext.getBean(Properties.AGENT_MANAGER);
		PersonLiteDTO pLite = null;
		MediaTypeDAO mediaTypeDAO = (MediaTypeDAO) ServiceUtil.appContext.getBean("mediaTypeDAO");
		MediaTypeLite mediaTypeLite = null;
		ProjectDAO projectDAO =  (ProjectDAO) ServiceUtil.appContext.getBean("projectDAO");
		List<ProjectLite> projectsList = new ArrayList<ProjectLite>();
		ProjectLite projectLite = null;
		TaxonomyManager taxonomyManager = (TaxonomyManager) ServiceUtil.appContext.getBean(Properties.TAXONOMY_MANAGER);
		TaxonLiteDTO tlDTO = null;
		

		gm.setMediaId(gmtv.getMediaId());
		logger.debug("Translating GMTV to DBValues... mediaId: " + gm.getMediaId());

		gm.setTitle(gmtv.getTitle());
		logger.debug("Translating GMTV to DBValues... Title: " + gm.getTitle());

		gm.setDescription(gmtv.getDescription());
		logger.debug("Translating GMTV to DBValues... Description: "
				+ gm.getDescription());

		try {
			mediaTypeLite = mediaTypeDAO.getMediaTypeLite(gmtv.getMediaType());
			gm.setMediaTypeId(mediaTypeLite.getMediaTypeId());
			logger.debug("Translating GMTV to DBValues... MediaType: "
					+ gm.getMediaTypeId());
		} catch (IllegalArgumentException iae) {
			throw iae;
		}

		// associations of the media:
		// FIXME: By now the mechanism of associations is not working good,
		// because you can only asociate a media to one thing (specimen,
		// observation, collectNumber, etc), this has to be improved to fix this
		// bug. Maybe the bug fix should be letting gmtv carry more than 1
		// value, rigths now only carries 1 Integer value

		// sets the associated values to a a new arrayList with size 0
		gm.setAssociatedSpecimensList(associatedSpecimensList);
		gm.setAssociatedObservationsList(associatedObservationsList);
		gm.setAssociatedGatheringsList(associatedGatheringsList);
		logger.debug("Translating GMTV to DBValues... "
				+ "Associations now set to new ArrayList()");

		logger.debug("Association Type Value = " + gmtv.getAssociatedToInfo().getType()+ ". On the GMTV Object");

		logger.debug("chequeando AssociatedTo.SPECIMEN_NUMBER ="+AssociatedToConstants.SPECIMEN_NUMBER.intValue());
		
		if (gmtv.getAssociatedToInfo().getType().equals(AssociatedToConstants.SPECIMEN_NUMBER)) {
			logger.debug("Associated to Specimen Number");
			try {
				
				SpecimenLiteDTO slDTO = new SpecimenLiteDTO(gmtv.getAssociatedToInfo().getValue());
				associatedSpecimensList.add(slDTO);
				gm.setAssociatedSpecimensList(associatedSpecimensList);
				logger.debug("Translating GMTV to DBValues... "
						+ "Associated To Specimen Number.");
			} catch (Exception e) {
				logger.error("Número de especimen ["
						+ gmtv.getAssociatedToInfo().getValue() + "] inválido");
				throw new IllegalArgumentException("Número de especimen ["
						+ gmtv.getAssociatedToInfo().getValue() + "] inválido");
			}

		} else if (gmtv.getAssociatedToInfo().getType().equals(AssociatedToConstants.OBSERVATION_NUMBER)) {
			logger.debug("Associated to Observation Number");
			try {
				ObservationLiteDTO olDTO = new ObservationLiteDTO(gmtv.getAssociatedToInfo().getValue());
				associatedObservationsList.add(olDTO);
				gm.setAssociatedObservationsList(associatedObservationsList);
				logger.debug("Translating GMTV to DBValues... "
						+ "Associated To Observation Number.");
			} catch (Exception e) {
				throw new IllegalArgumentException("Número de observacion ["
						+ gmtv.getAssociatedToInfo().getValue() + "] inválido");
			}

		} else if (gmtv.getAssociatedToInfo().getType().equals(AssociatedToConstants.GATHERING_CODE)) {
			logger.debug("Associated to Gathering Code");
			try {
				List<Object> temp = StringUtil.getIndividualItems(gmtv.getAssociatedToInfo().getValue(),
						java.lang.String.class);

				gatheringPersonName = (String) temp.get(0);
				gatheringNumber = (String) temp.get(1);
				pLite = agentManager.getPersonLiteByName(gatheringPersonName);
				GatheringLiteDTO glDTO = new GatheringLiteDTO(gatheringNumber, pLite.getName());
				
				associatedGatheringsList.add(glDTO);
				gm.setAssociatedGatheringsList(associatedGatheringsList);
				
				logger
						.debug("Translating GMTV to DBValues... Associated To Collect Number.");
			} catch (IllegalArgumentException iae) {
				throw new IllegalArgumentException("Código de Colecta ["
						+ gmtv.getAssociatedToInfo().getValue() + "] inválido. " + iae.getMessage());
			} catch (Exception e) {
				throw new IllegalArgumentException("Código de Colecta ["
						+ gmtv.getAssociatedToInfo().getValue() + "] inválido.");
			}
		}
		// else no association

		// site
		gm.setSiteDescription(gmtv.getSiteDescription());
		logger.debug("Translating GMTV to DBValues... SiteDescription: "
				+ gm.getSiteDescription());

		gm.setSiteId(gmtv.getSiteId());
		logger.debug("Translating GMTV to DBValues... SiteId: " + gm.getSiteId());

		// taxonomy
		try {
			logger.debug("Translating GMTV to DBValues... getting taxonomy:");
			List<TaxonLiteDTO> taxonLiteList = new ArrayList<TaxonLiteDTO>();

			if (gmtv.getTaxonomyInfo() != null) {
				String literalTaxonId;
				for (int i = 0; i < gmtv.getTaxonomyInfo().size(); i++) {
					literalTaxonId = (String) gmtv.getTaxonomyInfo().get(i);
					tlDTO = taxonomyManager.getTaxonLiteById(literalTaxonId);
					taxonLiteList.add(tlDTO);
				}
			}
			gm.setTaxonsList(taxonLiteList);
		} catch (Exception e) {
			throw new IllegalArgumentException("La siguiente taxonomía "
					+ "presenta errores que impiden poderla utilizar");
		}

		// GeneralMetadata >parameters of returning object
		// Keywords
		try {
			logger.debug("Translating GMTV to DBValues... getting keywords:");

			logger.debug("Translating GMTV to DBValues... ["
					+ gmtv.getKeyWords().size() + "] keywords found");
			
			for(TextInfo keyword : gmtv.getKeyWords()){
				keywordLite = new KeywordLiteDTO(String.valueOf(keyword.getId()), keyword.getName());
				keywordsList.add(keywordLite);
				logger.debug("Translating GMTV to DBValues... "
									+ "adding keyword id: '"
									+ keywordLite.getKeywordKey() + "'.");
			}

			gm.setKeywordsList(keywordsList);
			logger.debug("Translating GMTV to DBValues... keywords were set ["
					+ keywordsList.size() + "].");

		} catch (IllegalArgumentException iae) {
			throw new IllegalArgumentException("Las palabras claves ["
					+ gmtv.getKeyWords()
					+ "] presenta errores que impiden poderlas utilizar. "
					+ iae.getMessage());
		} catch (Exception e) {
			throw new IllegalArgumentException("Las palabras claves ["
					+ gmtv.getKeyWords()
					+ "] presenta errores que impiden poderlas utilizar");
		}

		// Projects
		logger.debug("Translating GMTV to DBValues... getting projects:");
		try {
			
			//lista de string
			List<String> projectsTextualNameList = (List) StringUtil.getIndividualItems(gmtv
					.getProjects(), String.class);

			logger.debug("Translating GMTV to DBValues... ["
					+ projectsTextualNameList.size() + "] projects found");
			for(String projectName : projectsTextualNameList){
				projectLite = projectDAO.getProjectLite(projectName);
				projectsList.add(projectLite);
				logger.debug("Translating GMTV to DBValues... adding project id: '"
						+ projectLite.getProjectId() + "'.");
			}
			
			gm.setProjectsList(projectsList);
			logger.debug("Translating GMTV to DBValues... # of set projects "
			+ projectsList.size() + ".");

		} catch (IllegalArgumentException iae) {
			throw new IllegalArgumentException("Los proyectos [" + gmtv.getProjects()
					+ "] presenta errores que impiden poderlos utilizar. "
					+ iae.getMessage());
		} catch (Exception e) {
			throw new IllegalArgumentException("Los proyectos [" + gmtv.getProjects()
					+ "] presenta errores que impiden poderlos utilizar. ");
		}

		// private Set series;
		// private Set synapticCollections;
		// TODO
		// private List series;
		// private List synopticColletion;

		return gm;
	}

	/**
	 * 
	 * @param uacm
	 * @return
	 * 
	 * FIXME: eliminar lo de static
	 */
	public static UsesAndCopyrightsTV toTextualValue(UsesAndCopyrightsDTO uacm) {
		
		InstitutionLiteDTO iLiteDTO;
		UsePolicyDAO usePolicyDAO = (UsePolicyDAO) ServiceUtil.appContext.getBean("usePolicyDAO");
		UsePolicyLite usePolicyLite = null;
		AgentManager agentManager = (AgentManager) ServiceUtil.appContext.getBean(Properties.AGENT_MANAGER);
		PersonLiteDTO pLite = null;
		PersonConverter plConverter = new PersonConverter();

		logger.debug("Translating UACDBValues to UACDTextualValues...");
		UsesAndCopyrightsTV uactv = new UsesAndCopyrightsTV();
		uactv.setMediaId(uacm.getMediaId());
		logger.debug("Translating UACDBValues to UACDTextualValues... MediaId:"
				+ uactv.getMediaId());

		// author
		logger.debug("Translating UACDBValues to UACDTextualValues... authorDBID: "
				+ uacm.getAuthorId());
		pLite = agentManager.getPersonLite(uacm.getAuthorId().toString());
		//uactv.setAuthor(pLite.getName());
		uactv.setAuthor( (PersonGWTDTO) plConverter.toGWTDTO(pLite));
		logger.debug("Translating UACDBValues to UACDTextualValues... author: "
				+ uactv.getAuthor());

		// owner stuff
		if (uacm.getInstitutionOwnerId() != null) {
			uactv.setOwnerType(MediaOwnerConstants.OWNER_INSTITUTION);
			iLiteDTO = agentManager.getInstitutionLite(uacm.getInstitutionOwnerId().toString());
			uactv.setOwner(iLiteDTO.getName());
		} else if (uacm.getPersonOwnerId() != null) {
			uactv.setOwnerType(MediaOwnerConstants.OWNER_PERSON);
			pLite = agentManager.getPersonLite(uacm.getPersonOwnerId().toString());
			uactv.setOwner(pLite.getName());
		}
		logger.debug("Translating UACDBValues to UACDTextualValues... ownerType: "
				+ uactv.getOwnerType());
		logger.debug("Translating UACDBValues to UACDTextualValues... owner: "
				+ uactv.getOwner());

		// use policy
		usePolicyLite = usePolicyDAO.getUsePolicyLite(uacm.getUsePolicyID());
		uactv.setUsePolicy(usePolicyLite.getName());
		logger.debug("Translating UACDBValues to UACDTextualValues... use policy: "
				+ uactv.getUsePolicy());

		// Media uses
		logger
				.debug("Translating UACDBValues to UACDTextualValues... Looking for mediaUses:");
		if (uacm.getMediaUsesList() != null) {
			String mediaUseTV = "";
			
			for(MediaUseLite mul : uacm.getMediaUsesList()){
				logger.debug("Translating UACDBValues to UACDTextualValues... adding mediaUse: '"
						+ mul.getMediaUseName() + "'");
				mediaUseTV = mediaUseTV.concat(mul.getMediaUseName()
						+ StringUtil.TEXT_DELIMITER);
			}
			uactv.setMultimediaUses(mediaUseTV);
		} else {
			uactv.setMultimediaUses("");
		}
		logger.debug("Translating UACDBValues to UACDTextualValues... mediaUses: "
				+ uactv.getMultimediaUses());

		// sets backup and public properties of the media
		if (uacm.getIsBackup().charValue() == 'Y')
			uactv.setIsBackup(true);
		else
			uactv.setIsBackup(false);
		logger.debug("Translating UACDBValues to UACDTextualValues... IsBackup: "
				+ uactv.getIsBackup());

		if (uacm.getIsPublic().charValue() == 'Y')
			uactv.setIsPublic(true);
		else
			uactv.setIsPublic(false);
		logger.debug("Translating UACDBValues to UACDTextualValues... IsPublic: "
				+ uactv.getIsPublic());

		logger.debug("Translating UACDBValues to UACDTextualValues... done");
		return uactv;
	}

	/**
	 * 
	 * @param uactv
	 * @return
	 * FIXME: eliminar lo de static
	 */
	@SuppressWarnings("unchecked")
	public static UsesAndCopyrightsDTO toDBValues(UsesAndCopyrightsTV uactv)
			throws IllegalArgumentException {

		// variables
		logger.debug("Translating UACDTV to DBValues...");
		UsesAndCopyrightsDTO uacm = new UsesAndCopyrightsDTO();
		//List<Integer> mediaUseDBIdsList = new ArrayList<Integer>();
		List<MediaUseLite> mediaUsesList = new ArrayList<MediaUseLite>();
		MediaUseDAO mediaUseDAO = (MediaUseDAO) ServiceUtil.appContext.getBean("mediaUseDAO");
		MediaUseLite mediaUseLite = null;
		InstitutionLiteDTO institutionLite = null;
		UsePolicyDAO usePolicyDAO = (UsePolicyDAO) ServiceUtil.appContext.getBean("usePolicyDAO");
		UsePolicyLite usePolicyLite = null;
		AgentManager agentManager = (AgentManager) ServiceUtil.appContext.getBean(Properties.AGENT_MANAGER);
		PersonLiteDTO pLite = null;
		

		uacm.setMediaId(uactv.getMediaId());
		logger.debug("Translating UACDTV to DBValues... mediaId: "
				+ uacm.getMediaId());

		try {

			// author
			pLite = agentManager.getPersonLiteByName(uactv.getAuthor().getName());
			uacm.setAuthorId(new Integer(pLite.getPersonKey()));
			logger.debug("Translating UACDTV to DBValues... author: "
					+ uacm.getAuthorId());

			// media owner stuff
			if (uactv.getOwnerType() == MediaOwnerConstants.OWNER_INSTITUTION) {
				institutionLite = agentManager.getInstitutionLiteByName(uactv.getOwner());
				uacm.setInstitutionOwnerId(new Integer(institutionLite.getInstitutionKey()));
				uacm.setPersonOwnerId(null);

			} else {
				pLite = agentManager.getPersonLiteByName(uactv.getOwner());
				uacm.setPersonOwnerId(new Integer(pLite.getPersonKey()));
				uacm.setInstitutionOwnerId(null);
			}
			logger.debug("Translating UACDTextualValues to UACDBValues... "
					+ "INBioInstitution Owner: " + uacm.getInstitutionOwnerId());
			logger.debug("Translating UACDTextualValues to UACDBValues... "
					+ "M3SPerson Owner: " + uacm.getPersonOwnerId());

			// use policy
			usePolicyLite = usePolicyDAO.getUsePolicyLite(uactv.getUsePolicy());
			uacm.setUsePolicyID(usePolicyLite.getUsePolicyId());
			
			//uacm
			logger.debug("Translating UACDTextualValues to UACDBValues... "
					+ "Use Policy DBId: " + uacm.getUsePolicyID());

		} catch (IllegalArgumentException iae) {
			throw iae;
		}

		// Media Uses
		try {
			logger.debug("Translating UACDTextualValues to UACDBValues... "
					+ "getting media uses:");
			List<String> mediaUseTextualNameList = (List) StringUtil.getIndividualItems(uactv
					.getMultimediaUses(), String.class);

			logger.debug("Translating UACDTextualValues to UACDBValues... ["
					+ mediaUseTextualNameList.size() + "] media uses found");

			for(String mediaUseName : mediaUseTextualNameList){
				mediaUseLite = mediaUseDAO.getMediaUseLite(mediaUseName, new Integer(MessageManager.DEFAULT_LANGUAGE));
				mediaUsesList.add(mediaUseLite);
				logger.debug("Translating UACDTextualValues to UACDBValues... adding mediaUse id: '"
						+ mediaUseLite.getMediaUseId() + "'.");
			}
			uacm.setMediaUsesList(mediaUsesList);
			logger.debug("Translating UACDTextualValues to UACDBValues... media uses were set ["
							+ mediaUsesList.size() + "].");
		} catch (IllegalArgumentException iae) {
			throw new IllegalArgumentException("Los siguientes usos ["
					+ uactv.getMultimediaUses()
					+ "] presentan inforamcion que impiden poder usarlos. "
					+ iae.getMessage());
		} catch (Exception e) {
			throw new IllegalArgumentException("Los siguientes usos ["
					+ uactv.getMultimediaUses()
					+ "] presentan inforamcion que impiden poder usarlos.");
		}

		// sets backup and public properties of the media
		try {
			if (uactv.getIsBackup() == true)
				uacm.setIsBackup(new Character('Y'));
			else
				uacm.setIsBackup(new Character('N'));
			logger
					.debug("Translating UACDTextualValues to UACDBValues... Is BackUp: "
							+ uacm.getIsBackup());
		} catch (Exception e) {
			throw new IllegalArgumentException("La informacion "
					+ "correspondiente a 'es de respaldo' está erronea.");
		}

		try {
			if (uactv.getIsPublic() == true)
				uacm.setIsPublic(new Character('Y'));
			else
				uacm.setIsPublic(new Character('N'));
			logger
					.debug("Translating UACDTextualValues to UACDBValues... Is Public: "
							+ uacm.getIsPublic());
		} catch (Exception e) {
			throw new IllegalArgumentException("La informacion "
					+ "correspondiente a 'es de uso público' está erronea.");
		}

		logger.debug("Translating UACDTextualValues to UACDBValues... done");
		return uacm;
	}

	/**
	 * Converts the Technical Metada object that is Database ready to a Technical
	 * Metadata Text Values object that is ready to be used in the view widgets
	 * 
	 * @param tm
	 *          is the source TechnicalMetadata Object
	 * @return a equivalent widgets display ready TechnicalMetadataTV Object
	 * 
	 */
	/*
	public static TechnicalMetadataTV toTextualValues(TechnicalMetadataDTO tm) {
		logger.debug("Translating TMDBvalues to TM TextualValues...");
		TechnicalMetadataTV tmtv = new TechnicalMetadataTV();
		MediaTypeDAO mediaTypeDAO = (MediaTypeDAO) ServiceUtil.appContext.getBean("mediaTypeDAO");
		MediaTypeLite mediaTypeLite = null;
		MediaAttributeDAO maDAO = (MediaAttributeDAO) ServiceUtil.appContext.getBean("mediaAttributeDAO");
		MediaAttributeFull maFull = null;
		
		tmtv.setMediaId(tm.getMediaId());
		logger.debug("Translating TMDBvalues to TM TextualValues...mediaId set: "
				+ tmtv.getMediaId());

		mediaTypeLite = mediaTypeDAO.getMediaTypeLite(tm.getMediaTypeId(),MessageManager.DEFAULT_LANGUAGE);
		tmtv.setMediaType(mediaTypeLite.getMediaTypeName());
		logger
				.debug("Translating TMDBvalues to TM TextualValues...mediaTypeName set: "
						+ tmtv.getMediaType());

		//sacando los nombres
		List<String> tmNames = new ArrayList<String>();
		for(int maId : tm.getMediaAttributeIds()){
			maFull = maDAO.getMediaAttributeFull(maId);
			logger.debug("getting Media Attribute Names...adding for ["
					+ maId + "] the name: " + maFull.getName()+ ".");
			tmNames.add(maFull.getName());
		}
		tmtv.setNames(tmNames);
		
		logger.debug("Translating TMDBNames to TM Textual Names... ["
				+ tmtv.getNames().size()
				+ "] names were set correctly from a total of ["
				+ tm.getMediaAttributeIds().size() + "] DBIds.");

		// setting the textual values to be shown
		List<String> MATextualValues = new ArrayList<String>();
		List<Object> MADBValues = tm.getMediaAttributeValue();
		for (int i = 0; i < MADBValues.size(); i++) {
			
			if (MADBValues.get(i) == null) {
				
				MATextualValues.add("");
			} else {
				MATextualValues.add(((Object) MADBValues.get(i)).toString());
			}

		}
		tmtv.setValues(MATextualValues);
		logger.debug("Translating TMDBValues to TM Textual Values... ["
				+ tmtv.getValues().size()
				+ "] values were set correctly from a total of ["
				+ tm.getMediaAttributeValue().size() + "] DB values.");

		logger.debug("Translating TMDBvalues to TM TextualValues... done");
		return tmtv;
	}
	*/

	/**
	 * Converts the Technical Metadata Text Values that are ready to be used on the
	 * 'view' widgets to a object that is Database ready, a Technical Metadata
	 * Object
	 * 
	 * @param tmtv
	 *          is the source TechnicalMetadataTV object
	 * @return a equivalent DB ready TechnicalMetadata Object
	 */
	/*
	public static TechnicalMetadataDTO toDBValues(TechnicalMetadataTV tmtv) {

		logger.debug("Translating TM TextualValues to TMDBvalues...");
		TechnicalMetadataDTO tm = new TechnicalMetadataDTO();
		MediaTypeDAO mediaTypeDAO = (MediaTypeDAO) ServiceUtil.appContext.getBean("mediaTypeDAO");
		MediaTypeLite mediaTypeLite = null;
		MediaAttributeDAO maDAO = (MediaAttributeDAO) ServiceUtil.appContext.getBean("mediaAttributeDAO");
		MediaAttributeFull maFull = null;

		tm.setMediaId(tmtv.getMediaId());

		logger.debug("Translating TM TextualValues to TMDBvalues... mediaId set:"
				+ tm.getMediaId());

		mediaTypeLite = mediaTypeDAO.getMediaTypeLite(tmtv.getMediaType());
		tm.setMediaTypeId(mediaTypeLite.getMediaTypeId());
		logger.debug("Translating TM TextualValues to TMDBvalues... mediaTypeId set:"
						+ tm.getMediaTypeId());
		
		List<Integer> mediaAttributeIds = new ArrayList<Integer>();
		List<String> names = tmtv.getNames();
		for(String name : names){
			maFull = maDAO.getMediaAttributeFull(name);
			mediaAttributeIds.add(maFull.getMediaAttributeId());
		}
		tm.setMediaAttributeIds(mediaAttributeIds);
		logger.debug("Translating TM Textual Names to TM DBIds... ["
				+ tm.getMediaAttributeIds().size()
				+ "] DBIds were set correctly from a total of ["
				+ tmtv.getNames().size() + "] Names.");

		tm.setMediaAttributeValue(getMediaAttributesDBValues(	tmtv.getValues(), tm.getMediaAttributeIds()));
		logger.debug("Translating TM TextualValues to TMDBvalues... ["
				+ tm.getMediaAttributeValue().size()
				+ "] Values were set correctly from a total of ["
				+ tmtv.getValues().size() + "] Textual Values.");

		logger.debug("Translating TM TextualValues to TMDBvalues... done");
		return tm;
	}
	*/


	
	/**
	 * Gets the values to be writen on the database for each media attribute of
	 * the list
	 * 
	 * @param textualValues
	 * @param MediaAttributeDBId
	 * @return
	 * @throws IllegalArgumentException
	 */
	private static List<Object> getMediaAttributesDBValues(
			List<String> textualValues, List<Integer> MediaAttributeDBId)
			throws IllegalArgumentException {
		logger.debug("getMediaAttributesValues...");

		MediaAttributeDAO maDAO = (MediaAttributeDAO) ServiceUtil.appContext.getBean("mediaAttributeDAO");
		MediaAttributeFull maFull = null;

		List<Object> DBvalues = new ArrayList<Object>();
		// Integer maDBId;
		Character valueType;
		String mediaAttributeTextualValue;
		logger.debug("getMediaAttributesValues... 00 - textualValues size"
				+ textualValues.size());
		for (int i = 0; i < textualValues.size(); i++) {
			logger.debug("getMediaAttributesValues... gettin value for DBId["
					+ MediaAttributeDBId.get(i) + "] and textValue["
					+ textualValues.get(i) + "]");

			if (textualValues.get(i).compareTo("") == 0) {
				logger.debug("getMediaAttributesValues... value for["
						+ MediaAttributeDBId.get(i) + "] = null");
				DBvalues.add(null);
			} else {

				maFull = maDAO.getMediaAttributeFull(MediaAttributeDBId.get(i));
				logger.debug("getMediaAttributesValues... no es null");

				valueType = maFull.getMediaAttributeValueType();
				mediaAttributeTextualValue = (String) textualValues.get(i);

				switch (valueType) {

				case 'V':
					logger
							.debug("getMediaAttributesValues... value for["
									+ MediaAttributeDBId.get(i) + "] = "
									+ mediaAttributeTextualValue);
					DBvalues.add(mediaAttributeTextualValue);
					break;

				case 'N':
					logger.debug("getMediaAttributesValues... value for["
							+ MediaAttributeDBId.get(i) + "] = "
							+ new Integer(mediaAttributeTextualValue));
					DBvalues.add(new Integer(mediaAttributeTextualValue));
					break;

				case 'D':
					// dateFormat1 is the one of the metadata that comes
					// from the camara
					SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
					// dateFormat2 means is info from the DB
					SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date = null;
					try {
						date = dateFormat1.parse(mediaAttributeTextualValue);
					} catch (Exception e) {
						logger.debug("date viene directamente de la camara");
					}

					try {
						if (date == null) {
							date = dateFormat2.parse(mediaAttributeTextualValue);
						}

						Timestamp timestamp = new Timestamp(date.getTime());
						logger.debug("getMediaAttributesValues... value for["
								+ MediaAttributeDBId.get(i) + "] = " + timestamp);
						DBvalues.add(timestamp);

					} catch (Exception e) {
						logger.error("EXCEPTION -ERROR en attributo de tipo Date");
						logger.error(e.getMessage());
						DBvalues.add(null);
					}

					break;
				}
			}
		}
		return DBvalues;
	}
	


}
