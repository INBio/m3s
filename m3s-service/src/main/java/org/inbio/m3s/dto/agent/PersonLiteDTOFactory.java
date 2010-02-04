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
public class PersonLiteDTOFactory extends BaseDTOFactory<Person, PersonLiteDTO> {

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dto.DTOFactory#createDTO(java.lang.Object)
	 */
	public PersonLiteDTO createDTO(Person p) {
		if(p == null)
				return null;
		return new PersonLiteDTO(p.getPersonId(), p.getFirstName()+" "+ p.getLastName());
	}

}
