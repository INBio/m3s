/**
 * 
 */
package org.inbio.m3s.usecases.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.dao.core.MediaAttributeDAO;
import org.inbio.m3s.dao.core.MediaAttributeTypeDAO;
import org.inbio.m3s.dao.core.MediaTypeDAO;
import org.inbio.m3s.dao.core.MetadataStandardDAO;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.TechnicalMetadataTV;
import org.inbio.m3s.dto.full.MediaAttributeFull;
import org.inbio.m3s.dto.full.MetadataStandardFull;
import org.inbio.m3s.dto.lite.MediaAttributeTypeLite;
import org.inbio.m3s.dto.lite.MediaTypeLite;
import org.inbio.m3s.usecases.admin.impl.EXIFMetadataExtractorRedHatImpl;
import org.inbio.m3s.usecases.admin.impl.EXIFMetadataExtractorDebianImpl;
import org.inbio.m3s.util.ServiceUtil;

/**
 * @author jgutierrez
 * 
 */
public class TechnicalAttributesManager {

	private static Logger logger = Logger.getLogger(TechnicalAttributesManager.class);

	/**
	 * Gets the technicalMetadata for a file and not from the database. Also load's
	 * the expected Names every technical metadata attribute
	 * 
	 * @param fileAddress
	 * @param mediaTypeName
	 * @return a TechnicalMetadataTV Object
	 * @throws IllegalArgumentException
	 * 
	 */
	public static TechnicalMetadataTV getTechnicalMetadataTVFromFile(
			String fileAddress, String mediaTypeName) throws IllegalArgumentException {
		logger.debug("getting Technical Metadata TV... from a file");
		MediaTypeDAO mediaTypeDAO = (MediaTypeDAO) ServiceUtil.appContext.getBean("mediaTypeDAO");
		MediaTypeLite mediaTypeLite = null;
		MediaAttributeTypeDAO matDAO = (MediaAttributeTypeDAO)  ServiceUtil.appContext.getBean("mediaAttributeTypeDAO");
		List<MediaAttributeTypeLite> matLiteList = null;
		MediaAttributeDAO maDAO = (MediaAttributeDAO) ServiceUtil.appContext.getBean("mediaAttributeDAO");
		MediaAttributeFull maFull = null;
		MetadataStandardDAO medatadataStandardDAO = (MetadataStandardDAO) ServiceUtil.appContext.getBean("metadataStandardDAO");
		MetadataStandardFull msf = null;
		
		TechnicalMetadataTV tmtv = new TechnicalMetadataTV();
		tmtv.setMediaId(null);
		tmtv.setMediaType(mediaTypeName);

		mediaTypeLite = mediaTypeDAO.getMediaTypeLite(mediaTypeName);
		//Integer mediaTypeDBId = mediaTypeLite.getMediaTypeId();
		logger.debug("getting Technical Metadata TV... for media type "
				+ mediaTypeName + "[" + mediaTypeLite.getMediaTypeId() + "].");

		// resulting lists to be added to the tmtv object
		List<String> names = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		
		matLiteList = matDAO.getAllByMediaType(mediaTypeLite.getMediaTypeId());
		
		// sets the values in the TechnicalMetadataTVObect
			String attributeValue = "";
			MetadataExtractor metadataExtractor = null;
			// sets the temporal values
			logger.debug("getting TechnicalMetadata info from[" + fileAddress + "]");
			
			for(MediaAttributeTypeLite matLite : matLiteList){
				maFull = maDAO.getMediaAttributeFull(matLite.getMediaAttributeId());
				logger.debug("Trabajando con: MANameTextual["+maFull.getName()+"], " 
						+	"metadataStandardDBId[" + matLite.getMetadataStandardId() + "], " 
						+	"standarAttributeId[" +matLite.getStandardAttributeId() + "]");
				
				if (matLite.getMetadataStandardId().equals(MetadataExtractor.EXIF)) {
					if(Properties.OS_FAMILY == Properties.DEBIAN_FAMILY)
						metadataExtractor = (MetadataExtractor) new EXIFMetadataExtractorDebianImpl();
					else if (Properties.OS_FAMILY == Properties.REDHAT_FAMILY)
						metadataExtractor = (MetadataExtractor) new EXIFMetadataExtractorRedHatImpl();
					
					metadataExtractor.init(fileAddress);

					logger.debug("EXIF standarAttributeId [" + matLite.getStandardAttributeId() + "]");
					attributeValue = metadataExtractor.getAttributeValue(matLite.getStandardAttributeId());
				
				} else {
					//	other metadata
					// Extracts the metadata using the specific implementarion of
					// the metadataExtractor for every single case
					logger.debug("medatadataStandardDBId [" + matLite.getMetadataStandardId() + "]");

					try {
						msf = medatadataStandardDAO.getMetadataStandardFull(matLite.getMetadataStandardId());
						//metadataExtractor = (MetadataExtractor) Class.forName(
						//		MetadataStandardDAO.getImplementingClass(metadataStandardDBId))
						//		.newInstance();
						logger.debug("Metadata Standard Implementation Class: "+ msf.getMetadataStandardImplementationClass());
						metadataExtractor = (MetadataExtractor) Class.forName(msf.getMetadataStandardImplementationClass()).newInstance();
					} catch (Exception e) {
						logger.error("error: "+ e.getMessage());
						logger.error("error: "+ e.toString());
						e.printStackTrace();
					}
 
					// TODO: check this. start
					metadataExtractor.init(fileAddress);

					logger.debug("standarAttributeId [" + matLite.getStandardAttributeId() + "]");
					try{
					attributeValue = metadataExtractor.getAttributeValue(matLite.getStandardAttributeId().intValue());
					logger.debug("attributeValue [" + attributeValue + "]");
					} catch (Exception e) {
						logger.error("error: "+ e.getMessage());
						logger.error("error: "+ e.toString());
						e.printStackTrace();
					}
					// TODO: check this. finish
				}
				
				names.add(maFull.getName());
				values.add(attributeValue);
								
			}

		tmtv.setNames(names);
		tmtv.setValues(values);
		logger.debug("getting TechnicalMetadataTV from file... done.");
		return tmtv;
	}
}
