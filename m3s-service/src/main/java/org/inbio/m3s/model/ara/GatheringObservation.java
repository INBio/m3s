package org.inbio.m3s.model.ara;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jgutierrez
 *
 */
public class GatheringObservation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer gatheringObservationId;

	private Site site;

	private ARAPerson responsiblePerson;

	private Date initialDate;

	private Date finalDate;
	
	private String surroundingsDescription;

	private Integer minimumElevation;

	private Integer maximumElevation;

	private Integer collectionId;
	
	private Integer labelId;
	
	private Integer originalLabelId;
	
	private String siteDescription;
	
	private Integer samplingTypeId;
	
	private Integer gradientPercentage;
	
	private Integer objVersion;
	
	private String createdBy;
	
	private Date creationDate;

	private String lastModificationBy;
	
	private Date lastModificationDate;	

	private Integer minimumDepth;
	
	private Integer maximumDepth;
	
	private Integer expositionId;

	/**
	 * 
	 */
	public GatheringObservation() {
		super();
	}

	/**
	 * @param gatheringObservationId
	 * @param site
	 * @param responsiblePerson
	 * @param initialDate
	 * @param finalDate
	 * @param collectionId
	 * @param objVersion
	 * @param createdBy
	 * @param creationDate
	 * @param lastModificationBy
	 * @param lastModificationDate
	 */
	public GatheringObservation(Integer gatheringObservationId, Site site, ARAPerson responsiblePerson, Date initialDate, Date finalDate, Integer collectionId, Integer objVersion, String createdBy, Date creationDate, String lastModificationBy, Date lastModificationDate) {
		super();
		this.gatheringObservationId = gatheringObservationId;
		this.site = site;
		this.responsiblePerson = responsiblePerson;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.collectionId = collectionId;
		this.objVersion = objVersion;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastModificationBy = lastModificationBy;
		this.lastModificationDate = lastModificationDate;
	}

	/**
	 * @param gatheringObservationId
	 * @param site
	 * @param responsiblePerson
	 * @param initialDate
	 * @param finalDate
	 * @param surroundingsDescription
	 * @param minimumElevation
	 * @param maximumElevation
	 * @param collectionId
	 * @param labelId
	 * @param originalLabelId
	 * @param siteDescription
	 * @param samplingTypeId
	 * @param gradientPercentage
	 * @param objVersion
	 * @param createdBy
	 * @param creationDate
	 * @param lastModificationBy
	 * @param lastModificationDate
	 * @param minimumDepth
	 * @param maximumDepth
	 * @param expositionId
	 */
	public GatheringObservation(Integer gatheringObservationId, Site site, ARAPerson responsiblePerson, Date initialDate, Date finalDate, String surroundingsDescription, Integer minimumElevation, Integer maximumElevation, Integer collectionId, Integer labelId, Integer originalLabelId, String siteDescription, Integer samplingTypeId, Integer gradientPercentage, Integer objVersion, String createdBy, Date creationDate, String lastModificationBy, Date lastModificationDate, Integer minimumDepth, Integer maximumDepth, Integer expositionId) {
		super();
		this.gatheringObservationId = gatheringObservationId;
		this.site = site;
		this.responsiblePerson = responsiblePerson;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.surroundingsDescription = surroundingsDescription;
		this.minimumElevation = minimumElevation;
		this.maximumElevation = maximumElevation;
		this.collectionId = collectionId;
		this.labelId = labelId;
		this.originalLabelId = originalLabelId;
		this.siteDescription = siteDescription;
		this.samplingTypeId = samplingTypeId;
		this.gradientPercentage = gradientPercentage;
		this.objVersion = objVersion;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastModificationBy = lastModificationBy;
		this.lastModificationDate = lastModificationDate;
		this.minimumDepth = minimumDepth;
		this.maximumDepth = maximumDepth;
		this.expositionId = expositionId;
	}

	/**
	 * @return the collectionId
	 */
	public Integer getCollectionId() {
		return collectionId;
	}

	/**
	 * @param collectionId the collectionId to set
	 */
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
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
	 * @return the expositionId
	 */
	public Integer getExpositionId() {
		return expositionId;
	}

	/**
	 * @param expositionId the expositionId to set
	 */
	public void setExpositionId(Integer expositionId) {
		this.expositionId = expositionId;
	}

	/**
	 * @return the finalDate
	 */
	public Date getFinalDate() {
		return finalDate;
	}

	/**
	 * @param finalDate the finalDate to set
	 */
	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	/**
	 * @return the gatheringObservationId
	 */
	public Integer getGatheringObservationId() {
		return gatheringObservationId;
	}

	/**
	 * @param gatheringObservationId the gatheringObservationId to set
	 */
	public void setGatheringObservationId(Integer gatheringObservationId) {
		this.gatheringObservationId = gatheringObservationId;
	}

	/**
	 * @return the gradientPercentage
	 */
	public Integer getGradientPercentage() {
		return gradientPercentage;
	}

	/**
	 * @param gradientPercentage the gradientPercentage to set
	 */
	public void setGradientPercentage(Integer gradientPercentage) {
		this.gradientPercentage = gradientPercentage;
	}

	/**
	 * @return the initialDate
	 */
	public Date getInitialDate() {
		return initialDate;
	}

	/**
	 * @param initialDate the initialDate to set
	 */
	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	/**
	 * @return the labelId
	 */
	public Integer getLabelId() {
		return labelId;
	}

	/**
	 * @param labelId the labelId to set
	 */
	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
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
	 * @return the maximumDepth
	 */
	public Integer getMaximumDepth() {
		return maximumDepth;
	}

	/**
	 * @param maximumDepth the maximumDepth to set
	 */
	public void setMaximumDepth(Integer maximumDepth) {
		this.maximumDepth = maximumDepth;
	}

	/**
	 * @return the maximumElevation
	 */
	public Integer getMaximumElevation() {
		return maximumElevation;
	}

	/**
	 * @param maximumElevation the maximumElevation to set
	 */
	public void setMaximumElevation(Integer maximumElevation) {
		this.maximumElevation = maximumElevation;
	}

	/**
	 * @return the minimumDepth
	 */
	public Integer getMinimumDepth() {
		return minimumDepth;
	}

	/**
	 * @param minimumDepth the minimumDepth to set
	 */
	public void setMinimumDepth(Integer minimumDepth) {
		this.minimumDepth = minimumDepth;
	}

	/**
	 * @return the minimumElevation
	 */
	public Integer getMinimumElevation() {
		return minimumElevation;
	}

	/**
	 * @param minimumElevation the minimumElevation to set
	 */
	public void setMinimumElevation(Integer minimumElevation) {
		this.minimumElevation = minimumElevation;
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
	 * @return the originalLabelId
	 */
	public Integer getOriginalLabelId() {
		return originalLabelId;
	}

	/**
	 * @param originalLabelId the originalLabelId to set
	 */
	public void setOriginalLabelId(Integer originalLabelId) {
		this.originalLabelId = originalLabelId;
	}

	/**
	 * @return the responsiblePerson
	 */
	public ARAPerson getResponsiblePerson() {
		return responsiblePerson;
	}

	/**
	 * @param responsiblePerson the responsiblePerson to set
	 */
	public void setResponsiblePerson(ARAPerson responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	/**
	 * @return the samplingTypeId
	 */
	public Integer getSamplingTypeId() {
		return samplingTypeId;
	}

	/**
	 * @param samplingTypeId the samplingTypeId to set
	 */
	public void setSamplingTypeId(Integer samplingTypeId) {
		this.samplingTypeId = samplingTypeId;
	}

	/**
	 * @return the site
	 */
	public Site getSite() {
		return site;
	}

	/**
	 * @param site the site to set
	 */
	public void setSite(Site site) {
		this.site = site;
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
	 * @return the surroundingsDescription
	 */
	public String getSurroundingsDescription() {
		return surroundingsDescription;
	}

	/**
	 * @param surroundingsDescription the surroundingsDescription to set
	 */
	public void setSurroundingsDescription(String surroundingsDescription) {
		this.surroundingsDescription = surroundingsDescription;
	}

	//private Set specimens = new HashSet(0);

}
