/**
 * 
 */
package org.inbio.m3s.dao.core;

import org.inbio.m3s.dao.GenericBaseDAO;
import org.inbio.m3s.model.core.UsePolicy;

/**
 * @author jgutierrez
 *
 */
public interface UsePolicyDAO extends GenericBaseDAO<UsePolicy, Integer>{
	
	public UsePolicy findByName(String usePolicyText);
	
}
