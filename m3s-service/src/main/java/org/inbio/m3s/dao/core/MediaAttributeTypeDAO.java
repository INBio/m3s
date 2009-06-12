/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dto.lite.MediaAttributeTypeLite;

/**
 * @author jgutierrez
 *
 */
public interface MediaAttributeTypeDAO {
	
	public List<MediaAttributeTypeLite> getAllByMediaType(Integer mediaTypeId) throws IllegalArgumentException;

}
