/**
 * 
 */
package org.inbio.m3s.service;

import java.util.List;

import org.inbio.m3s.dto.message.KeywordLiteDTO;

/**
 * @author jgutierrez
 *
 */
public interface MessageManager {
	
	// languagues
	public static Integer ESPANYOL = new Integer(1);

	public static Integer ENGLISH = new Integer(2);
	
	public static Integer DEFAULT_LANGUAGE = ESPANYOL;
	
	public KeywordLiteDTO getKeywordLite(String keywordName, Integer languageId) throws IllegalArgumentException;
	
	public List<KeywordLiteDTO> getAllKeywordLite(Integer languageId) throws IllegalArgumentException;
	
	public List<String> getTMRowTexts(String mediaTypeName);
}
