package org.inbio.m3s.model.core;

import java.util.Date;

import org.inbio.m3s.model.LogGenericEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class Privilege extends LogGenericEntity {

	private static final long serialVersionUID = 7997101139869693755L;

	private Integer privilegeId;

	private String name;

	//private Set<UserProjectPrivilege> userProjectPrivileges = new HashSet<UserProjectPrivilege>(0);

	public Privilege() {
	}

	public Privilege(String name) {
		this.name = name;
	}

	public Privilege(String name, Date creationDate, String createdBy,
			Date lastModificationDate, String lastModificationBy
			//,Set<UserProjectPrivilege> userProjectPrivileges) 
			){
		this.name = name;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
		//this.userProjectPrivileges = userProjectPrivileges;
	}

	public Integer getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
/*
	public Set<UserProjectPrivilege> getUserProjectPrivileges() {
		return this.userProjectPrivileges;
	}

	public void setUserProjectPrivileges(
			Set<UserProjectPrivilege> userProjectPrivileges) {
		this.userProjectPrivileges = userProjectPrivileges;
	}
*/

}
