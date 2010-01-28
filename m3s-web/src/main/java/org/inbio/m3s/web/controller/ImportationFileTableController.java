package org.inbio.m3s.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inbio.m3s.dto.importcontrol.ImportControlDTOFull;
import org.inbio.m3s.service.ImportationManager;
import org.inbio.m3s.web.controller.reusable.SimpleController;

import org.springframework.web.servlet.ModelAndView;



/**
 * 
 * @author jgutierrez
 *
 */
public class ImportationFileTableController extends SimpleController {

	
	//model & JSP's
	private String metadataUsernameKey;
	private String importationHistoryKey = "icDTOList";
	
	
	//managers
	private ImportationManager importationManager;
	
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = super.handleRequestInternal(request, response);

		String userName = request.getParameter(metadataUsernameKey);
		logger.debug("userName: "+userName);
 
		List<ImportControlDTOFull> importationHistoryListDTO = importationManager.getImportControlDTOFullList(userName, 20);
		logger.debug("results:"+importationHistoryListDTO.size());
		mav.addObject(importationHistoryKey, importationHistoryListDTO);

		
		return mav;
	}


	/**
	 * @return the metadataUsernameKey
	 */
	public String getMetadataUsernameKey() {
		return metadataUsernameKey;
	}


	/**
	 * @param metadataUsernameKey the metadataUsernameKey to set
	 */
	public void setMetadataUsernameKey(String metadataUsernameKey) {
		this.metadataUsernameKey = metadataUsernameKey;
	}


	/**
	 * @return the importationHistoryKey
	 */
	public String getImportationHistoryKey() {
		return importationHistoryKey;
	}


	/**
	 * @param importationHistoryKey the importationHistoryKey to set
	 */
	public void setImportationHistoryKey(String importationHistoryKey) {
		this.importationHistoryKey = importationHistoryKey;
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

