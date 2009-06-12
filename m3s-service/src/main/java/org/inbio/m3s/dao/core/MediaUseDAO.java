/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.dto.lite.MediaUseLite;

/**
 * @author jgutierrez
 *
 */
public interface MediaUseDAO extends BaseDAO{

	public MediaUseLite getMediaUseLite(String mediaUseName, Integer languageId) throws IllegalArgumentException;
	
	public MediaUseLite getMediaUseLite(Integer mediaUseId, Integer languageId) throws IllegalArgumentException;
	
	public List<MediaUseLite> listAllLite(Integer languageId) throws IllegalArgumentException;
	
	public List<MediaUseLite> getMediaUsesLite(Integer mediaId, Integer languageId) throws IllegalArgumentException;
	
}
