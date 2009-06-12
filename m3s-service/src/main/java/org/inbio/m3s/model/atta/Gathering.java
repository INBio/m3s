package org.inbio.m3s.model.atta;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Gathering implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer gatheringId;

	private Site site;

	private INBioPerson person;

	private Integer gatheringMethodId;

	private Date initialDateTime;

	private Date finalDateTime;

	private String surroundingsDescription;

	private Integer precision;

	private Integer elevationLowerBound;

	private Integer elevationUpperBound;

	private Date creationDate;

	private String createdBy;

	private Date lastModificationDate;

	private String lastModificationBy;

	private Integer relativeAltitude;

	private Integer collectionId;

	private Integer labelId;

	private Integer originalLabelId;

	private Integer samplingTypeId;

	private String gatheringSiteDescription;

	private Set<Specimen> specimens = new HashSet<Specimen>(0);

	public Gathering() {
	}

	public Gathering(Integer gatheringId, Site site, INBioPerson person,
			Date initialDateTime, Date finalDateTime, Integer collectionId) {
		this.gatheringId = gatheringId;
		this.site = site;
		this.person = person;
		this.initialDateTime = initialDateTime;
		this.finalDateTime = finalDateTime;
		this.collectionId = collectionId;
	}

	public Gathering(Integer gatheringId, Site site, INBioPerson person,
			Integer gatheringMethodId, Date initialDateTime,
			Date finalDateTime, String surroundingsDescription,
			Integer precision, Integer elevationLowerBound,
			Integer elevationUpperBound, Date creationDate, String createdBy,
			Date lastModificationDate, String lastModificationBy,
			Integer relativeAltitude, Integer collectionId, Integer labelId,
			Integer originalLabelId, Integer samplingTypeId,
			String gatheringSiteDescription, Set<Specimen> specimens) {
		this.gatheringId = gatheringId;
		this.site = site;
		this.person = person;
		this.gatheringMethodId = gatheringMethodId;
		this.initialDateTime = initialDateTime;
		this.finalDateTime = finalDateTime;
		this.surroundingsDescription = surroundingsDescription;
		this.precision = precision;
		this.elevationLowerBound = elevationLowerBound;
		this.elevationUpperBound = elevationUpperBound;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastModificationDate = lastModificationDate;
		this.lastModificationBy = lastModificationBy;
		this.relativeAltitude = relativeAltitude;
		this.collectionId = collectionId;
		this.labelId = labelId;
		this.originalLabelId = originalLabelId;
		this.samplingTypeId = samplingTypeId;
		this.gatheringSiteDescription = gatheringSiteDescription;
		this.specimens = specimens;
	}

	public Integer getGatheringId() {
		return this.gatheringId;
	}

	public void setGatheringId(Integer gatheringId) {
		this.gatheringId = gatheringId;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public INBioPerson getPerson() {
		return this.person;
	}

	public void setPerson(INBioPerson person) {
		this.person = person;
	}

	public Integer getGatheringMethodId() {
		return this.gatheringMethodId;
	}

	public void setGatheringMethodId(Integer gatheringMethodId) {
		this.gatheringMethodId = gatheringMethodId;
	}

	public Date getInitialDateTime() {
		return this.initialDateTime;
	}

	public void setInitialDateTime(Date initialDateTime) {
		this.initialDateTime = initialDateTime;
	}

	public Date getFinalDateTime() {
		return this.finalDateTime;
	}

	public void setFinalDateTime(Date finalDateTime) {
		this.finalDateTime = finalDateTime;
	}

	public String getSurroundingsDescription() {
		return this.surroundingsDescription;
	}

	public void setSurroundingsDescription(String surroundingsDescription) {
		this.surroundingsDescription = surroundingsDescription;
	}

	public Integer getPrecision() {
		return this.precision;
	}

	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	public Integer getElevationLowerBound() {
		return this.elevationLowerBound;
	}

	public void setElevationLowerBound(Integer elevationLowerBound) {
		this.elevationLowerBound = elevationLowerBound;
	}

	public Integer getElevationUpperBound() {
		return this.elevationUpperBound;
	}

	public void setElevationUpperBound(Integer elevationUpperBound) {
		this.elevationUpperBound = elevationUpperBound;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastModificationDate() {
		return this.lastModificationDate;
	}

	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public String getLastModificationBy() {
		return this.lastModificationBy;
	}

	public void setLastModificationBy(String lastModificationBy) {
		this.lastModificationBy = lastModificationBy;
	}

	public Integer getRelativeAltitude() {
		return this.relativeAltitude;
	}

	public void setRelativeAltitude(Integer relativeAltitude) {
		this.relativeAltitude = relativeAltitude;
	}

	public Integer getCollectionId() {
		return this.collectionId;
	}

	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}

	public Integer getLabelId() {
		return this.labelId;
	}

	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}

	public Integer getOriginalLabelId() {
		return this.originalLabelId;
	}

	public void setOriginalLabelId(Integer originalLabelId) {
		this.originalLabelId = originalLabelId;
	}

	public Integer getSamplingTypeId() {
		return this.samplingTypeId;
	}

	public void setSamplingTypeId(Integer samplingTypeId) {
		this.samplingTypeId = samplingTypeId;
	}

	public String getGatheringSiteDescription() {
		return this.gatheringSiteDescription;
	}

	public void setGatheringSiteDescription(String gatheringSiteDescription) {
		this.gatheringSiteDescription = gatheringSiteDescription;
	}

	public Set<Specimen> getSpecimens() {
		return this.specimens;
	}

	public void setSpecimens(Set<Specimen> specimens) {
		this.specimens = specimens;
	}

}
