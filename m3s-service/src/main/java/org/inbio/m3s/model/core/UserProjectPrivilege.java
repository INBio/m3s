package org.inbio.m3s.model.core;

import java.util.Date;

import org.inbio.m3s.model.LogGenericEntity;


public class UserProjectPrivilege extends LogGenericEntity {

	private static final long serialVersionUID = -1749501202414972537L;

	private UserProjectPrivilegeId id;

	private Privilege privilege;

	private SystemUser user;

	private Project project;

	public UserProjectPrivilege() {
	}

	public UserProjectPrivilege(UserProjectPrivilegeId id, Privilege privilege,
			SystemUser user, Project project) {
		this.id = id;
		this.privilege = privilege;
		this.user = user;
		this.project = project;
	}

	public UserProjectPrivilege(UserProjectPrivilegeId id, Privilege privilege,
			SystemUser user, Project project, Date creationDate,
			String createdBy, Date lastModificationDate,
			String lastModificationBy) {
		this.id = id;
		this.privilege = privilege;
		this.user = user;
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

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @return the user
	 */
	public SystemUser getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(SystemUser user) {
		this.user = user;
	}

}
