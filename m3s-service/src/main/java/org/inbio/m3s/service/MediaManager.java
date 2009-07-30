/**
 * 
 */
package org.inbio.m3s.service;

import java.util.List;

import org.inbio.m3s.dto.metadata.GeneralMetadataDTO;
import org.inbio.m3s.dto.metadata.MediaUseDTO;
import org.inbio.m3s.dto.metadata.TechnicalMetadataDTO;
import org.inbio.m3s.dto.metadata.UsesAndCopyrightsDTO;
import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.dto.message.ProjectDTO;
import org.inbio.m3s.dto.taxonomy.GatheringLiteDTO;
import org.inbio.m3s.dto.taxonomy.ObservationLiteDTO;
import org.inbio.m3s.dto.taxonomy.SpecimenLiteDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;

/**
 * @author jgutierrez
 *
 */
public interface MediaManager {
	
	public void updateUACM(UsesAndCopyrightsDTO newUAC);
	
	public void updateGM(GeneralMetadataDTO newGm) throws IllegalArgumentException;
	
	public UsesAndCopyrightsDTO getUACM(String mediaKey) throws IllegalArgumentException;
	
	/**
	 * 
	 * @param mediaKey
	 * @return
	 * @throws IllegalArgumentException
	 */
	public GeneralMetadataDTO getGeneralMetadataByMedia(String mediaKey) throws IllegalArgumentException;
	
	
	public Integer insertNewMedia(GeneralMetadataDTO gm, UsesAndCopyrightsDTO uac, TechnicalMetadataDTO tmDTO) throws IllegalArgumentException;

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
	
}
