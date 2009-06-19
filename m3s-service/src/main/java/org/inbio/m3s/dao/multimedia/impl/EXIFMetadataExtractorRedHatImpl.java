package org.inbio.m3s.dao.multimedia.impl;
/**
 * 
 */

import org.apache.log4j.Logger;
import org.inbio.m3s.dao.multimedia.MetadataExtractorDAO;
import org.inbio.m3s.util.ImageMagickAPI;


/**
 * @author jgutierrez
 * 
 * Works nice on Fedora...
 * @deprecated
 * 
 */
public class EXIFMetadataExtractorRedHatImpl implements MetadataExtractorDAO {
	
	private static Logger logger = Logger.getLogger(EXIFMetadataExtractorRedHatImpl.class);
	

	public String fileAddress = null;

	// standard attributes:
	public static final int ISO = 0;

	public static final int CAMARA_MAKER = 1;

	public static final int CAMARA_MODEL = 2;

	public static final int METERING_MODE = 3;

	public static final int EXPOSURE_MODE = 4;

	public static final int F_NUMBER = 5;

	public static final int FOCAL_LENGTH = 6;

	public static final int EXPOSURE_BIAS = 7;

	public static final int WHITE_BALANCE = 8;

	public static final int SATURATION = 9;

	public static final int FLASH = 10;

	public static final int PIXELS_HEIGHT = 11;

	public static final int PIXELS_WIDTH = 12;

	public static final int EXPOSURE_TIME = 13;

	public static final int PRODUCTION_DATE = 14;

	public static final int RESOLUTION = 15;

	public static final int LIGHT_SOURCE = 16;

	public static final int TOTAL_METADATA_ATTRIBUTES = 17;

	/**
	 * This arrays has the real hexadecimal exif attribute id value for each
	 * attribute implemented in the m3s. Obiously the standard attibute with
	 * value=0 is the corresponding of the EXIF_ATTRIBUTE_ID[0] The Hex values
	 * should be made of 4 digits.
	 */
	private static final String[] EXIF_ATTRIBUTE_ID = { "8827", "010F", "0110",
			"NIUS", "NIUS", "829D", "920A", "9204", "NIUS", "NIUS", "NIUS", "A002",
			"A003", "829A", "9003", "NIUS", "NIUS" };

	/**
	 * This array holds the name of the atribute in the EXIF standard. The name of
	 * the attribute is gotten using the number of the attribute as the index in
	 * the array. For example EXIF_ATTRIBUTE_NAME[LIGHT_SOURCE] = "LightSource"
	 */
	private static final String[] EXIF_ATTRIBUTE_NAME = { "ISO speed rating",
			"Make", "Model", "Metering mode", "Exposure mode", "F number",
			"Lens focal length", "Exposure bias", "White balance", "Saturation",
			"Flash", "Valid image width", "Valid image height", "Exposure time",
			"Date and time original image was generated", "Resolution",
			"Light source" };

	/**
	 * 
	 * 
	 */
	public EXIFMetadataExtractorRedHatImpl() {
		super();
	}

	/**
	 * @param fileAddress
	 */
	public void init(String fileAddress) {
		this.fileAddress = fileAddress;
	}

	/**
	 * do not use this method for this standard. Is the samething as using
	 * getStringAttributeValue
	 * 
	 * @deprecated
	 */
	//public Object getAttributeValue(int standardAttributeId, char resultType,
	//		String fileAddress) throws IllegalArgumentException {
	//	return getStringAttributeValue(standardAttributeId, fileAddress);
	//}

	/**
	 * Gets the value of the attribute as a String
	 * 
	 * @param fileAddress
	 *          of the file in the file System
	 * 
	 * @deprecated
	 * 
	 */
	//public String getStringAttributeValue(int standardAttributeId,
	//		String fileAddress) throws IllegalArgumentException {
	//	return this.getAttributeValue(standardAttributeId);
	//}

