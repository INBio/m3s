/**
 * 
 */
package org.inbio.m3s.dto.taxonomy;

import java.io.Serializable;

/**
 * @author jgutierrez
 *
 */
public class TaxonLiteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7171633841004967522L;

	private String taxonKey;
	
	private String defaultName;
	
	private String kingdomKey;

	
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
	 * 
	 */
	@Override
	public String toString(){
		return "El Taxon Lite DTO tiene:" +
				"\n\tTaxonId: " + this.getTaxonKey() +
				"\n\tDefault Name: " + this.getDefaultName() +
				"\n\tKingdom key: " + this.getKingdomKey() +
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
	
}
