/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.GenericBaseDAOImpl;
import org.inbio.m3s.dao.core.InstitutionDAO;
import org.inbio.m3s.model.general.Institution;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
/**
 * @author jgutierrez
 *
 */
public class M3SInstitutionDAOImpl extends GenericBaseDAOImpl<Institution, Integer> implements InstitutionDAO {
	

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.InstitutionDAO#findByName(java.lang.String)
	 */
	public Institution findByName(final String institutionName) {
		HibernateTemplate template = getHibernateTemplate();
		return (Institution) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select i"
						+ " from org.inbio.m3s.model.core.M3SInstitution as i "
						+ " where i.name = '"+ institutionName +"'");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}


	public List<Institution> findAllByPartialNamePaginated(String partialName,
			int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}




}
