/**
 * 
 */
package org.inbio.m3s.dto.metadata.util;

/**
 * @author jgutierrez
 *
 */
public enum OwnerEntity {

	INSTITUTION(new Integer(0), "Institucion"),
	PERSON(new Integer(1), "Persona");
		
	
	private Integer id;
	
	private String name;
	
	/**
	 */
	private OwnerEntity(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
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
}
