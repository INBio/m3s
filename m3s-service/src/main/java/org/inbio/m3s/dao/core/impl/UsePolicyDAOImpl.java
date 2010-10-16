/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.UsePolicyDAO;
import org.inbio.m3s.dao.GenericBaseDAOImpl;
import org.inbio.m3s.model.core.UsePolicy;
import org.inbio.m3s.service.MessageManager;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class UsePolicyDAOImpl extends GenericBaseDAOImpl<UsePolicy, Integer> implements UsePolicyDAO {
	
	private static Logger logger = Logger.getLogger(UsePolicyDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.UsePolicyDAO#findByName(java.lang.String)
	 */
	public UsePolicy findByName(final String usePolicyText) {
		logger.debug("findByName with param["+usePolicyText+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (UsePolicy) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select up"
						+ " from TextTranslation as tt, UsePolicy as up"
						+ " where tt.locale = :localeKey"
						+ " and tt.name = :usePolicyText"
						+ " and tt.textId = up.textByNameTextId");
				query.setParameter("localeKey", MessageManager.DEFAULT_LOCALE);
				query.setParameter("usePolicyText", usePolicyText);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}
	
	

}
