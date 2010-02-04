package org.inbio.m3s.model.core;

import org.inbio.m3s.model.LogGenericEntity;

/**
 * 
 */
public class SpecimenMediaId extends LogGenericEntity {

	/** NPI */
	private static final long serialVersionUID = 7086717851045215454L;

	private int specimenId;

	private int mediaId;

	public SpecimenMediaId() {
	}

	public SpecimenMediaId(int specimenId, int mediaId) {
		this.specimenId = specimenId;
		this.mediaId = mediaId;
	}

	public int getSpecimenId() {
		return this.specimenId;
	}

	public void setSpecimenId(int specimenId) {
		this.specimenId = specimenId;
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
		if (!(other instanceof SpecimenMediaId))
			return false;
		SpecimenMediaId castOther = (SpecimenMediaId) other;

		return (this.getSpecimenId() == castOther.getSpecimenId())
				&& (this.getMediaId() == castOther.getMediaId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getSpecimenId();
		result = 37 * result + this.getMediaId();
		return result;
	}

}
