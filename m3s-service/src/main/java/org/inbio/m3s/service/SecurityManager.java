/**
 * 
 */
package org.inbio.m3s.service;


/**
 * @author jgutierrez
 *
 */
public interface SecurityManager {
	
	public boolean isValidUser(String username, String password) throws IllegalArgumentException;

}
