/**
 * 
 */
package org.inbio.m3s.service.autocomplete.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.dao.core.PersonDAO;
import org.inbio.m3s.model.general.Person;
import org.inbio.m3s.service.autocomplete.AutoCompleteManager;

/**
 * @author jgutierrez
 * 
 */
public class PersonNameAutoCompleteManagerImpl implements AutoCompleteManager {

	protected static Log logger = LogFactory.getLog(PersonNameAutoCompleteManagerImpl.class);
	
	// DAO's
	private PersonDAO personDAO;

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.AutoCompleteManager#getAutoCompleteOptions(java.lang.String)
	 */
	public Map<Integer, String> getAutoCompleteOptions(String value) {
		
		List<Person> pList = personDAO.findAllByPartialNamePaginated("%"+value+"%", 20);
		Map<Integer, String> results = new HashMap<Integer, String>();
		
		for(Person p: pList)
			results.put(p.getPersonId(), p.getFirstName() + " " +p.getLastName());
		
		return results;
	}

	/**
	 * @return the personDAO
	 */
	public PersonDAO getPersonDAO() {
		return personDAO;
	}

	/**
	 * @param personDAO the personDAO to set
	 */
	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}


}
