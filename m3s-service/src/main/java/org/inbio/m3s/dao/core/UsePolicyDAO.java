/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.dto.lite.UsePolicyLite;

/**
 * @author jgutierrez
 *
 */
public interface UsePolicyDAO extends BaseDAO{
	
	public UsePolicyLite getUsePolicyLite(Integer usePolicyId) throws IllegalArgumentException;
	
	public UsePolicyLite getUsePolicyLite(String name) throws IllegalArgumentException;

	public List<UsePolicyLite> listAllLite() throws IllegalArgumentException;
	
}
