package org.inbio.m3s.model.core;


import java.util.Date;

import org.inbio.m3s.model.LogGenericEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class MediaAttributeType extends LogGenericEntity {

	private static final long serialVersionUID = 4023319254616697341L;

	private MediaAttributeTypeId id;

	private Integer mediaTypeId;
	
	private Integer mediaAttributeId;

	private Integer metadataStandardId;
	
	private int standardAttributeId;

	public MediaAttributeType() {
	}

	public MediaAttributeType(MediaAttributeTypeId id, Integer mediaTypeId,
			Integer mediaAttributeId, Integer metadataStandardId,
			int standardAttributeId) {
		this.id = id;
		this.mediaTypeId = mediaTypeId;
		this.mediaAttributeId = mediaAttributeId;
		this.metadataStandardId = metadataStandardId;
		this.standardAttributeId = standardAttributeId;
	}

	public MediaAttributeType(MediaAttributeTypeId id, Integer mediaTypeId,
			Integer mediaAttributeId, Integer metadataStandardId,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy, int standardAttributeId) {
		this.id = id;
		this.mediaAttributeId = mediaAttributeId;
		this.metadataStandardId = metadataStandardId;
		this.standardAttributeId = standardAttributeId;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
		this.standardAttributeId = standardAttributeId;
	}

	public MediaAttributeTypeId getId() {
		return this.id;
	}

	public void setId(MediaAttributeTypeId id) {
		this.id = id;
	}

	public int getStandardAttributeId() {
		return this.standardAttributeId;
	}

	public void setStandardAttributeId(int standardAttributeId) {
		this.standardAttributeId = standardAttributeId;
	}

	/**
	 * @return the mediaTypeId
	 */
	public Integer getMediaTypeId() {
		return mediaTypeId;
	}

	/**
	 * @param mediaTypeId the mediaTypeId to set
	 */
	public void setMediaTypeId(Integer mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
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

}
