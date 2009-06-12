/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.dto.lite.MediaTypeLite;

/**
 * @author jgutierrez
 *
 */
public interface MediaTypeDAO extends BaseDAO{

	
	public MediaTypeLite getMediaTypeLite(Integer mediaTypeId, int language) throws IllegalArgumentException;
	
	public MediaTypeLite getMediaTypeLite(String mediaTypeName) throws IllegalArgumentException;

	public List<MediaTypeLite> listAllForMediaCategoryLite(Integer mediaCategoryId) throws IllegalArgumentException;
	
}
