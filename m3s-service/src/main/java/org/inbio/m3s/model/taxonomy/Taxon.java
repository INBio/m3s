/**
 * 
 */
package org.inbio.m3s.model.taxonomy;

import java.util.Date;

import org.inbio.m3s.model.LogGenericEntity;

/**
 * @author jgutierrez
 *
 */
public class Taxon extends LogGenericEntity {
	
	private static final long serialVersionUID = 1L;

	private Integer taxonId;

	private Integer kingdomId;	
	//private Taxon taxonByKingdomTaxonId;

	private Integer sectionId;	
	//private Taxon taxonBySectionTaxonId;

	
	private Integer speciesId;
	//private Taxon taxonBySpeciesTaxonId;

	private Integer subSpeciesId;
	//private Taxon taxonBySubspeciesTaxonId;

	private Integer subClassId;
	//private Taxon taxonBySubclassTaxonId;

	private Integer dominiumId;
	//private Taxon taxonByDominiumTaxonId;

	private Integer subOrderId;
	//private Taxon taxonBySuborderTaxonId;

	private Integer familyId;
	//private Taxon taxonByFamilyTaxonId;

	private Integer synonymId;
	//private Taxon taxonBySynonymTaxonId;

	private Integer ancestorId;
	//private Taxon taxonByAncestorId;

	private Integer classId;
	//private Taxon taxonByClassTaxonId;

	private Integer varietyId;

	private Integer stirpsId;

	private Integer orderId;

	private Integer superFamilyId;

	private Integer subGenusId;

	private Integer tribeId;

	private Integer subTribeId;

	private Integer genusId;

	private Integer subSectionId;

	private Integer subFamilyId;

	private Integer subPhylumSubDivisionId;

	private Integer phylumDivisionId;

	private Integer taxonomicalRangeId;

	private String currentName;

	private Date currentNameTimestamp;

	private String defaultName;

	private Date currentPredecessorTimestamp;

	private Integer taxonCategoryId;

	private Integer descriptionMonth;

	private Integer descriptionYear;

	private String authorFormatParenthesis;

	private Integer collectionId;

	private String basionym;

	
	
	public Taxon() {
	}

	/**
	 * 
	 * @param taxonId
	 * @param taxonomicalRangeId
	 * @param currentName
	 * @param currentNameTimestamp
	 * @param defaultName
	 * @param taxonCategoryId
	 * @param authorFormatParenthesis
	 */
	public Taxon(Integer taxonId, Integer taxonomicalRangeId,
			String currentName, Date currentNameTimestamp, String defaultName,
			Integer taxonCategoryId, String authorFormatParenthesis) {
		this.taxonId = taxonId;
		this.taxonomicalRangeId = taxonomicalRangeId;
		this.currentName = currentName;
		this.currentNameTimestamp = currentNameTimestamp;
		this.defaultName = defaultName;
		this.taxonCategoryId = taxonCategoryId;
		this.authorFormatParenthesis = authorFormatParenthesis;
	}

