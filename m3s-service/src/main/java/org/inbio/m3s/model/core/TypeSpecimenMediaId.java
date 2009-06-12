package org.inbio.m3s.model.core;

import java.io.Serializable;

/**
 * 
 * @author jgutierrez
 *
 */
public class TypeSpecimenMediaId implements Serializable {

	private static final long serialVersionUID = -3875004947858874716L;

	private int typeSpecimenId;

	private int mediaId;

	public TypeSpecimenMediaId() {
	}

	public TypeSpecimenMediaId(int typeSpecimenId, int mediaId) {
		this.typeSpecimenId = typeSpecimenId;
		this.mediaId = mediaId;
	}

	public int getTypeSpecimenId() {
		return this.typeSpecimenId;
	}

	public void setTypeSpecimenId(int typeSpecimenId) {
		this.typeSpecimenId = typeSpecimenId;
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
		if (!(other instanceof TypeSpecimenMediaId))
			return false;
		TypeSpecimenMediaId castOther = (TypeSpecimenMediaId) other;

		return (this.getTypeSpecimenId() == castOther.getTypeSpecimenId())
				&& (this.getMediaId() == castOther.getMediaId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getTypeSpecimenId();
		result = 37 * result + this.getMediaId();
		return result;
	}

}
