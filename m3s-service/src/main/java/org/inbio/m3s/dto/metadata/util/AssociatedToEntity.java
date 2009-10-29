/**
 * 
 */
package org.inbio.m3s.dto.metadata.util;

/**
 * @author jgutierrez
 *
 */
public enum AssociatedToEntity {

	SPECIMEN_NUMBER(new Integer(0), "metadata.associated.to.specimen"),
	OBSERVATION_NUMBER(new Integer(1),"metadata.associated.to.observation"),
	GATHERING_CODE(new Integer(2),"metadata.associated.to.gathering"),
	NO_ASSOCIATION(new Integer(3),"metadata.associated.to.nothing");
		
	
	private Integer id;
	private String nameKey;

	
	/**
	 */
	private AssociatedToEntity(Integer id, String nameKey) {
		this.setId(id);
		this.setNameKey(nameKey);
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
