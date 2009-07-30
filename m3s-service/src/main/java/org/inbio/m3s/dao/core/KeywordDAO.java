/**
 * 
 */
package org.inbio.m3s.dao.core;


import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.dto.message.KeywordDTO;

/**
 * @author jgutierrez
 *
 */
public interface KeywordDAO extends BaseDAO{
	
	@Deprecated
	public KeywordDTO getKeywordLite(String keywordName, Integer languageId) throws IllegalArgumentException;
	@Deprecated
	public List<KeywordDTO> getAllKeywordLiteForMedia(Integer mediaId, Integer languageId) throws IllegalArgumentException;
	
}
