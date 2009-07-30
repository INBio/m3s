package org.inbio.m3s.model.core;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.inbio.m3s.model.DBLogEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class MediaType extends DBLogEntity implements java.io.Serializable {

	/** NPI */
	private static final long serialVersionUID = 3399750864350074030L;

	private Integer mediaTypeId;

	private Text textByDescriptionTextId;

	private Text textByNameTextId;

	private Integer mediaCategoryId;
	
	private MediaCategory mediaCategory;

	//private Set<Media> medias = new HashSet<Media>(0);

	private Set<MediaAttributeType> mediaAttributeTypes = new HashSet<MediaAttributeType>(0);

	public MediaType() {
	}

	public MediaType(Text textByNameTextId, MediaCategory mediaCategory) {
		this.textByNameTextId = textByNameTextId;
		this.mediaCategory = mediaCategory;
	}

	/**
	 * 
	 * @param textByDescriptionTextId
	 * @param textByNameTextId
	 * @param mediaCategoryId
	 * @param mediaCategory
	 * @param creationDate
	 * @param createdBy
	 * @param lastModificationDate
	 * @param lastModificationBy
	 * @param mediaAttributeTypes
	 */
	public MediaType(Text textByDescriptionTextId, Text textByNameTextId,
			Integer mediaCategoryId,
			MediaCategory mediaCategory, Date creationDate, String createdBy,
			Date lastModificationDate, String lastModificationBy,
			//Set<Media> medias, 
			Set<MediaAttributeType> mediaAttributeTypes) {
		this.textByDescriptionTextId = textByDescriptionTextId;
		this.textByNameTextId = textByNameTextId;
		this.setMediaCategoryId(mediaCategoryId);
		this.mediaCategory = mediaCategory;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
		//this.medias = medias;
		this.mediaAttributeTypes = mediaAttributeTypes;
	}
	
	/**
	 * 
	 * @param textByDescriptionTextId
	 * @param textByNameTextId
	 * @param mediaCategory
	 * @param creationDate
	 * @param createdBy
	 * @param lastModificationDate
	 * @param lastModificationBy
	 * @param mediaAttributeTypes
	 */
	public MediaType(Text textByDescriptionTextId, Text textByNameTextId,
			MediaCategory mediaCategory, Date creationDate, String createdBy,
			Date lastModificationDate, String lastModificationBy,
			//Set<Media> medias, 
			Set<MediaAttributeType> mediaAttributeTypes) {
		this.textByDescriptionTextId = textByDescriptionTextId;
		this.textByNameTextId = textByNameTextId;
		this.mediaCategory = mediaCategory;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
		//this.medias = medias;
		this.mediaAttributeTypes = mediaAttributeTypes;
	}

	public Integer getMediaTypeId() {
		return this.mediaTypeId;
	}

	public void setMediaTypeId(Integer mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
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

	public MediaCategory getMediaCategory() {
		return this.mediaCategory;
	}

	public void setMediaCategory(MediaCategory mediaCategory) {
		this.mediaCategory = mediaCategory;
	}
/*
	public Set<Media> getMedias() {
		return this.medias;
	}

	public void setMedias(Set<Media> medias) {
		this.medias = medias;
	}
*/
	public Set<MediaAttributeType> getMediaAttributeTypes() {
		return this.mediaAttributeTypes;
	}

	public void setMediaAttributeTypes(
			Set<MediaAttributeType> mediaAttributeTypes) {
		this.mediaAttributeTypes = mediaAttributeTypes;
	}

	/**
	 * @param mediaCategoryId the mediaCategoryId to set
	 */
	public void setMediaCategoryId(Integer mediaCategoryId) {
		this.mediaCategoryId = mediaCategoryId;
	}

	/**
	 * @return the mediaCategoryId
	 */
	public Integer getMediaCategoryId() {
		return mediaCategoryId;
	}

}
