/**
 * 
 */
package org.inbio.m3s.dao.atta.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.GenericBaseDAOImpl;
import org.inbio.m3s.dao.core.SiteDAO;
import org.inbio.m3s.model.general.Site;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 * 
 * 
 * ATTA session
 * 
 * @deprecated Este DAO debe ser elaborado de nuevo, pues no cumple con el patron DAO.
 * 
 */
public class INBioSiteDAOImpl extends GenericBaseDAOImpl<Site, Integer> implements SiteDAO  {

	private static Logger logger = Logger.getLogger(INBioSiteDAOImpl.class);

	/**
	 * 
	 * Gets the site DBid where a Specimen was gathered. Uses a conection the
	 * local atta database, invokes an atta function
	 * 
	 * @param specimenDBId
	 *            Data base identifier of the specimen
	 * @return The site or gathering label of a specimen
	 * @throws IllegalArgumentException
	 *             if the specimenDBId has no match
	 *             
	 *ATTA
	 */
	public String getSiteDBIdFromSpecimenNumber(final Integer specimenDBId)
			throws IllegalArgumentException {
		logger.debug("getDBIdFromSpecimenNumber... start param SpecimenDBId=["
				+ specimenDBId + "]");
		HibernateTemplate template = getHibernateTemplate();
		return (String) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createSQLQuery(
						"select atta.specimen_gathering_label(s.specimen_id) "
						+ "from atta.specimen s where s.specimen_id = "
						+ specimenDBId + " ");
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}

	/**
	 * Gets the site of an observation: site description, observation
	 * surrounding description, observation site description and coordinates.
	 * Uses a conection the local atta database, invokes an atta function
	 * 
	 * @param observationNumber
	 *            Database Identificator fo the observation
	 * @return The site or gathering label of a specimen
	 * @throws IllegalArgumentException
	 *             if the specimenDBId has no match
	 *             
	 *ATTA
	 */
	public String getiteDBIdFromObservationNumber(final Integer observationNumber) throws IllegalArgumentException {
		logger.debug("getDBIdFromObservationNumber... start");
		HibernateTemplate template = getHibernateTemplate();
		return (String) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createSQLQuery(
						"select atta.observation_info(o.observation_id, 1) "
						+ "from atta.observation o where o.observation_id = "
						+ observationNumber + " ");
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});		
	}

}
