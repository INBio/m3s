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
 * @author jgutierrez
 */
public class MediaAttribute extends LogGenericEntity {

	private static final long serialVersionUID = -7359379744017180231L;

	private Integer mediaAttributeId;
	
	// to be resolver in a properties i10n file
	private String descriptionTextKey;
	
	// to be resolver in a properties i10n file
	private String nameTextKey;

	private char mediaAttributeValueType;


	public MediaAttribute() {
	}

	public MediaAttribute(Integer mediaAttributeId, String nameTextKey, 
			String descriptionTextKey, char mediaAttributeValueType) {
		this.mediaAttributeId = mediaAttributeId;
		this.nameTextKey = nameTextKey;
		this.descriptionTextKey = descriptionTextKey;
		this.mediaAttributeValueType = mediaAttributeValueType;
	}


	public MediaAttribute(String descriptionTextKey, String nameTextKey,
			char mediaAttributeValueType, Date creationDate, 
			String createdBy, Date lastModificationDate,
			String lastModificationBy) {
		this.descriptionTextKey = descriptionTextKey;
		this.nameTextKey = nameTextKey;
		this.mediaAttributeValueType = mediaAttributeValueType;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
	}

	/**
	 * @return the mediaAttributeId
	 */
	public Integer getMediaAttributeId() {
		return mediaAttributeId;
	}

	/**
	 * @param mediaAttributeId the mediaAttributeId to set
	 */
	public void setMediaAttributeId(Integer mediaAttributeId) {
		this.mediaAttributeId = mediaAttributeId;
	}

	/**
	 * @return the descriptionTextKey
	 */
	public String getDescriptionTextKey() {
		return descriptionTextKey;
	}

	/**
	 * @param descriptionTextKey the descriptionTextKey to set
	 */
	public void setDescriptionTextKey(String descriptionTextKey) {
		this.descriptionTextKey = descriptionTextKey;
	}

	/**
	 * @return the nameTextKey
	 */
	public String getNameTextKey() {
		return nameTextKey;
	}

	/**
	 * @param nameTextKey the nameTextKey to set
	 */
	public void setNameTextKey(String nameTextKey) {
		this.nameTextKey = nameTextKey;
	}

	/**
	 * @return the mediaAttributeValueType
	 */
	public char getMediaAttributeValueType() {
		return mediaAttributeValueType;
	}

	/**
	 * @param mediaAttributeValueType the mediaAttributeValueType to set
	 */
	public void setMediaAttributeValueType(char mediaAttributeValueType) {
		this.mediaAttributeValueType = mediaAttributeValueType;
	}

}
