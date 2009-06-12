/**
 * 
 */
package org.inbio.m3s.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.inbio.m3s.dao.core.KeywordDAO;
import org.inbio.m3s.dao.core.MediaTypeDAO;
import org.inbio.m3s.dao.core.TextTranslationDAO;
import org.inbio.m3s.dto.lite.MediaTypeLite;
import org.inbio.m3s.dto.message.KeywordLiteDTO;
import org.inbio.m3s.model.core.Keyword;
import org.inbio.m3s.model.core.TextTranslation;
import org.inbio.m3s.service.MessageManager;

/**
 * @author jgutierrez
 *
 */
public class MessageManagerImpl implements MessageManager {
	
	private KeywordDAO keywordDAO;
	
	private TextTranslationDAO textTranslationDAO;
	
	private MediaTypeDAO mediaTypeDAO;

	/**
	 * @return the mediaTypeDAO
	 */
	public MediaTypeDAO getMediaTypeDAO() {
		return mediaTypeDAO;
	}



	/**
	 * @param mediaTypeDAO the mediaTypeDAO to set
	 */
	public void setMediaTypeDAO(MediaTypeDAO mediaTypeDAO) {
		this.mediaTypeDAO = mediaTypeDAO;
	}


	
	public KeywordLiteDTO getKeywordLite(String keywordName, Integer languageId)
			throws IllegalArgumentException {
	
		KeywordLiteDTO klDTO = keywordDAO.getKeywordLite(keywordName, languageId);
		
		return new KeywordLiteDTO(klDTO.getKeywordKey(), klDTO.getName());
	}


	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MessageManager#getAllKeywordLite(java.lang.Integer)
	 */
	public List<KeywordLiteDTO> getAllKeywordLite(Integer languageId)
			throws IllegalArgumentException {
		
		List<KeywordLiteDTO> kDTOList = new ArrayList<KeywordLiteDTO>();
		List<Keyword> kList = keywordDAO.findAll();
		TextTranslation tt;
		
		for(Keyword k : kList){
			tt = textTranslationDAO.finByIdAndLanguage(k.getText().getTextId(), languageId);
			kDTOList.add(new KeywordLiteDTO(k.getKeywordId().toString(), tt.getName()));
		}
		
		return kDTOList;
	}
	
	
	
	/**
	 * Gets the tecnical metadata texts to be display in each row of the
	 * felxtable. This method uses the Properties.DEFAULT_LANGUAGE as parameter
	 * for the query
	 * 
	 * @param mediaTypeName
	 *            the name of the media attribute
	 * @return a list of string values, get(N) should be display on row N.
	 * 
	 */
	public List<String> getTMRowTexts(String mediaTypeName) {
		//logger.debug("getting TM row texts...");
		MediaTypeLite mediaTypeLite = mediaTypeDAO.getMediaTypeLite(mediaTypeName);
		
		//Integer mediaTypeDBId = MediaTypeDAO.getMediaTypeDBId(mediaTypeName);
		List<String> rowTexts = textTranslationDAO.findTechnicalMetadataTexts(mediaTypeLite.getMediaTypeId(), MessageManager.DEFAULT_LANGUAGE);
		//logger.debug("getting TM row texts... mediaType '" + mediaTypeName
		//		+ "' with DBId[:" + mediaTypeLite.getMediaTypeId() + "]");

		// if theres no arguments something its wrong
		if (rowTexts.size() == 0) {
			//logger
			//		.debug("getting TM row texts... No media Atributes for that mediaType");
			throw new IllegalArgumentException(
					"Check the arguments of the query");
		}

		//logger.debug("getting TM row texts... get [" + rowTexts.size()
		//		+ "] result");
		//logger.debug("getting TM row texts... done");
		return rowTexts;

	}



	
	
	/**
	 * @param keywordDAO the keywordDAO to set
	 */
	public void setKeywordDAO(KeywordDAO keywordDAO) {
		this.keywordDAO = keywordDAO;
	}

	/**
	 * @return the keywordDAO
	 */
	public KeywordDAO getKeywordDAO() {
		return keywordDAO;
	}

	/**
	 * @param textTranslationDAO the textTranslationDAO to set
	 */
	public void setTextTranslationDAO(TextTranslationDAO textTranslationDAO) {
		this.textTranslationDAO = textTranslationDAO;
	}

	/**
	 * @return the textTranslationDAO
	 */
	public TextTranslationDAO getTextTranslationDAO() {
		return textTranslationDAO;
	}



}
