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
public class ProjectDTOFactory extends BaseDTOFactory{

	public Object createDTO(Object entity) {
		if(entity == null)
			return null;
		
		Project p = (Project) entity;
		return new ProjectDTO(p.getProjectId(), p.getName());
	}

}
