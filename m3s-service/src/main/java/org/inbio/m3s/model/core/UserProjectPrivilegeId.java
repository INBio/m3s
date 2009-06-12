package org.inbio.m3s.model.core;

import java.io.Serializable;

/**
 * 
 * @author jgutierrez
 *
 */
public class UserProjectPrivilegeId implements Serializable {

	private static final long serialVersionUID = 492369068251225592L;

	private int projectId;

	private int privilegeId;

	private String userName;

	public UserProjectPrivilegeId() {
	}

	public UserProjectPrivilegeId(int projectId, int privilegeId,
			String userName) {
		this.projectId = projectId;
		this.privilegeId = privilegeId;
		this.userName = userName;
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserProjectPrivilegeId))
			return false;
		UserProjectPrivilegeId castOther = (UserProjectPrivilegeId) other;

		return (this.getProjectId() == castOther.getProjectId())
				&& (this.getPrivilegeId() == castOther.getPrivilegeId())
				&& ((this.getUserName() == castOther.getUserName()) || (this
						.getUserName() != null
						&& castOther.getUserName() != null && this
						.getUserName().equals(castOther.getUserName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProjectId();
		result = 37 * result + this.getPrivilegeId();
		result = 37 * result
				+ (getUserName() == null ? 0 : this.getUserName().hashCode());
		return result;
	}

}
