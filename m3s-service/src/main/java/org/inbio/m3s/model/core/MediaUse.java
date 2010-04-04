package org.inbio.m3s.model.core;


import java.util.Date;

import org.inbio.m3s.model.LogGenericEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class MediaUse extends LogGenericEntity {

	private static final long serialVersionUID = -6967136431256564604L;

	private Integer mediaUseId;

	private Integer textByDescriptionTextId;

	private Integer textByNameTextId;

	public MediaUse() {
	}

	public MediaUse(Integer textByNameTextId) {
		this.textByNameTextId = textByNameTextId;
	}

	public MediaUse(Integer textByDescriptionTextId, Integer textByNameTextId,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy) {
		this.textByDescriptionTextId = textByDescriptionTextId;
		this.textByNameTextId = textByNameTextId;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
	}

	public Integer getMediaUseId() {
		return this.mediaUseId;
	}

	public void setMediaUseId(Integer mediaUseId) {
		this.mediaUseId = mediaUseId;
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


}
