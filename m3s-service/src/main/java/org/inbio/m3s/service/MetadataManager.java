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
	
	public TechnicalMetadataDTO getTechnicalMetadata(Integer mediaId);

}
