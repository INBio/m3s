/**
 * 
 */
package org.inbio.m3s.dto;

import java.io.Serializable;

/**
 * All DTO's should extend this base!
 * 
 * @author jgutierrez
 *
 */
public class BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//log value
	private String username;
	private String logCreationDate;

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
	 * @return the logCreationDate
	 */
	public String getLogCreationDate() {
		return logCreationDate;
	}

	/**
	 * @param logCreationDate the logCreationDate to set
	 */
	public void setLogCreationDate(String logCreationDate) {
		this.logCreationDate = logCreationDate;
	}

}
