package org.inbio.m3s.model.ara;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * @author jgutierrez
 *
 */
public class Site implements Serializable {
	/** */
	private static final long serialVersionUID = 1L;

	private Integer siteId;

	private String description;

	private Integer baseProjectionId;
	
	private Integer siteCalculationMethodId;
	
	private Integer precision;
	
	private Integer objVersion;

	private String createdBy;
	
	private Date creationDate;

	private String lastModificationBy;

	private Date lastModificationDate;
	
	private String name;
	
	private Integer originalProjectionId;
	
	private Integer geodeticDatum;
	
	private Integer featureTypeId;

	/**
	 * 
	 */
	public Site() {
		super();
	}

	/**
	 * @param siteId
	 * @param description
	 * @param baseProjectionId
	 * @param siteCalculationMethodId
	 * @param objVersion
	 * @param createdBy
	 * @param creationDate
	 * @param lastModificationBy
	 * @param lastModificationDate
	 * @param originalProjectionId
	 * @param featureTypeId
	 */
	public Site(Integer siteId, String description, Integer baseProjectionId, Integer siteCalculationMethodId, Integer objVersion, String createdBy, Date creationDate, String lastModificationBy, Date lastModificationDate, Integer originalProjectionId, Integer featureTypeId) {
		super();
		this.siteId = siteId;
		this.description = description;
		this.baseProjectionId = baseProjectionId;
		this.siteCalculationMethodId = siteCalculationMethodId;
		this.objVersion = objVersion;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastModificationBy = lastModificationBy;
		this.lastModificationDate = lastModificationDate;
		this.originalProjectionId = originalProjectionId;
		this.featureTypeId = featureTypeId;
	}

	/**
	 * @param siteId
	 * @param description
	 * @param baseProjectionId
	 * @param siteCalculationMethodId
	 * @param precision
	 * @param objVersion
	 * @param createdBy
	 * @param creationDate
	 * @param lastModificationBy
	 * @param lastModificationDate
	 * @param name
	 * @param originalProjectionId
	 * @param geodeticDatum
	 * @param featureTypeId
	 */
	public Site(Integer siteId, String description, Integer baseProjectionId, Integer siteCalculationMethodId, Integer precision, Integer objVersion, String createdBy, Date creationDate, String lastModificationBy, Date lastModificationDate, String name, Integer originalProjectionId, Integer geodeticDatum, Integer featureTypeId) {
		super();
		this.siteId = siteId;
		this.description = description;
		this.baseProjectionId = baseProjectionId;
		this.siteCalculationMethodId = siteCalculationMethodId;
		this.precision = precision;
		this.objVersion = objVersion;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastModificationBy = lastModificationBy;
		this.lastModificationDate = lastModificationDate;
		this.name = name;
		this.originalProjectionId = originalProjectionId;
		this.geodeticDatum = geodeticDatum;
		this.featureTypeId = featureTypeId;
	}

	/**
	 * @return the baseProjectionId
	 */
	public Integer getBaseProjectionId() {
		return baseProjectionId;
	}

	/**
	 * @param baseProjectionId the baseProjectionId to set
	 */
	public void setBaseProjectionId(Integer baseProjectionId) {
		this.baseProjectionId = baseProjectionId;
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
	 * @return the featureTypeId
	 */
	public Integer getFeatureTypeId() {
		return featureTypeId;
	}

	/**
	 * @param featureTypeId the featureTypeId to set
	 */
	public void setFeatureTypeId(Integer featureTypeId) {
		this.featureTypeId = featureTypeId;
	}

	/**
	 * @return the geodeticDatum
	 */
	public Integer getGeodeticDatum() {
		return geodeticDatum;
	}

	/**
	 * @param geodeticDatum the geodeticDatum to set
	 */
	public void setGeodeticDatum(Integer geodeticDatum) {
		this.geodeticDatum = geodeticDatum;
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
	 * @return the originalProjectionId
	 */
	public Integer getOriginalProjectionId() {
		return originalProjectionId;
	}

	/**
	 * @param originalProjectionId the originalProjectionId to set
	 */
	public void setOriginalProjectionId(Integer originalProjectionId) {
		this.originalProjectionId = originalProjectionId;
	}

	/**
	 * @return the precision
	 */
	public Integer getPrecision() {
		return precision;
	}

	/**
	 * @param precision the precision to set
	 */
	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	/**
	 * @return the siteCalculationMethodId
	 */
	public Integer getSiteCalculationMethodId() {
		return siteCalculationMethodId;
	}

	/**
	 * @param siteCalculationMethodId the siteCalculationMethodId to set
	 */
	public void setSiteCalculationMethodId(Integer siteCalculationMethodId) {
		this.siteCalculationMethodId = siteCalculationMethodId;
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
}
