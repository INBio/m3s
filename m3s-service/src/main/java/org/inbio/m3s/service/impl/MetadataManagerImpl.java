/**
 * 
 */
package org.inbio.m3s.service.impl;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.inbio.m3s.dao.core.MediaAttributeDAO;
import org.inbio.m3s.dao.core.MediaAttributeTypeDAO;
import org.inbio.m3s.dao.core.MediaAttributeValueDAO;
import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.dao.core.TextTranslationDAO;
import org.inbio.m3s.dao.multimedia.MetadataExtractorDAO;
import org.inbio.m3s.dto.metadata.TechnicalMetadataDTO;
import org.inbio.m3s.dto.metadata.TechnicalMetadataItemDTO;
import org.inbio.m3s.dto.metadata.util.MetadataStandardEntity;
import org.inbio.m3s.model.core.Media;
import org.inbio.m3s.model.core.MediaAttribute;
import org.inbio.m3s.model.core.MediaAttributeType;
import org.inbio.m3s.model.core.MediaAttributeValue;
import org.inbio.m3s.model.core.MediaAttributeValueId;
import org.inbio.m3s.model.core.TextTranslation;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.service.MetadataManager;

/**
 * @author jgutierrez
 *
 */
public class MetadataManagerImpl implements MetadataManager {
	
	private static Logger logger = Logger.getLogger(MetadataManagerImpl.class);
	
