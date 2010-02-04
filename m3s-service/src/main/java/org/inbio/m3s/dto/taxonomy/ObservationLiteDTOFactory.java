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
public class ObservationLiteDTOFactory extends BaseDTOFactory<ObservedTaxonMediaId,ObservationLiteDTO> {

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dto.DTOFactory#createDTO(java.lang.Object)
	 */
	public ObservationLiteDTO createDTO(ObservedTaxonMediaId otmId) {
		if(otmId == null)
				return null;
		
		else if(otmId.getClass() == ObservedTaxonMediaId.class){
			return new ObservationLiteDTO(String.valueOf(otmId.getObservationId()));
		} 
		
		return null;
	}
}
