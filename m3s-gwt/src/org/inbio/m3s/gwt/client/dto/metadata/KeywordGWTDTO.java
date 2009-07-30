/**
 * 
 */
package org.inbio.m3s.gwt.client.dto.metadata;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author jgutierrez
 *
 */
public class KeywordGWTDTO implements IsSerializable {

	private String keywordKey;
	
	private String name;

	/**
	 * 
	 */
	public KeywordGWTDTO() {
	}

	/**
	 * @param keywordKey
	 * @param name
	 */
	public KeywordGWTDTO(String keywordKey, String name) {
		this.setKeywordKey(keywordKey);
		this.setName(name);
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param keywordKey the keywordKey to set
	 */
	public void setKeywordKey(String keywordKey) {
		this.keywordKey = keywordKey;
	}

	/**
	 * @return the keywordKey
	 */
	public String getKeywordKey() {
		return keywordKey;
	}


	
	
	
}
