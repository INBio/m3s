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
package org.inbio.m3s.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author jgutierrez
 *
 */
public abstract class LogGenericEntity implements Serializable {


	private static Logger logger = Logger.getLogger(LogGenericEntity.class);
	/** */
	private static final long serialVersionUID = 1L;
	
	private Date creationDate;

	private String createdBy;

	private Date lastModificationDate;

	private String lastModificationBy;	

	
	
	/**
	 * 
	 */
	public LogGenericEntity() {
	}

	/**
	 * @param creationDate
	 * @param createdBy
	 * @param lastModificationDate
	 * @param lastModificationBy
	 */
	public LogGenericEntity(Date creationDate, String createdBy,
			Date lastModificationDate, String lastModificationBy) {
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastModificationDate = lastModificationDate;
		this.lastModificationBy = lastModificationBy;
	}
	
	/**
	 * For create
	 * 
	 */
	public void setCreateLogValues() {
		Date actualDate = getLogDate();
		this.creationDate = actualDate;
		this.lastModificationDate = actualDate;
	}

	/**
	 * For update purposes
	 * 
	 */
	public void setUpdateLogValues() {
		this.lastModificationDate = getLogDate();
	}

	/**
	 * 
	 * @return
	 */
	private Date getLogDate() {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String literalDate = dateFormat.format(new java.util.Date())
					.toString();
			Date date = dateFormat.parse(literalDate);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("ERROR escribiendo la bitacora");
			return new Date();
		}
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the lastModificationDate
	 */
	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	/**
	 * @param lastModificationDate the lastModificationDate to set
	 */
	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	/**
	 * @return the lastModificationBy
	 */
	public String getLastModificationBy() {
		return lastModificationBy;
	}

	/**
	 * @param lastModificationBy the lastModificationBy to set
	 */
	public void setLastModificationBy(String lastModificationBy) {
		this.lastModificationBy = lastModificationBy;
	}
}
