package org.inbio.m3s.model.core;

import java.io.Serializable;


public class MediaKeywordId implements Serializable {

	private static final long serialVersionUID = 4895851194794857912L;

	private Integer mediaId;

	private Integer keywordId;

	public MediaKeywordId() {
	}

	public MediaKeywordId(Integer mediaId, Integer keywordId) {
		this.mediaId = mediaId;
		this.keywordId = keywordId;
	}

	public Integer getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	public Integer getKeywordId() {
		return this.keywordId;
	}

	public void setKeywordId(Integer keywordId) {
		this.keywordId = keywordId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MediaKeywordId))
			return false;
		MediaKeywordId castOther = (MediaKeywordId) other;

		return (this.getMediaId() == castOther.getMediaId())
				&& (this.getKeywordId() == castOther.getKeywordId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getMediaId().intValue();
		result = 37 * result + this.getKeywordId().intValue();
		return result;
	}

}
