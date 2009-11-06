/**
 * 
 */
package org.inbio.m3s.dao.ara.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.InstitutionDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.model.ara.ARAInstitution;
import org.inbio.m3s.model.general.Institution;
import org.inbio.m3s.model.general.Person;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class ARAInstitutionDAOImpl extends BaseDAOImpl implements InstitutionDAO {

	//private static Logger logger = Logger.getLogger(ARAInstitutionDAOImpl.class);
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.InstitutionDAO#findByName(java.lang.String)
	 */
	public Institution findByName(final String institutionName) {
		//logger.debug("getting ALL Institutions Info in lite version...");
		HibernateTemplate template = getHibernateTemplate();
		return (Institution) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select i"
						+ " from ARAInstitution as i "
						+ " where i.name = :institutionName");
				query.setParameter("institutionName", institutionName);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.InstitutionDAO#findAllByPartialNamePaginated(java.lang.String, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Institution> findAllByPartialNamePaginated(final String partialName, final int maxResults) {
		logger.debug("findAllByPartialNamePaginated for institution name: '" + partialName + "'.");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Institution>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select i"
						+ " from ARAInstitution as i "
						+ " where i.name like :institutionName");
				query.setParameter("institutionName", partialName);
				query.setFirstResult(0);
				query.setMaxResults(maxResults);				
				query.setCacheable(true);					
				return query.list();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.impl.BaseDAOImpl#create(java.lang.Object)
	 */
	@Override
	public void create(Object entity) throws IllegalArgumentException {
		super.create((ARAInstitution) entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.BaseDAO#delete(java.lang.Object)
	 */
	@Override
	public void update(Object entity) throws IllegalArgumentException {
		super.update((ARAInstitution) entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.BaseDAO#update(java.lang.Object)
	 */
	@Override
	public void delete(Object entity) throws IllegalArgumentException {
		super.delete((ARAInstitution) entity);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.BaseDAO#findById(java.lang.Class, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object findById(Class entityClass, Object Id) throws IllegalArgumentException {
		return super.findById(ARAInstitution.class,Id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.BaseDAO#findAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAll(Class entityClass) throws IllegalArgumentException {
		return super.findAll(ARAInstitution.class);
	}


}
