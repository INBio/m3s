/**
 * 
 */
package org.inbio.m3s.model.ara;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jgutierrez
 *
 */
public class CollectorObserver implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CollectorObserverId id;
	
	private Integer sequence;
	
	private Integer objVersion;
	
	private String createdBy;
	
	private Date creationDate;

	private String lastModificationBy;
	
	private Date lastModificationDate;

	/**
	 * 
	 */
	public CollectorObserver() {
		super();
	}

	/**
	 * @param id
	 * @param sequence
	 * @param objVersion
	 * @param createdBy
	 * @param creationDate
	 * @param lastModificationBy
	 * @param lastModificationDate
	 */
	public CollectorObserver(CollectorObserverId id, Integer sequence, Integer objVersion, String createdBy, Date creationDate, String lastModificationBy, Date lastModificationDate) {
		super();
		this.id = id;
		this.sequence = sequence;
		this.objVersion = objVersion;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastModificationBy = lastModificationBy;
		this.lastModificationDate = lastModificationDate;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the id
	 */
	public CollectorObserverId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(CollectorObserverId id) {
		this.id = id;
	}

	/**
	 * @return the lastModificationBy
	 */
	public String getLastModificationBy() {
		return lastModificationBy;
	}

	/**
	 * @param lastModificationBy the lastModificationBy to set
	 */
	public void setLastModificationBy(String lastModificationBy) {
		this.lastModificationBy = lastModificationBy;
	}

	/**
	 * @return the lastModificationDate
	 */
	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	/**
	 * @param lastModificationDate the lastModificationDate to set
	 */
	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	/**
	 * @return the objVersion
	 */
	public Integer getObjVersion() {
		return objVersion;
	}

	/**
	 * @param objVersion the objVersion to set
	 */
	public void setObjVersion(Integer objVersion) {
		this.objVersion = objVersion;
	}

	/**
	 * @return the sequence
	 */
	public Integer getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
	

}
