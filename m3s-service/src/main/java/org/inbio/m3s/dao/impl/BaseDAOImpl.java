/**
 * 
 */
package org.inbio.m3s.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author jgutierrez
 * 
 */
public class BaseDAOImpl extends HibernateDaoSupport implements BaseDAO {

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.BaseDAO#create(java.lang.Object)
	 */
	public void create(Object entity) throws IllegalArgumentException {
		HibernateTemplate template = getHibernateTemplate();
		template.persist(entity);
		template.flush();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.BaseDAO#delete(java.lang.Object)
	 */
	public void update(Object entity) throws IllegalArgumentException {
		HibernateTemplate template = getHibernateTemplate();
		template.update(entity);
		template.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.BaseDAO#update(java.lang.Object)
	 */
	public void delete(Object entity) throws IllegalArgumentException {
		HibernateTemplate template = getHibernateTemplate();
		template.delete(entity);
		template.flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.BaseDAO#findById(java.lang.Class, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public Object findById(Class entityClass, Object Id) throws IllegalArgumentException {
		HibernateTemplate template = getHibernateTemplate();
		return template.get(entityClass, (Serializable) Id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.BaseDAO#findAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAll(Class entityClass) throws IllegalArgumentException {
		HibernateTemplate template = getHibernateTemplate();
		return template.loadAll(entityClass);
	}
		
}
