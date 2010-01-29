package org.inbio.m3s.web.controller.upload;

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
import org.inbio.m3s.util.ImageMagickAPI;
import org.inbio.m3s.web.controller.metadata.MetadataHandler;
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
public class MultipleImagesUploadController extends SimpleFormController {

	//Constants
	private String filePath; // ${temporalFilesPath}
	protected String fileExtension; //="jpg";
	
	//model & JSP
	private String metadataUsernameKey; //="username"
	private String fileNameKey;//=fileName
	
	private String formActionKey; //="formAction"
	private String formActionValue; //para el siguiente form...
	private String metadataMediaVisibleKey; //multimedio visible
	private String metadataMediaVisibleValue; //="checked"
	
	//in case of error	
	private String errorViewName; //="insertStep1";
	private String errorFormActionKey; //="jpgImagesFormAction"
	private String errorFormActionValue; //="uploadImages.html"
	
	//Managers and handlers
	private MetadataHandler metadataHandler;

	
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
			Object command, BindException errors) throws ServletException, IOException, Exception {
		
		//in case of error just fire this exception
		ValidationException ve = new ValidationException();
		ve.setViewName(errorViewName);
		Map<String,Object> modelElements = new HashMap<String, Object>();
		modelElements.put(errorFormActionKey,errorFormActionValue);
		ve.setModelElements(modelElements);
	
		String fileName ="";
		
		try { 

			ModelAndView mav = new ModelAndView(getSuccessView());

			String userName = request.getParameter(metadataUsernameKey);
			logger.debug("userName: "+userName);
			mav.addObject(metadataUsernameKey, userName);


			// Creates a new HttpSession so the other servlets could identify
			// the file that has been upload
			HttpSession session = request.getSession(true);
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss-");
			java.util.Date date = new java.util.Date();
			String fileId = dateFormat.format(date) + userName +session.getId();
			
			//Archivos de imagenes, pueden ser hasta 6.
				
				// cast the bean
				MultipleFilesUploadBean multipleImagesBean = (MultipleFilesUploadBean) command;
				
				//images Count
				int filesCount = multipleImagesBean.getFilesCount();
				//mav.addObject(totalFilesKey, filesCount);

				
				if(filesCount < 1){
				//no hay archivo que subir :S
					ve.setErrorMessageKey("error.insert.01");
					throw ve;
				}
				
				String systemFileName;
				
				for(MultipartFile file: multipleImagesBean.getFiles()){
					systemFileName = fileId+filesCount+"."+fileExtension;
					file.transferTo(new File(filePath+systemFileName));
				
					try{
							ImageMagickAPI.createThumb(filePath+systemFileName, filePath+"thumb-"+systemFileName);
					}catch (Exception e){
						//no se puede mover el archivo de imagen
						ve.setErrorMessageKey("error.insert.02");
						throw ve;
					}
					fileName += systemFileName+";";
					filesCount--;					
				}
				
				//add the fileNames hashmap
				mav.addObject(fileNameKey,fileName);
				logger.debug(fileNameKey+" "+fileName);
				
				
				//values needed in the next JSP
				
				//mav.addAllObjects(blabla());
				mav.addObject(formActionKey, formActionValue);
				//MediaVisible will be visible by default
				mav.addObject(metadataMediaVisibleKey, metadataMediaVisibleValue);
				
				Map<String,Object> metadataModelValues = new HashMap<String, Object>();
				metadataModelValues = metadataHandler.getMetadata(metadataModelValues);
				mav.addAllObjects(metadataModelValues);
				
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
	 * @return the fileNameKey
	 */
	public String getFileNameKey() {
		return fileNameKey;
	}



	/**
	 * @param fileNameKey the fileNameKey to set
	 */
	public void setFileNameKey(String fileNameKey) {
		this.fileNameKey = fileNameKey;
	}

	/**
	 * @return the formActionKey
	 */
	public String getFormActionKey() {
		return formActionKey;
	}



	/**
	 * @param formActionKey the formActionKey to set
	 */
	public void setFormActionKey(String formActionKey) {
		this.formActionKey = formActionKey;
	}



	/**
	 * @return the formActionValue
	 */
	public String getFormActionValue() {
		return formActionValue;
	}



	/**
	 * @param formActionValue the formActionValue to set
	 */
	public void setFormActionValue(String formActionValue) {
		this.formActionValue = formActionValue;
	}



	/**
	 * @return the metadataMediaVisibleKey
	 */
	public String getMetadataMediaVisibleKey() {
		return metadataMediaVisibleKey;
	}



	/**
	 * @param metadataMediaVisibleKey the metadataMediaVisibleKey to set
	 */
	public void setMetadataMediaVisibleKey(String metadataMediaVisibleKey) {
		this.metadataMediaVisibleKey = metadataMediaVisibleKey;
	}



	/**
	 * @return the metadataMediaVisibleValue
	 */
	public String getMetadataMediaVisibleValue() {
		return metadataMediaVisibleValue;
	}



	/**
	 * @param metadataMediaVisibleValue the metadataMediaVisibleValue to set
	 */
	public void setMetadataMediaVisibleValue(String metadataMediaVisibleValue) {
		this.metadataMediaVisibleValue = metadataMediaVisibleValue;
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
	 * @return the metadataHandler
	 */
	public MetadataHandler getMetadataHandler() {
		return metadataHandler;
	}



	/**
	 * @param metadataHandler the metadataHandler to set
	 */
	public void setMetadataHandler(MetadataHandler metadataHandler) {
		this.metadataHandler = metadataHandler;
	}


}

