package org.inbio.m3s.model.core;


import java.io.Serializable;
import java.util.Date;

import org.inbio.m3s.model.DBLogEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class MediaUseMedia extends DBLogEntity implements Serializable {

	private static final long serialVersionUID = 4340090392439307257L;

	private MediaUseMediaId id;

	private Media media;

	private MediaUse mediaUse;

	private char approved;

	private Integer approvedByPersonId;

	public MediaUseMedia() {
	}

	public MediaUseMedia(MediaUseMediaId id, Media media, MediaUse mediaUse,
			char approved) {
		this.id = id;
		this.media = media;
		this.mediaUse = mediaUse;
		this.approved = approved;
	}

	public MediaUseMedia(MediaUseMediaId id, Media media, MediaUse mediaUse,
			char approved, Integer approvedByPersonId, Date creationDate,
			String createdBy, Date lastModificationDate,
			String lastModificationBy) {
		this.id = id;
		this.media = media;
		this.mediaUse = mediaUse;
		this.approved = approved;
		this.approvedByPersonId = approvedByPersonId;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
	}

	public MediaUseMediaId getId() {
		return this.id;
	}

	public void setId(MediaUseMediaId id) {
		this.id = id;
	}

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public MediaUse getMediaUse() {
		return this.mediaUse;
	}

	public void setMediaUse(MediaUse mediaUse) {
		this.mediaUse = mediaUse;
	}

	public char getApproved() {
		return this.approved;
	}

	public void setApproved(char approved) {
		this.approved = approved;
	}

	public Integer getApprovedByPersonId() {
		return this.approvedByPersonId;
	}

	public void setApprovedByPersonId(Integer approvedByPersonId) {
		this.approvedByPersonId = approvedByPersonId;
	}

}
