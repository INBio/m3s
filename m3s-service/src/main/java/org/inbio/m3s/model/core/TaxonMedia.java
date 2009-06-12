package org.inbio.m3s.model.core;


import java.io.Serializable;
import java.util.Date;

import org.inbio.m3s.model.DBLogEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class TaxonMedia extends DBLogEntity implements Serializable {

	private static final long serialVersionUID = 3733061593047831448L;

	private TaxonMediaId id;

	private Media media;

	public TaxonMedia() {
	}

	public TaxonMedia(TaxonMediaId id, Media media) {
		this.id = id;
		this.media = media;
	}

	public TaxonMedia(TaxonMediaId id, Media media, Date creationDate,
			String createdBy, Date lastModificationDate,
			String lastModificationBy) {
		this.id = id;
		this.media = media;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
	}

	public TaxonMediaId getId() {
		return this.id;
	}

	public void setId(TaxonMediaId id) {
		this.id = id;
	}

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

}
