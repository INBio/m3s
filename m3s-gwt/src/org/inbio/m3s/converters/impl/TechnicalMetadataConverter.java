/**
 * 
 */
package org.inbio.m3s.converters.impl;

import java.util.ArrayList;
import java.util.List;

import org.inbio.m3s.converters.Converter;
import org.inbio.m3s.dto.metadata.TechnicalMetadataDTO;
import org.inbio.m3s.dto.metadata.TechnicalMetadataItemDTO;
import org.inbio.m3s.gwt.client.dto.metadata.TechnicalMetadataGWTDTO;
import org.inbio.m3s.gwt.client.dto.metadata.TechnicalMetadataItemGWTDTO;

/**
 * @author jgutierrez
 *
 */
public class TechnicalMetadataConverter implements Converter<TechnicalMetadataDTO, TechnicalMetadataGWTDTO> {

	public TechnicalMetadataGWTDTO toGWTDTO(TechnicalMetadataDTO dto) {
		if(dto == null)
			return null;
		List<TechnicalMetadataItemGWTDTO> itemsDTOGWT = new ArrayList<TechnicalMetadataItemGWTDTO>();
		for(TechnicalMetadataItemDTO tmiDTO : dto.getItems())
			itemsDTOGWT.add(new TechnicalMetadataItemGWTDTO(tmiDTO.getMediaAttributeKey(),
				tmiDTO.getMediaAttributeName(),tmiDTO.getValue()));
	
		return new TechnicalMetadataGWTDTO(dto.getMediaKey(),dto.getMediaTypeKey(), itemsDTOGWT);
	}
	
	public TechnicalMetadataDTO toDTO(TechnicalMetadataGWTDTO gwtdto) {
		if(gwtdto == null)
			return null;
		List<TechnicalMetadataItemDTO> tmiDTOList = new ArrayList<TechnicalMetadataItemDTO>();
		for(TechnicalMetadataItemGWTDTO tmiGWTDTO : gwtdto.getItems())
			tmiDTOList.add(new TechnicalMetadataItemDTO(tmiGWTDTO.getMediaAttributeKey(),
					tmiGWTDTO.getMediaAttributeName(),tmiGWTDTO.getValue()));
	
		return new TechnicalMetadataDTO(gwtdto.getMediaKey(),gwtdto.getMediaTypeKey(), tmiDTOList);
	}


	public List<TechnicalMetadataDTO> toDTOList(List<TechnicalMetadataGWTDTO> gwtdtoList) {
		if(gwtdtoList==null)
			return null;
		List<TechnicalMetadataDTO> dtoList = new ArrayList<TechnicalMetadataDTO>();
		for (TechnicalMetadataGWTDTO gwtdto: gwtdtoList)
			dtoList.add(toDTO(gwtdto));
		return dtoList;
	}


	public List<TechnicalMetadataGWTDTO> toGWTDTOList(List<TechnicalMetadataDTO> dtoList) {
		if(dtoList==null)
			return null;
		List<TechnicalMetadataGWTDTO> gwtdtoList = new ArrayList<TechnicalMetadataGWTDTO>();
		for (TechnicalMetadataDTO dto: dtoList)
			gwtdtoList.add(toGWTDTO(dto));
		return gwtdtoList;
	}


	

}
