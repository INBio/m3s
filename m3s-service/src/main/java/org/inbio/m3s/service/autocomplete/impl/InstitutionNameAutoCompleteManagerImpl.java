/**
 * 
 */
package org.inbio.m3s.service.autocomplete.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.dao.core.InstitutionDAO;
import org.inbio.m3s.model.general.Institution;
import org.inbio.m3s.service.autocomplete.AutoCompleteManager;

/**
 * @author jgutierrez
 * 
 */
public class InstitutionNameAutoCompleteManagerImpl implements AutoCompleteManager {

	protected static Log logger = LogFactory.getLog(InstitutionNameAutoCompleteManagerImpl.class);
	
	// DAO's
	private InstitutionDAO institutionDAO;

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.AutoCompleteManager#getAutoCompleteOptions(java.lang.String)
	 */
	public Map<Integer, String> getAutoCompleteOptions(String value) {
		
		List<Institution> iList = institutionDAO.findAllByPartialNamePaginated("%"+value+"%", 20);
		Map<Integer, String> results = new HashMap<Integer, String>();
		
		for(Institution i: iList)
			results.put(i.getInstitutionId(), i.getName());
		
		return results;
	}

	/**
	 * @return the institutionDAO
	 */
	public InstitutionDAO getInstitutionDAO() {
		return institutionDAO;
	}

	/**
	 * @param institutionDAO the institutionDAO to set
	 */
	public void setInstitutionDAO(InstitutionDAO institutionDAO) {
		this.institutionDAO = institutionDAO;
	}



}
