/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.MediaCategoryDAO;
import org.inbio.m3s.dto.lite.MediaCategoryLite;
import org.inbio.m3s.dao.DataCache;
import org.inbio.m3s.service.MessageManager;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author jgutierrez
 *
 */
public class MediaCategoryDAOImpl extends HibernateDaoSupport implements MediaCategoryDAO{
	
	private static Logger logger = Logger.getLogger(MediaCategoryDAOImpl.class);

	/**
	 * 
	 */
	public MediaCategoryLite getMediaCategoryLite(Integer mediaCategoryId) throws IllegalArgumentException {
		logger.debug("getMediaCategoryLite");
		String errorMsj = "No se encontro ninguna categoria con el Id #"
				+ mediaCategoryId + ". ";

		try {

			if (DataCache.mediaCategoriesInCache) {
				logger.debug("getting media category... were in cache.");
			} else {
				logger.debug("getting media category... weren't in cache!.");
				DataCache.initMediaCategoriesInfo();
			}

			int index = DataCache.mediaCategoriesDBIds.indexOf(mediaCategoryId);

			logger.debug("media category name... done: "+ DataCache.mediaCategoriesNames.get(index));

			return new MediaCategoryLite(mediaCategoryId, DataCache.mediaCategoriesNames.get(index));
		} catch (Exception e) {
			logger.error(errorMsj);
			logger.error(e.getMessage());
			throw new IllegalArgumentException(errorMsj);
		}
	}

	/**
	 * 
	 */
	public MediaCategoryLite getMediaCategoryLite(String categoryName) throws IllegalArgumentException {
		logger.debug("getMediaCategoryLite with name = '" + categoryName + "'.");
		String errorMsj = "El nombre de la categoria '" + categoryName+ "' no fue encontrado.";

		try {
			if (DataCache.mediaCategoriesInCache) {
				logger.debug("getting media category... were in cache.");
			} else {
				logger.debug("getting media category... weren't in cache!.");
				DataCache.initMediaCategoriesInfo();
			}

			int index = DataCache.mediaCategoriesNames.indexOf(categoryName);

			logger.debug("get media category... done: "	+ DataCache.mediaCategoriesDBIds.get(index));

			return new MediaCategoryLite(DataCache.mediaCategoriesDBIds.get(index), categoryName);
		} catch (Exception e) {
			logger.error(errorMsj);
			logger.error(e.getMessage());
			throw new IllegalArgumentException(errorMsj);
		}
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<MediaCategoryLite> listAllLite() throws Exception {
		logger.debug("listAllLite()...");
		HibernateTemplate template = getHibernateTemplate();
		return (List<MediaCategoryLite>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.MediaCategoryLite(mc.mediaCategoryId, tt.name)"
						+ " from TextTranslation as tt, MediaCategory as mc"
						+ " where tt.text.textId = mc.textByNameTextId.textId"
						+ " and tt.language.languageId = "+ MessageManager.DEFAULT_LANGUAGE);
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	/**
	 * 
	 * @param mediaTypeId
	 * @param language sugested language = Properties.DEFAULT_LANGUAGE
	 * @return
	 * @throws IllegalArgumentException
	 */
	public MediaCategoryLite getMediaCategoryLiteFromMediaType(final Integer mediaTypeId, final int language) throws IllegalArgumentException {
		logger.debug("getMediaCategoryLiteFromMediaType()...");
		HibernateTemplate template = getHibernateTemplate();
		return (MediaCategoryLite) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.MediaCategoryLite(mc.mediaCategoryId, tt.name)"
						+ " from TextTranslation as tt, MediaCategory as mc"
						+ " where tt.text.textId = mc.textByNameTextId.textId"
						+ " and tt.language.languageId = "+ language
						+ " and mc.mediaTypes.mediaTypeId = " + mediaTypeId);
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}

}
