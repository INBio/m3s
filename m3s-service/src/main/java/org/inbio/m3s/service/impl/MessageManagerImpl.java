/**
 * 
 */
package org.inbio.m3s.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.inbio.m3s.dao.core.KeywordDAO;
import org.inbio.m3s.dao.core.MediaCategoryDAO;
import org.inbio.m3s.dao.core.MediaTypeDAO;
import org.inbio.m3s.dao.core.ProjectDAO;
import org.inbio.m3s.dao.core.TextTranslationDAO;
import org.inbio.m3s.dao.core.UsePolicyDAO;
import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.dto.message.MediaCategoryDTO;
import org.inbio.m3s.dto.message.MediaTypeDTO;
import org.inbio.m3s.dto.message.ProjectDTO;
import org.inbio.m3s.dto.metadata.UsePolicyDTO;
import org.inbio.m3s.model.core.Keyword;
import org.inbio.m3s.model.core.MediaCategory;
import org.inbio.m3s.model.core.MediaType;
import org.inbio.m3s.model.core.Project;
import org.inbio.m3s.model.core.TextTranslation;
import org.inbio.m3s.model.core.UsePolicy;
import org.inbio.m3s.service.MessageManager;

/**
 * @author jgutierrez
 *
 */
public class MessageManagerImpl implements MessageManager {
	
