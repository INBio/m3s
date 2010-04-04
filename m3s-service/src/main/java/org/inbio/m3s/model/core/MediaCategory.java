package org.inbio.m3s.model.core;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.inbio.m3s.model.LogGenericEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class MediaCategory extends LogGenericEntity {

	private static final long serialVersionUID = -4579702356300665787L;

	private Integer mediaCategoryId;

	private Integer textByDescriptionTextId;

	private Integer textByNameTextId;

	private Set<MediaType> mediaTypes = new HashSet<MediaType>(0);

	public MediaCategory() {
	}

	public MediaCategory(Integer textByNameTextId) {
		this.textByNameTextId = textByNameTextId;
	}

	public MediaCategory(Integer textByDescriptionTextId, Integer textByNameTextId,
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

	public Integer getTextByDescriptionTextId() {
		return this.textByDescriptionTextId;
	}

	public void setTextByDescriptionTextId(Integer textByDescriptionTextId) {
		this.textByDescriptionTextId = textByDescriptionTextId;
	}

	public Integer getTextByNameTextId() {
		return this.textByNameTextId;
	}

	public void setTextByNameTextId(Integer textByNameTextId) {
		this.textByNameTextId = textByNameTextId;
	}

	public Set<MediaType> getMediaTypes() {
		return this.mediaTypes;
	}

	public void setMediaTypes(Set<MediaType> mediaTypes) {
		this.mediaTypes = mediaTypes;
	}

}
