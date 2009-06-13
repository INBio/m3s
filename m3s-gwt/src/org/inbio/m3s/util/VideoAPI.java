/**
 * 
 */
package org.inbio.m3s.util;

import org.apache.log4j.Logger;


/**
 * @author jgutierrez
 * 
 */
public class VideoAPI {
	
	private static Logger logger = Logger.getLogger(VideoAPI.class);

	/**
	 * Generates a snapshot at 1st sec. of the video, the size of the image will be 170x128.
	 * 
	 * 
	 * The output image will be an jpg.
	 * 
	 * @param quicktimeFileName
	 * @param destinyFileName
	 */
	public static void createThumb(String quicktimeFileName,
			String destinyFileName) {
		logger.debug("on createThumb[originalFileName]: " + quicktimeFileName);

		
		String[] cmd = { "ffmpeg", "-i",quicktimeFileName, "-vcodec","mjpeg",
				"-ss", "2", "-vframes","1", "-f","rawvideo", "-s","170x128", destinyFileName };
		
		logger.debug(OSCommand.run(cmd));

	}

	/**
	 * Receives a quicktime movie (.mov) file and converts it to a flash video
	 * (.flv), the output video will be sized to 360x244.
	 * 
	 * Use ffmpeg software
	 * 
	 * TODO: explain better this method
	 * 
	 * @param quicktimeFileName
	 *          ie. "videoPrueba02.mov"
	 * @param destinyFileName
	 *          ie. "07.flv"
	 */
	public static void createFLV(String quicktimeFileName, String destinyFileName) {

		String[] cmd = { "ffmpeg", "-i", quicktimeFileName, "-acodec", "mp3",
				"-ar", "22050", "-ab", "32", "-f", "flv", "-s", "360x244",
				destinyFileName };
		logger.debug(OSCommand.run(cmd));
	}

}
