/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.model.core.TaxonMediaId;

/**
 * @author jgutierrez
 *
 */
public interface TaxonMediaDAO extends BaseDAO{
	
	//public List<Integer> getTaxonIdByMediaId(Integer mediaId);

	public List<TaxonMediaId> getByMediaId(Integer mediaId);

}
