/**
 * 
 */
package org.inbio.m3s.dao.core;


/**
 * @author jgutierrez
 * 
 * @deprecated NO ES UN DAO, es de manager
 *
 */
public interface UserPrivilegeDAO {
	
	public boolean couldWrite(String username, Integer projectId) throws IllegalArgumentException;
	
	public boolean couldRead(String username, Integer projectId) throws IllegalArgumentException;
	
	
	
	

}
