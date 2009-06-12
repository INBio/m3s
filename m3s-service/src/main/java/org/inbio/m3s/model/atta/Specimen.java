package org.inbio.m3s.model.atta;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author jgutierrez
 *
 */
public class Specimen implements Serializable {

	private static final long serialVersionUID = 1212115617854666568L;

	private Integer specimenId;

	private Gathering gathering;

	private Integer specimenCategoryId;

	private Integer specimenTypeId;

	private Integer storageTypeId;

	private Integer substrateId;

	private Integer originId;

	private Integer preservationMediumId;

	private String discarded;

	private Integer gatheringDetailPersonId;

	private Integer gatheringNumber;

	private Integer morphologicalDescriptionId;

	private Date creationDate;

	private String createdBy;

	private Date lastModificationDate;

	private String lastModificationBy;

	private Integer collectionId;

	private Integer extractionTypeId;

	private Integer numberWhole;

	private Integer numberFragments;

	private String externalSpecimen;

	private Set<Identification> identifications = new HashSet<Identification>(0);

	// private Set specimenMedias = new HashSet(0);

	public Specimen() {
	}

	public Specimen(Integer specimenId, Gathering gathering,
			Integer specimenCategoryId, String discarded, Integer collectionId) {
		this.specimenId = specimenId;
		this.gathering = gathering;
		this.specimenCategoryId = specimenCategoryId;
		this.discarded = discarded;
		this.collectionId = collectionId;
	}

	public Specimen(Integer specimenId, Gathering gathering,
			Integer specimenCategoryId, Integer specimenTypeId,
			Integer storageTypeId, Integer substrateId, Integer originId,
			Integer preservationMediumId, String discarded,
			Integer gatheringDetailPersonId, Integer gatheringNumber,
			Integer morphologicalDescriptionId, Date creationDate,
			String createdBy, Date lastModificationDate,
			String lastModificationBy, Integer collectionId,
			Integer extractionTypeId, Integer numberWhole,
			Integer numberFragments, String externalSpecimen,
			Set<Identification> identifications) {
		// ,Set specimenMedias) {
		this.specimenId = specimenId;
		this.gathering = gathering;
		this.specimenCategoryId = specimenCategoryId;
		this.specimenTypeId = specimenTypeId;
		this.storageTypeId = storageTypeId;
		this.substrateId = substrateId;
		this.originId = originId;
		this.preservationMediumId = preservationMediumId;
		this.discarded = discarded;
		this.gatheringDetailPersonId = gatheringDetailPersonId;
		this.gatheringNumber = gatheringNumber;
		this.morphologicalDescriptionId = morphologicalDescriptionId;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastModificationDate = lastModificationDate;
		this.lastModificationBy = lastModificationBy;
		this.collectionId = collectionId;
		this.extractionTypeId = extractionTypeId;
		this.numberWhole = numberWhole;
		this.numberFragments = numberFragments;
		this.externalSpecimen = externalSpecimen;
		this.identifications = identifications;
		// this.specimenMedias = specimenMedias;
	}

	public Integer getSpecimenId() {
		return this.specimenId;
	}

	public void setSpecimenId(Integer specimenId) {
		this.specimenId = specimenId;
	}

	public Gathering getGathering() {
		return this.gathering;
	}

	public void setGathering(Gathering gathering) {
		this.gathering = gathering;
	}

	public Integer getSpecimenCategoryId() {
		return this.specimenCategoryId;
	}

	public void setSpecimenCategoryId(Integer specimenCategoryId) {
		this.specimenCategoryId = specimenCategoryId;
	}

	public Integer getSpecimenTypeId() {
		return this.specimenTypeId;
	}

	public void setSpecimenTypeId(Integer specimenTypeId) {
		this.specimenTypeId = specimenTypeId;
	}

	public Integer getStorageTypeId() {
		return this.storageTypeId;
	}

	public void setStorageTypeId(Integer storageTypeId) {
		this.storageTypeId = storageTypeId;
	}

	public Integer getSubstrateId() {
		return this.substrateId;
	}

	public void setSubstrateId(Integer substrateId) {
		this.substrateId = substrateId;
	}

	public Integer getOriginId() {
		return this.originId;
	}

	public void setOriginId(Integer originId) {
		this.originId = originId;
	}

	public Integer getPreservationMediumId() {
		return this.preservationMediumId;
	}

	public void setPreservationMediumId(Integer preservationMediumId) {
		this.preservationMediumId = preservationMediumId;
	}

	public String getDiscarded() {
		return this.discarded;
	}

	public void setDiscarded(String discarded) {
		this.discarded = discarded;
	}

	public Integer getGatheringDetailPersonId() {
		return this.gatheringDetailPersonId;
	}

	public void setGatheringDetailPersonId(Integer gatheringDetailPersonId) {
		this.gatheringDetailPersonId = gatheringDetailPersonId;
	}

	public Integer getGatheringNumber() {
		return this.gatheringNumber;
	}

	public void setGatheringNumber(Integer gatheringNumber) {
		this.gatheringNumber = gatheringNumber;
	}

	public Integer getMorphologicalDescriptionId() {
		return this.morphologicalDescriptionId;
	}

	public void setMorphologicalDescriptionId(Integer morphologicalDescriptionId) {
		this.morphologicalDescriptionId = morphologicalDescriptionId;
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

	public Integer getCollectionId() {
		return this.collectionId;
	}

	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}

	public Integer getExtractionTypeId() {
		return this.extractionTypeId;
	}

	public void setExtractionTypeId(Integer extractionTypeId) {
		this.extractionTypeId = extractionTypeId;
	}

	public Integer getNumberWhole() {
		return this.numberWhole;
	}

	public void setNumberWhole(Integer numberWhole) {
		this.numberWhole = numberWhole;
	}

	public Integer getNumberFragments() {
		return this.numberFragments;
	}

	public void setNumberFragments(Integer numberFragments) {
		this.numberFragments = numberFragments;
	}

	public String getExternalSpecimen() {
		return this.externalSpecimen;
	}

	public void setExternalSpecimen(String externalSpecimen) {
		this.externalSpecimen = externalSpecimen;
	}

	public Set<Identification> getIdentifications() {
		return this.identifications;
	}

	public void setIdentifications(Set<Identification> identifications) {
		this.identifications = identifications;
	}

	/*
	 * public Set getSpecimenMedias() { return this.specimenMedias; }
	 * 
	 * public void setSpecimenMedias(Set specimenMedias) { this.specimenMedias =
	 * specimenMedias; }
	 */

}
