/**
 * 
 */
package org.inbio.m3s.dto.metadata.util;

/**
 * @author jgutierrez
 *
 */
public enum MediaAtttributeValueTypeEntity {
	
	VARCHAR('V', "Hilera de Caracteres"),
	NUMERIC('N', "Numero"),  
	DATE('D', "Fecha");
	
	private char type;
	private String name;
	
	
	/**
	 * @param type
	 * @param name
	 */
	private MediaAtttributeValueTypeEntity(char type, String name) {
		this.type = type;
		this.name = name;
	}


	/**
	 * @return the type
	 */
	public char getType() {
		return type;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	
	
	
}
