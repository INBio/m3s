/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.dto.GeneralMetadataDTO;
import org.inbio.m3s.dto.metadata.UsesAndCopyrightsDTO;
import org.inbio.m3s.dto.lite.MediaLite;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 * 
 */
public class MediaDAOImpl extends BaseDAOImpl implements MediaDAO {

	private static Logger logger = Logger.getLogger(MediaDAOImpl.class);

		/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.core.impl.MediaDAO#getLastPublicMedia(int)
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
						+ " m.mediaType.mediaTypeId, m.usePolicy.usePolicyId, m.authorPersonId,"
						+ " m.ownerPersonId, m.ownerInstitutionId, m.description, m.siteId,"
						+ " m.siteDescription, m.title, m.series, m.isPublic, m.isBackup,"
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

	public MediaLite getMediaLite(final Integer mediaId)
			throws IllegalArgumentException {
		logger.debug("getMediaLite with param [" + mediaId + "]");
		HibernateTemplate template = getHibernateTemplate();
		return (MediaLite) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.MediaLite(m.mediaId,"
						+ " m.mediaType.mediaTypeId, m.usePolicy.usePolicyId, m.authorPersonId,"
						+ " m.ownerPersonId, m.ownerInstitutionId, m.description, m.siteId,"
						+ " m.siteDescription, m.title, m.series, m.isPublic, m.isBackup,"
						+ " m.creationDate, m.createdBy, m.lastModificationDate, m.lastModificationBy)"
						+ " from Media as m" + " where m.mediaId = " + mediaId + " ");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<MediaLite> getMediaLiteForTaxonId(final Integer taxonId) throws IllegalArgumentException {
		logger.debug("getMediaLiteForTaxonId with param [" + taxonId + "]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<MediaLite>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.MediaLite(m.mediaId,"
						+ " m.mediaType.mediaTypeId, m.usePolicy.usePolicyId, m.authorPersonId,"
						+ " m.ownerPersonId, m.ownerInstitutionId, m.description, m.siteId,"
						+ " m.siteDescription, m.title, m.series, m.isPublic, m.isBackup,"
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

	public GeneralMetadataDTO getGeneralMetadataDTO(final Integer mediaId) {
		logger.debug("getGeneralMetadataDTO with param [" + mediaId + "]");
		HibernateTemplate template = getHibernateTemplate();
		return (GeneralMetadataDTO) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.GeneralMetadataDTO(" +
						"m.title, m.description, m.mediaType.mediaTypeId, m.siteId, "
						+ "m.siteDescription)"
						+ " from Media as m"
						+ " where m.mediaId = "	+ mediaId);
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}

	public UsesAndCopyrightsDTO getUsesAndCopyrightsDTO(final Integer mediaId) {
		logger.debug("UsesAndCopyrightsDTO with param [" + mediaId + "]");
		HibernateTemplate template = getHibernateTemplate();
		return (UsesAndCopyrightsDTO) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.metadata.UsesAndCopyrightsDTO(" +
						" m.authorPersonId, m.ownerPersonId, m.ownerInstitutionId, "
						+ "m.usePolicy.usePolicyId, m.isBackup, m.isPublic)"
						+ " from Media as m"
						+ " where m.mediaId = "	+ mediaId);
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}


}
