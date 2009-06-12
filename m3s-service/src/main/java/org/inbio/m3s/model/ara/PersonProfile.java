package org.inbio.m3s.model.ara;

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

	private ARAPerson person;

	private String shortName;

	private Integer validFrom;

	private Integer validTo;

	private Date creationDate;

	private String createdBy;

	private Date lastModificationDate;

	private String lastModificationBy;
	
	private Integer objVersion;

	/**
	 * 
	 */
	public PersonProfile() {
		super();
	}

	/**
	 * @param id
	 * @param profile
	 * @param person
	 * @param objVersion
	 */
	public PersonProfile(PersonProfileId id, Profile profile, ARAPerson person, Integer objVersion) {
		super();
		this.id = id;
		this.profile = profile;
		this.person = person;
		this.objVersion = objVersion;
	}

	/**
	 * @param id
	 * @param profile
	 * @param person
	 * @param shortName
	 * @param validFrom
	 * @param validTo
	 * @param creationDate
	 * @param createdBy
	 * @param lastModificationDate
	 * @param lastModificationBy
	 * @param objVersion
	 */
	public PersonProfile(PersonProfileId id, Profile profile, ARAPerson person, String shortName, Integer validFrom, Integer validTo, Date creationDate, String createdBy, Date lastModificationDate, String lastModificationBy, Integer objVersion) {
		super();
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
		this.objVersion = objVersion;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the id
	 */
	public PersonProfileId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(PersonProfileId id) {
		this.id = id;
	}

	/**
	 * @return the lastModificationBy
	 */
	public String getLastModificationBy() {
		return lastModificationBy;
	}

	/**
	 * @param lastModificationBy the lastModificationBy to set
	 */
	public void setLastModificationBy(String lastModificationBy) {
		this.lastModificationBy = lastModificationBy;
	}

	/**
	 * @return the lastModificationDate
	 */
	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	/**
	 * @param lastModificationDate the lastModificationDate to set
	 */
	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	/**
	 * @return the objVersion
	 */
	public Integer getObjVersion() {
		return objVersion;
	}

	/**
	 * @param objVersion the objVersion to set
	 */
	public void setObjVersion(Integer objVersion) {
		this.objVersion = objVersion;
	}

	/**
	 * @return the person
	 */
	public ARAPerson getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(ARAPerson person) {
		this.person = person;
	}

	/**
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * @param profile the profile to set
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @return the validFrom
	 */
	public Integer getValidFrom() {
		return validFrom;
	}

	/**
	 * @param validFrom the validFrom to set
	 */
	public void setValidFrom(Integer validFrom) {
		this.validFrom = validFrom;
	}

	/**
	 * @return the validTo
	 */
	public Integer getValidTo() {
		return validTo;
	}

	/**
	 * @param validTo the validTo to set
	 */
	public void setValidTo(Integer validTo) {
		this.validTo = validTo;
	}

}
