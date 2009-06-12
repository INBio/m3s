package org.inbio.m3s.model.core;


import java.io.Serializable;
import java.util.Date;

import org.inbio.m3s.model.DBLogEntity;

public class TypeSpecimenMedia extends DBLogEntity implements Serializable{

	private static final long serialVersionUID = -5154561146957919662L;

	private TypeSpecimenMediaId id;

	private Media media;

	public TypeSpecimenMedia() {
	}

	public TypeSpecimenMedia(TypeSpecimenMediaId id, Media media) {
		this.id = id;
		this.media = media;
	}

	public TypeSpecimenMedia(TypeSpecimenMediaId id, Media media,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy) {
		this.id = id;
		this.media = media;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
	}

	public TypeSpecimenMediaId getId() {
		return this.id;
	}

	public void setId(TypeSpecimenMediaId id) {
		this.id = id;
	}

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

}
