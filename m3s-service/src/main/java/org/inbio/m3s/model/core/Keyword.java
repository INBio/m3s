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
public class Keyword extends LogGenericEntity {


	private static final long serialVersionUID = 2L;

	private Integer keywordId;

	private Integer nameTextId;
	
	private Integer keywordCategoryId;

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
			Integer keywordCategoryId) {
		this.keywordId = keywordId;
		this.nameTextId = nameTextId;
		this.keywordCategoryId = keywordCategoryId;
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

}
