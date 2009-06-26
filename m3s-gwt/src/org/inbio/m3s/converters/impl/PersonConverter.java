/**
 * 
 */
package org.inbio.m3s.converters.impl;

import java.util.ArrayList;
import java.util.List;

import org.inbio.m3s.converters.Converter;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.gwt.client.dto.util.PersonGWTDTO;

/**
 * @author jgutierrez
 *
 */
public class PersonConverter implements Converter<PersonLiteDTO, PersonGWTDTO> {

	public PersonGWTDTO toGWTDTO(PersonLiteDTO dto) {
		if(dto == null)
			return null;
		PersonGWTDTO pDTOGWT = new PersonGWTDTO();
		pDTOGWT.setPersonKey(dto.getPersonKey());
		pDTOGWT.setName(dto.getName());
		return pDTOGWT;
	}


	public PersonLiteDTO toDTO(PersonGWTDTO gwtdto) {
		if(gwtdto == null)
			return null;
		PersonLiteDTO pLiteDTO = new  PersonLiteDTO();
		pLiteDTO.setPersonKey(gwtdto.getPersonKey());
		pLiteDTO.setName(gwtdto.getName());
		return pLiteDTO;
	}


	public List<PersonLiteDTO> toDTOList(List<PersonGWTDTO> gwtdtoList) {
		if(gwtdtoList==null)
			return null;
		List<PersonLiteDTO> dtoList = new ArrayList<PersonLiteDTO>();
		for (PersonGWTDTO gwtdto: gwtdtoList)
			dtoList.add(toDTO(gwtdto));
		return dtoList;
	}


	public List<PersonGWTDTO> toGWTDTOList(List<PersonLiteDTO> dtoList) {
		if(dtoList==null)
			return null;
		List<PersonGWTDTO> gwtdtoList = new ArrayList<PersonGWTDTO>();
		for (PersonLiteDTO dto: dtoList)
			gwtdtoList.add(toGWTDTO(dto));
		return gwtdtoList;
	}

	

}
