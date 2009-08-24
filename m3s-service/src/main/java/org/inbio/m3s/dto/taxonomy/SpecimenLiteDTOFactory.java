/**
 * 
 */
package org.inbio.m3s.dto.taxonomy;

import org.apache.log4j.Logger;
import org.inbio.m3s.dto.BaseDTOFactory;
import org.inbio.m3s.model.core.SpecimenMediaId;
import org.inbio.m3s.model.general.Specimen;

/**
 * @author jgutierrez
 *
 */
public class SpecimenLiteDTOFactory extends BaseDTOFactory {
	
	private static Logger logger = Logger.getLogger(SpecimenLiteDTOFactory.class);

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dto.DTOFactory#createDTO(java.lang.Object)
	 */
	public Object createDTO(Object entity) {
		
		if(entity == null){
			logger.debug("createDTO with a null entity");
			return null;
				
		}
		
		logger.debug("createDTO with a: "+entity.getClass().toString());
		
		if(entity instanceof Specimen){
			logger.debug("createDTO with a Specimen class");
			Specimen s = (Specimen) entity;
			return new SpecimenLiteDTO(s.getSpecimenId().toString());
		
		} else if(entity instanceof SpecimenMediaId){
			logger.debug("createDTO with a SpecimenMediaId class");
			SpecimenMediaId smId = (SpecimenMediaId) entity;
			return new SpecimenLiteDTO(String.valueOf(smId.getSpecimenId()));
		}
		
		return null;
	}
}
