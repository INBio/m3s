package org.inbio.m3s.web.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.dto.lite.MediaLite;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * 
 * @author jgutierrez
 * 
 */
public class ImageController extends AbstractController {

	// values of the servlet
	private String metadataTemporalId = "temporal";

	// constantes
	private String temporalFilesPath; // ${temporalFilesPath}

	private String mediaFilesPath; // ${mediaFilesPath}

	private String thumbMediaFolder;// ${thumbMediaFolder}

	private String bigMediaFolder; // ${bigMediaFolder}

	private int thumbImageCode;

	private int bigImageCode;

	private MediaDAO mediaDAO;

	/*
	 * Este servlet ataja las peticiones de imagenes. (incluye imagenes
	 * temporales). Los parametros que el servlet soporta son: id= identificador
	 * la de imagenes size= el tamaaño que puede ser thumb o big temporal=el
	 * identificador de la imagen como temporal
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal
	 * (javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws Exception {

		try {

			String temporalId = httpServletRequest.getParameter(metadataTemporalId);

			String imageAddress;

			if (temporalId != null) {
				imageAddress = temporalFilesPath + temporalId;
			} else {

				Integer imageId = Integer.valueOf(httpServletRequest.getParameter("id"));
				int size = validatedLiteralSize(httpServletRequest.getParameter("size"));

				MediaLite mediaLite = mediaDAO.getMediaLite(imageId);
				// Is Visible?
				if (mediaLite.getIsPublic() == 'Y')
					imageAddress = getPath(imageId, size, mediaLite.getCreationDate()
							.toString());
				else {
					logger.error("image with id=" + imageId + " isn't visible");
					imageAddress = temporalFilesPath + "unavailable.png";
				}
				
				httpServletResponse.setHeader("Content-Disposition", "attachment; filename="+imageId+".jpg" );
			}

			// starting the delivering of the image

			ServletOutputStream out = httpServletResponse.getOutputStream(); // binary
																																				// output
			int contentLength = 0;
			BufferedInputStream input = null;

			// Open image file.
			// Prepare file object.
			File imageFile = new File(imageAddress);
			input = new BufferedInputStream(new FileInputStream(imageFile));
			contentLength = input.available();

			// has to be gotten from the fileMiMEType metadata value
			httpServletResponse.setContentType("image/jpeg");
			

			// Write file contents to response.
			while (contentLength-- > 0) {
				out.write(input.read());
			}

			out.flush();
			out.close();

			return null;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error404");
		}
	}

	/**
	 * 
	 * @param literalSize
	 * @return
	 */
	private int validatedLiteralSize(String literalSize) {

		try {

			return Integer.parseInt(literalSize);

		} catch (NumberFormatException nfe) {

			if (literalSize.compareToIgnoreCase("thumb") == 0) {
				return thumbImageCode;

			} else if (literalSize.compareToIgnoreCase("big") == 0) {
				return bigImageCode;
			}

		}
		// Image not found, return a temporal image with something
		return -1;
	}

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
	private String getPath(Integer imageId, int size, String imageCreationDate)
			throws IllegalArgumentException {

		String path = mediaFilesPath;

		// apends the size to the path
		if (size == thumbImageCode) {
			path = path.concat(File.separator + thumbMediaFolder);
		} else if (size == bigImageCode) {
			path = path.concat(File.separator + bigMediaFolder);
		} else {
			throw new IllegalArgumentException(
					"That size of image it's not valid or implemented");
		}

		// creation date folder
		path = path.concat(File.separator + imageCreationDate);

		// adds the id of the media
		path = path.concat(File.separator + imageId.toString());

		// adds the extention of the media file
		// FIXME: needs to work with DB conection
		// fileExtension = MultimediaDAO.getFileExtension(imageId);
		path = path.concat("." + "jpg");

		return path;

	}

	/**
	 * @return the metadataTemporalId
	 */
	public String getMetadataTemporalId() {
		return metadataTemporalId;
	}

	/**
	 * @param metadataTemporalId
	 *          the metadataTemporalId to set
	 */
	public void setMetadataTemporalId(String metadataTemporalId) {
		this.metadataTemporalId = metadataTemporalId;
	}

	/**
	 * @return the temporalFilesPath
	 */
	public String getTemporalFilesPath() {
		return temporalFilesPath;
	}

	/**
	 * @param temporalFilesPath
	 *          the temporalFilesPath to set
	 */
	public void setTemporalFilesPath(String temporalFilesPath) {
		this.temporalFilesPath = temporalFilesPath;
	}

	/**
	 * @return the mediaFilesPath
	 */
	public String getMediaFilesPath() {
		return mediaFilesPath;
	}

	/**
	 * @param mediaFilesPath
	 *          the mediaFilesPath to set
	 */
	public void setMediaFilesPath(String mediaFilesPath) {
		this.mediaFilesPath = mediaFilesPath;
	}

	/**
	 * @return the thumbMediaFolder
	 */
	public String getThumbMediaFolder() {
		return thumbMediaFolder;
	}

	/**
	 * @param thumbMediaFolder
	 *          the thumbMediaFolder to set
	 */
	public void setThumbMediaFolder(String thumbMediaFolder) {
		this.thumbMediaFolder = thumbMediaFolder;
	}

	/**
	 * @return the bigMediaFolder
	 */
	public String getBigMediaFolder() {
		return bigMediaFolder;
	}

	/**
	 * @param bigMediaFolder
	 *          the bigMediaFolder to set
	 */
	public void setBigMediaFolder(String bigMediaFolder) {
		this.bigMediaFolder = bigMediaFolder;
	}

	/**
	 * @return the thumbImageCode
	 */
	public int getThumbImageCode() {
		return thumbImageCode;
	}

	/**
	 * @param thumbImageCode
	 *          the thumbImageCode to set
	 */
	public void setThumbImageCode(int thumbImageCode) {
		this.thumbImageCode = thumbImageCode;
	}

	/**
	 * @return the bigImageCode
	 */
	public int getBigImageCode() {
		return bigImageCode;
	}

	/**
	 * @param bigImageCode
	 *          the bigImageCode to set
	 */
	public void setBigImageCode(int bigImageCode) {
		this.bigImageCode = bigImageCode;
	}

	/**
	 * @return the mediaDAO
	 */
	public MediaDAO getMediaDAO() {
		return mediaDAO;
	}

	/**
	 * @param mediaDAO
	 *          the mediaDAO to set
	 */
	public void setMediaDAO(MediaDAO mediaDAO) {
		this.mediaDAO = mediaDAO;
	}

}