	/**
	 * @param standardAttributeId
	 * 
	 */
	public String getAttributeValue(int standardAttributeId)
			throws IllegalArgumentException, IllegalStateException {

		if (this.fileAddress == null) {
			throw new IllegalStateException("Debe invocar el metodo init.");
		}

		try {

			switch (standardAttributeId) {
			case METERING_MODE:
				return getMeteringMode(fileAddress);
			case EXPOSURE_MODE:
				return getExposureMode(fileAddress);
			case F_NUMBER:
				String fNumber = ImageMagickAPI.identifyEXIF(
						EXIF_ATTRIBUTE_ID[standardAttributeId], fileAddress);
				fNumber = fixNumericValue(fNumber, 2);
				return "f/" + fNumber;

			case FOCAL_LENGTH:
				// according to the EXIF2-2 Spec: "The actual focal length of
				// the
				// lens, in mm. Conversion is not made to the focal length of a
				// 35 mm film camera."
				String focalLength = ImageMagickAPI.identifyEXIF(
						EXIF_ATTRIBUTE_ID[standardAttributeId], fileAddress);
				focalLength = fixNumericValue(focalLength, 2);
				return focalLength + " mm";

			case EXPOSURE_BIAS:
				// according to the EXIF2-2 Spec: "The exposure bias. The unit
				// is the APEX value. Ordinarily it is given in the range of
				// –99.99 to 99.99."
				String exposureBias = ImageMagickAPI.identifyEXIF(
						EXIF_ATTRIBUTE_ID[standardAttributeId], fileAddress);
				exposureBias = fixNumericValue(exposureBias, 2);
				return exposureBias + " APEX";

			case WHITE_BALANCE:
				return getWhiteBalance(fileAddress);

			case SATURATION:
				return getSaturation(fileAddress);

			case FLASH:
				return getFlash(fileAddress);

			case EXPOSURE_TIME:
				// according to the EXIF2-2 Spec: "Exposure time, given in seconds
				// (sec)."
				String exposureTime = ImageMagickAPI.identifyEXIF(
						EXIF_ATTRIBUTE_ID[standardAttributeId], fileAddress);
				exposureTime = fixNumericValue(exposureTime, 2);
				return exposureTime + " sec";

			case RESOLUTION:
				return getResolution(fileAddress);

			case LIGHT_SOURCE:
				return getLightSource(fileAddress);

			case ISO: 
				return this.fixString(ImageMagickAPI.identifyEXIF(
						EXIF_ATTRIBUTE_ID[standardAttributeId], fileAddress));
			case PIXELS_HEIGHT: 
				return this.fixString(ImageMagickAPI.identifyEXIF(
						EXIF_ATTRIBUTE_ID[standardAttributeId], fileAddress));
			case PIXELS_WIDTH: 
				return this.fixString(ImageMagickAPI.identifyEXIF(
						EXIF_ATTRIBUTE_ID[standardAttributeId], fileAddress));
			case PRODUCTION_DATE:
				return this.fixString(ImageMagickAPI.identifyEXIF(
						EXIF_ATTRIBUTE_ID[standardAttributeId], fileAddress));
				
			default:
				return ImageMagickAPI.identifyEXIF(
						EXIF_ATTRIBUTE_ID[standardAttributeId], fileAddress);
			 
			}

		} catch (Exception e) {
			//throw new IllegalArgumentException(
			//		"El atributo no existe en el estándar 'EXIF'");
			logger.debug("Se tuvo un error buscando el atributo EXIF: "+standardAttributeId);
			return "";
		}
	}

	/**
	 * Gets the metering textual value for the given imageAddress
	 * 
	 * @param fileAddress
	 * @return
	 */
	private String getMeteringMode(String fileAddress) {
		/*
		 * Posible textual values for the metering mode, using exif spec 0 = Unknown
		 * 1 = Average 2 = CenterWeightedAverage 3 = Spot 4 = MultiSpot 5 = Pattern
		 * 6 = Partial 255 = other
		 */
		String textValues[] = { "Unknown", "Average", "Center Weighted Average",
				"Spot", "MultiSpot", "Pattern", "Partial" };
		String meteringModeEXIFCode = "9207";

		try {
			Integer meteringModeValue = new Integer(ImageMagickAPI.identifyEXIF(
					meteringModeEXIFCode, fileAddress));
			return textValues[meteringModeValue.intValue()];

		} catch (Exception e) {
			return "other";
		}

	}

