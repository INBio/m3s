/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.MediaAttributeTypeDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.dto.lite.MediaAttributeTypeLite;
import org.inbio.m3s.model.core.MediaAttributeType;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class MediaAttributeTypeDAOImpl extends BaseDAOImpl implements MediaAttributeTypeDAO {

	private static Logger logger = Logger.getLogger(MediaAttributeTypeDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.interfaces.MediaAttributeTypeDAO#getAllByMediaType(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<MediaAttributeTypeLite> getAllByMediaType(final Integer mediaTypeId)
			throws IllegalArgumentException {
		logger.debug("getAllByMediaType");
		HibernateTemplate template = getHibernateTemplate();
		return (List<MediaAttributeTypeLite>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.MediaAttributeTypeLite(mat.mediaType.mediaTypeId," 
						+	"mat.mediaAttribute.mediaAttributeId, mat.metadataStandard.metadataStandardId," 
						+	"mat.standardAttributeId)"
						+ " from MediaAttributeType as mat"
						+ " where mat.mediaType.mediaTypeId = " + mediaTypeId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<MediaAttributeType> findAllByMediaType(final String mediaTypeKey) throws IllegalArgumentException {
		logger.debug("findAllByMediaType");
		HibernateTemplate template = getHibernateTemplate();
		return (List<MediaAttributeType>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select mat from MediaAttributeType as mat"
						+ " where mat.mediaType.mediaTypeId = " + mediaTypeKey);
				query.setCacheable(true);
				return query.list();
			}
		});
	}
	
}
