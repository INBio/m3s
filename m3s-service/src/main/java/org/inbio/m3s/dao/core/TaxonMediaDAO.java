/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.GenericBaseDAO;
import org.inbio.m3s.model.core.TaxonMedia;
import org.inbio.m3s.model.core.TaxonMediaId;

/**
 * @author jgutierrez
 *
 */
public interface TaxonMediaDAO extends GenericBaseDAO<TaxonMedia, TaxonMediaId>{
	
	//public List<Integer> getTaxonIdByMediaId(Integer mediaId);

	public List<TaxonMediaId> getByMediaId(Integer mediaId);

}
