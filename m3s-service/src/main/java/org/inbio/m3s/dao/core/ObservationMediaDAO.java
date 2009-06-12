/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.model.core.ObservedTaxonMediaId;

/**
 * @author jgutierrez
 *
 */
public interface ObservationMediaDAO extends BaseDAO{

	public List<ObservedTaxonMediaId> findAllByMediaId(final Integer mediaId) throws IllegalArgumentException;
}
