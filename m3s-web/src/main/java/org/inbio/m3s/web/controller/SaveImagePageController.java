/**
 * 
 */
package org.inbio.m3s.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inbio.m3s.dto.message.MediaTypeDTO;
import org.inbio.m3s.dto.metadata.UsePolicyDTO;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.web.filter.FilterMapWrapper;
import org.inbio.m3s.dto.util.KeyValueDTO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @deprecated (2010/01/21)
 * @author jgutierrez
 *
 */
public class SaveImagePageController extends AbstractController{
		
	private MessageManager messageManager;
	
	private String imageCode = "fileName";
	
	private String viewName = "insertStep2"; //se sobre escribe con el valor en el xml
	
	private String formActionValue;
	
	/* Mueve el valor de fileName a mediaId para que despliegue todo bien*/
	//private String metadataFileName;
	//private String metadataMediaId;
	//private String metadataFileNames;
	
	private String metadataMediaVisible;
	
	/* MediaOwner Widget */
  private FilterMapWrapper mediaOwnerFilters;
  private String mediaOwnerFiltersRequestKey;	
	
	public SaveImagePageController(){}

	protected ModelAndView handleRequestInternal(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("formAction", formActionValue);
		
		//String fileName = request.getParameter(metadataFileName);
    //mav.addObject(metadataMediaId,fileName);
    
    //Map<String, String> fileNames = request.getParameter(metadataFileNames);
    //mav.addObject(metadataFileNames, fileNames);
		
		//nombre del archivo que voy a subir 
		//(en realidad es como el código del archivo que voy a subir)
		String filename = request.getParameter(imageCode);
		mav.addObject("fileName", filename);
		
		//Tipos de multimedios
		List<MediaTypeDTO>  mediaTypes = messageManager.getAllMediaTypes();
		mav.addObject("mediaTypes", mediaTypes);
		
		//Tipos de Asociaciones
		List<KeyValueDTO> associatedToValues = messageManager.getAllAssociatedToValues();
		mav.addObject("associatedToValues", associatedToValues);
		
		//Tipos de Dueños de Imágenes
		List<KeyValueDTO> ownerValues = messageManager.getAllMediaOwnerValues();
		mav.addObject("mediaOwners", ownerValues);
		//Owner Widget -- usando en el mediaOwner.jsp
		mav.addObject(mediaOwnerFiltersRequestKey, mediaOwnerFilters.getFilters());
		
		//Políticas de Uso
		List<UsePolicyDTO> usePolicies = messageManager.getAllUsePolicies();
		mav.addObject("usePolicies", usePolicies);
		
		
		//MediaVisible will be visible by default
		mav.addObject(metadataMediaVisible, "checked");
		
		return mav;
	}

	/**
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> blablabla(Map<String,Object> context) throws Exception {
		
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
	
	/**
	 * @param messageManager the messageManager to set
	 */
	public void setMessageManager(MessageManager messageManager) {
		this.messageManager = messageManager;
	}

	/**
	 * @return the messageManager
	 */
	public MessageManager getMessageManager() {
		return messageManager;
	}

	/**
	 * @return the imageCode
	 */
	public String getImageCode() {
		return imageCode;
	}

	/**
	 * @param imageCode the imageCode to set
	 */
	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
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


}
