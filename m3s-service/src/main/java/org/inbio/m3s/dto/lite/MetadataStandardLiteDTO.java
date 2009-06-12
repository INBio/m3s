/**
 * 
 */
package org.inbio.m3s.dto.lite;

import java.io.Serializable;

/**
 * @author jgutierrez
 *
 */
public class MetadataStandardLiteDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private Integer metadataStandardId;
	
	private Integer standardAttributeId;
	
	
	public MetadataStandardLiteDTO(){
		
	}
	
	
	/**
	 * @param name
	 * @param metadataStandardId
	 * @param standardAttributeId
	 */
	public MetadataStandardLiteDTO(String name, Integer metadataStandardId,
			Integer standardAttributeId) {
		this.name = name;
		this.metadataStandardId = metadataStandardId;
		this.standardAttributeId = standardAttributeId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the metadataStandardId
	 */
	public Integer getMetadataStandardId() {
		return metadataStandardId;
	}

	/**
	 * @param metadataStandardId the metadataStandardId to set
	 */
	public void setMetadataStandardId(Integer metadataStandardId) {
		this.metadataStandardId = metadataStandardId;
	}

	/**
	 * @return the standardAttributeId
	 */
	public Integer getStandardAttributeId() {
		return standardAttributeId;
	}

	/**
	 * @param standardAttributeId the standardAttributeId to set
	 */
	public void setStandardAttributeId(Integer standardAttributeId) {
		this.standardAttributeId = standardAttributeId;
	}




}
