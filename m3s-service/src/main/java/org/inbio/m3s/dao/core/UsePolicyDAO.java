/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.GenericBaseDAO;
import org.inbio.m3s.dto.metadata.UsePolicyDTO;
import org.inbio.m3s.model.core.UsePolicy;

/**
 * @author jgutierrez
 *
 */
public interface UsePolicyDAO extends GenericBaseDAO<UsePolicy, Integer>{
	
	@Deprecated
	public UsePolicyDTO getUsePolicyLite(Integer usePolicyId) throws IllegalArgumentException;
	
	@Deprecated
	public UsePolicyDTO getUsePolicyLite(String name) throws IllegalArgumentException;

	@Deprecated
	public List<UsePolicyDTO> listAllLite() throws IllegalArgumentException;

	public UsePolicy findByName(String usePolicyText);
	
}
