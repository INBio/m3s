package org.inbio.m3s.model.ara;


import java.io.Serializable;
import java.util.Date;


/**
 * 
 * @author jgutierrez
 *
 */
public class Taxon implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer taxonId;
	
	private Integer taxonomicalRangeId;	

	private String currentName;

	private Date currentNameTimestamp;
	
	private String defaultName;

	private Date currentPredecessorTimestamp;

	private Integer taxonCategoryId;
	
	private Taxon dominum;
	
	private Taxon kingdom;
	
	private Taxon phylumDivision;
	
	private Taxon subPhylumSubDivision;
	
	private Taxon classRank;
	
	private Taxon subClass;
	
	private Taxon order;
	
	private Taxon subOrder;
	
	private Taxon superFamily;
	
	private Taxon family;
	
	private Taxon subFamily;
	
	private Taxon tribe;
	
	private Taxon subTribe;
	
	private Taxon genus;
	
	private Taxon subGenus;
	
	private Taxon section;
	
	private Taxon subSection;
	
	private Taxon stirps;
	
	private Taxon species;
	
	private Taxon subSpecies;
	
	private Taxon variety;

	private Integer descriptionMonth;

	private Integer descriptionYear;
	
	private Taxon synonym;
	
	private Integer authorFormatParenthesis;

	private Date creationDate;

	private String createdBy;

	private Date lastModificationDate;

	private String lastModificationBy;

	private Integer collectionId;
	
	private Taxon ancestorTaxon;

	private String basionym;

	private Integer countryId;
	
	private Integer objVersion;
	
	private Taxon subKingdom;
	
	private Taxon infraKingdom;
	
	private Taxon superPhylum;
	
	private Taxon superClass;
	
	private Taxon superOrder;

	/**
	 * 
	 */
	public Taxon() {
		super();
	}

	/**
	 * @param taxonId
	 * @param taxonomicalRangeId
	 * @param currentName
	 * @param currentNameTimestamp
	 * @param defaultName
	 * @param taxonCategoryId
	 * @param authorFormatParenthesis
	 * @param objVersion
	 */
	public Taxon(Integer taxonId, Integer taxonomicalRangeId, String currentName, Date currentNameTimestamp, String defaultName, Integer taxonCategoryId, Integer authorFormatParenthesis, Integer objVersion) {
		super();
		this.taxonId = taxonId;
		this.taxonomicalRangeId = taxonomicalRangeId;
		this.currentName = currentName;
		this.currentNameTimestamp = currentNameTimestamp;
		this.defaultName = defaultName;
		this.taxonCategoryId = taxonCategoryId;
		this.authorFormatParenthesis = authorFormatParenthesis;
		this.objVersion = objVersion;
	}

	/**
	 * @param taxonId
	 * @param taxonomicalRangeId
	 * @param currentName
	 * @param currentNameTimestamp
	 * @param defaultName
	 * @param currentPredecessorTimestamp
	 * @param taxonCategoryId
	 * @param dominum
	 * @param kingdom
	 * @param phylumDivision
	 * @param subPhylumSubDivision
	 * @param classRank
	 * @param subClass
	 * @param order
	 * @param subOrder
	 * @param superFamily
	 * @param family
	 * @param subFamily
	 * @param tribe
	 * @param subTribe
	 * @param genus
	 * @param subGenus
	 * @param section
	 * @param subSection
	 * @param stirps
	 * @param species
	 * @param subSpecies
	 * @param variety
	 * @param descriptionMonth
	 * @param descriptionYear
	 * @param synonym
	 * @param authorFormatParenthesis
	 * @param creationDate
	 * @param createdBy
	 * @param lastModificationDate
	 * @param lastModificationBy
	 * @param collectionId
	 * @param ancestorTaxon
	 * @param basionym
	 * @param countryId
	 * @param objVersion
	 * @param subKingdom
	 * @param infraKingdom
	 * @param superPhylum
	 * @param superClass
	 * @param superOrder
	 */
	public Taxon(Integer taxonId, Integer taxonomicalRangeId, String currentName, Date currentNameTimestamp, String defaultName, Date currentPredecessorTimestamp, Integer taxonCategoryId, Taxon dominum, Taxon kingdom, Taxon phylumDivision, Taxon subPhylumSubDivision, Taxon classRank, Taxon subClass, Taxon order, Taxon subOrder, Taxon superFamily, Taxon family, Taxon subFamily, Taxon tribe, Taxon subTribe, Taxon genus, Taxon subGenus, Taxon section, Taxon subSection, Taxon stirps, Taxon species, Taxon subSpecies, Taxon variety, Integer descriptionMonth, Integer descriptionYear, Taxon synonym, Integer authorFormatParenthesis, Date creationDate, String createdBy, Date lastModificationDate, String lastModificationBy, Integer collectionId, Taxon ancestorTaxon, String basionym, Integer countryId, Integer objVersion, Taxon subKingdom, Taxon infraKingdom, Taxon superPhylum, Taxon superClass, Taxon superOrder) {
		super();
		this.taxonId = taxonId;
		this.taxonomicalRangeId = taxonomicalRangeId;
		this.currentName = currentName;
		this.currentNameTimestamp = currentNameTimestamp;
		this.defaultName = defaultName;
		this.currentPredecessorTimestamp = currentPredecessorTimestamp;
		this.taxonCategoryId = taxonCategoryId;
		this.dominum = dominum;
		this.kingdom = kingdom;
		this.phylumDivision = phylumDivision;
		this.subPhylumSubDivision = subPhylumSubDivision;
		this.classRank = classRank;
		this.subClass = subClass;
		this.order = order;
		this.subOrder = subOrder;
		this.superFamily = superFamily;
		this.family = family;
		this.subFamily = subFamily;
		this.tribe = tribe;
		this.subTribe = subTribe;
		this.genus = genus;
		this.subGenus = subGenus;
		this.section = section;
		this.subSection = subSection;
		this.stirps = stirps;
		this.species = species;
		this.subSpecies = subSpecies;
		this.variety = variety;
		this.descriptionMonth = descriptionMonth;
		this.descriptionYear = descriptionYear;
		this.synonym = synonym;
		this.authorFormatParenthesis = authorFormatParenthesis;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastModificationDate = lastModificationDate;
		this.lastModificationBy = lastModificationBy;
		this.collectionId = collectionId;
		this.ancestorTaxon = ancestorTaxon;
		this.basionym = basionym;
		this.countryId = countryId;
		this.objVersion = objVersion;
		this.subKingdom = subKingdom;
		this.infraKingdom = infraKingdom;
		this.superPhylum = superPhylum;
		this.superClass = superClass;
		this.superOrder = superOrder;
	}

	/**
	 * @return the ancestorTaxon
	 */
	public Taxon getAncestorTaxon() {
		return ancestorTaxon;
	}

	/**
	 * @param ancestorTaxon the ancestorTaxon to set
	 */
	public void setAncestorTaxon(Taxon ancestorTaxon) {
		this.ancestorTaxon = ancestorTaxon;
	}

	/**
	 * @return the authorFormatParenthesis
	 */
	public Integer getAuthorFormatParenthesis() {
		return authorFormatParenthesis;
	}

	/**
	 * @param authorFormatParenthesis the authorFormatParenthesis to set
	 */
	public void setAuthorFormatParenthesis(Integer authorFormatParenthesis) {
		this.authorFormatParenthesis = authorFormatParenthesis;
	}

	/**
	 * @return the basionym
	 */
	public String getBasionym() {
		return basionym;
	}

	/**
	 * @param basionym the basionym to set
	 */
	public void setBasionym(String basionym) {
		this.basionym = basionym;
	}

	/**
	 * @return the classRank
	 */
	public Taxon getClassRank() {
		return classRank;
	}

	/**
	 * @param classRank the classRank to set
	 */
	public void setClassRank(Taxon classRank) {
		this.classRank = classRank;
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
	 * @return the countryId
	 */
	public Integer getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
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
	 * @return the currentName
	 */
	public String getCurrentName() {
		return currentName;
	}

	/**
	 * @param currentName the currentName to set
	 */
	public void setCurrentName(String currentName) {
		this.currentName = currentName;
	}

	/**
	 * @return the currentNameTimestamp
	 */
	public Date getCurrentNameTimestamp() {
		return currentNameTimestamp;
	}

	/**
	 * @param currentNameTimestamp the currentNameTimestamp to set
	 */
	public void setCurrentNameTimestamp(Date currentNameTimestamp) {
		this.currentNameTimestamp = currentNameTimestamp;
	}

	/**
	 * @return the currentPredecessorTimestamp
	 */
	public Date getCurrentPredecessorTimestamp() {
		return currentPredecessorTimestamp;
	}

	/**
	 * @param currentPredecessorTimestamp the currentPredecessorTimestamp to set
	 */
	public void setCurrentPredecessorTimestamp(Date currentPredecessorTimestamp) {
		this.currentPredecessorTimestamp = currentPredecessorTimestamp;
	}

	/**
	 * @return the defaultName
	 */
	public String getDefaultName() {
		return defaultName;
	}

	/**
	 * @param defaultName the defaultName to set
	 */
	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	/**
	 * @return the descriptionMonth
	 */
	public Integer getDescriptionMonth() {
		return descriptionMonth;
	}

	/**
	 * @param descriptionMonth the descriptionMonth to set
	 */
	public void setDescriptionMonth(Integer descriptionMonth) {
		this.descriptionMonth = descriptionMonth;
	}

	/**
	 * @return the descriptionYear
	 */
	public Integer getDescriptionYear() {
		return descriptionYear;
	}

	/**
	 * @param descriptionYear the descriptionYear to set
	 */
	public void setDescriptionYear(Integer descriptionYear) {
		this.descriptionYear = descriptionYear;
	}

	/**
	 * @return the dominum
	 */
	public Taxon getDominum() {
		return dominum;
	}

	/**
	 * @param dominum the dominum to set
	 */
	public void setDominum(Taxon dominum) {
		this.dominum = dominum;
	}

	/**
	 * @return the family
	 */
	public Taxon getFamily() {
		return family;
	}

	/**
	 * @param family the family to set
	 */
	public void setFamily(Taxon family) {
		this.family = family;
	}

	/**
	 * @return the genus
	 */
	public Taxon getGenus() {
		return genus;
	}

	/**
	 * @param genus the genus to set
	 */
	public void setGenus(Taxon genus) {
		this.genus = genus;
	}

	/**
	 * @return the infraKingdom
	 */
	public Taxon getInfraKingdom() {
		return infraKingdom;
	}

	/**
	 * @param infraKingdom the infraKingdom to set
	 */
	public void setInfraKingdom(Taxon infraKingdom) {
		this.infraKingdom = infraKingdom;
	}

	/**
	 * @return the kingdom
	 */
	public Taxon getKingdom() {
		return kingdom;
	}

	/**
	 * @param kingdom the kingdom to set
	 */
	public void setKingdom(Taxon kingdom) {
		this.kingdom = kingdom;
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
	 * @return the order
	 */
	public Taxon getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Taxon order) {
		this.order = order;
	}

	/**
	 * @return the phylumDivision
	 */
	public Taxon getPhylumDivision() {
		return phylumDivision;
	}

	/**
	 * @param phylumDivision the phylumDivision to set
	 */
	public void setPhylumDivision(Taxon phylumDivision) {
		this.phylumDivision = phylumDivision;
	}

	/**
	 * @return the section
	 */
	public Taxon getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(Taxon section) {
		this.section = section;
	}

	/**
	 * @return the species
	 */
	public Taxon getSpecies() {
		return species;
	}

	/**
	 * @param species the species to set
	 */
	public void setSpecies(Taxon species) {
		this.species = species;
	}

	/**
	 * @return the stirps
	 */
	public Taxon getStirps() {
		return stirps;
	}

	/**
	 * @param stirps the stirps to set
	 */
	public void setStirps(Taxon stirps) {
		this.stirps = stirps;
	}

	/**
	 * @return the subClass
	 */
	public Taxon getSubClass() {
		return subClass;
	}

	/**
	 * @param subClass the subClass to set
	 */
	public void setSubClass(Taxon subClass) {
		this.subClass = subClass;
	}

	/**
	 * @return the subFamily
	 */
	public Taxon getSubFamily() {
		return subFamily;
	}

	/**
	 * @param subFamily the subFamily to set
	 */
	public void setSubFamily(Taxon subFamily) {
		this.subFamily = subFamily;
	}

	/**
	 * @return the subGenus
	 */
	public Taxon getSubGenus() {
		return subGenus;
	}

	/**
	 * @param subGenus the subGenus to set
	 */
	public void setSubGenus(Taxon subGenus) {
		this.subGenus = subGenus;
	}

	/**
	 * @return the subKingdom
	 */
	public Taxon getSubKingdom() {
		return subKingdom;
	}

	/**
	 * @param subKingdom the subKingdom to set
	 */
	public void setSubKingdom(Taxon subKingdom) {
		this.subKingdom = subKingdom;
	}

	/**
	 * @return the subOrder
	 */
	public Taxon getSubOrder() {
		return subOrder;
	}

	/**
	 * @param subOrder the subOrder to set
	 */
	public void setSubOrder(Taxon subOrder) {
		this.subOrder = subOrder;
	}

	/**
	 * @return the subPhylumSubDivision
	 */
	public Taxon getSubPhylumSubDivision() {
		return subPhylumSubDivision;
	}

	/**
	 * @param subPhylumSubDivision the subPhylumSubDivision to set
	 */
	public void setSubPhylumSubDivision(Taxon subPhylumSubDivision) {
		this.subPhylumSubDivision = subPhylumSubDivision;
	}

	/**
	 * @return the subSection
	 */
	public Taxon getSubSection() {
		return subSection;
	}

	/**
	 * @param subSection the subSection to set
	 */
	public void setSubSection(Taxon subSection) {
		this.subSection = subSection;
	}

	/**
	 * @return the subSpecies
	 */
	public Taxon getSubSpecies() {
		return subSpecies;
	}

	/**
	 * @param subSpecies the subSpecies to set
	 */
	public void setSubSpecies(Taxon subSpecies) {
		this.subSpecies = subSpecies;
	}

	/**
	 * @return the subTribe
	 */
	public Taxon getSubTribe() {
		return subTribe;
	}

	/**
	 * @param subTribe the subTribe to set
	 */
	public void setSubTribe(Taxon subTribe) {
		this.subTribe = subTribe;
	}

	/**
	 * @return the superClass
	 */
	public Taxon getSuperClass() {
		return superClass;
	}

	/**
	 * @param superClass the superClass to set
	 */
	public void setSuperClass(Taxon superClass) {
		this.superClass = superClass;
	}

	/**
	 * @return the superFamily
	 */
	public Taxon getSuperFamily() {
		return superFamily;
	}

	/**
	 * @param superFamily the superFamily to set
	 */
	public void setSuperFamily(Taxon superFamily) {
		this.superFamily = superFamily;
	}

	/**
	 * @return the superOrder
	 */
	public Taxon getSuperOrder() {
		return superOrder;
	}

	/**
	 * @param superOrder the superOrder to set
	 */
	public void setSuperOrder(Taxon superOrder) {
		this.superOrder = superOrder;
	}

	/**
	 * @return the superPhylum
	 */
	public Taxon getSuperPhylum() {
		return superPhylum;
	}

	/**
	 * @param superPhylum the superPhylum to set
	 */
	public void setSuperPhylum(Taxon superPhylum) {
		this.superPhylum = superPhylum;
	}

	/**
	 * @return the synonym
	 */
	public Taxon getSynonym() {
		return synonym;
	}

	/**
	 * @param synonym the synonym to set
	 */
	public void setSynonym(Taxon synonym) {
		this.synonym = synonym;
	}

	/**
	 * @return the taxonCategoryId
	 */
	public Integer getTaxonCategoryId() {
		return taxonCategoryId;
	}

	/**
	 * @param taxonCategoryId the taxonCategoryId to set
	 */
	public void setTaxonCategoryId(Integer taxonCategoryId) {
		this.taxonCategoryId = taxonCategoryId;
	}

	/**
	 * @return the taxonId
	 */
	public Integer getTaxonId() {
		return taxonId;
	}

	/**
	 * @param taxonId the taxonId to set
	 */
	public void setTaxonId(Integer taxonId) {
		this.taxonId = taxonId;
	}

	/**
	 * @return the taxonomicalRangeId
	 */
	public Integer getTaxonomicalRangeId() {
		return taxonomicalRangeId;
	}

	/**
	 * @param taxonomicalRangeId the taxonomicalRangeId to set
	 */
	public void setTaxonomicalRangeId(Integer taxonomicalRangeId) {
		this.taxonomicalRangeId = taxonomicalRangeId;
	}

	/**
	 * @return the tribe
	 */
	public Taxon getTribe() {
		return tribe;
	}

	/**
	 * @param tribe the tribe to set
	 */
	public void setTribe(Taxon tribe) {
		this.tribe = tribe;
	}

	/**
	 * @return the variety
	 */
	public Taxon getVariety() {
		return variety;
	}

	/**
	 * @param variety the variety to set
	 */
	public void setVariety(Taxon variety) {
		this.variety = variety;
	}
	
	

}
