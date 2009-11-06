/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.KeywordDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.dto.message.KeywordDTO;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class KeywordDAOImpl extends BaseDAOImpl implements KeywordDAO {
	
	private static Logger logger = Logger.getLogger(KeywordDAOImpl.class);


	public KeywordDTO getKeywordLite(final String keywordName, final Integer languageId) {
		//	logger.debug("getKeywordDBId... start ");
		HibernateTemplate template = getHibernateTemplate();
		return (KeywordDTO) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
					  //"select k.keywordId"
						"select new org.inbio.m3s.dto.message.KeywordDTO(k.keywordId, tt.name)"
					+ " from TextTranslation as tt, Keyword as k"
					+ " where tt.name = '" + keywordName + "'"
					+ " and tt.text.textId = k.text"
					+ " and tt.language.languageId = " + languageId + "");
						//query.setParameter(0, nomenclaturalGroupId);
						query.setCacheable(true);
						return query.uniqueResult();
				}
			});
	}
	
	/* (non-Javadoc)
	 * @see org.inbio.portal.dao.nomenclaturalgroup.NomenclaturalGroupDAO#getChildGroupsOf(long)
	 */
	@SuppressWarnings("unchecked")
	public List<KeywordDTO> getAllKeywordLiteForMedia(final Integer mediaId, final Integer languageId) {
		HibernateTemplate template = getHibernateTemplate();
		return (List<KeywordDTO>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.message.KeywordDTO(mk.keyword.keywordId, tt.name)"
						+ " from TextTranslation as tt, MediaKeyword as mk"
						+ " where mk.id.mediaId = " + mediaId
						+ " and tt.text.textId = mk.keyword.text"
						+ " and tt.language.languageId = " + languageId + "");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<KeywordDTO> findAllByPartialNamePaginated(final String partialKeywrod, final int maxResults, final Integer languageId) {
		logger.debug("findAllByPartialNamePaginated with[" + partialKeywrod +"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<KeywordDTO>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.message.KeywordDTO(k.keywordId, tt.name)"
						+ " from TextTranslation as tt, Keyword as k"
						+ " where tt.language.languageId = :languageId" 
						+ " and tt.name like :keyword"
						+ " and k.text.textId = tt.text.textId");
				query.setParameter("languageId", languageId);
				query.setParameter("keyword", partialKeywrod);
				query.setFirstResult(0);
				query.setMaxResults(maxResults);				
				query.setCacheable(true);					
				return query.list();
			}
		});
	}
	
}
