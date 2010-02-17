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

	/*
	public void testGetMetadataByMedia(){
		MetadataManager mm = (MetadataManager) getBean(Properties.METADATA_MANAGER);	
		logger.info("Usando el Metadata Manger: "+Properties.METADATA_MANAGER);
		MetadataDTO mDTO = mm.getMetadataByMedia("1");
		logger.info(mDTO.toString());
			
	}
	*/	
	
	/*
	logger.info("media attribute id: "+String.valueOf(MediaAttributeEntity.YOUTUBE_ID.getMediaAtributeId()));
	TechnicalMetadataItemDTO maDTO = mDTO.getMediaAttributeItemByKey(String.valueOf(MediaAttributeEntity.YOUTUBE_ID.getMediaAtributeId()));
	logger.info("el youtube video Id");
	logger.info(maDTO.toString());
	*/
	
	
	public void testUpdateMetadata(){
		MetadataManager mm = (MetadataManager) getBean(Properties.METADATA_MANAGER);	
		logger.info("Usando el Metadata Manger: "+Properties.METADATA_MANAGER);
		MetadataDTO mDTO = mm.getMetadataByMedia("1");
		logger.info(mDTO.toString());
		logger.info("Antes, isPublic: "+mDTO.getIsPublic());
		
		mDTO.setIsPublic(new Character('N'));
		mm.updateMetadata(mDTO);
		
		mDTO = mm.getMetadataByMedia("1");
		logger.info("Despues, isPublic: "+mDTO.getIsPublic());
	}
	
	
	
	
		/*
		public void testGetTechMetadataFromFile(){
			
			MetadataManager mm = (MetadataManager) getBean(Properties.METADATA_MANAGER);
			logger.info("Usando el Metadata Manger: "+Properties.METADATA_MANAGER);
			
			TechnicalMetadataDTO tmDTO = mm.getTechMetadataFromFile("1", "/home/jgutierrez/Desktop/Foto.jpg");
			//TechnicalMetadataDTO tmDTO = mm.getTechMetadataFromFile("1", "/home/jgutierrez/SoftwareTools/apache-tomcat-6.0.16/webapps/m3sINBioFiles/TEMP_MEDIA_DIR/20090730-122053-75DE823CB57784B15DC524EABD8B181B1-Norops-capito.jpg");
			//TechnicalMetadataDTO tmDTO = mm.getTechMetadataFromFile("1", "/mnt/m3sImages/INBio/TEMP_MEDIA_DIR/20100205-153921-jgutierrez17fr0vfnv9tmf1.jpg");
			logger.info(tmDTO.toString());
		}
		*/
		
	
	/*
	public void testInsertNewMedia(){
		
		MediaManager mm = (MediaManager) getBean(Properties.MEDIA_MANAGER);	
				
		GeneralMetadataDTO gm = new GeneralMetadataDTO("title", "description", new Integer(1),null,"site description");
		UsesAndCopyrightsDTO uac = new UsesAndCopyrightsDTO(new Integer(10017),null,new Integer(1),new Integer(2),'N','Y'); 
		TechnicalMetadataDTO tm = mm.getTM(new Integer(100015));
		
		Integer mediaId = mm.insertNewMedia(gm, uac, tm);
		
		System.out.println("media insertado = "+mediaId);
	}
*/

	
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
