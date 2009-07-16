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
public class MediaUse extends DBLogEntity implements Serializable {

	private static final long serialVersionUID = -6967136431256564604L;

	private Integer mediaUseId;

	private Text textByDescriptionTextId;

	private Text textByNameTextId;

	public MediaUse() {
	}

	public MediaUse(Text textByNameTextId) {
		this.textByNameTextId = textByNameTextId;
	}

	public MediaUse(Text textByDescriptionTextId, Text textByNameTextId,
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


}
