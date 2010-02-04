package org.inbio.m3s.model.core;


import java.util.Date;

import org.inbio.m3s.model.LogGenericEntity;


public class MediaAttributeValue extends LogGenericEntity {

	/** NPI */
	private static final long serialVersionUID = 4782357846411568800L;

	private MediaAttributeValueId id;

	private String valueVarchar;

	private Integer valueId;

	private Integer valueNumber;

	private Date valueDate;

	public MediaAttributeValue() {
	}

	public MediaAttributeValue(MediaAttributeValueId id) {
		this.id = id;
	}

	public MediaAttributeValue(MediaAttributeValueId id, String valueVarchar,
			Integer valueId, Integer valueNumber, Date valueDate,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy) {
		this.id = id;
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