	private KeywordDAO keywordDAO;
	private TextTranslationDAO textTranslationDAO;
	private MediaTypeDAO mediaTypeDAO;
	private UsePolicyDAO usePolicyDAO;
	private MediaCategoryDAO mediaCategoryDAO;
	private ProjectDAO projectDAO;
	

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


	
	public KeywordDTO getKeywordLite(String keywordName, Integer languageId)
			throws IllegalArgumentException {
	
		KeywordDTO klDTO = keywordDAO.getKeywordLite(keywordName, languageId);
		
		return new KeywordDTO(klDTO.getKeywordKey(), klDTO.getName());
	}


	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MessageManager#getAllKeywordLite(java.lang.Integer)
	 */
	public List<KeywordDTO> getAllKeywordLite(Integer languageId)
			throws IllegalArgumentException {
		
		List<KeywordDTO> kDTOList = new ArrayList<KeywordDTO>();
		List<Object> kList = keywordDAO.findAll(Keyword.class);
		TextTranslation tt;
		
		for(Object k : kList){
			tt = textTranslationDAO.finByIdAndLanguage(((Keyword) k).getText().getTextId(), languageId);
			kDTOList.add(new KeywordDTO(((Keyword)k).getKeywordId().toString(), tt.getName()));
		}
		
		return kDTOList;
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
	public List<String> getTMRowTexts(String mediaTypeName) {
		//logger.debug("getting TM row texts...");
		MediaTypeDTO mediaTypeLite = mediaTypeDAO.getMediaTypeLite(mediaTypeName);
		
		//Integer mediaTypeDBId = MediaTypeDAO.getMediaTypeDBId(mediaTypeName);
		List<String> rowTexts = textTranslationDAO.findTechnicalMetadataTexts(Integer.valueOf(mediaTypeLite.getMediaTypeKey()), MessageManager.DEFAULT_LANGUAGE);
		//logger.debug("getting TM row texts... mediaType '" + mediaTypeName
		//		+ "' with DBId[:" + mediaTypeLite.getMediaTypeId() + "]");

		// if theres no arguments something its wrong
		if (rowTexts.size() == 0) {
			//logger
			//		.debug("getting TM row texts... No media Atributes for that mediaType");
			throw new IllegalArgumentException(
					"Check the arguments of the query");
		}

		//logger.debug("getting TM row texts... get [" + rowTexts.size()
		//		+ "] result");
		//logger.debug("getting TM row texts... done");
		return rowTexts;

	}

	public List<UsePolicyDTO> getAllUsePolicies() throws IllegalArgumentException {
		List<UsePolicyDTO> upDTOList = new ArrayList<UsePolicyDTO>();
		List<Object> upList =  usePolicyDAO.findAll(UsePolicy.class);
		TextTranslation tt;
		
		for(Object up : upList){
			tt = textTranslationDAO.finByIdAndLanguage(((UsePolicy) up).getTextByNameTextId().getTextId(), MessageManager.DEFAULT_LANGUAGE);
			upDTOList.add(new UsePolicyDTO(((UsePolicy) up).getUsePolicyId().toString(), tt.getName()));
		}
		
		return upDTOList;
	}
	
	public UsePolicyDTO getUsePolicy(String usePolicyKey) throws IllegalArgumentException {
		UsePolicy up = (UsePolicy) usePolicyDAO.findById(UsePolicy.class, Integer.valueOf(usePolicyKey));
		TextTranslation tt;
		tt = textTranslationDAO.finByIdAndLanguage(((UsePolicy) up).getTextByNameTextId().getTextId(), MessageManager.DEFAULT_LANGUAGE);
		return new UsePolicyDTO(usePolicyKey, tt.getName());
		
	}
	

	public List<MediaCategoryDTO> getAllMediaCategories() {
		List<MediaCategoryDTO> mcDTOList = new ArrayList<MediaCategoryDTO>();
		List<Object> mcList =   mediaCategoryDAO.findAll(MediaCategory.class);
		TextTranslation tt;
		
		for(Object mc : mcList){
			tt = textTranslationDAO.finByIdAndLanguage(((MediaCategory) mc).getTextByNameTextId().getTextId(), MessageManager.DEFAULT_LANGUAGE);
			mcDTOList.add(new MediaCategoryDTO(((MediaCategory) mc).getMediaCategoryId().toString(), tt.getName()));
		}
		
		return mcDTOList;
	}



	public MediaCategoryDTO getMediaCategoryByType(String mediaTypeKey) {
		
		MediaType mt = (MediaType) mediaTypeDAO.findById(MediaType.class, Integer.valueOf(mediaTypeKey));
		
		MediaCategory mc = (MediaCategory) mediaCategoryDAO.findById(MediaCategory.class, mt.getMediaCategoryId());
		TextTranslation tt;
		tt = textTranslationDAO.finByIdAndLanguage(((MediaCategory) mc).getTextByNameTextId().getTextId(), MessageManager.DEFAULT_LANGUAGE);
		return new MediaCategoryDTO(String.valueOf(mt.getMediaCategoryId()), tt.getName());
	}
	
	public MediaCategoryDTO getMediaCategory(String mediaCategoryKey) 
		throws IllegalArgumentException {
		MediaCategory mc = (MediaCategory) mediaCategoryDAO.findById(MediaCategory.class, Integer.valueOf(mediaCategoryKey));
		TextTranslation tt;
		tt = textTranslationDAO.finByIdAndLanguage(((MediaCategory) mc).getTextByNameTextId().getTextId(), MessageManager.DEFAULT_LANGUAGE);
		return new MediaCategoryDTO(mediaCategoryKey, tt.getName());
	}
	
	public List<MediaTypeDTO> getAllMediaTypes() {
		List<MediaTypeDTO> mtDTOList = new ArrayList<MediaTypeDTO>();
		List<Object> mtList =   mediaTypeDAO.findAll(MediaType.class);
		TextTranslation tt;
		
		for(Object mt : mtList){
			tt = textTranslationDAO.finByIdAndLanguage(((MediaCategory) mt).getTextByNameTextId().getTextId(), MessageManager.DEFAULT_LANGUAGE);
			mtDTOList.add(new MediaTypeDTO(((MediaType) mt).getMediaTypeId(), tt.getName()));
		}
		
		return mtDTOList;
	}



	public MediaTypeDTO getMediaType(String mediaTypeKey)
			throws IllegalArgumentException {
		MediaType mt = (MediaType) mediaTypeDAO.findById(MediaType.class, Integer.valueOf(mediaTypeKey));
		TextTranslation tt;
		tt = textTranslationDAO.finByIdAndLanguage(((MediaType) mt).getTextByNameTextId().getTextId(), MessageManager.DEFAULT_LANGUAGE);
		return new MediaTypeDTO(mediaTypeKey, tt.getName());
	}
	
	public MediaTypeDTO getMediaTypeByName(String mediaTypeName)
	throws IllegalArgumentException {
		MediaType mt = (MediaType) mediaTypeDAO.findByName(mediaTypeName);
		TextTranslation tt;
		tt = textTranslationDAO.finByIdAndLanguage(((MediaType) mt).getTextByNameTextId().getTextId(), MessageManager.DEFAULT_LANGUAGE);
		return new MediaTypeDTO(mt.getMediaTypeId(), tt.getName());
}



	public List<ProjectDTO> getAllProjects() {
		List<ProjectDTO> pDTOList = new ArrayList<ProjectDTO>();
		List<Object> pList =   projectDAO.findAll(Project.class);
		
		for(Object project : pList)
			pDTOList.add(new ProjectDTO( ((Project) project).getProjectId().toString(), ((Project) project).getName() ));
		
		return pDTOList;
	}



	public ProjectDTO getProjectById(String projectKey) {
		Project project = (Project) projectDAO.findById(UsePolicy.class, Integer.valueOf(projectKey));
		return new ProjectDTO(projectKey, project.getName());
	}



	public ProjectDTO getProjectByName(String projectName) throws IllegalArgumentException{
		Project project = (Project) projectDAO.findByName(projectName);
		return new ProjectDTO(String.valueOf(project.getProjectId()), project.getName());
	}
	
	/**
	 * @param keywordDAO the keywordDAO to set
	 */
	public void setKeywordDAO(KeywordDAO keywordDAO) {
		this.keywordDAO = keywordDAO;
	}

	/**
	 * @return the keywordDAO
	 */
	public KeywordDAO getKeywordDAO() {
		return keywordDAO;
	}

	/**
	 * @param textTranslationDAO the textTranslationDAO to set
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
	 * @param mediaCategoryDAO the mediaCategoryDAO to set
	 */
	public void setMediaCategoryDAO(MediaCategoryDAO mediaCategoryDAO) {
		this.mediaCategoryDAO = mediaCategoryDAO;
	}

	/**
	 * @return the mediaCategoryDAO
	 */
	public MediaCategoryDAO getMediaCategoryDAO() {
		return mediaCategoryDAO;
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



}
