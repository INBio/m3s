package org.inbio.m3s.service.util;

public interface MetadataExtractor {

	/**
	 * This constant values have to match the metadata_standard_id and name of the
	 * metadata_standard table of the m3s core database schema
	 */

	public static final Integer EXIF = new Integer(1);

	public static final Integer FILE_INFO = new Integer(2);

	/*****************************************************************************
	 * read metadata operations
	 * 
	 * constructor of child classes must have the media source file as parameter
	 * 
	 * @param source
	 *          file source where the attribute it's going to be read
	 ****************************************************************************/

	/**
	 * This method will work as the class constructor
	 * 
	 * @param fileAddress
	 *          of the file in the file System
	 * 
	 */
	public void init(String fileAddress);

	/**
	 * Gets the attibute as it is, in the original value type
	 * 
	 * @param standardAttributeId
	 *          the value as an integer on the EXIF metadata standard
	 * @param resultType
	 *          according to the Media Attribute Value Type, the posible values
	 *          are V:varchar-string, D:date and N:number-int
	 * @param fileAddress
	 *          of the file in the file System
	 * @deprecated
	 */
	//public Object getAttributeValue(int standardAttributeId, char resultType,
	//		String fileAddress) throws IllegalArgumentException;

	/**
	 * Gets the attribute value as a String useful for display methods, because
	 * all the values shown will be String
	 * 
	 * @param standardAttributeId
	 *          the value as an integer on the EXIF metadata standard
	 * @param fileAddress
	 *          of the file in the file System
	 * 
	 * @return
	 * @deprecated
	 */
	//public String getStringAttributeValue(int standardAttributeId,
	//		String fileAddress) throws IllegalArgumentException;

	/**
	 * Gets the attribute value as a String useful for display methods, because
	 * all the values shown will be String
	 * 
	 * 
	 * @param standardAttributeId
	 *          the value as an integer on the EXIF metadata standard
	 * @return
	 * @throws IllegalArgumentException
	 *           if the standardAttributeId is invalid for the metadata standard
	 * @throws IllegalStateException
	 *           if the method is called without initializing the implementing
	 *           class.
	 */
	public String getAttributeValue(int standardAttributeId)
			throws IllegalArgumentException, IllegalStateException;

}
