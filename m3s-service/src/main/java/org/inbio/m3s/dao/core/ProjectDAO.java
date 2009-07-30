/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.dto.message.ProjectDTO;
import org.inbio.m3s.model.core.Project;

/**
 * @author jgutierrez
 *
 */
public interface ProjectDAO extends BaseDAO {
	
	@Deprecated
	public ProjectDTO getProjectLite(Integer projectId) throws IllegalArgumentException;
	
	@Deprecated
	public ProjectDTO getProjectLite(String projectName) throws IllegalArgumentException;
	
	@Deprecated
	public List<ProjectDTO> getAllLite() throws IllegalArgumentException;
	
	@Deprecated
	public List<ProjectDTO> getAllProjectLiteForMedia(Integer mediaId) throws IllegalArgumentException;

	public Project findByName(String projectName) throws IllegalArgumentException;

}
