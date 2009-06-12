/**
 * 
 */
package org.inbio.m3s.dto;

/**
 * @author jgutierrez
 * 
 */
public class GatheringDataDTO {

	private Integer gatheringDetailPersonId;

	private Integer gatheringNumber;

	/**
	 * @param gatheringDetailPersonId
	 * @param gatheringNumber
	 */
	public GatheringDataDTO(Integer gatheringDetailPersonId,
			Integer gatheringNumber) {
		super();
		this.gatheringDetailPersonId = gatheringDetailPersonId;
		this.gatheringNumber = gatheringNumber;
	}

	/**
	 * @param gatheringDetailPersonId
	 *            the gatheringDetailPersonId to set
	 */
	public void setGatheringDetailPersonId(Integer gatheringDetailPersonId) {
		this.gatheringDetailPersonId = gatheringDetailPersonId;
	}

	/**
	 * @return the gatheringDetailPersonId
	 */
	public Integer getGatheringDetailPersonId() {
		return gatheringDetailPersonId;
	}

	/**
	 * @param gatheringNumber
	 *            the gatheringNumber to set
	 */
	public void setGatheringNumber(Integer gatheringNumber) {
		this.gatheringNumber = gatheringNumber;
	}

	/**
	 * @return the gatheringNumber
	 */
	public Integer getGatheringNumber() {
		return gatheringNumber;
	}

}
