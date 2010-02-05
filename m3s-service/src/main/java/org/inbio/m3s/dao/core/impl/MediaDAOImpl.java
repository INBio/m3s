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
import org.inbio.m3s.dto.lite.MediaLite;
import org.inbio.m3s.model.core.Media;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 * 
 */
public class MediaDAOImpl extends GenericBaseDAOImpl<Media,Integer> implements MediaDAO {

	private static Logger logger = Logger.getLogger(MediaDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.core.impl.MediaDAO#getLastPublicMedia(int)
	 * @deprecated
	 */
	@SuppressWarnings("unchecked")
	public List<MediaLite> getLastPublicMedia(final int quantity)
			throws IllegalArgumentException {
		logger.debug("getting last public media in the DB");
		HibernateTemplate template = getHibernateTemplate();
		return (List<MediaLite>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.MediaLite(m.mediaId,"
						+ " m.mediaTypeId, m.usePolicyId, m.authorPersonId,"
						+ " m.ownerPersonId, m.ownerInstitutionId, m.description, m.siteId,"
						+ " m.siteDescription, m.title, m.series, m.isPublic,"
						+ " m.creationDate, m.createdBy, m.lastModificationDate, m.lastModificationBy)"
						+ " from Media as m" + " where m.isPublic = 'Y'"
						+ " order by m.mediaId desc");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				query.setFirstResult(0);
				query.setMaxResults(quantity);
				return query.list();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.MediaDAO#getMediaLite(java.lang.Integer)
	 * @deprecated
	 */
	public MediaLite getMediaLite(final Integer mediaId)
			throws IllegalArgumentException {
		logger.debug("getMediaLite with param [" + mediaId + "]");
		HibernateTemplate template = getHibernateTemplate();
		return (MediaLite) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.MediaLite(m.mediaId,"
						+ " m.mediaTypeId, m.usePolicyId, m.authorPersonId,"
						+ " m.ownerPersonId, m.ownerInstitutionId, m.description, m.siteId,"
						+ " m.siteDescription, m.title, m.series, m.isPublic,"
						+ " m.creationDate, m.createdBy, m.lastModificationDate, m.lastModificationBy)"
						+ " from Media as m" + " where m.mediaId = " + mediaId + " ");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.MediaDAO#getMediaLiteForTaxonId(java.lang.Integer)
	 * @deprecated
	 */
	@SuppressWarnings("unchecked")
	public List<MediaLite> getMediaLiteForTaxonId(final Integer taxonId) throws IllegalArgumentException {
		logger.debug("getMediaLiteForTaxonId with param [" + taxonId + "]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<MediaLite>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.MediaLite(m.mediaId,"
						+ " m.mediaTypeId, m.usePolicyId, m.authorPersonId,"
						+ " m.ownerPersonId, m.ownerInstitutionId, m.description, m.siteId,"
						+ " m.siteDescription, m.title, m.series, m.isPublic,"
						+ " m.creationDate, m.createdBy, m.lastModificationDate, m.lastModificationBy)"
						+ " from Media as m, TaxonMedia as tm" 
						+ " where tm.id.mediaId = m.mediaId"
						+	" and tm.id.taxonId = "+ taxonId
						+ " and m.isPublic = 'Y'"
						+ " order by m.mediaId desc");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.MediaDAO#getGeneralMetadataDTO(java.lang.String)
	 * @deprecated
	public GeneralMetadataDTO getGeneralMetadataDTO(final String mediaId) {
		logger.debug("getGeneralMetadataDTO with param [" + mediaId + "]");
		HibernateTemplate template = getHibernateTemplate();
		return (GeneralMetadataDTO) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.metadata.GeneralMetadataDTO(" +
						"m.title, m.description, m.mediaTypeId, m.siteId, "
						+ "m.siteDescription)"
						+ " from Media as m"
						+ " where m.mediaId = "	+ mediaId);
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}
		 */

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.MediaDAO#getUsesAndCopyrightsDTO(java.lang.Integer)
	 * @deprecated
	public UsesAndCopyrightsDTO getUsesAndCopyrightsDTO(final Integer mediaId) {
		logger.debug("UsesAndCopyrightsDTO with param [" + mediaId + "]");
		HibernateTemplate template = getHibernateTemplate();
		return (UsesAndCopyrightsDTO) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.metadata.UsesAndCopyrightsDTO(" +
						" m.authorPersonId, m.ownerPersonId, m.ownerInstitutionId, "
						+ "m.usePolicyId, m.isPublic)"
						+ " from Media as m"
						+ " where m.mediaId = "	+ mediaId);
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}
 */


}
