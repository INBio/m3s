/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.dto.message.MediaTypeDTO;
import org.inbio.m3s.model.core.MediaType;

/**
 * @author jgutierrez
 *
 */
public interface MediaTypeDAO extends BaseDAO{

	@Deprecated
	public MediaTypeDTO getMediaTypeLite(Integer mediaTypeId, int language) throws IllegalArgumentException;
	@Deprecated
	public MediaTypeDTO getMediaTypeLite(String mediaTypeName) throws IllegalArgumentException;
	@Deprecated
	public List<MediaTypeDTO> listAllForMediaCategoryLite(Integer mediaCategoryId) throws IllegalArgumentException;

	public MediaType findByName(String mediaTypeName);
	
}
