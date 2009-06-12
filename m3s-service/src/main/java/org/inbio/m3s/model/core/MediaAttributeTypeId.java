package org.inbio.m3s.model.core;

import java.io.Serializable;

/**
 * 
 * @author jgutierrez
 *
 */
public class MediaAttributeTypeId implements Serializable {

	private static final long serialVersionUID = 6342379942501754084L;

	private int mediaTypeId;

	private int mediaAttributeId;

	public MediaAttributeTypeId() {
	}

	public MediaAttributeTypeId(int mediaTypeId, int mediaAttributeId) {
		this.mediaTypeId = mediaTypeId;
		this.mediaAttributeId = mediaAttributeId;
	}

	public int getMediaTypeId() {
		return this.mediaTypeId;
	}

	public void setMediaTypeId(int mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}

	public int getMediaAttributeId() {
		return this.mediaAttributeId;
	}

	public void setMediaAttributeId(int mediaAttributeId) {
		this.mediaAttributeId = mediaAttributeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MediaAttributeTypeId))
			return false;
		MediaAttributeTypeId castOther = (MediaAttributeTypeId) other;

		return (this.getMediaTypeId() == castOther.getMediaTypeId())
				&& (this.getMediaAttributeId() == castOther
						.getMediaAttributeId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getMediaTypeId();
		result = 37 * result + this.getMediaAttributeId();
		return result;
	}

}
