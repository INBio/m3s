/**
 * 
 */
package org.inbio.m3s.dto.message;


import java.io.Serializable;
/**
 * @author jgutierrez
 *
 */
public class KeywordLiteDTO  implements Serializable {

	private static final long serialVersionUID = -7969495353905529327L;

	private String keywordKey;
	
	private String name;

	/**
	 * 
	 */
	public KeywordLiteDTO(){
		super();
	}
	
	/**
	 * @param keywordKey
	 * @param name
	 */
	public KeywordLiteDTO(String keywordKey, String name) {
		super();
		this.setKeywordKey(keywordKey);
		this.name = name;
	}
	
	/**
	 * @param keywordId
	 * @param name
	 */
	public KeywordLiteDTO(Integer keywordId, String name) {
		super();
		this.setKeywordKey(keywordId.toString());
		this.name = name;
	}
	
	@Override
	public String toString(){
		return "EL Keyword Lite DTO tiene:" +
		"\n\tKey: " + keywordKey +
		"\n\tNombre: '"+ name +"'" +
		"";
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

