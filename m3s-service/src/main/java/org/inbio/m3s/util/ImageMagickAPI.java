/**
 * 
 */
package org.inbio.m3s.util;

import java.util.StringTokenizer;

import org.apache.log4j.Logger;

/**
 * @author jgutierrez
 * 
 */
public class ImageMagickAPI {

	private static Logger logger = Logger.getLogger(ImageMagickAPI.class);
//FIXME: no deberia estar aca	
	public static final int THUMBNAIL_MAX_SIZE = 170;
//FIXME: no deberia estar aca
	public static final int STANDARD_MAX_SIZE = 480;
	
	/**
	 * 
	 * @param originalFileName
	 * @param destinyFileName
	 */
	public static void createThumb(String originalFileName, String destinyFileName) throws Exception {
		logger.debug("on createThumb[originalFileName]: "+ originalFileName);

		int scaledWidth = 0;
		int scaledHeight = 0;
		int sourceWidth = getWidth(originalFileName);
		logger.debug("width: " + sourceWidth);
		int sourceHeight = getHeight(originalFileName);
		logger.debug("height: " + sourceHeight);

		// gets the adecuated sizes for the thumbnail keeping the ratio
		if (sourceWidth > sourceHeight) {
			scaledWidth = THUMBNAIL_MAX_SIZE;
			scaledHeight = getOtherSideMaxValue(THUMBNAIL_MAX_SIZE,
					sourceWidth, sourceHeight);
		} else { // (originalImage.getWidth() >= originalImage.getHeight()){
			scaledHeight = THUMBNAIL_MAX_SIZE;
			scaledWidth = getOtherSideMaxValue(THUMBNAIL_MAX_SIZE,
					sourceWidth, sourceHeight);
		}

		String[] cmd = { "convert", originalFileName, "-resize",
				scaledWidth + "X" + scaledHeight, destinyFileName };
		// String command = "convert " + originalFileName + " -resize "
		// + scaledWidth + "X" + scaledHeight + " " + destinyFileName;
		OSCommand.run(cmd);

	}

	/**
	 * 
	 * @param originalFileName
	 * @param destinyFileName
	 */
	public static void writeStandardSize(String originalFileName,
			String destinyFileName) {

		int scaledWidth = 0;
		int scaledHeight = 0;
		int sourceWidth = getWidth(originalFileName);
		int sourceHeight = getHeight(originalFileName);

		// gets the adecuated sizes for the thumbnail keeping the ratio
		if (sourceWidth > sourceHeight) {
			scaledWidth = STANDARD_MAX_SIZE;
			scaledHeight = getOtherSideMaxValue(STANDARD_MAX_SIZE,
					sourceWidth, sourceHeight);
		} else { // (originalImage.getWidth() >= originalImage.getHeight()){
			scaledHeight = STANDARD_MAX_SIZE;
			scaledWidth = getOtherSideMaxValue(STANDARD_MAX_SIZE,
					sourceWidth, sourceHeight);
		}

		String[] cmd = { "convert", originalFileName, "-resize",
				scaledWidth + "X" + scaledHeight, destinyFileName };
		// String command = "convert " + originalFileName + " -resize "
		// + scaledWidth + "X" + scaledHeight + " " + destinyFileName;
		OSCommand.run(cmd);
	}

	/**
	 * Calculates the best size for a resize image based on the dimensions of
	 * the original image. In case the sourceWidth < sourceHeight, the
	 * maxSideValue is the height of the resize image and the return value is
	 * the width. If the sourceWidth > sourceHeight, the maxSideValue is the
	 * width of the resize image and the return value is the height.
	 * 
	 * @param maxSideValue
	 *            value of the wished maximum side for the generated image
	 * @param sourceWidth
	 *            value of the source image width
	 * @param sourceHeight
	 *            value of the source image height
	 * @return the maximun value for the other side of the image.
	 * 
	 */
	private static int getOtherSideMaxValue(int maxSideValue, int sourceWidth,
			int sourceHeight) {
		logger.debug("actualWidth='" + sourceWidth + "' actualHeight='"
				+ sourceHeight + "'");
		double actualRatio = 0;
		int otherSideMaxValue;

		// Determina el lado mas grande para asignarle 480 pixels
		if (sourceWidth < sourceHeight) {
			actualRatio = (double) sourceWidth / (double) sourceHeight;
			otherSideMaxValue = (int) (actualRatio * maxSideValue);
		} else {
			actualRatio = (double) sourceHeight / (double) sourceWidth;
			otherSideMaxValue = (int) (actualRatio * maxSideValue);
		}
		logger.debug("maxSide='" + maxSideValue + "' maxOtherSide='"
				+ otherSideMaxValue + "'");
		return otherSideMaxValue;
	}

