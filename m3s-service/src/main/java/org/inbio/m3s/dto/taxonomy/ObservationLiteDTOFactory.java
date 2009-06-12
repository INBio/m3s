/**
 * 
 */
package org.inbio.m3s.dto.taxonomy;

import org.inbio.m3s.dto.BaseDTOFactory;
import org.inbio.m3s.model.core.ObservedTaxonMediaId;

/**
 * @author jgutierrez
 *
 */
public class ObservationLiteDTOFactory extends BaseDTOFactory {

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dto.DTOFactory#createDTO(java.lang.Object)
	 */
	public Object createDTO(Object entity) {
		if(entity == null)
				return null;
		
		else if(entity.getClass() == ObservedTaxonMediaId.class){
			ObservedTaxonMediaId otmId = (ObservedTaxonMediaId) entity;
			return new ObservationLiteDTO(String.valueOf(otmId.getObservationId()));
		} 
		
		return null;
	}
}
