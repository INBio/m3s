package org.inbio.m3s.web.controller.ajax;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inbio.m3s.dao.core.SiteDAO;
import org.inbio.m3s.dto.taxonomy.SpecimenLiteDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.service.TaxonomyManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * 
 * @author jgutierrez
 *
 */
public class AssociatedToController  extends MultiActionController {

	TaxonomyManager taxonomyManager;
	SiteDAO siteDAO;
	
	/* For the yahoo auto complete javascript this value always is = "query"*/
	private String queryParam = "param";
	//private String observationNumberQueryParam = "observationNumber";
	//private String gatheringCodeQueryParam= "gatheringCode";
	
	private String taxonNameParam = "name";
	private String kingdomNameParam = "kingdom";
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView taxonomyBySpecimenNumber(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String specimenNumber = request.getParameter(queryParam);		
		String errorMsj = "No se puede obtener la taxonomía asociada al especimen #" + specimenNumber + ".";
		TaxonLiteDTO tlDTO = null;
		List<TaxonLiteDTO> tlDTOList = new ArrayList<TaxonLiteDTO>();

		try {
			tlDTO = taxonomyManager.getTaxonLiteFromSpecimenId(specimenNumber);
			tlDTO = taxonomyManager.setKingdomName(tlDTO);
			//tlDTO = new TaxonLiteDTO("7", "por hacer", "123");
			tlDTOList.add(tlDTO);
			//}
			return writeXMLOnResponse(request,response, createTaxonomyXML(tlDTOList));

			
		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
	
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView siteBySpecimenNumber(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String specimenNumber = request.getParameter(queryParam);		
		String errorMsj = "No se puede obtener la descripcion del sitio para el especimen #"+ specimenNumber + ".";
		
		try {
			logger.debug("getSiteFromSpecimenNumber... done");
			String site = siteDAO.getSiteDBIdFromSpecimenNumber(new Integer(specimenNumber));

			return writeXMLOnResponse(request,response, createSiteXML(site));
			
		} catch (IllegalArgumentException iae) {
			logger.error("getSiteFromSpecimenNumber... error");
			logger.error(errorMsj);
			logger.error(iae.getMessage());
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
			
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView taxonomyByObservationNumber(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String observationNumber = request.getParameter(queryParam);

		String errorMsj = "No se puede obtener los taxonones asociados con la observacion  #"+ observationNumber + ".";
		List<TaxonLiteDTO> tlDTOList = new ArrayList<TaxonLiteDTO>();

		try {
			tlDTOList = taxonomyManager.getTaxonLiteFromObservationId(observationNumber);
			tlDTOList = taxonomyManager.setKingdomName(tlDTOList);
			
			return writeXMLOnResponse(request,response, createTaxonomyXML(tlDTOList));

			
		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
	
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView siteByObservationNumber(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String observationNumber = request.getParameter(queryParam);		
		logger.debug("getSiteFromObservationNumber... start");
		String errorMsj = "No se puede obtener la descripcion del sitio para la observacion #"
				+ observationNumber + ".";
		
		try {
			logger.debug("getSiteFromObservationNumber... done");
			String site = siteDAO.getiteDBIdFromObservationNumber(new Integer(observationNumber));
			return writeXMLOnResponse(request,response, createSiteXML(site));
			
		} catch (IllegalArgumentException iae) {
			logger.debug("getSiteFromObservationNumber... error");
			logger.debug(iae.getMessage());
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
			
	}	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView taxonomyByGatheringCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String gatheringCode = request.getParameter(queryParam);

		String errorMsj = "No se puede obtener taxonomía asociada a la recolecta  #" + gatheringCode + ".";
		List<TaxonLiteDTO> tlDTOList = new ArrayList<TaxonLiteDTO>();

		try {
			tlDTOList = taxonomyManager.getTaxonLiteFromGatheringCode(gatheringCode);
			tlDTOList = taxonomyManager.setKingdomName(tlDTOList);
			
			return writeXMLOnResponse(request,response, createTaxonomyXML(tlDTOList));

			
		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
	
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView siteByGatheringCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String gatheringCode = request.getParameter(queryParam);		
		String errorMsj = "No se puede obtener la descripcion del sitio para la recolecta #" + gatheringCode + ".";
		
		
		try {
			List<SpecimenLiteDTO> slDTOList = taxonomyManager.getSpecimenLiteForGatheringCode(gatheringCode);
			SpecimenLiteDTO slDTO = slDTOList.get(0);
		
			String site = siteDAO.getSiteDBIdFromSpecimenNumber(new Integer(slDTO.getSpecimenKey()));
			return writeXMLOnResponse(request,response, createSiteXML(site));

		} catch (IllegalArgumentException iae) {
			logger.debug("getSiteFromGatheringCode... error");
			logger.debug(iae.getMessage());
			throw new Exception(errorMsj + " "
					+ iae.getMessage());
		}
	}
	
	/**
	 * taxonomyByNameAndKingdom
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView taxonomyByNameAndKingdom(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String name = request.getParameter(taxonNameParam);
		String kingdom = request.getParameter(kingdomNameParam);		
		String errorMsj = "No se puede obtener la taxonomía asociada para: " + name + ".";
		TaxonLiteDTO tlDTO = null;
		
		List<TaxonLiteDTO> tlDTOList = new ArrayList<TaxonLiteDTO>();

		try {
			tlDTO = taxonomyManager.getTaxonLite(name, kingdom);
			if(tlDTO!=null){
				tlDTO = taxonomyManager.setKingdomName(tlDTO);
				tlDTOList.add(tlDTO);
			}
			
			return writeXMLOnResponse(request,response, createTaxonomyXML(tlDTOList));
			
		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
	
	}	
	
	private ModelAndView writeXMLOnResponse(HttpServletRequest request,
			HttpServletResponse response, String xml) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;charset=UTF-8");
		ServletOutputStream out = response.getOutputStream(); // binary output
	  
	  out.println(xml);
				
		out.flush();
		out.close();
		
		return null;
	}
	
	private String createTaxonomyXML(List<TaxonLiteDTO> taxonLiteDTOList) throws Exception {

		String rootElement = "response";
		
		String basicElement = "taxon";
		String taxonIdElement = "id";
		String taxonNameElement = "name";
		String kingdomNameElement = "kingdom";
		
		String output ="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
			+ "<"+rootElement+">";
		for(TaxonLiteDTO tlDTO : taxonLiteDTOList){
			output+= "<"+basicElement+">";
			  output+= "<"+taxonIdElement+">"+tlDTO.getTaxonKey()+"</"+taxonIdElement+">";
			  output+= "<"+taxonNameElement+">"+tlDTO.getDefaultName()+"</"+taxonNameElement+">";
			  output+= "<"+kingdomNameElement+">"+tlDTO.getKingdomName()+"</"+kingdomNameElement+">";
			output+="</"+basicElement+">";
		}
	  
	  output+= "</"+rootElement+">";
		
		return output;
	}	
	
	private String createSiteXML(String siteDescription) throws Exception {

		String rootElement = "response";
		
		String basicElement = "site";
		//String siteIdElement = "id";
		String siteDescriptionElement = "description";
		
		String output ="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
			+ "<"+rootElement+">";
		//for(TaxonLiteDTO tlDTO : taxonLiteDTOList){
			output+= "<"+basicElement+">";
			  //output+= "<"+siteIdElement+">"+siteLiteDTO.get .getTaxonKey()+"</"+siteIdElement+">";
			  output+= "<"+siteDescriptionElement+">"+siteDescription+"</"+siteDescriptionElement+">";
			output+="</"+basicElement+">";
		//}
	  
	  output+= "</"+rootElement+">";
		
		return output;
	}	

	/**
	 * @return the taxonomyManager
	 */
	public TaxonomyManager getTaxonomyManager() {
		return taxonomyManager;
	}

	/**
	 * @param taxonomyManager the taxonomyManager to set
	 */
	public void setTaxonomyManager(TaxonomyManager taxonomyManager) {
		this.taxonomyManager = taxonomyManager;
	}

	/**
	 * @return the siteDAO
	 */
	public SiteDAO getSiteDAO() {
		return siteDAO;
	}

	/**
	 * @param siteDAO the siteDAO to set
	 */
	public void setSiteDAO(SiteDAO siteDAO) {
		this.siteDAO = siteDAO;
	}
}

