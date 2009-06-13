/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.metadata.dto;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * This object is done with two lists the textual value the represents the name
 * of attribute and the textual value
 * 
 * @author jgutierrez
 * 
 */
public class TechnicalMetadataTV implements IsSerializable {

	private Integer mediaId;

	private String mediaType;

	private List<String> names;

	private List<String> values;


	
	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	public Integer getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaType
	 *            the mediaType to set
	 */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	/**
	 * @return the mediaType
	 */
	public String getMediaType() {
		return mediaType;
	}

	/**
	 * @param names
	 *            the names to set
	 */
	public void setNames(List<String> names) {
		this.names = names;
	}

	/**
	 * @return the names
	 */
	public List<String> getNames() {
		return names;
	}

	/**
	 * @param values
	 *            the values to set
	 */
	public void setValues(List<String> values) {
		this.values = values;
	}

	/**
	 * @return the values
	 */
	public List<String> getValues() {
		return values;
	}

}
