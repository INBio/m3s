package org.inbio.m3s.model.core;

import java.io.Serializable;


/**
 * 
 * @author jgutierrez
 *
 */
public class GatheringMedia implements Serializable {

	private static final long serialVersionUID = 1572311198816336616L;

	private GatheringMediaId id;

	private Media media;

	public GatheringMedia() {
	}

	public GatheringMedia(GatheringMediaId id, Media media) {
		this.id = id;
		this.media = media;
	}

	public GatheringMediaId getId() {
		return this.id;
	}

	public void setId(GatheringMediaId id) {
		this.id = id;
	}

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

}
