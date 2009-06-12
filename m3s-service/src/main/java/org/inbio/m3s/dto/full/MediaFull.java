/**
 * 
 */
package org.inbio.m3s.dto.full;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * @author jgutierrez
 *
 */
public class MediaFull implements Serializable {

	private static final long serialVersionUID = 7102550522670003311L;

	private Integer mediaId;

	private Integer mediaTypeId;

	private Integer usePolicy;

	private Integer authorPersonId;

	private Integer ownerPersonId;

	private Integer ownerInstitutionId;

	private String description;

	private String location;

	private String highResolutionVolume;

	private String highResolutionFileName;

	private Integer siteId;

	private String siteDescription;

	private String title;

	private String series;

	private char isPublic;

	private char isBackup;

	private Integer oldImageFile;

	private Integer oldImageId;

	private String contents;

	private Set<Integer> keywordIds = new HashSet<Integer>(0);

	private Set<Integer> gatheringIds = new HashSet<Integer>(0);

	private Set<Integer> specimenIds = new HashSet<Integer>(0);

	private Set<Integer> mediaAttributeIds = new HashSet<Integer>(0);

	private Set<Integer> projectIds = new HashSet<Integer>(0);

	private Set<Integer> typeSpecimenIds = new HashSet<Integer>(0);

	private Set<Integer> taxonIds = new HashSet<Integer>(0);

	private Set<Integer> observedTaxonIds = new HashSet<Integer>(0);

	private Set<Integer> mediaUseIds = new HashSet<Integer>(0);

	private Integer mediaDateYear;

	private Integer mediaDateMonth;

	private Integer mediaDateDay;

	private Date creationDate;

	private String createdBy;

	private Date lastModificationDate;

	private String lastModificationBy;

	/**
	 * 
	 */
	public MediaFull() {
		super();
	}

