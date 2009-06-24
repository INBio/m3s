/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.dto.lite.MediaAttributeTypeLite;
import org.inbio.m3s.model.core.MediaAttributeType;

/**
 * @author jgutierrez
 *
 */
public interface MediaAttributeTypeDAO extends BaseDAO{
	
	@Deprecated
	public List<MediaAttributeTypeLite> getAllByMediaType(Integer mediaTypeId) throws IllegalArgumentException;
	
	public List<MediaAttributeType> findAllByMediaType(String mediaTypeKey) throws IllegalArgumentException;

}
