/**
 * 
 */
package org.inbio.m3s.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.dto.metadata.TechnicalMetadataDTO;
import org.inbio.m3s.service.AbstractServiceTest;
import org.inbio.m3s.service.MetadataManager;

/**
 * @author jgutierrez
 *
 */
public class MetadataManagerTest extends AbstractServiceTest{
	
	protected static Log logger = LogFactory.getLog(MetadataManagerTest.class);

	public void testGetTechMetadataByMediaType(){
		
		MetadataManager mm = (MetadataManager) getBean(Properties.METADATA_MANAGER);
		logger.info("Usando el Metadata Manger: "+Properties.METADATA_MANAGER);
		
		TechnicalMetadataDTO tmDTO = mm.getTechMetadataByMediaType("1");
		logger.info(tmDTO.toString());
	}
	
	public void testGetTechMetadataFromFile(){
		
		MetadataManager mm = (MetadataManager) getBean(Properties.METADATA_MANAGER);
		logger.info("Usando el Metadata Manger: "+Properties.METADATA_MANAGER);
		
		TechnicalMetadataDTO tmDTO = mm.getTechMetadataFromFile("1", "/home/jgutierrez/Desktop/Foto.jpg");
		logger.info(tmDTO.toString());
	}
	
	public void testGetTechMetadataByMedia(){
		
		MetadataManager mm = (MetadataManager) getBean(Properties.METADATA_MANAGER);
		logger.info("Usando el Metadata Manger: "+Properties.METADATA_MANAGER);
		
		TechnicalMetadataDTO tmDTO = mm.getTechMetadataByMedia("100056");
		logger.info(tmDTO.toString());
	}

}
