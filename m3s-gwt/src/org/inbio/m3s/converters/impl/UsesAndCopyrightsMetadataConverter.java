/**
 * 
 */
package org.inbio.m3s.converters.impl;

import java.util.ArrayList;
import java.util.List;

import org.inbio.m3s.converters.BaseConverter;
import org.inbio.m3s.converters.Converter;
import org.inbio.m3s.dto.metadata.MediaUseDTO;
import org.inbio.m3s.dto.metadata.UsesAndCopyrightsDTO;
import org.inbio.m3s.gwt.client.dto.metadata.MediaUseGWTDTO;
import org.inbio.m3s.gwt.client.dto.metadata.UsesAndCopyrightsGWTDTO;

/**
 * @author jgutierrez
 *
 */
public class UsesAndCopyrightsMetadataConverter extends BaseConverter<UsesAndCopyrightsDTO, UsesAndCopyrightsGWTDTO> implements Converter<UsesAndCopyrightsDTO, UsesAndCopyrightsGWTDTO> {

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.converters.Converter#toGWTDTO(org.inbio.m3s.dto.BaseDTO)
	 */
	public UsesAndCopyrightsGWTDTO toGWTDTO(UsesAndCopyrightsDTO dto) {
		if(dto == null)
			return null;
		
		UsesAndCopyrightsGWTDTO uacGWTDTO = new UsesAndCopyrightsGWTDTO(dto.getMediaKey(),dto.getAuthorKey(),dto.getPersonOwnerKey(),
				dto.getInstitutionOwnerKey(),dto.getUsePolicyKey(),null,false,false); 
		
		List<MediaUseGWTDTO> muGWTDTOList = new ArrayList<MediaUseGWTDTO>();
		for(MediaUseDTO muDTO : dto.getMediaUsesList())
			muGWTDTOList.add(new MediaUseGWTDTO(muDTO.getMediaUseKey(),muDTO.getMediaUseName()));
		uacGWTDTO.setMediaUsesList(muGWTDTOList);
	
		if (dto.getIsBackup().charValue() == 'Y')
			uacGWTDTO.setIsBackup(true);
		else
			uacGWTDTO.setIsBackup(false);
	
		if (dto.getIsPublic().charValue() == 'Y')
			uacGWTDTO.setIsPublic(true);
		else
			uacGWTDTO.setIsPublic(false);
	
		return uacGWTDTO;
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.converters.Converter#toDTO(java.lang.Object)
	 */	
	public UsesAndCopyrightsDTO toDTO(UsesAndCopyrightsGWTDTO gwtdto) {
		if(gwtdto == null)
			return null;
		
		UsesAndCopyrightsDTO uacDTO = new UsesAndCopyrightsDTO(gwtdto.getMediaKey(),gwtdto.getAuthorKey(),gwtdto.getPersonOwnerKey(),
				gwtdto.getInstitutionOwnerKey(),gwtdto.getUsePolicyKey(),null,null,null);
		
		List<MediaUseDTO> muDTOList = new ArrayList<MediaUseDTO>();
		
		for(MediaUseGWTDTO muGWTDTO : gwtdto.getMediaUsesList())
			muDTOList.add(new MediaUseDTO(muGWTDTO.getMediaUseKey(),muGWTDTO.getMediaUseName()));
		uacDTO.setMediaUsesList(muDTOList);
	
		if (gwtdto.getIsBackup() == true)
			uacDTO.setIsBackup(new Character('Y'));
		else
			uacDTO.setIsBackup(new Character('N'));
		
		if (gwtdto.getIsPublic() == true)
			uacDTO.setIsPublic(new Character('Y'));
		else
			uacDTO.setIsPublic(new Character('N'));
		
		return uacDTO;
	}

}
