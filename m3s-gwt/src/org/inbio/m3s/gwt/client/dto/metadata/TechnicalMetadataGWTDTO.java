package org.inbio.m3s.gwt.client.dto.metadata;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author jgutierrez
 * 
 */
public class TechnicalMetadataGWTDTO implements IsSerializable {



	private String mediaKey;

	private String mediaTypeKey;
	
	private List<TechnicalMetadataItemGWTDTO> items;

	/**
	 * 
	 */
	public TechnicalMetadataGWTDTO() {
	}
	
	/**
	 * @param mediaKey
	 * @param mediaTypeKey
	 */
	public TechnicalMetadataGWTDTO(String mediaKey, String mediaTypeKey) {
		this.mediaKey = mediaKey;
		this.mediaTypeKey = mediaTypeKey;
		this.items = new ArrayList<TechnicalMetadataItemGWTDTO>();
	}
	
	/**
	 * @param mediaKey
	 * @param mediaTypeKey
	 * @param items
	 */
	public TechnicalMetadataGWTDTO(String mediaKey, String mediaTypeKey,
			List<TechnicalMetadataItemGWTDTO> items) {
		this.mediaKey = mediaKey;
		this.mediaTypeKey = mediaTypeKey;
		this.items = items;
	}

	
	/**
	 * 
	 * @param tmiDTO
	 */
	public void addItem(TechnicalMetadataItemGWTDTO tmiDTO){
		if(items!=null)
			items.add(tmiDTO);
		else
			throw new IllegalArgumentException("no se ha inicializado.");

	}
	

	@Override
	public String toString(){
		String result = "El Technical Metadata DTO tiene:" +
				"\n\tMediaId: " + this.getMediaKey() +
				"\n\tMediaTypeId: " + this.getMediaTypeKey() +
				"\n\tTotal de Items: " + this.items.size();
		for(TechnicalMetadataItemGWTDTO tmiDTO : this.getItems()){
			result = result + "\n\t["+tmiDTO.getMediaAttributeKey()+"] "
				+tmiDTO.getMediaAttributeName()+" = "+tmiDTO.getValue();
		}
			
		
		return result;
	}
	
	/**
	 * @return the mediaKey
	 */
	public String getMediaKey() {
		return mediaKey;
	}

	/**
	 * @param mediaKey the mediaKey to set
	 */
	public void setMediaKey(String mediaKey) {
		this.mediaKey = mediaKey;
	}

	/**
	 * @return the mediaTypeKey
	 */
	public String getMediaTypeKey() {
		return mediaTypeKey;
	}

	/**
	 * @param mediaTypeKey the mediaTypeKey to set
	 */
	public void setMediaTypeKey(String mediaTypeKey) {
		this.mediaTypeKey = mediaTypeKey;
	}

	/**
	 * @return the items
	 */
	public List<TechnicalMetadataItemGWTDTO> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<TechnicalMetadataItemGWTDTO> items) {
		this.items = items;
	}
}
