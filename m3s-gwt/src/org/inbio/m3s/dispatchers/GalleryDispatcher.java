/**
 * 
 */
package org.inbio.m3s.dispatchers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inbio.gwt.associatedto.client.dto.AssociatedToConstants;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.converters.impl.GeneralMetadataConverter;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.metadata.UsesAndCopyrightsDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.gwt.client.dto.metadata.GeneralMetadataGWTDTO;
import org.inbio.m3s.gwt.client.widgets.galleries.SearchCriteriaTriplet;
import org.inbio.m3s.manager.SearchManager;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.MediaManager;
import org.inbio.m3s.service.TaxonomyManager;
import org.inbio.m3s.util.ServiceUtil;


/**
 * Dispatches images stored on the server only if the image is set as public!.
 * 
 * 
 * @author jgutierrez
 * 
 */
public class GalleryDispatcher extends HttpServlet {

	public static int THUMP_IMAGE = 1;

	public static int BIG_IMAGE = 2;

	// private static final String UNKNOWN_MIME_TYPE =
	// "application/x-unknown-mime-type";

	/**
	 * 
	 */
	private static final long serialVersionUID = 5706888888097730675L;

	/**
	 * Returns an image from the database only if the image is public!.
	 * 
	 * The servlet receives a list of parameters in HttpServletRequest method
	 * parameter, the way each parameter is used will be explain here:
	 * 
	 * @param id
	 *          a literal id of the decidered image
	 * 
	 * @param size
	 *          String that says if is a "thumb", "big", "full" or whatever image
	 *          size.
	 * 
	 * Example of use: http://localhost:8686/m3sINBio/getGallery?filter=3&criteria=0&value=Atta&css=CSS_URL&first=3&last=5
	 * Example of use: http://localhost:8080/m3s/getGallery?keyword=Lepidoptera
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		ServletOutputStream out = res.getOutputStream(); // binary output
		res.setContentType("text/html");
		//parameters
		Integer searchFilterId = Integer.valueOf(req.getParameter("filter"));
		Integer searchCriteriaId = Integer.valueOf(req.getParameter("criteria"));
		String value = req.getParameter("value");
		String css = req.getParameter("css");
		int first =  Integer.valueOf(req.getParameter("first")).intValue();
		if(first < 1)
			first = 1;
		int last = Integer.valueOf(req.getParameter("last")).intValue();
		
		int totalResults;

		
		
		SearchCriteriaTriplet scTriplet = new SearchCriteriaTriplet(searchFilterId,searchCriteriaId,value);
		List<SearchCriteriaTriplet> sctList = new ArrayList<SearchCriteriaTriplet>();
		sctList.add(scTriplet);
		
		totalResults = SearchManager.getTotalResults(sctList); 
		List<Integer> mediaIdsList = SearchManager.getResults(sctList, first, last);
		MediaManager mediaManager = (MediaManager) ServiceUtil.appContext.getBean(Properties.MEDIA_MANAGER);
		GeneralMetadataGWTDTO gmGWTDTO;
		GeneralMetadataConverter gmmc = new GeneralMetadataConverter();
		UsesAndCopyrightsDTO uacDTO = null;
				
			// writting the HTML file
			out.println("<html><head><title>M3S Auto Generated Gallery</title>");
			if(css != null)
				out.println("<link href=\""+css+"\" rel=\"stylesheet\" type=\"text/css\">");
			out.println("</head><body>");

			out.println("<h1>Galería de Prueba</h1>");
			out.println("<p>");

			
			out.println("<table class=\"thumb\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr>");
			
			//los controles son de moverse a un lado u otro...
			//muestra tambien el total de resultados
			out.println(addControlButtons(searchFilterId.intValue(), searchCriteriaId.intValue(), value, css, first, last, totalResults));
			
			
			for (Integer mediaId : mediaIdsList) {
				gmGWTDTO = gmmc.toGWTDTO(mediaManager.getGeneralMetadataByMedia(String.valueOf(mediaId)));
				uacDTO = mediaManager.getUACM(String.valueOf(mediaId));
				//uacGWTDTO = uacmConverter.to
				///uactv = MetadataConverter.toTextualValue(mediaManager.getUACM(mediaId));
				out.println(addItem(gmGWTDTO, uacDTO));
			}
			
			out.println("</tr></tbody></table>");

			out.println("</body></html>");
			
			out.flush();
			out.close();
			
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
			prevResults = "<A HREF = \""+baseURL+"&first="+min+"&last="+max+"\">"+previousResultsText+"</A>";
		else
			prevResults = noMoreResult;
		
		//next min and max
		min = last+1;
		max = last+showing;
		if((totalResults - last) > 0)
			nextResults = "<A HREF = \""+baseURL+"&first="+min+"&last="+max+"\">"+posteriorResultsText+"</A>";
		else
			nextResults = noMoreResult;

		return "<p>Total de resultados: "+totalResults+" (Mostrando: "+showing+")</p>"
				+ "<p> "+prevResults+"  || "+nextResults+" </p>";
	}
	
	/**
	 * 
	 * @param gmGWTDTO
	 * @param uacDTO
	 * @return
	 */
	private String addItem(GeneralMetadataGWTDTO gmGWTDTO, UsesAndCopyrightsDTO uacDTO){
		String imageSize = "thumb";
		String baseURL = "/m3sINBio/getImage?";
		String mediaId = gmGWTDTO.getMediaKey();
		AgentManager agentManager = (AgentManager) ServiceUtil.appContext.getBean(Properties.AGENT_MANAGER);
		
		//taxonomia o titulo
		String info1;
		if(gmGWTDTO.getTaxonomyInfo()!=null){
			if(gmGWTDTO.getTaxonomyInfo().size() > 0){
			TaxonomyManager taxonomyManager = (TaxonomyManager) ServiceUtil.appContext.getBean(Properties.TAXONOMY_MANAGER);
			TaxonLiteDTO tlDTO = (TaxonLiteDTO) taxonomyManager.getTaxonLiteById((String) gmGWTDTO.getTaxonomyInfo().get(0));
			info1 = tlDTO.getDefaultName();
			} else
				info1="";
		} else if (gmGWTDTO.getTitle()!=null)
			info1= "Titulo: "+gmGWTDTO.getTitle();
		else
			info1="";
		
		//bio information
		String info2;
		if(gmGWTDTO.getAssociatedToInfo().getType().intValue() == AssociatedToConstants.GATHERING_CODE.intValue())
			info2 = "Código de Colecta: "+gmGWTDTO.getAssociatedToInfo().getValue();
		else if(gmGWTDTO.getAssociatedToInfo().getType().intValue() == AssociatedToConstants.SPECIMEN_NUMBER.intValue())
			info2 = "Número de Especimen: "+gmGWTDTO.getAssociatedToInfo().getValue();
		else if(gmGWTDTO.getAssociatedToInfo().getType().intValue() == AssociatedToConstants.OBSERVATION_NUMBER.intValue())
			info2 = "Número de Observación: "+gmGWTDTO.getAssociatedToInfo().getValue();
		else //if(gmtv.getAssociatedToInfo().getType().intValue() == AssociatedToConstants.NO_ASSOCIATION.intValue())
			info2 = "Sin información asociada";		

		//autor de la foto.
		//String info3 = "Autor: "+ uacDTO.getAuthor();
		PersonLiteDTO plDTO =  agentManager.getPersonLite(uacDTO.getAuthorKey());
		String info3 = "Autor: "+ plDTO.getName();
		
		return
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
	}	
	
	
	

}