	/**
	 * Gets the width of an image. [Tested with: .TIF, .JPG]. The Tiff files
	 * sucks because could more than one image, so when you try to get the width
	 * returns more than one result, so to know the width of the high def image
	 * you have to check all the given returned size's and select the biggest
	 * 
	 * @param imageAddress
	 * @return the high resolution image width
	 */
	public static int getWidth(String imageAddress) {

		String[] cmd = { "identify", "-format", "%w;", imageAddress };
		//String command = "identify -format %w; " + imageAddress;
		String commandResult = OSCommand.run(cmd);
		int width = 0;
		int tempWidth;

		// gets the width of the high def image
		StringTokenizer st = new StringTokenizer(commandResult, ";");
		while (st.hasMoreTokens()) {
			try {
				tempWidth = Integer.parseInt(st.nextToken());
				if (tempWidth > width)
					width = tempWidth;
			} catch (NumberFormatException nfe) {
				// ignore number format exception
				logger.error("number format exception");
			} catch (Exception e) {
				// ignore any other exception exception
				logger.error("exception");
			}
		}

		return width;
	}

	/**
	 * 
	 * Gets the height of an image. [Tested with: .TIF, .JPG]. The Tiff files
	 * sucks because could more than one image, so when you try to get the
	 * height returns more than one result, so to know the width of the high def
	 * image you have to check all the given returned size's and select the
	 * biggest
	 * 
	 * @param imageAddress
	 * @return
	 */
	public static int getHeight(String imageAddress) {
		//String command = "identify -format %h; " + imageAddress;
		String[] cmd = { "identify", "-format", "%h;", imageAddress };
		String commandResult = OSCommand.run(cmd);
		int height = 0;
		int tempHeight;

		// gets the height of the high def image
		StringTokenizer st = new StringTokenizer(commandResult, ";");
		while (st.hasMoreTokens()) {
			try {
				tempHeight = Integer.parseInt(st.nextToken());
				if (tempHeight > height)
					height = tempHeight;
			} catch (NumberFormatException nfe) {
				// ignore number format exception
			} catch (Exception e) {
				// ignore any other exception exception
			}
		}

		return height;
	}

	/**
	 * TODO: fix the final period(".") of some metadata
	 * 
	 * @param hexadecimalValue
	 * @return
	 * 
	 * Works nice on Fedora flavors
	 */
	public static String identifyEXIF(String hexadecimalValue,
			String imageAddress) throws IllegalArgumentException {

		String[] cmd = { "identify", "-format",
				"%[EXIF:#" + hexadecimalValue + "]", imageAddress };
		String commandResult = OSCommand.run(cmd);
		logger.debug("after executing the command: '"+commandResult+"'");

		// FIXME: check why some metadata finish with a period "."?
		while (commandResult.charAt(commandResult.length() - 1) == '.') {
			commandResult = commandResult.substring(0,
					commandResult.length() - 1);
		}

		return commandResult;

	}
	
	/**
	 * This method was created to solved the bug of the identify command on some 
	 * newer imagemagick versions (+6.3.7) that donde work properly.
	 * 
	 * This method deprecated the identifyEXIF(String, String).
	 * 
	 *  It's wired to GNU/Linux
	 * 
	 * @param exifAttribute
	 * @param imageAddress
	 * @return the EXIF value
	 */
	public static String identifyEXIFMetadata(String exifAttribute, String imageAddress) throws IllegalArgumentException {
		String[] cmd = { "identify", "-verbose", imageAddress};
		
		// String command = "identify -format %[EXIF:#" + hexadecimalValue + "]
		// "
		// + imageAddress;
		String commandResult = OSCommand.run(cmd);

		// FIXME: check why some metadata finish with a period "."?
		while (commandResult.charAt(commandResult.length() - 1) == '.') {
			commandResult = commandResult.substring(0,
					commandResult.length() - 1);
		}

		return commandResult;
	}
}
