package org.inbio.m3s.model.core;


import java.io.Serializable;
import java.util.Date;

import org.inbio.m3s.model.DBLogEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class MediaKeyword extends DBLogEntity implements Serializable {


	private static final long serialVersionUID = -4693211942901849162L;

	private MediaKeywordId id;

	private Keyword keyword;

	private Media media;

	public MediaKeyword() {
	}

	public MediaKeyword(MediaKeywordId id, Keyword keyword, Media media) {
		this.id = id;
		this.keyword = keyword;
		this.media = media;
	}

	public MediaKeyword(MediaKeywordId id, Keyword keyword, Media media,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy) {
		this.id = id;
		this.keyword = keyword;
		this.media = media;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
	}

	public MediaKeywordId getId() {
		return this.id;
	}

	public void setId(MediaKeywordId id) {
		this.id = id;
	}

	public Keyword getKeyword() {
		return this.keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

}
