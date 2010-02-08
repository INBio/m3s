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
import org.inbio.m3s.dao.core.MediaAttributeTypeDAO;
import org.inbio.m3s.model.core.MediaAttributeType;
import org.inbio.m3s.model.core.MediaAttributeTypeId;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class MediaAttributeTypeDAOImpl extends GenericBaseDAOImpl<MediaAttributeType, MediaAttributeTypeId> implements MediaAttributeTypeDAO {

	private static Logger logger = Logger.getLogger(MediaAttributeTypeDAOImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<MediaAttributeType> findAllByMediaType(final String mediaTypeKey) throws IllegalArgumentException {
		logger.debug("findAllByMediaType");
		HibernateTemplate template = getHibernateTemplate();
		return (List<MediaAttributeType>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select mat from MediaAttributeType as mat"
						+ " where mat.mediaTypeId = " + mediaTypeKey);
				query.setCacheable(true);
				return query.list();
			}
		});
	}
	
}
