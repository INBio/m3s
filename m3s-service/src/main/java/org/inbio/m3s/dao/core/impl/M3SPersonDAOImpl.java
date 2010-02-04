/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.inbio.m3s.dao.GenericBaseDAOImpl;
import org.inbio.m3s.dao.core.PersonDAO;
import org.inbio.m3s.model.general.Person;

/**
 * @author jgutierrez
 * 
 */
public class M3SPersonDAOImpl extends GenericBaseDAOImpl<Person, Integer> implements PersonDAO {	
	public List<Person> findAllGatheringResponsible()
			throws IllegalArgumentException {
		return null;
	}

	public Person findGatheringResposibleById(Integer responsiblePersonId)
			throws IllegalArgumentException {
		return (Person) findById(Person.class, responsiblePersonId);
	}
	
	public List<Person> findAllByPartialNamePaginated(String string, int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