	/**
	 * indicates the flash use mode applied by the camera when the image was shot.
	 * 
	 * 
	 * @param fileAddress
	 * @return
	 */
	private String getFlash(String fileAddress) {
		/**
		 * Resulting Flash tag values:
		 * <ul>
		 * <li>0000.H = Flash did not fire.</li>
		 * <li>0001.H = Flash fired.</li>
		 * <li>0005.H = Strobe return light not detected.</li>
		 * <li>0007.H = Strobe return light detected.</li>
		 * <li>0009.H = Flash fired, compulsory flash mode</li>
		 * <li>000D.H = Flash fired, compulsory flash mode, return light not
		 * detected</li>
		 * <li>000F.H = Flash fired, compulsory flash mode, return light detected</li>
		 * <li>0010.H = Flash did not fire, compulsory flash mode</li>
		 * <li>0018.H = Flash did not fire, auto mode</li>
		 * <li>0019.H = Flash fired, auto mode</li>
		 * <li>001D.H = Flash fired, auto mode, return light not detected</li>
		 * <li>001F.H = Flash fired, auto mode, return light detected</li>
		 * <li>0020.H = No flash function</li>
		 * <li>0041.H = Flash fired, red-eye reduction mode</li>
		 * <li>0045.H = Flash fired, red-eye reduction mode, return light not
		 * detected</li>
		 * <li>0047.H = Flash fired, red-eye reduction mode, return light detected</li>
		 * <li>0049.H = Flash fired, compulsory flash mode, red-eye reduction mode</li>
		 * <li>004D.H = Flash fired, compulsory flash mode, red-eye reduction mode,
		 * return light not detected</li>
		 * <li>004F.H = Flash fired, compulsory flash mode, red-eye reduction mode,
		 * return light detected</li>
		 * <li>0059.H = Flash fired, auto mode, red-eye reduction mode</li>
		 * <li>005D.H = Flash fired, auto mode, return light not detected, red-eye
		 * reduction mode</li>
		 * <li>005F.H = Flash fired, auto mode, return light detected, red-eye
		 * reduction mode</li>
		 * <li>Other = reserved</li>
		 * </ul>
		 */
		// the index in the Decimal NumberResultCode array will be the same in the
		// textValues array
		int DecimalNumberResultCode[] = { 0, 1, 5, 7, 9, 13, 15, 16, 24, 25, 29,
				30, 32, 65, 69, 71, 73, 77, 78, 89, 93, 95 };
		String textValues[] = {
				"Flash did not fire.",
				"Flash fired",
				"Strobe return light not detected.",
				"Strobe return light detected.",
				"Flash fired, compulsory flash mode",
				"Flash fired, compulsory flash mode, return light not detected",
				"Flash fired, compulsory flash mode, return light detected",
				"Flash did not fire, compulsory flash mode",
				"Flash did not fire, auto mode",
				"Flash fired, auto mode",
				"Flash fired, auto mode, return light not detected",
				"Flash fired, auto mode, return light detected",
				"No flash function",
				"Flash fired, red-eye reduction mode",
				"Flash fired, red-eye reduction mode, return light not detected",
				"Flash fired, red-eye reduction mode, return light detected",
				"Flash fired, compulsory flash mode, red-eye reduction mode",
				"Flash fired, compulsory flash mode, red-eye reduction mode, return light not detected",
				"Flash fired, compulsory flash mode, red-eye reduction mode, return light detected",
				"Flash fired, auto mode, red-eye reduction mode",
				"Flash fired, auto mode, return light not detected, red-eye reduction mode",
				"Flash fired, auto mode, return light detected, red-eye reduction mode" };
		String flashEXIFCode = "9209";

		try {
			Integer flashValue = new Integer(ImageMagickAPI.identifyEXIF(
					flashEXIFCode, fileAddress));
			int index = 256;
			for (int i = 0; i < DecimalNumberResultCode.length; i++) {
				if (DecimalNumberResultCode[i] == flashValue.intValue())
					index = i;
			}
			return textValues[index];

		} catch (Exception e) {
			return "other";
		}

	}

