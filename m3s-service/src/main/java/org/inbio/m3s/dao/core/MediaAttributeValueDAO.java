/**
 * 
 */
package org.inbio.m3s.dao.core;

import org.inbio.m3s.dao.GenericBaseDAO;
import org.inbio.m3s.dto.mediaattribute.MediaAttributeValueFullDTO;
import org.inbio.m3s.model.core.MediaAttributeValue;
import org.inbio.m3s.model.core.MediaAttributeValueId;

/**
 * @author jgutierrez
 *
 */
public interface MediaAttributeValueDAO extends GenericBaseDAO<MediaAttributeValue, MediaAttributeValueId> {

	//usar el del manager
	public MediaAttributeValueFullDTO getMediaAttributeValueFull(Integer mediaId, Integer mediaAttributeId) throws IllegalArgumentException;
	
//usar el manager
	//public void insertMediaAttributeValueFull(MediaAttributeValueFullDTO mavFull) throws IllegalArgumentException;
	
//usar el manager
	public void updateMediaAttributeValueFull(MediaAttributeValueFullDTO mavFull) throws IllegalArgumentException; 
	
	
}
