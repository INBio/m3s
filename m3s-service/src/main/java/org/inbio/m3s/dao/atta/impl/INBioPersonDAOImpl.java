/**
 * 
 */
package org.inbio.m3s.dao.atta.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.PersonDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.model.atta.INBioPerson;
import org.inbio.m3s.model.general.Person;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class INBioPersonDAOImpl extends BaseDAOImpl implements PersonDAO {
	
	//private static Logger logger = Logger.getLogger(INBioPersonDAOImpl.class);

	public static final Integer IMAGES_PROCESOR_PROFILE_ID = new Integer(12);
	public static final Integer IMAGES_AUTHOR_PROFILE_ID = new Integer(21);
	public static final Integer GATHERING_PROFILE_ID = new Integer(3);
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAll(Class entityClass) {
		HibernateTemplate template = getHibernateTemplate();
		return (List<Object>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select pp.person"
						+ " from org.inbio.m3s.model.atta.PersonProfile as pp"
						+ " where pp.profile.profileId = " + IMAGES_PROCESOR_PROFILE_ID
						+ " or pp.profile.profileId = " 	+ IMAGES_AUTHOR_PROFILE_ID
						+ " and pp.person.firstName is not null "
						+ " and pp.person.lastName is not null "
						+ " order by pp.person.firstName asc, pp.person.lastName asc");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Person> findAllByPartialNamePaginated(final String authorName, final int maxResults) {
		logger.debug("findAllByPartialNamePaginated for author name: '" + authorName + "'.");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Person>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select pp.person"
						+ " from org.inbio.m3s.model.atta.PersonProfile as pp"
						+ " where ( pp.profile.profileId =" + IMAGES_PROCESOR_PROFILE_ID
						+         " or pp.profile.profileId =" + IMAGES_AUTHOR_PROFILE_ID + ")"
						+ " and ( pp.person.firstName is not null "
						+       " and pp.person.lastName is not null )"
						+ " and ( pp.person.firstName like :authorName "
						+       " or pp.person.lastName like :authorName )"
						+ " order by pp.person.firstName asc, pp.person.lastName asc");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setParameter("authorName", authorName);
				query.setFirstResult(0);
				query.setMaxResults(maxResults);				
				query.setCacheable(true);					
				return query.list();
			}
		});
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Person> findAllGatheringResponsible()
			throws IllegalArgumentException {
		HibernateTemplate template = getHibernateTemplate();
		return (List<Person>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select pp.person"
						+ " from org.inbio.m3s.model.atta.PersonProfile as pp"
						+ " where pp.profile.profileId = " + GATHERING_PROFILE_ID
						+ " and pp.person.firstName is not null "
						+ " and pp.person.lastName is not null "
						+ " order by pp.person.firstName asc, pp.person.lastName asc");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	public Person findGatheringResposibleById(final Integer responsiblePersonId)
			throws IllegalArgumentException {
		HibernateTemplate template = getHibernateTemplate();
		return (Person) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select pp.person"
						+ " from org.inbio.m3s.model.atta.PersonProfile as pp"
						+ " where pp.profile.profileId = " + GATHERING_PROFILE_ID
						+ " and pp.person.personId = "+ responsiblePersonId);
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}	
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.impl.BaseDAOImpl#create(java.lang.Object)
	 */
	@Override
	public void create(Object entity) throws IllegalArgumentException {
		super.create((INBioPerson) entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.BaseDAO#delete(java.lang.Object)
	 */
	@Override
	public void update(Object entity) throws IllegalArgumentException {
		super.update((INBioPerson) entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.BaseDAO#update(java.lang.Object)
	 */
	@Override
	public void delete(Object entity) throws IllegalArgumentException {
		super.delete((INBioPerson) entity);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.BaseDAO#findById(java.lang.Class, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object findById(Class entityClass, Object Id) throws IllegalArgumentException {
		return super.findById(INBioPerson.class,Id);
	}


}
