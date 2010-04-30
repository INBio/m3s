/**
 * 
 */
package org.inbio.m3s.dao.core;

import org.inbio.m3s.dao.GenericBaseDAO;
import org.inbio.m3s.model.core.SystemUser;

/**
 * @author jgutierrez
 *
 */
public interface SystemUserDAO extends GenericBaseDAO<SystemUser, Integer> {


	/**
	 * 
	 * @param username
	 * @return
	 */
  public SystemUser findByUsername(String username);
  
}
