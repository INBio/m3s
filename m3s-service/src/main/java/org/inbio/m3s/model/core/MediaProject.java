package org.inbio.m3s.model.core;


import java.util.Date;

import org.inbio.m3s.model.LogGenericEntity;


public class MediaProject extends LogGenericEntity {

	private static final long serialVersionUID = 6530320584673498138L;

	private MediaProjectId id;

	private Media media;

	private Project project;

	public MediaProject() {
	}

	public MediaProject(MediaProjectId id, Media media, Project project) {
		this.id = id;
		this.media = media;
		this.project = project;
	}

	public MediaProject(MediaProjectId id, Media media, Project project,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy) {
		this.id = id;
		this.media = media;
		this.project = project;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
	}

	public MediaProjectId getId() {
		return this.id;
	}

	public void setId(MediaProjectId id) {
		this.id = id;
	}

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
