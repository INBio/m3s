/**
 * 
 */
package org.inbio.m3s.dto.metadata;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.inbio.m3s.dto.BaseDTO;
import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.dto.message.ProjectDTO;
import org.inbio.m3s.dto.taxonomy.GatheringLiteDTO;
import org.inbio.m3s.dto.taxonomy.ObservationLiteDTO;
import org.inbio.m3s.dto.taxonomy.SpecimenLiteDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;

/**
 * This class holds the basic metadata, the ones that are required.
 * 
 * @author jgutierrez
 *
 */
public class MetadataDTO extends BaseDTO {

	/** */
	private static final long serialVersionUID = 1L;
	
	private String mediaKey;
	private String title;
	private String description;
	private String mediaTypeKey;
	private List<SpecimenLiteDTO> associatedSpecimensList;
	private List<ObservationLiteDTO> associatedObservationsList;
	private List<GatheringLiteDTO> associatedGatheringsList;
	private List<ProjectDTO> projectsList;
	private List<TaxonLiteDTO> taxonsList;
	//private List keywords;
	private List<KeywordDTO> keywordsList;
	// For the site the metadata could be the ID of a previously registered site
	// or could be a String variable, thats why there are two variables to
	// manage that
	private String siteKey;
	private String siteDescription;
	private String authorKey;
	private String personOwnerKey;
	private String institutionOwnerKey;
	private String usePolicyKey;
	private Character isPublic;		
	
	//other metadata mediaAttributes associated to the mediaType
	private List<TechnicalMetadataItemDTO> items; //mediaAttributes list
	
	/**
	 * 
	 */
	@Override
	public String toString(){
		
		String value="The Metadata DTO:" +
				"\n\tMedia Key: " + this.mediaKey +
				"\n\tTitle: " + this.title +
				"\n\tDescription: " + this.description +
				"\n\tMedia Type Id: "+ this.mediaTypeKey +
				"\n\tAssociated Specimens: "+ this.associatedSpecimensList.size() +
				"\n\tAssociated Observations: "+ this.associatedObservationsList.size() +
				"\n\tAssociated Gatherings: "+ this.associatedGatheringsList.size() +
				"\n\tProjects: "+ this.projectsList.size() +
				"\n\tTaxons: "+ this.taxonsList.size() +
				"\n\tKeywords: "+ this.keywordsList.size() +
				"\n\tSite Id: "+ this.siteKey +
				"\n\tSite Description: "+ this.siteDescription +
				"\n\tAuthor id: " + this.authorKey +
				"\n\tInstitution Owner id: " + this.institutionOwnerKey +
				"\n\tPerson Owner id: " + this.personOwnerKey +
				"\n\tUse Policy Id: " + this.usePolicyKey +
				"\n\tIs public: "+ this.isPublic +	
				"";
		
		String value2;
		if(items != null){
			value2= "\n\tThe Technical Metadata DTO[" +this.items.size()+ "]";
		
			for(TechnicalMetadataItemDTO tmiDTO : this.getItems()){
				value2 = value2 + "\n\t["+tmiDTO.getMediaAttributeKey()+"] "
				+tmiDTO.getMediaAttributeName()+" = "+tmiDTO.getValue();
			}
		} else{
			value2 = "\n\tNo Technical Metadata Associated.";
		}
	
		return value + value2;
	}
	
	public TechnicalMetadataItemDTO getMediaAttributeItemByKey(String key){
		if(this.items == null)
			return null;
		
		for(TechnicalMetadataItemDTO ma : this.items){
			if(StringUtils.equals(key, ma.getMediaAttributeKey()))
				return ma;
		}
		
		return null;
	}
	


	/**
	 * @return the mediaKey
	 */
	public String getMediaKey() {
		return mediaKey;
	}


