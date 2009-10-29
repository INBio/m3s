/**
 * 
 */
package org.inbio.m3s.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.metadata.GeneralMetadataDTO;
import org.inbio.m3s.dto.metadata.UsesAndCopyrightsDTO;
import org.inbio.m3s.dto.search.SearchCriteriaTripletDTO;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.MediaManager;
import org.inbio.m3s.service.SearchManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author jgutierrez
 *
 */
public class GalleryController extends AbstractController {

	public static int THUMP_IMAGE = 1;

	public static int BIG_IMAGE = 2;

	// private static final String UNKNOWN_MIME_TYPE =
	// "application/x-unknown-mime-type";
	
	private SearchManager searchManager;
	private MediaManager mediaManager;
	private AgentManager agentManager;	
	
	
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
		
		ServletOutputStream out = response.getOutputStream(); // binary output
		response.setContentType("text/html");
		//parameters
		Integer searchFilterId = Integer.valueOf(request.getParameter("filter"));
		Integer searchCriteriaId = Integer.valueOf(request.getParameter("criteria"));
		String value = request.getParameter("value");
		String css = request.getParameter("css");
		int first =  Integer.valueOf(request.getParameter("first")).intValue();
		if(first < 1)
			first = 1;
		int last = Integer.valueOf(request.getParameter("last")).intValue();
		
		int totalResults;

		
		
		SearchCriteriaTripletDTO scTriplet = new SearchCriteriaTripletDTO(searchFilterId,searchCriteriaId,value);
		List<SearchCriteriaTripletDTO> sctList = new ArrayList<SearchCriteriaTripletDTO>();
		sctList.add(scTriplet);
		
		totalResults = searchManager.getTotalResults(sctList); 
		List<Integer> mediaIdsList = searchManager.getResults(sctList, first, last);
		
		GeneralMetadataDTO gmDTO;
		UsesAndCopyrightsDTO uacDTO = null;
				
			// writting the HTML file
			out.println("<html><head><title>M3S Auto Generated Gallery</title>");
			if(css != null)
				out.println("<link href=\""+css+"\" rel=\"stylesheet\" type=\"text/css\">");
			out.println("</head><body>");

			//cuestiones para el css
			out.println("<div id=\"wrapper\">");
			out.println("<div id=\"nido\">");
			out.println("<div id=\"banner\"> </div>");
			out.println("<div id=\"contenido\">");

			//out.println("<h1>Galería de Prueba</h1>");
			//out.println("<p>");
			//out.println("<table class=\"thumb\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr>");
			
			//los controles son de moverse a un lado u otro...
			//muestra tambien el total de resultados
			out.println(addControlButtons(searchFilterId.intValue(), searchCriteriaId.intValue(), value, css, first, last, totalResults));
			
			out.println("<div class=\"thumb\">");

			
			for (Integer mediaId : mediaIdsList) {
				gmDTO = mediaManager.getGeneralMetadataByMedia(String.valueOf(mediaId));
				uacDTO = mediaManager.getUACM(String.valueOf(mediaId));
				//uacGWTDTO = uacmConverter.to
				///uactv = MetadataConverter.toTextualValue(mediaManager.getUACM(mediaId));
				out.println(addItem(gmDTO, uacDTO));
			}
			
			//out.println("</tr></tbody></table>");

			out.println("<div class=\"clear\"></div></div></div>");
			out.println("<div id=\"footer\">");
			out.println("<div id=\"logos\">");
			out.println("<div id=\"contacto\"><a href=\"mailto:alm@nhm.ac.uk\">Contactenos</a></div>");
			out.println("</div>");
			out.println("<div id=\"footerIn\">2009 Derechos reservados al proyecto ‘Herramientas por el manejo del Parque Internacional La Amistad de la Iniciativa Darwin del Reino Unido</div>");			
			out.println("</div></div></div>");

			
			out.println("</body></html>");
			
			out.flush();
			out.close();
			
