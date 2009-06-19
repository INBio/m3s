/**
 * 
 */
package org.inbio.m3s.dao.ara.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.TaxonDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.model.ara.ARATaxon;
import org.inbio.m3s.model.atta.INBioTaxon;
import org.inbio.m3s.model.taxonomy.Taxon;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class ARATaxonDAOImpl extends BaseDAOImpl implements TaxonDAO {

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
						+ " where t.defaultName = '" + defaultName+ "' ");
				//query.setParameter(0, nomenclaturalGroupId);
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
		return (INBioTaxon) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select t"
						+ " from ARATaxon as t" 
						+ " where t.defaultName = '" + defaultName
						+ "'" + " and t.kingdomId = '"+ kingdomTaxonId + "' ");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
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
						+ " where t.familyId = " + familyTaxonId
						+	" or t.taxonId = " + familyTaxonId );
				//query.setParameter(0, nomenclaturalGroupId);
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
						+ " where t.genusId = " + genusTaxonId
						+	" or t.taxonId = " + genusTaxonId );
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.TaxonDAO#findByNameAndRange(java.lang.String, java.lang.Integer)
	 */
	public Taxon findByNameAndRange(final String taxonDefaultName,
			final Integer taxonomicalRangeId) {
		logger.debug("findByNameAndRange with taxonDefaultName["+taxonDefaultName+"] and Range["+taxonomicalRangeId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (INBioTaxon) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select t from ARATaxon as t"
						+ " where t.defaultName = '"+taxonDefaultName+"'"
						+ " and t.taxonomicalRangeId = " + taxonomicalRangeId);
				//query.setParameter(0, nomenclaturalGroupId);
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
						+ " where t.speciesId = " + speciesTaxonId 
						+	" or t.taxonId = " + speciesTaxonId );
				//query.setParameter(0, nomenclaturalGroupId);
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
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.impl.BaseDAOImpl#create(java.lang.Object)
	 */
	@Override
	public void create(Object entity) throws IllegalArgumentException {
		super.create((ARATaxon) entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.BaseDAO#delete(java.lang.Object)
	 */
	@Override
	public void update(Object entity) throws IllegalArgumentException {
		super.update((ARATaxon) entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.BaseDAO#update(java.lang.Object)
	 */
	@Override
	public void delete(Object entity) throws IllegalArgumentException {
		super.delete((ARATaxon) entity);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.BaseDAO#findById(java.lang.Class, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object findById(Class entityClass, Object Id) throws IllegalArgumentException {
		return super.findById(ARATaxon.class,Id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.BaseDAO#findAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAll(Class entityClass) throws IllegalArgumentException {
		return super.findAll(ARATaxon.class);
	}

}
