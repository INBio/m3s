package org.inbio.m3s.web.controller.upload;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.inbio.m3s.MultipleFilesUploadBean;
import org.inbio.m3s.dto.media.BriefMediaOutputDTO;
import org.inbio.m3s.dto.message.MediaTypeDTO;
import org.inbio.m3s.dto.metadata.UsePolicyDTO;
import org.inbio.m3s.dto.util.KeyValueDTO;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.util.ImageMagickAPI;
import org.inbio.m3s.web.controller.metadata.MetadataHandler;
import org.inbio.m3s.web.exception.ValidationException;
import org.inbio.m3s.web.filter.FilterMapWrapper;


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

	private String filePath;

	private String metadataUsername;
	private String metadataTotalFiles;
	private String metadataFileName;
	private MetadataHandler metadataHandler;
	
	
	//del saveImagePageController
	private String viewName;
	private String formActionValue;
	private MessageManager messageManager;
	private String metadataMediaVisible;
	
	/* MediaOwner Widget */
  private FilterMapWrapper mediaOwnerFilters;
  private String mediaOwnerFiltersRequestKey;		
	
	
	//output model objects
	//este será un key a un map de String y String!.
	private String metadataFileNames;

	private String errorViewName ="insertStep1";

	protected String fileExtension;
	

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
			Object command, BindException errors) throws ServletException, IOException, Exception {
		
		//in case of error just fire this exception
		ValidationException ve = new ValidationException();
		ve.setViewName(errorViewName);
		Map<String,Object> modelElements = new HashMap<String, Object>();
		modelElements.put("formAction","uploadImages.html");
		ve.setModelElements(modelElements);
	
		//	
		List<BriefMediaOutputDTO> fileNames = new ArrayList<BriefMediaOutputDTO>();
		String fileName ="";
		
		try { 

			//String fileType = request.getParameter(metadataFileType);
			//logger.debug("fileType = " +fileType);
			ModelAndView mav = new ModelAndView(getSuccessView());

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
				
				// cast the bean
				MultipleFilesUploadBean multipleImagesBean = (MultipleFilesUploadBean) command;
				
				//images Count
				int filesCount = multipleImagesBean.getFilesCount();
				mav.addObject(metadataTotalFiles, filesCount);

				
				if(filesCount < 1){
				//no hay archivo que subir :S
					ve.setErrorMessageKey("error.insert.01");
					throw ve;
				}
				
				
				String userFileName;
				String systemFileName;
				BriefMediaOutputDTO bmoDTO;
				
				for(MultipartFile file: multipleImagesBean.getFiles()){
					userFileName = "imagen #"+filesCount+"."+fileExtension;;
					systemFileName = fileId+filesCount+"."+fileExtension;
					file.transferTo(new File(filePath+systemFileName));
				
					try{
							ImageMagickAPI.createThumb(filePath+systemFileName, filePath+"thumb-"+systemFileName);
					}catch (Exception e){
						//no se puede mover el archivo de imagen
						ve.setErrorMessageKey("error.insert.02");
						throw ve;
					}
					bmoDTO = new BriefMediaOutputDTO(systemFileName,userFileName,null,null,null);
					fileNames.add(bmoDTO);
					fileName += systemFileName+";";
					filesCount--;					
				}
				
				//add the fileNames hashmap
				mav.addObject(metadataFileNames, fileNames);
				mav.addObject(metadataFileName,fileName);
				logger.debug(metadataFileName+" "+fileName);
				
				
				//values needed in the next JSP
				
				//mav.addAllObjects(blabla());
				mav.addObject("formAction", formActionValue);
				//MediaVisible will be visible by default
				mav.addObject(metadataMediaVisible, "checked");
				
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
	
	/**
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	private Map<String,Object> blabla() throws Exception {
		
		Map<String,Object> context = new HashMap<String, Object>();
		
		//ModelAndView mav = new ModelAndView(viewName);
		context.put("formAction", formActionValue);
				
		//Tipos de multimedios
		List<MediaTypeDTO>  mediaTypes = messageManager.getAllMediaTypes();
		context.put("mediaTypes", mediaTypes);
		
		//Tipos de Asociaciones
		List<KeyValueDTO> associatedToValues = messageManager.getAllAssociatedToValues();
		context.put("associatedToValues", associatedToValues);
		
		//Tipos de Dueños de Imágenes
		List<KeyValueDTO> ownerValues = messageManager.getAllMediaOwnerValues();
		context.put("mediaOwners", ownerValues);
		//Owner Widget -- usando en el mediaOwner.jsp
		context.put(mediaOwnerFiltersRequestKey, mediaOwnerFilters.getFilters());
		
		//Políticas de Uso
		List<UsePolicyDTO> usePolicies = messageManager.getAllUsePolicies();
		context.put("usePolicies", usePolicies);
		
		
		//MediaVisible will be visible by default
		context.put(metadataMediaVisible, "checked");
		
		return context;
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
	 * @return the metadataFileNames
	 */
	public String getMetadataFileNames() {
		return metadataFileNames;
	}


	/**
	 * @param metadataFileNames the metadataFileNames to set
	 */
	public void setMetadataFileNames(String metadataFileNames) {
		this.metadataFileNames = metadataFileNames;
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
	 * @return the messageManager
	 */
	public MessageManager getMessageManager() {
		return messageManager;
	}

	/**
	 * @param messageManager the messageManager to set
	 */
	public void setMessageManager(MessageManager messageManager) {
		this.messageManager = messageManager;
	}

	/**
	 * @return the metadataMediaVisible
	 */
	public String getMetadataMediaVisible() {
		return metadataMediaVisible;
	}

	/**
	 * @param metadataMediaVisible the metadataMediaVisible to set
	 */
	public void setMetadataMediaVisible(String metadataMediaVisible) {
		this.metadataMediaVisible = metadataMediaVisible;
	}

	/**
	 * @return the mediaOwnerFilters
	 */
	public FilterMapWrapper getMediaOwnerFilters() {
		return mediaOwnerFilters;
	}

	/**
	 * @param mediaOwnerFilters the mediaOwnerFilters to set
	 */
	public void setMediaOwnerFilters(FilterMapWrapper mediaOwnerFilters) {
		this.mediaOwnerFilters = mediaOwnerFilters;
	}

	/**
	 * @return the mediaOwnerFiltersRequestKey
	 */
	public String getMediaOwnerFiltersRequestKey() {
		return mediaOwnerFiltersRequestKey;
	}

	/**
	 * @param mediaOwnerFiltersRequestKey the mediaOwnerFiltersRequestKey to set
	 */
	public void setMediaOwnerFiltersRequestKey(String mediaOwnerFiltersRequestKey) {
		this.mediaOwnerFiltersRequestKey = mediaOwnerFiltersRequestKey;
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




}

