/**
 * 
 */
package org.inbio.m3s.dto.lite;

import java.io.Serializable;

/**
 * @author jgutierrez
 *
 */
public class ProjectLite implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7299033408917225120L;

	private Integer projectId;
	
	private String name;

	/**
	 * 
	 */
	public ProjectLite() {
	}

	/**
	 * @param projectId
	 * @param name
	 */
	public ProjectLite(Integer projectId, String name) {
		this.projectId = projectId;
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
	 * @return the projectId
	 */
	public Integer getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

}
