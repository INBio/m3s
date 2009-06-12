/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.model.general.Person;

/**
 * @author jgutierrez
 *
 */
public interface PersonDAO extends BaseDAO {
	
	
	public Person findGatheringResposibleById(Integer responsiblePersonId)	throws IllegalArgumentException;
	
	public List<Person> findAllGatheringResponsible() throws IllegalArgumentException;

}
