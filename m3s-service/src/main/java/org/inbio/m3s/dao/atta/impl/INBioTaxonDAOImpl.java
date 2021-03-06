/**
 * 
 */
package org.inbio.m3s.dao.atta.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.GenericBaseDAOImpl;
import org.inbio.m3s.dao.core.SpecimenDAO;
import org.inbio.m3s.dao.core.TaxonDAO;
import org.inbio.m3s.dao.core.TaxonMediaDAO;
import org.inbio.m3s.exception.TaxonNotFoundException;
import org.inbio.m3s.model.atta.INBioTaxon;
import org.inbio.m3s.model.taxonomy.Taxon;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 * 
 */
public class INBioTaxonDAOImpl extends GenericBaseDAOImpl<Taxon, Integer> implements TaxonDAO {
	
	private static Logger logger = Logger.getLogger(INBioTaxonDAOImpl.class);
	
	private TaxonMediaDAO taxonMediaDAO;
	private SpecimenDAO specimenDAO;

	/**
	 * 
	 */
	//atta
	@SuppressWarnings("unchecked")
	public List<Taxon> findAllByName(final String defaultName) throws IllegalArgumentException {
		logger.debug("getTaxonLite for default name: '" + defaultName + "'.");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Taxon>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select t"
						+ " from INBioTaxon as t" 
						+ " where t.defaultName = '" + defaultName+ "' ");
				//query.setParameter(0, nomenclaturalGroupId);
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
						+ " from INBioTaxon as t" 
						+ " where t.defaultName like :defaultName");
				query.setParameter("defaultName", partialTaxonName);
				query.setFirstResult(0);
				query.setMaxResults(maxResults);				
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Taxon> findAllByRangeAndPartialNamePaginated(final Integer taxonimicalRangeId, final String partialTaxonName) {
		logger.debug("findAllByRangeAndPartialNamePaginated, taxonimicalRangeId["+taxonimicalRangeId+"] and  partialTaxonName["+partialTaxonName+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Taxon>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select t"
						+ " from INBioTaxon as t"
						+ " where t.taxonomicalRangeId = :taxonimicalRangeId"
						+ " and t.defaultName like :defaultName");
				query.setParameter("taxonimicalRangeId", taxonimicalRangeId);
				query.setParameter("defaultName", partialTaxonName);
				query.setCacheable(true);
				return query.list();
			}
		});
	}	
	
	/**
	 * 
	 */
	//atta
	public Taxon findByDefaultNameAndKingdomId(final String defaultName, final Integer kingdomTaxonId) throws TaxonNotFoundException {
		logger.debug("getTaxonLite for default name: '" + defaultName + "' and kingdomTaxonId: '" + kingdomTaxonId + "'.");
		try{
		HibernateTemplate template = getHibernateTemplate();
		return (Taxon) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select t"
						+ " from INBioTaxon as t" 
						+ " where t.defaultName = '" + defaultName
						+ "'" + " and t.kingdomId = '"+ kingdomTaxonId + "' ");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
		} catch(Exception e){
			logger.error(e.getClass());
			logger.error(e.getLocalizedMessage());
			logger.error(e.getMessage());
			throw new TaxonNotFoundException(e.getMessage(), e.getCause(), defaultName);
		}
	}


	/**
	 * 
	 */
	//atta
	public Taxon findBySpecimenId(final Integer specimenId) throws IllegalArgumentException {
		logger.debug("getTaxonLiteFromSpecimenId with specimenId["+specimenId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (Taxon) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select i.taxon"
						+ " from Identification as i, INBioSpecimen as s "
						+ " where i.specimen.specimenId = " + specimenId + ""
						+ " and i.specimen.specimenId = s.specimenId");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}	