	/**
	 * 
	 * @param taxonId
	 * @param kingdomId
	 * @param sectionId
	 * @param speciesId
	 * @param subSpeciesId
	 * @param subClassId
	 * @param dominiumId
	 * @param subOrderId
	 * @param familyId
	 * @param synonymId
	 * @param ancestorId
	 * @param classId
	 * @param varietyId
	 * @param stirpsId
	 * @param orderId
	 * @param superFamilyId
	 * @param subGenusId
	 * @param tribeId
	 * @param subTribeId
	 * @param genusId
	 * @param subSectionId
	 * @param subFamilyId
	 * @param subPhylumSubDivisionId
	 * @param phylumDivisionId
	 * @param taxonomicalRangeId
	 * @param currentName
	 * @param currentNameTimestamp
	 * @param defaultName
	 * @param currentPredecessorTimestamp
	 * @param taxonCategoryId
	 * @param descriptionMonth
	 * @param descriptionYear
	 * @param authorFormatParenthesis
	 * @param creationDate
	 * @param createdBy
	 * @param lastModificationDate
	 * @param lastModificationBy
	 * @param collectionId
	 * @param basionym
	 */
	public Taxon(Integer taxonId, Integer kingdomId,
			Integer sectionId, Integer speciesId,
			Integer subSpeciesId, Integer subClassId,
			Integer dominiumId, Integer subOrderId,
			Integer familyId, Integer synonymId,
			Integer ancestorId, Integer classId,
			Integer varietyId, Integer stirpsId,
			Integer orderId, Integer superFamilyId,
			Integer subGenusId, Integer tribeId,
			Integer subTribeId, Integer genusId,
			Integer subSectionId, Integer subFamilyId,
			Integer subPhylumSubDivisionId,
			Integer phylumDivisionId, Integer taxonomicalRangeId,
			String currentName, Date currentNameTimestamp, String defaultName,
			Date currentPredecessorTimestamp, Integer taxonCategoryId,
			Integer descriptionMonth, Integer descriptionYear,
			String authorFormatParenthesis, Date creationDate,
			String createdBy, Date lastModificationDate,
			String lastModificationBy, Integer collectionId,
			String basionym) {
		this.taxonId = taxonId;
		this.kingdomId = kingdomId;
		this.sectionId = sectionId;
		this.speciesId = speciesId;
		this.subSpeciesId = subSpeciesId;
		this.subClassId = subClassId;
		this.dominiumId = dominiumId;
		this.subOrderId = subOrderId;
		this.familyId = familyId;
		this.synonymId = synonymId;
		this.ancestorId = ancestorId;
		this.classId = classId;
		this.varietyId = varietyId;
		this.stirpsId = stirpsId;
		this.orderId = orderId;
		this.superFamilyId = superFamilyId;
		this.subGenusId = subGenusId;
		this.tribeId = tribeId;
		this.subTribeId = subTribeId;
		this.genusId = genusId;
		this.subSectionId = subSectionId;
		this.subFamilyId = subFamilyId;
		this.subPhylumSubDivisionId = subPhylumSubDivisionId;
		this.phylumDivisionId = phylumDivisionId;
		this.taxonomicalRangeId = taxonomicalRangeId;
		this.currentName = currentName;
		this.currentNameTimestamp = currentNameTimestamp;
		this.defaultName = defaultName;
		this.currentPredecessorTimestamp = currentPredecessorTimestamp;
		this.taxonCategoryId = taxonCategoryId;
		this.descriptionMonth = descriptionMonth;
		this.descriptionYear = descriptionYear;
		this.authorFormatParenthesis = authorFormatParenthesis;
		this.collectionId = collectionId;
		this.basionym = basionym;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
	}
	
	
	@Override
	public String toString(){
		
		String result = "Los valores del Objecto son:" +
				"\n\tTaxonId: " +taxonId + " ["+defaultName+"]";

		if(kingdomId!=null)
			result = result + "\n\tTaxon Reino Id: " +kingdomId;
		
		if(phylumDivisionId!=null)
			result = result + "\n\tTaxon Filo Id: " +phylumDivisionId;
		
		if(orderId!=null)
			result = result + "\n\tTaxon Orden Id: " +orderId;
		
		if(classId!=null)
			result = result + "\n\tTaxon Clase Id: " +classId;
		
		if(familyId!=null)
			result = result + "\n\tTaxon Familia Id: " +familyId;
		
		if(genusId!=null)
				result = result + "\n\tTaxon Genero Id: " +genusId;
				
		if(speciesId!=null)
			result = result + "\n\tTaxon Specie Id: " +speciesId;		
		
		return result;
	}

	public Integer getTaxonId() {
		return this.taxonId;
	}

	public void setTaxonId(Integer taxonId) {
		this.taxonId = taxonId;
	}


	public Integer getTaxonomicalRangeId() {
		return this.taxonomicalRangeId;
	}

	public void setTaxonomicalRangeId(Integer taxonomicalRangeId) {
		this.taxonomicalRangeId = taxonomicalRangeId;
	}

