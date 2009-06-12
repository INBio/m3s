/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.MediaTypeDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.dto.lite.MediaTypeLite;
import org.inbio.m3s.service.MessageManager;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class MediaTypeDAOImpl extends BaseDAOImpl implements MediaTypeDAO{
	
	private static Logger logger = Logger.getLogger(MediaTypeDAOImpl.class);

	/**
	 * sugested languge: Properties.DEFAULT_LANGUAGE
	 */
	public MediaTypeLite getMediaTypeLite(final Integer mediaTypeId, final int language) throws IllegalArgumentException {
		logger.debug("getMediaTypeLite()...");
		HibernateTemplate template = getHibernateTemplate();
		return (MediaTypeLite) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.MediaTypeLite(mt.mediaTypeId, tt.name)"
						+ "from TextTranslation as tt, MediaType as mt"
						+ " where mt.mediaTypeId = " + mediaTypeId
						+ " and mt.textByNameTextId = tt.text"
						+ " and tt.language.languageId = " + language);
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}

	public MediaTypeLite getMediaTypeLite(final String mediaTypeName) throws IllegalArgumentException {
		logger.debug("getMediaTypeLite()...");
		HibernateTemplate template = getHibernateTemplate();
		return (MediaTypeLite) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.MediaTypeLite(mt.mediaTypeId, tt.name)"
						+ " from TextTranslation as tt, MediaType as mt"
						+ " where tt.language.languageId  = "	+ MessageManager.DEFAULT_LANGUAGE
						+ " and tt.name = '"+ mediaTypeName	+ "'"
						+ " and tt.text.textId = mt.textByNameTextId.textId");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<MediaTypeLite> listAllForMediaCategoryLite(final Integer mediaCategoryId) throws IllegalArgumentException {
		logger.debug("listAllForMediaCategoryLite()...");
		HibernateTemplate template = getHibernateTemplate();
		return (List<MediaTypeLite>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.MediaTypeLite(mt.mediaTypeId, tt.name)"
						+ " from TextTranslation as tt, MediaType as mt"
						+ " where mt.mediaCategory.mediaCategoryId = " + mediaCategoryId
						+ " and tt.text.textId = mt.textByNameTextId.textId"
						+ " and tt.language.languageId = "+ MessageManager.DEFAULT_LANGUAGE);
				query.setCacheable(true);
				return query.list();
			}
		});
		
	}


	
}
