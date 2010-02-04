package org.inbio.m3s.model.core;


import java.util.Date;

import org.inbio.m3s.model.LogGenericEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class GatheringMediaId extends LogGenericEntity {

	private static final long serialVersionUID = 1310099358746724987L;

	private Integer mediaId;

	private Integer gatheringDetailPersonId;

	private Integer gatheringNumber;

	public GatheringMediaId() {
	}

	public GatheringMediaId(Integer mediaId, Integer gatheringDetailPersonId,
			Integer gatheringNumber) {
		super();
		this.mediaId = mediaId;
		this.gatheringDetailPersonId = gatheringDetailPersonId;
		this.gatheringNumber = gatheringNumber;
	}

	public GatheringMediaId(Integer mediaId, Integer gatheringDetailPersonId,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy, Integer gatheringNumber) {
		this.mediaId = mediaId;
		this.gatheringDetailPersonId = gatheringDetailPersonId;
		this.gatheringNumber = gatheringNumber;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);

	}

	public Integer getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	public Integer getGatheringDetailPersonId() {
		return this.gatheringDetailPersonId;
	}

	public void setGatheringDetailPersonId(Integer gatheringDetailPersonId) {
		this.gatheringDetailPersonId = gatheringDetailPersonId;
	}

	public Integer getGatheringNumber() {
		return this.gatheringNumber;
	}

	public void setGatheringNumber(Integer gatheringNumber) {
		this.gatheringNumber = gatheringNumber;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GatheringMediaId))
			return false;
		GatheringMediaId castOther = (GatheringMediaId) other;

		return (this.getMediaId() == castOther.getMediaId())
				&& (this.getGatheringDetailPersonId() == castOther
						.getGatheringDetailPersonId())
				&& ((this.getCreationDate() == castOther.getCreationDate()) || (this
						.getCreationDate() != null
						&& castOther.getCreationDate() != null && this
						.getCreationDate().equals(castOther.getCreationDate())))
				&& ((this.getCreatedBy() == castOther.getCreatedBy()) || (this
						.getCreatedBy() != null
						&& castOther.getCreatedBy() != null && this
						.getCreatedBy().equals(castOther.getCreatedBy())))
				&& ((this.getLastModificationDate() == castOther
						.getLastModificationDate()) || (this
						.getLastModificationDate() != null
						&& castOther.getLastModificationDate() != null && this
						.getLastModificationDate().equals(
								castOther.getLastModificationDate())))
				&& ((this.getLastModificationBy() == castOther
						.getLastModificationBy()) || (this
						.getLastModificationBy() != null
						&& castOther.getLastModificationBy() != null && this
						.getLastModificationBy().equals(
								castOther.getLastModificationBy())))
				&& (this.getGatheringNumber() == castOther.getGatheringNumber());
	}

	public int hashCode() {
		Integer result = 17;

		result = 37 * result + this.getMediaId();
		result = 37 * result + this.getGatheringDetailPersonId();
		result = 37
				* result
				+ (getCreationDate() == null ? 0 : this.getCreationDate()
						.hashCode());
		result = 37 * result
				+ (getCreatedBy() == null ? 0 : this.getCreatedBy().hashCode());
		result = 37
				* result
				+ (getLastModificationDate() == null ? 0 : this
						.getLastModificationDate().hashCode());
		result = 37
				* result
				+ (getLastModificationBy() == null ? 0 : this
						.getLastModificationBy().hashCode());
		result = 37 * result + this.getGatheringNumber();
		return result.intValue();
	}

}
