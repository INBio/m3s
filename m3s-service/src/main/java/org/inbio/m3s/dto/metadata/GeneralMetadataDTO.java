/**
 * 
 */
package org.inbio.m3s.dto.metadata;

import java.util.List;

import org.inbio.m3s.dto.BaseDTO;
import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.dto.message.ProjectDTO;
import org.inbio.m3s.dto.taxonomy.GatheringLiteDTO;
import org.inbio.m3s.dto.taxonomy.ObservationLiteDTO;
import org.inbio.m3s.dto.taxonomy.SpecimenLiteDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;

/**
 * This class is intended to be useful in the metadata parameters pass beetwen
 * the usecases package and the gwt package.
 * 
 * 
 * @author jgutierrez
 * 
 */
public class GeneralMetadataDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mediaKey;

	private String title;

	private String description;

	// TODO: look in what table is this attribute....
	private String mediaTypeKey;
	
	private List<SpecimenLiteDTO> associatedSpecimensList;
	
	private List<ObservationLiteDTO> associatedObservationsList;
	
	private List<GatheringLiteDTO> associatedGatheringsList;

	private List<ProjectDTO> projectsList;
	

	// TODO:
	// private List series;

	private List<TaxonLiteDTO> taxonsList;

	// TODO: a list of this
	// private List synapticCollections;

	//private List keywords;
	private List<KeywordDTO> keywordsList;

	// For the site the metadata could be the ID of a previously registered site
	// or could be a String variable, thats why there are two variables to
	// manage that
	private String siteKey;

	private String siteDescription;

	/**
	 * 
	 */
	@Override
	public String toString(){
		return "El General Metadata DTO tiene:" +
				"\n\tMedia Key: " + this.getMediaKey() +
				"\n\tTitle: " + this.getTitle() +
				"\n\tDescription: " + this.getDescription() +
				"\n\tMedia Type Id: "+ this.getMediaTypeKey() +
				"\n\tAssociated Specimens: "+ this.getAssociatedSpecimensList().size() +
				"\n\tAssociated Observations: "+ this.getAssociatedObservationsList().size() +
				"\n\tAssociated Gatherings: "+ this.getAssociatedGatheringsList().size() +
				"\n\tProjects: "+ this.getProjectsList().size() +
				"\n\tTaxons: "+ this.getTaxonsList().size() +
				"\n\tKeywords: "+ this.getKeywordsList().size() +
				"\n\tSite Id: "+ this.getSiteKey() +
				"\n\tSite Description: "+ this.getSiteDescription() +
				"";
	}
	
	/**
	 * 
	 */
	public GeneralMetadataDTO() {
	}
	
	/**
	 * @param title
	 * @param description
	 * @param mediaTypeId
	 * @param siteId
	 * @param siteDescription
	 */
	public GeneralMetadataDTO(String title, String description,
			Integer mediaTypeId, Integer siteId, String siteDescription) {
		this.title = title;
		this.description = description;
		this.mediaTypeKey = String.valueOf(mediaTypeId);
		
		if(siteId == null)
			this.siteKey = null;
		else
			this.siteKey = String.valueOf(siteId);
		
		this.siteDescription = siteDescription;
	}
	
	/**
	 * @param title
	 * @param description
	 * @param mediaTypeKey
	 * @param siteKey
	 * @param siteDescription
	 */
	public GeneralMetadataDTO(String title, String description,
			String mediaTypeKey, String siteKey, String siteDescription) {
		this.title = title;
		this.description = description;
		this.mediaTypeKey = mediaTypeKey;
		this.setSiteKey(siteKey);
		this.siteDescription = siteDescription;
	}
	
	/**
	 * @param mediaKey
	 * @param title
	 * @param description
	 * @param mediaTypeKey
	 * @param siteKey
	 * @param siteDescription
	 */
	public GeneralMetadataDTO(String mediaKey, String title, String description,
			String mediaTypeKey, String siteKey, String siteDescription) {
		this.mediaKey = mediaKey;
		this.title = title;
		this.description = description;
		this.mediaTypeKey = mediaTypeKey;
		this.setSiteKey(siteKey);
		this.siteDescription = siteDescription;
	}
	
	
	/**
	 * @param mediaKey
	 * @param title
	 * @param description
	 * @param mediaTypeKey
	 * @param associatedSpecimensList
	 * @param associatedObservationsList
	 * @param associatedGatheringsList
	 * @param projectsList
	 * @param taxonsList
	 * @param keywordsList
	 * @param siteKey
	 * @param siteDescription
	 */
	public GeneralMetadataDTO(String mediaKey, String title, String description,
			String mediaTypeKey, List<SpecimenLiteDTO> associatedSpecimensList,
			List<ObservationLiteDTO> associatedObservationsList,
			List<GatheringLiteDTO> associatedGatheringsList,
			List<ProjectDTO> projectsList, List<TaxonLiteDTO> taxonsList,
			List<KeywordDTO> keywordsList, String siteKey, String siteDescription) {
		this.mediaKey = mediaKey;
		this.title = title;
		this.description = description;
		this.mediaTypeKey = mediaTypeKey;
		this.associatedSpecimensList = associatedSpecimensList;
		this.associatedObservationsList = associatedObservationsList;
		this.associatedGatheringsList = associatedGatheringsList;
		this.projectsList = projectsList;
		this.taxonsList = taxonsList;
		this.keywordsList = keywordsList;
		this.setSiteKey(siteKey);
		this.siteDescription = siteDescription;
	}



	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * @param siteDescription
	 *            the siteDescription to set
	 */
	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}

	/**
	 * @return the siteDescription
	 */
	public String getSiteDescription() {
		return siteDescription;
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
	 * @param mediaKey the mediaKey to set
	 */
	public void setMediaKey(String mediaKey) {
		this.mediaKey = mediaKey;
	}

	/**
	 * @return the mediaKey
	 */
	public String getMediaKey() {
		return mediaKey;
	}

	/**
	 * @param mediaTypeKey the mediaTypeKey to set
	 */
	public void setMediaTypeKey(String mediaTypeKey) {
		this.mediaTypeKey = mediaTypeKey;
	}

	/**
	 * @return the mediaTypeKey
	 */
	public String getMediaTypeKey() {
		return mediaTypeKey;
	}

	/**
	 * @param siteKey the siteKey to set
	 */
	public void setSiteKey(String siteKey) {
		this.siteKey = siteKey;
	}

	/**
	 * @return the siteKey
	 */
	public String getSiteKey() {
		return siteKey;
	}

}
