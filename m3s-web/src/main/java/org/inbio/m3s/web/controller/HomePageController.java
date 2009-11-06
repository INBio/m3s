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
import org.inbio.m3s.dto.media.BriefMediaOutputDTO;
import org.inbio.m3s.dto.media.BriefMediaOutputDTOFactory;
import org.inbio.m3s.service.StatisticsManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author jgutierrez
 *
 */
public class HomePageController extends AbstractController{
	
	private String metadataOutputMediaList;
	
	/* statisctis info*/
	private String metadataMultimediaCount;
	private String metadataImagesCount;
	private String metadataVideosCount;
	
	private MediaDAO mediaDAO;	
	private BriefMediaOutputDTOFactory briefMediaOutputDTOFactory;
	private StatisticsManager statisticsManager;
	
	protected static Log logger = LogFactory.getLog(HomePageController.class);
	
	public HomePageController(){}

	protected ModelAndView handleRequestInternal(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView("home");
		
		List<MediaLite> mediaLiteList = mediaDAO.getLastPublicMedia(12);
		
		List<BriefMediaOutputDTO> bmoDTOList = briefMediaOutputDTOFactory.createDTOList(mediaLiteList);
		mav.addObject(metadataOutputMediaList, bmoDTOList);
		
		logger.debug("Cantidad de resulatados> "+bmoDTOList.size());
		
		//Statistical Info
		mav.addObject(metadataMultimediaCount,String.valueOf(statisticsManager.getAllMediaCount()));
		mav.addObject(metadataImagesCount,String.valueOf(statisticsManager.getDSCPhotosCount()));
		mav.addObject(metadataVideosCount,String.valueOf(statisticsManager.getVideosCount()));
		
		
		
		return mav;
		
	}

	/**
	 * @param mediaDAO the mediaDAO to set
	 */
	public void setMediaDAO(MediaDAO mediaDAO) {
		this.mediaDAO = mediaDAO;
	}

	/**
	 * @return the briefMediaOutputDTOFactory
	 */
	public BriefMediaOutputDTOFactory getBriefMediaOutputDTOFactory() {
		return briefMediaOutputDTOFactory;
	}

	/**
	 * @param briefMediaOutputDTOFactory the briefMediaOutputDTOFactory to set
	 */
	public void setBriefMediaOutputDTOFactory(
			BriefMediaOutputDTOFactory briefMediaOutputDTOFactory) {
		this.briefMediaOutputDTOFactory = briefMediaOutputDTOFactory;
	}

	/**
	 * @return the metadataOutputMediaList
	 */
	public String getMetadataOutputMediaList() {
		return metadataOutputMediaList;
	}

	/**
	 * @param metadataOutputMediaList the metadataOutputMediaList to set
	 */
	public void setMetadataOutputMediaList(String metadataOutputMediaList) {
		this.metadataOutputMediaList = metadataOutputMediaList;
	}

	/**
	 * @return the statisticsManager
	 */
	public StatisticsManager getStatisticsManager() {
		return statisticsManager;
	}

	/**
	 * @param statisticsManager the statisticsManager to set
	 */
	public void setStatisticsManager(StatisticsManager statisticsManager) {
		this.statisticsManager = statisticsManager;
	}

	/**
	 * @return the mediaDAO
	 */
	public MediaDAO getMediaDAO() {
		return mediaDAO;
	}

	/**
	 * @return the metadataMultimediaCount
	 */
	public String getMetadataMultimediaCount() {
		return metadataMultimediaCount;
	}

	/**
	 * @param metadataMultimediaCount the metadataMultimediaCount to set
	 */
	public void setMetadataMultimediaCount(String metadataMultimediaCount) {
		this.metadataMultimediaCount = metadataMultimediaCount;
	}

	/**
	 * @return the metadataImagesCount
	 */
	public String getMetadataImagesCount() {
		return metadataImagesCount;
	}

	/**
	 * @param metadataImagesCount the metadataImagesCount to set
	 */
	public void setMetadataImagesCount(String metadataImagesCount) {
		this.metadataImagesCount = metadataImagesCount;
	}

	/**
	 * @return the metadataVideosCount
	 */
	public String getMetadataVideosCount() {
		return metadataVideosCount;
	}

	/**
	 * @param metadataVideosCount the metadataVideosCount to set
	 */
	public void setMetadataVideosCount(String metadataVideosCount) {
		this.metadataVideosCount = metadataVideosCount;
	}

}

