/**
 * 
 */
package org.inbio.m3s.service;

import java.util.List;

import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.dto.message.MediaCategoryDTO;
import org.inbio.m3s.dto.message.MediaTypeDTO;
import org.inbio.m3s.dto.message.ProjectDTO;
import org.inbio.m3s.dto.metadata.MediaUseDTO;
import org.inbio.m3s.dto.metadata.UsePolicyDTO;

/**
 * @author jgutierrez
 *
 */
public interface MessageManager {
	
	// languagues
	public static Integer ESPANYOL = new Integer(1);
	public static String ESPANYOL_KEY = "1";

	public static Integer ENGLISH = new Integer(2);
	public static String ENGLISH_KEY = "2";
	
	public static Integer DEFAULT_LANGUAGE = ESPANYOL;
	public static String DEFAULT_LANGUAGE_KEY = ESPANYOL_KEY;
	
	
	/**
	 * 
	 * @param keywordName
	 * @param languageId
	 * @return
	 * @throws IllegalArgumentException
	 */
	public KeywordDTO getKeywordLite(String keywordName, Integer languageId) throws IllegalArgumentException;
	
	/**
	 * 
	 * @param languageId
	 * @return
	 * @throws IllegalArgumentException
	 */
	public List<KeywordDTO> getAllKeywordLite(Integer languageId) throws IllegalArgumentException;
	
	/**
	 * @deprecated
	 * @param mediaTypeName
	 * @return
	 */
	public List<String> getTMRowTexts(String mediaTypeName);
	
	/**
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 */
	public List<UsePolicyDTO> getAllUsePolicies() throws IllegalArgumentException;
	
	/**
	 * 
	 * @param usePolicyKey
	 * @return
	 * @throws IllegalArgumentException
	 */
	public UsePolicyDTO getUsePolicy(String usePolicyKey) throws IllegalArgumentException;
	
	public UsePolicyDTO getUsePolicyByName(String usePolicyName) throws IllegalArgumentException;
	
	/**
	 * 
	 * @param mediaTypeKey
	 * @return
	 */
	public MediaCategoryDTO getMediaCategoryByType(String mediaTypeKey);
	
	/**
	 * 
	 * @return
	 */
	public List<MediaCategoryDTO> getAllMediaCategories();
	
	/**
	 * 
	 * @param mediaCategoryKey
	 * @return
	 * @throws IllegalArgumentException
	 */
	public MediaCategoryDTO getMediaCategory(String mediaCategoryKey) throws IllegalArgumentException;
	
	/**
	 * 
	 * @return
	 */
	public List<MediaTypeDTO> getAllMediaTypes();
	
	/**
	 * 
	 * @param mediaTypeName
	 * @return
	 * @throws IllegalArgumentException
	 */
	public MediaTypeDTO getMediaType(String mediaTypeKey) throws IllegalArgumentException;
	
	
	public MediaTypeDTO getMediaTypeByName(String mediaTypeName) throws IllegalArgumentException;
	
	/**
	 * 
	 * @return
	 */
	public ProjectDTO getProjectById(String projectKey);
	
	/**
	 * 
	 * @return
	 */
	public ProjectDTO getProjectByName(String projectName) throws IllegalArgumentException;
	
	/**
	 * 
	 * @return
	 */
	public List<ProjectDTO> getAllProjects();

	/**
	 * 
	 * @param mediaUseName
	 * @param defaultLanguageKey
	 * @return
	 */
	public MediaUseDTO getMediaUseByNameAndLanguage(String mediaUseName, String defaultLanguageKey);
}
