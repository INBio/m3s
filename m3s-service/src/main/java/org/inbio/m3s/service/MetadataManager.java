/**
 * 
 */
package org.inbio.m3s.service;

import org.inbio.m3s.dto.metadata.TechnicalMetadataDTO;


/**
 * @author jgutierrez
 *
 */
public interface MetadataManager {
	
	public TechnicalMetadataDTO getTechMetadataByMedia(String mediaKey);
	
	public TechnicalMetadataDTO getTechMetadataByMediaType(String mediaTypeKey);
	
	public TechnicalMetadataDTO getTechMetadataFromFile(String mediaTypeKey, String fileAddress);

}
