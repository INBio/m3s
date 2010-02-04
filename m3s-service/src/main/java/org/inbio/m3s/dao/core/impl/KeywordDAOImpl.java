/* M3S - multimedia management system
*
* Copyright (C) 2009  INBio - Instituto Nacional de Biodiversidad, Costa Rica
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.GenericBaseDAOImpl;
import org.inbio.m3s.dao.core.KeywordDAO;
import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.model.core.Keyword;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class KeywordDAOImpl extends GenericBaseDAOImpl<Keyword,Integer> implements KeywordDAO {	
	
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
					+ " and tt.text.textId = k.nameTextId"
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
						+ " and tt.text.textId = mk.keyword.nameTextId"
						+ " and tt.language.languageId = " + languageId + "");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}
	

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.KeywordDAO#findAllByMedia(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<Keyword> findAllByMedia(final Integer mediaId)  throws IllegalArgumentException {
		HibernateTemplate template = getHibernateTemplate();
		return (List<Keyword>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select mk.keyword"
						+ " from MediaKeyword as mk"
						+ " where mk.id.mediaId = :mediaId");
				query.setParameter("mediaId", mediaId);
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
						+ " and k.nameTextId = tt.text.textId");
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
