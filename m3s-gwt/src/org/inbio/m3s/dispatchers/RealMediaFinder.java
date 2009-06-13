/**
 * 
 */
package org.inbio.m3s.dispatchers;

import java.io.File;

import org.apache.log4j.Logger;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.dto.lite.MediaLite;
import org.inbio.m3s.util.ServiceUtil;

/**
 * @author jgutierrez
 * 
 * Finds the real path of the media managing access controls
 * 
 */
public class RealMediaFinder {

	private static Logger logger = Logger.getLogger(RealMediaFinder.class);
	
	/**
	 * Creates the address where an image should be given the id, makes a query to
	 * the DB to see the format of the image and the visibility issue (public or
	 * private).
	 * 
	 * @param imageId
	 * @param size
	 *          class constant
	 * @return the real path of the image on the file system
	 */
	public static String getPath(Integer imageId, int size)
			throws IllegalArgumentException {

		String path;
		MediaDAO mediaDAO = (MediaDAO) ServiceUtil.appContext.getBean("mediaDAO");
		MediaLite mediaLite = mediaDAO.getMediaLite(imageId);

		// look if the image is public
		//if (MultimediaDAO.isMediaVisible(imageId)) {
		path = Properties.MEDIA_REAL_BASE_ADDRESS;
		if(mediaLite.getIsPublic() == 'Y'){
			// path = ClientProperties.IMAGES_PUBLIC_WEB_BASE_ADDRESS;
			logger.debug("image visible");
		} else {
			//path = Properties.MEDIA_REAL_BASE_ADDRESS;
			logger.error("image NO visible");
		}

		// apends the size to the path
		if (size == ImageDispatcher.THUMP_IMAGE) {
			path = path.concat(File.separator + Properties.THUMB_IMAGES);
		} else if (size == ImageDispatcher.BIG_IMAGE) {
			path = path.concat(File.separator + Properties.BIG_IMAGES);
		} else {
			// HibernateUtil.closeM3SDBFactory();
			throw new IllegalArgumentException(
					"That size of image it's not valid or implemented");
		}

		// gets the DB insertion date (log field CREATION_DATE)
		try {
			
			//return ((Date) queryResult.get(0)).toString();
			//path = path.concat(File.separator+ MultimediaDAO.getMediaCreationDate(imageId));
			path = path.concat(File.separator+ mediaLite.getCreationDate().toString());
		} catch (IllegalArgumentException iae) {
			// HibernateUtil.closeM3SDBFactory();
			throw new IllegalArgumentException("Media not found");
		}
		// adds the id of the media
		path = path.concat(File.separator + imageId.toString());

		// adds the extention of the media file
		// FIXME: needs to work with DB conection
		// fileExtension = MultimediaDAO.getFileExtension(imageId);
		path = path.concat("." + "jpg");

		// HibernateUtil.closeM3SDBFactory();
		return path;

	}

	/**
	 * 
	 * @param videoId
	 * @return
	 */
	public static String getVideoPath(Integer videoId) {
		String path;
		MediaDAO mediaDAO = (MediaDAO) ServiceUtil.appContext.getBean("mediaDAO");
		MediaLite mediaLite = mediaDAO.getMediaLite(videoId);

		// the video is public, because was check on the VideoDispatcher method.
		path = Properties.MEDIA_REAL_BASE_ADDRESS;

		//videos always will be on the BIG_IMAGES folder
		path = path.concat(File.separator + Properties.BIG_IMAGES);

		// gets the DB insertion date (log field CREATION_DATE)
		path = path.concat(File.separator+ mediaLite.getCreationDate().toString());

		// adds the id of the media
		path = path.concat(File.separator + videoId.toString());

		// adds the extention of the media file
		// FIXME: needs to work with DB conection
		// fileExtension = MultimediaDAO.getFileExtension(imageId);
		path = path.concat("." + "flv");

		// HibernateUtil.closeM3SDBFactory();
		return path;
	}

}
