/**
 * 
 */
package org.inbio.m3s.converters;

import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.gwt.client.dto.util.PersonLiteDTOGWT;

/**
 * @author jgutierrez
 *
 */
public class PersonLiteConverter extends BaseDTOGWTFactory {

	public Object createDTOGWT(Object serviceDTO) {
		if(serviceDTO == null)
				return null;
		PersonLiteDTO pLiteDTO = (PersonLiteDTO) serviceDTO;
		PersonLiteDTOGWT pDTOGWT = new PersonLiteDTOGWT();
		populatePersonLiteDTOGWT(pLiteDTO, pDTOGWT);
		return pDTOGWT;
	}

	/**
	 * Copy properties from service dto to gwt dto.
	 * @param serviceDTO
	 * @param gwtDTO
	 */
	public void populatePersonLiteDTOGWT(PersonLiteDTO serviceDTO, PersonLiteDTOGWT gwtDTO){
		
		gwtDTO.setPersonKey(serviceDTO.getPersonKey());
		gwtDTO.setName(serviceDTO.getName());
				
	}
	

}
