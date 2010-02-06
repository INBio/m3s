/**
 * 
 */
package org.inbio.m3s.web.controller.metadata;


import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.dto.metadata.MetadataDTO;
import org.inbio.m3s.service.MetadataManager;
import org.inbio.m3s.web.exception.ValidationException;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author jgutierrez
 *
 */
public class UpdateMetadataController implements Controller {

	protected static Log logger = LogFactory.getLog(UpdateMetadataController.class);	

	//view and model
	private String viewName;//=editStep3	
	private String formActionKey;//=editFormAction
	private String formActionValue;//=editMedia.html
	
	//metadata Form
	private String fileNameCode;
	private String metadataUsername;
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

	//Error management
	private String errorViewName; //=editStep2
	private String errorFormActionKey;//=formAction
	private String errorFormActionValue;//=updateMetadata.html
	
	//Managers, Handlers and Utils (etc)
	private MetadataHandler metadataHandler;
	private MetadataManager metadataManager;
	
	
	
	public ModelAndView handleRequest(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
	
		//Create the view
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject(formActionKey, formActionValue);

		String mediaId = request.getParameter(fileNameCode);
		String userName = request.getParameter(metadataUsername);
		String title = request.getParameter(metadataTitle);
		String description = request.getParameter(metadataDescription);
		String mediaTypeId = request.getParameter(metadataMediaCategory);
		String projects = request.getParameter(metadataProjects);
		String keywords = request.getParameter(metadataKeywords);
		Integer associationTypeCode = Integer.valueOf(request.getParameter(metadataAssociatedToValueType));
		String associatedToValue = request.getParameter(metadataAssociatedToValue);
		String taxonomy = request.getParameter(metadataTaxonomy);
		String siteDescription = request.getParameter(metadataSiteDescription);
		String authorName = request.getParameter(metadataMediaAuthor);
		authorName = URLDecoder.decode(authorName, "UTF-8");
		Integer ownerTypeId = Integer.valueOf(request.getParameter(metadataOwnerType));
		String ownerName = request.getParameter(metadataOwnerValue);
		ownerName = URLDecoder.decode(ownerName, "UTF-8");
		String usePolicyKey = request.getParameter(metadataUsePolicy);
		String mediaVisible = request.getParameter(metadataMediaVisible);
		
		logger.debug("["+fileNameCode+"] mediaId: "+mediaId);
		logger.debug("userName: "+userName);
		logger.debug("title: "+title);
		logger.debug("description: "+description);
		logger.debug("mediaTypeId: "+mediaTypeId);
		logger.debug("projects: "+projects);
		logger.debug("keywords: "+keywords);
		logger.debug("associationTypeCode: "+associationTypeCode);
		logger.debug("associatedToValue: "+associatedToValue);
		logger.debug("taxonomy: "+taxonomy);
		logger.debug("siteDescription: "+siteDescription);
		logger.debug("author: "+authorName);
		logger.debug("ownerType: "+ownerTypeId);
		logger.debug("ownerValue: "+ownerName);
		logger.debug("usePolicy: "+usePolicyKey);
		logger.debug("mediaVisible: "+mediaVisible);
		
		try{
	
			MetadataDTO mDTO = metadataHandler.setMetadataDTO(mediaId, title, description, 
				mediaTypeId, null, siteDescription, projects, keywords, 
				associationTypeCode, associatedToValue, taxonomy, authorName, 
				ownerTypeId, ownerName, usePolicyKey, mediaVisible, userName); 
			
		
		logger.debug("updating metadata...");

		metadataManager.updateMetadata(mDTO);
		logger.debug("saving Metadata... saved");
		
		
		mav.addObject("mediaId", mediaId);
		logger.debug("Updated Media Id = "+mediaId);
		
		} catch(IllegalArgumentException iae){
			ValidationException ve = new ValidationException(iae.getMessage(), iae.getCause());
			
			ve.setViewName(errorViewName);
			ve.setErrorMessageKey("error.metadata.01");
			
			Map<String, Object> modelElements = new HashMap<String, Object>();
			modelElements.put("error", "ERROR: "+iae.getMessage());
			
			/*		 */
			modelElements.put(errorFormActionKey, errorFormActionValue);
			
			modelElements.put(fileNameCode, mediaId);
			modelElements.put("mediaId", mediaId);
			//modelElements.put(metadataUsername, userName);
			modelElements.put(metadataTitle, title);
			modelElements.put(metadataDescription, description);
			modelElements.put(metadataMediaCategory, mediaTypeId);
			modelElements.put(metadataProjects, projects);
			modelElements.put(metadataKeywords, keywords);
			modelElements.put(metadataAssociatedToValueType, associationTypeCode.toString());
			modelElements.put(metadataAssociatedToValue, associatedToValue);
			modelElements.put(metadataTaxonomy, taxonomy);
			modelElements.put(metadataSiteDescription, siteDescription);
			modelElements.put(metadataMediaAuthor, authorName);
			modelElements.put(metadataOwnerType, ownerTypeId.toString());
			modelElements.put(metadataOwnerValue, ownerName);
			modelElements.put(metadataUsePolicy, usePolicyKey);
			modelElements.put(metadataMediaVisible, mediaVisible);
			
			modelElements = metadataHandler.getMetadata(modelElements);
			
			ve.setModelElements(modelElements);
			logger.debug("throw ValidationException");
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
	 * @return the fileNameCode
	 */
	public String getFileNameCode() {
		return fileNameCode;
	}



	/**
	 * @param fileNameCode the fileNameCode to set
	 */
	public void setFileNameCode(String fileNameCode) {
		this.fileNameCode = fileNameCode;
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
