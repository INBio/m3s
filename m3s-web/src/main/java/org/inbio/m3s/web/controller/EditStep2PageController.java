/**
 * 
 */
package org.inbio.m3s.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.inbio.m3s.dto.agent.InstitutionLiteDTO;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.dto.message.ProjectDTO;
import org.inbio.m3s.dto.metadata.MetadataDTO;
import org.inbio.m3s.dto.metadata.util.AssociatedToEntity;
import org.inbio.m3s.dto.metadata.util.OwnerEntity;
import org.inbio.m3s.dto.taxonomy.GatheringLiteDTO;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.MetadataManager;
import org.inbio.m3s.util.StringUtil;
import org.inbio.m3s.web.controller.metadata.MetadataHandler;
import org.inbio.m3s.web.converter.TaxonGuiOrDTOConverter;
import org.inbio.m3s.web.exception.ValidationException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author jgutierrez
 *
 */
public class EditStep2PageController extends AbstractController{
	
	//model and view
	private String viewName;//=editStep2
	private String formActionKey;//="formAction"
	private String formActionValue;//=updateMetadata.html
	
	//metadata values
	//private String metadataUsername;
	private String metadataId;
	private String metadataTitle;
	private String metadataDescription;
	private String metadataMediaCategory;
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
	private String errorViewName; //="insertStep1";
	private String errorFormActionKey; //="editFormAction"
	private String errorFormActionValue; //="editMedia.html"
	
	//managers, handlers, converters, utils & etc
	private TaxonGuiOrDTOConverter taxonGuiOrDTOConverter;
	private MetadataHandler metadataHandler;
	private MetadataManager metadataManager;
	private AgentManager agentManager;

	
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		//in case of success
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject(formActionKey, formActionValue);
		
		//error management
		ValidationException ve = new ValidationException();
		ve.setViewName(errorViewName);
		Map<String,Object> modelElements = new HashMap<String, Object>();
		modelElements.put(errorFormActionKey,errorFormActionValue);
		ve.setModelElements(modelElements);

		//media ID validation
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
		
		//getting the metadata
		MetadataDTO mDTO = null;
		try{
			mDTO = metadataManager.getMetadataByMedia(mediaKey);
		} catch(Exception e){
			//esta jodido porque no se encontro nada para ese multimedio
			ve.setErrorMessageKey("error.edit.03");
			throw ve;
		}

		try{
			mav.addObject(metadataId, mediaKey);
			logger.debug(metadataId+" "+mediaKey);
			mav.addObject(metadataTitle, mDTO.getTitle());
			logger.debug(metadataTitle+" "+mDTO.getTitle());
			mav.addObject(metadataDescription, mDTO.getDescription());

			//Tipo de multimedio seleccionado
			mav.addObject(metadataMediaCategory, mDTO.getMediaTypeKey());

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
			Integer associatedToType;
			String associatedToValue;

			logger.debug("Discovering the association type:");
			if (mDTO.getAssociatedSpecimensList() != null && mDTO.getAssociatedSpecimensList().size() != 0) {
				associatedToType = AssociatedToEntity.SPECIMEN_NUMBER.getId();
				associatedToValue = mDTO.getAssociatedSpecimensList().get(0).getSpecimenKey();
			} else if (mDTO.getAssociatedObservationsList() != null && mDTO.getAssociatedObservationsList().size() != 0) {
				associatedToType = AssociatedToEntity.OBSERVATION_NUMBER.getId();
				associatedToValue = mDTO.getAssociatedObservationsList().get(0).getObservationKey();			
			} else if (mDTO.getAssociatedGatheringsList() != null && mDTO.getAssociatedGatheringsList().size() != 0) {
				GatheringLiteDTO glDTO = mDTO.getAssociatedGatheringsList().get(0);		
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
			mav.addObject(metadataTaxonomy, taxonGuiOrDTOConverter.toString(mDTO.getTaxonsList()));

			//site description
			mav.addObject(metadataSiteDescription, mDTO.getSiteDescription());

			//Author
			PersonLiteDTO authorPersonDTO = agentManager.getPersonLite(mDTO.getAuthorKey());
			mav.addObject(metadataMediaAuthor, authorPersonDTO.getName());


			logger.debug("personOwnerKey '"+mDTO.getPersonOwnerKey()+"'");
			logger.debug("institutionOwnerKey '"+mDTO.getInstitutionOwnerKey()+"'");
			//mav.addObject(metadataAssociatedToValueType, gmDTO.get);
			if(mDTO.getPersonOwnerKey() != null){
				logger.debug("person Owner");
				PersonLiteDTO ownerPersonDTO = agentManager.getPersonLite(mDTO.getPersonOwnerKey());
				mav.addObject(metadataOwnerValue, ownerPersonDTO.getName());
				mav.addObject(metadataOwnerType, String.valueOf(OwnerEntity.PERSON.getId()));
			} else{
				logger.debug("institution Owner");
				InstitutionLiteDTO ownerInstitutionDTO = agentManager.getInstitutionLite(mDTO.getInstitutionOwnerKey());
				mav.addObject(metadataOwnerValue, ownerInstitutionDTO.getName());
				mav.addObject(metadataOwnerType, String.valueOf(OwnerEntity.INSTITUTION.getId()));
			}


			//Políticas de Uso
			mav.addObject(metadataUsePolicy, mDTO.getUsePolicyKey());
			logger.debug(metadataUsePolicy+"'"+mDTO.getUsePolicyKey()+"'");

			if (mDTO.getIsPublic().charValue() == 'Y')
				mav.addObject(metadataMediaVisible, "checked");
			else
				mav.addObject(metadataMediaVisible, null);
			
			//metadata JSP values
			Map<String,Object> metadataModelValues = new HashMap<String, Object>();
			metadataModelValues = metadataHandler.getMetadata(metadataModelValues);
			mav.addAllObjects(metadataModelValues);
			
		} catch (Exception e){
			//esta jodido cargando los metadatos :s
			ve.setErrorMessageKey("error.edit.04");
			throw ve;
		}
		
		
		return mav;
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
	 * @return the formActionKey
	 */
	public String getFormActionKey() {
		return formActionKey;
	}



	/**
	 * @param formActionKey the formActionKey to set
	 */
	public void setFormActionKey(String formActionKey) {
		this.formActionKey = formActionKey;
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
	 * @return the errorFormActionKey
	 */
	public String getErrorFormActionKey() {
		return errorFormActionKey;
	}



	/**
	 * @param errorFormActionKey the errorFormActionKey to set
	 */
	public void setErrorFormActionKey(String errorFormActionKey) {
		this.errorFormActionKey = errorFormActionKey;
	}



	/**
	 * @return the errorFormActionValue
	 */
	public String getErrorFormActionValue() {
		return errorFormActionValue;
	}



	/**
	 * @param errorFormActionValue the errorFormActionValue to set
	 */
	public void setErrorFormActionValue(String errorFormActionValue) {
		this.errorFormActionValue = errorFormActionValue;
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
	 * @return the metadataHandler
	 */
	public MetadataHandler getMetadataHandler() {
		return metadataHandler;
	}



	/**
	 * @param metadataHandler the metadataHandler to set
	 */
	public void setMetadataHandler(MetadataHandler metadataHandler) {
		this.metadataHandler = metadataHandler;
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
