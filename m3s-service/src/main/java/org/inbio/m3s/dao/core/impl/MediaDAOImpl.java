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
import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.model.core.Media;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 * 
 */
public class MediaDAOImpl extends GenericBaseDAOImpl<Media,Integer> implements MediaDAO {

	private static Logger logger = Logger.getLogger(MediaDAOImpl.class);

	
	@SuppressWarnings("unchecked")
	public List<Media> getLastPublicMedia(final int quantity)
			throws IllegalArgumentException {
		logger.debug("getting last public media in the DB");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Media>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select m from Media as m" 
						+ " where m.isPublic = 'Y'"
						+ " order by m.mediaId desc");
				query.setCacheable(true);
				query.setFirstResult(0);
				query.setMaxResults(quantity);
				return query.list();
			}
		});
	}


}
