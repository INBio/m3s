/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.GenericBaseDAO;
import org.inbio.m3s.model.core.SecurityUsers;

/**
 * @author jgutierrez
 *
 */
public interface SecurityUserDAO extends GenericBaseDAO<SecurityUsers, String>{
	
	public List<SecurityUsers> findAllByUserName(String username) throws IllegalArgumentException;

}
