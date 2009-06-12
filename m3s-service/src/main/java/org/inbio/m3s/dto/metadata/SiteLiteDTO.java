/**
 * 
 */
package org.inbio.m3s.dto.metadata;

import java.io.Serializable;

/**
 * @author jgutierrez
 *
 */
public class SiteLiteDTO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8146150872831164148L;

	private String keywordKey;
	
	private String descripcion;
	
	
	/**
	 * 
	 */
	public SiteLiteDTO() {
	}
	
	/**
	 * @param keywordKey
	 * @param descripcion
	 */
	public SiteLiteDTO(String keywordKey, String descripcion) {
		this.keywordKey = keywordKey;
		this.descripcion = descripcion;
	}	
	
	@Override
	public String toString(){
		return "EL Gathering Lite DTO tiene:" +
		"\n\tKey : " + keywordKey +
		"\n\tDescription: '"+ descripcion +"'" +
		"";
	}

	/**
	 * @return the keywordKey
	 */
	public String getKeywordKey() {
		return keywordKey;
	}

	/**
	 * @param keywordKey the keywordKey to set
	 */
	public void setKeywordKey(String keywordKey) {
		this.keywordKey = keywordKey;
	}
}
