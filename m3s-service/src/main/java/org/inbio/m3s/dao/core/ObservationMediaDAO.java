/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.GenericBaseDAO;
import org.inbio.m3s.model.core.ObservedTaxonMedia;
import org.inbio.m3s.model.core.ObservedTaxonMediaId;

/**
 * @author jgutierrez
 *
 */
public interface ObservationMediaDAO extends GenericBaseDAO<ObservedTaxonMedia, ObservedTaxonMediaId>{

	public List<ObservedTaxonMediaId> findAllByMediaId(final Integer mediaId) throws IllegalArgumentException;
}
