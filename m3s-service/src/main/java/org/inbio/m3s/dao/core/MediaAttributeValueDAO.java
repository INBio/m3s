/**
 * 
 */
package org.inbio.m3s.dao.core;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.dto.mediaattribute.MediaAttributeValueFullDTO;

/**
 * @author jgutierrez
 *
 */
public interface MediaAttributeValueDAO extends BaseDAO {

	//usar el del manager
	public MediaAttributeValueFullDTO getMediaAttributeValueFull(Integer mediaId, Integer mediaAttributeId) throws IllegalArgumentException;
	
//usar el manager
	//public void insertMediaAttributeValueFull(MediaAttributeValueFullDTO mavFull) throws IllegalArgumentException;
	
//usar el manager
	public void updateMediaAttributeValueFull(MediaAttributeValueFullDTO mavFull) throws IllegalArgumentException; 
	
	
}
