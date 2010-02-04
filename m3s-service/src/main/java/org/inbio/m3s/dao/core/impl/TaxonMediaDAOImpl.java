/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.GenericBaseDAOImpl;
import org.inbio.m3s.dao.core.TaxonMediaDAO;
import org.inbio.m3s.model.core.TaxonMedia;
import org.inbio.m3s.model.core.TaxonMediaId;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class TaxonMediaDAOImpl extends GenericBaseDAOImpl<TaxonMedia, TaxonMediaId> implements TaxonMediaDAO {


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
