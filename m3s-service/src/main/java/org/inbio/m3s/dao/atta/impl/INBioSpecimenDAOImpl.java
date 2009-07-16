/**
 * 
 */
package org.inbio.m3s.dao.atta.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.SpecimenDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;

import org.inbio.m3s.model.general.Specimen;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class INBioSpecimenDAOImpl extends BaseDAOImpl implements SpecimenDAO{
	
	private static Logger logger = Logger.getLogger(INBioSpecimenDAOImpl.class);
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.SpecimenDAO#findByGatheringNumberAndGatheringPersonId(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<Specimen> findByGatheringNumberAndGatheringPersonId(
			final Integer gatheringPersonId, final Integer gatheringNumber)
			throws IllegalArgumentException {
		logger.debug("getSpecimenLiteFromGathering... start");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Specimen>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				/**
				 * The gathering number min and max is a stupid and wired way of make a
				 * query where the s.gatheringNumber = gatheringNumber, because when I
				 * do that hibernate doesn't return anything
				 */
				Integer gatheringNumberMin = gatheringNumber - 1;
				Integer gatheringNumberMax = gatheringNumber + 1;
				Query query = session.createQuery("select s" + " from INBioSpecimen as s "
						+ " where s.gatheringDetailPersonId = " + gatheringPersonId
						+ " and s.gatheringNumber > " + gatheringNumberMin
						+ " and s.gatheringNumber < " + gatheringNumberMax);
				query.setCacheable(true);
				return query.list();
			}
		});
	}


}
