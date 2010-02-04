/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.GenericBaseDAOImpl;
import org.inbio.m3s.dao.core.GatheringMediaDAO;
import org.inbio.m3s.model.core.GatheringMedia;
import org.inbio.m3s.model.core.GatheringMediaId;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;


/**
 * @author jgutierrez
 *
 */
public class GatheringMediaDAOImpl extends GenericBaseDAOImpl<GatheringMedia, GatheringMediaId> implements GatheringMediaDAO {
	
	private static Logger logger = Logger.getLogger(GatheringMediaDAOImpl.class);
	

	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.GatheringMediaDAO#findAllByMediaId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<GatheringMediaId> findAllByMediaId(final Integer mediaId)
			throws IllegalArgumentException {
		logger.debug("findAllByMediaId, param[mediaId:"+mediaId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<GatheringMediaId>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.model.core.GatheringMediaId(gm.id.mediaId,gm.id.gatheringDetailPersonId,gm.id.gatheringNumber)"
						+ " from GatheringMedia as gm "
						+ " where gm.id.mediaId = " + mediaId);
				query.setCacheable(false);
				return query.list();
			}
		});
	}


}
