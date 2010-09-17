/**
 * 
 */
package org.inbio.m3s.web.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.dto.lite.MediaLite;
import org.inbio.m3s.dto.media.BriefMediaOutputDTO;
import org.inbio.m3s.dto.media.BriefMediaOutputDTOFactory;
import org.inbio.m3s.dto.search.SearchCriteriaTripletDTO;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.SearchManager;
import org.inbio.m3s.web.controller.reusable.SimpleController;
import org.inbio.m3s.web.exception.ValidationException;
import org.inbio.m3s.web.filter.FilterMapWrapper;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jgutierrez
 *
 */
public class SearchController extends SimpleController {
	
	private String metadataMediaList;
	
	//managers
	private SearchManager searchManager;
	private AgentManager agentManager;	

	//DTOFactory/Service Mixture
	private BriefMediaOutputDTOFactory briefMediaOutputDTOFactory;
	
	//DAO :S ;(
	private MediaDAO mediaDAO;
	
	private String metadataFilters;
	private FilterMapWrapper filtersMap;
	
	private String metadataFilter ="filter";
	private String metadataCriteria = "criteria";

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		ModelAndView mav = super.handleRequestInternal(request, response);
		logger.debug("El Model al inicio es: ");
		for(Object o : mav.getModel().keySet()){
			logger.debug("\t"+o+" = "+mav.getModel().get(o));	
		}
		
		//filtros de búsqueda
		mav.addObject(metadataFilters, filtersMap.getFilters());
		
		String filter = request.getParameter(metadataFilter);
		String criteria = request.getParameter("criteria");
		String value = request.getParameter("value");
		
		//validacion de filtros más tuanis es urgente acá!
		
		try {
			//parametros obligatorios de una búsqueda:
			if(value!=null)
				value = URLDecoder.decode(value, "UTF-8");

			int first;
			int last;

			logger.debug("filter: "+filter);
			logger.debug("criteria: "+criteria);
			logger.debug("value: "+value);


			if(filter!=null&criteria!=null&value!=null){
				logger.debug("filter!=null&criteria!=null&value!=null");
				
				first =  Integer.valueOf(request.getParameter("first")).intValue();
				if(first < 1)
					first = 1;
				last = Integer.valueOf(request.getParameter("last")).intValue();

				Integer searchFilterId = Integer.valueOf(filter);
				Integer searchCriteriaId = Integer.valueOf(criteria);

				//comienza la busqueda
				SearchCriteriaTripletDTO scTriplet = new SearchCriteriaTripletDTO(searchFilterId,searchCriteriaId,value);
				List<SearchCriteriaTripletDTO> sctList = new ArrayList<SearchCriteriaTripletDTO>();
				sctList.add(scTriplet);

				int totalResults = searchManager.getTotalResults(sctList); 
				List<Integer> mediaIdsList = searchManager.getResults(sctList, first, last);

				MediaLite ml;
				List<BriefMediaOutputDTO> bmoDTOList = new ArrayList<BriefMediaOutputDTO>();
				for(Integer mediaId : mediaIdsList){
					ml = mediaDAO.getMediaLite(mediaId);
					bmoDTOList.add((BriefMediaOutputDTO) briefMediaOutputDTOFactory.createDTO(ml));
				}

				//pone la lista con resultados en el model
				mav.addObject(metadataMediaList, bmoDTOList);

				//interface elements
				mav.addAllObjects(setInterfaceElements(first,last,totalResults));

				//filter + value
				mav.addObject(metadataFilter, filter);
				mav.addObject("value", value);

			} else {
				logger.debug("en el else de> filter!=null&criteria!=null&value!=null");
				criteria ="0";
				first =0;
				last = 10;
			}

			mav.addObject("criteria", criteria);
			mav.addObject("first", first);
			mav.addObject("last", last);
			mav.addObject("error", null);

		} catch (Exception e){
			
			logger.debug("Exception: "+e.getMessage());
			
			ValidationException ve;
			Map<String, Object> modelElements = getModelElements();
			
			if(e instanceof IllegalArgumentException){
				ve = new ValidationException(e.getMessage(), e.getCause());
				modelElements.put("error", "ERROR: "+e.getMessage());
			} else {
				ve = new ValidationException();
			}
			
			ve.setViewName(getViewName());
			ve.setErrorMessageKey("error.metadata.01");
			
			/* el form action está incluido en los model elements heredados	*/
			modelElements.put(metadataFilters, filtersMap.getFilters());
			modelElements.put(metadataFilter, filter);
			modelElements.put("criteria", criteria);
			modelElements.put("value", value);
			modelElements.put("first", 0);
			modelElements.put("last", 10);
			
			
			ve.setModelElements(modelElements);
			logger.debug("throw ValidationException");
			throw ve;
		} 
		
		logger.debug("El Model es: ");
		for(Object o : mav.getModel().keySet()){
			logger.debug("\t"+o+" = "+mav.getModel().get(o));	
		}
		
		return mav;
	}
		
	/**
	 * 
	 * @param first
	 * @param last
	 * @param totalResults
	 * @return
	 */
	public Map<String, Object> setInterfaceElements(int first, int last, int totalResults){
		
		Map<String, Object> interfaceElements = new HashMap<String, Object>();
		
		//carnita
		//butons :s
		int showing = (last - first)+1;
		if(totalResults < showing)
			showing = totalResults;
		int min;
		int max;
		
		//previous min and max
		max = first -1;
		min = first-showing;
		if(first > 2){
			interfaceElements.put("previousParams", "&first="+min+"&last="+max);
		} else{
			interfaceElements.put("previousParams", null);
		}
		
		//next min and max
		min = last+1;
		max = last+showing;
		if((totalResults - last) > 0){
			interfaceElements.put("nextParams", "&first="+min+"&last="+max);
		} else{
			interfaceElements.put("nextParams", null);
		}
		
		//fin de buton
		interfaceElements.put("totalResults", totalResults);
		interfaceElements.put("showing", showing);
		
		return interfaceElements;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		logger.debug("Terminando clase searchController");	
		super.finalize();
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



	/**
	 * @return the metadataFilters
	 */
	public String getMetadataFilters() {
		return metadataFilters;
	}



	/**
	 * @param metadataFilters the metadataFilters to set
	 */
	public void setMetadataFilters(String metadataFilters) {
		this.metadataFilters = metadataFilters;
	}



	/**
	 * @return the filtersMap
	 */
	public FilterMapWrapper getFiltersMap() {
		return filtersMap;
	}



	/**
	 * @param filtersMap the filtersMap to set
	 */
	public void setFiltersMap(FilterMapWrapper filtersMap) {
		this.filtersMap = filtersMap;
	}


	
}
