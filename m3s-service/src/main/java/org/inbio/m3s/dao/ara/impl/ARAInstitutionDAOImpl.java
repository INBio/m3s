/**
 * 
 */
package org.inbio.m3s.dao.ara.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.GenericBaseDAOImpl;
import org.inbio.m3s.dao.core.InstitutionDAO;
import org.inbio.m3s.model.ara.ARAInstitution;
import org.inbio.m3s.model.general.Institution;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class ARAInstitutionDAOImpl extends GenericBaseDAOImpl<Institution,Integer> implements InstitutionDAO {
	
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
	public void create(Institution institution) throws IllegalArgumentException {
		super.create((ARAInstitution) institution);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.BaseDAO#delete(java.lang.Object)
	 */
	@Override
	public void update(Institution institution) throws IllegalArgumentException {
		super.update((ARAInstitution) institution);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.BaseDAO#update(java.lang.Object)
	 */
	@Override
	public void delete(Institution institution) throws IllegalArgumentException {
		super.delete((ARAInstitution) institution);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.BaseDAO#findById(java.lang.Class, java.lang.Object)
	 */
	@Override
	public Institution findById(Class<Institution> entityClass, Integer id) throws IllegalArgumentException {
		HibernateTemplate template = getHibernateTemplate();
		return (Institution) template.get(ARAInstitution.class, id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.BaseDAO#findAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Institution> findAll(Class<Institution> entityClass) throws IllegalArgumentException {
		//return super.findAll(ARAInstitution.class);
		HibernateTemplate template = getHibernateTemplate();
		return template.loadAll(ARAInstitution.class);
	}

}
