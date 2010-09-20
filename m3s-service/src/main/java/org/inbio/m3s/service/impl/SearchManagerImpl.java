/**
 * 
 */
package org.inbio.m3s.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.m3s.dao.GenericSearchDAO;
import org.inbio.m3s.dao.core.KeywordDAO;
import org.inbio.m3s.dao.core.ProjectDAO;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.dto.message.ProjectDTO;
import org.inbio.m3s.dto.search.SearchCriteriaTripletDTO;
import org.inbio.m3s.dto.search.SearchCriteriaValuesDTO;
import org.inbio.m3s.dto.search.SearchFilterEntity;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.dto.taxonomy.util.TaxonomicalRangeEntity;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.service.SearchManager;
import org.inbio.m3s.service.TaxonomyManager;

/**
 * @author jgutierrez
 *
 */
public class SearchManagerImpl implements SearchManager {
	private static Logger logger = Logger.getLogger(SearchManagerImpl.class);
	
	private GenericSearchDAO genericSearchDAO;
	private KeywordDAO keywordDAO;
	private ProjectDAO projectDAO;
	private AgentManager agentManager;
	private MessageManager messageManager;
	private TaxonomyManager taxonomyManager;

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.impl.SearchManager#getTotalResults(java.util.List)
	 */
	public Integer getTotalResults(List<SearchCriteriaTripletDTO> searchCriteria) throws IllegalArgumentException {
		logger.debug("getTotalResults starting");
		
		String HSQL = createHSQLQueryString(searchCriteria);
		HSQL = "select count(m.mediaId)"
			+ HSQL.substring(("select m.mediaId").length());


		logger.debug("query: " + HSQL);
		return genericSearchDAO.getTotalResults(HSQL);
	}

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.impl.SearchManager#getResults(java.util.List, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getResults(List searchCriteria, int first, int last)
			throws IllegalArgumentException {
		// try {
		String HSQL = createHSQLQueryString(searchCriteria);

		logger.debug("query getResults: " + HSQL);


		//return HibernateUtil.simpleHSQLQuery(HSQL, HibernateUtil
		//		.openM3SDBSession(), first, last);
		return genericSearchDAO.getResults(HSQL, first, last);

	}

