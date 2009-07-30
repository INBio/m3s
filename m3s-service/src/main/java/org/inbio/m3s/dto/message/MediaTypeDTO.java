/**
 * 
 */
package org.inbio.m3s.dto.message;

import java.io.Serializable;

/**
 * @author jgutierrez
 *
 */
public class MediaTypeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1925741921547016685L;

	private String mediaTypeKey;
	
	private String MediaTypeName;
	
	/**
	 * 
	 */
	public MediaTypeDTO() {
		super();
	}

	/**
	 * @param mediaTypeId
	 * @param mediaTypeName
	 */
	public MediaTypeDTO(Integer mediaTypeId, String mediaTypeName) {
		super();
		this.setMediaTypeKey(String.valueOf(mediaTypeId));
		MediaTypeName = mediaTypeName;
	}
	
	/**
	 * @param mediaTypeKey
	 * @param mediaTypeName
	 */
	public MediaTypeDTO(String mediaTypeKey, String mediaTypeName) {
		this.mediaTypeKey = mediaTypeKey;
		MediaTypeName = mediaTypeName;
	}


	/**
	 * @return the mediaTypeName
	 */
	public String getMediaTypeName() {
		return MediaTypeName;
	}

	/**
	 * @param mediaTypeName the mediaTypeName to set
	 */
	public void setMediaTypeName(String mediaTypeName) {
		MediaTypeName = mediaTypeName;
	}

	/**
	 * @param mediaTypeKey the mediaTypeKey to set
	 */
	public void setMediaTypeKey(String mediaTypeKey) {
		this.mediaTypeKey = mediaTypeKey;
	}

	/**
	 * @return the mediaTypeKey
	 */
	public String getMediaTypeKey() {
		return mediaTypeKey;
	}
	
	

}
