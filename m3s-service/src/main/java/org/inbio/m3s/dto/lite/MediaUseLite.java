/**
 * 
 */
package org.inbio.m3s.dto.lite;

import java.io.Serializable;

/**
 * @author jgutierrez
 *
 */
public class MediaUseLite implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2802058652970688470L;

	private Integer mediaUseId;
	
	private String mediaUseName;

	/**
	 * 
	 */
	public MediaUseLite() {
		super();
	}

	/**
	 * @param mediaUseId
	 * @param mediaUseName
	 */
	public MediaUseLite(Integer mediaUseId, String mediaUseName) {
		super();
		this.mediaUseId = mediaUseId;
		this.mediaUseName = mediaUseName;
	}
	
	/**
	 * @return the mediaUseId
	 */
	public Integer getMediaUseId() {
		return mediaUseId;
	}

	/**
	 * @param mediaUseId the mediaUseId to set
	 */
	public void setMediaUseId(Integer mediaUseId) {
		this.mediaUseId = mediaUseId;
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
