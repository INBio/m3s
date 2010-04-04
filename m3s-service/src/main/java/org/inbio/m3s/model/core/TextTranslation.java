package org.inbio.m3s.model.core;


import java.util.Date;

import org.inbio.m3s.model.LogGenericEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class TextTranslation extends LogGenericEntity {

	/** NPI */
	private static final long serialVersionUID = 1733974936541986181L;

	private Integer textTranslationId;

	private Integer textId;
	
	private String locale;

	private String name;

	public TextTranslation() {
	}

	public TextTranslation(Integer textId, String locale, String name) {
		this.textId = textId;
		this.locale = locale;
		this.name = name;
	}

	public TextTranslation(Integer textId, String locale, String name,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy) {
		this.textId = textId;
		this.locale = locale;
		this.name = name;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
	}

	public Integer getTextTranslationId() {
		return this.textTranslationId;
	}

	public void setTextTranslationId(Integer textTranslationId) {
		this.textTranslationId = textTranslationId;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TextTranslation [name=" + name + ", textTranslationId=" + textTranslationId + "]";
	}

	/**
	 * @return the textId
	 */
	public Integer getTextId() {
		return textId;
	}

	/**
	 * @param textId the textId to set
	 */
	public void setTextId(Integer textId) {
		this.textId = textId;
	}

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

}
