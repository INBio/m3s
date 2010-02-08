package org.inbio.m3s.web.controller.upload;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.inbio.m3s.SingleFileUploadBean;
import org.inbio.m3s.dto.importcontrol.ImportControlDTOFull;
import org.inbio.m3s.dto.metadata.util.ImportationFileEntity;
import org.inbio.m3s.service.ImportationManager;
import org.inbio.m3s.service.util.ImportThread;
import org.inbio.m3s.web.exception.ValidationException;


import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;



/**
 * 
 * @author jgutierrez
 *
 */
public class UploadImportationFileController extends SimpleFormController {

	//Constants
	private String importFilePath;
	private String importationBatchMediaPath;
	private String mediaFilesPath;//${mediaFilesDir}
	private String fileExtension; //="xls";
	
	//model & JSP's
	private String metadataUsernameKey;
	private String importationHistoryKey = "icDTOList";
	
	//in case of error	
	private String errorViewName; //="insertStep1";
	private String errorFormActionKey; //="excelFormAction"
	private String errorFormActionValue; //="uploadImportationFile.html"
	
	//managers
	private ImportationManager importationManager;
	private ImportThread importThread;	


	protected ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response,
			Object command,BindException errors) throws ServletException, IOException, Exception {

		//in case of error just fire this exception
		ValidationException ve = new ValidationException();
		ve.setViewName(errorViewName);
		Map<String,Object> modelElements = new HashMap<String, Object>();
		modelElements.put(errorFormActionKey,errorFormActionValue);
		ve.setModelElements(modelElements);
		
		
		try { 
			
			ModelAndView mav = new ModelAndView(getSuccessView());

			String userName = request.getParameter(metadataUsernameKey);
			logger.debug("userName: "+userName);
			//mav.addObject(metadataUsernameKey, userName);
			
			// Creates a new HttpSession so the other servlets could identify
			// the file that has been upload
			HttpSession session = request.getSession(true);			
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String systemFileName = timestamp.toString() + userName +session.getId()+"."+fileExtension;
			systemFileName = systemFileName.replace(' ', '-');

			// cast the bean
			SingleFileUploadBean bean = (SingleFileUploadBean) command;
			//let's see if there's content there
			MultipartFile importationFile = bean.getFile();
				
			if (importationFile == null || importationFile.isEmpty()) {
				//no hay archivo que subir :S
				ve.setErrorMessageKey("error.insert.01");
				throw ve;
			} else{
				logger.debug("despues del else.");
				File f = new File(importFilePath+systemFileName);
				importationFile.transferTo(f);
				f.setWritable(true, false);
				//f.setExecutable(true, false);
				//if(f.canWrite())
				//	logger.debug("se puede escribir");
				//else
				//	logger.debug("NOOOOOOOO se puede escribir");
				
			}

			
			List<ImportControlDTOFull> importationHistoryListDTO = importationManager.getImportControlDTOFullList(userName, 20);
			logger.debug("results:"+importationHistoryListDTO.size());
			mav.addObject(importationHistoryKey, importationHistoryListDTO);
			
			if(systemFileName != null)
				//executeImport(userName,fileName);
				importThread.run(ImportationFileEntity.MS_EXCEL_FILE, userName, systemFileName, importationFile.getOriginalFilename(),importFilePath,importationBatchMediaPath,mediaFilesPath);
			
			return mav;
		
		} catch (Exception e){

			if(ve.getErrorMessageKey()!= null)
				throw ve;
			
			ve.setErrorMessageKey("error.insert.01");
			throw ve;
		}

	}
	
	
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
	throws ServletException {
		// to actually be able to convert Multipart instance to byte[]
		// we have to register a custom editor
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		// now Spring knows how to handle multipart object and convert them
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


	/**
	 * @return the fileExtension
	 */
	public String getFileExtension() {
		return fileExtension;
	}


	/**
	 * @param fileExtension the fileExtension to set
	 */
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
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
	 * @return the errorViewName
	 */
	public String getErrorViewName() {
		return errorViewName;
	}


	/**
	 * @param errorViewName the errorViewName to set
	 */
	public void setErrorViewName(String errorViewName) {
		this.errorViewName = errorViewName;
	}


	/**
	 * @return the errorFormActionKey
	 */
	public String getErrorFormActionKey() {
		return errorFormActionKey;
	}


	/**
	 * @param errorFormActionKey the errorFormActionKey to set
	 */
	public void setErrorFormActionKey(String errorFormActionKey) {
		this.errorFormActionKey = errorFormActionKey;
	}


	/**
	 * @return the errorFormActionValue
	 */
	public String getErrorFormActionValue() {
		return errorFormActionValue;
	}


	/**
	 * @param errorFormActionValue the errorFormActionValue to set
	 */
	public void setErrorFormActionValue(String errorFormActionValue) {
		this.errorFormActionValue = errorFormActionValue;
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
	 * @return the importationBatchMediaPath
	 */
	public String getImportationBatchMediaPath() {
		return importationBatchMediaPath;
	}


	/**
	 * @param importationBatchMediaPath the importationBatchMediaPath to set
	 */
	public void setImportationBatchMediaPath(String importationBatchMediaPath) {
		this.importationBatchMediaPath = importationBatchMediaPath;
	}


	/**
	 * @return the mediaFilesPath
	 */
	public String getMediaFilesPath() {
		return mediaFilesPath;
	}


	/**
	 * @param mediaFilesPath the mediaFilesPath to set
	 */
	public void setMediaFilesPath(String mediaFilesPath) {
		this.mediaFilesPath = mediaFilesPath;
	}


}

