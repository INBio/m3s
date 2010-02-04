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
public class InstitutionLiteDTOFactory extends BaseDTOFactory<Institution,InstitutionLiteDTO> {

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dto.DTOFactory#createDTO(java.lang.Object)
	 */
	public InstitutionLiteDTO createDTO(Institution entity) {
		if(entity == null)
				return null;
		InstitutionLiteDTO dto = new InstitutionLiteDTO();
		populateInstitutionLite(entity, dto);
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
