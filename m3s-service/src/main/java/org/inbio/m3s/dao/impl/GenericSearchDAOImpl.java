/**
 * 
 */
package org.inbio.m3s.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.GenericSearchDAO;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author jgutierrez
 *
 */
public class GenericSearchDAOImpl extends HibernateDaoSupport implements GenericSearchDAO {

	/**
	 * Returns the total of elements that could be retrived using that search
	 * criteria
	 * 
	 * @return number of results
	 */
	public Integer getTotalResults(final String HSQL) throws IllegalArgumentException {
		logger.debug("query: " + HSQL);
		HibernateTemplate template = getHibernateTemplate();
		return (Integer) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(HSQL);
				query.setCacheable(false);
				logger.debug("result: " + new Integer ( query.uniqueResult().toString()));
				return new Integer ( query.uniqueResult().toString());
			}
		});		
		
	}

	/**
	 * 
	 * @param searchCriteria
	 * @param first
	 * @param last
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getResults(final String HSQL, final int first, final int last) throws IllegalArgumentException {

		logger.debug("query getResults: " + HSQL);
		HibernateTemplate template = getHibernateTemplate();
		return (List<Integer>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(HSQL);
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(false);
				query.setFirstResult(first-1);
				query.setMaxResults(last-(first-1));
				return query.list();
			}
		});		
	}
	
	
}
