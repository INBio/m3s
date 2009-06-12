package org.inbio.m3s.model.core;


import java.io.Serializable;
import java.util.Date;

import org.inbio.m3s.model.DBLogEntity;


public class ImportControl extends DBLogEntity implements Serializable {

	private static final long serialVersionUID = 8590095924748002871L;

	private ImportControlId id;

	private SecurityUsers securityUsers;

	private String status;

	private String userFileName;

	public ImportControl() {
	}

	public ImportControl(SecurityUsers securityUsers,String status, String userFileName) {
		this.securityUsers = securityUsers;
		this.status = status;
		this.userFileName = userFileName;
	}
	
	public ImportControl(ImportControlId id, SecurityUsers securityUsers,
			String status, String userFileName) {
		this.id = id;
		this.securityUsers = securityUsers;
		this.status = status;
		this.userFileName = userFileName;
	}

	public ImportControl(ImportControlId id, SecurityUsers securityUsers,
			String status, Date creationDate, String createdBy,
			Date lastModificationDate, String lastModificationBy,
			String userFileName) {
		this.id = id;
		this.securityUsers = securityUsers;
		this.status = status;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
		this.userFileName = userFileName;
	}
	
	@Override
	public String toString(){
		return "id["+this.id+"] with status: "+this.status;
		
	}

	public ImportControlId getId() {
		return this.id;
	}

	public void setId(ImportControlId id) {
		this.id = id;
	}

	public SecurityUsers getSecurityUsers() {
		return this.securityUsers;
	}

	public void setSecurityUsers(SecurityUsers securityUsers) {
		this.securityUsers = securityUsers;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserFileName() {
		return this.userFileName;
	}

	public void setUserFileName(String userFileName) {
		this.userFileName = userFileName;
	}

}
