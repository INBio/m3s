/**
 * 
 */
package org.inbio.m3s.config;

/**
 * This class has to manage the specific variables for a given user.
 * 
 * 
 * @author jgutierrez
 * 
 */
public class UserProfile {

	private static String username = "admin";

	/**
	 * @param username
	 *            the username to set
	 */
	public static void setUsername(String username) {
		UserProfile.username = username;
	}

	/**
	 * @return the username
	 */
	public static String getUsername() {
		return username;
	}

}
