/**
 * 
 */
package org.inbio.m3s.dto.message;

import org.inbio.m3s.dto.BaseDTO;

/**
 * @author jgutierrez
 *
 */
public class ProjectDTO extends BaseDTO {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7299033408917225120L;

	private String projectKey;
	
	private String name;

	/**
	 * 
	 */
	public ProjectDTO() {
	}

	/**
	 * @param projectKey
	 * @param name
	 */
	public ProjectDTO(String projectKey, String name) {
		this.projectKey = projectKey;
		this.name = name;
	}
	
	/**
	 * @param projectId
	 * @param name
	 */
	public ProjectDTO(Integer projectId, String name) {
		this.projectKey = String.valueOf(projectId);
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param projectKey the projectKey to set
	 */
	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	/**
	 * @return the projectKey
	 */
	public String getProjectKey() {
		return projectKey;
	}


}
