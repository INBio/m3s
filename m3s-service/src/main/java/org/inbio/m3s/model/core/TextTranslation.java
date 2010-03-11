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

	private Text text;

	private Language language;

	private String name;

	public TextTranslation() {
	}

	public TextTranslation(Text text, Language language, String name) {
		this.text = text;
		this.language = language;
		this.name = name;
	}

	public TextTranslation(Text text, Language language, String name,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy) {
		this.text = text;
		this.language = language;
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

	public Text getText() {
		return this.text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
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

}
