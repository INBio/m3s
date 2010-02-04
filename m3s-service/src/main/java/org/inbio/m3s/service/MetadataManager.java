/**
 * 
 */
package org.inbio.m3s.service;

import org.inbio.m3s.dto.metadata.MetadataDTO;
import org.inbio.m3s.dto.metadata.TechnicalMetadataDTO;


/**
 * @author jgutierrez
 *
 */
public interface MetadataManager {
	
	/**
	 * 
	 * @param mediaKey
	 * @return
	 * @throws IllegalArgumentException
	 */
	public MetadataDTO getMetadataByMedia(String mediaKey) throws IllegalArgumentException;

	/**
	 * 
	 * @param techMetadataDTO
	 */
	public void saveTechnicalMetadata(TechnicalMetadataDTO techMetadataDTO);

	/**
	 * Gets the Technical Metadata information from the database.
	 * 
	 * @param mediaKey
	 * @return
	 * @throws IllegalArgumentException is the mediaKey is invalid.
	 */
	public TechnicalMetadataDTO getTechMetadataByMedia(String mediaKey) throws IllegalArgumentException;

	/**
	 * This method returns a TechnicalMetadataDTO object with *no* values in the 
	 * TechnicalMetadataItemDTO List elements, ideal for user entry technicalMetadata
	 * 
	 * @param mediaTypeKey
	 * @return
	 */
	public TechnicalMetadataDTO getTechMetadataByMediaType(String mediaTypeKey);
	
	/**
	 * Returns the technical metadata that corresponds for the mediaType.
	 * If the file has no technical Metadata then a null TechnicalMetadataDTO will be return, 
	 * and is highly recommended to call the getTechMetadataByMediaType method to complete the DTO.   
	 * 
	 * @param mediaTypeKey
	 * @param fileAddress
	 * @return
	 * @see MetadataManager.getTechMetadataByMediaType(String mediaTypeKey)
	 */
	public TechnicalMetadataDTO getTechMetadataFromFile(String mediaTypeKey, String fileAddress);
	

}
