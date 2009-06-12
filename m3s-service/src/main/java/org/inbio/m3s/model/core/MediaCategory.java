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
public class MediaCategory extends DBLogEntity implements Serializable {

	private static final long serialVersionUID = -4579702356300665787L;

	private Integer mediaCategoryId;

	private Text textByDescriptionTextId;

	private Text textByNameTextId;

	private Set<MediaType> mediaTypes = new HashSet<MediaType>(0);

	public MediaCategory() {
	}

	public MediaCategory(Text textByNameTextId) {
		this.textByNameTextId = textByNameTextId;
	}

	public MediaCategory(Text textByDescriptionTextId, Text textByNameTextId,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy, Set<MediaType> mediaTypes) {
		this.textByDescriptionTextId = textByDescriptionTextId;
		this.textByNameTextId = textByNameTextId;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
		this.mediaTypes = mediaTypes;
	}

	public Integer getMediaCategoryId() {
		return this.mediaCategoryId;
	}

	public void setMediaCategoryId(Integer mediaCategoryId) {
		this.mediaCategoryId = mediaCategoryId;
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

	public Set<MediaType> getMediaTypes() {
		return this.mediaTypes;
	}

	public void setMediaTypes(Set<MediaType> mediaTypes) {
		this.mediaTypes = mediaTypes;
	}

}
