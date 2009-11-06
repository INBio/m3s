/**
 * 
 */
package org.inbio.m3s.dao.atta.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.InstitutionDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.model.atta.INBioInstitution;
import org.inbio.m3s.model.general.Institution;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

//import org.apache.log4j.Logger;

/**
 * @author jgutierrez
 *
 */
public class INBioInstitutionDAOImpl extends BaseDAOImpl implements InstitutionDAO {
	
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
						+ " from INBioInstitution as i "
						+ " where i.name = '"+ institutionName +"'");
				//query.setParameter(0, nomenclaturalGroupId);
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
						+ " from INBioInstitution as i "
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
		super.create((INBioInstitution) entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.BaseDAO#delete(java.lang.Object)
	 */
	@Override
	public void update(Object entity) throws IllegalArgumentException {
		super.update((INBioInstitution) entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.BaseDAO#update(java.lang.Object)
	 */
	@Override
	public void delete(Object entity) throws IllegalArgumentException {
		super.delete((INBioInstitution) entity);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.BaseDAO#findById(java.lang.Class, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object findById(Class entityClass, Object Id) throws IllegalArgumentException {
		return super.findById(INBioInstitution.class,Id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.BaseDAO#findAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAll(Class entityClass) throws IllegalArgumentException {
		return super.findAll(INBioInstitution.class);
	}



}
