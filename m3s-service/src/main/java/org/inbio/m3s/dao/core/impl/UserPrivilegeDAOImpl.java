/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.UserPrivilegeDAO;
import org.inbio.m3s.model.core.UserProjectPrivilegeId;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author jgutierrez
 * @deprecated
 *
 */
public class UserPrivilegeDAOImpl extends HibernateDaoSupport implements UserPrivilegeDAO {
	
	private static Logger logger = Logger.getLogger(UserPrivilegeDAOImpl.class);
	
	// project privileges
	private static final String READ = "Lectura";

	private static final String WRITE = "Escritura";

	//private static final String ADMIN = "Administración";

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.interfaces.UserPrivilegeDAO#couldRead(java.lang.String, java.lang.Integer)
	 */
	public boolean couldRead(String username, Integer projectId)
			throws IllegalArgumentException {
		return hasPrivilege(username, projectId, READ);
	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.interfaces.UserPrivilegeDAO#couldWrite(java.lang.String, java.lang.Integer)
	 */
	public boolean couldWrite(String username, Integer projectId)
			throws IllegalArgumentException {
		return hasPrivilege(username, projectId, WRITE);
	}
	
	/**
	 * 
	 * @param username
	 * @param projectId
	 * @param privilegeName
	 *            is a constant of the Properties class
	 * @return
	 * @throws IllegalArgumentException
	 *             if the query using the method params generate an exception
	 */
	private Boolean hasPrivilege(final String username, final Integer projectId,
			final String privilegeName) throws IllegalArgumentException {
		logger.debug("hasPrivilege... start ");
		HibernateTemplate template = getHibernateTemplate();
		return (Boolean) template.execute(new HibernateCallback() {
			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select upv.UserProjectPrivilegeId "
						+ "from UserProjectPrivilege as upv "
						+ "where upv.project.projectId = " + projectId
						+ " " + "and upv.securityUsers.name = " + username
						+ " " + "and upv.privilege.name = " + privilegeName
						+ " ");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				List<UserProjectPrivilegeId> result = query.list();
				if (result.size() != 1) {
					logger.error("hasPrivilege... the user has no privileges to ["
							+ privilegeName + "] on this "
							+ "project or has more than one privilege");
					return new Boolean(false);
				}
				logger.debug("hasPrivilege... done");
				return new Boolean (true);
			}
		});
	}

}
