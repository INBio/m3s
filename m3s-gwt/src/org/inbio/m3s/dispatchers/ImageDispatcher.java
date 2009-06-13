/**
 * 
 */
package org.inbio.m3s.dispatchers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inbio.m3s.config.Properties;
import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.dto.lite.MediaLite;
import org.inbio.m3s.util.ServiceUtil;

/**
 * Dispatches images stored on the server only if the image is set as public!.
 * 
 * 
 * @author jgutierrez
 * 
 */
public class ImageDispatcher extends HttpServlet {

	public static int THUMP_IMAGE = 1;

	public static int BIG_IMAGE = 2;

	// private static final String UNKNOWN_MIME_TYPE =
	// "application/x-unknown-mime-type";

	/**
	 * 
	 */
	private static final long serialVersionUID = 5706888888097730675L;

	/**
	 * Returns an image from the database only if the image is public!.
	 * 
	 * The servlet receives a list of parameters in HttpServletRequest method
	 * parameter, the way each parameter is used will be explain here:
	 * 
	 * @param id
	 *          a literal id of the decidered image
	 * 
	 * @param size
	 *          String that says if is a "thumb", "big", "full" or whatever image
	 *          size.
	 * 
	 * Example of use: http://localhost:8080/m3s/getImage?size=thumb&id=IMAGE_ID
	 * http://localhost:8080/m3s/getImage?size=big&id=252
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		ServletOutputStream out = res.getOutputStream(); // binary output
		// String authenticationKey = req.getParameter("authenticationKey");
		Integer imageId = Integer.valueOf(req.getParameter("id"));
		int size = validatedLiteralSize(req.getParameter("size")); 
		
		int contentLength = 0;
		BufferedInputStream input = null;

		//imagen 'hosteada' en attila...
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

			MediaDAO mediaDAO = (MediaDAO) ServiceUtil.appContext.getBean("mediaDAO");
			MediaLite mediaLite = mediaDAO.getMediaLite(imageId);


			String imageAddress;
			// value == 'Y'
			// if (MultimediaDAO.isMediaVisible(imageId))
			if (mediaLite.getIsPublic() == 'Y')
				imageAddress = RealMediaFinder.getPath(imageId, size);
			else
				imageAddress = Properties.web_APP_FILES + "/BIG_IMG_PRIVATE.gif";

			// Open image file.
			// Prepare file object.
			File imageFile = new File(imageAddress);
			input = new BufferedInputStream(new FileInputStream(
					imageFile));
			contentLength = input.available();

		}

		// has to be gotten from the fileMiMEType metadata value
		res.setContentType("image/jpeg");

		
		//	 Write file contents to response.
		while (contentLength-- > 0) {
			out.write(input.read());
		}
		
		out.flush();
		out.close();
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
  				return ImageDispatcher.THUMP_IMAGE;
  			
  			} else if (literalSize.compareToIgnoreCase("big") == 0) {
  				return ImageDispatcher.BIG_IMAGE;
  			}  			
 
  		}
				// Image not found, return a temporal image with something
				return -1;
	}

}
