/**
 * 
 */
package org.inbio.m3s.dao.multimedia.impl;

import org.inbio.m3s.dao.multimedia.MetadataExtractorDAO;
import org.inbio.m3s.dto.metadata.EXIFStandardAttributeEntity;
import org.inbio.m3s.util.OSCommand;

/**
 * @author jgutierrez
 * 
 * This class is usefull only for ubuntu/debian releases
 * @deprecated
 * 
 */
public class EXIFMetadataExtractorDebianImpl implements MetadataExtractorDAO {

	private String commandResult = null;

	// just the way identity -verbose command separates the metadata
	private final String metadataDelimiter = "  ";

	private final String preIndexOfArg = "Exif:";

	private final String postIndexOfArg = ":";

	/**
	 * 
	 */
	public EXIFMetadataExtractorDebianImpl() {
		super();
	}

	/**
	 * Inits the commandResult string with the returned value of 
	 * $>identify	 * -verbose $FILE_NAME
	 * 
	 * @param fileAddress
	 */
	public void init(String fileAddress) {
		String[] cmd = { "identify", "-verbose", fileAddress };
		this.commandResult = OSCommand.run(cmd);
	}

	/**
	 * 
	 * @param attribute
	 * @return
	 * @throws IllegalArgumentException
	 */
	public String getStringAttributeValue(EXIFStandardAttributeEntity attribute)
			throws IllegalArgumentException, IllegalStateException {

		if (this.commandResult == null) {
			throw new IllegalStateException(
					"Debe invocar el metodo init.");
		}

		if (attribute.equals(EXIFStandardAttributeEntity.METERING_MODE))
			return getMeteringMode(attribute.getName());

		else if (attribute.equals(EXIFStandardAttributeEntity.EXPOSURE_MODE))
			return getExposureMode(attribute.getName());

		else if (attribute.equals(EXIFStandardAttributeEntity.F_NUMBER)) {
			String fNumber = parseCommandResult(attribute.getName());
			if (fNumber.equals(""))
				return "";
			fNumber = fixNumericValue(fNumber, 2);
			return "f/" + fNumber;
		}

		else if (attribute.equals(EXIFStandardAttributeEntity.FOCAL_LENGTH)) {
			// according to the EXIF2-2 Spec: "The actual focal length of
			// the
			// lens, in mm. Conversion is not made to the focal length of a
			// 35 mm film camera."
			String focalLength = parseCommandResult(attribute.getName());
			if (focalLength.equals(""))
				return "";
			focalLength = fixNumericValue(focalLength, 2);
			return focalLength + " mm";
		}

		else if (attribute.equals(EXIFStandardAttributeEntity.EXPOSURE_BIAS)) {
			// according to the EXIF2-2 Spec: "The exposure bias. The unit
			// is the APEX value. Ordinarily it is given in the range of
			// –99.99 to 99.99."
			String exposureBias = parseCommandResult(attribute.getName());
			if (exposureBias.equals(""))
				return "";
			exposureBias = fixNumericValue(exposureBias, 2);
			return exposureBias + " APEX";
		}

		else if (attribute.equals(EXIFStandardAttributeEntity.WHITE_BALANCE)) {
			return getWhiteBalance(attribute.getName());
		}

		else if (attribute.equals(EXIFStandardAttributeEntity.SATURATION)) {
			return getSaturation(attribute.getName());
		}

		else if (attribute.equals(EXIFStandardAttributeEntity.FLASH)) {
			return getFlash(attribute.getName());
		}

		else if (attribute.equals(EXIFStandardAttributeEntity.EXPOSURE_TIME)) {
			// according to the EXIF2-2 Spec: "Exposure time, given in seconds
			// (sec)."
			String exposureTime = parseCommandResult(attribute.getName());
			if (exposureTime.equals(""))
				return "";
			exposureTime = fixNumericValue(exposureTime, 2);
			return exposureTime + " sec";
		}

		else if (attribute.equals(EXIFStandardAttributeEntity.RESOLUTION)) {
			return getResolution();
		}

		else if (attribute.equals(EXIFStandardAttributeEntity.LIGHT_SOURCE)) {
			return getLightSource(attribute.getName());
		}

		else
			return parseCommandResult(attribute.getName());
	}

	/**
	 * debe tomar la hilera y buscar en ella el metadato esperado
	 * 
	 * @param attribute
	 * @return
	 */
	private String parseCommandResult(String attributeName) {
		String searchArg = this.preIndexOfArg + attributeName + this.postIndexOfArg;

		int start = this.commandResult.indexOf(searchArg);
		int end = commandResult.indexOf(this.metadataDelimiter, start);

		if (start != -1 && end != -1)
			return commandResult.substring(start + searchArg.length() + 1, end);
		else
			return "";
	}

