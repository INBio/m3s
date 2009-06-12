package org.inbio.m3s.model.core;

import java.io.Serializable;

/**
 * 
 * @author jgutierrez
 *
 */
public class MediaProjectId implements Serializable {

	private static final long serialVersionUID = -2748327849103203470L;

	private int mediaId;

	private int projectId;

	public MediaProjectId() {
	}

	public MediaProjectId(int mediaId, int projectId) {
		this.mediaId = mediaId;
		this.projectId = projectId;
	}

	public int getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MediaProjectId))
			return false;
		MediaProjectId castOther = (MediaProjectId) other;

		return (this.getMediaId() == castOther.getMediaId())
				&& (this.getProjectId() == castOther.getProjectId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getMediaId();
		result = 37 * result + this.getProjectId();
		return result;
	}

}
