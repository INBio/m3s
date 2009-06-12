/**
 * 
 */
package org.inbio.m3s.dto.agent;

import org.inbio.m3s.dto.BaseDTOFactory;
import org.inbio.m3s.model.general.Institution;

/**
 * @author jgutierrez
 *
 */
public class InstitutionLiteDTOFactory extends BaseDTOFactory {

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dto.DTOFactory#createDTO(java.lang.Object)
	 */
	public Object createDTO(Object entity) {
		if(entity == null)
				return null;
		Institution i = (Institution) entity;
		InstitutionLiteDTO dto = new InstitutionLiteDTO();
		populateInstitutionLite(i, dto);
		return dto;
	}

	/**
	 * Copy properties from model object to dto.
	 * @param i
	 * @param dto
	 */
	public void populateInstitutionLite(Institution i, InstitutionLiteDTO dto){
		
		dto.setInstitutionKey(i.getInstitutionId().toString());
		dto.setName(i.getName());
				
	}

}
