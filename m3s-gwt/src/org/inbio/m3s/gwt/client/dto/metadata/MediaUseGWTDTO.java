/**
 * 
 */
package org.inbio.m3s.gwt.client.dto.metadata;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author jgutierrez
 *
 */
public class MediaUseGWTDTO implements IsSerializable {

	private String mediaUseKey;
	
	private String mediaUseName;

	/**
	 * 
	 */
	public MediaUseGWTDTO() {
	}

	/**
	 * @param mediaUseKey
	 * @param mediaUseName
	 */
	public MediaUseGWTDTO(String mediaUseKey, String mediaUseName) {
		this.mediaUseKey = mediaUseKey;
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
