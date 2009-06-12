/**
 * 
 */
package org.inbio.m3s.dao.core;


import java.util.List;

import org.inbio.m3s.dto.message.KeywordLiteDTO;
import org.inbio.m3s.model.core.Keyword;

/**
 * @author jgutierrez
 *
 */
public interface KeywordDAO {
	
	//public KeywordDTOLite getKeywordLite(Integer keywordId, Integer languageId) throws IllegalArgumentException;
	
	//implemntar solucion
	public KeywordLiteDTO getKeywordLite(String keywordName, Integer languageId) throws IllegalArgumentException;
	
	public List<KeywordLiteDTO> getAllKeywordLiteForMedia(Integer mediaId, Integer languageId) throws IllegalArgumentException;
	
	public List<Keyword> findAll();
}
