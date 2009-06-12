/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.TaxonMediaDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.model.core.TaxonMediaId;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class TaxonMediaDAOImpl extends BaseDAOImpl implements TaxonMediaDAO {

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.TaxonMediaDAO#getTaxonIdByMediaId()
	@SuppressWarnings("unchecked")
	public List<Integer> getTaxonIdByMediaId(final Integer mediaId) {
		logger.debug("getTaxonIdByMediaId... with param ["+mediaId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Integer>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select distinct tm.id.taxonId from TaxonMedia as tm "
						+ "where tm.id.mediaId = " + mediaId + "");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}
	 */


	@SuppressWarnings("unchecked")
	public List<TaxonMediaId> getByMediaId(final Integer mediaId) {
		logger.debug("getByMediaId... with param ["+mediaId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<TaxonMediaId>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						  "select distinct tm.id" 
						+	" from TaxonMedia as tm "
						+ " where tm.id.mediaId = " + mediaId + "");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}

}
