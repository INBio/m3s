/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.inbio.m3s.dao.core.PersonDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.model.core.M3SPerson;
import org.inbio.m3s.model.general.Person;

/**
 * @author jgutierrez
 * 
 */
public class M3SPersonDAOImpl extends BaseDAOImpl implements PersonDAO {
	
	//private static Logger logger = Logger.getLogger(M3SPersonDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.core.PersonDAO#listAllLite()
	 */
	/*
	@SuppressWarnings("unchecked")
	public List<PersonLite> listAllLite() throws Exception {
		logger.debug("getting ALL people Info in lite version...");

		return HibernateUtil
				.simpleHSQLQuery(
						"select new org.inbio.m3s.model.lite.PersonLite("
									+ "p.personId, p.firstName,  p.lastName)"
									+ "from M3SPerson as p "
									+ "where p.firstName is not null "
									+ "and p.lastName is not null "
									+ "order by p.firstName asc, p.lastName asc", HibernateUtil.openM3SDBSession());
	}
	*/

	public List<Person> findAllGatheringResponsible()
			throws IllegalArgumentException {
		return null;
	}

	public Person findGatheringResposibleById(Integer responsiblePersonId)
			throws IllegalArgumentException {
		return (Person) super.findById(M3SPerson.class, responsiblePersonId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.impl.BaseDAOImpl#create(java.lang.Object)
	 */
	@Override
	public void create(Object entity) throws IllegalArgumentException {
		super.create((M3SPerson) entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.BaseDAO#delete(java.lang.Object)
	 */
	@Override
	public void update(Object entity) throws IllegalArgumentException {
		super.update((M3SPerson) entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.BaseDAO#update(java.lang.Object)
	 */
	@Override
	public void delete(Object entity) throws IllegalArgumentException {
		super.delete((M3SPerson) entity);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.BaseDAO#findById(java.lang.Class, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object findById(Class entityClass, Object Id) throws IllegalArgumentException {
		return super.findById(M3SPerson.class,Id);
	}

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.BaseDAO#findAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAll(Class entityClass) throws IllegalArgumentException {
		return super.findAll(M3SPerson.class);
	}

}