	public String getCurrentName() {
		return this.currentName;
	}

	public void setCurrentName(String currentName) {
		this.currentName = currentName;
	}

	public Date getCurrentNameTimestamp() {
		return this.currentNameTimestamp;
	}

	public void setCurrentNameTimestamp(Date currentNameTimestamp) {
		this.currentNameTimestamp = currentNameTimestamp;
	}

	public String getDefaultName() {
		return this.defaultName;
	}

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	public Date getCurrentPredecessorTimestamp() {
		return this.currentPredecessorTimestamp;
	}

	public void setCurrentPredecessorTimestamp(Date currentPredecessorTimestamp) {
		this.currentPredecessorTimestamp = currentPredecessorTimestamp;
	}

	public Integer getTaxonCategoryId() {
		return this.taxonCategoryId;
	}

	public void setTaxonCategoryId(Integer taxonCategoryId) {
		this.taxonCategoryId = taxonCategoryId;
	}

	public Integer getDescriptionMonth() {
		return this.descriptionMonth;
	}

	public void setDescriptionMonth(Integer descriptionMonth) {
		this.descriptionMonth = descriptionMonth;
	}

	public Integer getDescriptionYear() {
		return this.descriptionYear;
	}

	public void setDescriptionYear(Integer descriptionYear) {
		this.descriptionYear = descriptionYear;
	}

	public String getAuthorFormatParenthesis() {
		return this.authorFormatParenthesis;
	}

	public void setAuthorFormatParenthesis(String authorFormatParenthesis) {
		this.authorFormatParenthesis = authorFormatParenthesis;
	}

