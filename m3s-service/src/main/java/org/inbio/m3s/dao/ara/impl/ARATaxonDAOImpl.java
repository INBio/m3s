/**
 * 
 */
package org.inbio.m3s.dao.ara.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.GenericBaseDAOImpl;
import org.inbio.m3s.dao.core.TaxonDAO;
import org.inbio.m3s.model.ara.ARATaxon;
import org.inbio.m3s.model.taxonomy.Taxon;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class ARATaxonDAOImpl extends GenericBaseDAOImpl<Taxon,Integer> implements TaxonDAO {

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.TaxonDAO#findAllByName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Taxon> findAllByName(final String defaultName)
			throws IllegalArgumentException {
		logger.debug("getTaxonLite for default name: '" + defaultName + "'.");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Taxon>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select t"
						+ " from ARATaxon as t" 
						+ " where t.defaultName = :defaultName");
				query.setParameter("defaultName", defaultName);
				query.setCacheable(true);
				return query.list();
			}
		});
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.TaxonDAO#findAllByPartialNamePaginated(java.lang.String, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Taxon> findAllByPartialNamePaginated(final String partialTaxonName, final int maxResults) throws IllegalArgumentException {
		logger.debug("findAllByPartialNamePaginated for default name: '" + partialTaxonName + "'.");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Taxon>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select t"
						+ " from ARATaxon as t" 
						+ " where t.defaultName like :defaultName");
				query.setParameter("defaultName", partialTaxonName);
				query.setFirstResult(0);
				query.setMaxResults(maxResults);				
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.TaxonDAO#findByDefaultNameAndKingdomId(java.lang.String, java.lang.Integer)
	 */
	public Taxon findByDefaultNameAndKingdomId(final String defaultName,
			final Integer kingdomTaxonId) throws IllegalArgumentException {
		logger.debug("getTaxonLite for default name: '" + defaultName
				+ "' and kingdomTaxonId: '" + kingdomTaxonId + "'.");
		HibernateTemplate template = getHibernateTemplate();
		return (Taxon) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select t"
						+ " from ARATaxon as t" 
						+ " where t.defaultName = :defaultName"
						+" and t.kingdomId = :kingdomTaxonId");
				query.setParameter("defaultName", defaultName);
				query.setParameter("kingdomTaxonId", kingdomTaxonId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.TaxonDAO#findByOrder(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<Taxon> findByOrder(final Integer orderTaxonId) {
		logger.debug("findByOrder with orderTaxonId["+orderTaxonId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Taxon>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select t"
						+ " from ARATaxon as t"
						+ " where t.orderId = :orderTaxonId"
						+	" or t.taxonId = :taxonId");
				query.setParameter("orderTaxonId", orderTaxonId);
				query.setParameter("taxonId", orderTaxonId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.TaxonDAO#findByFamily(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<Taxon> findByFamily(final Integer familyTaxonId) {
		logger.debug("findByFamily with familyTaxonId["+familyTaxonId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Taxon>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select t"
						+ " from ARATaxon as t"
						+ " where t.familyId = :familyTaxonId"
						+	" or t.taxonId = :taxonId");
				query.setParameter("familyTaxonId", familyTaxonId);
				query.setParameter("taxonId", familyTaxonId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.TaxonDAO#findByGenus(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<Taxon> findByGenus(final Integer genusTaxonId) {
		logger.debug("findByGenus with genusTaxonId["+genusTaxonId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Taxon>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select t"
						+ " from ARATaxon as t"
						+ " where t.genusId = :genusTaxonId"
						+	" or t.taxonId = :taxonId" );
				query.setParameter("genusTaxonId", genusTaxonId);
				query.setParameter("taxonId", genusTaxonId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.TaxonDAO#findByNameAndRange(java.lang.String, java.lang.Integer)
	 */
	public Taxon findByNameAndRange(final String taxonDefaultName,final Integer taxonomicalRangeId) {
		logger.debug("findByNameAndRange with taxonDefaultName["+taxonDefaultName+"] and Range["+taxonomicalRangeId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (ARATaxon) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select t from ARATaxon as t"
						+ " where t.defaultName = :taxonDefaultName"
						+ " and t.taxonomicalRangeId = :taxonomicalRangeId");
				query.setParameter("taxonDefaultName", taxonDefaultName);
				query.setParameter("taxonomicalRangeId", taxonomicalRangeId);				
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.TaxonDAO#findByObservationId(java.lang.Integer)
	 */
	public List<Taxon> findByObservationId(Integer observationId)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.TaxonDAO#findBySpecies(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<Taxon> findBySpecies(final Integer speciesTaxonId) {
		logger.debug("findBySpecies with speciesTaxonId["+speciesTaxonId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Taxon>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select t"
						+ " from ARATaxon as t"
						+ " where t.speciesId = :speciesTaxonId" 
						+	" or t.taxonId = :taxonId");
				query.setParameter("speciesTaxonId", speciesTaxonId);
				query.setParameter("taxonId", speciesTaxonId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.TaxonDAO#findBySpecimenId(java.lang.Integer)
	 */
	public Taxon findBySpecimenId(Integer specimenId)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return (ARATaxon) new Taxon(new Integer(7), new Integer(1), "falta hacer", new Date(), "falta hacer", new Integer(1), "falta hacer");
	}


}
