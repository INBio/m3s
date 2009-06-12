/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.model.core.TextTranslation;

/**
 * @author jgutierrez
 *
 */
public interface TextTranslationDAO {
	
	TextTranslation finByIdAndLanguage(Integer id, Integer languageId);
	
	List<String> findTechnicalMetadataTexts(Integer mediaTypeId, Integer languageId);
	
	

}