	/**
	 * indicates the direction of saturation processing applied by the camera when
	 * the image was shot.
	 * 
	 * 
	 * @param fileAddress
	 * @return
	 */
	private String getSaturation(String fileAddress) {
		/**
		 * Posible textual values for the metering mode, using exif spec:
		 * <ul>
		 * <li>0=Normal</li>
		 * <li>1=Low saturation</li>
		 * <li>2=High saturation</li>
		 * </ul>
		 */
		String textValues[] = { "Normal", "Low saturation", "High saturation" };
		String saturationEXIFCode = "A409";

		try {
			Integer saturationValue = new Integer(ImageMagickAPI.identifyEXIF(
					saturationEXIFCode, fileAddress));
			return textValues[saturationValue.intValue()];

		} catch (Exception e) {
			return "other";
		}
	}

	/**
	 * gets the The kind of light source.
	 * 
	 * @param fileAddress
	 * @return
	 */
	private String getLightSource(String fileAddress) {
		/*
		 * Posible textual values for the metering mode, using exif spec 0 =
		 * unknown, 1 = Daylight, 2 = Fluorescent, 3=Tungsten (incandescent light),
		 * 4= Flash, 5-8=the spec says anythig, 9 =Fine weather, 10=Cloudy weather,
		 * 11=Shade, 12=Daylight fluorescent (D 5700 – 7100K), 13=Day white
		 * fluorescent (N 4600 – 5400K), 14=Cool white fluorescent (W 3900 – 4500K),
		 * 15=White fluorescent (WW 3200 – 3700K), 16=the spec says anythig,
		 * 17=Standard light A, 18=Standard light B, 19=Standard light C, 20=D55,
		 * 21=D65, 22=D75, 23=D50, 24=ISO studio tungsten, 255=other light source,
		 * otherr=reserved
		 */

		String textValues[] = { "unknown", "Daylight", "Fluorescent",
				"Tungsten (incandescent light)", "Flash", "", "", "", "",
				"Fine weather", "Cloudy weather", "Shade",
				"Daylight fluorescent (D 5700 – 7100K)",
				"Day white fluorescent (N 4600 – 5400K)",
				"Cool white fluorescent (W 3900 – 4500K)",
				"White fluorescent (WW 3200 – 3700K)", "", "Standard light A",
				"Standard light B", "Standard light C", "D55", "D65", "D75", "D50",
				"ISO studio tungsten" };
		String lightSourceEXIFCode = "9208";

		try {
			Integer lightSourceValue = new Integer(ImageMagickAPI.identifyEXIF(
					lightSourceEXIFCode, fileAddress));

			if (lightSourceValue.intValue() == 255)
				return "other light source";

			else
				return textValues[lightSourceValue.intValue()];

		} catch (Exception e) {
			return "other";
		}

	}

	/**
	 * Gets the white balance textual value for the given imageAddress
	 * 
	 * @param fileAddress
	 * @return
	 */
	private String getWhiteBalance(String fileAddress) {
		/*
		 * Posible textual values for the metering mode, using exif spec 0 = Auto
		 * white balance 1 = Manual white balance Other = reserved
		 */

		String textValues[] = { "Auto white balance", "Manual white balance" };
		// String whiteBalanceEXIFCode = "A408";
		String whiteBalanceEXIFCode = "A403";

		try {
			Integer whiteBalanceValue = new Integer(ImageMagickAPI.identifyEXIF(
					whiteBalanceEXIFCode, fileAddress));
			return textValues[whiteBalanceValue.intValue()];

		} catch (Exception e) {
			return "other";
		}

	}

