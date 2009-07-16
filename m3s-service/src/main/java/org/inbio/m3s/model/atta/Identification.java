package org.inbio.m3s.model.atta;


import java.io.Serializable;
import java.util.Date;

public class Identification implements Serializable {

	/** */
	private static final long serialVersionUID = -2458246110813063391L;

	private IdentificationId id;

	private INBioTaxon taxon;

	private INBioSpecimen specimen;

	private INBioPerson person;

	private Date identificationDate;

	private Integer identificationTypeId;

	private String identificationStatus;

	private Date creationDate;

	private String createdBy;

	private Date lastModificationDate;

	private String lastModificationBy;

	private Integer dataEntryError;

	public Identification() {
	}

	public Identification(IdentificationId id, INBioTaxon taxon, INBioSpecimen specimen,
			Date identificationDate, String identificationStatus) {
		this.id = id;
		this.taxon = taxon;
		this.specimen = specimen;
		this.identificationDate = identificationDate;
		this.identificationStatus = identificationStatus;
	}

	public Identification(IdentificationId id, INBioTaxon taxon, INBioSpecimen specimen,
			INBioPerson person, Date identificationDate,
			Integer identificationTypeId, String identificationStatus,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy, Integer dataEntryError) {
		this.id = id;
		this.taxon = taxon;
		this.specimen = specimen;
		this.person = person;
		this.identificationDate = identificationDate;
		this.identificationTypeId = identificationTypeId;
		this.identificationStatus = identificationStatus;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastModificationDate = lastModificationDate;
		this.lastModificationBy = lastModificationBy;
		this.dataEntryError = dataEntryError;
	}

	public IdentificationId getId() {
		return this.id;
	}

	public void setId(IdentificationId id) {
		this.id = id;
	}

	public INBioTaxon getTaxon() {
		return this.taxon;
	}

	public void setTaxon(INBioTaxon taxon) {
		this.taxon = taxon;
	}

	public INBioSpecimen getSpecimen() {
		return this.specimen;
	}

	public void setSpecimen(INBioSpecimen specimen) {
		this.specimen = specimen;
	}

	public INBioPerson getPerson() {
		return this.person;
	}

	public void setPerson(INBioPerson person) {
		this.person = person;
	}

	public Date getIdentificationDate() {
		return this.identificationDate;
	}

	public void setIdentificationDate(Date identificationDate) {
		this.identificationDate = identificationDate;
	}

	public Integer getIdentificationTypeId() {
		return this.identificationTypeId;
	}

	public void setIdentificationTypeId(Integer identificationTypeId) {
		this.identificationTypeId = identificationTypeId;
	}

	public String getIdentificationStatus() {
		return this.identificationStatus;
	}

	public void setIdentificationStatus(String identificationStatus) {
		this.identificationStatus = identificationStatus;
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

	public Integer getDataEntryError() {
		return this.dataEntryError;
	}

	public void setDataEntryError(Integer dataEntryError) {
		this.dataEntryError = dataEntryError;
	}

}
