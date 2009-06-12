package org.inbio.m3s.model.ara;

import java.io.Serializable;


/**
 * 
 * @author jgutierrez
 *
 */
public class PersonProfileId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int personId;

	private Integer profileId;

	/**
	 * 
	 *
	 */
	public PersonProfileId() {
	}

	/**
	 * 
	 * @param personId
	 * @param profileId
	 */
	public PersonProfileId(int personId, Integer profileId) {
		this.personId = personId;
		this.profileId = profileId;
	}

	public int getPersonId() {
		return this.personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public Integer getProfileId() {
		return this.profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PersonProfileId))
			return false;
		PersonProfileId castOther = (PersonProfileId) other;

		return (this.getPersonId() == castOther.getPersonId())
				&& ((this.getProfileId() == castOther.getProfileId()) || (this
						.getProfileId() != null
						&& castOther.getProfileId() != null && this
						.getProfileId().equals(castOther.getProfileId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getPersonId();
		result = 37 * result
				+ (getProfileId() == null ? 0 : this.getProfileId().hashCode());
		return result;
	}

}
