package org.inbio.m3s.model.atta;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Site implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer siteId;

	private String description;

	private String longitude;

	private String latitude;

	private Date creationDate;

	private String createdBy;

	private Date lastModificationDate;

	private String lastModificationBy;

	private Integer lambertX;

	private Integer lambertY;

	private String lambertProjection;

	private Integer siteCalculationMethodId;

	private Integer crtmEast;

	private Integer crtmNorth;

	private Integer baseProjectionId;

	private Set<Observation> observations = new HashSet<Observation>(0);

	private Set<Gathering> gatherings = new HashSet<Gathering>(0);

	public Site() {
	}

	public Site(Integer siteId, String longitude, String latitude) {
		this.siteId = siteId;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Site(Integer siteId, String description, String longitude,
			String latitude, Date creationDate, String createdBy,
			Date lastModificationDate, String lastModificationBy,
			Integer lambertX, Integer lambertY, String lambertProjection,
			Integer siteCalculationMethodId, Integer crtmEast,
			Integer crtmNorth, Integer baseProjectionId, Set<Observation> observations,
			Set<Gathering> gatherings) {
		this.siteId = siteId;
		this.description = description;
		this.longitude = longitude;
		this.latitude = latitude;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastModificationDate = lastModificationDate;
		this.lastModificationBy = lastModificationBy;
		this.lambertX = lambertX;
		this.lambertY = lambertY;
		this.lambertProjection = lambertProjection;
		this.siteCalculationMethodId = siteCalculationMethodId;
		this.crtmEast = crtmEast;
		this.crtmNorth = crtmNorth;
		this.baseProjectionId = baseProjectionId;
		this.observations = observations;
		this.gatherings = gatherings;
	}

	public Integer getSiteId() {
		return this.siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
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

	public Integer getLambertX() {
		return this.lambertX;
	}

	public void setLambertX(Integer lambertX) {
		this.lambertX = lambertX;
	}

	public Integer getLambertY() {
		return this.lambertY;
	}

	public void setLambertY(Integer lambertY) {
		this.lambertY = lambertY;
	}

	public String getLambertProjection() {
		return this.lambertProjection;
	}

	public void setLambertProjection(String lambertProjection) {
		this.lambertProjection = lambertProjection;
	}

	public Integer getSiteCalculationMethodId() {
		return this.siteCalculationMethodId;
	}

	public void setSiteCalculationMethodId(Integer siteCalculationMethodId) {
		this.siteCalculationMethodId = siteCalculationMethodId;
	}

	public Integer getCrtmEast() {
		return this.crtmEast;
	}

	public void setCrtmEast(Integer crtmEast) {
		this.crtmEast = crtmEast;
	}

	public Integer getCrtmNorth() {
		return this.crtmNorth;
	}

	public void setCrtmNorth(Integer crtmNorth) {
		this.crtmNorth = crtmNorth;
	}

	public Integer getBaseProjectionId() {
		return this.baseProjectionId;
	}

	public void setBaseProjectionId(Integer baseProjectionId) {
		this.baseProjectionId = baseProjectionId;
	}

	public Set<Observation> getObservations() {
		return this.observations;
	}

	public void setObservations(Set<Observation> observations) {
		this.observations = observations;
	}

	public Set<Gathering> getGatherings() {
		return this.gatherings;
	}

	public void setGatherings(Set<Gathering> gatherings) {
		this.gatherings = gatherings;
	}

}
