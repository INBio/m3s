/**
 * 
 */
package org.inbio.m3s.dto.lite;

import java.io.Serializable;


/**
 * @author jgutierrez
 *
 */
public class MediaAttributeTypeLite implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8207996812489341787L;

	private Integer mediaType;
	
	private Integer mediaAttributeId;
	
	private Integer metadataStandardId;

	private Integer standardAttributeId;

	/**
	 * 
	 */
	public MediaAttributeTypeLite() {
		super();
	}

	/**
	 * @param mediaType
	 * @param mediaAttributeId
	 * @param metadataStandardId
	 * @param standardAttributeId
	 */
	public MediaAttributeTypeLite(Integer mediaType, Integer mediaAttributeId, Integer metadataStandardId, Integer standardAttributeId) {
		super();
		this.mediaType = mediaType;
		this.mediaAttributeId = mediaAttributeId;
		this.metadataStandardId = metadataStandardId;
		this.standardAttributeId = standardAttributeId;
	}

	/**
	 * @return the mediaAttributeId
	 */
	public Integer getMediaAttributeId() {
		return mediaAttributeId;
	}

	/**
	 * @param mediaAttributeId the mediaAttributeId to set
	 */
	public void setMediaAttributeId(Integer mediaAttributeId) {
		this.mediaAttributeId = mediaAttributeId;
	}

	/**
	 * @return the mediaType
	 */
	public Integer getMediaType() {
		return mediaType;
	}

	/**
	 * @param mediaType the mediaType to set
	 */
	public void setMediaType(Integer mediaType) {
		this.mediaType = mediaType;
	}

	/**
	 * @return the metadataStandardId
	 */
	public Integer getMetadataStandardId() {
		return metadataStandardId;
	}

	/**
	 * @param metadataStandardId the metadataStandardId to set
	 */
	public void setMetadataStandardId(Integer metadataStandardId) {
		this.metadataStandardId = metadataStandardId;
	}

	/**
	 * @return the standardAttributeId
	 */
	public Integer getStandardAttributeId() {
		return standardAttributeId;
	}

	/**
	 * @param standardAttributeId the standardAttributeId to set
	 */
	public void setStandardAttributeId(Integer standardAttributeId) {
		this.standardAttributeId = standardAttributeId;
	}

}
