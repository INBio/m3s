/**
 * 
 */
package org.inbio.m3s.dto.message;

import org.inbio.m3s.dto.BaseDTOFactory;
import org.inbio.m3s.model.core.Project;

/**
 * @author jgutierrez
 *
 */
public class ProjectDTOFactory extends BaseDTOFactory<Project,ProjectDTO>{

	public ProjectDTO createDTO(Project p) {
		if(p == null)
			return null;
		
		return new ProjectDTO(p.getProjectId(), p.getName());
	}

}
