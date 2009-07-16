/**
 * 
 */
package org.inbio.m3s.dao.atta.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.SpecimenDAO;
import org.inbio.m3s.dao.core.TaxonDAO;
import org.inbio.m3s.dao.core.TaxonMediaDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.model.atta.INBioTaxon;
import org.inbio.m3s.model.taxonomy.Taxon;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 * 
 */
public class INBioTaxonDAOImpl extends BaseDAOImpl implements TaxonDAO {

	private static Logger logger = Logger.getLogger(INBioTaxonDAOImpl.class);
	
	private TaxonMediaDAO taxonMediaDAO;
	private SpecimenDAO specimenDAO;

	/**
	 * 
	 */
	//atta
	@SuppressWarnings("unchecked")
	public List<Taxon> findAllByName(final String defaultName)
			throws IllegalArgumentException {
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

	
	/**
	 * 
	 */
	//atta
	public Taxon findByDefaultNameAndKingdomId(final String defaultName, final Integer kingdomTaxonId)
			throws IllegalArgumentException {
		logger.debug("getTaxonLite for default name: '" + defaultName
				+ "' and kingdomTaxonId: '" + kingdomTaxonId + "'.");
		HibernateTemplate template = getHibernateTemplate();
		return (INBioTaxon) template.execute(new HibernateCallback() {
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
	}


	/**
	 * 
	 */
	//atta
	public Taxon findBySpecimenId(final Integer specimenId) throws IllegalArgumentException {
		logger.debug("getTaxonLiteFromSpecimenId with specimenId["+specimenId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (INBioTaxon) template.execute(new HibernateCallback() {
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

/*
	public List<TaxonLite> getTaxonLiteFromGatheringCode(String gatheringCode) throws IllegalArgumentException {
		logger.debug("getTaxonIdsFromGatheringCode start");

		String errorMsj = "No se puede obtener taxonomía asociada a la recolecta  #"
				+ gatheringCode + ".";

		List<TaxonLite> taxonsList = new ArrayList<TaxonLite>();
		TaxonLite taxonLite = null;
		List<SpecimenLite> specimensList = specimenDAO.getSpecimenLiteFromGathering(gatheringCode);

		try {
			logger.debug("numero de especimenes: "+ specimensList.size());

			for(SpecimenLite sl : specimensList) { 
				taxonLite = getTaxonLiteFromSpecimenId(sl.getSpecimenId());
				taxonsList.add(taxonLite);
			}
			logger.debug("numero de taxones: " + taxonsList.size());

			return taxonsList;

		} catch (Exception iae) {
			throw new IllegalArgumentException(errorMsj + iae.getMessage());
		}

		
	}
*/

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
	public INBioTaxon findByNameAndRange(final String taxonDefaultName, final Integer taxonomicalRangeId) {
		logger.debug("findByNameAndRange with taxonDefaultName["+taxonDefaultName+"] and Range["+taxonomicalRangeId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (INBioTaxon) template.execute(new HibernateCallback() {
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
	public void create(Object entity) throws IllegalArgumentException {
		super.create((INBioTaxon) entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.BaseDAO#delete(java.lang.Object)
	 */
	@Override
	public void update(Object entity) throws IllegalArgumentException {
		super.update((INBioTaxon) entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.BaseDAO#update(java.lang.Object)
	 */
	@Override
	public void delete(Object entity) throws IllegalArgumentException {
		super.delete((INBioTaxon) entity);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.BaseDAO#findById(java.lang.Class, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object findById(Class entityClass, Object Id) throws IllegalArgumentException {
		return super.findById(INBioTaxon.class,Id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.BaseDAO#findAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAll(Class entityClass) throws IllegalArgumentException {
		return super.findAll(INBioTaxon.class);
	}

	
	
}
