/**
 * 
 */
package org.inbio.m3s.dto.mediaattribute;

import org.inbio.m3s.dto.BaseDTOFactory;
import org.inbio.m3s.model.core.MediaAttributeValue;

/**
 * @author jgutierrez
 *
 */
public class MediaAttributeValueFullDTOFactory extends BaseDTOFactory<MediaAttributeValue,MediaAttributeValueFullDTO> {

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dto.DTOFactory#createDTO(java.lang.Object)
	 */
	public MediaAttributeValueFullDTO createDTO(MediaAttributeValue mav) {
		if(mav == null)
				return null;
		MediaAttributeValueFullDTO dto = new MediaAttributeValueFullDTO();
		populateMediaAttributeValueFull(mav, dto);
		return dto;
	}

	/**
	 * Copy properties from model object to dto.
	 * @param mav
	 * @param dto
	 */
	public void populateMediaAttributeValueFull(MediaAttributeValue mav, MediaAttributeValueFullDTO dto){
		
		dto.setMediaId(mav.getId().getMediaId());
		dto.setMediaAttributeId(mav.getId().getMediaAttributeId());
		dto.setValueVarchar(mav.getValueVarchar());
		dto.setValueId(mav.getValueId());
		dto.setValueNumber(mav.getValueNumber());
		dto.setValueDate(mav.getValueDate());
		
		//bitácora
		dto.setCreationDate(mav.getCreationDate());
		dto.setCreatedBy(mav.getCreatedBy());
		dto.setLastModificationBy(mav.getLastModificationBy());
		dto.setLastModificationDate(mav.getLastModificationDate());
				
	}

}
