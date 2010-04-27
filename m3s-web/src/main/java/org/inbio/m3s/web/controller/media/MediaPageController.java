/**
 * 
 */
package org.inbio.m3s.web.controller.media;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.inbio.m3s.dto.agent.InstitutionLiteDTO;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.dto.message.ProjectDTO;
import org.inbio.m3s.dto.metadata.MetadataDTO;
import org.inbio.m3s.dto.metadata.TechnicalMetadataItemDTO;
import org.inbio.m3s.dto.metadata.UsePolicyDTO;
import org.inbio.m3s.dto.metadata.util.AssociatedToEntity;
import org.inbio.m3s.dto.metadata.util.MediaAttributeEntity;
import org.inbio.m3s.dto.metadata.util.MediaTypeEntity;
import org.inbio.m3s.dto.taxonomy.GatheringLiteDTO;
import org.inbio.m3s.dto.util.KeyValueDTO;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.service.MetadataManager;
import org.inbio.m3s.service.TaxonomyManager;
import org.inbio.m3s.util.StringUtil;
import org.inbio.m3s.web.controller.reusable.SimpleController;
import org.inbio.m3s.web.converter.TaxonGuiOrDTOConverter;
import org.inbio.m3s.web.exception.ValidationException;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jgutierrez
 *
 */
public class MediaPageController extends SimpleController{
	
	protected static Log logger = LogFactory.getLog(MediaPageController.class);
	
	//web parameters
	private String metadataId;
	private String metadataTitle;
	private String metadataDescription;
	private String mediaTypeIdKey;
	private String mediaTypeNameKey;//=mediaTypeName
	private String metadataProjects;
	private String metadataKeywords;
	private String metadataAssociatedToValueType;
	private String metadataAssociatedToValue;
	
	private String metadataTaxonomy; 
	private String metadataSiteDescription;
	private String metadataMediaAuthor;
	private String metadataOwnerType;
	private String metadataOwnerValue;
	private String metadataUsePolicy;
	private String metadataMediaVisible;
	
	//in case of error
	private String errorViewName = "mediaPage";
	
	private MessageManager messageManager;
	private MetadataManager metadataManager;
	private TaxonomyManager taxonomyManager;
	private AgentManager agentManager;
	
	private TaxonGuiOrDTOConverter taxonGuiOrDTOConverter;
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.web.controller.reusable.SimpleController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		ModelAndView mav = super.handleRequestInternal(request, response);
		
		//String mediaId = request.getParameter(metadataMediaId);
		//mav.addObject("mediaId", mediaId);
		
		ValidationException ve = new ValidationException();
		ve.setViewName(errorViewName);

		String mediaKey = request.getParameter(metadataId);
		if(StringUtils.isBlank(mediaKey)){
			//enviar msj de que esta jodido
			ve.setErrorMessageKey("error.edit.01");
			throw ve;
		} else if(!StringUtils.isNumeric(mediaKey)){
			//esta jodido porque tiene valores NO numericos
			ve.setErrorMessageKey("error.edit.02");
			throw ve;
		}
		
		MetadataDTO mDTO = null;
		try{
			mDTO = metadataManager.getMetadataByMedia(mediaKey);
			logger.debug("molt be");
		} catch(Exception e){
			logger.error("exception at metadataManager.getMetadataByMedia(mediaKey)");
			//esta jodido porque no se encontro nada para ese multimedio
			ve.setErrorMessageKey("error.edit.03");
			throw ve;
		}

