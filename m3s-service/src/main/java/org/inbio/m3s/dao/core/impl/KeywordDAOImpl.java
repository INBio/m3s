/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.KeywordDAO;
import org.inbio.m3s.dto.message.KeywordLiteDTO;
import org.inbio.m3s.model.core.Keyword;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author jgutierrez
 *
 */
public class KeywordDAOImpl extends HibernateDaoSupport implements KeywordDAO {
	
	private static Logger logger = Logger.getLogger(KeywordDAOImpl.class);

	@SuppressWarnings("unchecked")
	public List<Keyword> findAll() {
		logger.debug("findAll()...");
		HibernateTemplate template = getHibernateTemplate();
		return (List<Keyword>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select k"
						+ " from Keyword as k");
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	/*
	public KeywordDTOLite getKeywordLite(final Integer keywordId, final Integer languageId) {
		//logger.debug("getKeywordName... start ");
		HibernateTemplate template = getHibernateTemplate();
		return (KeywordDTOLite) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.KeywordDTOLite(k.keywordId, tt.name)"
						+ " from TextTranslation as tt, Keyword as k "
						+ " where k.keywordId = " + keywordId
						+ " and tt.text.textId = k.text"
						+ " and tt.language.languageId = " + languageId + "");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}
	*/

	public KeywordLiteDTO getKeywordLite(final String keywordName, final Integer languageId) {
		//	logger.debug("getKeywordDBId... start ");
		HibernateTemplate template = getHibernateTemplate();
		return (KeywordLiteDTO) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
					  //"select k.keywordId"
						"select new org.inbio.m3s.dto.message.KeywordLiteDTO(k.keywordId, tt.name)"
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
	public List<KeywordLiteDTO> getAllKeywordLiteForMedia(final Integer mediaId, final Integer languageId) {
		HibernateTemplate template = getHibernateTemplate();
		return (List<KeywordLiteDTO>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.message.KeywordLiteDTO(mk.keyword.keywordId, tt.name)"
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
	
}
