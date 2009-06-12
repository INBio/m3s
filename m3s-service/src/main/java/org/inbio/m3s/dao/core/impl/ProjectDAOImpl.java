/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.ProjectDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.dto.lite.ProjectLite;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class ProjectDAOImpl extends BaseDAOImpl implements ProjectDAO{

	private static Logger logger = Logger.getLogger(ProjectDAOImpl.class);
	
	

	public ProjectLite getProjectLite(final Integer projectId) throws IllegalArgumentException {
		logger.debug("getProjectLite for projectId[" + projectId +"]");
		HibernateTemplate template = getHibernateTemplate();
		return (ProjectLite) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.ProjectLite(p.projectId, p.name)"
						+ " from Project as p" 
						+ " where p.projectId = " + projectId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
		
					
	}

	
	public ProjectLite getProjectLite(final String projectName) throws IllegalArgumentException {
		logger.debug("getProjectLite for projectName[" + projectName +"]");
		HibernateTemplate template = getHibernateTemplate();
		return (ProjectLite) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.ProjectLite(p.projectId, p.name)"
						+ " from Project as p" 
						+ " where p.name = '" + projectName+"'");
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<ProjectLite> getAllLite() throws IllegalArgumentException {
		logger.debug("listAllLite");
		HibernateTemplate template = getHibernateTemplate();
		return (List<ProjectLite>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.dto.lite.ProjectLite(p.projectId, p.name)"
						+ " from Project as p");
				query.setCacheable(true);
				return query.list();
			}
		});
		
	}

	@SuppressWarnings("unchecked")
	public List<ProjectLite> getAllProjectLiteForMedia(final Integer mediaId) throws IllegalArgumentException {
		logger.debug("getAllProjectLiteForMedia with param ["+mediaId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<ProjectLite>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.ProjectLite(p.projectId, p.name)"
						+ " from Project as p, MediaProject as mp"
						+ " where mp.id.mediaId = "+ mediaId
						+ " and mp.id.projectId = p.projectId");
				query.setCacheable(true);
				return query.list();
			}
		});
	}

}
