/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.MediaUseDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.dto.lite.MediaUseLite;
import org.inbio.m3s.dao.DataCache;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 * 
 */
public class MediaUseDAOImpl extends BaseDAOImpl  implements MediaUseDAO {

	private static Logger logger = Logger.getLogger(MediaUseDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.core.MediaUseDAO#getMediaUseLite(java.lang.String)
	 */
	public MediaUseLite getMediaUseLite(String mediaUseName, Integer languageId)
			throws IllegalArgumentException {
		logger.debug("getMediaUseLite with name = '" + mediaUseName + "'.");
		String errorMsj = "El nombre de la uso '" + mediaUseName+ "' no fue encontrado.";

		try {
			if (DataCache.mediaUsesInCache) {
				logger.debug("getting media uses... were in cache.");
			} else {
				logger.debug("getting media uses... weren't in cache!.");
				DataCache.initMediaUsesInfo();
			}

			int index = DataCache.mediaUsesNames.indexOf(mediaUseName);

			logger.debug("get media use... done: "	+ DataCache.mediaUsesDBIds.get(index));

			return new MediaUseLite(DataCache.mediaUsesDBIds.get(index), mediaUseName);
		} catch (Exception e) {
			logger.error(errorMsj);
			logger.error(e.getMessage());
			throw new IllegalArgumentException(errorMsj);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.core.MediaUseDAO#getMediaUseLite(java.lang.Integer)
	 */
	public MediaUseLite getMediaUseLite(Integer mediaUseId, Integer languageId)
			throws IllegalArgumentException {
		logger.debug("getMediaUseLite");
		String errorMsj = "No se encontro ningun uso con el Id #" + mediaUseId
				+ ". ";

		try {

			if (DataCache.mediaUsesInCache) {
				logger.debug("getting media uses... were in cache.");
			} else {
				logger.debug("getting media uses... weren't in cache!.");
				DataCache.initMediaCategoriesInfo();
			}

			int index = DataCache.mediaUsesDBIds.indexOf(mediaUseId);

			logger.debug("media use name... done: "
					+ DataCache.mediaUsesNames.get(index));

			return new MediaUseLite(mediaUseId, DataCache.mediaUsesNames.get(index));
		} catch (Exception e) {
			logger.error(errorMsj);
			logger.error(e.getMessage());
			throw new IllegalArgumentException(errorMsj);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.core.MediaUseDAO#getMediaUsesLite(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<MediaUseLite> getMediaUsesLite(final Integer mediaId, final Integer languageId)
			throws IllegalArgumentException {
		logger.debug("getMediaUsesLite()...");
		HibernateTemplate template = getHibernateTemplate();
		return (List<MediaUseLite>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.MediaUseLite(mum.mediaUse.mediaUseId, tt.name)"			
						+ " from TextTranslation as tt, MediaUseMedia as mum"
						+ " where mum.media.mediaId = "+ mediaId
						+ " and tt.language.languageId = " + languageId + ""
						+ " and tt.text = mum.mediaUse.textByNameTextId");
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.core.MediaUseDAO#listAllLite()
	 */
	@SuppressWarnings("unchecked")
	public List<MediaUseLite> listAllLite(final Integer languageId)
			throws IllegalArgumentException {
		logger.debug("getMediaUsesLite()...");
		HibernateTemplate template = getHibernateTemplate();
		return (List<MediaUseLite>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.MediaUseLite(mu.mediaUseId, tt.name)"
						+ " from TextTranslation as tt, MediaUse as mu"
						+ " where tt.language.languageId = " + languageId + ""
						+ " and tt.text = mu.textByNameTextId");
				query.setCacheable(true);
				return query.list();
			}
		});
	}

}
