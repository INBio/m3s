/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.model.core.GatheringMediaId;


/**
 * @author jgutierrez
 *
 */
public interface GatheringMediaDAO extends BaseDAO{
	
	public List<GatheringMediaId> findAllByMediaId(Integer mediaId) throws IllegalArgumentException;

}
