/**
 * 
 */
package org.inbio.m3s.dao.core.impl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.GenericBaseDAOImpl;
import org.inbio.m3s.dao.core.MediaAttributeValueDAO;
import org.inbio.m3s.dto.mediaattribute.MediaAttributeValueFullDTO;
import org.inbio.m3s.model.core.MediaAttributeValue;
import org.inbio.m3s.model.core.MediaAttributeValueId;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 * @deprecated
 */
public class MediaAttributeValueDAOImpl extends  GenericBaseDAOImpl<MediaAttributeValue, MediaAttributeValueId> implements MediaAttributeValueDAO {

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.interfaces.MediaAttributeValueDAO#getMediaAttributeValueFull(java.lang.Integer, java.lang.Integer)
	 */
	public MediaAttributeValueFullDTO getMediaAttributeValueFull(final Integer mediaId, final Integer mediaAttributeId) throws IllegalArgumentException {	
		logger.debug("getMediaAttributeValueFull, params mediaAttributeId["
				+ mediaAttributeId + "] y mediaId[" + mediaId + "]");
		HibernateTemplate template = getHibernateTemplate();
		return (MediaAttributeValueFullDTO) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.mediaattribute.MediaAttributeValueFullDTO(mav.id.mediaAttributeId,mav.id.mediaId," +
						"mav.valueVarchar,mav.valueId,mav.valueNumber,mav.valueDate," +
						"mav.creationDate,mav.createdBy,mav.lastModificationDate,mav.lastModificationBy)" +
						" from MediaAttributeValue as mav"+
						" where mav.id.mediaAttributeId = " + mediaAttributeId +
						" and mav.id.mediaId = " + mediaId);
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});					
	}


	public void updateMediaAttributeValueFull(MediaAttributeValueFullDTO mavFull) throws IllegalArgumentException {
		logger.debug("updateMediaAttributeValueFull");


		MediaAttributeValue mav;
		MediaAttributeValueId mavId;

		try {

			mavId = new MediaAttributeValueId(mavFull.getMediaAttributeId(), mavFull.getMediaId());
			mav = new MediaAttributeValue(mavId);

			mav.setValueVarchar(mavFull.getValueVarchar());
			mav.setValueNumber(mavFull.getValueNumber());
			mav.setValueDate(mavFull.getValueDate());
			mav.setValueId(mavFull.getValueId());

			// saves the Media Object in the database
			this.update(mav);

		} catch (Exception he) {
			logger.error("There was a hibernate exeption in the updateMediaAttributeValueFull");
			logger.error(he.getMessage());
			throw new IllegalArgumentException("fails on updateMediaAttributeValueFull", he);
		}
		
	}

}
