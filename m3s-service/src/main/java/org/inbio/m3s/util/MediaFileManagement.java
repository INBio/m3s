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

	/* Inyected values*/
	private String thumbFolder = "THUMB";
	private String bigFolder = "BIG";
	private String originalFolder = "ORIGINAL";
	private String m3sMediaBaseDir = "/mnt/m3sImages/INBio/MEDIA"; 


	// Media Size
	public int ORIGINAL = 0;
	public int THUMB = 1;
	public int BIG = 2;
	//FIXME: esto no deberia estar aca
	public Integer DSC_MEDIA_TYPE_ID = new Integer(1);
	//FIXME: esto no deberia estar aca	
	public Integer MOV_VIDEO_MEDIA_TYPE_ID = new Integer(4);


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
	public void organizeAndCleanFiles(String tempFileName, Integer mediaId, Integer mediaTypeId) throws IllegalArgumentException {
		logger.debug("organizeAndCleanFiles");
		logger.debug("params: [tempFileName=" + tempFileName + "], " + "[mediaId="
				+ mediaId + "] " + "[mediaTypeId=" + mediaTypeId + "].");

		// todays images and date params to keep media organized
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String todaysDate = (dateFormat.format(new Date())).toString();

		// address of the high quality image
		String orignalMediaFilePath = m3sMediaBaseDir + File.separator + originalFolder
		+ File.separator + todaysDate + File.separator
		+ mediaId.toString()
		+ getFileExtension(mediaTypeId, ORIGINAL);

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
			thumbMediaFilePath = m3sMediaBaseDir
			+ File.separator + thumbFolder;
			bigMediaFilePath = m3sMediaBaseDir
			+ File.separator + bigFolder;

			thumbMediaFilePath = thumbMediaFilePath + File.separator
			+ todaysDate;
			bigMediaFilePath = bigMediaFilePath + File.separator
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
			throw new IllegalArgumentException("Error Grave: Problemas moviendo los archivos.");
		}
		logger.debug("Fin prueba de mover archivo con apache commons-io");

		thumbMediaFilePath = thumbMediaFilePath + File.separator
		+ mediaId.toString()
		+ getFileExtension(mediaTypeId, THUMB);

		bigMediaFilePath = bigMediaFilePath + File.separator
		+ mediaId.toString()
		+ getFileExtension(mediaTypeId, BIG);

		// create the thumb and big media files
		createLowResFiles(mediaTypeId, orignalMediaFilePath, thumbMediaFilePath,
				bigMediaFilePath);

	}

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean isFileReadable(String fileName){
		logger.debug("isFileReadable");
		logger.debug("params: [fileName=" + fileName + "].");

		try{
			File f =new File(fileName);
			return f.canRead();

		} catch(NullPointerException npe){
			logger.error("file '" + fileName + "' doesnt exist!.");
			return false;
		} catch(Exception e){
			logger.error("file '" + fileName + "' is not accesible [pero si existe].");
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
	private void createLowResFiles(Integer mediaTypeId, String originalMediaFilePath, 
			String thumbMediaFilePath, String bigMediaFilePath) throws IllegalArgumentException {

		try{
			if (mediaTypeId.equals(DSC_MEDIA_TYPE_ID)) {
				ImageMagickAPI.createThumb(originalMediaFilePath, thumbMediaFilePath);
				ImageMagickAPI.writeStandardSize(originalMediaFilePath, bigMediaFilePath);

			} else if (mediaTypeId.equals(MOV_VIDEO_MEDIA_TYPE_ID)) {
				VideoAPI.createThumb(originalMediaFilePath, thumbMediaFilePath);
				VideoAPI.createFLV(originalMediaFilePath, bigMediaFilePath);
			} else {
				logger.error("No se puede reconocer el mediaTypeId recibido como parametro");
				throw new IllegalArgumentException("No se puede reconocer el mediaTypeId recibido como parametro");
			}

		} catch(Exception e){
			logger.error("No se puede reconocer el mediaTypeId recibido como parametro");
			throw new IllegalArgumentException("No se puede reconocer el mediaTypeId recibido como parametro");
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
	private String getFileExtension(Integer mediaTypeId, int mediaSize) throws IllegalArgumentException {

		if (mediaTypeId.equals(DSC_MEDIA_TYPE_ID)) {
			return ".jpg";

		} else if (mediaTypeId.equals(MOV_VIDEO_MEDIA_TYPE_ID)) {

			if (mediaSize == ORIGINAL)
				return ".mov";
			else if (mediaSize == THUMB)
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

	/**
	 * @return the thumbFolder
	 */
	public String getThumbFolder() {
		return thumbFolder;
	}

	/**
	 * @param thumbFolder the thumbFolder to set
	 */
	public void setThumbFolder(String thumbFolder) {
		this.thumbFolder = thumbFolder;
	}

	/**
	 * @return the bigFolder
	 */
	public String getBigFolder() {
		return bigFolder;
	}

	/**
	 * @param bigFolder the bigFolder to set
	 */
	public void setBigFolder(String bigFolder) {
		this.bigFolder = bigFolder;
	}

	/**
	 * @return the originalFolder
	 */
	public String getOriginalFolder() {
		return originalFolder;
	}

	/**
	 * @param originalFolder the originalFolder to set
	 */
	public void setOriginalFolder(String originalFolder) {
		this.originalFolder = originalFolder;
	}

	/**
	 * @return the m3sMediaBaseDir
	 */
	public String getM3sMediaBaseDir() {
		return m3sMediaBaseDir;
	}

	/**
	 * @param m3sMediaBaseDir the m3sMediaBaseDir to set
	 */
	public void setM3sMediaBaseDir(String m3sMediaBaseDir) {
		this.m3sMediaBaseDir = m3sMediaBaseDir;
	}


}
