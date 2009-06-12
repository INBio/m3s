package org.inbio.m3s.model.ara;

import java.io.Serializable;

/**
 * 
 * @author jgutierrez
 *
 */
public class CollectorObserverId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer gatheringObservationId;

	private Integer collectorPersonId;

	
	public CollectorObserverId() {
	}

	public CollectorObserverId(Integer gatheringObservationId, Integer collectorPersonId) {
		this.gatheringObservationId = gatheringObservationId;
		this.collectorPersonId = collectorPersonId;
	}

	public Integer getGatheringObservationId() {
		return this.gatheringObservationId;
	}

	public void setGatheringObservationId(Integer specimenId) {
		this.gatheringObservationId = specimenId;
	}

	public Integer getCollectorPersonId() {
		return this.collectorPersonId;
	}

	public void setCollectorPersonId(Integer identificationSequence) {
		this.collectorPersonId = identificationSequence;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CollectorObserverId))
			return false;
		CollectorObserverId castOther = (CollectorObserverId) other;

		return ((this.getGatheringObservationId() == castOther.getGatheringObservationId()) || (this
				.getGatheringObservationId() != null
				&& castOther.getGatheringObservationId() != null && this.getGatheringObservationId()
				.equals(castOther.getGatheringObservationId())))
				&& ((this.getCollectorPersonId() == castOther
						.getCollectorPersonId()) || (this
						.getCollectorPersonId() != null
						&& castOther.getCollectorPersonId() != null && this
						.getCollectorPersonId().equals(
								castOther.getCollectorPersonId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getGatheringObservationId() == null ? 0 : this.getGatheringObservationId()
						.hashCode());
		result = 37
				* result
				+ (getCollectorPersonId() == null ? 0 : this
						.getCollectorPersonId().hashCode());
		return result;
	}

}
