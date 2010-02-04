package org.inbio.m3s.service.impl;

import org.apache.log4j.Logger;
import org.inbio.m3s.dao.core.MediaAttributeDAO;
import org.inbio.m3s.dao.core.MediaAttributeValueDAO;
import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.dto.mediaattribute.MediaAttributeValueFullDTO;
import org.inbio.m3s.dto.mediaattribute.MediaAttributeValueFullDTOFactory;
import org.inbio.m3s.model.core.MediaAttributeValue;
import org.inbio.m3s.model.core.MediaAttributeValueId;
import org.inbio.m3s.service.MediaAttributeManager;

public class MediaAttributeManagerImpl implements MediaAttributeManager {
	
	private static Logger logger = Logger.getLogger(MediaAttributeManagerImpl.class);
	
	private MediaAttributeValueDAO mavDAO;
	private MediaAttributeDAO maDAO;
	private MediaDAO mediaDAO;
	
	private MediaAttributeValueFullDTOFactory mavFullDTOFactory;

	/**
	 * 
	 */
	public MediaAttributeValueFullDTO getMediaAttributeValueFull(Integer mediaId,
			Integer mediaAttributeId) throws IllegalArgumentException {
		logger.debug("getMediaAttributeValueFull() with mediaId " + mediaId + " and mediaAttributeId "+mediaAttributeId);
		
		MediaAttributeValueId mavId = new MediaAttributeValueId(mediaAttributeId.intValue(),mediaAttributeId.intValue()); 
		MediaAttributeValue mav = (MediaAttributeValue) mavDAO.findById(MediaAttributeValue.class, mavId);

		if(mav == null)
			throw new IllegalArgumentException("no media attribute value information");
		
		return (MediaAttributeValueFullDTO) mavFullDTOFactory.createDTO(mav);

	}

	/**
	 * 
	 */
	public void insertMediaAttributeValueFull(MediaAttributeValueFullDTO mavFull)
			throws IllegalArgumentException {

		try {

			MediaAttributeValueId mavId = new MediaAttributeValueId(mavFull.getMediaAttributeId(), mavFull.getMediaId());
			MediaAttributeValue mav = new MediaAttributeValue(mavId);

			mav.setValueVarchar(mavFull.getValueVarchar());
			mav.setValueNumber(mavFull.getValueNumber());
			mav.setValueDate(mavFull.getValueDate());
			mav.setValueId(mavFull.getValueId());

			// saves the Media Object in the database
			mavDAO.create(mav);
		} catch (Exception e) {
			throw new IllegalArgumentException("feo feo!");
		}


	}

	public void updateMediaAttributeValueFull(MediaAttributeValueFullDTO mavFull)
			throws IllegalArgumentException {
		try {

			MediaAttributeValueId mavId = new MediaAttributeValueId(mavFull.getMediaAttributeId(), mavFull.getMediaId());
			MediaAttributeValue mav = new MediaAttributeValue(mavId);

			mav.setValueVarchar(mavFull.getValueVarchar());
			mav.setValueNumber(mavFull.getValueNumber());
			mav.setValueDate(mavFull.getValueDate());
			mav.setValueId(mavFull.getValueId());

			// saves the Media Object in the database
			mavDAO.update(mav);
		} catch (Exception e) {
			throw new IllegalArgumentException("feo feo!");
		}

	}

	/**
	 * @param mavDAO the mavDAO to set
	 */
	public void setMavDAO(MediaAttributeValueDAO mavDAO) {
		this.mavDAO = mavDAO;
	}

	/**
	 * @return the mavDAO
	 */
	public MediaAttributeValueDAO getMavDAO() {
		return mavDAO;
	}

	/**
	 * @param mavFullDTOFactory the mavFullDTOFactory to set
	 */
	public void setMavFullDTOFactory(MediaAttributeValueFullDTOFactory mavFullDTOFactory) {
		this.mavFullDTOFactory = mavFullDTOFactory;
	}

	/**
	 * @return the mavFullDTOFactory
	 */
	public MediaAttributeValueFullDTOFactory getMavFullDTOFactory() {
		return mavFullDTOFactory;
	}

	/**
	 * @param mediaDAO the mediaDAO to set
	 */
	public void setMediaDAO(MediaDAO mediaDAO) {
		this.mediaDAO = mediaDAO;
	}

	/**
	 * @return the mediaDAO
	 */
	public MediaDAO getMediaDAO() {
		return mediaDAO;
	}

	/**
	 * @param maDAO the maDAO to set
	 */
	public void setMaDAO(MediaAttributeDAO maDAO) {
		this.maDAO = maDAO;
	}

	/**
	 * @return the maDAO
	 */
	public MediaAttributeDAO getMaDAO() {
		return maDAO;
	}

}
