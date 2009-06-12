package org.inbio.m3s.model.core;


import java.io.Serializable;
import java.util.Date;

import org.inbio.m3s.model.DBLogEntity;


public class SpecimenMedia extends DBLogEntity implements Serializable {

	private static final long serialVersionUID = -1115783028384884287L;

	private SpecimenMediaId id;

	private Media media;

	private Integer specimenViewId;

	private String description;

	public SpecimenMedia() {
	}

	public SpecimenMedia(SpecimenMediaId id, Media media) {
		this.id = id;
		this.media = media;
	}

	public SpecimenMedia(SpecimenMediaId id, Media media,
			Integer specimenViewId, String description, Date creationDate,
			String createdBy, Date lastModificationDate,
			String lastModificationBy) {
		this.id = id;
		this.media = media;
		this.specimenViewId = specimenViewId;
		this.description = description;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
	}

	public SpecimenMediaId getId() {
		return this.id;
	}

	public void setId(SpecimenMediaId id) {
		this.id = id;
	}

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public Integer getSpecimenViewId() {
		return this.specimenViewId;
	}

	public void setSpecimenViewId(Integer specimenViewId) {
		this.specimenViewId = specimenViewId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
