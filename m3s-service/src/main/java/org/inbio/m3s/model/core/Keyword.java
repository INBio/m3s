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
import java.util.HashSet;
import java.util.Set;

import org.inbio.m3s.model.LogGenericEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class Keyword extends LogGenericEntity {


	private static final long serialVersionUID = 2L;

	private Integer keywordId;

	private Integer nameTextId;
	
	private Integer keywordCategoryId;
	
	private Set<TextTranslation> translations = new HashSet<TextTranslation>(0);
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if(this== null)
			return "null";
		return "Keyword [keywordCategoryId=" + keywordCategoryId + ", keywordId="
				+ keywordId + ", nameTextId=" + nameTextId + ", translations="
				+ translations + "]";
	}
	
	/**
	 * 
	 */
	public Keyword() {
	}
	
	/**
	 * @param keywordId
	 * @param nameTextId
	 * @param keywordCategoryId
	 */
	public Keyword(Integer keywordId, Integer nameTextId,
			Integer keywordCategoryId, Set<TextTranslation> translations) {
		this.keywordId = keywordId;
		this.nameTextId = nameTextId;
		this.keywordCategoryId = keywordCategoryId;
		this.translations = translations;
	}

	/**
	 * @param creationDate
	 * @param createdBy
	 * @param lastModificationDate
	 * @param lastModificationBy
	 * @param keywordId
	 * @param nameTextId
	 * @param keywordCategoryId
	 */
	public Keyword(Date creationDate, String createdBy,
			Date lastModificationDate, String lastModificationBy, Integer keywordId,
			Integer nameTextId, Integer keywordCategoryId) {
		super(creationDate, createdBy, lastModificationDate, lastModificationBy);
		this.keywordId = keywordId;
		this.nameTextId = nameTextId;
		this.keywordCategoryId = keywordCategoryId;
	}


	
	/**
	 * @return the keywordId
	 */
	public Integer getKeywordId() {
		return keywordId;
	}

	/**
	 * @param keywordId the keywordId to set
	 */
	public void setKeywordId(Integer keywordId) {
		this.keywordId = keywordId;
	}

	/**
	 * @return the nameTextId
	 */
	public Integer getNameTextId() {
		return nameTextId;
	}

	/**
	 * @param nameTextId the nameTextId to set
	 */
	public void setNameTextId(Integer nameTextId) {
		this.nameTextId = nameTextId;
	}

	/**
	 * @return the keywordCategoryId
	 */
	public Integer getKeywordCategoryId() {
		return keywordCategoryId;
	}

	/**
	 * @param keywordCategoryId the keywordCategoryId to set
	 */
	public void setKeywordCategoryId(Integer keywordCategoryId) {
		this.keywordCategoryId = keywordCategoryId;
	}

	/**
	 * @return the translations
	 */
	public Set<TextTranslation> getTranslations() {
		return translations;
	}

	/**
	 * @param translations the translations to set
	 */
	public void setTranslations(Set<TextTranslation> translations) {
		this.translations = translations;
	}

}
