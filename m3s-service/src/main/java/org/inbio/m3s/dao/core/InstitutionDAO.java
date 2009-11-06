/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

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
	 * @returns
	 */
	public Institution findByName(String institutionName);

	public List<Institution> findAllByPartialNamePaginated(String partialName, int maxResults);
	
	
	
}

