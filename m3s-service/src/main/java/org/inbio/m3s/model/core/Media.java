/* M3S - multimedia management system
*
* Copyright (C) 2009  INBio - Instituto Nacional de Biodiversidad, Costa Rica
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.inbio.m3s.model.core;


import java.util.Date;

import org.inbio.m3s.model.LogGenericEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class Media extends LogGenericEntity {

	private static final long serialVersionUID = 1L;

	private Integer mediaId;

	private Integer mediaTypeId;

	private Integer usePolicyId;

	private Integer authorPersonId;

	private Integer ownerPersonId;

	private Integer ownerInstitutionId;

	private String description;

	private String location;

	private String highResolutionVolume;

	private String highResolutionFileName;

	private Integer siteId;

	private String siteDescription;

	private String title;

	private String series;

	private char isPublic;

	private Integer oldImageFile;

	private Integer oldImageId;

	private String contents;

	private Integer mediaDateYear;

	private Integer mediaDateMonth;

	private Integer mediaDateDay;

	public Media() {
	}

	public Media(Integer usePolicyId, Integer mediaTypeId, char isPublic) {
		this.usePolicyId = usePolicyId;
		this.mediaTypeId = mediaTypeId;
		this.isPublic = isPublic;
	}

	public Media(Integer usePolicyId, Integer mediaTypeId,
			Integer authorPersonId, Integer ownerPersonId,
			Integer ownerInstitutionId, String description, String location,
			String highResolutionVolume, String highResolutionFileName,
			Integer siteId, String siteDescription, String title,
			String series, char isPublic, Integer oldImageFile,
			Integer oldImageId, String contents, Date creationDate,
			String createdBy, Date lastModificationDate,
			String lastModificationBy, 
			Integer mediaDateYear,
			Integer mediaDateMonth, Integer mediaDateDay) {
		this.usePolicyId = usePolicyId;
		this.mediaTypeId = mediaTypeId;
		this.authorPersonId = authorPersonId;
		this.ownerPersonId = ownerPersonId;
		this.ownerInstitutionId = ownerInstitutionId;
		this.description = description;
		this.location = location;
		this.highResolutionVolume = highResolutionVolume;
		this.highResolutionFileName = highResolutionFileName;
		this.siteId = siteId;
		this.siteDescription = siteDescription;
		this.title = title;
		this.series = series;
		this.isPublic = isPublic;
		this.oldImageFile = oldImageFile;
		this.oldImageId = oldImageId;
		this.contents = contents;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
		this.mediaDateYear = mediaDateYear;
		this.mediaDateMonth = mediaDateMonth;
		this.mediaDateDay = mediaDateDay;
	}

	public Integer getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	public Integer getAuthorPersonId() {
		return this.authorPersonId;
	}

	public void setAuthorPersonId(Integer authorPersonId) {
		this.authorPersonId = authorPersonId;
	}

	public Integer getOwnerPersonId() {
		return this.ownerPersonId;
	}

	public void setOwnerPersonId(Integer ownerPersonId) {
		this.ownerPersonId = ownerPersonId;
	}

	public Integer getOwnerInstitutionId() {
		return this.ownerInstitutionId;
	}

	public void setOwnerInstitutionId(Integer ownerInstitutionId) {
		this.ownerInstitutionId = ownerInstitutionId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHighResolutionVolume() {
		return this.highResolutionVolume;
	}

	public void setHighResolutionVolume(String highResolutionVolume) {
		this.highResolutionVolume = highResolutionVolume;
	}

	public String getHighResolutionFileName() {
		return this.highResolutionFileName;
	}

	public void setHighResolutionFileName(String highResolutionFileName) {
		this.highResolutionFileName = highResolutionFileName;
	}

	public Integer getSiteId() {
		return this.siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getSiteDescription() {
		return this.siteDescription;
	}

	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSeries() {
		return this.series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public char getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(char isPublic) {
		this.isPublic = isPublic;
	}
	
	public Integer getOldImageFile() {
		return this.oldImageFile;
	}

	public void setOldImageFile(Integer oldImageFile) {
		this.oldImageFile = oldImageFile;
	}

	public Integer getOldImageId() {
		return this.oldImageId;
	}

	public void setOldImageId(Integer oldImageId) {
		this.oldImageId = oldImageId;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	/**
	 * @param mediaDateYear
	 *            the mediaDateYear to set
	 */
	public void setMediaDateYear(Integer mediaDateYear) {
		this.mediaDateYear = mediaDateYear;
	}

	/**
	 * @return the mediaDateYear
	 */
	public Integer getMediaDateYear() {
		return mediaDateYear;
	}

	/**
	 * @param mediaDateMonth
	 *            the mediaDateMonth to set
	 */
	public void setMediaDateMonth(Integer mediaDateMonth) {
		this.mediaDateMonth = mediaDateMonth;
	}

	/**
	 * @return the mediaDateMonth
	 */
	public Integer getMediaDateMonth() {
		return mediaDateMonth;
	}

	/**
	 * @param mediaDateDay
	 *            the mediaDateDay to set
	 */
	public void setMediaDateDay(Integer mediaDateDay) {
		this.mediaDateDay = mediaDateDay;
	}

	/**
	 * @return the mediaDateDay
	 */
	public Integer getMediaDateDay() {
		return mediaDateDay;
	}

	/**
	 * @return the mediaTypeId
	 */
	public Integer getMediaTypeId() {
		return mediaTypeId;
	}

	/**
	 * @param mediaTypeId the mediaTypeId to set
	 */
	public void setMediaTypeId(Integer mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}

	/**
	 * @return the usePolicyId
	 */
	public Integer getUsePolicyId() {
		return usePolicyId;
	}

	/**
	 * @param usePolicyId the usePolicyId to set
	 */
	public void setUsePolicyId(Integer usePolicyId) {
		this.usePolicyId = usePolicyId;
	}
}
