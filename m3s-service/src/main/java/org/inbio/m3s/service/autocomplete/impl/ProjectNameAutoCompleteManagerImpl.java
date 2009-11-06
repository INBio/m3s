/**
 * 
 */
package org.inbio.m3s.service.autocomplete.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.dao.core.ProjectDAO;
import org.inbio.m3s.model.core.Project;
import org.inbio.m3s.service.autocomplete.AutoCompleteManager;

/**
 * @author jgutierrez
 * 
 */
public class ProjectNameAutoCompleteManagerImpl implements AutoCompleteManager {

	protected static Log logger = LogFactory.getLog(ProjectNameAutoCompleteManagerImpl.class);
	
	// DAO's
	private ProjectDAO projectDAO;

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.AutoCompleteManager#getAutoCompleteOptions(java.lang.String)
	 */
	public Map<Integer, String> getAutoCompleteOptions(String value) {
		
		List<Project> pList = projectDAO.findAllByPartialNamePaginated("%"+value+"%", 20);
		Map<Integer, String> results = new HashMap<Integer, String>();
		
		for(Project p: pList)
			results.put(p.getProjectId(), p.getName());
		
		return results;
	}

	/**
	 * @return the projectDAO
	 */
	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	/**
	 * @param projectDAO the projectDAO to set
	 */
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}


}
