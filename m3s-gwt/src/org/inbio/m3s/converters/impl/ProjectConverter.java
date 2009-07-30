/**
 * 
 */
package org.inbio.m3s.converters.impl;

import org.inbio.m3s.converters.BaseConverter;
import org.inbio.m3s.converters.Converter;
import org.inbio.m3s.dto.message.ProjectDTO;
import org.inbio.m3s.gwt.client.dto.metadata.ProjectGWTDTO;

/**
 * @author jgutierrez
 *
 */
public class ProjectConverter extends BaseConverter<ProjectDTO, ProjectGWTDTO> implements Converter<ProjectDTO, ProjectGWTDTO> {

	public ProjectDTO toDTO(ProjectGWTDTO gwtdto) {
		if(gwtdto == null)
			return null;
		
		return new ProjectDTO(gwtdto.getProjectKey(),gwtdto.getName());
	}

	public ProjectGWTDTO toGWTDTO(ProjectDTO dto) {
		if(dto == null)
			return null;
		
		return new ProjectGWTDTO(dto.getProjectKey(),dto.getName());
	}

}
