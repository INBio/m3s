/**
 * 
 */
package org.inbio.m3s.dto.taxonomy;

import java.io.Serializable;

/**
 * @author jgutierrez
 * 
 */
public class GatheringLiteDTO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6357305267344639304L;

	private String gatheringKey;
	
	private String responsiblePersonName;

	/**
	 * 
	 */
	public GatheringLiteDTO() {
		super();
	}

	/**
	 * @param gatheringKey
	 * @param responsiblePersonName
	 */
	public GatheringLiteDTO(String gatheringKey, String responsiblePersonName) {
		super();
		this.setGatheringKey(gatheringKey);
		this.setResponsiblePersonName(responsiblePersonName);
	}
	
	@Override
	public String toString(){
		return "EL Gathering Lite DTO tiene:" +
		"\n\tKey : " + this.getGatheringKey() +
		"\n\tNombre del Responsable de la Colecta: '"+ this.getResponsiblePersonName() +"'" +
		"";
	}

	/**
	 * @param gatheringKey the gatheringKey to set
	 */
	public void setGatheringKey(String gatheringKey) {
		this.gatheringKey = gatheringKey;
	}

	/**
	 * @return the gatheringKey
	 */
	public String getGatheringKey() {
		return gatheringKey;
	}

	/**
	 * @param responsiblePersonName the responsiblePersonName to set
	 */
	public void setResponsiblePersonName(String responsiblePersonName) {
		this.responsiblePersonName = responsiblePersonName;
	}

	/**
	 * @return the responsiblePersonName
	 */
	public String getResponsiblePersonName() {
		return responsiblePersonName;
	}

	
}
