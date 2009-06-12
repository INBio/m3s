package org.inbio.m3s.model.core;


import java.io.Serializable;
import java.util.Date;

import org.inbio.m3s.model.DBLogEntity;


public class MediaAttributeValue extends DBLogEntity implements Serializable {

	/** NPI */
	private static final long serialVersionUID = 4782357846411568800L;

	private MediaAttributeValueId id;

	private MediaAttribute mediaAttribute;

	private Media media;

	private String valueVarchar;

	private Integer valueId;

	private Integer valueNumber;

	private Date valueDate;

	public MediaAttributeValue() {
	}

	public MediaAttributeValue(MediaAttributeValueId id,
			MediaAttribute mediaAttribute, Media media) {
		this.id = id;
		this.mediaAttribute = mediaAttribute;
		this.media = media;
	}

	public MediaAttributeValue(MediaAttributeValueId id,
			MediaAttribute mediaAttribute, Media media, String valueVarchar,
			Integer valueId, Integer valueNumber, Date valueDate,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy) {
		this.id = id;
		this.mediaAttribute = mediaAttribute;
		this.media = media;
		this.valueVarchar = valueVarchar;
		this.valueId = valueId;
		this.valueNumber = valueNumber;
		this.valueDate = valueDate;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
	}

	public MediaAttributeValueId getId() {
		return this.id;
	}

	public void setId(MediaAttributeValueId id) {
		this.id = id;
	}

	public MediaAttribute getMediaAttribute() {
		return this.mediaAttribute;
	}

	public void setMediaAttribute(MediaAttribute mediaAttribute) {
		this.mediaAttribute = mediaAttribute;
	}

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public String getValueVarchar() {
		return this.valueVarchar;
	}

	public void setValueVarchar(String valueVarchar) {
		this.valueVarchar = valueVarchar;
	}

	public Integer getValueId() {
		return this.valueId;
	}

	public void setValueId(Integer valueId) {
		this.valueId = valueId;
	}

	public Integer getValueNumber() {
		return this.valueNumber;
	}

	public void setValueNumber(Integer valueNumber) {
		this.valueNumber = valueNumber;
	}

	public Date getValueDate() {
		return this.valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

}
