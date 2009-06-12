package org.inbio.m3s.model.ara;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jgutierrez
 *
 */
public class IdentificationId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer specimenId;

	private Date initialTimestamp;

	private Integer identificationSequence;

	public IdentificationId() {
	}

	public IdentificationId(Integer specimenId, Date initialTimestamp,
			Integer identificationSequence) {
		this.specimenId = specimenId;
		this.initialTimestamp = initialTimestamp;
		this.identificationSequence = identificationSequence;
	}

	public Integer getSpecimenId() {
		return this.specimenId;
	}

	public void setSpecimenId(Integer specimenId) {
		this.specimenId = specimenId;
	}

	public Date getInitialTimestamp() {
		return this.initialTimestamp;
	}

	public void setInitialTimestamp(Date initialTimestamp) {
		this.initialTimestamp = initialTimestamp;
	}

	public Integer getIdentificationSequence() {
		return this.identificationSequence;
	}

	public void setIdentificationSequence(Integer identificationSequence) {
		this.identificationSequence = identificationSequence;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof IdentificationId))
			return false;
		IdentificationId castOther = (IdentificationId) other;

		return ((this.getSpecimenId() == castOther.getSpecimenId()) || (this
				.getSpecimenId() != null
				&& castOther.getSpecimenId() != null && this.getSpecimenId()
				.equals(castOther.getSpecimenId())))
				&& ((this.getInitialTimestamp() == castOther
						.getInitialTimestamp()) || (this.getInitialTimestamp() != null
						&& castOther.getInitialTimestamp() != null && this
						.getInitialTimestamp().equals(
								castOther.getInitialTimestamp())))
				&& ((this.getIdentificationSequence() == castOther
						.getIdentificationSequence()) || (this
						.getIdentificationSequence() != null
						&& castOther.getIdentificationSequence() != null && this
						.getIdentificationSequence().equals(
								castOther.getIdentificationSequence())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getSpecimenId() == null ? 0 : this.getSpecimenId()
						.hashCode());
		result = 37
				* result
				+ (getInitialTimestamp() == null ? 0 : this
						.getInitialTimestamp().hashCode());
		result = 37
				* result
				+ (getIdentificationSequence() == null ? 0 : this
						.getIdentificationSequence().hashCode());
		return result;
	}

}
