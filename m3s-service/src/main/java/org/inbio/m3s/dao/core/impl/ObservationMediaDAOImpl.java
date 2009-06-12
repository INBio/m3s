/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.ObservationMediaDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.model.core.ObservedTaxonMediaId;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class ObservationMediaDAOImpl extends BaseDAOImpl implements ObservationMediaDAO{

	private static Logger logger = Logger.getLogger(ObservationMediaDAOImpl.class);

	
	@SuppressWarnings("unchecked")
	public List<ObservedTaxonMediaId> findAllByMediaId(final Integer mediaId) throws IllegalArgumentException {
		logger.debug("getAllObservationsLiteForMedia... start");
		HibernateTemplate template = getHibernateTemplate();
		return (List<ObservedTaxonMediaId>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select otm.id" 
					+	" from ObservedTaxonMedia as otm"
					+	" where otm.id.mediaId = " + mediaId);
				query.setCacheable(false);
				return  query.list();
			}
		});
	}
	
}
