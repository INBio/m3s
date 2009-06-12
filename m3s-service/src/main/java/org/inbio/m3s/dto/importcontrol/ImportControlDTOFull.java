/**
 * 
 */
package org.inbio.m3s.dto.importcontrol;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jgutierrez
 *
 */
public class ImportControlDTOFull implements Serializable {

	private static final long serialVersionUID = 6031170297863901696L;
	
	private String systemFileName;
	
	private String status;
	
	private String username;
	
	private String userFileName;
	
	private Date creationDate;

	private String createdBy;

	private Date lastModificationDate;

	private String lastModificationBy;

	/**
	 * 
	 */
	public ImportControlDTOFull() {
		super();
	}

	/**
	 * @param systemFileName
	 * @param status
	 * @param username
	 * @param userFileName
	 * @param creationDate
	 * @param createdBy
	 * @param lastModificationDate
	 * @param lastModificationBy
	 */
	public ImportControlDTOFull(String systemFileName, String status, String username, String userFileName, Date creationDate, String createdBy, Date lastModificationDate, String lastModificationBy) {
		super();
		this.systemFileName = systemFileName;
		this.status = status;
		this.username = username;
		this.userFileName = userFileName;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastModificationDate = lastModificationDate;
		this.lastModificationBy = lastModificationBy;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the systemFileName
	 */
	public String getSystemFileName() {
		return systemFileName;
	}

	/**
	 * @param systemFileName the systemFileName to set
	 */
	public void setSystemFileName(String systemFileName) {
		this.systemFileName = systemFileName;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the userFileName
	 */
	public String getUserFileName() {
		return userFileName;
	}

	/**
	 * @param userFileName the userFileName to set
	 */
	public void setUserFileName(String userFileName) {
		this.userFileName = userFileName;
	}
	
	
	
	

}