	/**
	 * @param mediaId
	 * @param mediaTypeId
	 * @param usePolicy
	 * @param authorPersonId
	 * @param ownerPersonId
	 * @param ownerInstitutionId
	 * @param description
	 * @param location
	 * @param highResolutionVolume
	 * @param highResolutionFileName
	 * @param siteId
	 * @param siteDescription
	 * @param title
	 * @param series
	 * @param isPublic
	 * @param isBackup
	 * @param oldImageFile
	 * @param oldImageId
	 * @param contents
	 * @param keywordIds
	 * @param gatheringIds
	 * @param specimenIds
	 * @param mediaAttributeIds
	 * @param projectIds
	 * @param typeSpecimenIds
	 * @param taxonIds
	 * @param observedTaxonIds
	 * @param mediaUseIds
	 * @param mediaDateYear
	 * @param mediaDateMonth
	 * @param mediaDateDay
	 * @param creationDate
	 * @param createdBy
	 * @param lastModificationDate
	 * @param lastModificationBy
	 */
	public MediaFull(Integer mediaId, Integer mediaTypeId, Integer usePolicy, Integer authorPersonId, Integer ownerPersonId, Integer ownerInstitutionId, String description, String location, String highResolutionVolume, String highResolutionFileName, Integer siteId, String siteDescription, String title, String series, char isPublic, char isBackup, Integer oldImageFile, Integer oldImageId, String contents, Set<Integer> keywordIds, Set<Integer> gatheringIds, Set<Integer> specimenIds, Set<Integer> mediaAttributeIds, Set<Integer> projectIds, Set<Integer> typeSpecimenIds, Set<Integer> taxonIds, Set<Integer> observedTaxonIds, Set<Integer> mediaUseIds, Integer mediaDateYear, Integer mediaDateMonth, Integer mediaDateDay, Date creationDate, String createdBy, Date lastModificationDate, String lastModificationBy) {
		super();
		this.mediaId = mediaId;
		this.mediaTypeId = mediaTypeId;
		this.usePolicy = usePolicy;
		this.authorPersonId = authorPersonId;
		this.ownerPersonId = ownerPersonId;
		this.ownerInstitutionId = ownerInstitutionId;
		this.description = description;
		this.location = location;
		this.highResolutionVolume = highResolutionVolume;
		this.highResolutionFileName = highResolutionFileName;
		this.siteId = siteId;
		this.siteDescription = siteDescription;
		this.title = title;
		this.series = series;
		this.isPublic = isPublic;
		this.isBackup = isBackup;
		this.oldImageFile = oldImageFile;
		this.oldImageId = oldImageId;
		this.contents = contents;
		this.keywordIds = keywordIds;
		this.gatheringIds = gatheringIds;
		this.specimenIds = specimenIds;
		this.mediaAttributeIds = mediaAttributeIds;
		this.projectIds = projectIds;
		this.typeSpecimenIds = typeSpecimenIds;
		this.taxonIds = taxonIds;
		this.observedTaxonIds = observedTaxonIds;
		this.mediaUseIds = mediaUseIds;
		this.mediaDateYear = mediaDateYear;
		this.mediaDateMonth = mediaDateMonth;
		this.mediaDateDay = mediaDateDay;
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
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
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
	 * @return the gatheringIds
	 */
	public Set<Integer> getGatheringIds() {
		return gatheringIds;
	}

	/**
	 * @param gatheringIds the gatheringIds to set
	 */
	public void setGatheringIds(Set<Integer> gatheringIds) {
		this.gatheringIds = gatheringIds;
	}

	/**
	 * @return the highResolutionFileName
	 */
	public String getHighResolutionFileName() {
		return highResolutionFileName;
	}

	/**
	 * @param highResolutionFileName the highResolutionFileName to set
	 */
	public void setHighResolutionFileName(String highResolutionFileName) {
		this.highResolutionFileName = highResolutionFileName;
	}

	/**
	 * @return the highResolutionVolume
	 */
	public String getHighResolutionVolume() {
		return highResolutionVolume;
	}

	/**
	 * @param highResolutionVolume the highResolutionVolume to set
	 */
	public void setHighResolutionVolume(String highResolutionVolume) {
		this.highResolutionVolume = highResolutionVolume;
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
	 * @return the keywordIds
	 */
	public Set<Integer> getKeywordIds() {
		return keywordIds;
	}

	/**
	 * @param keywordIds the keywordIds to set
	 */
	public void setKeywordIds(Set<Integer> keywordIds) {
		this.keywordIds = keywordIds;
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
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the mediaAttributeIds
	 */
	public Set<Integer> getMediaAttributeIds() {
		return mediaAttributeIds;
	}

	/**
	 * @param mediaAttributeIds the mediaAttributeIds to set
	 */
	public void setMediaAttributeIds(Set<Integer> mediaAttributeIds) {
		this.mediaAttributeIds = mediaAttributeIds;
	}

	/**
	 * @return the mediaDateDay
	 */
	public Integer getMediaDateDay() {
		return mediaDateDay;
	}

	/**
	 * @param mediaDateDay the mediaDateDay to set
	 */
	public void setMediaDateDay(Integer mediaDateDay) {
		this.mediaDateDay = mediaDateDay;
	}

	/**
	 * @return the mediaDateMonth
	 */
	public Integer getMediaDateMonth() {
		return mediaDateMonth;
	}

	/**
	 * @param mediaDateMonth the mediaDateMonth to set
	 */
	public void setMediaDateMonth(Integer mediaDateMonth) {
		this.mediaDateMonth = mediaDateMonth;
	}

	/**
	 * @return the mediaDateYear
	 */
	public Integer getMediaDateYear() {
		return mediaDateYear;
	}

	/**
	 * @param mediaDateYear the mediaDateYear to set
	 */
	public void setMediaDateYear(Integer mediaDateYear) {
		this.mediaDateYear = mediaDateYear;
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
	 * @return the mediaUseIds
	 */
	public Set<Integer> getMediaUseIds() {
		return mediaUseIds;
	}

	/**
	 * @param mediaUseIds the mediaUseIds to set
	 */
	public void setMediaUseIds(Set<Integer> mediaUseIds) {
		this.mediaUseIds = mediaUseIds;
	}

	/**
	 * @return the observedTaxonIds
	 */
	public Set<Integer> getObservedTaxonIds() {
		return observedTaxonIds;
	}

	/**
	 * @param observedTaxonIds the observedTaxonIds to set
	 */
	public void setObservedTaxonIds(Set<Integer> observedTaxonIds) {
		this.observedTaxonIds = observedTaxonIds;
	}

	/**
	 * @return the oldImageFile
	 */
	public Integer getOldImageFile() {
		return oldImageFile;
	}

	/**
	 * @param oldImageFile the oldImageFile to set
	 */
	public void setOldImageFile(Integer oldImageFile) {
		this.oldImageFile = oldImageFile;
	}

	/**
	 * @return the oldImageId
	 */
	public Integer getOldImageId() {
		return oldImageId;
	}

	/**
	 * @param oldImageId the oldImageId to set
	 */
	public void setOldImageId(Integer oldImageId) {
		this.oldImageId = oldImageId;
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
	 * @return the projectIds
	 */
	public Set<Integer> getProjectIds() {
		return projectIds;
	}

	/**
	 * @param projectIds the projectIds to set
	 */
	public void setProjectIds(Set<Integer> projectIds) {
		this.projectIds = projectIds;
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
	 * @return the specimenIds
	 */
	public Set<Integer> getSpecimenIds() {
		return specimenIds;
	}

	/**
	 * @param specimenIds the specimenIds to set
	 */
	public void setSpecimenIds(Set<Integer> specimenIds) {
		this.specimenIds = specimenIds;
	}

	/**
	 * @return the taxonIds
	 */
	public Set<Integer> getTaxonIds() {
		return taxonIds;
	}

	/**
	 * @param taxonIds the taxonIds to set
	 */
	public void setTaxonIds(Set<Integer> taxonIds) {
		this.taxonIds = taxonIds;
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
	 * @return the typeSpecimenIds
	 */
	public Set<Integer> getTypeSpecimenIds() {
		return typeSpecimenIds;
	}

	/**
	 * @param typeSpecimenIds the typeSpecimenIds to set
	 */
	public void setTypeSpecimenIds(Set<Integer> typeSpecimenIds) {
		this.typeSpecimenIds = typeSpecimenIds;
	}

	/**
	 * @return the usePolicy
	 */
	public Integer getUsePolicy() {
		return usePolicy;
	}

	/**
	 * @param usePolicy the usePolicy to set
	 */
	public void setUsePolicy(Integer usePolicy) {
		this.usePolicy = usePolicy;
	}

	
}
