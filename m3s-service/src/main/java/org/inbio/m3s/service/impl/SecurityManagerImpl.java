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

/**
 * @author jgutierrez
 * 
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
