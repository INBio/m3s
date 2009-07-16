/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.dto.metadata.UsePolicyDTO;

/**
 * @author jgutierrez
 *
 */
public interface UsePolicyDAO extends BaseDAO{
	
	@Deprecated
	public UsePolicyDTO getUsePolicyLite(Integer usePolicyId) throws IllegalArgumentException;
	
	@Deprecated
	public UsePolicyDTO getUsePolicyLite(String name) throws IllegalArgumentException;

	@Deprecated
	public List<UsePolicyDTO> listAllLite() throws IllegalArgumentException;
	
}
