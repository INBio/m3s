/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.GenericBaseDAO;
import org.inbio.m3s.model.core.MediaAttribute;

/**
 * @author jgutierrez
 *
 */
public interface MediaAttributeDAO extends GenericBaseDAO<MediaAttribute, Integer> {
	
	public List<MediaAttribute> findAllByMediaType(String mediaTypeKey) throws IllegalArgumentException;
}
