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
public class Project extends LogGenericEntity {

	private static final long serialVersionUID = 2782827914571243164L;

	private Integer projectId;

	private String projectManagerUserName;

	private String name;

	private String description;


	public Project() {
	}

	public Project(String projectManagerUserName, String name,
			String description, Date creationDate, String createdBy,
			Date lastModificationDate, String lastModificationBy) {
		this.projectManagerUserName = projectManagerUserName;
		this.name = name;
		this.description = description;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
	}

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the projectManagerUserName
	 */
	public String getProjectManagerUserName() {
		return projectManagerUserName;
	}

	/**
	 * @param projectManagerUserName the projectManagerUserName to set
	 */
	public void setProjectManagerUserName(String projectManagerUserName) {
		this.projectManagerUserName = projectManagerUserName;
	}

}
