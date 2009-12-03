/**
 * 
 */
package org.inbio.m3s.web.controller.metadata;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.dto.message.MediaTypeDTO;
import org.inbio.m3s.dto.metadata.UsePolicyDTO;
import org.inbio.m3s.dto.util.KeyValueDTO;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.web.filter.FilterMapWrapper;

/**
 * Handles the metadata issues! 
 * Other controller don't have to worry of that just relegate.
 * 
 * 
 * @author jgutierrez
 *
 */
public class MetadataHandler {
	
	protected static Log logger = LogFactory.getLog(MetadataHandler.class);
	private MessageManager messageManager;
	
	private String metadataMediaTypes = "mediaTypes";
	private String metadataAssociatedToValues = "associatedToValues";
	private String metadataUsePolicies = "usePolicies";
	/* MediaOwner Widget */
  private FilterMapWrapper mediaOwnerFilters;
  private String mediaOwnerFiltersRequestKey;
  
	/**
	 * Este metodo por ahora SOLO trae los metadatos necesarios para dropdowns y esas varas
	 * 
	 * @param modelElements
	 * @return
	 */
	public Map<String,Object> getMetadata(Map<String,Object> modelElements){
		
	  //Tipos de multimedios
		List<MediaTypeDTO>  mediaTypes = messageManager.getAllMediaTypes();
		modelElements.put(metadataMediaTypes, mediaTypes);
		/* esta linea siguiente establece cual es el valor seleccionado!*/
		//modelElements.put(metadataMediaCategory, gmDTO.getMediaTypeKey());
		
		//Tipos de Asociaciones
		List<KeyValueDTO> associatedToValues = messageManager.getAllAssociatedToValues();
		modelElements.put(metadataAssociatedToValues, associatedToValues);
		
	  //Owner Widget -- usando en el mediaOwner.jsp
		modelElements.put(mediaOwnerFiltersRequestKey, mediaOwnerFilters.getFilters());
		
		//Políticas de Uso
		List<UsePolicyDTO> usePolicies = messageManager.getAllUsePolicies();
		modelElements.put(metadataUsePolicies, usePolicies);
		//mav.addObject(metadataUsePolicy, uacDTO.getUsePolicyKey());
		//logger.debug(metadataUsePolicy+"'"+uacDTO.getUsePolicyKey()+"'");
		
		return modelElements;
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
	 * @return the metadataMediaTypes
	 */
	public String getMetadataMediaTypes() {
		return metadataMediaTypes;
	}

	/**
	 * @param metadataMediaTypes the metadataMediaTypes to set
	 */
	public void setMetadataMediaTypes(String metadataMediaTypes) {
		this.metadataMediaTypes = metadataMediaTypes;
	}

	/**
	 * @return the metadataAssociatedToValues
	 */
	public String getMetadataAssociatedToValues() {
		return metadataAssociatedToValues;
	}

	/**
	 * @param metadataAssociatedToValues the metadataAssociatedToValues to set
	 */
	public void setMetadataAssociatedToValues(String metadataAssociatedToValues) {
		this.metadataAssociatedToValues = metadataAssociatedToValues;
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
	 * @return the metadataUsePolicies
	 */
	public String getMetadataUsePolicies() {
		return metadataUsePolicies;
	}

	/**
	 * @param metadataUsePolicies the metadataUsePolicies to set
	 */
	public void setMetadataUsePolicies(String metadataUsePolicies) {
		this.metadataUsePolicies = metadataUsePolicies;
	}
	
}
