/**
 * 
 */
package org.inbio.m3s.dto.taxonomy;

import org.apache.log4j.Logger;
import org.inbio.m3s.dto.BaseDTOFactory;
import org.inbio.m3s.model.core.SpecimenMediaId;

/**
 * @author jgutierrez
 *
 */
public class SpecimenLiteDTOFactory extends BaseDTOFactory<SpecimenMediaId, SpecimenLiteDTO> {
	
	private static Logger logger = Logger.getLogger(SpecimenLiteDTOFactory.class);

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dto.DTOFactory#createDTO(java.lang.Object)
	 */
	public SpecimenLiteDTO createDTO(SpecimenMediaId smId) {
		
		if(smId == null){
			logger.debug("createDTO with a null entity");
			return null;
				
		}
		
		logger.debug("createDTO with a: "+smId.getClass().toString());
		/*
		if(s instanceof Specimen){
			logger.debug("createDTO with a Specimen class");
			return new SpecimenLiteDTO(s.getSpecimenId().toString());
		
		} else*/
		if(smId instanceof SpecimenMediaId){
			logger.debug("createDTO with a SpecimenMediaId class");
			return new SpecimenLiteDTO(String.valueOf(smId.getSpecimenId()));
		}
		
		return null;
	}
}
