/**
 * 
 */
package org.inbio.m3s.dto.search;

/**
 * @author jgutierrez
 *
 */
public enum SearchFilterEntity {
	
	MEDIA_ID(0, "Id de Imágen"),
	TAXON(1, "Taxón"),
	AUTHOR(2,"Autor"),
	KEYWORD(3,"Palabra Clave"),
	PROJECT(4,"Proyecto"),
	FAMILY(5,"Familia"),
	GENUS(6,"Genero"),
	SPECIES(7,"Specie");
	
	private int id;
	
	private String keyword;
	
	public static String getKeywordById(int searchFilterId){
		SearchFilterEntity[] values = SearchFilterEntity.values();
		
		for(SearchFilterEntity sfe: values){
			if(sfe.getId() == searchFilterId)				
				return sfe.getKeyword();
		}
			
		return null;
	}
	

	/**
	 * @param id
	 * @param keyword
	 */
	private SearchFilterEntity(int id, String keyword) {
		this.id = id;
		this.keyword = keyword;
	}


	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}


	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
	
}
