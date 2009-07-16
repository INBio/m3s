/**
 * 
 */
package org.inbio.m3s.dto.taxonomy;

import org.inbio.m3s.dto.BaseDTOFactory;
import org.inbio.m3s.model.core.SpecimenMediaId;
import org.inbio.m3s.model.general.Specimen;

/**
 * @author jgutierrez
 *
 */
public class SpecimenLiteDTOFactory extends BaseDTOFactory {

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dto.DTOFactory#createDTO(java.lang.Object)
	 */
	public Object createDTO(Object entity) {
		if(entity == null)
				return null;
		
		else if(entity.getClass() == Specimen.class){
			Specimen s = (Specimen) entity;
			return new SpecimenLiteDTO(s.getSpecimenId().toString());
		} else if(entity.getClass() == SpecimenMediaId.class){
			SpecimenMediaId smId = (SpecimenMediaId) entity;
			return new SpecimenLiteDTO(String.valueOf(smId.getSpecimenId()));
		}
		
		return null;
	}
}
