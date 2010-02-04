package org.inbio.m3s.model.core;


import java.util.Date;

import org.inbio.m3s.model.LogGenericEntity;


public class SecurityUsers extends LogGenericEntity {

	private static final long serialVersionUID = 832577557606661546L;

	private String name;

	private String description;

	private Float priority;

	private Float userType;

	private String password;

	private Character deleted;

	private Character systemGroup;

	//private Set<Project> projects = new HashSet<Project>(0);

	//private Set<UserProjectPrivilege> userProjectPrivileges = new HashSet<UserProjectPrivilege>(0);

	//private Set<ImportControl> importControls = new HashSet<ImportControl>(0);

	public SecurityUsers() {
	}

	public SecurityUsers(String password) {
		this.password = password;
	}

	public SecurityUsers(String description, Float priority, Float userType,
			String password, Character deleted, Character systemGroup,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy 
			//,Set<Project> projects,
			//Set<UserProjectPrivilege> userProjectPrivileges,
			//Set<ImportControl> importControls
			) {
		this.description = description;
		this.priority = priority;
		this.userType = userType;
		this.password = password;
		this.deleted = deleted;
		this.systemGroup = systemGroup;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
		//this.projects = projects;
		//this.userProjectPrivileges = userProjectPrivileges;
		//this.importControls = importControls;
	}

	@Override
	public String toString(){
		return "name["+this.name+"] and password["+this.password+"], description: "+this.description;
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

	public Float getPriority() {
		return this.priority;
	}

	public void setPriority(Float priority) {
		this.priority = priority;
	}

	public Float getUserType() {
		return this.userType;
	}

	public void setUserType(Float userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Character getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Character deleted) {
		this.deleted = deleted;
	}

	public Character getSystemGroup() {
		return this.systemGroup;
	}

	public void setSystemGroup(Character systemGroup) {
		this.systemGroup = systemGroup;
	}
/*
	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Set<UserProjectPrivilege> getUserProjectPrivileges() {
		return this.userProjectPrivileges;
	}

	public void setUserProjectPrivileges(
			Set<UserProjectPrivilege> userProjectPrivileges) {
		this.userProjectPrivileges = userProjectPrivileges;
	}

	public Set<ImportControl> getImportControls() {
		return this.importControls;
	}

	public void setImportControls(Set<ImportControl> importControls) {
		this.importControls = importControls;
	}
*/
}
