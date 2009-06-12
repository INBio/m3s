/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.StatisticsDAO;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author jgutierrez
 *
 */
public class StatisticsDAOImpl extends HibernateDaoSupport implements StatisticsDAO {

	private final Integer DSC_MEDIA_TYPE_ID = new Integer(1);
	
	private final Integer MOV_VIDEO_MEDIA_TYPE_ID = new Integer(4);
	
	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.StatisticsDAO#DSCPhotosCount()
	 */
	public Integer DSCPhotosCount() throws IllegalArgumentException {
		HibernateTemplate template = getHibernateTemplate();
		return (Integer) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select count(m) from Media m where m.mediaType.mediaTypeId = "
						+ DSC_MEDIA_TYPE_ID);
				query.setCacheable(true);
				return query.list().get(0);
			}
		});
	}
	

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.StatisticsDAO#allMediaCount()
	 */
	public Integer allMediaCount() throws IllegalArgumentException {
		HibernateTemplate template = getHibernateTemplate();
		return (Integer) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select count(m) from Media m");
				query.setCacheable(true);
				return query.list().get(0);
			}
		});
	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.StatisticsDAO#videosCount()
	 */
	public Integer videosCount() throws IllegalArgumentException {
		HibernateTemplate template = getHibernateTemplate();
		return (Integer) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select count(m) from Media m where m.mediaType.mediaTypeId = "
							+ MOV_VIDEO_MEDIA_TYPE_ID);
				query.setCacheable(true);
				return query.list().get(0);
			}
		});
	}

}
