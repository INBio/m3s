package org.inbio.m3s.web.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLConnection;

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

	public int THUMP_IMAGE = 1;
	public int BIG_IMAGE = 2;
	
	public String THUMB_IMAGES = "THUMB";
	public String BIG_IMAGES = "BIG";	
	
	public String WEB_APP_FILES = "/usr/share/tomcat5.5/webapps/m3sINBioFiles/";
	
	public String MEDIA_REAL_BASE_ADDRESS= "/mnt/m3sImages/INBio/MEDIA/";
	
	private MediaDAO mediaDAO;
	
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        
        ServletOutputStream out = httpServletResponse.getOutputStream(); // binary output
    		Integer imageId = Integer.valueOf(httpServletRequest.getParameter("id"));
    		int size = validatedLiteralSize(httpServletRequest.getParameter("size"));
    		
    		
    		int contentLength = 0;
    		BufferedInputStream input = null;

    		//imagen 'hosteada' en attila...
    		/*
    		if (imageId <= 100000) {
    			String strURL = "http://attila.inbio.ac.cr:7777/pls/portal30/IMAGEDB.GET_BFILE_IMAGE?p_imageId="+imageId+"&p_imageResolutionId="+size+"";
    			URL url  = new URL(strURL);
    	    URLConnection conn = url.openConnection();      
          conn.setUseCaches(false); 
          contentLength = conn.getContentLength();
          input = new BufferedInputStream(conn.getInputStream());
    			 
    			// oracle image
    			//http://attila.inbio.ac.cr:7777/pls/portal30//IMAGEDB.GET_BFILE_IMAGE?p_imageId=33433&p_imageResolutionId=1
          
          //imagen 'hosteada' en m3s
    		} else {
*/
    			MediaLite mediaLite = mediaDAO.getMediaLite(imageId);


    			String imageAddress;
    			// value == 'Y'
    			// if (MultimediaDAO.isMediaVisible(imageId))
    			if (mediaLite.getIsPublic() == 'Y')
    				imageAddress = getPath(imageId, size);
    			else
    				imageAddress = WEB_APP_FILES + "images/unavailable.png";

    			// Open image file.
    			// Prepare file object.
    			File imageFile = new File(imageAddress);
    			input = new BufferedInputStream(new FileInputStream(
    					imageFile));
    			contentLength = input.available();

//    		} //parte del 'if' para ver si la img esta en attila

    		// has to be gotten from the fileMiMEType metadata value
    		httpServletResponse.setContentType("image/jpeg");

    		
    		//	 Write file contents to response.
    		while (contentLength-- > 0) {
    			out.write(input.read());
    		}
    		
    		out.flush();
    		out.close();
    		
    		return null;
    }
    
  	/**
  	 * 
  	 * @param literalSize
  	 * @return
  	 */
    private int validatedLiteralSize(String literalSize) {
    	
    		try{
    			
    			return Integer.parseInt(literalSize);

    		} catch(NumberFormatException nfe){
    		
    			if (literalSize.compareToIgnoreCase("thumb") == 0) {
    				return THUMP_IMAGE;
    			
    			} else if (literalSize.compareToIgnoreCase("big") == 0) {
    				return BIG_IMAGE;
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
  	private String getPath(Integer imageId, int size) throws IllegalArgumentException {

  		String path;
  		MediaLite mediaLite = mediaDAO.getMediaLite(imageId);

  		// look if the image is public
  		//if (MultimediaDAO.isMediaVisible(imageId)) {
  		path = MEDIA_REAL_BASE_ADDRESS;
  		if(mediaLite.getIsPublic() == 'Y'){
  			// path = ClientProperties.IMAGES_PUBLIC_WEB_BASE_ADDRESS;
  			logger.debug("image visible");
  		} else {
  			//path = Properties.MEDIA_REAL_BASE_ADDRESS;
  			logger.error("image NO visible");
  		}

  		// apends the size to the path
  		if (size == THUMP_IMAGE) {
  			path = path.concat(File.separator + THUMB_IMAGES);
  		} else if (size == BIG_IMAGE) {
  			path = path.concat(File.separator + BIG_IMAGES);
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
  	 * @param mediaDAO the mediaDAO to set
  	 */
  	public void setMediaDAO(MediaDAO mediaDAO) {
  		this.mediaDAO = mediaDAO;
  	}
}