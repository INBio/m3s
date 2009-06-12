/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dto.lite.MediaCategoryLite;

/**
 * @author jgutierrez
 *
 */
public interface MediaCategoryDAO {
	
	public MediaCategoryLite getMediaCategoryLiteFromMediaType(Integer mediaTypeId, int language) throws IllegalArgumentException;
	
	public MediaCategoryLite getMediaCategoryLite(Integer mediaCategoryId) throws IllegalArgumentException;
	
	public MediaCategoryLite getMediaCategoryLite(String categoryName) throws IllegalArgumentException;

	public List<MediaCategoryLite> listAllLite() throws Exception;

}
