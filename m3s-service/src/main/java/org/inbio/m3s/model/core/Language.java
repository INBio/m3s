package org.inbio.m3s.model.core;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.inbio.m3s.model.DBLogEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class Language extends DBLogEntity implements Serializable {

	/** NPI */
	private static final long serialVersionUID = 1L;

	private Integer languageId;

	private Text textByDescriptionTextId;

	private Text textByNameTextId;

	private String name;

	private String description;

	private Set<TextTranslation> textTranslations = new HashSet<TextTranslation>(
			0);

	public Language() {
	}

	public Language(Text textByNameTextId, String name) {
		this.textByNameTextId = textByNameTextId;
		this.name = name;
	}

	public Language(Text textByDescriptionTextId, Text textByNameTextId,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy, String name, String description,
			Set<TextTranslation> textTranslations) {
		this.textByDescriptionTextId = textByDescriptionTextId;
		this.textByNameTextId = textByNameTextId;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
		this.name = name;
		this.description = description;
		this.textTranslations = textTranslations;
	}

	public Integer getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public Text getTextByDescriptionTextId() {
		return this.textByDescriptionTextId;
	}

	public void setTextByDescriptionTextId(Text textByDescriptionTextId) {
		this.textByDescriptionTextId = textByDescriptionTextId;
	}

	public Text getTextByNameTextId() {
		return this.textByNameTextId;
	}

	public void setTextByNameTextId(Text textByNameTextId) {
		this.textByNameTextId = textByNameTextId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<TextTranslation> getTextTranslations() {
		return this.textTranslations;
	}

	public void setTextTranslations(Set<TextTranslation> textTranslations) {
		this.textTranslations = textTranslations;
	}

}
