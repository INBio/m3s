/**
 * 
 */
package org.inbio.m3s.dto.importcontrol;

import java.io.Serializable;

/**
 * @author jgutierrez
 *
 */
public class ImportControlDTOLite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6031170297863901696L;
	
	private String systemFileName;
	
	private String status;
	
	private String username;
	
	private String userFileName;
	

	/**
	 * 
	 */
	public ImportControlDTOLite() {
		super();
	}

	/**
	 * @param systemFileName
	 * @param status
	 * @param username
	 * @param userFileName
	 */
	public ImportControlDTOLite(String systemFileName, String status, String username, String userFileName) {
		super();
		this.systemFileName = systemFileName;
		this.status = status;
		this.username = username;
		this.userFileName = userFileName;
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
