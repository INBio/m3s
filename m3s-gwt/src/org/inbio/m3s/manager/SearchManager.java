/**
 * 
 */
package org.inbio.m3s.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.dao.GenericSearchDAO;
import org.inbio.m3s.dao.core.KeywordDAO;
import org.inbio.m3s.dao.core.ProjectDAO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.dto.taxonomy.util.TaxonomicalRangeEntity;
import org.inbio.m3s.gwt.client.dto.util.SearchFilterEntity;
import org.inbio.m3s.gwt.client.widgets.galleries.SearchCriteriaTriplet;
import org.inbio.m3s.gwt.client.widgets.galleries.SearchCriteriaValues;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.service.TaxonomyManager;
import org.inbio.m3s.util.ServiceUtil;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.lite.ProjectLite;
import org.inbio.m3s.dto.message.KeywordLiteDTO;

/**
 * @author jgutierrez
 * 
 */
public class SearchManager {

	private static Logger logger = Logger.getLogger(SearchManager.class);

	/**
	 * Returns the total of elements that could be retrived using that search
	 * criteria
	 * 
	 * @return number of results
	 */
	public static Integer getTotalResults(List<SearchCriteriaTriplet> searchCriteria)
			throws IllegalArgumentException {
		String HSQL = createHSQLQueryString(searchCriteria);
		HSQL = "select count(m.mediaId)"
			+ HSQL.substring(("select m.mediaId").length());
		
		GenericSearchDAO gsDAOImpl = (GenericSearchDAO) ServiceUtil.appContext.getBean("genericSearchDAO");
		


		logger.debug("query: " + HSQL);
		//List queryResult = (List) HibernateUtil.simpleHSQLQuery(HSQL,
		//		HibernateUtil.openM3SDBSession());
		
		//return (Integer) queryResult.get(0);
		return gsDAOImpl.getTotalResults(HSQL);
	}

	/**
	 * 
	 * @param searchCriteria
	 * @param first
	 * @param last
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static List<Integer> getResults(List searchCriteria, int first, int last)
			throws IllegalArgumentException {
		// try {
		String HSQL = createHSQLQueryString(searchCriteria);

		logger.debug("query getResults: " + HSQL);
		GenericSearchDAO gsDAOImpl = (GenericSearchDAO) ServiceUtil.appContext.getBean("genericSearchDAO");


		//return HibernateUtil.simpleHSQLQuery(HSQL, HibernateUtil
		//		.openM3SDBSession(), first, last);
		return gsDAOImpl.getResults(HSQL, first, last);

	}

	/**
	 * 
	 * @param searchCriteria
	 * @return
	 * 
	 */
	private static String createHSQLQueryString(List<SearchCriteriaTriplet> searchCriteria)
			throws IllegalArgumentException {
		logger.debug("createHSQLQueryString with " + searchCriteria.size()
				+ " search criterias");

		String select = "select m.mediaId";
		String from = " from Media as m";
		String where = " where";
		int items = 0;
		SearchCriteriaTriplet tripletPivote;

		for (int i = 0; i < searchCriteria.size(); i++) {

			tripletPivote = (SearchCriteriaTriplet) searchCriteria.get(i);

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
	private static String higherTaxonomyWhere(SearchCriteriaTriplet triplete, TaxonomicalRangeEntity taxonomicalRangeEntity) {

		String where = " m.mediaId in ( select tm.id.mediaId"
				+ " from TaxonMedia as tm"
				+		" where";

		String criteria = getCriteria(triplete.getCriteria());
		boolean firstWhereElement = true;

		TaxonomyManager taxonomyManager = (TaxonomyManager) ServiceUtil.appContext.getBean(Properties.TAXONOMY_MANAGER);
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
	private static String authorWhere(SearchCriteriaTriplet triplete)
			throws IllegalArgumentException {

		Integer criteria = triplete.getCriteria();
		String value = triplete.getValue();

		AgentManager agentManager = (AgentManager) ServiceUtil.appContext.getBean(Properties.AGENT_MANAGER);
		PersonLiteDTO pLite = agentManager.getPersonLiteByName(value);
		//PersonDTOLite personLite = personDAO.getPersonLite(value);
		
		
		//return " m.authorPersonId " + getCriteria(criteria) + " " + personLite.getPersonId()+ "";
		return " m.authorPersonId " + getCriteria(criteria) + " " + pLite.getPersonKey()+ "";

	}
	
	/**
	 * 
	 * @param triplete
	 * @return
	 * @throws IllegalArgumentException
	 */
	private static String keywordWhere(SearchCriteriaTriplet triplete) 
	throws IllegalArgumentException {
		
	
		Integer criteria = triplete.getCriteria();
		String value = triplete.getValue();
		
		KeywordDAO keywordDAO = (KeywordDAO) ServiceUtil.appContext.getBean("keywordDAO");
		KeywordLiteDTO klDTO = keywordDAO.getKeywordLite(value, MessageManager.DEFAULT_LANGUAGE);
		
		return " m.mediaId in ( select mk.id.mediaId" +
				" from MediaKeyword as mk" +
				" where mk.id.keywordId" + getCriteria(criteria) +" "+ klDTO.getKeywordKey()+" )";
	}

	/**
	 * 
	 * @param triplete
	 * @return
	 * @throws IllegalArgumentException
	 */
	private static String projectWhere(SearchCriteriaTriplet triplete) 
	throws IllegalArgumentException {
		
	
		Integer criteria = triplete.getCriteria();
		String value = triplete.getValue();
		
		ProjectDAO projectDAO = (ProjectDAO) ServiceUtil.appContext.getBean("projectDAO");
		ProjectLite projectLite = projectDAO.getProjectLite(value);
		
		return " m.mediaId in ( select mp.id.mediaId" +
				" from MediaProject as mp" +
				" where mp.id.projectId" + getCriteria(criteria) +" "+ projectLite.getProjectId()+" )";
	}
	
	/**
	 * 
	 * @param triplete
	 * @return
	 * @throws IllegalArgumentException
	 */
	private static String taxonomyWhere(SearchCriteriaTriplet triplete)
			throws IllegalArgumentException {

		String where = " m.mediaId in ( select tm.id.mediaId"
				+ " from TaxonMedia as tm where tm.id.taxonId";

		Integer criteria = triplete.getCriteria();
		String value = triplete.getValue();
		
		//FIXME esto no funciona para todos los casos pues un taxon 
		//no es unico solo por su nombre, requiere al menos el kingdom
		TaxonomyManager taxonomyManager = (TaxonomyManager) ServiceUtil.appContext.getBean(Properties.TAXONOMY_MANAGER);
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
	private static String getCriteria(Integer criteriaValue) {
		if (criteriaValue.equals(SearchCriteriaValues.IS)) {
			return "=";
		} else {
			return null;
		}
	}

}
