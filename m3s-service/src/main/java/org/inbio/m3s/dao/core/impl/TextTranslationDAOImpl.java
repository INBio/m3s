/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.TextTranslationDAO;
import org.inbio.m3s.model.core.TextTranslation;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author jgutierrez
 *
 */
public class TextTranslationDAOImpl extends HibernateDaoSupport implements
		TextTranslationDAO {

	public TextTranslation finByIdAndLanguage(final Integer id, final Integer languageId) {
		logger.debug("TextTranslation... start ");
		HibernateTemplate template = getHibernateTemplate();
		return (TextTranslation) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select tt"
						+ " from TextTranslation as tt"
						+ " where tt.text.textId = " + id
						+ " and tt.language.languageId = " + languageId + "");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findTechnicalMetadataTexts(final Integer mediaTypeId, final Integer languageId) {
		logger.debug("findTechnicalMetadataTexts... start ");
		HibernateTemplate template = getHibernateTemplate();
		return (List<String>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select tt.name" +
						" from TextTranslation as tt, MediaAttributeType as mat"	+ 
						" where mat.mediaType.mediaTypeId = "	+ mediaTypeId +
						" and tt.text.textId = mat.mediaAttribute.textByNameTextId.textId" +
						" and tt.language.languageId = " + languageId + "");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}
	

}
