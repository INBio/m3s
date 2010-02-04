/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.GenericBaseDAO;
import org.inbio.m3s.model.core.GatheringMedia;
import org.inbio.m3s.model.core.GatheringMediaId;


/**
 * @author jgutierrez
 *
 */
public interface GatheringMediaDAO extends GenericBaseDAO<GatheringMedia,GatheringMediaId>{
	
	public List<GatheringMediaId> findAllByMediaId(Integer mediaId) throws IllegalArgumentException;

}
