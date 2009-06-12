package org.inbio.m3s.model.core;

import java.io.Serializable;

/**
 * 
 * @author jgutierrez
 *
 */
public class ImportControlId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String systemFileName;

	private String userName;

	public ImportControlId() {
	}

	public ImportControlId(String systemFileName, String userName) {
		this.systemFileName = systemFileName;
		this.userName = userName;
	}

	public String getSystemFileName() {
		return this.systemFileName;
	}

	public void setSystemFileName(String systemFileName) {
		this.systemFileName = systemFileName;
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
		if (!(other instanceof ImportControlId))
			return false;
		ImportControlId castOther = (ImportControlId) other;

		return ((this.getSystemFileName() == castOther.getSystemFileName()) || (this
				.getSystemFileName() != null
				&& castOther.getSystemFileName() != null && this
				.getSystemFileName().equals(castOther.getSystemFileName())))
				&& ((this.getUserName() == castOther.getUserName()) || (this
						.getUserName() != null
						&& castOther.getUserName() != null && this
						.getUserName().equals(castOther.getUserName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getSystemFileName() == null ? 0 : this.getSystemFileName()
						.hashCode());
		result = 37 * result
				+ (getUserName() == null ? 0 : this.getUserName().hashCode());
		return result;
	}

}
