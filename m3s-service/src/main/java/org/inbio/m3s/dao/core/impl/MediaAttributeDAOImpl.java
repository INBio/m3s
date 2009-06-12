/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.MediaAttributeDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.dto.full.MediaAttributeFull;
import org.inbio.m3s.service.MessageManager;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class MediaAttributeDAOImpl extends BaseDAOImpl implements MediaAttributeDAO {
	
	private static Logger logger = Logger.getLogger(MediaAttributeDAOImpl.class);

	/* 
	 * Este metodo no esta devolviendo la descripcion del MediaAttribute! 
	 * 
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.interfaces.MediaAttributeDAO#getMediaAttributeFull(java.lang.Integer)
	 */
	public MediaAttributeFull getMediaAttributeFull(final Integer mediaAttributeId)
			throws IllegalArgumentException {
		logger.debug("getMediaAttributeFull, param mediaAttributeId["	+ mediaAttributeId);
		HibernateTemplate template = getHibernateTemplate();
		return (MediaAttributeFull) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.full.MediaAttributeFull(ma.mediaAttributeId,tt.name,tt.name," +
						"ma.mediaAttributeTableName,ma.mediaAttributeValueType,ma.mediaAttributeTypePredefined)" +
						" from MediaAttribute as ma, TextTranslation as tt"+
						" where ma.mediaAttributeId = " + mediaAttributeId +
						" and tt.language.languageId = "	+ MessageManager.DEFAULT_LANGUAGE +
						" and tt.text = ma.textByNameTextId");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				MediaAttributeFull maf = (MediaAttributeFull) query.uniqueResult();
				maf.setDescription("");
				return maf;
			}
		});
	}
	
	/*
	 * Este metodo no esta devolviendo la descripcion del MediaAttribute!
	 * 
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.interfaces.MediaAttributeDAO#getMediaAttributeFull(java.lang.String)
	 */
	public MediaAttributeFull getMediaAttributeFull(final String mediaAttributeName) throws IllegalArgumentException {
		logger.debug("getMediaAttributeFull, param mediaAttributeName["	+ mediaAttributeName);
		HibernateTemplate template = getHibernateTemplate();
		return (MediaAttributeFull) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.full.MediaAttributeFull(ma.mediaAttributeId,tt.name,tt.name," +
						"ma.mediaAttributeTableName,ma.mediaAttributeValueType,ma.mediaAttributeTypePredefined)" +
						" from MediaAttribute as ma, TextTranslation as tt"+
						" where tt.name = '"	+ mediaAttributeName+ "'" +
						" and tt.text.textId = ma.textByNameTextId.textId " +
						" and tt.language.languageId = " + MessageManager.DEFAULT_LANGUAGE + "");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				MediaAttributeFull maf = (MediaAttributeFull) query.uniqueResult();
				maf.setDescription("");
				return maf;
			}
		});
					
	}

	/*
	 * Este metodo no esta devolviendo la descripcion del MediaAttribute!
	 * 
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.interfaces.MediaAttributeDAO#getMediaAttributesFullForMedia(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<MediaAttributeFull> getMediaAttributesFullForMediaType(final Integer mediaTypeId) throws IllegalArgumentException {
		logger.debug("getMediaAttributesFullForMedia, param mediaTypeId["	+ mediaTypeId);
		HibernateTemplate template = getHibernateTemplate();
		return (List<MediaAttributeFull>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.full.MediaAttributeFull(ma.mediaAttributeId,tt.name,tt.name," +
						"ma.mediaAttributeTableName,ma.mediaAttributeValueType,ma.mediaAttributeTypePredefined)" +
						" from MediaAttribute as ma, TextTranslation as tt, MediaAttributeType as mat"+
						" where mat.id.mediaTypeId = " + mediaTypeId +
						" and ma.mediaAttributeId = mat.id.mediaAttributeId" +
						" and tt.language.languageId = "	+ MessageManager.DEFAULT_LANGUAGE +
						" and tt.text = ma.textByNameTextId");
				query.setCacheable(true);
				List<MediaAttributeFull> tempResult = query.list();
				for(MediaAttributeFull maf : tempResult){
					maf.setDescription("");
				}
				return tempResult;
			}
		});
				
	}




	
}
