/**
 * 
 */
package org.inbio.m3s.dao.core;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.model.general.Institution;

/**
 * @author jgutierrez
 * 
 *
 */
public interface InstitutionDAO extends BaseDAO{

	/**
	 * 
	 * @param institutionName
	 * @return
	 */
	public Institution findByName(String institutionName);
	
	
	
}
