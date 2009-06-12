/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.dto.lite.ProjectLite;

/**
 * @author jgutierrez
 *
 */
public interface ProjectDAO extends BaseDAO {
	
	public ProjectLite getProjectLite(Integer projectId) throws IllegalArgumentException;
	
	public ProjectLite getProjectLite(String projectName) throws IllegalArgumentException;
	
	public List<ProjectLite> getAllLite() throws IllegalArgumentException;
	
	public List<ProjectLite> getAllProjectLiteForMedia(Integer mediaId) throws IllegalArgumentException;

}
