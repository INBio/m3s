/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.GenericBaseDAOImpl;
import org.inbio.m3s.dao.core.SecurityUserDAO;
import org.inbio.m3s.model.core.SecurityUsers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class SecurityUserDAOImpl extends GenericBaseDAOImpl<SecurityUsers, String> implements SecurityUserDAO {

	@SuppressWarnings("unchecked")
	public List<SecurityUsers> findAllByUserName(final String username)
			throws IllegalArgumentException {
		HibernateTemplate template = getHibernateTemplate();
		return (List<SecurityUsers>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select su"
						+ " from SecurityUsers as su" 
						+ " where su.name = '" + username + "'");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}


}
