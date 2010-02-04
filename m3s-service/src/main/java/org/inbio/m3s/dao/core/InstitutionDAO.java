/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.GenericBaseDAO;
import org.inbio.m3s.model.general.Institution;

/**
 * @author jgutierrez
 * 
 *
 */
public interface InstitutionDAO extends GenericBaseDAO<Institution, Integer>{
	/**
	 * 
	 * @param institutionName
	 * @returns
	 */
	public Institution findByName(String institutionName);

	public List<Institution> findAllByPartialNamePaginated(String partialName, int maxResults);
	
	
	
}

