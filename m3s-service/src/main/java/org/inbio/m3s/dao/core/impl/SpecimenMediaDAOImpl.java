/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.GenericBaseDAOImpl;
import org.inbio.m3s.dao.core.SpecimenMediaDAO;
import org.inbio.m3s.model.core.SpecimenMedia;
import org.inbio.m3s.model.core.SpecimenMediaId;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class SpecimenMediaDAOImpl extends GenericBaseDAOImpl<SpecimenMedia, SpecimenMediaId> implements SpecimenMediaDAO {
	
	private static Logger logger = Logger.getLogger(SpecimenMediaDAOImpl.class);


	@SuppressWarnings("unchecked")
	public List<SpecimenMediaId> findAllByMediaId(final Integer mediaId)
			throws IllegalArgumentException {
		logger.debug("getAllSpecimensLiteForMedia... start");
		HibernateTemplate template = getHibernateTemplate();
		return (List<SpecimenMediaId>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
					  "select sm.id"
						+ " from SpecimenMedia as sm"
						+ " where sm.id.mediaId = " + mediaId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}

}
