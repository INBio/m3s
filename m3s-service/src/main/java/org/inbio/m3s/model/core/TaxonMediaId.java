package org.inbio.m3s.model.core;

import java.io.Serializable;

/**
 * 
 * @author jgutierrez
 *
 */
public class TaxonMediaId implements Serializable {

	private static final long serialVersionUID = -6749901726299669316L;

	private int taxonId;

	private int mediaId;

	public TaxonMediaId() {
	}

	public TaxonMediaId(int taxonId, int mediaId) {
		this.taxonId = taxonId;
		this.mediaId = mediaId;
	}

	public int getTaxonId() {
		return this.taxonId;
	}

	public void setTaxonId(int taxonId) {
		this.taxonId = taxonId;
	}

	public int getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TaxonMediaId))
			return false;
		TaxonMediaId castOther = (TaxonMediaId) other;

		return (this.getTaxonId() == castOther.getTaxonId())
				&& (this.getMediaId() == castOther.getMediaId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getTaxonId();
		result = 37 * result + this.getMediaId();
		return result;
	}

}
