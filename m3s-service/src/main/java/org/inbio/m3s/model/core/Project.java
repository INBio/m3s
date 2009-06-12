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
public class Project extends DBLogEntity implements Serializable {

	private static final long serialVersionUID = 2782827914571243164L;

	private Integer projectId;

	private SecurityUsers securityUsers;

	private String name;

	private String description;

	private Set<UserProjectPrivilege> userProjectPrivileges = new HashSet<UserProjectPrivilege>(0);

	private Set<MediaProject> mediaProjects = new HashSet<MediaProject>(0);

	public Project() {
	}

	public Project(SecurityUsers securityUsers, String name) {
		this.securityUsers = securityUsers;
		this.name = name;
	}

	public Project(SecurityUsers securityUsers, String name,
			String description, Date creationDate, String createdBy,
			Date lastModificationDate, String lastModificationBy,
			Set<UserProjectPrivilege> userProjectPrivileges,
			Set<MediaProject> mediaProjects) {
		this.securityUsers = securityUsers;
		this.name = name;
		this.description = description;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
		this.userProjectPrivileges = userProjectPrivileges;
		this.mediaProjects = mediaProjects;
	}

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public SecurityUsers getSecurityUsers() {
		return this.securityUsers;
	}

	public void setSecurityUsers(SecurityUsers securityUsers) {
		this.securityUsers = securityUsers;
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

	public Set<UserProjectPrivilege> getUserProjectPrivileges() {
		return this.userProjectPrivileges;
	}

	public void setUserProjectPrivileges(
			Set<UserProjectPrivilege> userProjectPrivileges) {
		this.userProjectPrivileges = userProjectPrivileges;
	}

	public Set<MediaProject> getMediaProjects() {
		return this.mediaProjects;
	}

	public void setMediaProjects(Set<MediaProject> mediaProjects) {
		this.mediaProjects = mediaProjects;
	}

}
