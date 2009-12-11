/**
 * 
 */
package org.inbio.m3s.service;

import java.util.List;

import org.inbio.m3s.dto.taxonomy.GatheringLiteDTO;
import org.inbio.m3s.dto.taxonomy.ObservationLiteDTO;
import org.inbio.m3s.dto.taxonomy.SpecimenLiteDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.dto.taxonomy.util.TaxonomicalRangeEntity;
import org.inbio.m3s.exception.TaxonNotFoundException;

/**
 * @author jgutierrez
 *
 */
public interface TaxonomyManager {

	/**
	 * Este metodo es usado en las consultas! importante.
	 * Si es parte de este manager!
	 * 
	 * @param taxonDefaultName
	 * @param taxonomicalRange
	 * @return
	 */
	public List<TaxonLiteDTO> getTaxonsIncludedIn(String taxonDefaultName, TaxonomicalRangeEntity taxonomicalRange);
	

	/**
	 * Este metodo esta bien hecho, para este manager ;)
	 * 
	 * @param mediaKey
	 * @return
	 */
	public List<GatheringLiteDTO> getAllGatheringsLiteForMedia(String mediaKey);
	
	
	/**
	 * Este metodo esta bien hecho, para este manager ;)
	 * 
	 * @param mediaKey
	 * @return
	 */
	public List<SpecimenLiteDTO> getAllSpecimensLiteForMedia(String mediaKey);
	
	/**
	 * Este metodo esta bien hecho, para este manager ;)
	 * 
	 * @param mediaKey
	 * @return
	 * @throws IllegalArgumentException
	 */
	public List<ObservationLiteDTO> getAllObservationsLiteForMedia(String mediaKey) throws IllegalArgumentException;
	
	/**
	 * Este metodo esta bien hecho, para este manager ;)
	 * 
	 * @param gatheringCode
	 * @return
	 * @throws IllegalArgumentException
	 */
	public List<SpecimenLiteDTO> getSpecimenLiteForGatheringCode(String gatheringCode) throws IllegalArgumentException;
	
	
	/**
	 * El default name no es UNICO!, por eso este metodo retorna un list.
	 * 
	 */
	public List<TaxonLiteDTO> getTaxonLite(String defaultName) throws IllegalArgumentException;
	
	/**
	 * @param defaultName
	 *          the taxon name
	 * @param kingdom
	 *          the kingdom of the taxon default name
	 * @return 
	 * @throws IllegalArgumentException 
	 */
	public TaxonLiteDTO getTaxonLite(String defaultName, String kingdomName) throws TaxonNotFoundException;
	
	/**
	 *  -List de manera correcta
	 * 
	 * @param taxonId
	 * @return
	 * @throws IllegalArgumentException
	 */
	public TaxonLiteDTO getTaxonLiteById(String taxonKey) throws IllegalArgumentException;

	/**
	 * 
	 * @param specimenKey
	 * @return
	 * @throws IllegalArgumentException
	 */
	public TaxonLiteDTO getTaxonLiteFromSpecimenId(String specimenKey) throws IllegalArgumentException;
	
	/**
	 * 
	 * @param mediaKey
	 * @return
	 * @throws IllegalArgumentException
	 */
	public List<TaxonLiteDTO> getTaxonLiteForMediaId(String mediaKey) throws IllegalArgumentException;
	
	/**
	 * 
	 * @param observationKey
	 *            DB Identifier of the observation
	 * @return
	 * @throws IllegalArgumentException
	 */
	public List<TaxonLiteDTO> getTaxonLiteFromObservationId(String observationKey) throws IllegalArgumentException;
	
	/**
	 * bien
	 * 
	 * @param gatheringCode
	 * @return
	 * @throws IllegalArgumentException
	 */
	public List<TaxonLiteDTO> getTaxonLiteFromGatheringCode(String gatheringCode) throws IllegalArgumentException;
	
	
	public TaxonLiteDTO setKingdomName(TaxonLiteDTO tlDTO) throws IllegalArgumentException;
	
	public List<TaxonLiteDTO> setKingdomName(List<TaxonLiteDTO> tlDTOList) throws IllegalArgumentException;
}
