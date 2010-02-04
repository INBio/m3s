/* M3S - multimedia management system
*
* Copyright (C) 2009  INBio - Instituto Nacional de Biodiversidad, Costa Rica
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.GenericBaseDAOImpl;
import org.inbio.m3s.dao.core.ProjectDAO;
import org.inbio.m3s.model.core.Project;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class ProjectDAOImpl extends GenericBaseDAOImpl<Project,Integer> implements ProjectDAO{

	private static Logger logger = Logger.getLogger(ProjectDAOImpl.class);
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.ProjectDAO#findAllByMedia(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<Project> findAllByMedia(final Integer mediaId) throws IllegalArgumentException {
		logger.debug("findAllByMedia with param ["+mediaId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Project>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select p from Project as p, MediaProject as mp"
						+ " where mp.id.mediaId = :mediaId"
						+ " and mp.id.projectId = p.projectId");
				query.setParameter("mediaId", mediaId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.ProjectDAO#findByName(java.lang.String)
	 */
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

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.ProjectDAO#findAllByPartialNamePaginated(java.lang.String, int)
	 */
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
