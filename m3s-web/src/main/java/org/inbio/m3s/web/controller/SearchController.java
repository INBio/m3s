/**
 * 
 */
package org.inbio.m3s.web.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.dto.lite.MediaLite;
import org.inbio.m3s.dto.media.BriefMediaOutputDTO;
import org.inbio.m3s.dto.media.BriefMediaOutputDTOFactory;
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

	//DTOFactory/Service Mixture
	private BriefMediaOutputDTOFactory briefMediaOutputDTOFactory;
	
	//DAO :S ;(
	private MediaDAO mediaDAO;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ModelAndView mav = super.handleRequestInternal(request, response);
		
		//parametros obligatorios de una búsqueda:
		String filter = request.getParameter("filter");
		String criteria = request.getParameter("criteria");
		String value = request.getParameter("value");
		if(value!=null)
			value = URLDecoder.decode(value, "UTF-8");
		logger.debug("filter: "+filter);
		logger.debug("criteria: "+criteria);
		logger.debug("value: "+value);
		
		if(filter!=null&criteria!=null&value!=null){
			int first =  Integer.valueOf(request.getParameter("first")).intValue();
			if(first < 1)
				first = 1;
			int last = Integer.valueOf(request.getParameter("last")).intValue();
			
			Integer searchFilterId = Integer.valueOf(filter);
		  Integer searchCriteriaId = Integer.valueOf(criteria);
		  SearchCriteriaTripletDTO scTriplet = new SearchCriteriaTripletDTO(searchFilterId,searchCriteriaId,value);
		  List<SearchCriteriaTripletDTO> sctList = new ArrayList<SearchCriteriaTripletDTO>();
		  sctList.add(scTriplet);
		
		  int totalResults = searchManager.getTotalResults(sctList); 
		  List<Integer> mediaIdsList = searchManager.getResults(sctList, first, last);
		
		  //List<MediaLite> mediaLiteList = new ArrayList<MediaLite>();
		  MediaLite ml;
		  List<BriefMediaOutputDTO> bmoDTOList = new ArrayList<BriefMediaOutputDTO>();
		  for(Integer mediaId : mediaIdsList){
		  	ml = mediaDAO.getMediaLite(mediaId);
		  	bmoDTOList.add((BriefMediaOutputDTO) briefMediaOutputDTOFactory.createDTO(ml));
		  }
			
			mav.addObject(metadataMediaList, bmoDTOList);
			
			//butons :s
			String previousResultsText = " << Resultados anteriores ";
			String posteriorResultsText = " Resultados posteriores >> ";
			String noMoreResult = "No hay más resultados";
			int showing = (last - first)+1;
			if(totalResults < showing)
				showing = totalResults;
			int min;
			int max;
			String prevResults;
			String nextResults;
			
			String baseURL = "/m3sINBio/getGallery?filter="+filter+"&criteria="+criteria+"&value="+value;
			
			//previous min and max
			max = first -1;
			min = first-showing;
			if(first > 2){
				prevResults = "<a href = \""+baseURL+"&first="+min+"&last="+max+"\">"+previousResultsText+"</a>";
				mav.addObject("previousParams", "&first="+min+"&last="+max);
			} else{
				prevResults = noMoreResult;
				mav.addObject("previousParams", null);
			}
			
			
			//next min and max
			min = last+1;
			max = last+showing;
			if((totalResults - last) > 0){
				nextResults = "<a href = \""+baseURL+"&first="+min+"&last="+max+"\">"+posteriorResultsText+"</a>";
				mav.addObject("nextParams", "&first="+min+"&last="+max);
			} else{
				nextResults = noMoreResult;
				mav.addObject("nextParams", null);
			}
			
			
			
			
			mav.addObject("controlButtons","<p> "+prevResults+"  || "+nextResults+" </p>");
			//fin de buton
			mav.addObject("totalResults", totalResults);
			mav.addObject("showing", showing);
			mav.addObject("criteria", criteria);
			mav.addObject("filter", filter);
			mav.addObject("value", value);
			mav.addObject("first", first);
			mav.addObject("last", last);
		}
		
		return mav;
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
	
}
