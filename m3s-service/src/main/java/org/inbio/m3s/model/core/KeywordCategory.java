package org.inbio.m3s.model.core;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.inbio.m3s.model.LogGenericEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class KeywordCategory extends LogGenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1411582543078529443L;

	private Integer keywordCategoryId;

	private Text text;

	private String description;

	private Set<Keyword> keywords = new HashSet<Keyword>(0);

	public KeywordCategory() {
	}

	public KeywordCategory(Text text) {
		this.text = text;
	}

	public KeywordCategory(Text text, String description, Date creationDate,
			String createdBy, Date lastModificationDate,
			String lastModificationBy, Set<Keyword> keywords) {
		this.text = text;
		this.description = description;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
		this.keywords = keywords;
	}

	public Integer getKeywordCategoryId() {
		return this.keywordCategoryId;
	}

	public void setKeywordCategoryId(Integer keywordCategoryId) {
		this.keywordCategoryId = keywordCategoryId;
	}

	public Text getText() {
		return this.text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Keyword> getKeywords() {
		return this.keywords;
	}

	public void setKeywords(Set<Keyword> keywords) {
		this.keywords = keywords;
	}

}
