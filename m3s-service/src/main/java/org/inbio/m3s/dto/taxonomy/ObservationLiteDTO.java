/**
 * 
 */
package org.inbio.m3s.dto.taxonomy;

import java.io.Serializable;

/**
 * @author jgutierrez
 *
 */
public class ObservationLiteDTO implements Serializable{
	
	/* */
	private static final long serialVersionUID = 1L;
	
	private String observationKey;
	
	
	
	/**
	 * @param observationKey
	 */
	public ObservationLiteDTO(String observationKey) {
		super();
		this.observationKey = observationKey;
	}

	/**
	 * 
	 */
	public ObservationLiteDTO() {
		super();
	}
	
	
	
	/**
	 * @param observationKey the observationKey to set
	 */
	public void setObservationKey(String observationKey) {
		this.observationKey = observationKey;
	}
	/**
	 * @return the observationKey
	 */
	public String getObservationKey() {
		return observationKey;
	}
	

}
