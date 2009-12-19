package org.inbio.m3s.web.controller.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inbio.m3s.dto.importcontrol.ImportControlDTOFull;
import org.inbio.m3s.service.ImportationManager;
import org.inbio.m3s.service.TaxonomyManager;
import org.inbio.m3s.web.controller.reusable.XMLWriterMultiActionController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author jgutierrez
 *
 */
public class DataTableController extends XMLWriterMultiActionController {

	ImportationManager importationManager;
	TaxonomyManager taxonomyManager;
	
	/* For the yahoo auto complete javascript this value always is = "query"*/
	private String queryParam = "param";
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView importationData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String username = request.getParameter(queryParam);		
		//String errorMsj = "No se puede obtener la taxonomía asociada al especimen #" + specimenNumber + ".";
		

		List<ImportControlDTOFull> icList = importationManager.getImportControlDTOFullList(username, 10);
		
	  return writeXMLOnResponse(request,response, createImportationInfoXML(icList));
	
	}
	
	
	private String createImportationInfoXML(List<ImportControlDTOFull> icDTOList) throws Exception {

		String rootElement = "myroot";
		
		//estos si quedan pura vida.
		//String totalRecords = "totalrecords";
		String allitems = "allitems";
		String itemElement ="item";
		String userFileNameElement = "userfilename";
		String statusElement = "status";
		String creationDateElement ="creationdate";
		String downloadIdElement ="downloadid";
		
		String output ="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
			+ "<"+rootElement+" rootatt=\"5\">";
		
		//output+= "<"+totalRecords+">1</"+totalRecords+">";
		
		output+= "<"+allitems+">";
		for(ImportControlDTOFull icDTOFull : icDTOList){
			output+= "<"+itemElement+">";
			  output+= "<"+userFileNameElement+">"+icDTOFull.getUserFileName()+"</"+userFileNameElement+">";
			  output+= "<"+statusElement+">"+icDTOFull.getStatus()+"</"+statusElement+">";
			  output+= "<"+creationDateElement+">"+icDTOFull.getCreationDate().toString()+"</"+creationDateElement+">";
			  output+= "<"+downloadIdElement+">"+icDTOFull.getSystemFileName()+"</"+downloadIdElement+">";
			output+="</"+itemElement+">";
		}
		output+= "</"+allitems+">";
	  
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
	 * @return the importationManager
	 */
	public ImportationManager getImportationManager() {
		return importationManager;
	}


	/**
	 * @param importationManager the importationManager to set
	 */
	public void setImportationManager(ImportationManager importationManager) {
		this.importationManager = importationManager;
	}

	
}

