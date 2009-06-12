/**
 * 
 */
package org.inbio.m3s.dto.lite;

import java.io.Serializable;

/**
 * @author jgutierrez
 *
 */
public class UserDTOLite  implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String password;

	/**
	 * 
	 */
	public UserDTOLite() {
	}
	
	/**
	 * @param name
	 * @param password
	 */
	public UserDTOLite(String name, String password) {
		this.name = name;
		this.password = password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
