/**
 * 
 */
package org.inbio.m3s.service;

import java.util.List;

import org.inbio.m3s.dto.media.BriefMediaOutputDTO;
import org.inbio.m3s.dto.metadata.MetadataDTO;
import org.inbio.m3s.dto.metadata.TechnicalMetadataDTO;
import org.inbio.m3s.dto.metadata.TechnicalMetadataItemDTO;


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
	 * @param quantity
	 * @return
	 * @throws IllegalArgumentException
	 */
	public List<BriefMediaOutputDTO> getLastPublicMetadataBrief(int quantity) throws IllegalArgumentException;
	
	/**
	 * 
	 * @param mediaKey
	 * @return
	 * @throws IllegalArgumentException
	 */
	public BriefMediaOutputDTO getMetadataBriefByMedia(String mediaKey) throws IllegalArgumentException;
	
	/**
	 * 
	 * @param metadataDTO
	 * @throws IllegalArgumentException
	 */
	public void updateMetadata(MetadataDTO metadataDTO) throws IllegalArgumentException; 
	
	/**
	 * Saves a new MetadataDTO in the database
	 * 
	 * @param metadataDTO
	 * @return the ID of the new Media
	 * @throws IllegalArgumentException
	 */
	public Integer saveMetadata(MetadataDTO metadataDTO) throws IllegalArgumentException;

	/**
	 * 
	 * @param mediaKey
	 * @param mediaAttributesList
	 * @throws IllegalArgumentException
	 */
	public void saveTechnicalMetadata(String mediaKey, List<TechnicalMetadataItemDTO> mediaAttributesList) throws IllegalArgumentException;

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
	
	
	/**  Are this methods private?**/
	/*
	public void addProjectsList(String mediaKey, List<ProjectDTO> projectLiteList) throws IllegalArgumentException;
	
	public void addKeywordsList(String mediaKey, List<KeywordDTO> keywordsList) throws IllegalArgumentException;

	public void deleteKeywords(String mediaKey, List<KeywordDTO> keywordsList) throws IllegalArgumentException;

	public void addMediaUses(String mediaKey, List<MediaUseDTO> mediaUsesList) throws IllegalArgumentException;

	public void deleteMediaUses(String mediaKey, List<MediaUseDTO> mediaUsesList) throws IllegalArgumentException;

	public void addTaxons(String mediaKey, List<TaxonLiteDTO> taxonsList) throws IllegalArgumentException;

	public void deleteTaxons(String mediaKey, List<TaxonLiteDTO> taxonsList) throws IllegalArgumentException;
	
	public void addGatherings(String mediaKey, List<GatheringLiteDTO> gatheringsList) throws IllegalArgumentException;

	public void deleteGatherings(String mediaKey, List<GatheringLiteDTO> gatheringsList) throws IllegalArgumentException;

	public void addSpecimens(String mediaKey, List<SpecimenLiteDTO> specimensList) throws IllegalArgumentException;

	public void deleteSpecimens(String mediaKey, List<SpecimenLiteDTO> specimensList) throws IllegalArgumentException;

	public void addObservations(String mediaKey, List<ObservationLiteDTO> observationsList) throws IllegalArgumentException;
	
	public void deleteObservations(String mediaKey, List<ObservationLiteDTO> observationsList) throws IllegalArgumentException;
*/
}
