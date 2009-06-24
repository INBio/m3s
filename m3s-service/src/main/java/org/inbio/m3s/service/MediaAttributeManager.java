/**
 * 
 */
package org.inbio.m3s.service;

import org.inbio.m3s.dto.mediaattribute.MediaAttributeValueFullDTO;

/**
 * @author jgutierrez
 *
 */
public interface MediaAttributeManager {
	
	public MediaAttributeValueFullDTO getMediaAttributeValueFull(Integer mediaId, Integer mediaAttributeId) throws IllegalArgumentException;
	
	@Deprecated
	public void insertMediaAttributeValueFull(MediaAttributeValueFullDTO mavFull) throws IllegalArgumentException;
	
	@Deprecated
	public void updateMediaAttributeValueFull(MediaAttributeValueFullDTO mavFull) throws IllegalArgumentException; 
	
	
}