		try{
			mav.addObject("mDTO",mDTO);
			
			mav.addObject(metadataId, mediaKey);
			mav.addObject(metadataTitle, mDTO.getTitle());
			mav.addObject(metadataDescription, mDTO.getDescription());
			
			//Tipo de multimedios
			MediaTypeEntity mediaType = MediaTypeEntity.getById(Integer.valueOf(mDTO.getMediaTypeKey()).intValue());
			mav.addObject(mediaTypeIdKey, mDTO.getMediaTypeKey());
			mav.addObject(mediaTypeNameKey, mediaType.getNamekey());
			
			TechnicalMetadataItemDTO maDTO;
			
			//Si es video saca los metadatos acá:
			if( mediaType == MediaTypeEntity.YOUTUBE_VIDEO) {
				maDTO = mDTO.getMediaAttributeItemByKey(String.valueOf(MediaAttributeEntity.YOUTUBE_ID.getMediaAtributeId()));
				logger.debug("el youtube video Id");
				logger.debug(maDTO.toString());
				mav.addObject("videoIdKey",maDTO.getValue());
			}
			
			//GPS georeferenciacion
			maDTO = mDTO.getMediaAttributeItemByKey(String.valueOf(MediaAttributeEntity.GPS_LATITUDE.getMediaAtributeId()));
			if( maDTO != null) { 
				logger.debug("el gpsLatitude");
				logger.debug(maDTO.toString());
				mav.addObject("gpsLatitudeKey",maDTO.getValue());
			}
			maDTO = mDTO.getMediaAttributeItemByKey(String.valueOf(MediaAttributeEntity.GPS_LONGITUDE.getMediaAtributeId()));
			if( maDTO != null) { 
				logger.debug("el gpsLongitude");
				logger.debug(maDTO.toString());
				mav.addObject("gpsLongitudeKey",maDTO.getValue());
			}
			
			//projects
			List<ProjectDTO> pDTOList = mDTO.getProjectsList();
			String projectsList ="";
			for(ProjectDTO pDTO : pDTOList)
				projectsList = projectsList + pDTO.getName() + StringUtil.TEXT_DELIMITER;
			mav.addObject(metadataProjects, projectsList);

			//keywords
			List<KeywordDTO> kDTOList = mDTO.getKeywordsList();
			String keywordsList ="";
			for(KeywordDTO kDTO : kDTOList)
				keywordsList = keywordsList + kDTO.getName() + StringUtil.TEXT_DELIMITER;
			mav.addObject(metadataKeywords, keywordsList);
			
			//Tipos de Asociaciones
			List<KeyValueDTO> associatedToValues = messageManager.getAllAssociatedToValues();
			mav.addObject("associatedToValues", associatedToValues);

			String associatedToType;
			String associatedToValue;

			logger.debug("Discovering the association type:");
			if (mDTO.getAssociatedSpecimensList() != null && mDTO.getAssociatedSpecimensList().size() != 0) {
				associatedToType = AssociatedToEntity.SPECIMEN_NUMBER.getNameKey();
				associatedToValue = mDTO.getAssociatedSpecimensList().get(0).getSpecimenKey();
			} else if (mDTO.getAssociatedObservationsList() != null && mDTO.getAssociatedObservationsList().size() != 0) {
				associatedToType = AssociatedToEntity.OBSERVATION_NUMBER.getNameKey();
				associatedToValue = mDTO.getAssociatedObservationsList().get(0).getObservationKey();			
			} else if (mDTO.getAssociatedGatheringsList() != null && mDTO.getAssociatedGatheringsList().size() != 0) {
				GatheringLiteDTO glDTO = mDTO.getAssociatedGatheringsList().get(0);		
				String gatheringPersonName = glDTO.getResponsiblePersonName();
				associatedToType = AssociatedToEntity.GATHERING_CODE.getNameKey();
				associatedToValue = gatheringPersonName + StringUtil.TEXT_DELIMITER + glDTO.getGatheringKey();
			} else {
				associatedToType = AssociatedToEntity.NO_ASSOCIATION.getNameKey();
				associatedToValue = "";
			}		

			logger.debug(" Associated Type: "+ associatedToType);
			logger.debug(" Associated Value: "+ associatedToValue);
			mav.addObject(metadataAssociatedToValueType, associatedToType);
			mav.addObject(metadataAssociatedToValue, associatedToValue);
			
		  //taxonomy
			mav.addObject(metadataTaxonomy, taxonGuiOrDTOConverter.toString(mDTO.getTaxonsList()));

			//site description
			mav.addObject(metadataSiteDescription, mDTO.getSiteDescription());

			//Author
			PersonLiteDTO authorPersonDTO = agentManager.getPersonLite(mDTO.getAuthorKey());
			mav.addObject(metadataMediaAuthor, authorPersonDTO.getName());
			mav.addObject("mediaAuthorEmailKey", authorPersonDTO.getEmail());

			//Tipos de Dueños de Imágenes -- esto debe ser eliminado
			logger.debug("personOwnerKey '"+mDTO.getPersonOwnerKey()+"'");
			logger.debug("institutionOwnerKey '"+mDTO.getInstitutionOwnerKey()+"'");
			//mav.addObject(metadataAssociatedToValueType, gmDTO.get);
			if(mDTO.getPersonOwnerKey() != null){
				logger.debug("person Owner");
				PersonLiteDTO ownerPersonDTO = agentManager.getPersonLite(mDTO.getPersonOwnerKey());
				mav.addObject(metadataOwnerValue, ownerPersonDTO.getName());
				//mav.addObject(metadataOwnerType, String.valueOf(OwnerEntity.PERSON.getId()));
			} else{
				logger.debug("institution Owner");
				InstitutionLiteDTO ownerInstitutionDTO = agentManager.getInstitutionLite(mDTO.getInstitutionOwnerKey());
				mav.addObject(metadataOwnerValue, ownerInstitutionDTO.getName());
				//mav.addObject(metadataOwnerType, String.valueOf(OwnerEntity.INSTITUTION.getId()));
			}			
			
		//Políticas de Uso
			UsePolicyDTO usePolicyDTO = messageManager.getUsePolicy(mDTO.getUsePolicyKey());
			mav.addObject(metadataUsePolicy, usePolicyDTO.getName());
			logger.debug(metadataUsePolicy+"'"+usePolicyDTO.getName()+"'");

			if (mDTO.getIsPublic().charValue() == 'Y')
				mav.addObject(metadataMediaVisible, "checked");
			else
				mav.addObject(metadataMediaVisible, null);		
			
			
		} catch (Exception e){
			//esta jodido cargando los metadatos :s
			ve.setErrorMessageKey("error.edit.04");
			throw ve;
		}
		