	/**
	 * Gets the exposure mode textual value for the given imageAddress
	 * 
	 * @param fileAddress
	 * @return
	 */
	private String getExposureMode(String fileAddress) {
		/*
		 * Posible textual values for the metering mode, using exif spec 0 = Auto
		 * exposure 1 = Manual exposure 2 = Auto bracket, Other = reserved
		 */
		String textValues[] = { "Auto exposure", "Manual exposure", "Auto bracket" };
		String exposureModeEXIFCode = "A402";

		try {
			Integer meteringModeValue = new Integer(ImageMagickAPI.identifyEXIF(
					exposureModeEXIFCode, fileAddress));
			return textValues[meteringModeValue.intValue()];

		} catch (Exception e) {
			return "other";
		}
	}

	/**
	 * This methods looks for the resolution of an image a returns a string with
	 * this format: XResolution ResolutionUnit X YResolution ResolutionUnit.
	 * Example: 180 DPI X 180 PDI
	 * 
	 * @param FileName
	 * @return
	 */
	private String getResolution(String fileAddress) {
		String XResolutionEXIFCode = "011A";
		String YResolutionEXIFCode = "011B";
		String resolutionEXIFCode = "0128";
		// In the spec the values for codes 0 and 1 are empty. for number 2 means
		// resolution unit is Inch, thats why DPI and por number 3 the resolution
		// unit es centimeter (dpcm)
		String resultionUnits[] = { "", "", "dpi", "dpcm" };

		String XResolutionValue = ImageMagickAPI.identifyEXIF(XResolutionEXIFCode,
				fileAddress);
		XResolutionValue = fixNumericValue(XResolutionValue, 0);

		String YResolutionValue = ImageMagickAPI.identifyEXIF(YResolutionEXIFCode,
				fileAddress);

		YResolutionValue = fixNumericValue(YResolutionValue, 0);

		String resolutionUnit = ImageMagickAPI.identifyEXIF(resolutionEXIFCode,
				fileAddress);
		try {
			resolutionUnit = resultionUnits[Integer.parseInt(resolutionUnit)];
		} catch (Exception e) {
			resolutionUnit = "other";
		}

		return XResolutionValue + " " + resolutionUnit + " X " + YResolutionValue
				+ " " + resolutionUnit;
	}

	/**
	 * Fix values for numbers given in this way "300/1" when the best way to have
	 * the value is like "300"
	 * 
	 * @param numericValue
	 *          the value to be fixed for a better view
	 * @param decimals
	 *          the number of digits after the period
	 * @return the value fixed or the same numerica value if some exception is
	 *         fired
	 */
	private String fixNumericValue(String numericValue, int decimals) {
		try {
			String params[] = numericValue.split("/");
			Float param1 = new Float(params[0]);
			Float param2 = new Float(params[1]);
			Float result = param1 / param2;

			String literalResult = result.toString();
			int periodIndex = literalResult.indexOf(".");
			if (decimals != 0) {
				periodIndex = periodIndex + 1;
			}

			try {
				return literalResult.substring(0, periodIndex + decimals);
			} catch (Exception e) {
				return literalResult;
			}

		} catch (Exception e) {
			return numericValue;
		}
	}

	/**
	 * This method fix the returned value for non varchar types. This applies to
	 * ISO, PIXELS_HEIGHT, PIXELS_WIDTH and DATE. If the actual value is equal to
	 * "unknown", that will be changed to "".
	 * 
	 * @param actualValue
	 * @return the new actual value.
	 */
	private String fixString(String actualValue) {
		// unknown		
		if(actualValue.equals("unknown"))
			return "";
		else
			return actualValue;

	}

	/**
	 * 
	 * @param attributeNumber
	 */
	@SuppressWarnings("unused")
	private String getEXIFAttributeName(int attributeNumber) {
		try {
			return EXIF_ATTRIBUTE_NAME[attributeNumber];
		} catch (Exception e) {
			return "unknown";
		}

	}

}
