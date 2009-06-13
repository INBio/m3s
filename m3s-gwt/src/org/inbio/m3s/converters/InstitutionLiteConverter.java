/**
 * 
 */
package org.inbio.m3s.converters;

import org.inbio.m3s.dto.agent.InstitutionLiteDTO;
import org.inbio.m3s.gwt.client.dto.util.InstitutionLiteDTOGWT;

/**
 * @author jgutierrez
 *
 */
public class InstitutionLiteConverter extends BaseDTOGWTFactory {

	public Object createDTOGWT(Object serviceDTO) {
		if(serviceDTO == null)
				return null;
		InstitutionLiteDTO iLiteDTO = (InstitutionLiteDTO) serviceDTO;
		InstitutionLiteDTOGWT iDTOGWT = new InstitutionLiteDTOGWT();
		populateInstitutionLiteDTOGWT(iLiteDTO, iDTOGWT);
		return iDTOGWT;
	}

	/**
	 * Copy properties from service dto to gwt dto.
	 * @param serviceDTO
	 * @param gwtDTO
	 */
	public void populateInstitutionLiteDTOGWT(InstitutionLiteDTO serviceDTO, InstitutionLiteDTOGWT gwtDTO){
		
		gwtDTO.setInstitutionKey(serviceDTO.getInstitutionKey());
		gwtDTO.setName(serviceDTO.getName());
				
	}
	

}
