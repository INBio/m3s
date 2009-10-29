/**
 * 
 */
package org.inbio.m3s.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inbio.m3s.dto.agent.InstitutionLiteDTO;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.dto.message.MediaTypeDTO;
import org.inbio.m3s.dto.message.ProjectDTO;
import org.inbio.m3s.dto.metadata.GeneralMetadataDTO;
import org.inbio.m3s.dto.metadata.UsePolicyDTO;
import org.inbio.m3s.dto.metadata.UsesAndCopyrightsDTO;
import org.inbio.m3s.dto.metadata.util.AssociatedToEntity;
import org.inbio.m3s.dto.metadata.util.OwnerEntity;
import org.inbio.m3s.dto.taxonomy.GatheringLiteDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.dto.util.KeyValueDTO;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.MediaManager;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.service.TaxonomyManager;
import org.inbio.m3s.util.StringUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author jgutierrez
 *
 */
public class EditStep2PageController extends AbstractController{
	
	private String viewName;
	
	private String formActionValue;
	
	private String metadataUsername;
	private String metadataId;
	private String metadataTitle;
	private String metadataDescription;
	private String metadataMediaCategory;
	private String metadataProjects;
	private String metadataKeywords;
	private String metadataAssociatedToValueType;
	private String metadataAssociatedToValue;
	
	private String metadataTaxonomy; 
	private String metadataKingdom;
	private String metadataSiteDescription;
	private String metadataMediaAuthor;
	private String metadataOwnerType;
	private String metadataOwnerValue;
	private String metadataUsePolicy;
	private String metadataMediaVisible;	
	
	private MessageManager messageManager;
	private MediaManager mediaManager;
	private TaxonomyManager taxonomyManager;
	private AgentManager agentManager;
	
	
	public EditStep2PageController(){}

