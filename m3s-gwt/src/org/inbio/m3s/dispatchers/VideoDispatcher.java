/**
 * 
 */
package org.inbio.m3s.dispatchers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
 * Dispatches FLV videos stored on the server only if is public!.
 * 
 * 
 * @author jgutierrez
 * 
 */
public class VideoDispatcher extends HttpServlet {


	//private static final String UNKNOWN_MIME_TYPE = "application/x-unknown-mime-type";

	/**
	 * 
	 */
	private static final long serialVersionUID = 5706888888097730675L;

	/**
	 * Returns an video from the database only if is public!.
	 * 
	 * The servlet receives a list of parameters in HttpServletRequest method
	 * parameter, the way each parameter is used will be explain here:
	 * 
	 * @param id
	 *          a literal id of the decidered video
	 * 
	 * 
	 * Example of use: 
	 * http://localhost:8080/m3s/getVideo?id=114
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		ServletOutputStream out = res.getOutputStream(); // binary output
		//String authenticationKey = req.getParameter("authenticationKey");
		Integer videoId = Integer.valueOf(req.getParameter("id"));
		MediaDAO mediaDAO = (MediaDAO) ServiceUtil.appContext.getBean("mediaDAO");
		MediaLite mediaLite = mediaDAO.getMediaLite(videoId);		
		

		String mediaAddress;
		//value == 'Y'
		//if (MultimediaDAO.isMediaVisible(imageId))
		if(mediaLite.getIsPublic() == 'Y'){
			//logger.debug("video visible");
			mediaAddress = RealMediaFinder.getVideoPath(videoId);
			//mediaAddress = RealMediaFinder.getPath(imageId, size);
		}	else{
			mediaAddress = Properties.web_APP_FILES + "/BIG_IMG_PRIVATE.gif";
			throw new ServletException("flv not exists");
		}
		
		
		// Open file.
		// Prepare file object.
		File file = new File(mediaAddress);
		BufferedInputStream input = new BufferedInputStream(new FileInputStream(
				file));
		int contentLength = input.available();

		// set the content type
		// if (new MimetypesFileTypeMap().getContentType(imageFile)) {

		// has to be gotten from the fileMiMEType metadata value
		res.setContentType("video/x-flv");

		// Write file contents to response.
		while (contentLength-- > 0) {
			out.write(input.read());
		}

		out.flush();
		out.close();
	}


}
