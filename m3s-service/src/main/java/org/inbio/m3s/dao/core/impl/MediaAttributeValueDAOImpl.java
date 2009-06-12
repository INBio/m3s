/**
 * 
 */
package org.inbio.m3s.dao.core.impl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.MediaAttributeDAO;
import org.inbio.m3s.dao.core.MediaAttributeValueDAO;
import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.dto.mediaattribute.MediaAttributeValueFullDTO;
import org.inbio.m3s.model.core.Media;
import org.inbio.m3s.model.core.MediaAttribute;
import org.inbio.m3s.model.core.MediaAttributeValue;
import org.inbio.m3s.model.core.MediaAttributeValueId;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class MediaAttributeValueDAOImpl extends BaseDAOImpl implements MediaAttributeValueDAO {
	
	private MediaDAO mediaDAO;
	private MediaAttributeDAO mediaAttributeDAO;

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

	/*
	public void insertMediaAttributeValueFull(MediaAttributeValueFullDTO mavFull) throws IllegalArgumentException {
		logger.debug("insertMediaAttributeValueFull");
		Session session = null;
		Transaction tx = null;
		Media m;
		MediaAttribute ma;
		MediaAttributeValue mav;
		MediaAttributeValueId mavId;

		try {
			session = HibernateUtil.openM3SDBSession();
			tx = session.beginTransaction();

			m = (Media) session.load(Media.class, mavFull.getMediaId());
			ma = (MediaAttribute) session.load(MediaAttribute.class, mavFull.getMediaAttributeId());
			mavId = new MediaAttributeValueId(mavFull.getMediaAttributeId(), mavFull.getMediaId());
			mav = new MediaAttributeValue(mavId, ma, m);

			mav.setValueVarchar(mavFull.getValueVarchar());
			mav.setValueNumber(mavFull.getValueNumber());
			mav.setValueDate(mavFull.getValueDate());
			mav.setValueId(mavFull.getValueId());

			// saves the Media Object in the database
			session.save(mav);
			session.flush();
			tx.commit();

		} catch (HibernateException he) {
			HibernateUtil.rollback(tx);
			logger
					.error("There was a hibernate exeption in the insertMediaAttributeValueFull");
			logger.error(he.getMessage());
			throw new IllegalArgumentException("fails on insertMediaAttributeValueFull", he);
		} finally {
			HibernateUtil.closeSession(session);
		}
		
	}
*/


	public void updateMediaAttributeValueFull(MediaAttributeValueFullDTO mavFull) throws IllegalArgumentException {
		logger.debug("updateMediaAttributeValueFull");

		Media m;
		MediaAttribute ma;
		MediaAttributeValue mav;
		MediaAttributeValueId mavId;

		try {

			m = (Media) mediaDAO.findById(Media.class, mavFull.getMediaId());
			ma = (MediaAttribute) mediaAttributeDAO.findById(MediaAttribute.class, mavFull.getMediaAttributeId());
			mavId = new MediaAttributeValueId(mavFull.getMediaAttributeId(), mavFull.getMediaId());
			mav = new MediaAttributeValue(mavId, ma, m);

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

	/**
	 * @return the mediaDAO
	 */
	public MediaDAO getMediaDAO() {
		return mediaDAO;
	}

	/**
	 * @param mediaDAO the mediaDAO to set
	 */
	public void setMediaDAO(MediaDAO mediaDAO) {
		this.mediaDAO = mediaDAO;
	}

	/**
	 * @return the mediaAttributeDAO
	 */
	public MediaAttributeDAO getMediaAttributeDAO() {
		return mediaAttributeDAO;
	}

	/**
	 * @param mediaAttributeDAO the mediaAttributeDAO to set
	 */
	public void setMediaAttributeDAO(MediaAttributeDAO mediaAttributeDAO) {
		this.mediaAttributeDAO = mediaAttributeDAO;
	}

}
