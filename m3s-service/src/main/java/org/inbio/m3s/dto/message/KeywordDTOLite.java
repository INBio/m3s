/**
 * 
 */
package org.inbio.m3s.dto.message;


import java.io.Serializable;
/**
 * @author jgutierrez
 * @deprecated
 */
public class KeywordDTOLite  implements Serializable {

	private static final long serialVersionUID = -7969495353905529327L;

	private Integer keywordId;
	
	private String name;

	/**
	 * 
	 */
	public KeywordDTOLite(){
		super();
	}
	
	/**
	 * @param keywordId
	 * @param name
	 */
	public KeywordDTOLite(Integer keywordId, String name) {
		super();
		this.keywordId = keywordId;
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the keywordId
	 */
	public Integer getKeywordId() {
		return keywordId;
	}

	/**
	 * @param keywordId the keywordId to set
	 */
	public void setKeywordId(Integer keywordId) {
		this.keywordId = keywordId;
	}



}

