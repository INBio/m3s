/**
 * 
 */
package org.inbio.m3s.service.autocomplete.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.dao.core.KeywordDAO;
import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.service.autocomplete.AutoCompleteManager;

/**
 * @author jgutierrez
 * 
 */
public class KeywordAutoCompleteManagerImpl implements AutoCompleteManager {

	protected static Log logger = LogFactory.getLog(KeywordAutoCompleteManagerImpl.class);
	
	// DAO's
	private KeywordDAO keywordDAO;

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.AutoCompleteManager#getAutoCompleteOptions(java.lang.String)
	 */
	public Map<Integer, String> getAutoCompleteOptions(String value) {
		
		List<KeywordDTO> kDTOList =  keywordDAO.findAllByPartialNamePaginated("%"+value+"%", 20, MessageManager.DEFAULT_LANGUAGE);
		Map<Integer, String> results = new HashMap<Integer, String>();
		
		for(KeywordDTO kDTO: kDTOList)
			results.put(Integer.valueOf(kDTO.getKeywordKey()), kDTO.getName());
		
		return results;
	}

	/**
	 * @return the keywordDAO
	 */
	public KeywordDAO getKeywordDAO() {
		return keywordDAO;
	}

	/**
	 * @param keywordDAO the keywordDAO to set
	 */
	public void setKeywordDAO(KeywordDAO keywordDAO) {
		this.keywordDAO = keywordDAO;
	}



}
