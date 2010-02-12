/**
 * 
 */
package org.inbio.m3s.web.controller.metadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.dao.core.SiteDAO;
import org.inbio.m3s.dto.agent.InstitutionLiteDTO;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.message.MediaTypeDTO;
import org.inbio.m3s.dto.metadata.MetadataDTO;
import org.inbio.m3s.dto.metadata.UsePolicyDTO;
import org.inbio.m3s.dto.metadata.util.AssociatedToEntity;
import org.inbio.m3s.dto.metadata.util.OwnerEntity;
import org.inbio.m3s.dto.taxonomy.GatheringLiteDTO;
import org.inbio.m3s.dto.taxonomy.ObservationLiteDTO;
import org.inbio.m3s.dto.taxonomy.SpecimenLiteDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.dto.util.KeyValueDTO;
import org.inbio.m3s.exception.*;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.service.SiteManager;
import org.inbio.m3s.service.util.ImportFileParser;
import org.inbio.m3s.util.StringUtil;
import org.inbio.m3s.web.converter.TaxonGuiOrDTOConverter;
import org.inbio.m3s.web.filter.FilterMapWrapper;

/**
 * Handles the metadata issues! 
 * Other controller don't have to worry of that just relegate.
 * 
 * 
 * @author jgutierrez
 *
 */
public class MetadataHandler {
	
	protected static Log logger = LogFactory.getLog(MetadataHandler.class);
	private MessageManager messageManager;
	private AgentManager agentManager;
	private SiteManager siteManager;
	private SiteDAO siteDAO;
	private TaxonGuiOrDTOConverter taxonGuiOrDTOConverter;
	
	private String metadataAssociatedToValues = "associatedToValues";
	private String metadataUsePolicies = "usePolicies";
	/* MediaOwner Widget */
  private FilterMapWrapper mediaOwnerFilters;
  private String mediaOwnerFiltersRequestKey;
  
