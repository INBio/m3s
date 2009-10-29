/**
 * 
 */
package org.inbio.m3s.dto.util;

import org.inbio.m3s.dto.BaseDTO;

/**
 * General purpose DTO that has a Key equivalent to a database ID
 * and a nameKey, that should be an key to be resolved in some messages file.
 * 
 * 
 * @author jgutierrez
 *
 */
public class KeyValueDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*Database ID as a String value*/
	private String key;
	/*NameKey of the item, to be resolved in a messages.properties */
	private String nameKey;

	/**
	 * @param key
	 * @param nameKey
	 */
	public KeyValueDTO(String key, String nameKey) {
		this.key = key;
		this.nameKey = nameKey;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the nameKey
	 */
	public String getNameKey() {
		return nameKey;
	}

	/**
	 * @param nameKey the nameKey to set
	 */
	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}

	
	
}

