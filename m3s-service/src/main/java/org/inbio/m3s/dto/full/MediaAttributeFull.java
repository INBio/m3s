/**
 * 
 */
package org.inbio.m3s.dto.full;

import java.io.Serializable;


/**
 * @author jgutierrez
 *
 */
public class MediaAttributeFull implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3167083065184885036L;

	private Integer mediaAttributeId;

	private String description;

	private String name;

	private String mediaAttributeTableName;

	private char mediaAttributeValueType;

	private char mediaAttributeTypePredefined;

	/**
	 * 
	 */
	public MediaAttributeFull() {
	}

	/**
	 * @param mediaAttributeId
	 * @param description
	 * @param name
	 * @param mediaAttributeTableName
	 * @param mediaAttributeValueType
	 * @param mediaAttributeTypePredefined
	 */
	public MediaAttributeFull(Integer mediaAttributeId, String description, String name, String mediaAttributeTableName, char mediaAttributeValueType, char mediaAttributeTypePredefined) {
		this.mediaAttributeId = mediaAttributeId;
		this.description = description;
		this.name = name;
		this.mediaAttributeTableName = mediaAttributeTableName;
		this.mediaAttributeValueType = mediaAttributeValueType;
		this.mediaAttributeTypePredefined = mediaAttributeTypePredefined;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the mediaAttributeId
	 */
	public Integer getMediaAttributeId() {
		return mediaAttributeId;
	}

	/**
	 * @param mediaAttributeId the mediaAttributeId to set
	 */
	public void setMediaAttributeId(Integer mediaAttributeId) {
		this.mediaAttributeId = mediaAttributeId;
	}

	/**
	 * @return the mediaAttributeTableName
	 */
	public String getMediaAttributeTableName() {
		return mediaAttributeTableName;
	}

	/**
	 * @param mediaAttributeTableName the mediaAttributeTableName to set
	 */
	public void setMediaAttributeTableName(String mediaAttributeTableName) {
		this.mediaAttributeTableName = mediaAttributeTableName;
	}

	/**
	 * @return the mediaAttributeTypePredefined
	 */
	public char getMediaAttributeTypePredefined() {
		return mediaAttributeTypePredefined;
	}

	/**
	 * @param mediaAttributeTypePredefined the mediaAttributeTypePredefined to set
	 */
	public void setMediaAttributeTypePredefined(char mediaAttributeTypePredefined) {
		this.mediaAttributeTypePredefined = mediaAttributeTypePredefined;
	}

	/**
	 * @return the mediaAttributeValueType
	 */
	public char getMediaAttributeValueType() {
		return mediaAttributeValueType;
	}

	/**
	 * @param mediaAttributeValueType the mediaAttributeValueType to set
	 */
	public void setMediaAttributeValueType(char mediaAttributeValueType) {
		this.mediaAttributeValueType = mediaAttributeValueType;
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

}
