/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dto.lite.MetadataStandardLiteDTO;

/**
 * @author jgutierrez
 *
 */
public interface MetadataStandardDAO {

	//public MetadataStandardFull getMetadataStandardFull(Integer MetadataStandardId) throws IllegalArgumentException;
	
	public List<MetadataStandardLiteDTO> getMetadataStandardLite(Integer mediaTypeDBId, Integer languageId) throws IllegalArgumentException;
	
}
