/**
 * 
 */
package org.inbio.m3s.web.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.service.TaxonomyManager;

/**
 * The simple data for the taxon case is this way:
 * 
 * Scientific Name1 [Kingdom NameX];Scientific Name2 [Kingdom NameY]; ... Scientific NameN [Kingdom NameZ];
 * 
 * where:
 * - N as an integer (we hope the taxons list is a finite number ;))
 * - X,Y and Z could be what ever kingdom. 
 * 
 * In a general way, one scientific name with the corresponding Kingdom name
 * 
 * 
 * @author jgutierrez
 *
 */
public class TaxonGuiOrDTOConverter implements GuiOrDTOConverter<TaxonLiteDTO> {

	private TaxonomyManager taxonomyManager;

	/*
	 * 
	 * @param simpleData: should be something with the format 'Scientific Name [Kingdom Name]'
	 *  
	 * (non-Javadoc)
	 * @see org.inbio.m3s.web.converter.GuiOrDTOConverter#toDTO(java.lang.String)
	 */
	public TaxonLiteDTO toDTO(String simpleData) {
		
		String kingdomName = StringUtils.substringBetween(simpleData, "[", "]");
		String taxonName = StringUtils.substringBefore(simpleData, "[");
		
		TaxonLiteDTO tlDTO = taxonomyManager.getTaxonLite(taxonName.trim(), kingdomName.trim());
		return taxonomyManager.setKingdomName(tlDTO);
	}

	/*
	 * The main goal of this method should be to create tokens with the simpleData into more simpler 
	 * data chunks that should be pack in a DTO using the toDTO method.
	 * 
	 * (non-Javadoc)
	 * @see org.inbio.m3s.web.converter.GuiOrDTOConverter#toDTOList(java.lang.String)
	 */
	public List<TaxonLiteDTO> toDTOList(String simpleData) {

		List<TaxonLiteDTO> taxonLiteDTOList = new ArrayList<TaxonLiteDTO>();
		TaxonLiteDTO tlDTO;
		
		if(simpleData != null && simpleData != "" ){
			
			String[] tokens = StringUtils.split(simpleData, ';');
			
			for(String token : tokens){
				tlDTO = toDTO(token);
				taxonLiteDTOList.add(tlDTO);
			}
			
		}

		return taxonLiteDTOList;
	}
 
/*
 * (non-Javadoc)
 * @see org.inbio.m3s.web.converter.GuiOrDTOConverter#toString(java.util.List)
 */
	public String toString(List<TaxonLiteDTO> dtoList) {
		
		String simpleData ="";
		
		if(dtoList!=null && dtoList.size()>0){
			
			//no kingdom names...
			if(dtoList.get(0).getKingdomName() == null)
				dtoList = taxonomyManager.setKingdomName(dtoList);
		
			for(TaxonLiteDTO tlDTO : dtoList)
				simpleData += tlDTO.getDefaultName()+" ["+tlDTO.getKingdomName()+"];";
			
		}
		
		return simpleData;
	}

	/**
	 * @return the taxonomyManager
	 */
	public TaxonomyManager getTaxonomyManager() {
		return taxonomyManager;
	}

	/**
	 * @param taxonomyManager the taxonomyManager to set
	 */
	public void setTaxonomyManager(TaxonomyManager taxonomyManager) {
		this.taxonomyManager = taxonomyManager;
	}
		
}
