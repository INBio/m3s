/**
 * 
 */
package org.inbio.m3s.dto.lite;

import java.io.Serializable;
import java.util.Date;

/**
 * @deprecated
 * @author jgutierrez
 *
 */
public class MediaLite implements Serializable{

	private static final long serialVersionUID = 4258439259282403505L;

	private Integer mediaId;

	private Integer mediaTypeId;

	private Integer usePolicyId;

	private Integer authorPersonId;

	private Integer ownerPersonId;

	private Integer ownerInstitutionId;

	private String description;
	
	private Integer siteId;

	private String siteDescription;

	private String title;

	private String series;

	private char isPublic;

	private char isBackup;

	private Date creationDate;

	private String createdBy;

	private Date lastModificationDate;

	private String lastModificationBy;

	/**
	 * 
	 */
	public MediaLite() {
		super();
	}

	/**
	 * @param mediaId
	 * @param mediaTypeId
	 * @param usePolicyId
	 * @param authorPersonId
	 * @param ownerPersonId
	 * @param ownerInstitutionId
	 * @param description
	 * @param siteId
	 * @param siteDescription
	 * @param title
	 * @param series
	 * @param isPublic
	 * @param isBackup
	 * @param creationDate
	 * @param createdBy
	 * @param lastModificationDate
	 * @param lastModificationBy
	 */
	public MediaLite(Integer mediaId, Integer mediaTypeId, Integer usePolicy, Integer authorPersonId, Integer ownerPersonId, Integer ownerInstitutionId, String description, Integer siteId, String siteDescription, String title, String series, char isPublic, char isBackup, Date creationDate, String createdBy, Date lastModificationDate, String lastModificationBy) {
		super();
		this.mediaId = mediaId;
		this.mediaTypeId = mediaTypeId;
		this.usePolicyId = usePolicy;
		this.authorPersonId = authorPersonId;
		this.ownerPersonId = ownerPersonId;
		this.ownerInstitutionId = ownerInstitutionId;
		this.description = description;
		this.siteId = siteId;
		this.siteDescription = siteDescription;
		this.title = title;
		this.series = series;
		this.isPublic = isPublic;
		this.isBackup = isBackup;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastModificationDate = lastModificationDate;
		this.lastModificationBy = lastModificationBy;
	}

	/**
	 * @return the authorPersonId
	 */
	public Integer getAuthorPersonId() {
		return authorPersonId;
	}

	/**
	 * @param authorPersonId the authorPersonId to set
	 */
	public void setAuthorPersonId(Integer authorPersonId) {
		this.authorPersonId = authorPersonId;
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
	 * @return the isBackup
	 */
	public char getIsBackup() {
		return isBackup;
	}

	/**
	 * @param isBackup the isBackup to set
	 */
	public void setIsBackup(char isBackup) {
		this.isBackup = isBackup;
	}

	/**
	 * @return the isPublic
	 */
	public char getIsPublic() {
		return isPublic;
	}

	/**
	 * @param isPublic the isPublic to set
	 */
	public void setIsPublic(char isPublic) {
		this.isPublic = isPublic;
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
	 * @return the mediaId
	 */
	public Integer getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * @return the mediaTypeId
	 */
	public Integer getMediaTypeId() {
		return mediaTypeId;
	}

	/**
	 * @param mediaTypeId the mediaTypeId to set
	 */
	public void setMediaTypeId(Integer mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}

	/**
	 * @return the ownerInstitutionId
	 */
	public Integer getOwnerInstitutionId() {
		return ownerInstitutionId;
	}

	/**
	 * @param ownerInstitutionId the ownerInstitutionId to set
	 */
	public void setOwnerInstitutionId(Integer ownerInstitutionId) {
		this.ownerInstitutionId = ownerInstitutionId;
	}

	/**
	 * @return the ownerPersonId
	 */
	public Integer getOwnerPersonId() {
		return ownerPersonId;
	}

	/**
	 * @param ownerPersonId the ownerPersonId to set
	 */
	public void setOwnerPersonId(Integer ownerPersonId) {
		this.ownerPersonId = ownerPersonId;
	}

	/**
	 * @return the series
	 */
	public String getSeries() {
		return series;
	}

	/**
	 * @param series the series to set
	 */
	public void setSeries(String series) {
		this.series = series;
	}

	/**
	 * @return the siteDescription
	 */
	public String getSiteDescription() {
		return siteDescription;
	}

	/**
	 * @param siteDescription the siteDescription to set
	 */
	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}

	/**
	 * @return the siteId
	 */
	public Integer getSiteId() {
		return siteId;
	}

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the usePolicyId
	 */
	public Integer getUsePolicyId() {
		return usePolicyId;
	}

	/**
	 * @param usePolicyId the usePolicyId to set
	 */
	public void setUsePolicyId(Integer usePolicy) {
		this.usePolicyId = usePolicy;
	}


	
}
