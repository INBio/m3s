package org.inbio.m3s.model.core;


import java.io.Serializable;
import java.util.Date;

import org.inbio.m3s.model.DBLogEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class Keyword extends DBLogEntity implements Serializable {

	private static final long serialVersionUID = 5448313950438830953L;

	private Integer keywordId;

	private Text text;

	private KeywordCategory keywordCategory;

	//private Set<MediaKeyword> mediaKeywords = new HashSet<MediaKeyword>(0);

	public Keyword() {
	}

	public Keyword(Text text, KeywordCategory keywordCategory) {
		this.text = text;
		this.keywordCategory = keywordCategory;
	}

	public Keyword(Text text, KeywordCategory keywordCategory,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy) {
		this.text = text;
		this.keywordCategory = keywordCategory;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
	}

	public Integer getKeywordId() {
		return this.keywordId;
	}

	public void setKeywordId(Integer keywordId) {
		this.keywordId = keywordId;
	}

	public Text getText() {
		return this.text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public KeywordCategory getKeywordCategory() {
		return this.keywordCategory;
	}

	public void setKeywordCategory(KeywordCategory keywordCategory) {
		this.keywordCategory = keywordCategory;
	}


}
