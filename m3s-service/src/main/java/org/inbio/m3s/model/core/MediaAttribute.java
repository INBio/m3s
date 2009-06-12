package org.inbio.m3s.model.core;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.inbio.m3s.model.DBLogEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class MediaAttribute extends DBLogEntity implements Serializable {

	private static final long serialVersionUID = -7359379744017180231L;

	private Integer mediaAttributeId;

	private Text textByDescriptionTextId;

	private Text textByNameTextId;

	private String mediaAttributeTableName;

	private char mediaAttributeValueType;

	private char mediaAttributeTypePredefined;

	private Set<MediaAttributeValue> mediaAttributeValues = new HashSet<MediaAttributeValue>(
			0);

	//private Set<MediaAttributeType> mediaAttributeTypes = new HashSet<MediaAttributeType>(0);

	public MediaAttribute() {
	}

	public MediaAttribute(char mediaAttributeValueType,
			char mediaAttributeTypePredefined) {
		this.mediaAttributeValueType = mediaAttributeValueType;
		this.mediaAttributeTypePredefined = mediaAttributeTypePredefined;
	}

	public MediaAttribute(Text textByDescriptionTextId, Text textByNameTextId,
			String mediaAttributeTableName, char mediaAttributeValueType,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy, char mediaAttributeTypePredefined,
			Set<MediaAttributeValue> mediaAttributeValues
			//,Set<MediaAttributeType> mediaAttributeTypes
			) {
		this.textByDescriptionTextId = textByDescriptionTextId;
		this.textByNameTextId = textByNameTextId;
		this.mediaAttributeTableName = mediaAttributeTableName;
		this.mediaAttributeValueType = mediaAttributeValueType;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
		this.mediaAttributeTypePredefined = mediaAttributeTypePredefined;
		this.mediaAttributeValues = mediaAttributeValues;
		//this.mediaAttributeTypes = mediaAttributeTypes;
	}

	public Integer getMediaAttributeId() {
		return this.mediaAttributeId;
	}

	public void setMediaAttributeId(Integer mediaAttributeId) {
		this.mediaAttributeId = mediaAttributeId;
	}

	public Text getTextByDescriptionTextId() {
		return this.textByDescriptionTextId;
	}

	public void setTextByDescriptionTextId(Text textByDescriptionTextId) {
		this.textByDescriptionTextId = textByDescriptionTextId;
	}

	public Text getTextByNameTextId() {
		return this.textByNameTextId;
	}

	public void setTextByNameTextId(Text textByNameTextId) {
		this.textByNameTextId = textByNameTextId;
	}

	public String getMediaAttributeTableName() {
		return this.mediaAttributeTableName;
	}

	public void setMediaAttributeTableName(String mediaAttributeTableName) {
		this.mediaAttributeTableName = mediaAttributeTableName;
	}

	public char getMediaAttributeValueType() {
		return this.mediaAttributeValueType;
	}

	public void setMediaAttributeValueType(char mediaAttributeValueType) {
		this.mediaAttributeValueType = mediaAttributeValueType;
	}

	public char getMediaAttributeTypePredefined() {
		return this.mediaAttributeTypePredefined;
	}

	public void setMediaAttributeTypePredefined(
			char mediaAttributeTypePredefined) {
		this.mediaAttributeTypePredefined = mediaAttributeTypePredefined;
	}

	public Set<MediaAttributeValue> getMediaAttributeValues() {
		return this.mediaAttributeValues;
	}

	public void setMediaAttributeValues(
			Set<MediaAttributeValue> mediaAttributeValues) {
		this.mediaAttributeValues = mediaAttributeValues;
	}
/*
	public Set<MediaAttributeType> getMediaAttributeTypes() {
		return this.mediaAttributeTypes;
	}

	public void setMediaAttributeTypes(
			Set<MediaAttributeType> mediaAttributeTypes) {
		this.mediaAttributeTypes = mediaAttributeTypes;
	}
*/
}
