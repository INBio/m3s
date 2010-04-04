/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.GenericBaseDAOImpl;
import org.inbio.m3s.dao.core.TextTranslationDAO;
import org.inbio.m3s.model.core.TextTranslation;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class TextTranslationDAOImpl extends GenericBaseDAOImpl<TextTranslation, Integer> implements TextTranslationDAO {

	public TextTranslation finByIdAndLanguage(final Integer id, final String locale) {
		logger.debug("TextTranslation... start ");
		HibernateTemplate template = getHibernateTemplate();
		return (TextTranslation) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select tt"
						+ " from TextTranslation as tt"
						+ " where tt.textId = " + id
						+ " and tt.locale = :locale");
				query.setParameter("locale", locale);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findTechnicalMetadataTexts(final Integer mediaTypeId, final String locale) {
		logger.debug("findTechnicalMetadataTexts... start ");
		HibernateTemplate template = getHibernateTemplate();
		return (List<String>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select tt.name" +
						" from TextTranslation as tt, MediaAttributeType as mat"	+ 
						" where mat.mediaType.mediaTypeId = "	+ mediaTypeId +
						" and tt.textId = mat.mediaAttribute.textByNameTextId.textId" +
						" and tt.locale = :locale");
				query.setParameter("locale", locale);
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}
	

}
