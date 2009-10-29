/**
 * 
 */
package org.inbio.m3s.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.dto.lite.MediaLite;
import org.inbio.m3s.dto.metadata.GeneralMetadataDTO;
import org.inbio.m3s.dto.metadata.UsesAndCopyrightsDTO;
import org.inbio.m3s.dto.search.SearchCriteriaTripletDTO;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.MediaManager;
import org.inbio.m3s.service.SearchManager;
import org.inbio.m3s.web.controller.reusable.SimpleController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jgutierrez
 *
 */
public class SearchController extends SimpleController {
	
	private String metadataMediaList;
	
	//managers
	private SearchManager searchManager;
	private MediaManager mediaManager;
	private AgentManager agentManager;	
	
	//DAO :S
	private MediaDAO mediaDAO;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ModelAndView mav = super.handleRequestInternal(request, response);
		
		//parametros obligatorios de una búsqueda:
		String filter = request.getParameter("filter");
		String criteria = request.getParameter("criteria");
		String value = request.getParameter("value");
		if(filter!=null&criteria!=null&value!=null){
			int first =  Integer.valueOf(request.getParameter("first")).intValue();
			if(first < 1)
				first = 1;
			int last = Integer.valueOf(request.getParameter("last")).intValue();
			
			
			List<MediaLite> medias = query(filter,criteria,value,first,last);
			mav.addObject(metadataMediaList, medias);
		}
		
		return mav;
	}
	
	private List<MediaLite> query(String filter, String criteria, String value, int first, int last){
		
		Integer searchFilterId = Integer.valueOf(filter);
		Integer searchCriteriaId = Integer.valueOf(criteria);
		SearchCriteriaTripletDTO scTriplet = new SearchCriteriaTripletDTO(searchFilterId,searchCriteriaId,value);
		List<SearchCriteriaTripletDTO> sctList = new ArrayList<SearchCriteriaTripletDTO>();
		sctList.add(scTriplet);
		
		int totalResults = searchManager.getTotalResults(sctList); 
		List<Integer> mediaIdsList = searchManager.getResults(sctList, first, last);
		
		List<MediaLite> mediaLiteList = new ArrayList<MediaLite>();
		for(Integer mediaId : mediaIdsList){
			mediaLiteList.add(mediaDAO.getMediaLite(mediaId));
		}
		
		return mediaLiteList;
	}

	/**
	 * @return the metadataMediaList
	 */
	public String getMetadataMediaList() {
		return metadataMediaList;
	}

	/**
	 * @param metadataMediaList the metadataMediaList to set
	 */
	public void setMetadataMediaList(String metadataMediaList) {
		this.metadataMediaList = metadataMediaList;
	}

	/**
	 * @return the searchManager
	 */
	public SearchManager getSearchManager() {
		return searchManager;
	}

	/**
	 * @param searchManager the searchManager to set
	 */
	public void setSearchManager(SearchManager searchManager) {
		this.searchManager = searchManager;
	}

	/**
	 * @return the mediaManager
	 */
	public MediaManager getMediaManager() {
		return mediaManager;
	}

	/**
	 * @param mediaManager the mediaManager to set
	 */
	public void setMediaManager(MediaManager mediaManager) {
		this.mediaManager = mediaManager;
	}

	/**
	 * @return the agentManager
	 */
	public AgentManager getAgentManager() {
		return agentManager;
	}

	/**
	 * @param agentManager the agentManager to set
	 */
	public void setAgentManager(AgentManager agentManager) {
		this.agentManager = agentManager;
	}

	/**
	 * @return the mediaDAO
	 */
	public MediaDAO getMediaDAO() {
		return mediaDAO;
	}

	/**
	 * @param mediaDAO the mediaDAO to set
	 */
	public void setMediaDAO(MediaDAO mediaDAO) {
		this.mediaDAO = mediaDAO;
	}
	
}
