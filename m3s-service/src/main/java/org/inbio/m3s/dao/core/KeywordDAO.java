/**
 * 
 */
package org.inbio.m3s.dao.core;


import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.dto.message.KeywordDTO;

/**
 * @author jgutierrez
 *	Un método a nivel e dao no debería devolver un dto, pero por lo 
 * enredado como funciona el manejo de keyword se prefirió así
 */
public interface KeywordDAO extends BaseDAO{
	
	public KeywordDTO getKeywordLite(String keywordName, Integer languageId) throws IllegalArgumentException;
	
	public List<KeywordDTO> getAllKeywordLiteForMedia(Integer mediaId, Integer languageId) throws IllegalArgumentException;
	 
	public List<KeywordDTO> findAllByPartialNamePaginated(String partialKeywrod, int maxResults, Integer languageId);
	
}
