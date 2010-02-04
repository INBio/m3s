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
import org.inbio.m3s.dto.metadata.MetadataDTO;
import org.inbio.m3s.dto.search.SearchCriteriaTripletDTO;
import org.inbio.m3s.dto.taxonomy.GatheringLiteDTO;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.MetadataManager;
import org.inbio.m3s.service.SearchManager;
import org.inbio.m3s.util.StringUtil;
import org.inbio.m3s.web.controller.reusable.SimpleController;
import org.inbio.m3s.web.exception.ValidationException;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jgutierrez
 *
 */
public class GalleryController extends SimpleController {

	public static int THUMP_IMAGE = 1;

	public static int BIG_IMAGE = 2;

	// private static final String UNKNOWN_MIME_TYPE =
	// "application/x-unknown-mime-type";
	
	private SearchManager searchManager;
	private MetadataManager metadataManager;
	private AgentManager agentManager;	
	
	//DTOFactory/Service Mixture
	private BriefMediaOutputDTOFactory briefMediaOutputDTOFactory;
	
	//DAO :S ;(
	private MediaDAO mediaDAO;
	
	//parameters
	private String metadataMediaList;
	private String metadataFilter ="filter";
	private String metadataCriteria = "criteria";
	private String metadataValue = "value";
	private String metadataCss = "css";
	
	
	/**
	 * Returns a galleries of public images from the database.
	 * 
	 * The servlet receives a list of parameters in HttpServletRequest method
	 * parameter, the way each parameter is used will be explain here:
	 * 
	 * 
	 * Example of use: http://localhost:8686/m3sINBio/getGallery?filter=3&criteria=0&value=Atta&css=CSS_URL&first=3&last=5
	 * Example of use: http://localhost:8080/m3s/getGallery?keyword=Lepidoptera
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = super.handleRequestInternal(request, response);
		
		
		String filter = request.getParameter(metadataFilter);
		String criteria = request.getParameter(metadataCriteria);
		String value = request.getParameter(metadataValue);
		String css = request.getParameter(metadataCss);
		
		
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

				MetadataDTO mDTO;
				MediaLite ml;
				List<BriefMediaOutputDTO> bmoDTOList = new ArrayList<BriefMediaOutputDTO>();
				BriefMediaOutputDTO bmoDTO;
				String info2 = "";
				
				for(Integer mediaId : mediaIdsList){
					ml = mediaDAO.getMediaLite(mediaId);
					mDTO = metadataManager.getMetadataByMedia(String.valueOf(mediaId));
					
					bmoDTO = (BriefMediaOutputDTO) briefMediaOutputDTOFactory.createDTO(ml);
				
					//modify info2, set the association type
					//biodiversity information (associated to?)
					logger.debug("Discovering the association type:");
					if (mDTO.getAssociatedSpecimensList() != null && mDTO.getAssociatedSpecimensList().size() != 0) {
						info2 = "Número de Especimen: "+mDTO.getAssociatedSpecimensList().get(0).getSpecimenKey();
					} else if (mDTO.getAssociatedObservationsList() != null && mDTO.getAssociatedObservationsList().size() != 0) {
						info2 = "Número de Observación: "+mDTO.getAssociatedObservationsList().get(0).getObservationKey();
					} else if (mDTO.getAssociatedGatheringsList() != null && mDTO.getAssociatedGatheringsList().size() != 0) {
						GatheringLiteDTO glDTO = mDTO.getAssociatedGatheringsList().get(0);		
						String gatheringPersonName = glDTO.getResponsiblePersonName();
						info2 = "Código de Colecta: "+gatheringPersonName + StringUtil.TEXT_DELIMITER + glDTO.getGatheringKey();
					} else {
						info2 = "Sin información asociada";
					}					
					bmoDTO.setInfo2(info2);
					
					bmoDTOList.add(bmoDTO);
				}

				//pone la lista con resultados en el model
				mav.addObject(metadataMediaList, bmoDTOList);

				//interface elements
				mav.addAllObjects(setInterfaceElements(first,last,totalResults));

				//filter + value
				mav.addObject(metadataFilter, filter);
				mav.addObject(metadataValue, value);

			} else {
				criteria ="0";
				first =0;
				last = 10;
			}
			
			modelElements.put(metadataCss, css);
			mav.addObject(metadataCriteria, criteria);
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
			modelElements.put(metadataFilter, filter);
			modelElements.put(metadataCriteria, criteria);
			modelElements.put(metadataValue, value);
			modelElements.put(metadataCss, css);
			modelElements.put("first", 0);
			modelElements.put("last", 10);
			
			ve.setModelElements(modelElements);
			logger.debug("throw ValidationException");
			throw ve;
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
	private Map<String, Object> setInterfaceElements(int first, int last, int totalResults){
		
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
	 * @return the metadataFilter
	 */
	public String getMetadataFilter() {
		return metadataFilter;
	}

	/**
	 * @param metadataFilter the metadataFilter to set
	 */
	public void setMetadataFilter(String metadataFilter) {
		this.metadataFilter = metadataFilter;
	}

	/**
	 * @return the metadataCriteria
	 */
	public String getMetadataCriteria() {
		return metadataCriteria;
	}

	/**
	 * @param metadataCriteria the metadataCriteria to set
	 */
	public void setMetadataCriteria(String metadataCriteria) {
		this.metadataCriteria = metadataCriteria;
	}

	/**
	 * @return the metadataValue
	 */
	public String getMetadataValue() {
		return metadataValue;
	}

	/**
	 * @param metadataValue the metadataValue to set
	 */
	public void setMetadataValue(String metadataValue) {
		this.metadataValue = metadataValue;
	}

	/**
	 * @return the metadataCss
	 */
	public String getMetadataCss() {
		return metadataCss;
	}

	/**
	 * @param metadataCss the metadataCss to set
	 */
	public void setMetadataCss(String metadataCss) {
		this.metadataCss = metadataCss;
	}

	/**
	 * @return the metadataManager
	 */
	public MetadataManager getMetadataManager() {
		return metadataManager;
	}

	/**
	 * @param metadataManager the metadataManager to set
	 */
	public void setMetadataManager(MetadataManager metadataManager) {
		this.metadataManager = metadataManager;
	}		

}
