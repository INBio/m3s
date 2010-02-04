/**
 * 
 */
package org.inbio.m3s.service.impl;

import org.inbio.m3s.config.Properties;
import org.inbio.m3s.dto.metadata.MetadataDTO;
import org.inbio.m3s.dto.metadata.TechnicalMetadataDTO;
import org.inbio.m3s.dto.metadata.TechnicalMetadataItemDTO;
import org.inbio.m3s.dto.metadata.util.MediaAttributeEntity;
import org.inbio.m3s.service.AbstractServiceTest;
import org.inbio.m3s.service.MetadataManager;

/**
 * @author jgutierrez
 *
 */
@SuppressWarnings("unused")
public class MetadataManagerTest extends AbstractServiceTest{

	/*	*/
	public void testGetMetadataByMedia(){
		MetadataManager mm = (MetadataManager) getBean(Properties.METADATA_MANAGER);	
		logger.info("Usando el Metadata Manger: "+Properties.METADATA_MANAGER);
		MetadataDTO mDTO = mm.getMetadataByMedia("26");
		logger.info(mDTO.toString());
		
		logger.info("media attribute id: "+String.valueOf(MediaAttributeEntity.YOUTUBE_ID.getMediaAtributeId()));
		TechnicalMetadataItemDTO maDTO = mDTO.getMediaAttributeItemByKey(String.valueOf(MediaAttributeEntity.YOUTUBE_ID.getMediaAtributeId()));
		logger.info("el youtube video Id");
		logger.info(maDTO.toString());
		
	}

	
	/*
	public void testGetTechMetadataByMedia(){
		
		MetadataManager mm = (MetadataManager) getBean(Properties.METADATA_MANAGER);
		logger.info("Usando el Metadata Manger: "+Properties.METADATA_MANAGER);
		
		TechnicalMetadataDTO tmDTO = mm.getTechMetadataByMedia("26");
		logger.info(tmDTO.toString());
	}
	*/
	
	/*
	public void testGetTechMetadataByMediaType(){
		
		MetadataManager mm = (MetadataManager) getBean(Properties.METADATA_MANAGER);
		logger.info("Usando el Metadata Manger: "+Properties.METADATA_MANAGER);
		
		TechnicalMetadataDTO tmDTO = mm.getTechMetadataByMediaType("5");
		logger.info(tmDTO.toString());
	}
*/
	
	/*
	public void testGetTechMetadataFromFile(){
		
		MetadataManager mm = (MetadataManager) getBean(Properties.METADATA_MANAGER);
		logger.info("Usando el Metadata Manger: "+Properties.METADATA_MANAGER);
		
		//TechnicalMetadataDTO tmDTO = mm.getTechMetadataFromFile("1", "/home/jgutierrez/Desktop/Foto.jpg");
		TechnicalMetadataDTO tmDTO = mm.getTechMetadataFromFile("1", "/home/jgutierrez/SoftwareTools/apache-tomcat-6.0.16/webapps/m3sINBioFiles/TEMP_MEDIA_DIR/20090730-122053-75DE823CB57784B15DC524EABD8B181B1-Norops-capito.jpg");
		logger.info(tmDTO.toString());
	}
	*/
	
	/*
	public void testSaveTechnicalMetadata(){
		
		MetadataManager mm = (MetadataManager) getBean(Properties.METADATA_MANAGER);
		logger.info("Usando el Metadata Manger: "+Properties.METADATA_MANAGER);
		
		TechnicalMetadataDTO tmDTO = mm.getTechMetadataFromFile("1", "/home/jgutierrez/Desktop/Foto.jpg");
		tmDTO.setMediaKey("100001");
		
		mm.saveTechnicalMetadata(tmDTO);
		
		//logger.info(tmDTO.toString());
	}
	*/
	
	

}
