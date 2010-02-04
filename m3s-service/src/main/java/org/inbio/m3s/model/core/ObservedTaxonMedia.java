package org.inbio.m3s.model.core;


import java.util.Date;

import org.inbio.m3s.model.LogGenericEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class ObservedTaxonMedia extends LogGenericEntity {

	private static final long serialVersionUID = 8470062155559418835L;

	private ObservedTaxonMediaId id;

	private Media media;

	private Integer taxonId;

	public ObservedTaxonMedia() {
	}

	public ObservedTaxonMedia(ObservedTaxonMediaId id, Media media) {
		this.id = id;
		this.media = media;
	}

	public ObservedTaxonMedia(ObservedTaxonMediaId id, Media media,
			Integer taxonId, Date creationDate, String createdBy,
			Date lastModificationDate, String lastModificationBy) {
		this.id = id;
		this.media = media;
		this.taxonId = taxonId;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
	}

	public ObservedTaxonMediaId getId() {
		return this.id;
	}

	public void setId(ObservedTaxonMediaId id) {
		this.id = id;
	}

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public Integer getTaxonId() {
		return this.taxonId;
	}

	public void setTaxonId(Integer taxonId) {
		this.taxonId = taxonId;
	}

}
