package org.inbio.m3s.model.core;

import java.io.Serializable;

/**
 * 
 * @author jgutierrez
 *
 */
public class MediaUseMediaId implements Serializable {

	private static final long serialVersionUID = 8767986094209496159L;

	private int mediaId;

	private int mediaUseId;

	public MediaUseMediaId() {
	}

	public MediaUseMediaId(int mediaId, int mediaUseId) {
		this.mediaId = mediaId;
		this.mediaUseId = mediaUseId;
	}

	public int getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}

	public int getMediaUseId() {
		return this.mediaUseId;
	}

	public void setMediaUseId(int mediaUseId) {
		this.mediaUseId = mediaUseId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MediaUseMediaId))
			return false;
		MediaUseMediaId castOther = (MediaUseMediaId) other;

		return (this.getMediaId() == castOther.getMediaId())
				&& (this.getMediaUseId() == castOther.getMediaUseId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getMediaId();
		result = 37 * result + this.getMediaUseId();
		return result;
	}

}
