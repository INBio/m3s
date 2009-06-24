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
	


	/**
	 * 
	 * @param techMetadataDTO
	 */
	public void saveTechnicalMetadata(TechnicalMetadataDTO techMetadataDTO);
	/**
	 * @param mediaKey
	 * @return
	 * @throws IllegalArgumentException
	 */
	public TechnicalMetadataDTO getTechMetadataByMedia(String mediaKey) throws IllegalArgumentException;
	/**
	 * @param mediaTypeKey
	 * @return
	 */
	public TechnicalMetadataDTO getTechMetadataByMediaType(String mediaTypeKey);
	/**
	 * @param mediaTypeKey
	 * @param fileAddress
	 * @return
	 */
	public TechnicalMetadataDTO getTechMetadataFromFile(String mediaTypeKey, String fileAddress);

}
