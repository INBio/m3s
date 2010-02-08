/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.GenericBaseDAO;
import org.inbio.m3s.exception.TaxonNotFoundException;
import org.inbio.m3s.model.taxonomy.Taxon;

/**
 * @author jgutierrez
 *
 */
public interface TaxonDAO extends GenericBaseDAO<Taxon, Integer> {
	

	public List<Taxon> findAllByName(String defaultName) throws IllegalArgumentException;
	
	/**
	 * This method is useful for the autocompleteManager implementation, receives part of the
	 * taxon name and looks for all the possible names. 
	 * 
	 * @param partialTaxonName
	 * @param maxResults
	 * @return
	 * @throws IllegalArgumentException
	 */
	public List<Taxon> findAllByPartialNamePaginated(String partialTaxonName, int maxResults) throws IllegalArgumentException;
	
	/**
	 * 
	 * @param taxonimicalRangeId
	 * @param partialTaxonName
	 * @return
	 */
	public List<Taxon> findAllByRangeAndPartialNamePaginated(final Integer taxonimicalRangeId,final String partialTaxonName);

	public Taxon findByDefaultNameAndKingdomId(String defaultName, Integer kingdomTaxonId) throws TaxonNotFoundException;

	public Taxon findBySpecimenId(Integer specimenId) throws IllegalArgumentException;
	
	public List<Taxon> findByObservationId(Integer observationId) throws IllegalArgumentException;
	

	/**
	 * 
	 * @param taxonDefaultName
	 * @param taxonomicalRangeId
	 * @return
	 */
	public Taxon findByNameAndRange(String taxonDefaultName, Integer taxonomicalRangeId);

	/**
	 * 
	 * @param taxonId
	 * @return
	 */
	public List<Taxon> findByOrder(Integer orderTaxonId);
	
	/**
	 * 
	 * @param taxonId
	 * @return
	 */
	public List<Taxon> findByFamily(Integer familyTaxonId);

	/**
	 * 
	 * @param genusTaxonId
	 * @return
	 */
	public List<Taxon> findByGenus(Integer genusTaxonId);

	/**
	 * 
	 * @param speciesTaxonId
	 * @return
	 */
	public List<Taxon> findBySpecies(Integer speciesTaxonId);
	

}
