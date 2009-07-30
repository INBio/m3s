/**
 * 
 */
package org.inbio.m3s.converters.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.gwt.associatedto.client.dto.AssociatedToConstants;
import org.inbio.gwt.associatedto.client.dto.AssociatedToInfo;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.converters.BaseConverter;
import org.inbio.m3s.converters.Converter;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.message.MediaCategoryDTO;
import org.inbio.m3s.dto.message.MediaTypeDTO;
import org.inbio.m3s.dto.metadata.GeneralMetadataDTO;
import org.inbio.m3s.dto.taxonomy.GatheringLiteDTO;
import org.inbio.m3s.dto.taxonomy.ObservationLiteDTO;
import org.inbio.m3s.dto.taxonomy.SpecimenLiteDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.gwt.client.dto.metadata.GeneralMetadataGWTDTO;
import org.inbio.m3s.gwt.client.dto.metadata.ProjectGWTDTO;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.service.TaxonomyManager;
import org.inbio.m3s.util.ServiceUtil;
import org.inbio.m3s.util.StringUtil;

/**
 * @author jgutierrez
 *
 */
public class GeneralMetadataConverter extends BaseConverter<GeneralMetadataDTO, GeneralMetadataGWTDTO> implements Converter<GeneralMetadataDTO, GeneralMetadataGWTDTO> {

	//Managers	
	MessageManager messageManager = (MessageManager) ServiceUtil.appContext.getBean(Properties.MESSAGE_MANAGER);
	AgentManager agentManager = (AgentManager) ServiceUtil.appContext.getBean(Properties.AGENT_MANAGER);
	TaxonomyManager taxonomyManager = (TaxonomyManager) ServiceUtil.appContext.getBean(Properties.TAXONOMY_MANAGER);
	
	//Converters
	ProjectConverter projectConverter = new ProjectConverter();
	KeywordConverter keywordConverter = new KeywordConverter();
	
	private static Logger logger = Logger.getLogger(GeneralMetadataConverter.class);


	public GeneralMetadataGWTDTO toGWTDTO(GeneralMetadataDTO dto) {
		if(dto == null)
			return null;
		
		GeneralMetadataGWTDTO gmGWTDTO = new GeneralMetadataGWTDTO(dto.getMediaKey(),dto.getTitle(),
				dto.getDescription(),null,null,projectConverter.toGWTDTOList(dto.getProjectsList()),dto.getSiteKey(),dto.getSiteDescription());
		 //* @param mediaCategoryKey
	// mediaType
		MediaTypeDTO mtDTO = messageManager.getMediaType(dto.getMediaTypeKey());
		gmGWTDTO.setMediaType(mtDTO.getMediaTypeName());
		logger.debug("Translating GMDBValues to GMTextValues... mediaType: "+ gmGWTDTO.getMediaType());
		// category
		MediaCategoryDTO mcDTO = messageManager.getMediaCategoryByType(dto.getMediaTypeKey());
		gmGWTDTO.setMediaCategory(mcDTO.getMediaCategoryName());
		logger.debug("Translating GMDBValues to GMTextValues... category: "+ gmGWTDTO.getMediaCategory());
		
		if (dto.getSiteKey()== null)
			gmGWTDTO.setSiteKey(null);
		
	// associations of the media:
		// FIXME: By now the mechanism of associations is not working good,
		// because you can only asociate a media to one thing (specimen,
		// observation, collectNumber, etc), this has to be improved to fix this
		// bug. Maybe the bug fix should be letting gmtv carry more than 1
		// textual value, Right now carries only 1 Integer value
		Integer associatedToType;
		String associatedToValue;
		
		logger.debug("Translating GMDBValues to GMTextValues... Discovering the association type:");
		if (dto.getAssociatedSpecimensList() != null && dto.getAssociatedSpecimensList().size() != 0) {
			logger.debug("Translating GMDBValues to GMTextValues... AssociatedSpecimen number> "
					+ dto.getAssociatedSpecimensList().size());
			
			//associatedTo
			associatedToType = AssociatedToConstants.SPECIMEN_NUMBER;
			associatedToValue = dto.getAssociatedSpecimensList().get(0).getSpecimenKey();
			gmGWTDTO.setAssociatedToInfo(new AssociatedToInfo(associatedToType, associatedToValue));
			logger.debug("Translating GMDBValues to GMTextValues... AssociatedSpecimen: "
							+ gmGWTDTO.getAssociatedToInfo().getType());
		} else if (dto.getAssociatedObservationsList() != null && dto.getAssociatedObservationsList().size() != 0) {
			associatedToType = AssociatedToConstants.OBSERVATION_NUMBER;
			associatedToValue = dto.getAssociatedObservationsList().get(0).getObservationKey();
			gmGWTDTO.setAssociatedToInfo(new AssociatedToInfo(associatedToType, associatedToValue));
			logger
					.debug("Translating GMDBValues to GMTextValues... AssociatedObservation: "
							+ gmGWTDTO.getAssociatedToInfo().getType());
		} else if (dto.getAssociatedGatheringsList() != null
				&& dto.getAssociatedGatheringsList().size() != 0) {
			
			GatheringLiteDTO glDTO = dto.getAssociatedGatheringsList().get(0);		
			String gatheringPersonName = glDTO.getResponsiblePersonName();
	
			associatedToType = AssociatedToConstants.GATHERING_CODE;
			associatedToValue = gatheringPersonName + StringUtil.TEXT_DELIMITER
			+ glDTO.getGatheringKey();
				
			gmGWTDTO.setAssociatedToInfo(new AssociatedToInfo(associatedToType, associatedToValue));
			
			logger.debug("Translating GMDBValues to GMTextValues... AssociatedCollection: "
							+ gmGWTDTO.getAssociatedToInfo().getType());
		} else {
			gmGWTDTO.setAssociatedToInfo(new AssociatedToInfo(AssociatedToConstants.NO_ASSOCIATION, ""));
			logger.debug("Translating GMDBValues to GMTextValues... No asociation.");
		}

		// Taxonomy
		logger.debug("Translating GMDBValues to GMTextValues... Looking for taxonomy:");
		List<String> taxonomyTV = new ArrayList<String>();
		if(dto.getTaxonsList() != null) {
			for(TaxonLiteDTO tlDTO  : dto.getTaxonsList()){
				taxonomyTV.add(tlDTO.getTaxonKey());
			}
		}
		gmGWTDTO.setTaxonomyInfo(taxonomyTV);
		logger.debug("Translating GMDBValues to GMTextValues... "
				+ "taxonomy elements: " + gmGWTDTO.getTaxonomyInfo().size());

		// keyWords
		gmGWTDTO.setKeywordsList(keywordConverter.toGWTDTOList(dto.getKeywordsList()));		
	
		// Projects
		gmGWTDTO.setProjectsList(projectConverter.toGWTDTOList(dto.getProjectsList()));
	
		
		gmGWTDTO.setSeries(null);
		gmGWTDTO.setSynopticColletion(null);		
		
		return gmGWTDTO;

	}
	
