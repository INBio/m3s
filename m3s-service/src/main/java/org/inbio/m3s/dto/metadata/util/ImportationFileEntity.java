/**
 * 
 */
package org.inbio.m3s.dto.metadata.util;

/**
 * @author jgutierrez
 *
 */
public enum ImportationFileEntity {

	MS_EXCEL_FILE(0);
		
	
	private int id;
	
	/**
	 */
	private ImportationFileEntity(int id) {
		this.setId(id);
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
	public int getId() {
		return id;
	}
}
