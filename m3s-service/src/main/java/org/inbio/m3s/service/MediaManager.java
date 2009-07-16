/**
 * 
 */
package org.inbio.m3s.service;

import java.util.List;

import org.inbio.m3s.dto.GeneralMetadataDTO;
import org.inbio.m3s.dto.metadata.MediaUseDTO;
import org.inbio.m3s.dto.metadata.TechnicalMetadataDTO;
import org.inbio.m3s.dto.metadata.UsesAndCopyrightsDTO;
import org.inbio.m3s.dto.lite.ProjectLite;
import org.inbio.m3s.dto.message.KeywordLiteDTO;
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
	
	public GeneralMetadataDTO getGM(Integer mediaId) throws IllegalArgumentException;
	
	public Integer insertNewMedia(GeneralMetadataDTO gm, UsesAndCopyrightsDTO uac, TechnicalMetadataDTO tmDTO) throws IllegalArgumentException;

	public void addProjectsList(Integer mediaId, List<ProjectLite> projectLiteList) throws IllegalArgumentException;
	
	public void addKeywordsList(Integer mediaId, List<KeywordLiteDTO> keywordsList) throws IllegalArgumentException;

	public void deleteKeywords(Integer mediaId, List<KeywordLiteDTO> keywordsList) throws IllegalArgumentException;

	public void addMediaUses(String mediaKey, List<MediaUseDTO> mediaUsesList) throws IllegalArgumentException;

	public void deleteMediaUses(String mediaKey, List<MediaUseDTO> mediaUsesList) throws IllegalArgumentException;

	public void addTaxons(Integer mediaId, List<TaxonLiteDTO> taxonsList) throws IllegalArgumentException;

	public void deleteTaxons(Integer mediaId, List<TaxonLiteDTO> taxonsList) throws IllegalArgumentException;
	
	public void addGatherings(Integer mediaId, List<GatheringLiteDTO> gatheringsList) throws IllegalArgumentException;

	public void deleteGatherings(Integer mediaId, List<GatheringLiteDTO> gatheringsList) throws IllegalArgumentException;

	public void addSpecimens(Integer mediaId, List<SpecimenLiteDTO> specimensList) throws IllegalArgumentException;

	public void deleteSpecimens(Integer mediaId, List<SpecimenLiteDTO> specimensList) throws IllegalArgumentException;

	public void addObservations(Integer mediaId, List<ObservationLiteDTO> observationsList) throws IllegalArgumentException;
	
	public void deleteObservations(Integer mediaId, List<ObservationLiteDTO> observationsList) throws IllegalArgumentException;
	
}
