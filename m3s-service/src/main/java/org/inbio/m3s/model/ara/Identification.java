package org.inbio.m3s.model.ara;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jgutierrez
 *
 */
public class Identification implements Serializable {

	/** */
	private static final long serialVersionUID = -2458246110813063391L;

	private IdentificationId id;

	private Specimen specimen;
	
	private ARATaxon taxon;

	private Date identificationDate;

	private Integer identificationTypeId;

	private ARAPerson valuerPerson;

	private String dataEntryError;
	
	private Integer objVersion;

	private String createdBy;
	
	private Date creationDate;

	private String lastModificationBy;
	
	private Date lastModificationDate;

	private Integer identificationStatusId;

	/**
	 * 
	 */
	public Identification() {
		super();
	}

	/**
	 * @param id
	 * @param specimen
	 * @param taxon
	 * @param identificationDate
	 * @param objVersion
	 * @param createdBy
	 * @param creationDate
	 * @param lastModificationBy
	 * @param lastModificationDate
	 * @param identificationStatusId
	 */
	public Identification(IdentificationId id, Specimen specimen, ARATaxon taxon, Date identificationDate, Integer objVersion, String createdBy, Date creationDate, String lastModificationBy, Date lastModificationDate, Integer identificationStatusId) {
		super();
		this.id = id;
		this.specimen = specimen;
		this.taxon = taxon;
		this.identificationDate = identificationDate;
		this.objVersion = objVersion;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastModificationBy = lastModificationBy;
		this.lastModificationDate = lastModificationDate;
		this.identificationStatusId = identificationStatusId;
	}

	/**
	 * @param id
	 * @param specimen
	 * @param taxon
	 * @param identificationDate
	 * @param identificationTypeId
	 * @param valuerPerson
	 * @param dataEntryError
	 * @param objVersion
	 * @param createdBy
	 * @param creationDate
	 * @param lastModificationBy
	 * @param lastModificationDate
	 * @param identificationStatusId
	 */
	public Identification(IdentificationId id, Specimen specimen, ARATaxon taxon, Date identificationDate, Integer identificationTypeId, ARAPerson valuerPerson, String dataEntryError, Integer objVersion, String createdBy, Date creationDate, String lastModificationBy, Date lastModificationDate, Integer identificationStatusId) {
		super();
		this.id = id;
		this.specimen = specimen;
		this.taxon = taxon;
		this.identificationDate = identificationDate;
		this.identificationTypeId = identificationTypeId;
		this.valuerPerson = valuerPerson;
		this.dataEntryError = dataEntryError;
		this.objVersion = objVersion;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastModificationBy = lastModificationBy;
		this.lastModificationDate = lastModificationDate;
		this.identificationStatusId = identificationStatusId;
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
	 * @return the dataEntryError
	 */
	public String getDataEntryError() {
		return dataEntryError;
	}

	/**
	 * @param dataEntryError the dataEntryError to set
	 */
	public void setDataEntryError(String dataEntryError) {
		this.dataEntryError = dataEntryError;
	}

	/**
	 * @return the id
	 */
	public IdentificationId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(IdentificationId id) {
		this.id = id;
	}

	/**
	 * @return the identificationDate
	 */
	public Date getIdentificationDate() {
		return identificationDate;
	}

	/**
	 * @param identificationDate the identificationDate to set
	 */
	public void setIdentificationDate(Date identificationDate) {
		this.identificationDate = identificationDate;
	}

	/**
	 * @return the identificationStatusId
	 */
	public Integer getIdentificationStatusId() {
		return identificationStatusId;
	}

	/**
	 * @param identificationStatusId the identificationStatusId to set
	 */
	public void setIdentificationStatusId(Integer identificationStatusId) {
		this.identificationStatusId = identificationStatusId;
	}

	/**
	 * @return the identificationTypeId
	 */
	public Integer getIdentificationTypeId() {
		return identificationTypeId;
	}

	/**
	 * @param identificationTypeId the identificationTypeId to set
	 */
	public void setIdentificationTypeId(Integer identificationTypeId) {
		this.identificationTypeId = identificationTypeId;
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
	 * @return the specimen
	 */
	public Specimen getSpecimen() {
		return specimen;
	}

	/**
	 * @param specimen the specimen to set
	 */
	public void setSpecimen(Specimen specimen) {
		this.specimen = specimen;
	}

	/**
	 * @return the taxon
	 */
	public ARATaxon getTaxon() {
		return taxon;
	}

	/**
	 * @param taxon the taxon to set
	 */
	public void setTaxon(ARATaxon taxon) {
		this.taxon = taxon;
	}

	/**
	 * @return the valuerPerson
	 */
	public ARAPerson getValuerPerson() {
		return valuerPerson;
	}

	/**
	 * @param valuerPerson the valuerPerson to set
	 */
	public void setValuerPerson(ARAPerson valuerPerson) {
		this.valuerPerson = valuerPerson;
	}
	
}
