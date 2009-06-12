package org.inbio.m3s.model.atta;


import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jgutierrez
 *
 */
public class PersonProfile implements Serializable {

	private static final long serialVersionUID = 1L;

	private PersonProfileId id;

	private Profile profile;

	private INBioPerson person;

	private String shortName;

	private Integer validFrom;

	private Integer validTo;

	private Date creationDate;

	private String createdBy;

	private Date lastModificationDate;

	private String lastModificationBy;

	public PersonProfile() {
	}

	public PersonProfile(PersonProfileId id, Profile profile, INBioPerson person) {
		this.id = id;
		this.profile = profile;
		this.person = person;
	}

	public PersonProfile(PersonProfileId id, Profile profile, INBioPerson person,
			String shortName, Integer validFrom, Integer validTo,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy) {
		this.id = id;
		this.profile = profile;
		this.person = person;
		this.shortName = shortName;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastModificationDate = lastModificationDate;
		this.lastModificationBy = lastModificationBy;
	}

	public PersonProfileId getId() {
		return this.id;
	}

	public void setId(PersonProfileId id) {
		this.id = id;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public INBioPerson getPerson() {
		return this.person;
	}

	public void setPerson(INBioPerson person) {
		this.person = person;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Integer validFrom) {
		this.validFrom = validFrom;
	}

	public Integer getValidTo() {
		return this.validTo;
	}

	public void setValidTo(Integer validTo) {
		this.validTo = validTo;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastModificationDate() {
		return this.lastModificationDate;
	}

	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public String getLastModificationBy() {
		return this.lastModificationBy;
	}

	public void setLastModificationBy(String lastModificationBy) {
		this.lastModificationBy = lastModificationBy;
	}

}
