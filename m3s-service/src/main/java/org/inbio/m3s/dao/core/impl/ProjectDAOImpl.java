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
import org.inbio.m3s.dto.message.ProjectDTO;
import org.inbio.m3s.model.core.Project;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class ProjectDAOImpl extends BaseDAOImpl implements ProjectDAO{

	private static Logger logger = Logger.getLogger(ProjectDAOImpl.class);
	
	

	public ProjectDTO getProjectLite(final Integer projectId) throws IllegalArgumentException {
		logger.debug("getProjectLite for projectId[" + projectId +"]");
		HibernateTemplate template = getHibernateTemplate();
		return (ProjectDTO) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.message.ProjectDTO(p.projectId, p.name)"
						+ " from Project as p" 
						+ " where p.projectId = " + projectId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
		
					
	}

	
	public ProjectDTO getProjectLite(final String projectName) throws IllegalArgumentException {
		logger.debug("getProjectLite for projectName[" + projectName +"]");
		HibernateTemplate template = getHibernateTemplate();
		return (ProjectDTO) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.message.ProjectDTO(p.projectId, p.name)"
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
	public List<ProjectDTO> getAllLite() throws IllegalArgumentException {
		logger.debug("listAllLite");
		HibernateTemplate template = getHibernateTemplate();
		return (List<ProjectDTO>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.message.ProjectDTO(p.projectId, p.name)"
						+ " from Project as p");
				query.setCacheable(true);
				return query.list();
			}
		});
		
	}

	@SuppressWarnings("unchecked")
	public List<ProjectDTO> getAllProjectLiteForMedia(final Integer mediaId) throws IllegalArgumentException {
		logger.debug("getAllProjectLiteForMedia with param ["+mediaId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<ProjectDTO>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.message.ProjectDTO(p.projectId, p.name)"
						+ " from Project as p, MediaProject as mp"
						+ " where mp.id.mediaId = "+ mediaId
						+ " and mp.id.projectId = p.projectId");
				query.setCacheable(true);
				return query.list();
			}
		});
	}


	public Project findByName(final String projectName) throws IllegalArgumentException {
		logger.debug("findByName with[" + projectName +"]");
		HibernateTemplate template = getHibernateTemplate();
		return (Project) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select p from Project as p" 
						+ " where p.name = '" + projectName+"'");
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Project> findAllByPartialNamePaginated(final String partialName, final int maxResults) {
		logger.debug("findAllByPartialNamePaginated with[" + partialName +"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Project>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select p from Project as p" 
						+ " where p.name like :projectName");
				query.setParameter("projectName", partialName);
				query.setFirstResult(0);
				query.setMaxResults(maxResults);				
				query.setCacheable(true);					
				return query.list();
			}
		});
	}

}
