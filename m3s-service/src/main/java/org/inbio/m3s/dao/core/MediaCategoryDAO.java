/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.dto.message.MediaCategoryDTO;

/**
 * @author jgutierrez
 *
 */
public interface MediaCategoryDAO extends BaseDAO{
	
	@Deprecated
	public MediaCategoryDTO getMediaCategoryLiteFromMediaType(Integer mediaTypeId, int language) throws IllegalArgumentException;
	
	@Deprecated
	public MediaCategoryDTO getMediaCategoryLite(Integer mediaCategoryId) throws IllegalArgumentException;
	
	@Deprecated
	public MediaCategoryDTO getMediaCategoryLite(String categoryName) throws IllegalArgumentException;

	@Deprecated
	public List<MediaCategoryDTO> listAllLite() throws Exception;

}
