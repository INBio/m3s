/**
 * 
 */
package org.inbio.m3s.service;

import java.util.List;

import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.dto.message.MediaCategoryDTO;
import org.inbio.m3s.dto.message.MediaTypeDTO;
import org.inbio.m3s.dto.message.ProjectDTO;
import org.inbio.m3s.dto.metadata.UsePolicyDTO;
import org.inbio.m3s.dto.util.KeyValueDTO;
import org.inbio.m3s.exception.KeywordNotFoundException;
import org.inbio.m3s.exception.MediaTypeNotFoundException;
import org.inbio.m3s.exception.ProjectNotFoundException;
import org.inbio.m3s.exception.UsePolicyNotFoundException;

/**
 * @author jgutierrez
 *
 */
public interface MessageManager {
	
	//locales
	public static String DEFAULT_LOCALE = "es";
	public static String SPANISH_LOCALE = "es";
	public static String ENGLISH_LOCALE = "en";
	public static String PORTUGUES_LOCALE = "pt";
 
	
	
	/**
	 * 
	 * @param keywordName
	 * @param locale
	 * @return
	 * @throws KeywordNotFoundException
	 */
	public KeywordDTO getKeywordLite(String keywordName, String locale) throws KeywordNotFoundException;
	
	/**
	 * 
	 * @param locale
	 * @return
	 * @throws IllegalArgumentException
	 */
	public List<KeywordDTO> getAllKeywordLite(String locale) throws IllegalArgumentException;
	
	/**
	 * Parsea el texto que viene del archivo excell, que tiene una estructura de
	 * valores separados por ';' y devuelve una lista de objetos TextInfo.
	 * 
	 * @param textualKeywords
	 *          String values separated by the default delimiter. (probably ';')
	 * @return
	 */
	public List<KeywordDTO> getKeywordsFromStringList(String textualKeywords) throws KeywordNotFoundException;
	
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
	
	public UsePolicyDTO getUsePolicyByName(String usePolicyName) throws UsePolicyNotFoundException;
	
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
	 * @throws MediaTypeNotFoundException
	 */
	public MediaTypeDTO getMediaType(String mediaTypeKey);
	
	
	public MediaTypeDTO getMediaTypeByName(String mediaTypeName) throws MediaTypeNotFoundException;
	
	/**
	 * 
	 * @return
	 */
	public ProjectDTO getProjectById(String projectKey);
	
	/**
	 * 
	 * @return
	 * @throws ProjectNotFoundException
	 * 
	 */
	public ProjectDTO getProjectByName(String projectName) throws ProjectNotFoundException;
	
	/**
	 * 
	 * @return
	 */
	public List<ProjectDTO> getAllProjects();

	/**
	 * The parameter should be a lis of projects separated using
	 * a delimiter defined in the StringUtil.getIndividualItems() method
	 * 
	 * @param projects
	 * @return
	 */
	public List<ProjectDTO> getProjectsFromStringList(String projects);
	

	/**
	 * 
	 * @param mediaUseName
	 * @param defaultLanguageKey
	 * @return
	 */
	//public MediaUseDTO getMediaUseByNameAndLanguage(String mediaUseName, String defaultLanguageKey) throws MediaUseNotFoundException ;
	
	/**
	 * The options for the associated to values
	 * 
	 * @return
	 */
	public List<KeyValueDTO> getAllAssociatedToValues();
	
	/**
	 * 
	 * @return
	 */
	public List<KeyValueDTO> getAllMediaOwnerValues();
}
