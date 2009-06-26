/**
 * 
 */
package org.inbio.m3s.converters.impl;

import java.util.ArrayList;
import java.util.List;

import org.inbio.m3s.converters.Converter;
import org.inbio.m3s.dto.agent.InstitutionLiteDTO;
import org.inbio.m3s.gwt.client.dto.util.InstitutionLiteGWTDTO;

/**
 * @author jgutierrez
 *
 */
public class InstitutionConverter implements Converter<InstitutionLiteDTO, InstitutionLiteGWTDTO> {


	public InstitutionLiteGWTDTO toGWTDTO(InstitutionLiteDTO dto) {
		if(dto == null)
			return null;
		InstitutionLiteGWTDTO iDTOGWT = new InstitutionLiteGWTDTO();
		iDTOGWT.setInstitutionKey(dto.getInstitutionKey());
		iDTOGWT.setName(dto.getName());
		return iDTOGWT;
	}

	public InstitutionLiteDTO toDTO(InstitutionLiteGWTDTO gwtdto) {
		if(gwtdto == null)
			return null;
		InstitutionLiteDTO iDTO = new InstitutionLiteDTO();
		iDTO.setInstitutionKey(gwtdto.getInstitutionKey());
		iDTO.setName(gwtdto.getName());
		return iDTO;
	}
	
	  /**
	   * 
	   * @param dtoList
	   * @return
	   */
	public List<InstitutionLiteGWTDTO> toGWTDTOList(List<InstitutionLiteDTO> dtoList) {
		if(dtoList==null)
			return null;
		List<InstitutionLiteGWTDTO> gwtdtoList = new ArrayList<InstitutionLiteGWTDTO>();
		for (InstitutionLiteDTO dto: dtoList)
			gwtdtoList.add(toGWTDTO(dto));
		return gwtdtoList;
	}
	
	/**
	 * 
	 * @param gwtdtoList
	 * @return
	 */
	public List<InstitutionLiteDTO> toDTOList(List<InstitutionLiteGWTDTO> gwtdtoList) {
		if(gwtdtoList==null)
			return null;
		List<InstitutionLiteDTO> dtoList = new ArrayList<InstitutionLiteDTO>();
		for (InstitutionLiteGWTDTO gwtdto: gwtdtoList)
			dtoList.add(toDTO(gwtdto));
		return dtoList;
	}



}
