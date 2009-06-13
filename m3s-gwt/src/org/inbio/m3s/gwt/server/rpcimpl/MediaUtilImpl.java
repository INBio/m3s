package org.inbio.m3s.gwt.server.rpcimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.gwt.client.exception.RPCIllegalArgumentException;
import org.inbio.m3s.gwt.client.rpcinterface.MediaUtilRPC;
import org.inbio.m3s.util.ImageMagickAPI;
import org.inbio.m3s.util.OSCommand;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MediaUtilImpl extends RemoteServiceServlet implements MediaUtilRPC {

	/**
	 * 
	 */
	private static final long serialVersionUID = -32676364925909217L;

	private static Logger logger = Logger.getLogger(MediaUtilImpl.class);
	
	//FIXME:esto no deberia estar aca.
	public static final String TEMPORAL_THUMB_PREXIF = "thumb-";

	/**
	 * Writes the thumpnail in the uploadedImages Folder with the prefix
	 * "thumb-" (defined as a constant on the file
	 * Properties.TEMPORAL_THUMB_PREXIF). Example: The fileId is "aguacate.png"
	 * the resulting temporal thumbnail will have the name "thumb-aguacate.png"
	 * 
	 * @param fileId
	 *            this is the name of the image, name gived by the server as an
	 *            unique id for temporal use only
	 * @return the webAddress where the image is accesible for the web
	 *         application
	 * 
	 */
	public String createTempThumbnail(String fileId)
			throws RPCIllegalArgumentException {

		String outputSufix = TEMPORAL_THUMB_PREXIF + fileId;

		String sourceFileName = Properties.REAL_TEMP_FILES_DIR + fileId;

		String destinyFileName = Properties.REAL_TEMP_FILES_DIR + outputSufix;

		ImageMagickAPI.createThumb(sourceFileName, destinyFileName);

		return Properties.WEB_TEMP_MEDIA_DIR + outputSufix;

	}

	/**
	 * Takes the temporal image and moves it to the ORIGINAL IMAGES folder.,
	 * removes the thumbnail and the temporal full size image. Creates the THUMB
	 * and BIG images, and ordered in the day's folder
	 * 
	 * 
	 * @param tempFileId
	 *            unique temporal identifier of the image
	 * @param DBFileName
	 *            the name of the file with its extension
	 * @param mediaId
	 *            Data Base media Identifier
	 */
	public void organizeAndCleanFiles(String tempFileId, String DBFileName,
			Integer mediaId) throws RPCIllegalArgumentException {
		logger.debug("organizeAndCleanFiles");
		
		//MediaDAO mediaDAO = MediaDAOFactory.createMediaDAOImpl();
		//MediaLite mediaLite = mediaDAO.getMediaLite(mediaId);
		
		// todays images and date params to keep media organized
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = new java.util.Date();
		String todaysDate = (dateFormat.format(date)).toString();

		// address of the high quality image
		String tempImagePath = Properties.REAL_TEMP_FILES_DIR + tempFileId;
		// new path of the original image, first creates the folder (if it
		// doesnt exists), then sets the complete originalImageTemp variable
		String orignalImagePath = Properties.IMAGES_ORIGINAL_REAL_BASE_ADDRESS
				+ Properties.FILE_SEPARATOR + todaysDate;
		// creates the dayFolder (in case its not created before)
		String[] cmd = { "mkdir", orignalImagePath };
		// command = "mkdir " + orignalImagePath;
		OSCommand.run(cmd);
		orignalImagePath = orignalImagePath + Properties.FILE_SEPARATOR
				+ DBFileName;
		String[] cmd2 = { "mv", tempImagePath, orignalImagePath };
		// command = "mv " + tempImagePath + " " + orignalImagePath;
		OSCommand.run(cmd2);

		// address of the thumbnail of the temporal image
		String tempThumbPath = Properties.REAL_TEMP_FILES_DIR
				+ TEMPORAL_THUMB_PREXIF + tempFileId;

		// the path of this images depends on the visibility of the media
		String thumbImagePath;
		String bigImagePath;

		// visibility
		//if (MultimediaDAO.isMediaVisible(mediaId)) {
		//if (mediaLite.getIsPublic() == 'Y') {
			thumbImagePath = Properties.MEDIA_REAL_BASE_ADDRESS
					+ Properties.FILE_SEPARATOR + Properties.THUMB_IMAGES;
			bigImagePath = Properties.MEDIA_REAL_BASE_ADDRESS
					+ Properties.FILE_SEPARATOR + Properties.BIG_IMAGES;
		//} else {
		//	thumbImagePath = Properties.MEDIA_REAL_BASE_ADDRESS
		//			+ Properties.FILE_SEPARATOR + Properties.THUMB_IMAGES;
		//	bigImagePath = Properties.MEDIA_REAL_BASE_ADDRESS
		//			+ Properties.FILE_SEPARATOR + Properties.BIG_IMAGES;
		//}
		thumbImagePath = thumbImagePath + Properties.FILE_SEPARATOR
				+ todaysDate;
		bigImagePath = bigImagePath + Properties.FILE_SEPARATOR + todaysDate;
		// creates the folder with the date to keep media organized(in case its
		// not created before)
		String[] cmd3 = { "mkdir", thumbImagePath };
		// command = "mkdir " + thumbImagePath;
		OSCommand.run(cmd3);
		String[] cmd4 = { "mkdir", bigImagePath };
		// command = "mkdir " + bigImagePath;
		OSCommand.run(cmd4);
		thumbImagePath = thumbImagePath + Properties.FILE_SEPARATOR
				+ DBFileName;
		bigImagePath = bigImagePath + Properties.FILE_SEPARATOR + DBFileName;

		// the thumbnail was already created it has just to be moved
		String[] cmd5 = { "mv", tempThumbPath, thumbImagePath };
		// command = "mv " + tempThumbPath + " " + thumbImagePath;
		OSCommand.run(cmd5);

		// created the big image and put it in the placeit has to be
		ImageMagickAPI.writeStandardSize(orignalImagePath, bigImagePath);
	}
}
