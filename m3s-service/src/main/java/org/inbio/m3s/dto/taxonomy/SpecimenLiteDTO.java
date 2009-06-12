/**
 * 
 */
package org.inbio.m3s.dto.taxonomy;

import java.io.Serializable;

/**
 * @author jgutierrez
 *
 */
public class SpecimenLiteDTO implements Serializable {

	private static final long serialVersionUID = 8660560976413993377L;
	
	private String specimenKey;

	/**
	 * 
	 */
	public SpecimenLiteDTO() {
		super();
	}

	/**
	 * @param specimenKey
	 */
	public SpecimenLiteDTO(String specimenKey) {
		super();
		this.setSpecimenKey(specimenKey);
	}

	/**
	 * @param specimenKey the specimenKey to set
	 */
	public void setSpecimenKey(String specimenKey) {
		this.specimenKey = specimenKey;
	}

	/**
	 * @return the specimenKey
	 */
	public String getSpecimenKey() {
		return specimenKey;
	}

	
}
