/**
 * 
 */
package org.inbio.m3s.dto.metadata;

/**
 * @author jgutierrez
 *
 */
public enum EXIFStandardAttributeEntity  {
	
	ISO(0, "ISOSpeedRatings"), 
	CAMARA_MAKER(1, "Make"), 
	CAMARA_MODEL(2, "Model"),
	METERING_MODE(3, "MeteringMode"),
	EXPOSURE_MODE(4, "ExposureProgram"),
	F_NUMBER(5, "FNumber"),
	FOCAL_LENGTH(6, "FocalLength"),
	EXPOSURE_BIAS(7,"ExposureBiasValue"),
	WHITE_BALANCE(8, "WhiteBalance"),
	SATURATION(9, "Saturation"),
	FLASH(10,"Flash"),
	//PIXELS_HEIGHT(11,"PixelYDimension"), Se probara con otro atributo
	PIXELS_HEIGHT(11,"ExifImageLength"), //
	//PIXELS_WIDTH(12,"PixelXDimension"), //ExifImageWidth
	PIXELS_WIDTH(12,"ExifImageWidth"), //
	EXPOSURE_TIME(13,"ExposureTime"),
	PRODUCTION_DATE(14, "DateTimeOriginal"),
	RESOLUTION(15, "various"), //is the combination of... YResolution, XResolution, ResolutionUnit 
	LIGHT_SOURCE(16, "LightSource"); //

	//public static final int TOTAL_METADATA_ATTRIBUTES = 17;


	private int id;
	//name in the exif standard
	private String name;
	
	

	/**
	 * @param id
	 * @param name
	 */
	private EXIFStandardAttributeEntity(int id, String name) {
		this.id = id;
		this.name = name;
	}



	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static EXIFStandardAttributeEntity getById(int id){
		EXIFStandardAttributeEntity[] todos = EXIFStandardAttributeEntity.values();
		return todos[id];
	}
	
}