	public Integer getCollectionId() {
		return this.collectionId;
	}

	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}
	
	public String getBasionym() {
		return this.basionym;
	}

	public void setBasionym(String basionym) {
		this.basionym = basionym;
	}

	/**
	 * @return the kingdomId
	 */
	public Integer getKingdomId() {
		return kingdomId;
	}

	/**
	 * @param kingdomId the kingdomId to set
	 */
	public void setKingdomId(Integer kingdomId) {
		this.kingdomId = kingdomId;
	}

	/**
	 * @return the sectionId
	 */
	public Integer getSectionId() {
		return sectionId;
	}

	/**
	 * @param sectionId the sectionId to set
	 */
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	/**
	 * @return the speciesId
	 */
	public Integer getSpeciesId() {
		return speciesId;
	}

	/**
	 * @param speciesId the speciesId to set
	 */
	public void setSpeciesId(Integer speciesId) {
		this.speciesId = speciesId;
	}

	/**
	 * @return the familyId
	 */
	public Integer getFamilyId() {
		return familyId;
	}

	/**
	 * @param familyId the familyId to set
	 */
	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}

	/**
	 * @param varietyId the varietyId to set
	 */
	public void setVarietyId(Integer varietyId) {
		this.varietyId = varietyId;
	}

	/**
	 * @return the varietyId
	 */
	public Integer getVarietyId() {
		return varietyId;
	}

	/**
	 * @return the subSpeciesId
	 */
	public Integer getSubSpeciesId() {
		return subSpeciesId;
	}

	/**
	 * @param subSpeciesId the subSpeciesId to set
	 */
	public void setSubSpeciesId(Integer subSpeciesId) {
		this.subSpeciesId = subSpeciesId;
	}

	/**
	 * @return the subClassId
	 */
	public Integer getSubClassId() {
		return subClassId;
	}

	/**
	 * @param subClassId the subClassId to set
	 */
	public void setSubClassId(Integer subClassId) {
		this.subClassId = subClassId;
	}

	/**
	 * @return the dominiumId
	 */
	public Integer getDominiumId() {
		return dominiumId;
	}

	/**
	 * @param dominiumId the dominiumId to set
	 */
	public void setDominiumId(Integer dominiumId) {
		this.dominiumId = dominiumId;
	}

	/**
	 * @return the subOrderId
	 */
	public Integer getSubOrderId() {
		return subOrderId;
	}

	/**
	 * @param subOrderId the subOrderId to set
	 */
	public void setSubOrderId(Integer subOrderId) {
		this.subOrderId = subOrderId;
	}

	/**
	 * @return the synonymId
	 */
	public Integer getSynonymId() {
		return synonymId;
	}

	/**
	 * @param synonymId the synonymId to set
	 */
	public void setSynonymId(Integer synonymId) {
		this.synonymId = synonymId;
	}

	/**
	 * @return the ancestorId
	 */
	public Integer getAncestorId() {
		return ancestorId;
	}

	/**
	 * @param ancestorId the ancestorId to set
	 */
	public void setAncestorId(Integer ancestorId) {
		this.ancestorId = ancestorId;
	}

	/**
	 * @return the classId
	 */
	public Integer getClassId() {
		return classId;
	}

	/**
	 * @param classId the classId to set
	 */
	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	/**
	 * @return the stirpsId
	 */
	public Integer getStirpsId() {
		return stirpsId;
	}

	/**
	 * @param stirpsId the stirpsId to set
	 */
	public void setStirpsId(Integer stirpsId) {
		this.stirpsId = stirpsId;
	}

	/**
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the superFamilyId
	 */
	public Integer getSuperFamilyId() {
		return superFamilyId;
	}

	/**
	 * @param superFamilyId the superFamilyId to set
	 */
	public void setSuperFamilyId(Integer superFamilyId) {
		this.superFamilyId = superFamilyId;
	}

	/**
	 * @return the subGenusId
	 */
	public Integer getSubGenusId() {
		return subGenusId;
	}

	/**
	 * @param subGenusId the subGenusId to set
	 */
	public void setSubGenusId(Integer subGenusId) {
		this.subGenusId = subGenusId;
	}

	/**
	 * @return the tribeId
	 */
	public Integer getTribeId() {
		return tribeId;
	}

	/**
	 * @param tribeId the tribeId to set
	 */
	public void setTribeId(Integer tribeId) {
		this.tribeId = tribeId;
	}

	/**
	 * @return the subTribeId
	 */
	public Integer getSubTribeId() {
		return subTribeId;
	}

	/**
	 * @param subTribeId the subTribeId to set
	 */
	public void setSubTribeId(Integer subTribeId) {
		this.subTribeId = subTribeId;
	}

	/**
	 * @return the genusId
	 */
	public Integer getGenusId() {
		return genusId;
	}

	/**
	 * @param genusId the genusId to set
	 */
	public void setGenusId(Integer genusId) {
		this.genusId = genusId;
	}

	/**
	 * @return the subSectionId
	 */
	public Integer getSubSectionId() {
		return subSectionId;
	}

	/**
	 * @param subSectionId the subSectionId to set
	 */
	public void setSubSectionId(Integer subSectionId) {
		this.subSectionId = subSectionId;
	}

	/**
	 * @return the subFamilyId
	 */
	public Integer getSubFamilyId() {
		return subFamilyId;
	}

	/**
	 * @param subFamilyId the subFamilyId to set
	 */
	public void setSubFamilyId(Integer subFamilyId) {
		this.subFamilyId = subFamilyId;
	}

	/**
	 * @return the subPhylumSubDivisionId
	 */
	public Integer getSubPhylumSubDivisionId() {
		return subPhylumSubDivisionId;
	}

	/**
	 * @param subPhylumSubDivisionId the subPhylumSubDivisionId to set
	 */
	public void setSubPhylumSubDivisionId(Integer subPhylumSubDivisionId) {
		this.subPhylumSubDivisionId = subPhylumSubDivisionId;
	}

	/**
	 * @return the phylumDivisionId
	 */
	public Integer getPhylumDivisionId() {
		return phylumDivisionId;
	}

	/**
	 * @param phylumDivisionId the phylumDivisionId to set
	 */
	public void setPhylumDivisionId(Integer phylumDivisionId) {
		this.phylumDivisionId = phylumDivisionId;
	}
}