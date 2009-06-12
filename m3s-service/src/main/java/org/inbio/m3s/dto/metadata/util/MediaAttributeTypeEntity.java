/**
 * 
 */
package org.inbio.m3s.dto.metadata.util;

/**
 * @author jgutierrez
 *
 * @deprecated
 * @NIUS
 */
public enum MediaAttributeTypeEntity {

	VARCHAR('V'),
	NUMBER('N'),
	DATE('D'),
	PREDEFINED('Y'),
	NOT_PREDEFINED('N');
	
	
	private Character value;
	
	/**
	 * @param value
	 */
	private MediaAttributeTypeEntity(Character value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public Character getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Character value) {
		this.value = value;
	}
}