	/**
	 * @param mediaKey the mediaKey to set
	 */
	public void setMediaKey(String mediaKey) {
		this.mediaKey = mediaKey;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the mediaTypeKey
	 */
	public String getMediaTypeKey() {
		return mediaTypeKey;
	}


	/**
	 * @param mediaTypeKey the mediaTypeKey to set
	 */
	public void setMediaTypeKey(String mediaTypeKey) {
		this.mediaTypeKey = mediaTypeKey;
	}


	/**
	 * @return the associatedSpecimensList
	 */
	public List<SpecimenLiteDTO> getAssociatedSpecimensList() {
		return associatedSpecimensList;
	}


	/**
	 * @param associatedSpecimensList the associatedSpecimensList to set
	 */
	public void setAssociatedSpecimensList(
			List<SpecimenLiteDTO> associatedSpecimensList) {
		this.associatedSpecimensList = associatedSpecimensList;
	}


	/**
	 * @return the associatedObservationsList
	 */
	public List<ObservationLiteDTO> getAssociatedObservationsList() {
		return associatedObservationsList;
	}


	/**
	 * @param associatedObservationsList the associatedObservationsList to set
	 */
	public void setAssociatedObservationsList(
			List<ObservationLiteDTO> associatedObservationsList) {
		this.associatedObservationsList = associatedObservationsList;
	}


	/**
	 * @return the associatedGatheringsList
	 */
	public List<GatheringLiteDTO> getAssociatedGatheringsList() {
		return associatedGatheringsList;
	}


	/**
	 * @param associatedGatheringsList the associatedGatheringsList to set
	 */
	public void setAssociatedGatheringsList(
			List<GatheringLiteDTO> associatedGatheringsList) {
		this.associatedGatheringsList = associatedGatheringsList;
	}


	/**
	 * @return the projectsList
	 */
	public List<ProjectDTO> getProjectsList() {
		return projectsList;
	}


	/**
	 * @param projectsList the projectsList to set
	 */
	public void setProjectsList(List<ProjectDTO> projectsList) {
		this.projectsList = projectsList;
	}


	/**
	 * @return the taxonsList
	 */
	public List<TaxonLiteDTO> getTaxonsList() {
		return taxonsList;
	}


	/**
	 * @param taxonsList the taxonsList to set
	 */
	public void setTaxonsList(List<TaxonLiteDTO> taxonsList) {
		this.taxonsList = taxonsList;
	}


	/**
	 * @return the keywordsList
	 */
	public List<KeywordDTO> getKeywordsList() {
		return keywordsList;
	}


	/**
	 * @param keywordsList the keywordsList to set
	 */
	public void setKeywordsList(List<KeywordDTO> keywordsList) {
		this.keywordsList = keywordsList;
	}


	/**
	 * @return the siteKey
	 */
	public String getSiteKey() {
		return siteKey;
	}


	/**
	 * @param siteKey the siteKey to set
	 */
	public void setSiteKey(String siteKey) {
		this.siteKey = siteKey;
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
	 * @return the authorKey
	 */
	public String getAuthorKey() {
		return authorKey;
	}


	/**
	 * @param authorKey the authorKey to set
	 */
	public void setAuthorKey(String authorKey) {
		this.authorKey = authorKey;
	}


	/**
	 * @return the personOwnerKey
	 */
	public String getPersonOwnerKey() {
		return personOwnerKey;
	}


	/**
	 * @param personOwnerKey the personOwnerKey to set
	 */
	public void setPersonOwnerKey(String personOwnerKey) {
		this.personOwnerKey = personOwnerKey;
	}


	/**
	 * @return the institutionOwnerKey
	 */
	public String getInstitutionOwnerKey() {
		return institutionOwnerKey;
	}


	/**
	 * @param institutionOwnerKey the institutionOwnerKey to set
	 */
	public void setInstitutionOwnerKey(String institutionOwnerKey) {
		this.institutionOwnerKey = institutionOwnerKey;
	}


	/**
	 * @return the usePolicyKey
	 */
	public String getUsePolicyKey() {
		return usePolicyKey;
	}


	/**
	 * @param usePolicyKey the usePolicyKey to set
	 */
	public void setUsePolicyKey(String usePolicyKey) {
		this.usePolicyKey = usePolicyKey;
	}


	/**
	 * @return the isPublic
	 */
	public Character getIsPublic() {
		return isPublic;
	}


	/**
	 * @param isPublic the isPublic to set
	 */
	public void setIsPublic(Character isPublic) {
		this.isPublic = isPublic;
	}


	/**
	 * @return the items
	 */
	public List<TechnicalMetadataItemDTO> getItems() {
		return items;
	}


	/**
	 * @param items the items to set
	 */
	public void setItems(List<TechnicalMetadataItemDTO> items) {
		this.items = items;
	}


}


