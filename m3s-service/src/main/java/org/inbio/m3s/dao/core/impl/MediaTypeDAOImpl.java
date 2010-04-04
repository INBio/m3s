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

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.GenericBaseDAOImpl;
import org.inbio.m3s.dao.core.MediaTypeDAO;
import org.inbio.m3s.model.core.MediaType;
import org.inbio.m3s.service.MessageManager;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class MediaTypeDAOImpl extends GenericBaseDAOImpl<MediaType,Integer> implements MediaTypeDAO{
	
	private static Logger logger = Logger.getLogger(MediaTypeDAOImpl.class);

	/**
	 * sugested languge: Properties.DEFAULT_LANGUAGE
	public MediaTypeDTO getMediaTypeLite(final Integer mediaTypeId, final int language) throws IllegalArgumentException {
		logger.debug("getMediaTypeLite()...");
		HibernateTemplate template = getHibernateTemplate();
		return (MediaTypeDTO) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.message.MediaTypeDTO(mt.mediaTypeId, tt.name)"
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
	 */
	
	/*
	public MediaTypeDTO getMediaTypeLite(final String mediaTypeName) throws IllegalArgumentException {
		logger.debug("getMediaTypeLite()...");
		HibernateTemplate template = getHibernateTemplate();
		return (MediaTypeDTO) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.message.MediaTypeDTO(mt.mediaTypeId, tt.name)"
						+ " from TextTranslation as tt, MediaType as mt"
						+ " where tt.language.languageId  = "	+ MessageManager.DEFAULT_LOCALE
						+ " and tt.name = '"+ mediaTypeName	+ "'"
						+ " and tt.textId = mt.textByNameTextId.textId");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}
*/
	
	/*
	@SuppressWarnings("unchecked")
	public List<MediaTypeDTO> listAllForMediaCategoryLite(final Integer mediaCategoryId) throws IllegalArgumentException {
		logger.debug("listAllForMediaCategoryLite()...");
		HibernateTemplate template = getHibernateTemplate();
		return (List<MediaTypeDTO>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.message.MediaTypeDTO(mt.mediaTypeId, tt.name)"
						+ " from TextTranslation as tt, MediaType as mt"
						+ " where mt.mediaCategory.mediaCategoryId = " + mediaCategoryId
						+ " and tt.textId = mt.textByNameTextId.textId"
						+ " and tt.locale = "+ MessageManager.DEFAULT_LOCALE);
				query.setCacheable(true);
				return query.list();
			}
		});
		
	}
*/
	
	/*
	 * 
	 */
	public MediaType findByName(final String mediaTypeName) {
		logger.debug("getMediaTypeLite()...");
		HibernateTemplate template = getHibernateTemplate();
		return (MediaType) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select mt from TextTranslation as tt, MediaType as mt"
						+ " where tt.locale = :locale" 
						+ " and tt.name = :mediaTypeName"
						+ " and tt.textId = mt.mediaTypeNameTextId");
				query.setParameter("locale", MessageManager.DEFAULT_LOCALE);
				query.setParameter("mediaTypeName", mediaTypeName);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}


	
}
