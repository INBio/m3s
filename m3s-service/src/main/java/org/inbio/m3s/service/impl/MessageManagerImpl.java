/**
 * 
 */
package org.inbio.m3s.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.m3s.dao.core.KeywordDAO;
import org.inbio.m3s.dao.core.MediaCategoryDAO;
import org.inbio.m3s.dao.core.MediaTypeDAO;
import org.inbio.m3s.dao.core.MediaUseDAO;
import org.inbio.m3s.dao.core.ProjectDAO;
import org.inbio.m3s.dao.core.TextTranslationDAO;
import org.inbio.m3s.dao.core.UsePolicyDAO;
import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.dto.message.MediaCategoryDTO;
import org.inbio.m3s.dto.message.MediaTypeDTO;
import org.inbio.m3s.dto.message.ProjectDTO;
import org.inbio.m3s.dto.message.ProjectDTOFactory;
import org.inbio.m3s.dto.metadata.MediaUseDTO;
import org.inbio.m3s.dto.metadata.UsePolicyDTO;
import org.inbio.m3s.dto.metadata.util.AssociatedToEntity;
import org.inbio.m3s.dto.metadata.util.OwnerEntity;
import org.inbio.m3s.dto.util.KeyValueDTO;
import org.inbio.m3s.exception.KeywordNotFoundException;
import org.inbio.m3s.exception.MediaTypeNotFoundException;
import org.inbio.m3s.exception.MediaUseNotFoundException;
import org.inbio.m3s.exception.ProjectNotFoundException;
import org.inbio.m3s.exception.UsePolicyNotFoundException;
import org.inbio.m3s.model.core.Keyword;
import org.inbio.m3s.model.core.MediaCategory;
import org.inbio.m3s.model.core.MediaType;
import org.inbio.m3s.model.core.Project;
import org.inbio.m3s.model.core.TextTranslation;
import org.inbio.m3s.model.core.UsePolicy;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.service.util.ImportFromFile;
import org.inbio.m3s.util.StringUtil;

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
	private MediaUseDAO mediaUseDAO;
	
	//dto Factory
	private ProjectDTOFactory projectDTOFactory;
	
	private static Logger logger = Logger.getLogger(ImportFromFile.class);
	

	
	public KeywordDTO getKeywordLite(String keywordName, Integer languageId) throws KeywordNotFoundException {
	
		KeywordDTO klDTO = keywordDAO.getKeywordLite(keywordName, languageId);
		if(klDTO==null)
			throw new KeywordNotFoundException("The keyword ["+keywordName+"] cannot be found the database", null, keywordName);
		
		return new KeywordDTO(klDTO.getKeywordKey(), klDTO.getName());
	}


	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MessageManager#getAllKeywordLite(java.lang.Integer)
	 */
	public List<KeywordDTO> getAllKeywordLite(Integer languageId)
			throws IllegalArgumentException {
		
		List<KeywordDTO> kDTOList = new ArrayList<KeywordDTO>();
		List<Keyword> kList = keywordDAO.findAll(Keyword.class);
		TextTranslation tt;
		
		for(Keyword k : kList){
			tt = textTranslationDAO.finByIdAndLanguage(k.getNameTextId(), languageId);
			kDTOList.add(new KeywordDTO(String.valueOf(k.getKeywordId()), tt.getName()));
		}
		
		return kDTOList;
	}
	/**
	 * Parsea el texto que viene del archivo excell, que tiene una estructura de
	 * valores separados por ';' y devuelve una lista de objetos TextInfo.
	 * 
	 * @param textualKeywords
	 *          String values separated by the default delimiter. (probably ';')
	 * @return
	 */
	public List<KeywordDTO> getKeywordsFromStringList(String textualKeywords) throws KeywordNotFoundException {
		
		
		KeywordDTO klDTO;
		List<KeywordDTO> klDTOList = new ArrayList<KeywordDTO>();
		
		List<Object> separatedValues = StringUtil.getIndividualItems(textualKeywords,java.lang.String.class);
		
		for(Object elem : separatedValues){
			klDTO = getKeywordLite((String)elem, MessageManager.DEFAULT_LANGUAGE);
			klDTOList.add(klDTO);
		}
		
		return klDTOList;
	}		


	public List<UsePolicyDTO> getAllUsePolicies() throws IllegalArgumentException {
		List<UsePolicyDTO> upDTOList = new ArrayList<UsePolicyDTO>();
		List<UsePolicy> upList =  usePolicyDAO.findAll(UsePolicy.class);
		TextTranslation tt;
		
		for(UsePolicy up : upList){
			tt = textTranslationDAO.finByIdAndLanguage(((UsePolicy) up).getTextByNameTextId().getTextId(), MessageManager.DEFAULT_LANGUAGE);
			upDTOList.add(new UsePolicyDTO(((UsePolicy) up).getUsePolicyId().toString(), tt.getName()));
		}
		
		return upDTOList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.MessageManager#getUsePolicy(java.lang.String)
	 */
	public UsePolicyDTO getUsePolicy(String usePolicyKey) throws IllegalArgumentException {
		UsePolicy up = (UsePolicy) usePolicyDAO.findById(UsePolicy.class, Integer.valueOf(usePolicyKey));
		TextTranslation tt;
		tt = textTranslationDAO.finByIdAndLanguage(((UsePolicy) up).getTextByNameTextId().getTextId(), MessageManager.DEFAULT_LANGUAGE);
		return new UsePolicyDTO(usePolicyKey, tt.getName());
		
	}
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.MessageManager#getUsePolicyByName(java.lang.String)
	 */
	public UsePolicyDTO getUsePolicyByName(String usePolicyName) throws UsePolicyNotFoundException {
		UsePolicy up = (UsePolicy) usePolicyDAO.findByName(usePolicyName);
		if(up==null)
			throw new UsePolicyNotFoundException("The use policy ["+usePolicyName+"] cannot be found the database", null, usePolicyName);
		//TextTranslation tt;
		//tt = textTranslationDAO.finByIdAndLanguage(((UsePolicy) up).getTextByNameTextId().getTextId(), MessageManager.DEFAULT_LANGUAGE);
		return new UsePolicyDTO(up.getUsePolicyId() , usePolicyName);
	}
	

	public List<MediaCategoryDTO> getAllMediaCategories() {
		List<MediaCategoryDTO> mcDTOList = new ArrayList<MediaCategoryDTO>();
		List<MediaCategory> mcList =   mediaCategoryDAO.findAll(MediaCategory.class);
		TextTranslation tt;
		
		for(MediaCategory mc : mcList){
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
		List<MediaType> mtList = mediaTypeDAO.findAll(MediaType.class);
		TextTranslation tt;
		
		for(MediaType mt : mtList){
			tt = textTranslationDAO.finByIdAndLanguage(mt.getMediaTypeNameTextId(), MessageManager.DEFAULT_LANGUAGE);
			mtDTOList.add(new MediaTypeDTO(mt.getMediaTypeId(), tt.getName()));
		}
		
		return mtDTOList;
	}



	public MediaTypeDTO getMediaType(String mediaTypeKey) throws IllegalArgumentException {
		MediaType mt = mediaTypeDAO.findById(MediaType.class, Integer.valueOf(mediaTypeKey));
		TextTranslation tt;
		tt = textTranslationDAO.finByIdAndLanguage(mt.getMediaTypeNameTextId(), MessageManager.DEFAULT_LANGUAGE);
		return new MediaTypeDTO(mediaTypeKey, tt.getName());
	}
	
	public MediaTypeDTO getMediaTypeByName(String mediaTypeName) throws MediaTypeNotFoundException {
		MediaType mt = (MediaType) mediaTypeDAO.findByName(mediaTypeName);
		if(mt==null)
			throw new MediaTypeNotFoundException("The media type ["+mediaTypeName+"] cannot be found the database", null, mediaTypeName);
		
		//TextTranslation tt;
		//tt = textTranslationDAO.finByIdAndLanguage(((MediaType) mt).getTextByNameTextId().getTextId(), MessageManager.DEFAULT_LANGUAGE);
		return new MediaTypeDTO(mt.getMediaTypeId(), mediaTypeName);
}



	public List<ProjectDTO> getAllProjects() {
		List<ProjectDTO> pDTOList = new ArrayList<ProjectDTO>();
		List<Project> pList =   projectDAO.findAll(Project.class);
		
		for(Project project : pList)
			pDTOList.add((ProjectDTO) projectDTOFactory.createDTO(project));
		
		return pDTOList;
	}



	public ProjectDTO getProjectById(String projectKey) {
		Project project = projectDAO.findById(Project.class, Integer.valueOf(projectKey));
		return (ProjectDTO) projectDTOFactory.createDTO(project);
	}



	public ProjectDTO getProjectByName(String projectName) throws ProjectNotFoundException{
		Project project = (Project) projectDAO.findByName(projectName);
		if(project==null)
			throw new ProjectNotFoundException("The project ["+projectName+"] cannot be found the database", null, projectName);
		return 	(ProjectDTO) projectDTOFactory.createDTO(project);
	}
	
	/**
	 * 
	 * @param projects
	 * @return
	 */
	public List<ProjectDTO> getProjectsFromStringList(String projects) {

		List<Object> projectsTextualNameList = StringUtil.getIndividualItems(projects, String.class);
		List<ProjectDTO> projectsList = new ArrayList<ProjectDTO>();
		
		logger.debug("Translating GMTV to DBValues... [" + projectsTextualNameList.size() + "] projects found");
		
		
		for(Object projectName : projectsTextualNameList){
			projectsList.add(getProjectByName((String)projectName));
			logger.debug("Translating GMTV to DBValues... adding project name: '" + (String)projectName + "'.");
		}
		
		return projectsList;
	}
	
	

	public MediaUseDTO getMediaUseByNameAndLanguage(String mediaUseName, String languageKey) throws MediaUseNotFoundException {
		MediaUseDTO muDTO = mediaUseDAO.findByNameAndLanguage(mediaUseName, languageKey);
		if(muDTO==null)
			throw new MediaUseNotFoundException("The media use ["+mediaUseName+"] cannot be found the database", null, mediaUseName);
		return muDTO;
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



	/**
	 * @param projectDTOFactory the projectDTOFactory to set
	 */
	public void setProjectDTOFactory(ProjectDTOFactory projectDTOFactory) {
		this.projectDTOFactory = projectDTOFactory;
	}



	/**
	 * @return the projectDTOFactory
	 */
	public ProjectDTOFactory getProjectDTOFactory() {
		return projectDTOFactory;
	}



	/**
	 * @param mediaUseDAO the mediaUseDAO to set
	 */
	public void setMediaUseDAO(MediaUseDAO mediaUseDAO) {
		this.mediaUseDAO = mediaUseDAO;
	}



	/**
	 * @return the mediaUseDAO
	 */
	public MediaUseDAO getMediaUseDAO() {
		return mediaUseDAO;
	}



	/**
	 * 
	 */
	public List<KeyValueDTO> getAllAssociatedToValues() {
		
		List<KeyValueDTO> atDTOList = new ArrayList<KeyValueDTO>();
		
		AssociatedToEntity[] associatedToEntities = AssociatedToEntity.values();
		for(AssociatedToEntity ate : associatedToEntities)
			atDTOList.add(new KeyValueDTO(String.valueOf(ate.getId()), ate.getNameKey()));
		 
		return atDTOList;
	}

	/**
	 * 
	 */
	public List<KeyValueDTO> getAllMediaOwnerValues() {
		List<KeyValueDTO> atDTOList = new ArrayList<KeyValueDTO>();
		OwnerEntity[] ownerEntities = OwnerEntity.values();
		for(OwnerEntity oe : ownerEntities)
			atDTOList.add(new KeyValueDTO(String.valueOf(oe.getId()), oe.getNameKey()));
		 
		return atDTOList;
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

}
