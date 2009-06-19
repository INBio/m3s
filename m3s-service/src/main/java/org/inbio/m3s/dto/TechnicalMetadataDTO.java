package org.inbio.m3s.dto;


/**
 * 
 */


import java.util.List;

/**
 * @author jgutierrez
 * 
 * @deprecated
 */
public class TechnicalMetadataDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6685922843477293668L;

	private Integer mediaId;

	private Integer mediaTypeId;

	private List<Integer> mediaAttributeIds;

	private List<Object> mediaAttributeValue;
	
	/**
	 * @param mediaId
	 *            the mediaId to set
	 */
	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * @return the mediaId
	 */
	public Integer getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaTypeId
	 *            the mediaTypeId to set
	 */
	public void setMediaTypeId(Integer mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}

	/**
	 * @return the mediaTypeId
	 */
	public Integer getMediaTypeId() {
		return mediaTypeId;
	}

	/**
	 * @param mediaAttributeIds
	 *            the mediaAttributeIds to set
	 */
	public void setMediaAttributeIds(List<Integer> mediaAttributeIds) {
		this.mediaAttributeIds = mediaAttributeIds;
	}

	/**
	 * @return the mediaAttributeIds
	 */
	public List<Integer> getMediaAttributeIds() {
		return mediaAttributeIds;
	}

	/**
	 * @param mediaAttributeValue
	 *            the mediaAttributeValue to set
	 */
	public void setMediaAttributeValue(List<Object> mediaAttributeValue) {
		this.mediaAttributeValue = mediaAttributeValue;
	}

	/**
	 * @return the mediaAttributeValue
	 */
	public List<Object> getMediaAttributeValue() {
		return mediaAttributeValue;
	}

}
