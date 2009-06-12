/**
 * 
 */
package org.inbio.m3s.dto.agent;

import org.inbio.m3s.dto.BaseDTOFactory;
import org.inbio.m3s.model.general.Person;

/**
 * @author jgutierrez
 *
 */
public class PersonLiteDTOFactory extends BaseDTOFactory {

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dto.DTOFactory#createDTO(java.lang.Object)
	 */
	public Object createDTO(Object entity) {
		if(entity == null)
				return null;
		Person p = (Person) entity;
		PersonLiteDTO dto = new PersonLiteDTO();
		populatePersonLite(p, dto);
		return dto;
	}

	/**
	 * Copy properties from model object to dto.
	 * @param i
	 * @param dto
	 */
	public void populatePersonLite(Person p, PersonLiteDTO dto){
		
		dto.setPersonKey(p.getPersonId().toString());
		dto.setName(p.getFirstName() + " " + p.getLastName());
				
	}
	

}
