/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.dto.metadata.MediaUseDTO;

/**
 * @author jgutierrez
 *
 */
public interface MediaUseDAO extends BaseDAO{
	@Deprecated
	public MediaUseDTO getMediaUseLite(String mediaUseName, Integer languageId) throws IllegalArgumentException;
	@Deprecated
	public MediaUseDTO getMediaUseLite(Integer mediaUseId, Integer languageId) throws IllegalArgumentException;
	@Deprecated
	public List<MediaUseDTO> listAllLite(Integer languageId) throws IllegalArgumentException;
	@Deprecated
	public List<MediaUseDTO> getMediaUsesLite(Integer mediaId, Integer languageId) throws IllegalArgumentException;
	
	public List<MediaUseDTO> findAllByMediaAndLanguage(String mediaKey, String languageKey) throws IllegalArgumentException;
	
	public MediaUseDTO findByNameAndLanguage(String mediaUseName, String languageKey) throws IllegalArgumentException;
	
}
