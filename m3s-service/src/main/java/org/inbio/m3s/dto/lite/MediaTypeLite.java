/**
 * 
 */
package org.inbio.m3s.dto.lite;

import java.io.Serializable;

/**
 * @author jgutierrez
 *
 */
public class MediaTypeLite implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1925741921547016685L;

	private Integer mediaTypeId;
	
	private String MediaTypeName;

	//private String/Integer mediaCategory;
	
	/**
	 * 
	 */
	public MediaTypeLite() {
		super();
	}

	/**
	 * @param mediaTypeId
	 * @param mediaTypeName
	 */
	public MediaTypeLite(Integer mediaTypeId, String mediaTypeName) {
		super();
		this.mediaTypeId = mediaTypeId;
		MediaTypeName = mediaTypeName;
	}

	/**
	 * @return the mediaTypeId
	 */
	public Integer getMediaTypeId() {
		return mediaTypeId;
	}

	/**
	 * @param mediaTypeId the mediaTypeId to set
	 */
	public void setMediaTypeId(Integer mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
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
	
	

}
