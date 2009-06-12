package org.inbio.m3s.model.ara;


import java.io.Serializable;
import java.util.Date;


public class Specimen implements Serializable {

	private static final long serialVersionUID = 1212115617854666568L;

	private Integer specimenId;

	private GatheringObservation gatheringObservation;

	private String discarded;
	
	private Integer specimenCategoryId;

	private Integer specimenTypeId;

	private Integer storageTypeId;

	private Integer substrateId;

	private Integer originId;

	private Integer preservationMediumId;

	private Integer morphologicalDescriptionId;
	
	private Integer lifeStageId;
	
	private Integer sexId;
	
	private Integer numberWhole;

	private Integer numberFragments;

	private Integer extractionTypeId;

	private Integer objVersion;
	
	private String createdBy;

	private Date creationDate;

	private String lastModificationBy;	

	private Date lastModificationDate;

	private Integer collectionId;
	
	private String externalSpecimen;

	private Integer gatheringObservationMethodId;
	
	private Integer certaintyLevel;
	
	private Date dateTime;
	
	private Integer gatheringObservationDetailId;

	/**
	 * 
	 */
	public Specimen() {
		super();
	}

	/**
	 * @param specimenId
	 * @param gatheringObservation
	 * @param discarded
	 * @param objVersion
	 * @param createdBy
	 * @param creationDate
	 * @param lastModificationBy
	 * @param lastModificationDate
	 */
	public Specimen(Integer specimenId, GatheringObservation gatheringObservation, String discarded, Integer objVersion, String createdBy, Date creationDate, String lastModificationBy, Date lastModificationDate) {
		super();
		this.specimenId = specimenId;
		this.gatheringObservation = gatheringObservation;
		this.discarded = discarded;
		this.objVersion = objVersion;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastModificationBy = lastModificationBy;
		this.lastModificationDate = lastModificationDate;
	}

	/**
	 * @param specimenId
	 * @param gatheringObservation
	 * @param discarded
	 * @param specimenCategoryId
	 * @param specimenTypeId
	 * @param storageTypeId
	 * @param substrateId
	 * @param originId
	 * @param preservationMediumId
	 * @param morphologicalDescriptionId
	 * @param lifeStageId
	 * @param sexId
	 * @param numberWhole
	 * @param numberFragments
	 * @param extractionTypeId
	 * @param objVersion
	 * @param createdBy
	 * @param creationDate
	 * @param lastModificationBy
	 * @param lastModificationDate
	 * @param collectionId
	 * @param externalSpecimen
	 * @param gatheringObservationMethodId
	 * @param certaintyLevel
	 * @param dateTime
	 * @param gatheringObservationDetailId
	 */
	public Specimen(Integer specimenId, GatheringObservation gatheringObservation, String discarded, Integer specimenCategoryId, Integer specimenTypeId, Integer storageTypeId, Integer substrateId, Integer originId, Integer preservationMediumId, Integer morphologicalDescriptionId, Integer lifeStageId, Integer sexId, Integer numberWhole, Integer numberFragments, Integer extractionTypeId, Integer objVersion, String createdBy, Date creationDate, String lastModificationBy, Date lastModificationDate, Integer collectionId, String externalSpecimen, Integer gatheringObservationMethodId, Integer certaintyLevel, Date dateTime, Integer gatheringObservationDetailId) {
		super();
		this.specimenId = specimenId;
		this.gatheringObservation = gatheringObservation;
		this.discarded = discarded;
		this.specimenCategoryId = specimenCategoryId;
		this.specimenTypeId = specimenTypeId;
		this.storageTypeId = storageTypeId;
		this.substrateId = substrateId;
		this.originId = originId;
		this.preservationMediumId = preservationMediumId;
		this.morphologicalDescriptionId = morphologicalDescriptionId;
		this.lifeStageId = lifeStageId;
		this.sexId = sexId;
		this.numberWhole = numberWhole;
		this.numberFragments = numberFragments;
		this.extractionTypeId = extractionTypeId;
		this.objVersion = objVersion;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastModificationBy = lastModificationBy;
		this.lastModificationDate = lastModificationDate;
		this.collectionId = collectionId;
		this.externalSpecimen = externalSpecimen;
		this.gatheringObservationMethodId = gatheringObservationMethodId;
		this.certaintyLevel = certaintyLevel;
		this.dateTime = dateTime;
		this.gatheringObservationDetailId = gatheringObservationDetailId;
	}

	/**
	 * @return the certaintyLevel
	 */
	public Integer getCertaintyLevel() {
		return certaintyLevel;
	}

