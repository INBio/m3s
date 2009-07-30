/**
 * 
 */
package org.inbio.m3s.gwt.client.dto.metadata;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author jgutierrez
 *
 */
public class ProjectGWTDTO implements IsSerializable {

	private String projectKey;
	
	private String name;

	/**
	 * 
	 */
	public ProjectGWTDTO() {
	}

	/**
	 * @param projectKey
	 * @param name
	 */
	public ProjectGWTDTO(String projectKey, String name) {
		this.setProjectKey(projectKey);
		this.setName(name);
	}
	
	@Override
	public String toString(){
		return "EL Project GWTDTO tiene:" +
		"\n\tKey: " + projectKey +
		"\n\tNombre: '"+ name +"'" +
		"";
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

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	
	
	
}