	/**
	 * 
	 * @param searchCriteria
	 * @return
	 * 
	 */
	private String createHSQLQueryString(List<SearchCriteriaTripletDTO> searchCriteria)
			throws IllegalArgumentException {
		logger.debug("createHSQLQueryString with " + searchCriteria.size()
				+ " search criterias");

		String select = "select m.mediaId";
		String from = " from Media as m";
		String where = " where";
		int items = 0;
		SearchCriteriaTripletDTO tripletPivote;

		for (int i = 0; i < searchCriteria.size(); i++) {

			tripletPivote = (SearchCriteriaTripletDTO) searchCriteria.get(i);

			if (items != 0) {
				where = where.concat(" or");
			}

			if (tripletPivote.getFilter().intValue() == SearchFilterEntity.MEDIA_ID.getId()) {

				where = where.concat(" m.mediaId "
						+ getCriteria(tripletPivote.getCriteria()) + " "
						+ tripletPivote.getValue() + "");
				items++;

			} else if (tripletPivote.getFilter().intValue() == SearchFilterEntity.TAXON.getId()) {
				where = where.concat(taxonomyWhere(tripletPivote));
				items++;
			
			} else if (tripletPivote.getFilter().intValue() == SearchFilterEntity.AUTHOR.getId()) {
				where = where.concat(authorWhere(tripletPivote));
				items++;
			
			} else if (tripletPivote.getFilter().intValue() == SearchFilterEntity.KEYWORD.getId()) {
				where = where.concat(keywordWhere(tripletPivote));
				items++;
			
			} else if (tripletPivote.getFilter().intValue() == SearchFilterEntity.PROJECT.getId()) {
				where = where.concat(projectWhere(tripletPivote));
				items++;
			
			} else if (tripletPivote.getFilter().intValue() == SearchFilterEntity.FAMILY.getId()) {
				where = where.concat(higherTaxonomyWhere(tripletPivote, TaxonomicalRangeEntity.FAMILY));
				items++;
			
			}  else if (tripletPivote.getFilter().intValue() == SearchFilterEntity.GENUS.getId()) {
				where = where.concat(higherTaxonomyWhere(tripletPivote, TaxonomicalRangeEntity.GENUS));
				items++;
			
			}  else if (tripletPivote.getFilter().intValue() == SearchFilterEntity.SPECIES.getId()) {
				where = where.concat(higherTaxonomyWhere(tripletPivote, TaxonomicalRangeEntity.SPECIES));
				items++;
			} else if (tripletPivote.getFilter().intValue() == SearchFilterEntity.ORDER.getId()) {
				where = where.concat(higherTaxonomyWhere(tripletPivote, TaxonomicalRangeEntity.ORDER));
				items++;
			
			} else if (tripletPivote.getFilter().intValue() == SearchFilterEntity.TITLE.getId()){
				where = where.concat(" m.title "
						//+ getCriteria(tripletPivote.getCriteria()) + " "
						+ getCriteria(SearchCriteriaValuesDTO.LIKE) + " "
						//+ tripletPivote.getValue() + "");
						+ "'%"+tripletPivote.getValue()+"%'");
				items++;
			} else if (tripletPivote.getFilter().intValue() == SearchFilterEntity.DESCRIPTION.getId()){
				where = where.concat(" m.description "
						//+ getCriteria(tripletPivote.getCriteria()) + " "
						+ getCriteria(SearchCriteriaValuesDTO.LIKE) + " "
					//+ tripletPivote.getValue() + "");
						+ "'%"+tripletPivote.getValue()+"%'");
				items++;
			}
			
		}

		return select + from + where;
	}

	/**
	 * 
	 * @param triplete
	 * @param taxonomicalRangeEntity
	 * @return
	 */
	private String higherTaxonomyWhere(SearchCriteriaTripletDTO triplete, TaxonomicalRangeEntity taxonomicalRangeEntity) {

		String where = " m.mediaId in ( select tm.id.mediaId"
				+ " from TaxonMedia as tm"
				+		" where";

		String criteria = getCriteria(triplete.getCriteria());
		boolean firstWhereElement = true;

		List<TaxonLiteDTO> tlDTOList = taxonomyManager.getTaxonsIncludedIn(triplete.getValue(), taxonomicalRangeEntity);

		for(TaxonLiteDTO tlDTO : tlDTOList){
			if(!firstWhereElement){
				where = where.concat(" or tm.id.taxonId " + criteria + " " + tlDTO.getTaxonKey());
			}
			else
				where = where.concat(" tm.id.taxonId " + criteria + " " + tlDTO.getTaxonKey());
				firstWhereElement = false;
		}
		
	 where = where.concat(" )");
		return where;

	}


	/**
	 * 
	 * @param triplete
	 * @return
	 * @throws IllegalArgumentException
	 */
	private String authorWhere(SearchCriteriaTripletDTO triplete)
			throws IllegalArgumentException {

		Integer criteria = triplete.getCriteria();
		String value = triplete.getValue();

		PersonLiteDTO pLite = agentManager.getPersonLiteByName(value);
		
		return " m.authorPersonId " + getCriteria(criteria) + " " + pLite.getPersonKey()+ "";

	}
	
	/**
	 * 
	 * @param triplete
	 * @return
	 * @throws IllegalArgumentException
	 */
	private String keywordWhere(SearchCriteriaTripletDTO triplete) 
	throws IllegalArgumentException {
		
	
		Integer criteria = triplete.getCriteria();
		String value = triplete.getValue();
		
		KeywordDTO klDTO = keywordDAO.getKeywordLite(value, MessageManager.DEFAULT_LOCALE);
		
		return " m.mediaId in ( select mk.id.mediaId" +
				" from MediaKeyword as mk" +
				" where mk.id.keywordId " + getCriteria(criteria) +" "+ klDTO.getKeywordKey()+" )";
	}

