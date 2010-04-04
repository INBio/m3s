/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.GenericBaseDAO;
import org.inbio.m3s.model.core.TextTranslation;

/**
 * @author jgutierrez
 *
 */
public interface TextTranslationDAO extends GenericBaseDAO<TextTranslation, Integer>{
	
	TextTranslation finByIdAndLanguage(Integer id, String locale);
	
	@Deprecated
	List<String> findTechnicalMetadataTexts(Integer mediaTypeId, String locale);
	
	

}
