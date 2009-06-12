package org.inbio.m3s.model.atta;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author jgutierrez
 *
 */
public class Profile implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer profileId;

	private String name;

	private String description;

	private Date creationDate;

	private String createdBy;

	private Date lastModificationDate;

	private String lastModificationBy;

	private Set<PersonProfile> personProfiles = new HashSet<PersonProfile>(0);

	public Profile() {
	}

	public Profile(Integer profileId, String name) {
		this.profileId = profileId;
		this.name = name;
	}

	public Profile(Integer profileId, String name, String description,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy, Set<PersonProfile> personProfiles) {
		this.profileId = profileId;
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastModificationDate = lastModificationDate;
		this.lastModificationBy = lastModificationBy;
		this.personProfiles = personProfiles;
	}

	public Integer getProfileId() {
		return this.profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Set<PersonProfile> getPersonProfiles() {
		return this.personProfiles;
	}

	public void setPersonProfiles(Set<PersonProfile> personProfiles) {
		this.personProfiles = personProfiles;
	}

}
