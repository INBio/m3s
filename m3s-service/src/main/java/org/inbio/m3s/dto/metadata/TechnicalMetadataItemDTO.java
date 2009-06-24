package org.inbio.m3s.dto.metadata;


import java.io.Serializable;

/**
 * @author jgutierrez
 *
 */
public class TechnicalMetadataItemDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 456870559838502944L;

	private String mediaAttributeKey;
	
	private String mediaAttributeName;

	private String value;

	/**
	 * 
	 */
	public TechnicalMetadataItemDTO() {
		super();
	}

	/**
	 * @param institutionKey
	 * @param mediaAttributeName
	 * @param name
	 */
	public TechnicalMetadataItemDTO(String mediaAttributeKey, String mediaAttributeName, String value) {
		super();
		this.setMediaAttributeKey(mediaAttributeKey);
		this.setMediaAttributeName(mediaAttributeName);
		this.setValue(value);
	}
	
	@Override
	public String toString(){
		return "El Tecnical Metadata Item DTO tiene:" +
		"\n\tKey : " + this.getMediaAttributeKey() +
		"\n\tMediaAttributeName : " + this.getMediaAttributeName() +
		"\n\tValue: "+ this.getValue() +
		"";
	}

	/**
	 * @param mediaAttributeKey the mediaAttributeKey to set
	 */
	public void setMediaAttributeKey(String mediaAttributeKey) {
		this.mediaAttributeKey = mediaAttributeKey;
	}

	/**
	 * @return the mediaAttributeKey
	 */
	public String getMediaAttributeKey() {
		return mediaAttributeKey;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @return the mediaAttributeName
	 */
	public String getMediaAttributeName() {
		return mediaAttributeName;
	}

	/**
	 * @param mediaAttributeName the mediaAttributeName to set
	 */
	public void setMediaAttributeName(String mediaAttributeName) {
		this.mediaAttributeName = mediaAttributeName;
	}

	
}
