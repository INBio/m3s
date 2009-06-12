/**
 * 
 */
package org.inbio.m3s.dto.taxonomy.util;

/**
 * @author jgutierrez
 *
 */
public enum TaxonomicalRangeEntity {
	
	ROOT(0, "Raiz"),
	KINGDOM(1,"Reino"),
	PHYLUM(2, "Filo/División"),
	CLASS(4, "Clase"),
	ORDER(6,"Orden"),
	FAMILY(9,"Familia"),
	GENUS(13,"Género"),
	SPECIES(19,"Especie");
	

	private int id;
	private String name;
	
	/**
	 * @param id
	 * @param name
	 */
	private TaxonomicalRangeEntity(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
