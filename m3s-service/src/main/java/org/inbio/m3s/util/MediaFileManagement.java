/**
 * 
 */
package org.inbio.m3s.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;


/**
 * @author jgutierrez
 * 
 */
public class MediaFileManagement {
	
	private static Logger logger = Logger.getLogger(MediaFileManagement.class);
	
//FIXME: esto no deberia estar aca
	public final static Integer DSC_MEDIA_TYPE_ID = new Integer(1);
//FIXME: esto no deberia estar aca	
	public final static Integer MOV_VIDEO_MEDIA_TYPE_ID = new Integer(4);
	
	private final static String M3S_BASE_DIR = "/home/jgutierrez/SoftwareTools/m3sINBio/";
	public static String IMAGES_ORIGINAL_REAL_BASE_ADDRESS = M3S_BASE_DIR + "MEDIA/ORIGINAL";
	public static String FILE_SEPARATOR = "/";
	public static String MEDIA_REAL_BASE_ADDRESS = M3S_BASE_DIR + "MEDIA/";
	public static String THUMB_IMAGES = "THUMB";
	public static String BIG_IMAGES = "BIG";
	
	// Media Size
	public final static int ORIGINAL = 0;
	public  final static int THUMB = 1;
	public static int BIG = 2;

	/**
	 * Takes the temporal media and moves it to the ORIGINAL MEDIA folder.,
	 * removes the tempFileName and Creates the THUMB and BIG media. Everything on
	 * the day's folder
	 * 
	 * 
	 * @param tempFileName
	 *          temporal name of the image
	 * @param DBFileName
	 *          the name of the file with its extension
	 * @param mediaId
	 *          Data Base media Identifier
	 * @throws IllegalArgumentExcption
	 */
	public static void organizeAndCleanFiles(String tempFileName, Integer mediaId, Integer mediaTypeId) throws IllegalArgumentException {
		logger.debug("organizeAndCleanFiles");
		logger.debug("params: [tempFileName=" + tempFileName + "], " + "[mediaId="
				+ mediaId + "] " + "[mediaTypeId=" + mediaTypeId + "].");

		// String DBFileName = mediaId.toString() + getFileExtension(mediaTypeId);

		// todays images and date params to keep media organized
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String todaysDate = (dateFormat.format(new Date())).toString();

		// address of the high quality image
		String orignalMediaFilePath = IMAGES_ORIGINAL_REAL_BASE_ADDRESS
				+ FILE_SEPARATOR + todaysDate + FILE_SEPARATOR
				+ mediaId.toString()
				+ getFileExtension(mediaTypeId, MediaFileManagement.ORIGINAL);

		String thumbMediaFilePath;
		String bigMediaFilePath;
		File dir = null;
		File file = null;
		File newFile = null;
		logger.debug("moviendo archivos con apache commons-io");
		try {
			file = new File(tempFileName);
			newFile = new File(orignalMediaFilePath);
			logger.debug("archivo fuente : '" + file.getCanonicalPath() + "'.");
			// file.
			logger.debug("archivo destino: '" + newFile.getCanonicalPath() + "'.");
			FileUtils.moveFile(file, newFile);

			// the path of this media depends on the visibility -> ya no!
			thumbMediaFilePath = MEDIA_REAL_BASE_ADDRESS
					+ FILE_SEPARATOR + THUMB_IMAGES;
			bigMediaFilePath = MEDIA_REAL_BASE_ADDRESS
					+ FILE_SEPARATOR + BIG_IMAGES;

			thumbMediaFilePath = thumbMediaFilePath + FILE_SEPARATOR
					+ todaysDate;
			bigMediaFilePath = bigMediaFilePath + FILE_SEPARATOR
					+ todaysDate;
			// creates the folder with the date to keep media organized(in case its
			// not created before)
			dir = new File(thumbMediaFilePath);
			FileUtils.forceMkdir(dir);
			dir = new File(bigMediaFilePath);
			FileUtils.forceMkdir(dir);
		} catch (Exception e) {
			logger.error("algo salio MAL moviendo los archivos.");
			logger.error(e.getMessage());
			throw new IllegalArgumentException(
					"Error Grave: Problemas moviendo los archivos.");
		}
		logger.debug("Fin prueba de mover archivo con apache commons-io");

		thumbMediaFilePath = thumbMediaFilePath + FILE_SEPARATOR
				+ mediaId.toString()
				+ getFileExtension(mediaTypeId, MediaFileManagement.THUMB);
		bigMediaFilePath = bigMediaFilePath + FILE_SEPARATOR
				+ mediaId.toString()
				+ getFileExtension(mediaTypeId, MediaFileManagement.BIG);
		// create the thumb and big media files
		createLowResFiles(mediaTypeId, orignalMediaFilePath, thumbMediaFilePath,
				bigMediaFilePath);

	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isFileReadable(String fileName){
		logger.debug("isFileReadable");
		logger.debug("params: [fileName=" + fileName + "].");
		
		try{
			File f =new File(fileName);
			return f.canRead();
		
		} catch(Exception e){
			logger.error("file '" + fileName + "' is not accesible.");
			return false;
		}
		
	}

	/**
	 * Creates the low resoultion files to be shown on the web.
	 * 
	 * @param mediaTypeId
	 * @param originalMediaFilePath
	 *          with the full path and the file name. ie: /media/orginal/7.mov
	 * @param thumbMediaFilePath
	 *          with the full path and the file name. ie: /media/thumb/7.jpg
	 * @param bigMediaFilePath
	 *          with the full path and the file name. ie: /media/thumb/7.flv
	 * @throws IllegalArgumentException
	 */
	private static void createLowResFiles(Integer mediaTypeId,
			String originalMediaFilePath, String thumbMediaFilePath,
			String bigMediaFilePath) throws IllegalArgumentException {

		if (mediaTypeId.equals(DSC_MEDIA_TYPE_ID)) {
			ImageMagickAPI.createThumb(originalMediaFilePath, thumbMediaFilePath);
			ImageMagickAPI.writeStandardSize(originalMediaFilePath, bigMediaFilePath);

		} else if (mediaTypeId.equals(MOV_VIDEO_MEDIA_TYPE_ID)) {
			VideoAPI.createThumb(originalMediaFilePath, thumbMediaFilePath);
			VideoAPI.createFLV(originalMediaFilePath, bigMediaFilePath);

		} else {
			logger
					.error("No se puede reconocer el mediaTypeId recibido como parametro");
			throw new IllegalArgumentException(
					"No se puede reconocer el mediaTypeId recibido como parametro");
		}

	}
	
	/**
	 * Este metodo esta todo alambrado, pero en teoria es para que dado un
	 * mediaTypeId, retorne la extensión del archivo que debe guardarse en la
	 * estructura de datos de la BD.
	 * 
	 * @param mediaTypeId
	 * @param mediaSize
	 *          estan al final de este archivo como constantes.
	 * @return la extension apropiada para el multimedio
	 * @throws IllegalArgumentException
	 */
	private static String getFileExtension(Integer mediaTypeId, int mediaSize)
			throws IllegalArgumentException {

		if (mediaTypeId.equals(DSC_MEDIA_TYPE_ID)) {
			return ".jpg";

		} else if (mediaTypeId.equals(MOV_VIDEO_MEDIA_TYPE_ID)) {

			if (mediaSize == MediaFileManagement.ORIGINAL)
				return ".mov";
			else if (mediaSize == MediaFileManagement.THUMB)
				return ".jpg";
			else
				// if(mediaSize == ImportFromFile.BIG)
				return ".flv";
		} else {
			logger
					.error("No se puede reconocer el mediaTypeId recibido como parametro");
			throw new IllegalArgumentException(
					"No se puede reconocer el mediaTypeId recibido como parametro");
		}
	}

}
