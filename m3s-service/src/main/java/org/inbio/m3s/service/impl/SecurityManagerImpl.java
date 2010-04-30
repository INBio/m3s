/**
 * 
 */
package org.inbio.m3s.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.dao.core.SecurityUserDAO;
import org.inbio.m3s.model.core.SecurityUsers;
import org.inbio.m3s.service.SecurityManager;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.User;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;

/**
 * @author jgutierrez
 * @deprecated
 * @use UserDetailsManagerImpl
 */
public class SecurityManagerImpl implements SecurityManager {

	protected static Log logger = LogFactory.getLog(SecurityManagerImpl.class);

	SecurityUserDAO securityUserDAO;

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.SecurityManager#isValidUser(java.lang.String, java.lang.String)
	 */
	public boolean isValidUser(String username, String password) throws IllegalArgumentException {
		
		List<SecurityUsers> suList = securityUserDAO.findAllByUserName(username);
		
		for(SecurityUsers su : suList){
			if (password.compareTo(su.getPassword()) == 0) {
				logger.debug("isValidUser... done with afirmative result");
				return true;
			}
		}
		
		logger.debug("isValidUser... done with negative result");
		return false;
	}
	
	/**
	 * 
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		GrantedAuthority[] authorities = {
				new GrantedAuthorityImpl("ROLE_USER")
		}; 
		User user = new User("jgutierrez", "prueba",true, true, true, true, authorities);
		return user;
	}

	/**
	 * @param securityUserDAO
	 *          the securityUserDAO to set
	 */
	public void setSecurityUserDAO(SecurityUserDAO securityUserDAO) {
		this.securityUserDAO = securityUserDAO;
	}

	/**
	 * @return the securityUserDAO
	 */
	public SecurityUserDAO getSecurityUserDAO() {
		return securityUserDAO;
	}

}
