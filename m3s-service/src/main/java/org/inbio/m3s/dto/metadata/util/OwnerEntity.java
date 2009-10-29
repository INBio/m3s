/**
 * 
 */
package org.inbio.m3s.dto.metadata.util;

/**
 * @author jgutierrez
 *
 */
public enum OwnerEntity {

	INSTITUTION(new Integer(0), "Institucion", "metadata.owner.insitution"),
	PERSON(new Integer(1), "Persona","metadata.owner.person");
		
	
	private Integer id;
	
	private String name;
	
	private String nameKey;
	
	/**
	 */
	private OwnerEntity(Integer id, String name, String nameKey) {
		this.id = id;
		this.name = name;
		this.nameKey = nameKey;
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

	/**
	 * @param nameKey the nameKey to set
	 */
	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}

	/**
	 * @return the nameKey
	 */
	public String getNameKey() {
		return nameKey;
	}
}
