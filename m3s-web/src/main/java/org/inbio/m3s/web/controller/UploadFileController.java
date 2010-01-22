package org.inbio.m3s.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.inbio.m3s.MultipleFilesUploadBean;
import org.inbio.m3s.SingleFileUploadBean;
import org.inbio.m3s.util.ImageMagickAPI;
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
public class UploadFileController extends SimpleFormController {

	private String filePath;
	private String metadataFileType;
	private String metadataFileName;
	private String metadataUsername;
	private String metadataTotalFiles;

	private String errorViewName ="insertStep1";

	/** The key correspondes to fileType Options */
	protected Map<String, String> posibleSuccessViewMap;	
	/** The  */
	protected Map<String, String> posibleFileExtensionMap;

	protected ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors) throws ServletException, IOException, Exception {

		//in case of error just fire this exception
		ValidationException ve = new ValidationException();
		ve.setViewName(errorViewName);
		Map<String,Object> modelElements = new HashMap<String, Object>();
		modelElements.put("formAction","uploadFile.html");
		ve.setModelElements(modelElements);
		
		
		try { 

			String fileType = request.getParameter(metadataFileType);
			logger.debug("fileType = " +fileType);
			ModelAndView mav = new ModelAndView(posibleSuccessViewMap.get(fileType));

			String userName = request.getParameter(metadataUsername);
			logger.debug("userName: "+userName);
			mav.addObject(metadataUsername, userName);


			// Creates a new HttpSession so the other servlets could identify
			// the file that has been upload
			HttpSession session = request.getSession(true);
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss-");
			java.util.Date date = new java.util.Date();
			String fileId = dateFormat.format(date) + userName +session.getId();
			
			//Archivos de imagenes, pueden ser hasta 6.
			if(fileType.compareTo("jpgImage")==0){
				
				// cast the bean
				MultipleFilesUploadBean bean = (MultipleFilesUploadBean) command;
				
				int filesCount = bean.getFilesCount();
				mav.addObject(metadataTotalFiles, filesCount);
				
				if(filesCount == 0){
				//no hay archivo que subir :S
					ve.setErrorMessageKey("error.insert.01");
					throw ve;
				}
				
				String fileName = fileId+filesCount+"."+posibleFileExtensionMap.get(fileType);;
				mav.addObject(metadataFileName, fileName);
				
				for(MultipartFile file: bean.getFiles()){
					fileName = fileId+filesCount+"."+posibleFileExtensionMap.get(fileType);
					file.transferTo(new File(filePath+fileName));
				
					try{
							ImageMagickAPI.createThumb(filePath+fileName, filePath+"thumb-"+fileName);
					}catch (Exception e){
						//no se puede mover el archivo de imagen
						ve.setErrorMessageKey("error.insert.02");
						throw ve;
					}
					
					filesCount--;					
				}
				
				//mav.addObject(metadataFileName, fileName);
				//TODO: fix the filename problem
				
				
			
			//Archivo Excel, solo puede ser 1.
			} else if (fileType.compareTo("excel")==0){
				String fileName = fileId+"."+posibleFileExtensionMap.get(fileType);

				// cast the bean
				SingleFileUploadBean bean = (SingleFileUploadBean) command;
				//let's see if there's content there
				MultipartFile file = bean.getFile();
				
				if (file == null || file.isEmpty()) {
					//no hay archivo que subir :S
					ve.setErrorMessageKey("error.insert.01");
					throw ve;
				} else{
					file.transferTo(new File(filePath+fileName));
				}
				

				mav.addObject(metadataFileName, fileName);
				mav.addObject(metadataTotalFiles, 1);
				
			
		}

			
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
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the posibleSuccessViewMap
	 */
	public Map<String, String> getPosibleSuccessViewMap() {
		return posibleSuccessViewMap;
	}

	/**
	 * @param posibleSuccessViewMap the posibleSuccessViewMap to set
	 */
	public void setPosibleSuccessViewMap(Map<String, String> posibleSuccessViewMap) {
		this.posibleSuccessViewMap = posibleSuccessViewMap;
	}

	/**
	 * @return the posibleFileExtensionMap
	 */
	public Map<String, String> getPosibleFileExtensionMap() {
		return posibleFileExtensionMap;
	}

	/**
	 * @param posibleFileExtensionMap the posibleFileExtensionMap to set
	 */
	public void setPosibleFileExtensionMap(
			Map<String, String> posibleFileExtensionMap) {
		this.posibleFileExtensionMap = posibleFileExtensionMap;
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
	 * @return the metadataFileType
	 */
	public String getMetadataFileType() {
		return metadataFileType;
	}

	/**
	 * @param metadataFileType the metadataFileType to set
	 */
	public void setMetadataFileType(String metadataFileType) {
		this.metadataFileType = metadataFileType;
	}

	/**
	 * @return the metadataTotalFiles
	 */
	public String getMetadataTotalFiles() {
		return metadataTotalFiles;
	}

	/**
	 * @param metadataTotalFiles the metadataTotalFiles to set
	 */
	public void setMetadataTotalFiles(String metadataTotalFiles) {
		this.metadataTotalFiles = metadataTotalFiles;
	}

}

