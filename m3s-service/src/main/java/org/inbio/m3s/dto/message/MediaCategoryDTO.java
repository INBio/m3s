/**
 * 
 */
package org.inbio.m3s.dto.message;

import java.io.Serializable;

/**
 * @author jgutierrez
 *
 */
public class MediaCategoryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mediaCategoryKey;
	
	private String mediaCategoryName;
	
	/**
	 * 
	 */
	public MediaCategoryDTO() {
		super();
	}

	/**
	 * @param mediaUseKey
	 * @param mediaCategoryName
	 */
	public MediaCategoryDTO(String mediaCategoryKey, String mediaCategoryName) {
		super();
		this.mediaCategoryKey = mediaCategoryKey;
		this.mediaCategoryName = mediaCategoryName;
	}

	/**
	 * @param mediaCategoryId
	 * @param mediaUseName
	 */
	public MediaCategoryDTO(Integer mediaCategoryId, String mediaUseName) {
		super();
		this.mediaCategoryKey = mediaCategoryId.toString();
		this.mediaCategoryName = mediaUseName;
	}
	
	@Override
	public String toString(){
		return "La Media Category DTO tiene:" +
		"\n\tKey : " + this.getMediaCategoryKey() +
		"\n\tName: '"+ this.getMediaCategoryName() +"'";
	}


	/**
	 * @param mediaCategoryKey the mediaCategoryKey to set
	 */
	public void setMediaCategoryKey(String mediaCategoryKey) {
		this.mediaCategoryKey = mediaCategoryKey;
	}


	/**
	 * @return the mediaCategoryKeyUse
	 */
	public String getMediaCategoryKey() {
		return mediaCategoryKey;
	}


	/**
	 * @param mediaCategoryName the mediaCategoryName to set
	 */
	public void setMediaCategoryName(String mediaCategoryName) {
		this.mediaCategoryName = mediaCategoryName;
	}


	/**
	 * @return the mediaCategoryName
	 */
	public String getMediaCategoryName() {
		return mediaCategoryName;
	}
}
