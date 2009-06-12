package org.inbio.m3s.model.atta;

import java.io.Serializable;
import java.util.Date;


public class ObservedTaxon implements Serializable {

	private static final long serialVersionUID = 1L;

	private ObservedTaxonId id;

	private Taxon taxon;

	private Observation observation;

	private Date taxonomicalTimestamp;

	private Integer originId;

	private Integer substrateId;

	private Date creationDate;

	private String createdBy;

	private Date lastModificationDate;

	private String lastModificationBy;

	// private Set observedTaxonMedias = new HashSet(0);

	public ObservedTaxon() {
	}

	public ObservedTaxon(ObservedTaxonId id, Taxon taxon,
			Observation observation, Date taxonomicalTimestamp) {
		this.id = id;
		this.taxon = taxon;
		this.observation = observation;
		this.taxonomicalTimestamp = taxonomicalTimestamp;
	}

	public ObservedTaxon(ObservedTaxonId id, Taxon taxon,
			Observation observation, Date taxonomicalTimestamp,
			Integer originId, Integer substrateId, Date creationDate,
			String createdBy, Date lastModificationDate,
			String lastModificationBy) {
		// Set observedTaxonMedias) {
		this.id = id;
		this.taxon = taxon;
		this.observation = observation;
		this.taxonomicalTimestamp = taxonomicalTimestamp;
		this.originId = originId;
		this.substrateId = substrateId;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastModificationDate = lastModificationDate;
		this.lastModificationBy = lastModificationBy;
		// this.observedTaxonMedias = observedTaxonMedias;
	}

	public ObservedTaxonId getId() {
		return this.id;
	}

	public void setId(ObservedTaxonId id) {
		this.id = id;
	}

	public Taxon getTaxon() {
		return this.taxon;
	}

	public void setTaxon(Taxon taxon) {
		this.taxon = taxon;
	}

	public Observation getObservation() {
		return this.observation;
	}

	public void setObservation(Observation observation) {
		this.observation = observation;
	}

	public Date getTaxonomicalTimestamp() {
		return this.taxonomicalTimestamp;
	}

	public void setTaxonomicalTimestamp(Date taxonomicalTimestamp) {
		this.taxonomicalTimestamp = taxonomicalTimestamp;
	}

	public Integer getOriginId() {
		return this.originId;
	}

	public void setOriginId(Integer originId) {
		this.originId = originId;
	}

	public Integer getSubstrateId() {
		return this.substrateId;
	}

	public void setSubstrateId(Integer substrateId) {
		this.substrateId = substrateId;
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

	/*
	 * public Set getObservedTaxonMedias() { return this.observedTaxonMedias; }
	 * 
	 * public void setObservedTaxonMedias(Set observedTaxonMedias) {
	 * this.observedTaxonMedias = observedTaxonMedias; }
	 */

}