	public GeneralMetadataDTO toDTO(GeneralMetadataGWTDTO gwtdto) {
		if(gwtdto == null)
			return null;
		
		GeneralMetadataDTO gmDTO = new GeneralMetadataDTO(gwtdto.getMediaKey(), gwtdto.getTitle(),gwtdto.getDescription(),
				null, gwtdto.getSiteKey(),gwtdto.getSiteDescription());
		
			MediaTypeDTO mtDTO = messageManager.getMediaTypeByName(gwtdto.getMediaType());
			gmDTO.setMediaTypeKey(mtDTO.getMediaTypeKey());
			logger.debug("Translating GMTV to DBValues... MediaType: "
					+ gmDTO.getMediaTypeKey());
		
		
		List<SpecimenLiteDTO> associatedSpecimensList = new ArrayList<SpecimenLiteDTO>();
		List<ObservationLiteDTO> associatedObservationsList =  new ArrayList<ObservationLiteDTO>();
		List<GatheringLiteDTO> associatedGatheringsList = new ArrayList<GatheringLiteDTO>();
		String gatheringPersonName;
		String gatheringNumber;
		PersonLiteDTO pLite = null;
	// associations of the media:
		// FIXME: By now the mechanism of associations is not working good,
		// because you can only asociate a media to one thing (specimen,
		// observation, collectNumber, etc), this has to be improved to fix this
		// bug. Maybe the bug fix should be letting gmtv carry more than 1
		// value, rigths now only carries 1 Integer value

		// sets the associated values to a a new arrayList with size 0
		gmDTO.setAssociatedSpecimensList(associatedSpecimensList);
		gmDTO.setAssociatedObservationsList(associatedObservationsList);
		gmDTO.setAssociatedGatheringsList(associatedGatheringsList);
		logger.debug("Translating GMTV to DBValues... "
				+ "Associations now set to new ArrayList()");

		logger.debug("Association Type Value = " + gwtdto.getAssociatedToInfo().getType()+ ". On the GMTV Object");

		logger.debug("chequeando AssociatedTo.SPECIMEN_NUMBER ="+AssociatedToConstants.SPECIMEN_NUMBER.intValue());
		
		if (gwtdto.getAssociatedToInfo().getType().equals(AssociatedToConstants.SPECIMEN_NUMBER)) {
			logger.debug("Associated to Specimen Number");
			try {
				
				SpecimenLiteDTO slDTO = new SpecimenLiteDTO(gwtdto.getAssociatedToInfo().getValue());
				associatedSpecimensList.add(slDTO);
				gmDTO.setAssociatedSpecimensList(associatedSpecimensList);
				logger.debug("Translating GMTV to DBValues... "
						+ "Associated To Specimen Number.");
			} catch (Exception e) {
				logger.error("Número de especimen ["+ gwtdto.getAssociatedToInfo().getValue() + "] inválido");
				throw new IllegalArgumentException("Número de especimen ["+ gwtdto.getAssociatedToInfo().getValue() + "] inválido");
			}

		} else if (gwtdto.getAssociatedToInfo().getType().equals(AssociatedToConstants.OBSERVATION_NUMBER)) {
			logger.debug("Associated to Observation Number");
			try {
				ObservationLiteDTO olDTO = new ObservationLiteDTO(gwtdto.getAssociatedToInfo().getValue());
				associatedObservationsList.add(olDTO);
				gmDTO.setAssociatedObservationsList(associatedObservationsList);
				logger.debug("Translating GMTV to DBValues... "
						+ "Associated To Observation Number.");
			} catch (Exception e) {
				throw new IllegalArgumentException("Número de observacion ["
						+ gwtdto.getAssociatedToInfo().getValue() + "] inválido");
			}

		} else if (gwtdto.getAssociatedToInfo().getType().equals(AssociatedToConstants.GATHERING_CODE)) {
			logger.debug("Associated to Gathering Code");
			try {
				List<Object> temp = StringUtil.getIndividualItems(gwtdto.getAssociatedToInfo().getValue(),
						java.lang.String.class);

				gatheringPersonName = (String) temp.get(0);
				gatheringNumber = (String) temp.get(1);
				pLite = agentManager.getPersonLiteByName(gatheringPersonName);
				GatheringLiteDTO glDTO = new GatheringLiteDTO(gatheringNumber, pLite.getName());
				
				associatedGatheringsList.add(glDTO);
				gmDTO.setAssociatedGatheringsList(associatedGatheringsList);
				
				logger
						.debug("Translating GMTV to DBValues... Associated To Collect Number.");
			} catch (IllegalArgumentException iae) {
				throw new IllegalArgumentException("Código de Colecta ["
						+ gwtdto.getAssociatedToInfo().getValue() + "] inválido. " + iae.getMessage());
			} catch (Exception e) {
				throw new IllegalArgumentException("Código de Colecta ["
						+ gwtdto.getAssociatedToInfo().getValue() + "] inválido.");
			}
		}
		// else no association
		
		
		/*
		private List<ProjectLite> projectsList;
		private List<TaxonLiteDTO> taxonsList;
		private List<KeywordLiteDTO> keywordsList;
			 */
		// taxonomy
		TaxonLiteDTO tlDTO = null;
		try {
			logger.debug("Translating GMTV to DBValues... getting taxonomy:");
			List<TaxonLiteDTO> taxonLiteList = new ArrayList<TaxonLiteDTO>();

			if (gwtdto.getTaxonomyInfo() != null) {
				String literalTaxonId;
				for (int i = 0; i < gwtdto.getTaxonomyInfo().size(); i++) {
					literalTaxonId = (String) gwtdto.getTaxonomyInfo().get(i);
					tlDTO = taxonomyManager.getTaxonLiteById(literalTaxonId);
					taxonLiteList.add(tlDTO);
				}
			}
			gmDTO.setTaxonsList(taxonLiteList);
		} catch (Exception e) {
			throw new IllegalArgumentException("La siguiente taxonomía "
					+ "presenta errores que impiden poderla utilizar");
		}

		// GeneralMetadata >parameters of returning object
		// Keywords
		gmDTO.setKeywordsList(keywordConverter.toDTOList(gwtdto.getKeywordsList()));
		
		// Projects
		logger.debug("Projectos Antes");
		for(ProjectGWTDTO pGWTDTO : gwtdto.getProjectsList())
			logger.debug(pGWTDTO.toString());
		gmDTO.setProjectsList(projectConverter.toDTOList(gwtdto.getProjectsList()));
		logger.debug("Projectos Despues: "+gmDTO.getProjectsList().size());
		

	
		
		return gmDTO;
	}
	
	



}
