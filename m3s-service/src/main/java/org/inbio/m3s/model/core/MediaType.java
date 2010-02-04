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
public class MediaType extends LogGenericEntity {

	/** NPI */
	private static final long serialVersionUID = 3399750864350074030L;

	private Integer mediaTypeId;

	//private Text textByNameTextId;
	private Integer mediaTypeNameTextId;
	
	//private Text textByDescriptionTextId;
	private Integer mediaTypeDescriptionTextId;
	
	private Integer mediaCategoryId;

	public MediaType() {
	}

	/**
	 * 
	 * @param mediaTypeDescriptionTextId
	 * @param mediaTypeNameTextId
	 * @param mediaCategoryId
	 * @param creationDate
	 * @param createdBy
	 * @param lastModificationDate
	 * @param lastModificationBy
	 */
	public MediaType(Integer mediaTypeDescriptionTextId, Integer mediaTypeNameTextId,
			Integer mediaCategoryId, Date creationDate, String createdBy,
			Date lastModificationDate, String lastModificationBy) {
		this.mediaTypeDescriptionTextId = mediaTypeDescriptionTextId;
		this.mediaTypeNameTextId = mediaTypeNameTextId;
		this.mediaCategoryId = mediaCategoryId;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
	}
	

	public MediaType(Integer mediaTypeDescriptionTextId, Integer mediaTypeNameTextId, 
			Date creationDate, String createdBy,
			Date lastModificationDate, String lastModificationBy) {
		this.mediaTypeDescriptionTextId = mediaTypeDescriptionTextId;
		this.mediaTypeNameTextId = mediaTypeNameTextId;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
	}

	public Integer getMediaTypeId() {
		return this.mediaTypeId;
	}

	public void setMediaTypeId(Integer mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}

	/**
	 * @param mediaCategoryId the mediaCategoryId to set
	 */
	public void setMediaCategoryId(Integer mediaCategoryId) {
		this.mediaCategoryId = mediaCategoryId;
	}

	/**
	 * @return the mediaCategoryId
	 */
	public Integer getMediaCategoryId() {
		return mediaCategoryId;
	}

	/**
	 * @return the mediaTypeNameTextId
	 */
	public Integer getMediaTypeNameTextId() {
		return mediaTypeNameTextId;
	}

	/**
	 * @param mediaTypeNameTextId the mediaTypeNameTextId to set
	 */
	public void setMediaTypeNameTextId(Integer mediaTypeNameTextId) {
		this.mediaTypeNameTextId = mediaTypeNameTextId;
	}

	/**
	 * @return the mediaTypeDescriptionTextId
	 */
	public Integer getMediaTypeDescriptionTextId() {
		return mediaTypeDescriptionTextId;
	}

	/**
	 * @param mediaTypeDescriptionTextId the mediaTypeDescriptionTextId to set
	 */
	public void setMediaTypeDescriptionTextId(Integer mediaTypeDescriptionTextId) {
		this.mediaTypeDescriptionTextId = mediaTypeDescriptionTextId;
	}

}
