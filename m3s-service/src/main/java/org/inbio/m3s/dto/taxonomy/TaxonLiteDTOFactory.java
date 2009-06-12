/**
 * 
 */
package org.inbio.m3s.dto.taxonomy;

import org.inbio.m3s.dto.BaseDTOFactory;
import org.inbio.m3s.dto.taxonomy.util.TaxonomicalRangeEntity;
import org.inbio.m3s.model.atta.Taxon;

/**
 * @author jgutierrez
 *
 */
public class TaxonLiteDTOFactory extends BaseDTOFactory {

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dto.DTOFactory#createDTO(java.lang.Object)
	 */
	public Object createDTO(Object entity) {
		if(entity == null)
				return null;
		Taxon t = (Taxon) entity;
		TaxonLiteDTO dto = new TaxonLiteDTO();
		populateTaxonLite(t, dto);
		return dto;
	}

	/**
	 * Copy properties from model object to dto.
	 * @param mav
	 * @param dto
	 */
	public void populateTaxonLite(Taxon t, TaxonLiteDTO dto){
		
		dto.setTaxonKey(t.getTaxonId().toString());
		dto.setDefaultName(t.getDefaultName());

		if(t.getTaxonomicalRangeId().intValue() == TaxonomicalRangeEntity.KINGDOM.getId())
			dto.setKingdomKey(t.getTaxonId().toString());
		else
			dto.setKingdomKey(t.getKingdomId().toString());
	}

}
