/**
 * 
 */
package org.inbio.m3s.dto.metadata.util;

/**
 * @author jgutierrez
 *
 */
public enum MediaAttributeEntity {

	ISO(1, "ISOSpeedRatings","",'N'), 
	CAMARA_MAKER(2, "Make","",'V'), 
	CAMARA_MODEL(3, "Model","",'V'),
	METERING_MODE(4, "MeteringMode","",'V'),
	EXPOSURE_MODE(5, "ExposureProgram","",'V'),
	F_NUMBER(6, "FNumber","",'V'),
	FOCAL_LENGTH(7, "FocalLength","",'V'),
	EXPOSURE_BIAS(8,"ExposureBiasValue","",'V'),
	WHITE_BALANCE(9, "WhiteBalance","",'V'),
	SATURATION(10, "Saturation","",'V'),
	FLASH(11,"Flash","",'V'),
	PIXELS_HEIGHT(12,"ExifImageLength","",'N'),
	PIXELS_WIDTH(13,"ExifImageWidth","",'N'),
	EXPOSURE_TIME(14,"ExposureTime","",'V'),
	PRODUCTION_DATE(15, "DateTimeOriginal","",'D'),
	FILE_EXTENSION(16, "FileExtension","",'V'),
	FILE_TYPE(17, "FileType","",'V'),
	FORMAT_NAME(18, "format_name","",'V'),
	ASPECT_RATIO(19, "aspect_radio","",'V'),
	PIXELS_HORIZONTAL(20, "pixels_horizontal","",'V'),
	PIXELS_VERTICAL(21, "pixels_vertical","",'V'),
	SOUND(22, "sound","",'V'),
	SIGNAL_FORMAT(23, "signal_format","",'V'),
	COMPRESSION(24, "compression","",'V'),
	AUDIO_DATA_ENCODING(25,"audio_data_enconding","",'V'),
	BITS_PER_SAMPLE(26,"bits_per_sample","",'V'),
	TAGGED_ID(27,"tagged_id","",'V'),
	DURATION(28,"duration","",'V'),
	NUMBER_OF_CHANNELS(29,"number_of_channels","",'V'),
	SAMPLIG_FREQUENCY(30,"sampling_frecuency","",'V'),
	YOUTUBE_ID(31,"youtube_id","",'V')
	; 



	//in older versions> *media_attribute.media_attribute_id*
	private int mediaAtributeId;
	//to be resolve in a properties i10n file
	private String namekey;
	//to be resolve in a properties i10n file
	private String descriptionkey;
	// the possible values are: N (numeric), D (Date) and V (varchar) 
	private char mediaAttributeValueType;

	
	/**
	 * @param mediaAtributeId
	 * @param namekey
	 * @param descriptionkey
	 * @param mediaAttributeValueType
	 */
	private MediaAttributeEntity(int mediaAtributeId, String namekey,
			String descriptionkey, char mediaAttributeValueType) {
		this.mediaAtributeId = mediaAtributeId;
		this.namekey = namekey;
		this.descriptionkey = descriptionkey;
		this.mediaAttributeValueType = mediaAttributeValueType;
	}

	/**
	 * 
	 * @param mediaAtributeId
	 * @return
	 */
	public static MediaAttributeEntity getById(int mediaAtributeId){
		MediaAttributeEntity[] all = MediaAttributeEntity.values();
		//the ENUM starts in 0 and the original table in 1 
		return all[mediaAtributeId - 1];
	}

	/**
	 * @return the mediaAtributeId
	 */
	public int getMediaAtributeId() {
		return mediaAtributeId;
	}


	/**
	 * @param mediaAtributeId the mediaAtributeId to set
	 */
	public void setMediaAtributeId(int mediaAtributeId) {
		this.mediaAtributeId = mediaAtributeId;
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
	 * @return the mediaAttributeValueType
	 */
	public char getMediaAttributeValueType() {
		return mediaAttributeValueType;
	}


	/**
	 * @param mediaAttributeValueType the mediaAttributeValueType to set
	 */
	public void setMediaAttributeValueType(char mediaAttributeValueType) {
		this.mediaAttributeValueType = mediaAttributeValueType;
	}

	
	
}