	/**
	 * 
	 * @param triplete
	 * @return
	 * @throws IllegalArgumentException
	 */
	private String projectWhere(SearchCriteriaTripletDTO triplete) 
	throws IllegalArgumentException {
			
		Integer criteria = triplete.getCriteria();
		String value = triplete.getValue();
		
		//ProjectDAO projectDAO = (ProjectDAO) ServiceUtil.appContext.getBean("projectDAO");
		ProjectDTO projectLite = messageManager.getProjectByName(value);
		
		return " m.mediaId in ( select mp.id.mediaId" +
				" from MediaProject as mp" +
				" where mp.id.projectId" + getCriteria(criteria) +" "+ projectLite.getProjectKey()+" )";
	}
	
	/**
	 * 
	 * @param triplete
	 * @return
	 * @throws IllegalArgumentException
	 */
	private String taxonomyWhere(SearchCriteriaTripletDTO triplete)
			throws IllegalArgumentException {

		String where = " m.mediaId in ( select tm.id.mediaId"
				+ " from TaxonMedia as tm where tm.id.taxonId";

		Integer criteria = triplete.getCriteria();
		String value = triplete.getValue();
		
		//FIXME esto no funciona para todos los casos pues un taxon 
		//no es unico solo por su nombre, requiere al menos el kingdom
		List<TaxonLiteDTO> posibleTaxons = taxonomyManager.getTaxonLite(value);
		TaxonLiteDTO tlDTO = posibleTaxons.get(0);


		where = where.concat(" " + getCriteria(criteria) + " " + tlDTO.getTaxonKey() + " )");

		return where;

	}
	
	/**
	 * Decodes a criteriaValue into the adecuate value for a hibernate query
	 * 
	 * @param criteriaValue
	 * @return
	 */
	private String getCriteria(Integer criteriaValue) {
		if (criteriaValue.equals(SearchCriteriaValuesDTO.IS)) {
			return "=";
		} else if(criteriaValue.equals(SearchCriteriaValuesDTO.LIKE)){
			return "like";
		} else {
			return null;
		}
	}

	/**
	 * @return the genericSearchDAO
	 */
	public GenericSearchDAO getGenericSearchDAO() {
		return genericSearchDAO;
	}

	/**
	 * @param genericSearchDAO the genericSearchDAO to set
	 */
	public void setGenericSearchDAO(GenericSearchDAO genericSearchDAO) {
		this.genericSearchDAO = genericSearchDAO;
	}

	/**
	 * @return the keywordDAO
	 */
	public KeywordDAO getKeywordDAO() {
		return keywordDAO;
	}

	/**
	 * @param keywordDAO the keywordDAO to set
	 */
	public void setKeywordDAO(KeywordDAO keywordDAO) {
		this.keywordDAO = keywordDAO;
	}

	/**
	 * @return the projectDAO
	 */
	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	/**
	 * @param projectDAO the projectDAO to set
	 */
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	/**
	 * @return the agentManager
	 */
	public AgentManager getAgentManager() {
		return agentManager;
	}

	/**
	 * @param agentManager the agentManager to set
	 */
	public void setAgentManager(AgentManager agentManager) {
		this.agentManager = agentManager;
	}

	/**
	 * @return the messageManager
	 */
	public MessageManager getMessageManager() {
		return messageManager;
	}

	/**
	 * @param messageManager the messageManager to set
	 */
	public void setMessageManager(MessageManager messageManager) {
		this.messageManager = messageManager;
	}

	/**
	 * @return the taxonomyManager
	 */
	public TaxonomyManager getTaxonomyManager() {
		return taxonomyManager;
	}

	/**
	 * @param taxonomyManager the taxonomyManager to set
	 */
	public void setTaxonomyManager(TaxonomyManager taxonomyManager) {
		this.taxonomyManager = taxonomyManager;
	}

}
