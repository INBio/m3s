package org.inbio.m3s.dto.metadata;

/**
 * 
 */

import java.util.ArrayList;
import java.util.List;

import org.inbio.m3s.dto.metadata.TechnicalMetadataItemDTO;

/**
 * @author jgutierrez
 * 
 */
public class TechnicalMetadataDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6685922843477293668L;

	private String mediaKey;

	private String mediaTypeKey;
	
	private List<TechnicalMetadataItemDTO> items;

	
	/**
	 * @param mediaKey
	 * @param mediaTypeKey
	 */
	public TechnicalMetadataDTO(String mediaKey, String mediaTypeKey) {
		this.mediaKey = mediaKey;
		this.mediaTypeKey = mediaTypeKey;
		this.items = new ArrayList<TechnicalMetadataItemDTO>();
	}
	
	/**
	 * @param mediaKey
	 * @param mediaTypeKey
	 * @param items
	 */
	public TechnicalMetadataDTO(String mediaKey, String mediaTypeKey,
			List<TechnicalMetadataItemDTO> items) {
		this.mediaKey = mediaKey;
		this.mediaTypeKey = mediaTypeKey;
		this.items = items;
	}

	
	/**
	 * 
	 * @param tmiDTO
	 */
	public void addItem(TechnicalMetadataItemDTO tmiDTO){
		if(items!=null)
			items.add(tmiDTO);
		else 
			throw new NullPointerException();
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
	public List<TechnicalMetadataItemDTO> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<TechnicalMetadataItemDTO> items) {
		this.items = items;
	}
}
