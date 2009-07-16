package org.inbio.m3s.model.atta;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Observation implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer observationId;

	private INBioPerson person;

	private INBioSite site;

	private String contents;

	private Integer certaintyLevel;

	private Date observationDate;

	private String surroundingsDescription;

	private String observationSiteDescription;

	private Integer precision;

	private Integer elevationLowerBound;

	private Integer elevationUpperBound;

	private Integer relativeAltitude;

	private Integer samplingTypeId;

	private Integer altimeterElevation;

	private Integer gradientPercentage;

	private Integer expositionId;

	private Integer altitudeFloorId;

	private Date creationDate;

	private String createdBy;

	private Date lastModificationDate;

	private String lastModificationBy;

	private Integer mapElevation;

	private String observationNumber;

	private Integer gpsParameterSequence;

	private Set<ObservedTaxon> observedTaxons = new HashSet<ObservedTaxon>(0);

	public Observation() {
	}

	public Observation(Integer observationId, Date observationDate) {
		this.observationId = observationId;
		this.observationDate = observationDate;
	}

	public Observation(Integer observationId, INBioPerson person, INBioSite site,
			String contents, Integer certaintyLevel, Date observationDate,
			String surroundingsDescription, String observationSiteDescription,
			Integer precision, Integer elevationLowerBound,
			Integer elevationUpperBound, Integer relativeAltitude,
			Integer samplingTypeId, Integer altimeterElevation,
			Integer gradientPercentage, Integer expositionId,
			Integer altitudeFloorId, Date creationDate, String createdBy,
			Date lastModificationDate, String lastModificationBy,
			Integer mapElevation, String observationNumber,
			Integer gpsParameterSequence, Set<ObservedTaxon> observedTaxons) {
		this.observationId = observationId;
		this.person = person;
		this.site = site;
		this.contents = contents;
		this.certaintyLevel = certaintyLevel;
		this.observationDate = observationDate;
		this.surroundingsDescription = surroundingsDescription;
		this.observationSiteDescription = observationSiteDescription;
		this.precision = precision;
		this.elevationLowerBound = elevationLowerBound;
		this.elevationUpperBound = elevationUpperBound;
		this.relativeAltitude = relativeAltitude;
		this.samplingTypeId = samplingTypeId;
		this.altimeterElevation = altimeterElevation;
		this.gradientPercentage = gradientPercentage;
		this.expositionId = expositionId;
		this.altitudeFloorId = altitudeFloorId;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastModificationDate = lastModificationDate;
		this.lastModificationBy = lastModificationBy;
		this.mapElevation = mapElevation;
		this.observationNumber = observationNumber;
		this.gpsParameterSequence = gpsParameterSequence;
		this.observedTaxons = observedTaxons;
	}

	public Integer getObservationId() {
		return this.observationId;
	}

	public void setObservationId(Integer observationId) {
		this.observationId = observationId;
	}

	public INBioPerson getPerson() {
		return this.person;
	}

	public void setPerson(INBioPerson person) {
		this.person = person;
	}

	public INBioSite getSite() {
		return this.site;
	}

	public void setSite(INBioSite site) {
		this.site = site;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Integer getCertaintyLevel() {
		return this.certaintyLevel;
	}

	public void setCertaintyLevel(Integer certaintyLevel) {
		this.certaintyLevel = certaintyLevel;
	}

	public Date getObservationDate() {
		return this.observationDate;
	}

	public void setObservationDate(Date observationDate) {
		this.observationDate = observationDate;
	}

	public String getSurroundingsDescription() {
		return this.surroundingsDescription;
	}

	public void setSurroundingsDescription(String surroundingsDescription) {
		this.surroundingsDescription = surroundingsDescription;
	}

	public String getObservationSiteDescription() {
		return this.observationSiteDescription;
	}

	public void setObservationSiteDescription(String observationSiteDescription) {
		this.observationSiteDescription = observationSiteDescription;
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

	public Integer getRelativeAltitude() {
		return this.relativeAltitude;
	}

	public void setRelativeAltitude(Integer relativeAltitude) {
		this.relativeAltitude = relativeAltitude;
	}

	public Integer getSamplingTypeId() {
		return this.samplingTypeId;
	}

	public void setSamplingTypeId(Integer samplingTypeId) {
		this.samplingTypeId = samplingTypeId;
	}

	public Integer getAltimeterElevation() {
		return this.altimeterElevation;
	}

	public void setAltimeterElevation(Integer altimeterElevation) {
		this.altimeterElevation = altimeterElevation;
	}

	public Integer getGradientPercentage() {
		return this.gradientPercentage;
	}

	public void setGradientPercentage(Integer gradientPercentage) {
		this.gradientPercentage = gradientPercentage;
	}

	public Integer getExpositionId() {
		return this.expositionId;
	}

	public void setExpositionId(Integer expositionId) {
		this.expositionId = expositionId;
	}

	public Integer getAltitudeFloorId() {
		return this.altitudeFloorId;
	}

	public void setAltitudeFloorId(Integer altitudeFloorId) {
		this.altitudeFloorId = altitudeFloorId;
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

	public Integer getMapElevation() {
		return this.mapElevation;
	}

	public void setMapElevation(Integer mapElevation) {
		this.mapElevation = mapElevation;
	}

	public String getObservationNumber() {
		return this.observationNumber;
	}

	public void setObservationNumber(String observationNumber) {
		this.observationNumber = observationNumber;
	}

	public Integer getGpsParameterSequence() {
		return this.gpsParameterSequence;
	}

	public void setGpsParameterSequence(Integer gpsParameterSequence) {
		this.gpsParameterSequence = gpsParameterSequence;
	}

	public Set<ObservedTaxon> getObservedTaxons() {
		return this.observedTaxons;
	}

	public void setObservedTaxons(Set<ObservedTaxon> observedTaxons) {
		this.observedTaxons = observedTaxons;
	}

}
