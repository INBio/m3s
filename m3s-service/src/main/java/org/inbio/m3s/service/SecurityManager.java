/**
 * 
 */
package org.inbio.m3s.service;

import org.springframework.security.userdetails.UserDetailsService;


/**
 * @author jgutierrez
 *
 *@Deprecated
 */
public interface SecurityManager extends UserDetailsService {
	
	//public boolean isValidUser(String username, String password) throws IllegalArgumentException;

}