	/**
	 * Este metodo por ahora SOLO trae los metadatos necesarios para dropdowns y esas varas
	 * 
	 * @param modelElements
	 * @return
	 */
	public Map<String,Object> getMetadata(Map<String,Object> modelElements){
				
		//Tipos de Asociaciones
		List<KeyValueDTO> associatedToValues = messageManager.getAllAssociatedToValues();
		modelElements.put(metadataAssociatedToValues, associatedToValues);
		
	  //Owner Widget -- usando en el mediaOwner.jsp
		modelElements.put(mediaOwnerFiltersRequestKey, mediaOwnerFilters.getFilters());
		
		//Políticas de Uso
		List<UsePolicyDTO> usePolicies = messageManager.getAllUsePolicies();
		modelElements.put(metadataUsePolicies, usePolicies);
		
		return modelElements;
	}
	
	
	/*
	 *  Copy & Paste de ImportFromFile.java
	 * 
	 */
	public MetadataDTO setMetadataDTO(String mediaKey, String title, String description,
			String mediaTypeKey, String siteKey, String siteDescription, String projects, String keywords, 
			Integer associationTypeCode, String associatedToValue, String taxonomy,String authorName, 
			Integer ownerTypeId, String ownerName, String usePolicyKey, String mediaVisible,
			String username ) throws IllegalArgumentException {
		
		//Main object to populate
		MetadataDTO mDTO = new MetadataDTO(mediaKey,title,description,mediaTypeKey,
				siteKey,siteDescription,username);
		
		
		List<TaxonLiteDTO> taxonsList = new ArrayList<TaxonLiteDTO>();
		
		try {
			//el primer nulo es el mediaId, como es nuevo siempre ira en null
			//y el el otro valor que esta en null es el siteKey... ¿?
			mDTO.setProjectsList(messageManager.getProjectsFromStringList(projects));
			mDTO.setKeywordsList(messageManager.getKeywordsFromStringList(keywords));
			
			//AssociatedTo....			
			List<SpecimenLiteDTO> associatedSpecimensList = new ArrayList<SpecimenLiteDTO>();
			mDTO.setAssociatedSpecimensList(associatedSpecimensList);
			List<ObservationLiteDTO> associatedObservationsList =  new ArrayList<ObservationLiteDTO>();
			mDTO.setAssociatedObservationsList(associatedObservationsList);
			List<GatheringLiteDTO> associatedGatheringsList = new ArrayList<GatheringLiteDTO>();
			mDTO.setAssociatedGatheringsList(associatedGatheringsList);
			
			if (associationTypeCode.equals(AssociatedToEntity.SPECIMEN_NUMBER.getId())) {
				logger.debug("Associated to Specimen Number");
					
					SpecimenLiteDTO slDTO = new SpecimenLiteDTO(associatedToValue);
					associatedSpecimensList.add(slDTO);
					mDTO.setAssociatedSpecimensList(associatedSpecimensList);
					logger.debug("Associated To Specimen Number... done");
			
			} else if (associationTypeCode.equals(AssociatedToEntity.OBSERVATION_NUMBER.getId())) {
				logger.debug("Associated to Observation Number");
				ObservationLiteDTO olDTO = new ObservationLiteDTO(associatedToValue);
				associatedObservationsList.add(olDTO);
				mDTO.setAssociatedObservationsList(associatedObservationsList);
				logger.debug("Associated To Observation Number... done");

			} else if (associationTypeCode.equals(AssociatedToEntity.GATHERING_CODE.getId())) {
				logger.debug("Associated to Gathering Code");
				List<Object> temp = StringUtil.getIndividualItems(associatedToValue,java.lang.String.class);
				
				String gatheringPersonName = (String) temp.get(0);
				String gatheringNumber = (String) temp.get(1);
				PersonLiteDTO pLite = agentManager.getGatheringResposibleLiteByName(gatheringPersonName);
				logger.debug(pLite.toString());
				GatheringLiteDTO glDTO = new GatheringLiteDTO(gatheringNumber, pLite.getName());
				logger.debug(glDTO.toString());
				
				associatedGatheringsList.add(glDTO);
				mDTO.setAssociatedGatheringsList(associatedGatheringsList);
					
				logger.debug("Associated To Collect Number... done");
			}
			
			//taxonomy
			logger.debug("taxonomy: '" + taxonomy + "'");
			taxonsList = taxonGuiOrDTOConverter.toDTOList(taxonomy);
			mDTO.setTaxonsList(taxonsList);
			logger.debug("Taxonomy elements: '" + mDTO.getTaxonsList().size() + "'");

			
		// TODO: has to fix the siteId stuff
			// site Description
			if (siteDescription == null || siteDescription.compareTo("") == 0) {

				if (associationTypeCode.equals(AssociatedToEntity.SPECIMEN_NUMBER.getId())) {
					siteDescription = siteDAO.getSiteDBIdFromSpecimenNumber(new Integer(associatedToValue));
				} else if (associationTypeCode.equals(AssociatedToEntity.OBSERVATION_NUMBER.getId())) {
					siteDescription = siteDAO.getiteDBIdFromObservationNumber(new Integer(associatedToValue));
				} else if (associationTypeCode.equals(AssociatedToEntity.GATHERING_CODE.getId())) {
					siteDescription = siteManager.getSiteFromGatheringCode(associatedToValue);
				}
			}
			mDTO.setSiteDescription(siteDescription);
			mDTO.setSiteKey(null);
			logger.debug("Site Description: '" + mDTO.getSiteDescription() + "'");
			logger.debug("Site Key: '" + mDTO.getSiteKey() + "'");
			
			// set author
			PersonLiteDTO plDTO = agentManager.getPersonLiteByName(authorName);
			mDTO.setAuthorKey(plDTO.getPersonKey());
			logger.debug("Author Key: '" + mDTO.getAuthorKey() + "'");
			
			if (ownerTypeId.equals(OwnerEntity.INSTITUTION.getId())) {
				InstitutionLiteDTO iLiteDTO = agentManager.getInstitutionLiteByName(ownerName);
				mDTO.setInstitutionOwnerKey(iLiteDTO.getInstitutionKey());
				mDTO.setPersonOwnerKey(null);
			} else if (ownerTypeId.equals(OwnerEntity.PERSON.getId())) {
				PersonLiteDTO oplDTO = agentManager.getPersonLiteByName(ownerName);
				mDTO.setPersonOwnerKey(oplDTO.getPersonKey());
				mDTO.setInstitutionOwnerKey(null);
			} else {
				logger.error("No valid owner Type... debería tirarse una excepcion");	
			}
			logger.debug("Author Owner Type: '" + mDTO.getAuthorKey() + "'");
			logger.debug("Institution Owner Type: '" + mDTO.getInstitutionOwnerKey() + "'");
	
			// use policy
			mDTO.setUsePolicyKey(usePolicyKey);
			logger.debug("Use policy: '" + mDTO.getUsePolicyKey() + "'");	

			// mediaUses
			//uacDTO.setMediaUsesList(new ArrayList<MediaUseDTO>());
	
			// visible value
			if(mediaVisible == null){
				mDTO.setIsPublic(new Character('N'));			
			} else{
				mDTO.setIsPublic(new Character('Y'));
			}
			
			logger.debug("Is public: '" + mDTO.getIsPublic() + "'");

			
			
			return mDTO;
			
		} catch (MediaTypeNotFoundException mtnfe) {
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el Taxon: '" + mtnfe.getNotFoundMediaType() + "' en la Base de Datos.");
			throw new IllegalArgumentException(mtnfe.getMessage());
		
		} catch (ProjectNotFoundException pnfe) {
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el Proyecto: '" + pnfe.getNotFoundProject() + "' en la Base de Datos.");
			throw new IllegalArgumentException(pnfe.getMessage());
		
		} catch (KeywordNotFoundException knfe) {
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar la Palabra Clave: '" + knfe.getNotFoundKeyword() + "' en la Base de Datos.");
			throw new IllegalArgumentException(knfe.getMessage());
			
		} catch (TaxonNotFoundException tnfe) {
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el Taxon: '" + tnfe.getNotFoundTaxonName() + "' en la Base de Datos.");
			throw new IllegalArgumentException(tnfe.getMessage());
		
		} catch (AssociationTypeNotFoundException atnfe){
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el Taxon: '" + atnfe.getNotFoundAssociationType() + "' en la Base de Datos.");
			throw new IllegalArgumentException(atnfe.getMessage());

		} catch (PersonNotFoundException pnfe) {
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar la Persona: '" + pnfe.getNotFoundPerson() + "' en la Base de Datos.");
			throw new IllegalArgumentException(pnfe.getMessage());
		
		} catch (InstitutionNotFoundException infe) {
		  logger.error(ImportFileParser.ERROR + " 'No se puede encontrar la Institucion: '" + infe.getNotFoundInstitution() + "' en la Base de Datos.");
		  throw new IllegalArgumentException(infe.getMessage());
		  
		} catch (OwnerTypeNotFoundException otnfe){
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el Tipo de dueño: '" + otnfe.getNotFoundOwnerType() + "' en la Base de Datos.");
			throw new IllegalArgumentException(otnfe.getMessage());
		
		} catch (MediaUseNotFoundException munfe){
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el Uso para el Medio: '" + munfe.getNotFoundMediaUse() + "' en la Base de Datos.");
			throw new IllegalArgumentException(munfe.getMessage());

		} catch (YesNoValueNotFoundException ynvnfe){
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el valor: '" + ynvnfe.getNotFoundYesNoValue() + "' como afirmativo o negativo..");
			throw new IllegalArgumentException(ynvnfe.getMessage());
		
		}	catch (Exception e) {
			logger.error(ImportFileParser.ERROR + " '" + e.getMessage() + "'");
			throw new IllegalArgumentException(e.getMessage());
		}
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


	/**
	 * @return the siteManager
	 */
	public SiteManager getSiteManager() {
		return siteManager;
	}


	/**
	 * @param siteManager the siteManager to set
	 */
	public void setSiteManager(SiteManager siteManager) {
		this.siteManager = siteManager;
	}


	/**
	 * @return the siteDAO
	 */
	public SiteDAO getSiteDAO() {
		return siteDAO;
	}


	/**
	 * @param siteDAO the siteDAO to set
	 */
	public void setSiteDAO(SiteDAO siteDAO) {
		this.siteDAO = siteDAO;
	}


	/**
	 * @return the taxonGuiOrDTOConverter
	 */
	public TaxonGuiOrDTOConverter getTaxonGuiOrDTOConverter() {
		return taxonGuiOrDTOConverter;
	}


	/**
	 * @param taxonGuiOrDTOConverter the taxonGuiOrDTOConverter to set
	 */
	public void setTaxonGuiOrDTOConverter(
			TaxonGuiOrDTOConverter taxonGuiOrDTOConverter) {
		this.taxonGuiOrDTOConverter = taxonGuiOrDTOConverter;
	}


	/**
	 * @return the metadataAssociatedToValues
	 */
	public String getMetadataAssociatedToValues() {
		return metadataAssociatedToValues;
	}


	/**
	 * @param metadataAssociatedToValues the metadataAssociatedToValues to set
	 */
	public void setMetadataAssociatedToValues(String metadataAssociatedToValues) {
		this.metadataAssociatedToValues = metadataAssociatedToValues;
	}


	/**
	 * @return the metadataUsePolicies
	 */
	public String getMetadataUsePolicies() {
		return metadataUsePolicies;
	}


	/**
	 * @param metadataUsePolicies the metadataUsePolicies to set
	 */
	public void setMetadataUsePolicies(String metadataUsePolicies) {
		this.metadataUsePolicies = metadataUsePolicies;
	}


	/**
	 * @return the mediaOwnerFilters
	 */
	public FilterMapWrapper getMediaOwnerFilters() {
		return mediaOwnerFilters;
	}


	/**
	 * @param mediaOwnerFilters the mediaOwnerFilters to set
	 */
	public void setMediaOwnerFilters(FilterMapWrapper mediaOwnerFilters) {
		this.mediaOwnerFilters = mediaOwnerFilters;
	}


	/**
	 * @return the mediaOwnerFiltersRequestKey
	 */
	public String getMediaOwnerFiltersRequestKey() {
		return mediaOwnerFiltersRequestKey;
	}


	/**
	 * @param mediaOwnerFiltersRequestKey the mediaOwnerFiltersRequestKey to set
	 */
	public void setMediaOwnerFiltersRequestKey(String mediaOwnerFiltersRequestKey) {
		this.mediaOwnerFiltersRequestKey = mediaOwnerFiltersRequestKey;
	}
	
}
