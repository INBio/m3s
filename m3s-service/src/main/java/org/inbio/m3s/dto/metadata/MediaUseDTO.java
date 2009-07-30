/**
 * 
 */
package org.inbio.m3s.dto.metadata;

import java.io.Serializable;

/**
 * @author jgutierrez
 *
 */
public class MediaUseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mediaUseKey;
	
	private String mediaUseName;
	
	
	@Override
	public String toString(){
		return "La Media Use DTO tiene:" +
		"\n\tKey : " + this.getMediaUseKey() +
		"\n\tName: '"+ this.getMediaUseName() +"'";
	}
	
	/**
	 * 
	 */
	public MediaUseDTO() {
		super();
	}

	/**
	 * @param mediaUseKey
	 * @param mediaUseName
	 */
	public MediaUseDTO(String mediaUseKey, String mediaUseName) {
		super();
		this.mediaUseKey = mediaUseKey;
		this.mediaUseName = mediaUseName;
	}

	/**
	 * @param mediaUseId
	 * @param mediaUseName
	 */
	public MediaUseDTO(Integer mediaUseId, String mediaUseName) {
		super();
		this.mediaUseKey = mediaUseId.toString();
		this.mediaUseName = mediaUseName;
	}
	
	/**
	 * @return the mediaUseKey
	 */
	public String getMediaUseKey() {
		return mediaUseKey;
	}

	/**
	 * @param mediaUseKey the mediaUseKey to set
	 */
	public void setMediaUseKey(String mediaUseKey) {
		this.mediaUseKey = mediaUseKey;
	}

	/**
	 * @return the mediaUseName
	 */
	public String getMediaUseName() {
		return mediaUseName;
	}

	/**
	 * @param mediaUseName the mediaUseName to set
	 */
	public void setMediaUseName(String mediaUseName) {
		this.mediaUseName = mediaUseName;
	}

}
