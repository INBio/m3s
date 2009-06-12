/**
 * 
 */
package org.inbio.m3s.dto;

import java.io.Serializable;
import java.util.List;

import org.inbio.m3s.dto.message.KeywordLiteDTO;
import org.inbio.m3s.dto.taxonomy.GatheringLiteDTO;
import org.inbio.m3s.dto.taxonomy.ObservationLiteDTO;
import org.inbio.m3s.dto.taxonomy.SpecimenLiteDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.dto.lite.ProjectLite;

/**
 * This class is intended to be useful in the metadata parameters pass beetwen
 * the usecases package and the gwt package.
 * 
 * 
 * @author jgutierrez
 * 
 */
public class GeneralMetadataDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer mediaId;

	private String title;

	private String description;

	// TODO: look in what table is this attribute....
	private Integer mediaTypeId;
	
	private List<SpecimenLiteDTO> associatedSpecimensList;
	
	private List<ObservationLiteDTO> associatedObservationsList;
	
	private List<GatheringLiteDTO> associatedGatheringsList;

	private List<ProjectLite> projectsList;
	

	// TODO:
	// private List series;

	private List<TaxonLiteDTO> taxonsList;

	// TODO: a list of this
	// private List synapticCollections;

	//private List keywords;
	private List<KeywordLiteDTO> keywordsList;

	// For the site the metadata could be the ID of a previously registered site
	// or could be a String variable, thats why there are two variables to
	// manage that
	private Integer siteId;

	private String siteDescription;

	/**
	 * 
	 */
	@Override
	public String toString(){
		return "El General Metadata DTO tiene:" +
				"\n\tMediaID: " + this.getMediaId() +
				"\n\tTitle: " + this.getTitle() +
				"\n\tDescription: " + this.getDescription() +
				"\n\tMedia Type Id: "+ this.getMediaTypeId() +
				"\n\tAssociated Specimens: "+ this.getAssociatedSpecimensList().size() +
				"\n\tAssociated Observations: "+ this.getAssociatedObservationsList().size() +
				"\n\tAssociated Gatherings: "+ this.getAssociatedGatheringsList().size() +
				"\n\tProjects: "+ this.getProjectsList().size() +
				"\n\tTaxons: "+ this.getTaxonsList().size() +
				"\n\tKeywords: "+ this.getKeywordsList().size() +
				"\n\tSite Id: "+ this.getSiteId() +
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
		this.mediaTypeId = mediaTypeId;
		this.siteId = siteId;
		this.siteDescription = siteDescription;
	}
	
	
	/**
	 * @param mediaId
	 * @param title
	 * @param description
	 * @param mediaTypeId
	 * @param associatedSpecimensList
	 * @param associatedObservationsList
	 * @param associatedGatheringsList
	 * @param projectsList
	 * @param taxonsList
	 * @param keywordsList
	 * @param siteId
	 * @param siteDescription
	 */
	public GeneralMetadataDTO(Integer mediaId, String title, String description,
			Integer mediaTypeId, List<SpecimenLiteDTO> associatedSpecimensList,
			List<ObservationLiteDTO> associatedObservationsList,
			List<GatheringLiteDTO> associatedGatheringsList,
			List<ProjectLite> projectsList, List<TaxonLiteDTO> taxonsList,
			List<KeywordLiteDTO> keywordsList, Integer siteId, String siteDescription) {
		this.mediaId = mediaId;
		this.title = title;
		this.description = description;
		this.mediaTypeId = mediaTypeId;
		this.associatedSpecimensList = associatedSpecimensList;
		this.associatedObservationsList = associatedObservationsList;
		this.associatedGatheringsList = associatedGatheringsList;
		this.projectsList = projectsList;
		this.taxonsList = taxonsList;
		this.keywordsList = keywordsList;
		this.siteId = siteId;
		this.siteDescription = siteDescription;
	}



	/**
	 * @param mediaId
	 *            the mediaId to set
	 */
	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * @return the mediaId
	 */
	public Integer getMediaId() {
		return mediaId;
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
	 * @param mediaTypeId
	 *            the mediaTypeId to set
	 */
	public void setMediaTypeId(Integer mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}

	/**
	 * @return the mediaTypeId
	 */
	public Integer getMediaTypeId() {
		return mediaTypeId;
	}

	/**
	 * @param siteId
	 *            the siteId to set
	 */
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	/**
	 * @return the siteId
	 */
	public Integer getSiteId() {
		return siteId;
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
	public List<ProjectLite> getProjectsList() {
		return projectsList;
	}

	/**
	 * @param projectsList the projectsList to set
	 */
	public void setProjectsList(List<ProjectLite> projectsList) {
		this.projectsList = projectsList;
	}

	/**
	 * @return the keywordsList
	 */
	public List<KeywordLiteDTO> getKeywordsList() {
		return keywordsList;
	}

	/**
	 * @param keywordsList the keywordsList to set
	 */
	public void setKeywordsList(List<KeywordLiteDTO> keywordsList) {
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

}