	protected ModelAndView handleRequestInternal(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("formAction", formActionValue);

		String mediaKey = request.getParameter(metadataId);
		
		GeneralMetadataDTO gmDTO = mediaManager.getGeneralMetadataByMedia(mediaKey);
		UsesAndCopyrightsDTO uacDTO = mediaManager.getUACM(mediaKey);
		
		mav.addObject(metadataId, mediaKey);
		mav.addObject(metadataTitle, gmDTO.getTitle());
		mav.addObject(metadataDescription, gmDTO.getDescription());
		
		//Tipos de multimedios
		List<MediaTypeDTO>  mediaTypes = messageManager.getAllMediaTypes();
		mav.addObject("mediaTypes", mediaTypes);
		mav.addObject(metadataMediaCategory, gmDTO.getMediaTypeKey());
		
	//projects and keywords
		List<ProjectDTO> pDTOList = gmDTO.getProjectsList();
		String projectsList ="";
		for(ProjectDTO pDTO : pDTOList)
			projectsList = projectsList + pDTO.getName() + StringUtil.TEXT_DELIMITER;
		mav.addObject(metadataProjects, projectsList);
		
		List<KeywordDTO> kDTOList = gmDTO.getKeywordsList();
		String keywordsList ="";
		for(KeywordDTO kDTO : kDTOList)
			keywordsList = keywordsList + kDTO.getName() + StringUtil.TEXT_DELIMITER;
		mav.addObject(metadataKeywords, keywordsList);
		
		//Tipos de Asociaciones
		List<KeyValueDTO> associatedToValues = messageManager.getAllAssociatedToValues();
		mav.addObject("associatedToValues", associatedToValues);
		
		Integer associatedToType;
		String associatedToValue;
		
		logger.debug("Discovering the association type:");
		if (gmDTO.getAssociatedSpecimensList() != null && gmDTO.getAssociatedSpecimensList().size() != 0) {
			associatedToType = AssociatedToEntity.SPECIMEN_NUMBER.getId();
			associatedToValue = gmDTO.getAssociatedSpecimensList().get(0).getSpecimenKey();
		} else if (gmDTO.getAssociatedObservationsList() != null && gmDTO.getAssociatedObservationsList().size() != 0) {
			associatedToType = AssociatedToEntity.OBSERVATION_NUMBER.getId();
			associatedToValue = gmDTO.getAssociatedObservationsList().get(0).getObservationKey();			
		} else if (gmDTO.getAssociatedGatheringsList() != null && gmDTO.getAssociatedGatheringsList().size() != 0) {
			GatheringLiteDTO glDTO = gmDTO.getAssociatedGatheringsList().get(0);		
			String gatheringPersonName = glDTO.getResponsiblePersonName();
			associatedToType = AssociatedToEntity.GATHERING_CODE.getId();
			associatedToValue = gatheringPersonName + StringUtil.TEXT_DELIMITER + glDTO.getGatheringKey();
		} else {
			associatedToType = AssociatedToEntity.NO_ASSOCIATION.getId();
			associatedToValue = "";
		}		
		
		logger.debug(" Associated Type: "+ associatedToType);
		logger.debug(" Associated Value: "+ associatedToValue);
		mav.addObject(metadataAssociatedToValueType, String.valueOf(associatedToType));
		mav.addObject(metadataAssociatedToValue, associatedToValue);
		
		//taxonomy
		if(gmDTO.getTaxonsList().size() > 0 ){
			mav.addObject(metadataTaxonomy, gmDTO.getTaxonsList().get(0).getDefaultName());
			TaxonLiteDTO kingdomDTO = taxonomyManager.getTaxonLiteById(gmDTO.getTaxonsList().get(0).getKingdomKey());
			mav.addObject(metadataKingdom, kingdomDTO.getDefaultName());
		}
		
		//site description
		mav.addObject(metadataSiteDescription, gmDTO.getSiteDescription());
		
		//Author
		PersonLiteDTO authorPersonDTO = agentManager.getPersonLite(uacDTO.getAuthorKey());
		mav.addObject(metadataMediaAuthor, authorPersonDTO.getName());
		
						
		//Tipos de Dueños de Imágenes
		List<KeyValueDTO> ownerValues = messageManager.getAllMediaOwnerValues();
		mav.addObject("mediaOwners", ownerValues);	
		
		logger.debug("personOwnerKey '"+uacDTO.getPersonOwnerKey()+"'");
		logger.debug("institutionOwnerKey '"+uacDTO.getInstitutionOwnerKey()+"'");
		//mav.addObject(metadataAssociatedToValueType, gmDTO.get);
		if(uacDTO.getPersonOwnerKey() != null){
			logger.debug("person Owner");
			PersonLiteDTO ownerPersonDTO = agentManager.getPersonLite(uacDTO.getPersonOwnerKey());
			mav.addObject(metadataOwnerValue, ownerPersonDTO.getName());
			mav.addObject(metadataOwnerType, String.valueOf(OwnerEntity.PERSON.getId()));
		} else{
			logger.debug("institution Owner");
			InstitutionLiteDTO ownerInstitutionDTO = agentManager.getInstitutionLite(uacDTO.getInstitutionOwnerKey());
			mav.addObject(metadataOwnerValue, ownerInstitutionDTO.getName());
			mav.addObject(metadataOwnerType, String.valueOf(OwnerEntity.INSTITUTION.getId()));
		}
		
		//Políticas de Uso
		List<UsePolicyDTO> usePolicies = messageManager.getAllUsePolicies();
		mav.addObject("usePolicies", usePolicies);
		mav.addObject(metadataUsePolicy, uacDTO.getUsePolicyKey());
		logger.debug(metadataUsePolicy+"'"+uacDTO.getUsePolicyKey()+"'");
		
		if (uacDTO.getIsPublic().charValue() == 'Y')
			mav.addObject(metadataMediaVisible, "checked");
		else
			mav.addObject(metadataMediaVisible, null);
		
		return mav;
	}

	/**
	 * @return the mediaManager
	 */
	public MediaManager getMediaManager() {
		return mediaManager;
	}

	/**
	 * @param mediaManager the mediaManager to set
	 */
	public void setMediaManager(MediaManager mediaManager) {
		this.mediaManager = mediaManager;
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
	 * @return the viewName
	 */
	public String getViewName() {
		return viewName;
	}

	/**
	 * @param viewName the viewName to set
	 */
	public void setViewName(String viewName) {
		this.viewName = viewName;
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
	 * @return the metadataMediaCategory
	 */
	public String getMetadataMediaCategory() {
		return metadataMediaCategory;
	}

	/**
	 * @param metadataMediaCategory the metadataMediaCategory to set
	 */
	public void setMetadataMediaCategory(String metadataMediaCategory) {
		this.metadataMediaCategory = metadataMediaCategory;
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
	 * @return the formActionValue
	 */
	public String getFormActionValue() {
		return formActionValue;
	}

	/**
	 * @param formActionValue the formActionValue to set
	 */
	public void setFormActionValue(String formActionValue) {
		this.formActionValue = formActionValue;
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
	 * @return the metadataKingdom
	 */
	public String getMetadataKingdom() {
		return metadataKingdom;
	}

	/**
	 * @param metadataKingdom the metadataKingdom to set
	 */
	public void setMetadataKingdom(String metadataKingdom) {
		this.metadataKingdom = metadataKingdom;
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
	 * @return the metadataUsername
	 */
	public String getMetadataUsername() {
		return metadataUsername;
	}

	/**
	 * @param metadataUsername the metadataUsername to set
	 */
	public void setMetadataUsername(String metadataUsername) {
		this.metadataUsername = metadataUsername;
	}

}
