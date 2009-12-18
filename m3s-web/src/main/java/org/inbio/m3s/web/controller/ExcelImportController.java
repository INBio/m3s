/**
 * 
 */
package org.inbio.m3s.web.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inbio.m3s.dto.importcontrol.ImportControlDTOFull;
import org.inbio.m3s.dto.metadata.util.ImportationFileEntity;
import org.inbio.m3s.service.ImportationManager;
import org.inbio.m3s.service.util.ImportThread;
import org.inbio.m3s.util.OSCommand;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author jgutierrez
 *
 */
public class ExcelImportController extends AbstractController{

	//view
	private String viewName;
	
	//data
	private String tempFilePath;
	private String importFilePath;
	private String metadataFileName;
	private String metadataUsername;
	
	//managers
	private ImportationManager importationManager;
	private ImportThread importThread;
	
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView(viewName);
		
		String userName = request.getParameter(metadataUsername);
		String fileName = request.getParameter(metadataFileName);
		logger.debug("userName: "+userName);
		logger.debug("fileName: "+fileName);
		
		List<ImportControlDTOFull> l = getResultTableData(userName);
		logger.debug("results:"+l.size());
		mav.addObject("icDTOList", l);
		
		if(fileName != null)
			executeImport(userName,fileName);
		
		return mav;
	}
	
	/**
	 * @param username
	 *            user which import control information is requested
	 * @return a list of ImportResultData elements to be displayed on the table
	 */
	private List<ImportControlDTOFull> getResultTableData(String userName) {
		
		List<ImportControlDTOFull> icList = importationManager.getImportControlDTOFullList(userName, 20);
		for(ImportControlDTOFull  icDTO : icList){
			icDTO.setSystemFileName("http://localhost:8180/m3sINBioFiles/IMPORT_FILES/"+icDTO.getSystemFileName());
		}
		
		//Properties.WEB_IMPORT_FILES_DIR+icFull.getSystemFileName()
		
		return icList;
	}

	/**
	 * @param usernameImportOwner
	 * @param tempFileId
	 * @deprecated
	 */
	private void executeImport(String usernameImportOwner, String tempFileId) {

		// Moves the import file to the imports file dir and renames it...
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String systemFileName = timestamp.toString() + tempFileId;
		systemFileName = systemFileName.replace(' ', '-');

		String tempFileAddress =  tempFilePath + tempFileId;

		String systemFileAddress = importFilePath	+ systemFileName;

		logger.debug("mv " + tempFileAddress + " " + systemFileAddress);

		String[] cmd = { "mv", tempFileAddress, systemFileAddress };
		OSCommand.run(cmd);

		importThread.run(ImportationFileEntity.MS_EXCEL_FILE, usernameImportOwner, systemFileName, tempFileId);

	}	

	/**
	 * @return the viewName
	 */
	public String getViewName() {
		return viewName;
	}

	/**
	 * @param viewName the viewName to set
	 */
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	/**
	 * @return the metadataFileName
	 */
	public String getMetadataFileName() {
		return metadataFileName;
	}

	/**
	 * @param metadataFileName the metadataFileName to set
	 */
	public void setMetadataFileName(String metadataFileName) {
		this.metadataFileName = metadataFileName;
	}

	/**
	 * @return the metadataUsername
	 */
	public String getMetadataUsername() {
		return metadataUsername;
	}

	/**
	 * @param metadataUsername the metadataUsername to set
	 */
	public void setMetadataUsername(String metadataUsername) {
		this.metadataUsername = metadataUsername;
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

	/**
	 * @return the importThread
	 */
	public ImportThread getImportThread() {
		return importThread;
	}

	/**
	 * @param importThread the importThread to set
	 */
	public void setImportThread(ImportThread importThread) {
		this.importThread = importThread;
	}

	/**
	 * @return the tempFilePath
	 */
	public String getTempFilePath() {
		return tempFilePath;
	}

	/**
	 * @param tempFilePath the tempFilePath to set
	 */
	public void setTempFilePath(String tempFilePath) {
		this.tempFilePath = tempFilePath;
	}

	/**
	 * @return the importFilePath
	 */
	public String getImportFilePath() {
		return importFilePath;
	}

	/**
	 * @param importFilePath the importFilePath to set
	 */
	public void setImportFilePath(String importFilePath) {
		this.importFilePath = importFilePath;
	}
}
