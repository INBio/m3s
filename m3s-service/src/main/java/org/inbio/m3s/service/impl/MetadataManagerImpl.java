/**
 * 
 */
package org.inbio.m3s.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.m3s.dao.core.MediaAttributeDAO;
import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.dto.full.MediaAttributeFull;
import org.inbio.m3s.dto.lite.MediaLite;
import org.inbio.m3s.dto.mediaattribute.MediaAttributeValueFullDTO;
import org.inbio.m3s.dto.metadata.TechnicalMetadataDTO;
import org.inbio.m3s.model.core.Media;
import org.inbio.m3s.service.MetadataManager;

/**
 * @author jgutierrez
 *
 */
public class MetadataManagerImpl implements MetadataManager {
	
	private static Logger logger = Logger.getLogger(MetadataManagerImpl.class);
	
	//falta hacer los gets y sets
	private MediaDAO mediaDAO;
	private MediaAttributeDAO mediaAttributeDAO;

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.MetadataManager#getTechnicalMetadata(java.lang.Integer)
	 */
	public TechnicalMetadataDTO getTechnicalMetadata(Integer mediaId) {
		logger.debug("getTechnicalMetadata with mediaId [" + mediaId + "]");
		
		Media media =  (Media) mediaDAO.findById(Media.class, mediaId);
				
		TechnicalMetadataDTO tmDTO = new TechnicalMetadataDTO(mediaId.toString(),media.getMediaType().toString());
		
		// TODO Auto-generated method stub
		return null;
	}
	
/*
public TechnicalMetadataDTO getTM(Integer mediaId) {
		
		
		
		List<MediaAttributeFull> mediaAttributes = null;
		List<Integer> mediaAttributeIds = new ArrayList<Integer>();
		List<Object> mediaAttributeValues = new ArrayList<Object>();
		MediaAttributeValueFullDTO mavFull = null;
		logger.debug("getting TM... gotten mediaId:" + tm.getMediaId());

		
		
		try {
			

			//sets the media attributes id's	
			mediaAttributes = mediaAttributeDAO.getMediaAttributesFullForMediaType(tm.getMediaTypeId()); 

			
			for(MediaAttributeFull maf : mediaAttributes){
				logger.debug("getting TM... getting media Attributes values for '"
						+ maf.getMediaAttributeId() + "'");
				
				mediaAttributeIds.add(maf.getMediaAttributeId());

				mavFull = mediaAttributeValueDAO.getMediaAttributeValueFull(mediaId, maf.getMediaAttributeId());
				switch(maf.getMediaAttributeValueType()){
					case 'V' :
						mediaAttributeValues.add(mavFull.getValueVarchar());
						break;
					case 'N' :
						mediaAttributeValues.add(mavFull.getValueNumber());
						break;
					case 'D' :
						mediaAttributeValues.add(mavFull.getValueDate());
						break;
					default:
						mediaAttributeValues.add("");
				}
				
			}
			
			tm.setMediaAttributeIds(mediaAttributeIds);
			logger.debug("getting TM... gotten media Attributes.>"	
					+ tm.getMediaAttributeIds().size());
			tm.setMediaAttributeValue(mediaAttributeValues);
			logger.debug("getting TM... gotten media Attributes values.>"
					+ tm.getMediaAttributeValue().size());

		} catch (Exception he) {
			logger.error("There was a hibernate exeption in the query");
			throw new IllegalArgumentException("Query fails on getTM", he);
		} 

		logger.info("getting TM... done.");
		return tm;
	}
	*/

}
