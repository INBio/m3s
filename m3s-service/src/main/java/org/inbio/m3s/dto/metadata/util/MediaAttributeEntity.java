/**
 * 
 */
package org.inbio.m3s.dto.metadata.util;

/**
 * @author jgutierrez
 *
 */
public enum MediaAttributeEntity {

	ISO(1, "media.attribute.iso","",'N'), 
	CAMARA_MAKER(2, "media.attribute.make","",'V'), 
	CAMARA_MODEL(3, "media.attribute.model","",'V'),
	METERING_MODE(4, "media.attribute.metering.mode","",'V'),
	EXPOSURE_MODE(5, "media.attribute.exposure.program","",'V'),
	F_NUMBER(6, "media.attribute.fnumber","",'V'),
	FOCAL_LENGTH(7, "media.attribute.focal.length","",'V'),
	EXPOSURE_BIAS(8,"media.attribute.exposure.bias.value","",'V'),
	WHITE_BALANCE(9, "media.attribute.white.balance","",'V'),
	SATURATION(10, "media.attribute.saturation","",'V'),
	FLASH(11,"media.attribute.flash","",'V'),
	PIXELS_HEIGHT(12,"media.attribute.exif.image.length","",'N'),
	PIXELS_WIDTH(13,"media.attribute.exif.image.width","",'N'),
	EXPOSURE_TIME(14,"media.attribute.exposure.time","",'V'),
	PRODUCTION_DATE(15, "media.attribute.date.time.original","",'D'),
	FILE_EXTENSION(16, "media.attribute.file.extension","",'V'),
	FILE_TYPE(17, "media.attribute.file.type","",'V'),
	FORMAT_NAME(18, "media.attribute.format.name","",'V'),
	ASPECT_RATIO(19, "media.attribute.aspect.radio","",'V'),
	PIXELS_HORIZONTAL(20, "media.attribute.pixels.horizontal","",'V'),
	PIXELS_VERTICAL(21, "media.attribute.pixels.vertical","",'V'),
	SOUND(22, "media.attribute.sound","",'V'),
	SIGNAL_FORMAT(23, "media.attribute.signal.format","",'V'),
	COMPRESSION(24, "media.attribute.compression","",'V'),
	AUDIO_DATA_ENCODING(25,"media.attribute.audio.data.enconding","",'V'),
	BITS_PER_SAMPLE(26,"media.attribute.bits.per.sample","",'V'),
	TAGGED_ID(27,"media.attribute.tagged.id","",'V'),
	DURATION(28,"media.attribute.duration","",'V'),
	NUMBER_OF_CHANNELS(29,"media.attribute.number.of.channels","",'V'),
	SAMPLIG_FREQUENCY(30,"media.attribute.sampling.frecuency","",'V'),
	PREVIOUS_CATEGORY(31,"media.attribute.previous.category","",'V'), //categoria anterior
	FILM_BRAND(32,"media.attribute.film.brand","",'V'),
	SCANNING_DATE(33,"media.attribute.scanning.date","",'D'),
	IMAGE_DATE(34,"media.attribute.image.date","",'D'),
	YOUTUBE_ID(35,"media.attribute.youtube.id","",'V'),
	HIGH_RESOLUTION_VOLUME(36, "media.attribute.high.resolution.volume","", 'V'),
	HIGH_RESOLUTION_FILE_NAME(37, "media.attribute.high.resolution.file.name","", 'V'),
	GPS_LONGITUDE(38, "media.attribute.gps.longitude","",'V'),
	GPS_LATITUDE(39, "media.attribute.gps.latitude","",'V')
	; 

	/* A que estandard pertenecen:
	 *  del 01 al 15 -> EXIF
	 *  del 16 al 18 -> file type
	 *  del 19 al 30 -> MET (video) 
	 *  del 31 al 34 -> migración
	 *  del 35 al 35 -> video youtube
	 *  del 36 al 37 -> migración (más de migración)
	 *  del 38 al 39 -> gps information
	 */


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
