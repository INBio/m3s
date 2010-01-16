/**
 * 
 */
package org.inbio.m3s.web.controller.media;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.dto.lite.MediaLite;
import org.inbio.m3s.dto.media.BriefMediaOutputDTO;
import org.inbio.m3s.dto.media.BriefMediaOutputDTOFactory;
import org.inbio.m3s.dto.search.SearchCriteriaTripletDTO;
import org.inbio.m3s.service.StatisticsManager;
import org.inbio.m3s.web.controller.reusable.SimpleController;
import org.inbio.m3s.web.exception.ValidationException;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jgutierrez
 *
 */
public class MediaPageController extends SimpleController{
	
	protected static Log logger = LogFactory.getLog(MediaPageController.class);
	
	//web parameters
	private String metadataMediaId ="id";
	
	private MediaDAO mediaDAO;	
	private BriefMediaOutputDTOFactory briefMediaOutputDTOFactory;
	private StatisticsManager statisticsManager;
	
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		ModelAndView mav = super.handleRequestInternal(request, response);
		
		String mediaId = request.getParameter(metadataMediaId);
		mav.addObject("mediaId", mediaId);
		
		//String filter = request.getParameter(metadataFilter);
		//String criteria = request.getParameter("criteria");
		//String value = request.getParameter("value");
		
				
		//List<MediaLite> mediaLiteList = mediaDAO.getLastPublicMedia(12);
		
		//List<BriefMediaOutputDTO> bmoDTOList = briefMediaOutputDTOFactory.createDTOList(mediaLiteList);
		//mav.addObject(metadataOutputMediaList, bmoDTOList);
		
		//logger.debug("Cantidad de resulatados> "+bmoDTOList.size());
		
		
		return mav;
		
	}


}

