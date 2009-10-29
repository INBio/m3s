/**
 * 
 */
package org.inbio.m3s.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.dto.lite.MediaLite;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author jgutierrez
 *
 */
public class HomePageController extends AbstractController{
	
	private MediaDAO mediaDAO;	
	
	protected static Log logger = LogFactory.getLog(HomePageController.class);
	
	public HomePageController(){}

	protected ModelAndView handleRequestInternal(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		
		List<MediaLite> mediaLiteList = mediaDAO.getLastPublicMedia(8);
		logger.debug("Cantidad de resulatados> "+mediaLiteList.size());
		
		return new ModelAndView("home","medias",mediaLiteList);
	}

	/**
	 * @param mediaDAO the mediaDAO to set
	 */
	public void setMediaDAO(MediaDAO mediaDAO) {
		this.mediaDAO = mediaDAO;
	}

}
