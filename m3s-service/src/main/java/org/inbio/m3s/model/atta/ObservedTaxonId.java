package org.inbio.m3s.model.atta;

import java.io.Serializable;

/**
 * 
 * @author jgutierrez
 *
 */
public class ObservedTaxonId implements Serializable {

	private static final long serialVersionUID = 1L;

	private int observationId;

	private Integer taxonId;

	public ObservedTaxonId() {
	}

	public ObservedTaxonId(int observationId, Integer taxonId) {
		this.observationId = observationId;
		this.taxonId = taxonId;
	}

	public int getObservationId() {
		return this.observationId;
	}

	public void setObservationId(int observationId) {
		this.observationId = observationId;
	}

	public Integer getTaxonId() {
		return this.taxonId;
	}

	public void setTaxonId(Integer taxonId) {
		this.taxonId = taxonId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ObservedTaxonId))
			return false;
		ObservedTaxonId castOther = (ObservedTaxonId) other;

		return (this.getObservationId() == castOther.getObservationId())
				&& ((this.getTaxonId() == castOther.getTaxonId()) || (this
						.getTaxonId() != null
						&& castOther.getTaxonId() != null && this.getTaxonId()
						.equals(castOther.getTaxonId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getObservationId();
		result = 37 * result
				+ (getTaxonId() == null ? 0 : this.getTaxonId().hashCode());
		return result;
	}

}
