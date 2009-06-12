/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.model.atta.Taxon;

/**
 * @author jgutierrez
 *
 */
public interface TaxonDAO extends BaseDAO {
	

	public List<Taxon> findAllByName(String defaultName) throws IllegalArgumentException;
	

	public Taxon findByDefaultNameAndKingdomId(String defaultName, Integer kingdomTaxonId) throws IllegalArgumentException;

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