	private MediaDAO mediaDAO;
	private MediaAttributeDAO mediaAttributeDAO;
	private TextTranslationDAO textTranslationDAO;
	private MediaAttributeTypeDAO mediaAttributeTypeDAO;
	private MediaAttributeValueDAO mediaAttributeValueDAO;
	/* Este dao no se inicializa con inyeccion de dependencia */
	/* This dao is not initialized using dependency injection */
	private MetadataExtractorDAO metadataExtractorDAO;

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.MetadataManager#getTechMetadataByMedia(java.lang.String)
	 */
	public TechnicalMetadataDTO getTechMetadataByMedia(String mediaKey) throws IllegalArgumentException{
		logger.debug("getTechnicalMetadata with mediaId [" + mediaKey + "]");
		
		Media media =  (Media) getMediaDAO().findById(Media.class, new Integer(mediaKey));
		if(media==null)
			throw new IllegalArgumentException("Invalid mediaKey["+mediaKey+"]");
		
		String mediaTypeKey = String.valueOf(media.getMediaType().getMediaTypeId());
				
		TechnicalMetadataDTO tmDTO = getTechMetadataByMediaType(mediaTypeKey);
		tmDTO.setMediaKey(mediaKey);
		
		MediaAttributeValue mav;
		MediaAttributeValueId mavId;
		
		for(TechnicalMetadataItemDTO tmiDTO : tmDTO.getItems()){
			mavId = new MediaAttributeValueId(new Integer(tmiDTO.getMediaAttributeKey()), media.getMediaId());
			mav = (MediaAttributeValue) mediaAttributeValueDAO.findById(MediaAttributeValue.class, mavId);
			
			switch(mav.getMediaAttribute().getMediaAttributeValueType()){
				case 'V' :
					tmiDTO.setValue(mav.getValueVarchar());
					break;
				case 'N' :
					if(mav.getValueNumber()!=null)
						tmiDTO.setValue(mav.getValueNumber().toString());
					else
						tmiDTO.setValue(null);
					break;
				case 'D' :
					tmiDTO.setValue(mav.getValueDate().toString());
					break;
				default:
					tmiDTO.setValue("");
			}
			
		}
		
		return tmDTO;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.MetadataManager#getTechMetadataByMediaType(java.lang.String)
	 */
	public TechnicalMetadataDTO getTechMetadataByMediaType(String mediaTypeKey) {
		
		logger.debug("getTechMetadataByMediaType with mediaId [" + mediaTypeKey + "]");
		
		TechnicalMetadataDTO tmDTO = new TechnicalMetadataDTO(null,mediaTypeKey);
		TechnicalMetadataItemDTO tmiDTO;
		TextTranslation tt;
		
		for(MediaAttribute ma : mediaAttributeDAO.findAllByMediaType(mediaTypeKey)){
			tt = getTextTranslationDAO().finByIdAndLanguage(ma.getTextByNameTextId().getTextId(), MessageManager.DEFAULT_LANGUAGE);
			tmiDTO = new TechnicalMetadataItemDTO(String.valueOf(ma.getMediaAttributeId()),tt.getName(),null);
			tmDTO.addItem(tmiDTO);
		}
		return tmDTO;

	}

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.MetadataManager#getTechMetadataFromFile(java.lang.String, java.lang.String)
	 */
	public TechnicalMetadataDTO getTechMetadataFromFile(String mediaTypeKey, String fileAddress) {
		
		logger.debug("getTechMetadataFromFile with mediaTypeKey [" + mediaTypeKey + "] for:"+fileAddress);
		
		TechnicalMetadataDTO tmDTO = new TechnicalMetadataDTO(null,mediaTypeKey);
		TechnicalMetadataItemDTO tmiDTO;
		MetadataStandardEntity metadataStandardEntity;
		MediaAttribute ma;
		TextTranslation tt;
		String name;
		String value;
		String id;
		
		try{
			
			for(MediaAttributeType mat : mediaAttributeTypeDAO.findAllByMediaType(mediaTypeKey)){
				
				metadataStandardEntity = MetadataStandardEntity.getById(mat.getMetadataStandard().getMetadataStandardId().intValue());
				logger.debug("Metadata Standard Implementation Class: "+ metadataStandardEntity.getImplementingClass());
				metadataExtractorDAO = metadataStandardEntity.getMetadataExtractorDAOImpl();
				metadataExtractorDAO.init(fileAddress);
				value = metadataExtractorDAO.getAttributeValue(mat.getStandardAttributeId());
				
				ma = (MediaAttribute) mediaAttributeDAO.findById(MediaAttribute.class, mat.getMediaAttribute().getMediaAttributeId());			
				id = String.valueOf(ma.getMediaAttributeId()); 
				
				tt = getTextTranslationDAO().finByIdAndLanguage(ma.getTextByNameTextId().getTextId(), MessageManager.DEFAULT_LANGUAGE);
				name = tt.getName(); 
	
				tmiDTO = new TechnicalMetadataItemDTO(id,name,value);
				tmDTO.addItem(tmiDTO);
			}
			
			logger.info(tmDTO.toString());
			return tmDTO;
			
		} catch(Exception e){
			return null;
			
		}
	}
	

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.MetadataManager#saveTechnicalMetadata(org.inbio.m3s.dto.metadata.TechnicalMetadataDTO)
	 */
	public void saveTechnicalMetadata(TechnicalMetadataDTO techMetadataDTO) {
		logger.info("saveTechnicalMetadata for mediaId = " + techMetadataDTO.getMediaKey());
		logger.info(techMetadataDTO.toString());
		
		MediaAttribute ma;
		MediaAttributeValue mav;
		MediaAttributeValueId mavId;

		for(TechnicalMetadataItemDTO tmiDTO : techMetadataDTO.getItems()){
			logger.info(tmiDTO.toString());
			mavId = new MediaAttributeValueId(tmiDTO.getMediaAttributeKey(), techMetadataDTO.getMediaKey());
			mav = (MediaAttributeValue) mediaAttributeValueDAO.findById(MediaAttributeValue.class, mavId);
			
			
			if(mav==null)
				mav = saveEmptyMediaAttributeValue(tmiDTO.getMediaAttributeKey(), techMetadataDTO.getMediaKey());
			ma = mav.getMediaAttribute();
			
			switch(ma.getMediaAttributeValueType()){
			case 'V' : {
				logger.info("varchar");
				mav.setValueVarchar(tmiDTO.getValue());
				logger.info("salvando: "+mav.getId()+" con el valor:"+mav.getValueVarchar());
				break;
			}
			case 'N' : {
				logger.info("number");
				try{
					mav.setValueNumber(Integer.valueOf(tmiDTO.getValue()));
				} catch(Exception e){
					mav.setValueNumber(null);
				}
				logger.info("salvando: "+mav.getId()+" con el valor:"+mav.getValueNumber());
				break;
			}
			case 'D' :
				logger.info("date");
				// dateFormat for metadata from the camara
				SimpleDateFormat dateFormat;
				Date date = null;
				try {
					dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
					date = dateFormat.parse(tmiDTO.getValue());
				} catch (Exception e) {
					logger.debug("date viene directamente de la camara");
				}

				try {
					// dateFormat for info from the DB
					dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if (date == null) 
						date = dateFormat.parse(tmiDTO.getValue());
				} catch (Exception e) {
					logger.error(e.getMessage());
					logger.info("salvando: "+mav.getId()+" con el valor:"+mav.getValueDate());
					mav.setValueDate(null);
				}
				
				if(date==null){
					mav.setValueDate(new Date());
					logger.info("salvando: "+mav.getId()+" con el valor:"+mav.getValueDate());
				}
				else{
					mav.setValueDate(new Timestamp(date.getTime()));
					logger.info("salvando: "+mav.getId()+" con el valor:"+mav.getValueDate());
				}
				break;
			}
			
			mediaAttributeValueDAO.update(mav);
		}
			
		logger.info("saveTechnicalMetadata done!");
	}

	/**
	 * 
	 * @param mediaAttributeKey
	 * @param mediaKey
	 * @return
	 */
	private MediaAttributeValue saveEmptyMediaAttributeValue(String mediaAttributeKey, String mediaKey) {
		Media media = (Media) mediaDAO.findById(Media.class, Integer.valueOf(mediaKey));
		MediaAttribute ma = (MediaAttribute) mediaAttributeDAO.findById(MediaAttribute.class, Integer.valueOf(mediaAttributeKey));
		MediaAttributeValueId mavId = new MediaAttributeValueId(ma.getMediaAttributeId(), media.getMediaId());
		MediaAttributeValue mav = new MediaAttributeValue(mavId, ma, media,null,null,null,null,null,null,null,null);
		// saves the Media Object in the database
		mediaAttributeValueDAO.create(mav);
		return mav;
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
	 * @param mediaAttributeDAO the mediaAttributeDAO to set
	 */
	public void setMediaAttributeDAO(MediaAttributeDAO mediaAttributeDAO) {
		this.mediaAttributeDAO = mediaAttributeDAO;
	}

	/**
	 * @return the mediaAttributeDAO
	 */
	public MediaAttributeDAO getMediaAttributeDAO() {
		return mediaAttributeDAO;
	}

	/**
	 * @param textTranslationDAO the textTranslationDAO to set
	 */
	public void setTextTranslationDAO(TextTranslationDAO textTranslationDAO) {
		this.textTranslationDAO = textTranslationDAO;
	}

	/**
	 * @return the textTranslationDAO
	 */
	public TextTranslationDAO getTextTranslationDAO() {
		return textTranslationDAO;
	}

	/**
	 * @return the mediaAttributeTypeDAO
	 */
	public MediaAttributeTypeDAO getMediaAttributeTypeDAO() {
		return mediaAttributeTypeDAO;
	}

	/**
	 * @param mediaAttributeTypeDAO the mediaAttributeTypeDAO to set
	 */
	public void setMediaAttributeTypeDAO(MediaAttributeTypeDAO mediaAttributeTypeDAO) {
		this.mediaAttributeTypeDAO = mediaAttributeTypeDAO;
	}

	/**
	 * @return the metadataExtractorDAO
	 */
	public MetadataExtractorDAO getMetadataExtractorDAO() {
		return metadataExtractorDAO;
	}

	/**
	 * @param metadataExtractorDAO the metadataExtractorDAO to set
	 */
	public void setMetadataExtractorDAO(MetadataExtractorDAO metadataExtractorDAO) {
		this.metadataExtractorDAO = metadataExtractorDAO;
	}


	/**
	 * @return the mediaAttributeValueDAO
	 */
	public MediaAttributeValueDAO getMediaAttributeValueDAO() {
		return mediaAttributeValueDAO;
	}


	/**
	 * @param mediaAttributeValueDAO the mediaAttributeValueDAO to set
	 */
	public void setMediaAttributeValueDAO(
			MediaAttributeValueDAO mediaAttributeValueDAO) {
		this.mediaAttributeValueDAO = mediaAttributeValueDAO;
	}

}