		mav.addObject("url", this.getHostUrl(request));
		
		return mav;
			
		
	}
	
	
	/**
	 * Get the host url.
	 * @param request
	 * @return
	 */
	private String getHostUrl(HttpServletRequest request) {
		StringBuffer hostUrl = new StringBuffer("http://");
		String host = request.getHeader("host");
		hostUrl.append(host);
		hostUrl.append(request.getContextPath());
		return hostUrl.toString();
	}


	/**
	 * @return the errorViewName
	 */
	public String getErrorViewName() {
		return errorViewName;
	}


	/**
	 * @param errorViewName the errorViewName to set
	 */
	public void setErrorViewName(String errorViewName) {
		this.errorViewName = errorViewName;
	}


	/**
	 * @return the metadataId
	 */
	public String getMetadataId() {
		return metadataId;
	}


	/**
	 * @param metadataId the metadataId to set
	 */
	public void setMetadataId(String metadataId) {
		this.metadataId = metadataId;
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
	 * @return the metadataTitle
	 */
	public String getMetadataTitle() {
		return metadataTitle;
	}


	/**
	 * @param metadataTitle the metadataTitle to set
	 */
	public void setMetadataTitle(String metadataTitle) {
		this.metadataTitle = metadataTitle;
	}


	/**
	 * @return the metadataDescription
	 */
	public String getMetadataDescription() {
		return metadataDescription;
	}


	/**
	 * @param metadataDescription the metadataDescription to set
	 */
	public void setMetadataDescription(String metadataDescription) {
		this.metadataDescription = metadataDescription;
	}


	/**
	 * @return the metadataProjects
	 */
	public String getMetadataProjects() {
		return metadataProjects;
	}


	/**
	 * @param metadataProjects the metadataProjects to set
	 */
	public void setMetadataProjects(String metadataProjects) {
		this.metadataProjects = metadataProjects;
	}


	/**
	 * @return the metadataKeywords
	 */
	public String getMetadataKeywords() {
		return metadataKeywords;
	}


	/**
	 * @param metadataKeywords the metadataKeywords to set
	 */
	public void setMetadataKeywords(String metadataKeywords) {
		this.metadataKeywords = metadataKeywords;
	}


	/**
	 * @return the metadataAssociatedToValueType
	 */
	public String getMetadataAssociatedToValueType() {
		return metadataAssociatedToValueType;
	}


	/**
	 * @param metadataAssociatedToValueType the metadataAssociatedToValueType to set
	 */
	public void setMetadataAssociatedToValueType(
			String metadataAssociatedToValueType) {
		this.metadataAssociatedToValueType = metadataAssociatedToValueType;
	}


	/**
	 * @return the metadataAssociatedToValue
	 */
	public String getMetadataAssociatedToValue() {
		return metadataAssociatedToValue;
	}


	/**
	 * @param metadataAssociatedToValue the metadataAssociatedToValue to set
	 */
	public void setMetadataAssociatedToValue(String metadataAssociatedToValue) {
		this.metadataAssociatedToValue = metadataAssociatedToValue;
	}


	/**
	 * @return the metadataTaxonomy
	 */
	public String getMetadataTaxonomy() {
		return metadataTaxonomy;
	}


	/**
	 * @param metadataTaxonomy the metadataTaxonomy to set
	 */
	public void setMetadataTaxonomy(String metadataTaxonomy) {
		this.metadataTaxonomy = metadataTaxonomy;
	}


	/**
	 * @return the metadataSiteDescription
	 */
	public String getMetadataSiteDescription() {
		return metadataSiteDescription;
	}


	/**
	 * @param metadataSiteDescription the metadataSiteDescription to set
	 */
	public void setMetadataSiteDescription(String metadataSiteDescription) {
		this.metadataSiteDescription = metadataSiteDescription;
	}


	/**
	 * @return the metadataMediaAuthor
	 */
	public String getMetadataMediaAuthor() {
		return metadataMediaAuthor;
	}


	/**
	 * @param metadataMediaAuthor the metadataMediaAuthor to set
	 */
	public void setMetadataMediaAuthor(String metadataMediaAuthor) {
		this.metadataMediaAuthor = metadataMediaAuthor;
	}


	/**
	 * @return the metadataOwnerType
	 */
	public String getMetadataOwnerType() {
		return metadataOwnerType;
	}


	/**
	 * @param metadataOwnerType the metadataOwnerType to set
	 */
	public void setMetadataOwnerType(String metadataOwnerType) {
		this.metadataOwnerType = metadataOwnerType;
	}


	/**
	 * @return the metadataOwnerValue
	 */
	public String getMetadataOwnerValue() {
		return metadataOwnerValue;
	}


	/**
	 * @param metadataOwnerValue the metadataOwnerValue to set
	 */
	public void setMetadataOwnerValue(String metadataOwnerValue) {
		this.metadataOwnerValue = metadataOwnerValue;
	}


	/**
	 * @return the metadataUsePolicy
	 */
	public String getMetadataUsePolicy() {
		return metadataUsePolicy;
	}


	/**
	 * @param metadataUsePolicy the metadataUsePolicy to set
	 */
	public void setMetadataUsePolicy(String metadataUsePolicy) {
		this.metadataUsePolicy = metadataUsePolicy;
	}


	/**
	 * @return the metadataMediaVisible
	 */
	public String getMetadataMediaVisible() {
		return metadataMediaVisible;
	}


	/**
	 * @param metadataMediaVisible the metadataMediaVisible to set
	 */
	public void setMetadataMediaVisible(String metadataMediaVisible) {
		this.metadataMediaVisible = metadataMediaVisible;
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


	/**
	 * @return the mediaTypeNameKey
	 */
	public String getMediaTypeNameKey() {
		return mediaTypeNameKey;
	}


	/**
	 * @param mediaTypeNameKey the mediaTypeNameKey to set
	 */
	public void setMediaTypeNameKey(String mediaTypeNameKey) {
		this.mediaTypeNameKey = mediaTypeNameKey;
	}


	/**
	 * @return the mediaTypeIdKey
	 */
	public String getMediaTypeIdKey() {
		return mediaTypeIdKey;
	}


	/**
	 * @param mediaTypeIdKey the mediaTypeIdKey to set
	 */
	public void setMediaTypeIdKey(String mediaTypeIdKey) {
		this.mediaTypeIdKey = mediaTypeIdKey;
	}


}