	/**
	 * Gets the metering textual value for the given imageAddress
	 * 
	 * @param fileAddress
	 * @return
	 */
	private String getMeteringMode(String attributeName) {
		/*
		 * Posible textual values for the metering mode, using exif spec 0 = Unknown
		 * 1 = Average 2 = CenterWeightedAverage 3 = Spot 4 = MultiSpot 5 = Pattern
		 * 6 = Partial 255 = other
		 */
		String textValues[] = { "Unknown", "Average", "Center Weighted Average",
				"Spot", "MultiSpot", "Pattern", "Partial" };

		String numericalMeeteringMode = parseCommandResult(attributeName);
		try {
			return textValues[Integer.parseInt(numericalMeeteringMode)];

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
	private String getExposureMode(String attributeName) {
		/*
		 * Posible textual values for the metering mode, using exif spec 0 = Auto
		 * exposure 1 = Manual exposure 2 = Auto bracket, Other = reserved
		 */
		String textValues[] = { "Auto exposure", "Manual exposure", "Auto bracket" };

		String numericalExposureMode = parseCommandResult(attributeName);
		try {
			return textValues[Integer.parseInt(numericalExposureMode)];

		} catch (Exception e) {
			return "other";
		}
	}

	/**
	 * Gets the white balance textual value for the given imageAddress
	 * 
	 * @param attributeName
	 * @return
	 */
	private String getWhiteBalance(String attributeName) {
		/*
		 * Posible textual values for the metering mode, using exif spec 0 = Auto
		 * white balance 1 = Manual white balance Other = reserved
		 */

		String textValues[] = { "Auto white balance", "Manual white balance" };
		// String whiteBalanceEXIFCode = "A408";

		String numericalWhiteBalance = parseCommandResult(attributeName);
		try {
			return textValues[Integer.parseInt(numericalWhiteBalance)];

		} catch (Exception e) {
			return "other";
		}

	}

	/**
	 * indicates the direction of saturation processing applied by the camera when
	 * the image was shot.
	 * 
	 * 
	 * @param attributeName
	 * @return
	 */
	private String getSaturation(String attributeName) {
		/**
		 * Posible textual values for the metering mode, using exif spec:
		 * <ul>
		 * <li>0=Normal</li>
		 * <li>1=Low saturation</li>
		 * <li>2=High saturation</li>
		 * </ul>
		 */
		String textValues[] = { "Normal", "Low saturation", "High saturation" };

		String numericalSaturation = parseCommandResult(attributeName);
		try {
			return textValues[Integer.parseInt(numericalSaturation)];

		} catch (Exception e) {
			return "other";
		}
	}

	/**
	 * indicates the flash use mode applied by the camera when the image was shot.
	 * 
	 * 
	 * @param attributeName
	 * @return
	 */
	private String getFlash(String attributeName) {
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

		try {
			Integer numericalFlash = new Integer(parseCommandResult(attributeName));

			int index = 256;
			for (int i = 0; i < DecimalNumberResultCode.length; i++) {
				if (DecimalNumberResultCode[i] == numericalFlash.intValue())
					index = i;
			}
			return textValues[index];

		} catch (Exception e) {
			return "other";
		}
	}

	/**
	 * gets the The kind of light source.
	 * 
	 * @param attributeName
	 * @return
	 */
	private String getLightSource(String attributeName) {
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

		String numericalLigthSource = parseCommandResult(attributeName);
		try {
			return textValues[Integer.parseInt(numericalLigthSource)];

		} catch (Exception e) {
			return "other";
		}
	}

	/**
	 * This methods looks for the resolution of an image a returns a string with
	 * this format: XResolution ResolutionUnit X YResolution ResolutionUnit.
	 * Example: 180 DPI X 180 PDI
	 * 
	 * @return
	 */
	private String getResolution() {
		String XResolutionEXIFCode = "XResolution";
		String YResolutionEXIFCode = "YResolution";
		String resolutionEXIFCode = "ResolutionUnit";
		// In the spec the values for codes 0 and 1 are empty. for number 2 means
		// resolution unit is Inch, thats why DPI and por number 3 the resolution
		// unit es centimeter (dpcm)
		String resultionUnits[] = { "", "", "dpi", "dpcm" };

		String XResolutionValue = parseCommandResult(XResolutionEXIFCode);
		XResolutionValue = fixNumericValue(XResolutionValue, 0);

		String YResolutionValue = parseCommandResult(YResolutionEXIFCode);
		YResolutionValue = fixNumericValue(YResolutionValue, 0);

		String resolutionUnit = parseCommandResult(resolutionEXIFCode);
		try {
			resolutionUnit = resultionUnits[Integer.parseInt(resolutionUnit)];
		} catch (Exception e) {
			resolutionUnit = "other";
		}

		if (XResolutionValue.equals("") || YResolutionValue.equals(""))
			return "";

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
	 * @deprecated
	 */
	public Object getAttributeValue(int standardAttributeId, char resultType,
			String fileAddress) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAttributeValue(int standardAttributeId)
			throws IllegalArgumentException, IllegalStateException {
		return this.getStringAttributeValue(EXIFStandardAttributeEntity
				.getById(standardAttributeId));
	}

	/**
	 * @deprecated
	 */
	public String getStringAttributeValue(int standardAttributeId,
			String fileAddress) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