	/**
	 * @param certaintyLevel the certaintyLevel to set
	 */
	public void setCertaintyLevel(Integer certaintyLevel) {
		this.certaintyLevel = certaintyLevel;
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
	 * @return the dateTime
	 */
	public Date getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * @return the discarded
	 */
	public String getDiscarded() {
		return discarded;
	}

	/**
	 * @param discarded the discarded to set
	 */
	public void setDiscarded(String discarded) {
		this.discarded = discarded;
	}

	/**
	 * @return the externalSpecimen
	 */
	public String getExternalSpecimen() {
		return externalSpecimen;
	}

	/**
	 * @param externalSpecimen the externalSpecimen to set
	 */
	public void setExternalSpecimen(String externalSpecimen) {
		this.externalSpecimen = externalSpecimen;
	}

	/**
	 * @return the extractionTypeId
	 */
	public Integer getExtractionTypeId() {
		return extractionTypeId;
	}

	/**
	 * @param extractionTypeId the extractionTypeId to set
	 */
	public void setExtractionTypeId(Integer extractionTypeId) {
		this.extractionTypeId = extractionTypeId;
	}

	/**
	 * @return the gatheringObservation
	 */
	public GatheringObservation getGatheringObservation() {
		return gatheringObservation;
	}

	/**
	 * @param gatheringObservation the gatheringObservation to set
	 */
	public void setGatheringObservation(GatheringObservation gatheringObservation) {
		this.gatheringObservation = gatheringObservation;
	}

	/**
	 * @return the gatheringObservationDetailId
	 */
	public Integer getGatheringObservationDetailId() {
		return gatheringObservationDetailId;
	}

	/**
	 * @param gatheringObservationDetailId the gatheringObservationDetailId to set
	 */
	public void setGatheringObservationDetailId(Integer gatheringObservationDetailId) {
		this.gatheringObservationDetailId = gatheringObservationDetailId;
	}

	/**
	 * @return the gatheringObservationMethodId
	 */
	public Integer getGatheringObservationMethodId() {
		return gatheringObservationMethodId;
	}

	/**
	 * @param gatheringObservationMethodId the gatheringObservationMethodId to set
	 */
	public void setGatheringObservationMethodId(Integer gatheringObservationMethodId) {
		this.gatheringObservationMethodId = gatheringObservationMethodId;
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
	 * @return the lifeStageId
	 */
	public Integer getLifeStageId() {
		return lifeStageId;
	}

	/**
	 * @param lifeStageId the lifeStageId to set
	 */
	public void setLifeStageId(Integer lifeStageId) {
		this.lifeStageId = lifeStageId;
	}

	/**
	 * @return the morphologicalDescriptionId
	 */
	public Integer getMorphologicalDescriptionId() {
		return morphologicalDescriptionId;
	}

	/**
	 * @param morphologicalDescriptionId the morphologicalDescriptionId to set
	 */
	public void setMorphologicalDescriptionId(Integer morphologicalDescriptionId) {
		this.morphologicalDescriptionId = morphologicalDescriptionId;
	}

	/**
	 * @return the numberFragments
	 */
	public Integer getNumberFragments() {
		return numberFragments;
	}

	/**
	 * @param numberFragments the numberFragments to set
	 */
	public void setNumberFragments(Integer numberFragments) {
		this.numberFragments = numberFragments;
	}

	/**
	 * @return the numberWhole
	 */
	public Integer getNumberWhole() {
		return numberWhole;
	}

	/**
	 * @param numberWhole the numberWhole to set
	 */
	public void setNumberWhole(Integer numberWhole) {
		this.numberWhole = numberWhole;
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
	 * @return the originId
	 */
	public Integer getOriginId() {
		return originId;
	}

	/**
	 * @param originId the originId to set
	 */
	public void setOriginId(Integer originId) {
		this.originId = originId;
	}

	/**
	 * @return the preservationMediumId
	 */
	public Integer getPreservationMediumId() {
		return preservationMediumId;
	}

	/**
	 * @param preservationMediumId the preservationMediumId to set
	 */
	public void setPreservationMediumId(Integer preservationMediumId) {
		this.preservationMediumId = preservationMediumId;
	}

	/**
	 * @return the sexId
	 */
	public Integer getSexId() {
		return sexId;
	}

	/**
	 * @param sexId the sexId to set
	 */
	public void setSexId(Integer sexId) {
		this.sexId = sexId;
	}

	/**
	 * @return the specimenCategoryId
	 */
	public Integer getSpecimenCategoryId() {
		return specimenCategoryId;
	}

	/**
	 * @param specimenCategoryId the specimenCategoryId to set
	 */
	public void setSpecimenCategoryId(Integer specimenCategoryId) {
		this.specimenCategoryId = specimenCategoryId;
	}

	/**
	 * @return the specimenId
	 */
	public Integer getSpecimenId() {
		return specimenId;
	}

	/**
	 * @param specimenId the specimenId to set
	 */
	public void setSpecimenId(Integer specimenId) {
		this.specimenId = specimenId;
	}

	/**
	 * @return the specimenTypeId
	 */
	public Integer getSpecimenTypeId() {
		return specimenTypeId;
	}

	/**
	 * @param specimenTypeId the specimenTypeId to set
	 */
	public void setSpecimenTypeId(Integer specimenTypeId) {
		this.specimenTypeId = specimenTypeId;
	}

	/**
	 * @return the storageTypeId
	 */
	public Integer getStorageTypeId() {
		return storageTypeId;
	}

	/**
	 * @param storageTypeId the storageTypeId to set
	 */
	public void setStorageTypeId(Integer storageTypeId) {
		this.storageTypeId = storageTypeId;
	}

	/**
	 * @return the substrateId
	 */
	public Integer getSubstrateId() {
		return substrateId;
	}

	/**
	 * @param substrateId the substrateId to set
	 */
	public void setSubstrateId(Integer substrateId) {
		this.substrateId = substrateId;
	}

		
	
}
