/**
 * 
 */
package org.inbio.m3s.dto.full;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jgutierrez
 *
 */
public class MetadataStandardFull implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1298412123407332950L;

	private Integer metadataStandardId;

	private String name;

	private String description;

	private String metadataStandardImplementationClass;
	
	private Date creationDate;

	private String createdBy;

	private Date lastModificationDate;

	private String lastModificationBy;

	/**
	 * 
	 */
	public MetadataStandardFull() {
	}

	/**
	 * @param metadataStandardId
	 * @param name
	 * @param description
	 * @param metadataStandardImplementationClass
	 * @param creationDate
	 * @param createdBy
	 * @param lastModificationDate
	 * @param lastModificationBy
	 */
	public MetadataStandardFull(Integer metadataStandardId, String name, String description, String metadataStandardImplementationClass, Date creationDate, String createdBy, Date lastModificationDate, String lastModificationBy) {
		this.metadataStandardId = metadataStandardId;
		this.name = name;
		this.description = description;
		this.metadataStandardImplementationClass = metadataStandardImplementationClass;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastModificationDate = lastModificationDate;
		this.lastModificationBy = lastModificationBy;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the metadataStandardId
	 */
	public Integer getMetadataStandardId() {
		return metadataStandardId;
	}

	/**
	 * @param metadataStandardId the metadataStandardId to set
	 */
	public void setMetadataStandardId(Integer metadataStandardId) {
		this.metadataStandardId = metadataStandardId;
	}

	/**
	 * @return the metadataStandardImplementationClass
	 */
	public String getMetadataStandardImplementationClass() {
		return metadataStandardImplementationClass;
	}

	/**
	 * @param metadataStandardImplementationClass the metadataStandardImplementationClass to set
	 */
	public void setMetadataStandardImplementationClass(
			String metadataStandardImplementationClass) {
		this.metadataStandardImplementationClass = metadataStandardImplementationClass;
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
