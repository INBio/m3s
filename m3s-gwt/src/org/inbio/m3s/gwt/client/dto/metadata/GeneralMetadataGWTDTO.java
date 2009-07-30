/**
 * 
 */
package org.inbio.m3s.gwt.client.dto.metadata;

import java.util.ArrayList;
import java.util.List;

import org.inbio.gwt.associatedto.client.dto.AssociatedToInfo;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author jgutierrez
 *
 */
public class GeneralMetadataGWTDTO implements IsSerializable{


	private String mediaKey;

	private String title;

	private String description;

	private String mediaCategory;
	
	private String mediaType;
	
	// For the site the metadata could be the ID of a previously registered site
	// or could be a String variable, thats why there are two variables to
	// manage that
	private String siteKey;

	private String siteDescription;
	
	private List<ProjectGWTDTO> projectsList;

	/**
	 * @deprecated
	 */
	private String series;

	// a list of String elements
	/**
	 * @deprecated
	 */
	private List<String> taxonomyInfo = new ArrayList<String>();

	private String synopticColletion;

	//kewords
	//private List<TextInfo> keyWords = new ArrayList<TextInfo>();
	
	private List<KeywordGWTDTO> keywordsList;

	
	/**
	 * @deprecated
	 */
	private AssociatedToInfo associatedToInfo;
	
	/**
	 *
	private List<SpecimenLiteDTO> associatedSpecimensList;
	 */
	
	/**
	private List<ObservationLiteDTO> associatedObservationsList;
	*/
	
	/**
	private List<GatheringLiteDTO> associatedGatheringsList;
	*
	

	// TODO:
	// private List series;

	/**
	private List<TaxonLiteDTO> taxonsList;
	 */
	
	// TODO: a list of this
	// private List synapticCollections;

	/**
	private List<KeywordLiteDTO> keywordsList;
	*/



	/**
	 * 
	 */
	@Override
	public String toString(){
		return "El General Metadata DTO tiene:" +
				"\n\tMedia Key: " + this.getMediaKey() +
				"\n\tTitle: " + this.getTitle() +
				"\n\tDescription: " + this.getDescription() +
				"\n\tMedia Category: "+ this.getMediaCategory() +
				"\n\tMedia Type: "+ this.getMediaType() +
				//"\n\tAssociated Specimens: "+ this.getAssociatedSpecimensList().size() +
				//"\n\tAssociated Observations: "+ this.getAssociatedObservationsList().size() +
				//"\n\tAssociated Gatherings: "+ this.getAssociatedGatheringsList().size() +
				"\n\tProjects: "+ this.getProjectsList().size() +
				//"\n\tTaxons: "+ this.getTaxonsList().size() +
				//"\n\tKeywords: "+ this.getKeywordsList().size() +
				"\n\tSite Id: "+ this.getSiteKey() +
				"\n\tSite Description: "+ this.getSiteDescription() +
				"";
	}
	
	/**
	 * 
	 */
	public GeneralMetadataGWTDTO() {
	}
	
	/**
	 * @param title
	 * @param description
	 * @param mediaTypeId
	 * @param siteId
	 * @param siteDescription
	 */
	public GeneralMetadataGWTDTO(String title, String description,
			Integer mediaTypeId, Integer siteId, String siteDescription) {
		this.title = title;
		this.description = description;
		this.mediaType = String.valueOf(mediaTypeId);
		
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
	public GeneralMetadataGWTDTO(String title, String description,
			String mediaTypeKey, String siteKey, String siteDescription) {
		this.title = title;
		this.description = description;
		this.mediaType = mediaTypeKey;
		this.setSiteKey(siteKey);
		this.siteDescription = siteDescription;
	}
	
	
	/**
	 * @param mediaKey
	 * @param title
	 * @param description
	 * @param mediaCategoryKey
	 * @param mediaTypeKey
	 * @param siteKey
	 * @param siteDescription
	 */
	public GeneralMetadataGWTDTO(String mediaKey, String title, String description,
			String mediaCategoryKey, String mediaTypeKey, 
			//List<SpecimenLiteDTO> associatedSpecimensList,
			//List<ObservationLiteDTO> associatedObservationsList,
			//List<GatheringLiteDTO> associatedGatheringsList,
			List<ProjectGWTDTO> projectsList, 
			//List<TaxonLiteDTO> taxonsList,
			//List<KeywordLiteDTO> keywordsList, 
			String siteKey, String siteDescription) {
		this.mediaKey = mediaKey;
		this.title = title;
		this.description = description;
		this.mediaCategory = mediaCategoryKey;
		this.mediaType = mediaTypeKey;
		//this.associatedSpecimensList = associatedSpecimensList;
		//this.associatedObservationsList = associatedObservationsList;
		//this.associatedGatheringsList = associatedGatheringsList;
		this.setProjectsList(projectsList);
		//this.taxonsList = taxonsList;
		//this.keywordsList = keywordsList;
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
	 * @param mediaType the mediaType to set
	 */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	/**
	 * @return the mediaType
	 */
	public String getMediaType() {
		return mediaType;
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

	/**
	 * @param mediaCategory the mediaCategory to set
	 */
	public void setMediaCategory(String mediaCategoryKey) {
		this.mediaCategory = mediaCategoryKey;
	}

	/**
	 * @return the mediaCategory
	 */
	public String getMediaCategory() {
		return mediaCategory;
	}

	/**
	 * @param associatedToInfo the associatedToInfo to set
	 */
	public void setAssociatedToInfo(AssociatedToInfo associatedToInfo) {
		this.associatedToInfo = associatedToInfo;
	}

	/**
	 * @return the associatedToInfo
	 */
	public AssociatedToInfo getAssociatedToInfo() {
		return associatedToInfo;
	}

	/**
	 * @param series the series to set
	 */
	public void setSeries(String series) {
		this.series = series;
	}

	/**
	 * @return the series
	 */
	public String getSeries() {
		return series;
	}

	/**
	 * @param taxonomyInfo the taxonomyInfo to set
	 */
	public void setTaxonomyInfo(List<String> taxonomyInfo) {
		this.taxonomyInfo = taxonomyInfo;
	}

	/**
	 * @return the taxonomyInfo
	 */
	public List<String> getTaxonomyInfo() {
		return taxonomyInfo;
	}

	/**
	 * @param synopticColletion the synopticColletion to set
	 */
	public void setSynopticColletion(String synopticColletion) {
		this.synopticColletion = synopticColletion;
	}

	/**
	 * @return the synopticColletion
	 */
	public String getSynopticColletion() {
		return synopticColletion;
	}

	/**
	 * @param projectsList the projectsList to set
	 */
	public void setProjectsList(List<ProjectGWTDTO> projectsList) {
		this.projectsList = projectsList;
	}

	/**
	 * @return the projectsList
	 */
	public List<ProjectGWTDTO> getProjectsList() {
		return projectsList;
	}

	/**
	 * @param keywordsList the keywordsList to set
	 */
	public void setKeywordsList(List<KeywordGWTDTO> keywordsList) {
		this.keywordsList = keywordsList;
	}

	/**
	 * @return the keywordsList
	 */
	public List<KeywordGWTDTO> getKeywordsList() {
		return keywordsList;
	}

}
