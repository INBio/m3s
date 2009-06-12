/**
 * 
 */
package org.inbio.m3s.dto.metadata.util;

/**
 * @author jgutierrez
 *
 */
public enum AssociatedToEntity {

	SPECIMEN_NUMBER(new Integer(0)),
	OBSERVATION_NUMBER(new Integer(1)),
	GATHERING_CODE(new Integer(2)),
	NO_ASSOCIATION(new Integer(3));
		
	
	private Integer id;
	
	/**
	 */
	private AssociatedToEntity(Integer id) {
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
	public Integer getId() {
		return id;
	}
}
