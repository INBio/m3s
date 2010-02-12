/**
 * 
 */
package org.inbio.m3s.dto.metadata.util;

/**
 * @author jgutierrez
 *
 */
public enum MediaTypeEntity {

	
	DSC_IMAGE(1,"mediatype.dsc_image","",1),
	SCANNED_IMAGE(2,"mediatype.scanned_image","",1),
	SLIDE(3,"mediatype.slide_image","",1),
	VIDEO_MOV(4,"mediatype.video_mov","",3),
	MIGRATION(5,"mediatype.migration_image","",1),
	YOUTUBE_VIDEO(6,"mediatype.youtube_video","",3)
	; 


	//media Type
	private int mediaTypeId;
	//to be resolve in a properties i10n file
	private String namekey;
	//to be resolve in a properties i10n file
	private String descriptionkey;
	// the possible values are:
	// 1 -> Images
	// 2 -> Sound
	// 3 -> Video
	private int mediaCategoryId;

	
	/**
	 * @param mediaTypeId
	 * @param namekey
	 * @param descriptionkey
	 * @param mediaCategoryId
	 */
	private MediaTypeEntity(int mediaTypeId, String namekey,
			String descriptionkey, int mediaCategoryId) {
		this.mediaTypeId = mediaTypeId;
		this.namekey = namekey;
		this.descriptionkey = descriptionkey;
		this.mediaCategoryId = mediaCategoryId;
	}

	/**
	 * 
	 * @param mediaTypeId
	 * @return
	 */
	public static MediaTypeEntity getById(int mediaTypeId){
		MediaTypeEntity[] all = MediaTypeEntity.values();
		//the ENUM starts in 0 and the original table in 1 
		return all[mediaTypeId - 1];
	}

	/**
	 * @return the mediaTypeId
	 */
	public int getMediaTypeId() {
		return mediaTypeId;
	}


	/**
	 * @param mediaTypeId the mediaTypeId to set
	 */
	public void setMediaTypeId(int mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}


	/**
	 * @return the namekey
	 */
	public String getNamekey() {
		return namekey;
	}


	/**
	 * @param namekey the namekey to set
	 */
	public void setNamekey(String namekey) {
		this.namekey = namekey;
	}


	/**
	 * @return the descriptionkey
	 */
	public String getDescriptionkey() {
		return descriptionkey;
	}


	/**
	 * @param descriptionkey the descriptionkey to set
	 */
	public void setDescriptionkey(String descriptionkey) {
		this.descriptionkey = descriptionkey;
	}


	/**
	 * @return the mediaCategoryId
	 */
	public int getMediaCategoryId() {
		return mediaCategoryId;
	}


	/**
	 * @param mediaCategoryId the mediaCategoryId to set
	 */
	public void setMediaCategoryId(int mediaCategoryId) {
		this.mediaCategoryId = mediaCategoryId;
	}
	
	
}
