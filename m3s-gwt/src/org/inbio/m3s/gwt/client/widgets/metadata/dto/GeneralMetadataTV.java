/*
 * MainMetadata.java
 *
 * Created on February 12, 2007, 3:30 PM
 *
 */

package org.inbio.m3s.gwt.client.widgets.metadata.dto;

import java.util.ArrayList;
import java.util.List;

import org.inbio.gwt.associatedto.client.dto.AssociatedToInfo;
import org.inbio.gwt.controlledtext.client.dto.TextInfo;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * General Metadata Textual Values
 * 
 * Object that represents all the General Metadata, in order to make easier to
 * display/retrieve the information from the widgets and/or the database.
 * 
 * @author jgutierrez
 */
public class GeneralMetadataTV implements IsSerializable {

	private Integer mediaId;

	private String title;

	private String description;
	
	private String mediaCategory;

	private String mediaType;

	private AssociatedToInfo associatedToInfo;

	private Integer siteId;

	private String siteDescription;

	private String projects;

	private String series;

	// a list of String elements
	private List<String> taxonomyInfo = new ArrayList<String>();

	private String synopticColletion;

	//kewords
	private List<TextInfo> keyWords = new ArrayList<TextInfo>();

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
	 * Creates a new instance of MainMetadata
	 */
	public GeneralMetadataTV() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * public Integer getCategoryDBId() { return categoryDBId; }
	 * 
	 * public void setCategoryDBId(Integer category) { this.categoryDBId =
	 * category; }
	 */
	/**
	 * @param categoryTV
	 *            the categoryTV to set
	 */
	public void setMediaCategory(String categoryTV) {
		this.mediaCategory = categoryTV;
	}

	/**
	 * @return the categoryTV
	 */
	public String getMediaCategory() {
		return mediaCategory;
	}

	/**
	 * @param mediaTypeTV
	 *            the mediaTypeTV to set
	 */
	public void setMediaType(String mediaTypeTV) {
		this.mediaType = mediaTypeTV;
	}

	/**
	 * @return the mediaTypeTV
	 */
	public String getMediaType() {
		return mediaType;
	}

	public String getSiteDescription() {
		return siteDescription;
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

	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}

	public String getProjects() {
		return projects;
	}

	public void setProjects(String projects) {
		this.projects = projects;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getSynopticColletion() {
		return synopticColletion;
	}

	public void setSynopticColletion(String synopticColletion) {
		this.synopticColletion = synopticColletion;
	}


	/**
	 * @param taxonomyInfo
	 *            the taxonomyInfo to set
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
	 * @return the keywords
	 */
	public List<TextInfo> getKeyWords() {
		return keyWords;
	}

	/**
	 * @param keywords the keywords to set
	 */
	public void setKeyWords(List<TextInfo> keywords) {
		this.keyWords = keywords;
	}

	/**
	 * @return the associatedToInfo
	 */
	public AssociatedToInfo getAssociatedToInfo() {
		return associatedToInfo;
	}

	/**
	 * @param associatedToInfo the associatedToInfo to set
	 */
	public void setAssociatedToInfo(AssociatedToInfo associatedToInfo) {
		this.associatedToInfo = associatedToInfo;
	}

}
