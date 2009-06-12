package org.inbio.m3s.model.core;


import java.io.Serializable;
import java.util.Date;

import org.inbio.m3s.model.DBLogEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class Media extends DBLogEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer mediaId;

	private MediaType mediaType;

	private UsePolicy usePolicy;

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

	//private Set<MediaKeyword> mediaKeywords = new HashSet<MediaKeyword>(0);

	//private Set<GatheringMedia> gatheringMedias = new HashSet<GatheringMedia>(0);

	//private Set<SpecimenMedia> specimenMedias = new HashSet<SpecimenMedia>(0);

	//private Set<MediaAttributeValue> mediaAttributeValues = new HashSet<MediaAttributeValue>(0);

	//private Set<MediaProject> mediaProjects = new HashSet<MediaProject>(0);

	//private Set<TypeSpecimenMedia> typeSpecimenMedias = new HashSet<TypeSpecimenMedia>(0);

	//private Set<TaxonMediaDAO> taxonMedias = new HashSet<TaxonMediaDAO>(0);

	//private Set<ObservedTaxonMedia> observedTaxonMedias = new HashSet<ObservedTaxonMedia>(0);

	//private Set<MediaUseMedia> mediaUseMedias = new HashSet<MediaUseMedia>(0);

	private Integer mediaDateYear;

	private Integer mediaDateMonth;

	private Integer mediaDateDay;

	public Media() {
	}

	public Media(UsePolicy usePolicy, MediaType mediaType, char isPublic,
			char isBackup) {
		this.usePolicy = usePolicy;
		this.mediaType = mediaType;
		this.isPublic = isPublic;
		this.isBackup = isBackup;
	}

	public Media(UsePolicy usePolicy, MediaType mediaType,
			Integer authorPersonId, Integer ownerPersonId,
			Integer ownerInstitutionId, String description, String location,
			String highResolutionVolume, String highResolutionFileName,
			Integer siteId, String siteDescription, String title,
			String series, char isPublic, char isBackup, Integer oldImageFile,
			Integer oldImageId, String contents, Date creationDate,
			String createdBy, Date lastModificationDate,
			String lastModificationBy, 
			/*
			Set<MediaKeyword> mediaKeywords,
			Set<GatheringMedia> gatheringMedias,
			Set<SpecimenMedia> specimenMedias,
			Set<MediaAttributeValue> mediaAttributeValues,
			Set<MediaProject> mediaProjects,
			Set<TypeSpecimenMedia> typeSpecimenMedias,
			Set<TaxonMediaDAO> taxonMedias,
			Set<ObservedTaxonMedia> observedTaxonMedias,
			Set<MediaUseMedia> mediaUseMedias,
			*/ 
			Integer mediaDateYear,
			Integer mediaDateMonth, Integer mediaDateDay) {
		this.usePolicy = usePolicy;
		this.mediaType = mediaType;
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
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
		/*
		this.mediaKeywords = mediaKeywords;
		this.gatheringMedias = gatheringMedias;
		this.specimenMedias = specimenMedias;
		this.mediaAttributeValues = mediaAttributeValues;
		this.mediaProjects = mediaProjects;
		this.typeSpecimenMedias = typeSpecimenMedias;
		this.taxonMedias = taxonMedias;
		this.observedTaxonMedias = observedTaxonMedias;
		this.mediaUseMedias = mediaUseMedias;
		*/
		this.mediaDateYear = mediaDateYear;
		this.mediaDateMonth = mediaDateMonth;
		this.mediaDateDay = mediaDateDay;
	}

	public Integer getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	public MediaType getMediaType() {
		return this.mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	public UsePolicy getUsePolicy() {
		return this.usePolicy;
	}

	public void setUsePolicy(UsePolicy usePolicy) {
		this.usePolicy = usePolicy;
	}

	public Integer getAuthorPersonId() {
		return this.authorPersonId;
	}

	public void setAuthorPersonId(Integer authorPersonId) {
		this.authorPersonId = authorPersonId;
	}

	public Integer getOwnerPersonId() {
		return this.ownerPersonId;
	}

	public void setOwnerPersonId(Integer ownerPersonId) {
		this.ownerPersonId = ownerPersonId;
	}

	public Integer getOwnerInstitutionId() {
		return this.ownerInstitutionId;
	}

	public void setOwnerInstitutionId(Integer ownerInstitutionId) {
		this.ownerInstitutionId = ownerInstitutionId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHighResolutionVolume() {
		return this.highResolutionVolume;
	}

	public void setHighResolutionVolume(String highResolutionVolume) {
		this.highResolutionVolume = highResolutionVolume;
	}

	public String getHighResolutionFileName() {
		return this.highResolutionFileName;
	}

	public void setHighResolutionFileName(String highResolutionFileName) {
		this.highResolutionFileName = highResolutionFileName;
	}

	public Integer getSiteId() {
		return this.siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getSiteDescription() {
		return this.siteDescription;
	}

	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSeries() {
		return this.series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public char getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(char isPublic) {
		this.isPublic = isPublic;
	}

	public char getIsBackup() {
		return this.isBackup;
	}

	public void setIsBackup(char isBackup) {
		this.isBackup = isBackup;
	}

	public Integer getOldImageFile() {
		return this.oldImageFile;
	}

	public void setOldImageFile(Integer oldImageFile) {
		this.oldImageFile = oldImageFile;
	}

	public Integer getOldImageId() {
		return this.oldImageId;
	}

	public void setOldImageId(Integer oldImageId) {
		this.oldImageId = oldImageId;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	/**
	 * @param mediaDateYear
	 *            the mediaDateYear to set
	 */
	public void setMediaDateYear(Integer mediaDateYear) {
		this.mediaDateYear = mediaDateYear;
	}

	/**
	 * @return the mediaDateYear
	 */
	public Integer getMediaDateYear() {
		return mediaDateYear;
	}

	/**
	 * @param mediaDateMonth
	 *            the mediaDateMonth to set
	 */
	public void setMediaDateMonth(Integer mediaDateMonth) {
		this.mediaDateMonth = mediaDateMonth;
	}

	/**
	 * @return the mediaDateMonth
	 */
	public Integer getMediaDateMonth() {
		return mediaDateMonth;
	}

	/**
	 * @param mediaDateDay
	 *            the mediaDateDay to set
	 */
	public void setMediaDateDay(Integer mediaDateDay) {
		this.mediaDateDay = mediaDateDay;
	}

	/**
	 * @return the mediaDateDay
	 */
	public Integer getMediaDateDay() {
		return mediaDateDay;
	}
}
