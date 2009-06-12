package org.inbio.m3s.model.core;

import java.io.Serializable;
import java.util.Date;

import org.inbio.m3s.model.DBLogEntity;


public class UserProjectPrivilege extends DBLogEntity implements Serializable {

	private static final long serialVersionUID = -1749501202414972537L;

	private UserProjectPrivilegeId id;

	private Privilege privilege;

	private SecurityUsers securityUsers;

	private Project project;

	public UserProjectPrivilege() {
	}

	public UserProjectPrivilege(UserProjectPrivilegeId id, Privilege privilege,
			SecurityUsers securityUsers, Project project) {
		this.id = id;
		this.privilege = privilege;
		this.securityUsers = securityUsers;
		this.project = project;
	}

	public UserProjectPrivilege(UserProjectPrivilegeId id, Privilege privilege,
			SecurityUsers securityUsers, Project project, Date creationDate,
			String createdBy, Date lastModificationDate,
			String lastModificationBy) {
		this.id = id;
		this.privilege = privilege;
		this.securityUsers = securityUsers;
		this.project = project;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
	}

	public UserProjectPrivilegeId getId() {
		return this.id;
	}

	public void setId(UserProjectPrivilegeId id) {
		this.id = id;
	}

	public Privilege getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public SecurityUsers getSecurityUsers() {
		return this.securityUsers;
	}

	public void setSecurityUsers(SecurityUsers securityUsers) {
		this.securityUsers = securityUsers;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
