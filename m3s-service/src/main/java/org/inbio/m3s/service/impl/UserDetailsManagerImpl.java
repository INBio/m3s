/**
 * 
 */
package org.inbio.m3s.service.impl;

import org.inbio.m3s.dao.core.SystemUserDAO;
import org.inbio.m3s.model.core.SystemUser;
import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsManager;
import org.springframework.security.userdetails.UsernameNotFoundException;

/**
 * @author jgutierrez
 *
 */
public class UserDetailsManagerImpl implements UserDetailsManager {
	
	private SystemUserDAO systemUserDAO;

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetailsManager#changePassword(java.lang.String, java.lang.String)
	 */
	public void changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetailsManager#createUser(org.springframework.security.userdetails.UserDetails)
	 */
	public void createUser(UserDetails user) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetailsManager#deleteUser(java.lang.String)
	 */
	public void deleteUser(String username) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetailsManager#updateUser(org.springframework.security.userdetails.UserDetails)
	 */
	public void updateUser(UserDetails user) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetailsManager#userExists(java.lang.String)
	 */
	public boolean userExists(String username) {
		
		return systemUserDAO.findByUsername(username) != null;
			
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		SystemUser su = systemUserDAO.findByUsername(username);

		if(su==null)
			throw new UsernameNotFoundException("username does not exist in the database.");
		
		return su;

	}

	/**
	 * @return the systemUserDAO
	 */
	public SystemUserDAO getSystemUserDAO() {
		return systemUserDAO;
	}

	/**
	 * @param systemUserDAO the systemUserDAO to set
	 */
	public void setSystemUserDAO(SystemUserDAO systemUserDAO) {
		this.systemUserDAO = systemUserDAO;
	}

}
