package org.inbio.m3s.model.core;


import java.io.Serializable;
import java.util.Date;

import org.inbio.m3s.model.DBLogEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class MediaAttributeType extends DBLogEntity implements Serializable {

	private static final long serialVersionUID = 4023319254616697341L;

	private MediaAttributeTypeId id;

	private MediaType mediaType;

	private MediaAttribute mediaAttribute;

	private MetadataStandard metadataStandard;

	private int standardAttributeId;

	public MediaAttributeType() {
	}

	public MediaAttributeType(MediaAttributeTypeId id, MediaType mediaType,
			MediaAttribute mediaAttribute, MetadataStandard metadataStandard,
			int standardAttributeId) {
		this.id = id;
		this.mediaType = mediaType;
		this.mediaAttribute = mediaAttribute;
		this.metadataStandard = metadataStandard;
		this.standardAttributeId = standardAttributeId;
	}

	public MediaAttributeType(MediaAttributeTypeId id, MediaType mediaType,
			MediaAttribute mediaAttribute, MetadataStandard metadataStandard,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy, int standardAttributeId) {
		this.id = id;
		this.mediaType = mediaType;
		this.mediaAttribute = mediaAttribute;
		this.metadataStandard = metadataStandard;
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

	public MediaType getMediaType() {
		return this.mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	public MediaAttribute getMediaAttribute() {
		return this.mediaAttribute;
	}

	public void setMediaAttribute(MediaAttribute mediaAttribute) {
		this.mediaAttribute = mediaAttribute;
	}

	public MetadataStandard getMetadataStandard() {
		return this.metadataStandard;
	}

	public void setMetadataStandard(MetadataStandard metadataStandard) {
		this.metadataStandard = metadataStandard;
	}

	public int getStandardAttributeId() {
		return this.standardAttributeId;
	}

	public void setStandardAttributeId(int standardAttributeId) {
		this.standardAttributeId = standardAttributeId;
	}

}
