/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.GenericBaseDAOImpl;
import org.inbio.m3s.dao.core.SystemUserDAO;
import org.inbio.m3s.model.core.SystemUser;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class SystemUserDAOImpl extends GenericBaseDAOImpl<SystemUser, Integer> implements
		SystemUserDAO {

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.SystemUserDAO#findByUsername(java.lang.String)
	 */
	public SystemUser findByUsername(final String username) {
		HibernateTemplate template = getHibernateTemplate();
		return (SystemUser) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select su"
						+ " from SystemUser as su"
						+ " where su.username = :username");
				query.setParameter("username", username);
				query.setCacheable(true);
				
				return query.uniqueResult();
			}
		});
	}
}