//atta
	@SuppressWarnings("unchecked")
	public List<Taxon> findByObservationId(final Integer observationId) 
		throws IllegalArgumentException {

		logger.debug("findByObservationId... with param ["+observationId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Taxon>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select ot.taxon"
						+ " from ObservedTaxon as ot"
						+ " where ot.observation.observationId = " + observationId+ " ");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.list();
			}
		});		
	}

	/**
	 * @return the taxonMediaDAO
	 */
	public TaxonMediaDAO getTaxonMediaDAO() {
		return taxonMediaDAO;
	}


	/**
	 * @param taxonMediaDAO the taxonMediaDAO to set
	 */
	public void setTaxonMediaDAO(TaxonMediaDAO taxonMediaDAO) {
		this.taxonMediaDAO = taxonMediaDAO;
	}


	/**
	 * @return the specimenDAO
	 */
	public SpecimenDAO getSpecimenDAO() {
		return specimenDAO;
	}


	/**
	 * @param specimenDAO the specimenDAO to set
	 */
	public void setSpecimenDAO(SpecimenDAO specimenDAO) {
		this.specimenDAO = specimenDAO;
	}


	/**
	 * 
	 */
	public Taxon findByNameAndRange(final String taxonDefaultName, final Integer taxonomicalRangeId) {
		logger.debug("findByNameAndRange with taxonDefaultName["+taxonDefaultName+"] and Range["+taxonomicalRangeId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (Taxon) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select t from INBioTaxon as t"
						+ " where t.defaultName = '"+taxonDefaultName+"'"
						+ " and t.taxonomicalRangeId = " + taxonomicalRangeId);
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}
	

	@SuppressWarnings("unchecked")
	public List<Taxon> findByOrder(final Integer orderTaxonId) {
		logger.debug("findByOrder with orderTaxonId["+orderTaxonId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Taxon>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select t"
						+ " from INBioTaxon as t"
						+ " where t.orderId = " + orderTaxonId
						+	" or t.taxonId = " + orderTaxonId );
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Taxon> findByFamily(final Integer familyTaxonId) {
		logger.debug("findByFamily with familyTaxonId["+familyTaxonId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Taxon>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select t"
						+ " from INBioTaxon as t"
						+ " where t.familyId = " + familyTaxonId
						+	" or t.taxonId = " + familyTaxonId );
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Taxon> findByGenus(final Integer genusTaxonId) {
		logger.debug("findByGenus with genusTaxonId["+genusTaxonId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Taxon>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select t"
						+ " from INBioTaxon as t"
						+ " where t.genusId = " + genusTaxonId
						+	" or t.taxonId = " + genusTaxonId );
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<Taxon> findBySpecies(final Integer speciesTaxonId) {
		logger.debug("findBySpecies with speciesTaxonId["+speciesTaxonId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Taxon>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select t"
						+ " from INBioTaxon as t"
						+ " where t.speciesId = " + speciesTaxonId 
						+	" or t.taxonId = " + speciesTaxonId );
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}


	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.impl.BaseDAOImpl#create(java.lang.Object)
	 */
	@Override
	public void create(Taxon entity) throws IllegalArgumentException {
		super.create((INBioTaxon) entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.BaseDAO#delete(java.lang.Object)
	 */
	@Override
	public void update(Taxon entity) throws IllegalArgumentException {
		super.update((INBioTaxon) entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.BaseDAO#update(java.lang.Object)
	 */
	@Override
	public void delete(Taxon entity) throws IllegalArgumentException {
		super.delete((INBioTaxon) entity);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.BaseDAO#findById(java.lang.Class, java.lang.Object)
	 */
	@Override
	public Taxon findById(Class<Taxon> entityClass, Integer id) throws IllegalArgumentException {
		HibernateTemplate template = getHibernateTemplate();
		return (Taxon) template.get(INBioTaxon.class, id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.BaseDAO#findAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Taxon> findAll(Class<Taxon> entityClass) throws IllegalArgumentException {
		HibernateTemplate template = getHibernateTemplate();
		return template.loadAll(INBioTaxon.class);
	}	
	
}
