/**
 * 
 */
package org.inbio.m3s.dto.metadata.util;

/**
 * @author jgutierrez
 * @deprecated
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
	
	private MediaAttributeTypeEntity(char value) {
		this.value = new Character(value);
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
	
	public boolean equals(char mediaAttributeType){
		return this.value.charValue() == mediaAttributeType;
	}
	
}
