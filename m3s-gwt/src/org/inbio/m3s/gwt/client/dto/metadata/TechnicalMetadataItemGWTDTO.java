package org.inbio.m3s.gwt.client.dto.metadata;


import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author jgutierrez
 *
 */
public class TechnicalMetadataItemGWTDTO implements IsSerializable {

	private String mediaAttributeKey;
	
	private String mediaAttributeName;

	private String value;

	/**
	 * 
	 */
	public TechnicalMetadataItemGWTDTO() {
		super();
	}

	/**
	 * @param institutionKey
	 * @param mediaAttributeName
	 * @param name
	 */
	public TechnicalMetadataItemGWTDTO(String mediaAttributeKey, String mediaAttributeName, String value) {
		super();
		this.setMediaAttributeKey(mediaAttributeKey);
		this.setMediaAttributeName(mediaAttributeName);
		this.setValue(value);
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
