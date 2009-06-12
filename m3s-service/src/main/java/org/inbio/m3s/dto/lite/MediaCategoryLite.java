/**
 * 
 */
package org.inbio.m3s.dto.lite;

import java.io.Serializable;

/**
 * @author jgutierrez
 *
 */
public class MediaCategoryLite implements Serializable{
	

	private static final long serialVersionUID = -5368930809936836379L;

	private Integer mediaCategoryId;
	
	private String mediaCategoryName;

	//lista de media types?
	
	/**
	 * @param mediaCategoryId
	 * @param mediaCategoryName
	 */
	public MediaCategoryLite(Integer mediaCategoryId, String mediaCategoryName) {
		super();
		this.mediaCategoryId = mediaCategoryId;
		this.mediaCategoryName = mediaCategoryName;
	}

	/**
	 * 
	 */
	public MediaCategoryLite() {
		super();
	}

	/**
	 * @return the mediaCategoryId
	 */
	public Integer getMediaCategoryId() {
		return mediaCategoryId;
	}

	/**
	 * @param mediaCategoryId the mediaCategoryId to set
	 */
	public void setMediaCategoryId(Integer mediaCategoryId) {
		this.mediaCategoryId = mediaCategoryId;
	}

	/**
	 * @return the mediaCategoryName
	 */
	public String getMediaCategoryName() {
		return mediaCategoryName;
	}

	/**
	 * @param mediaCategoryName the mediaCategoryName to set
	 */
	public void setMediaCategoryName(String mediaCategoryName) {
		this.mediaCategoryName = mediaCategoryName;
	}


	
	
	
}
