package org.inbio.m3s.model.core;


import org.inbio.m3s.model.LogGenericEntity;


public class ObservedTaxonMediaId extends LogGenericEntity {


	private static final long serialVersionUID = -4360795480632373839L;

	private int observationId;

	private int mediaId;

	public ObservedTaxonMediaId() {
	}

	public ObservedTaxonMediaId(int observationId, int mediaId) {
		this.observationId = observationId;
		this.mediaId = mediaId;
	}

	public int getObservationId() {
		return this.observationId;
	}

	public void setObservationId(int observationId) {
		this.observationId = observationId;
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
		if (!(other instanceof ObservedTaxonMediaId))
			return false;
		ObservedTaxonMediaId castOther = (ObservedTaxonMediaId) other;

		return (this.getObservationId() == castOther.getObservationId())
				&& (this.getMediaId() == castOther.getMediaId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getObservationId();
		result = 37 * result + this.getMediaId();
		return result;
	}

}