			return null;
	}
	
	/**
	 * 
	 * @param filter
	 * @param criteria
	 * @param value
	 * @param css
	 * @param first
	 * @param last
	 * @param totalResults
	 * @return
	 */
	private String addControlButtons(int filter, int criteria, String value, String css, int first, int last, int totalResults){
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
		
		String baseURL = "/m3sINBio/getGallery?filter="+filter+"&criteria="+criteria+"&value="+value+"&css="+css;
		
		//previous min and max
		max = first -1;
		min = first-showing;
		if(first > 2)
			prevResults = "<a href = \""+baseURL+"&first="+min+"&last="+max+"\">"+previousResultsText+"</a>";
		else
			prevResults = noMoreResult;
		
		//next min and max
		min = last+1;
		max = last+showing;
		if((totalResults - last) > 0)
			nextResults = "<a href = \""+baseURL+"&first="+min+"&last="+max+"\">"+posteriorResultsText+"</a>";
		else
			nextResults = noMoreResult;

		return "<p>Total de resultados: "+totalResults+" (Mostrando: "+showing+")</p>"
				+ "<p> "+prevResults+"  || "+nextResults+" </p>";
	}
	
	/**
	 * 
	 * @param gmDTO
	 * @param uacDTO
	 * @return
	 */
	private String addItem(GeneralMetadataDTO gmDTO, UsesAndCopyrightsDTO uacDTO){
		String imageSize = "thumb";
		String linkImageSize = "big";
		String baseURL = "/m3sINBio/getImage?";
		String mediaId = gmDTO.getMediaKey();
		
		//taxonomia o titulo
		String info1 = "";
		/*
		if(gmDTO.getTaxonomyInfo()!=null){
			if(gmTO.getTaxonomyInfo().size() > 0){
			TaxonomyManager taxonomyManager = (TaxonomyManager) ServiceUtil.appContext.getBean(Properties.TAXONOMY_MANAGER);
			TaxonLiteDTO tlDTO = (TaxonLiteDTO) taxonomyManager.getTaxonLiteById((String) gmGWTDTO.getTaxonomyInfo().get(0));
			info1 = tlDTO.getDefaultName();
			} else
				info1="";
		} else if (gmGWTDTO.getTitle()!=null)
			info1= "Titulo: "+gmGWTDTO.getTitle();
		else
			info1="";
		*/
		
		//bio information
		String info2 = "";
		/*
		if(gmGWTDTO.getAssociatedToInfo().getType().intValue() == AssociatedToConstants.GATHERING_CODE.intValue())
			info2 = "Código de Colecta: "+gmGWTDTO.getAssociatedToInfo().getValue();
		else if(gmGWTDTO.getAssociatedToInfo().getType().intValue() == AssociatedToConstants.SPECIMEN_NUMBER.intValue())
			info2 = "Número de Especimen: "+gmGWTDTO.getAssociatedToInfo().getValue();
		else if(gmGWTDTO.getAssociatedToInfo().getType().intValue() == AssociatedToConstants.OBSERVATION_NUMBER.intValue())
			info2 = "Número de Observación: "+gmGWTDTO.getAssociatedToInfo().getValue();
		else //if(gmtv.getAssociatedToInfo().getType().intValue() == AssociatedToConstants.NO_ASSOCIATION.intValue())
			info2 = "Sin información asociada";		
		 */
		
		//autor de la foto.
		PersonLiteDTO plDTO =  agentManager.getPersonLite(uacDTO.getAuthorKey());
		String info3 = "Autor: "+ plDTO.getName();
		
		return
			"<div class=\"imagesRightPanel\"> <a href=\""+baseURL+"size="+linkImageSize+"&id="+mediaId+"\">"
		+ "<div class=\"thumb-image\" style=\"background-image: url("+baseURL+"size="+imageSize+"&id="+mediaId+")\"></div>"
		+ "<div class=\"gwt-Label imaName\">"+info1+"</div>"
		+ "<div class=\"imaInfo\">"+info1+"</div>"
		+ "<div class=\"imaInfo\">"+info2+"</div>"
		+ "</a> </div>";
		/*
			 "<td style=\"vertical-align: top;\" align=\"left\"/>"
		 +  "<table style=\"width: 170px; height: 220px;\" class=\"imagesRightPanel\" cellpadding=\"0\" cellspacing=\"0\">"
		 +   "<tbody>"
		 +    "<tr>"
		 +     "<td style=\"vertical-align: top;\" align=\"left\">"
		 +      "<table style=\"width: 170px; height: 170px;\" cellpadding=\"0\" cellspacing=\"0\">"
		 +       "<tbody>"
		 +        "<tr>"
		 +         "<td style=\"vertical-align: middle;\" height=\"\" width=\"\" align=\"center\">"
		 +          "<img title=\"titulo1\" src=\""+baseURL+"size="+imageSize+"&id="+mediaId+"\" class=\"gwt-Image\">"
		 +         "</td>"
		 +        "</tr>"
		 +       "</tbody>"
		 +      "</table>"
		 +     "</td>"
		 +    "</tr>"
		 +    "<tr>"
		 +     "<td style=\"vertical-align: top;\" align=\"left\">"
		 +      "<div class=\"gwt-Label imaName\">"+info1+"</div>"
		 +     "</td>"
		 +    "</tr>"
		 +    "<tr>"
		 +     "<td style=\"vertical-align: top;\" align=\"left\">"
		 +      "<div class=\"imaInfo\">"+info2+"</div>"
		 +     "</td>"
		 +    "</tr>"
		 +    "<tr>"
		 +     "<td style=\"vertical-align: top;\" align=\"left\">"
		 +      "<div class=\"imaInfo\">"+info3+"</div>"
		 +     "</td>"
		 +    "</tr>"
		 +   "</tbody>"
		 +  "</table>"
		 + "</td>"
		;
		*/
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

}
