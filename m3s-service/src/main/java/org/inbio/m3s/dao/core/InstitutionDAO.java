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
//public interface InstitutionDAO<E extends Institution,I extends Object> extends BaseDAO<E,I>{
public interface InstitutionDAO extends BaseDAO{
	/**
	 * 
	 * @param institutionName
	 * @returns
	 */
	public Institution findByName(String institutionName);
	
	
	
}

