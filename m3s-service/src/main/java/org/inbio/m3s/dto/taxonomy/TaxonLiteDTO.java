/**
 * 
 */
package org.inbio.m3s.dto.taxonomy;

import org.inbio.m3s.dto.BaseDTO;

/**
 * @author jgutierrez
 *
 */
public class TaxonLiteDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7171633841004967522L;

	private String taxonKey;
	
	private String defaultName;
	
	private String kingdomKey;

	/* The kingdom name will be useful for some operations of the GUI that need
	 * not only the kingdomKey... this value won't be filled by default,
	 * in those cases is responsibility of the programmer to set the value */
	private String kingdomName;
	
	/**
	 * 
	 */
	public TaxonLiteDTO() {
		super();
	}
	
	/**
	 * @param taxonKey
	 * @param defaultName
	 * @param kingdomKey
	 */
	public TaxonLiteDTO(String taxonKey, String defaultName, String kingdomKey) {
		this.taxonKey = taxonKey;
		this.defaultName = defaultName;
		this.kingdomKey = kingdomKey;
	}
	
	/**
	 * @param taxonKey
	 * @param defaultName
	 * @param kingdomKey
	 * @param kingdomName
	 */
	public TaxonLiteDTO(String taxonKey, String defaultName, String kingdomKey, String kingdomName) {
		this.taxonKey = taxonKey;
		this.defaultName = defaultName;
		this.kingdomKey = kingdomKey;
		this.kingdomName = kingdomName;
	}	
	
	/**
	 * 
	 */
	@Override
	public String toString(){
		return "El Taxon Lite DTO tiene:" +
				"\n\tTaxonId: " + this.getTaxonKey() +
				"\n\tDefault Name: " + this.getDefaultName() +
				"\n\tKingdom key: " + this.getKingdomKey() +
				"\n\tKingdom Name: " + this.getKingdomName() +
				"";
	}

	/**
	 * @return the taxonKey
	 */
	public String getTaxonKey() {
		return taxonKey;
	}

	/**
	 * @param taxonKey the taxonKey to set
	 */
	public void setTaxonKey(String taxonKey) {
		this.taxonKey = taxonKey;
	}

	/**
	 * @return the defaultName
	 */
	public String getDefaultName() {
		return defaultName;
	}

	/**
	 * @param defaultName the defaultName to set
	 */
	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	/**
	 * @return the kingdomKey
	 */
	public String getKingdomKey() {
		return kingdomKey;
	}

	/**
	 * @param kingdomKey the kingdomKey to set
	 */
	public void setKingdomKey(String kingdomKey) {
		this.kingdomKey = kingdomKey;
	}

	/**
	 * @return the kingdomName
	 */
	public String getKingdomName() {
		return kingdomName;
	}

	/**
	 * @param kingdomName the kingdomName to set
	 */
	public void setKingdomName(String kingdomName) {
		this.kingdomName = kingdomName;
	}
	
}
