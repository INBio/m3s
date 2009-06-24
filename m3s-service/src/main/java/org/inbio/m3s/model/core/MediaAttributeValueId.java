package org.inbio.m3s.model.core;

/**
 * 
 * @author jgutierrez
 *
 */
public class MediaAttributeValueId implements java.io.Serializable {

	private static final long serialVersionUID = -2978868652169454300L;

	private int mediaAttributeId;

	private int mediaId;

	public MediaAttributeValueId() {
	}

	public MediaAttributeValueId(int mediaAttributeId, int mediaId) {
		this.mediaAttributeId = mediaAttributeId;
		this.mediaId = mediaId;
	}
	
	public MediaAttributeValueId(String mediaAttributeKey, String mediaKey) {
		this.mediaAttributeId = Integer.valueOf(mediaAttributeKey).intValue();
		this.mediaId = Integer.valueOf(mediaKey).intValue();
	}

	public int getMediaAttributeId() {
		return this.mediaAttributeId;
	}

	public void setMediaAttributeId(int mediaAttributeId) {
		this.mediaAttributeId = mediaAttributeId;
	}

	public int getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MediaAttributeValueId))
			return false;
		MediaAttributeValueId castOther = (MediaAttributeValueId) other;

		return (this.getMediaAttributeId() == castOther.getMediaAttributeId())
				&& (this.getMediaId() == castOther.getMediaId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getMediaAttributeId();
		result = 37 * result + this.getMediaId();
		return result;
	}

}
