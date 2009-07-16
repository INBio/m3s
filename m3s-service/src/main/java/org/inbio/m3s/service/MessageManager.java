/**
 * 
 */
package org.inbio.m3s.service;

import java.util.List;

import org.inbio.m3s.dto.message.KeywordLiteDTO;
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
	
	public KeywordLiteDTO getKeywordLite(String keywordName, Integer languageId) throws IllegalArgumentException;
	
	public List<KeywordLiteDTO> getAllKeywordLite(Integer languageId) throws IllegalArgumentException;
	
	public List<String> getTMRowTexts(String mediaTypeName);
	
	public List<UsePolicyDTO> getAllUsePolicies() throws IllegalArgumentException;
	
	public UsePolicyDTO getUsePolicy(String usePolicyKey) throws IllegalArgumentException;
}
