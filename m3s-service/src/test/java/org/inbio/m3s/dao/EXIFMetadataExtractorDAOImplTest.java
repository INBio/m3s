package org.inbio.m3s.dao;

import org.inbio.m3s.dao.core.SecurityUserDAO;
import org.inbio.m3s.dao.multimedia.MetadataExtractorDAO;
import org.inbio.m3s.dto.metadata.util.EXIFStandardAttributeEntity;
import org.inbio.m3s.model.core.SecurityUsers;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

@SuppressWarnings("unused")
public class EXIFMetadataExtractorDAOImplTest extends AbstractDependencyInjectionSpringContextTests{

	@Override
	protected String[] getConfigLocations() {
		return new String [] {
				//"classpath*:/**/applicationContext-*.xml",
				//"classpath*:**/applicationContext-*.xml",
				//"classpath*:org/inbio/m3s/**/applicationContext-*.xml",
				//"classpath*:/org/inbio/m3s/**/impl/applicationContext-*-test.xml",
				"classpath*:org/inbio/m3s/dao/applicationContext-dao.xml",
				"classpath*:org/inbio/m3s/dao/applicationContext-factories.xml",
				//"classpath*:/org/inbio/m3s/service/impl/applicationContext-service-test.xml"				
				};
	}
	
	public void testSimple(){
		
		MetadataExtractorDAO exifExtractor = (MetadataExtractorDAO) this.applicationContext.getBean("EXIFMetadataExtractorDAO");

		exifExtractor.init("/home/james/Desktop/Foto.jpg");
		
		System.out.println(EXIFStandardAttributeEntity.ISO.getName()+" = "+exifExtractor.getAttributeValue(EXIFStandardAttributeEntity.ISO.getId()));
		System.out.println(EXIFStandardAttributeEntity.CAMARA_MAKER.getName()+" = "+exifExtractor.getAttributeValue(EXIFStandardAttributeEntity.CAMARA_MAKER.getId()));
		System.out.println(EXIFStandardAttributeEntity.CAMARA_MODEL.getName()+" = "+exifExtractor.getAttributeValue(EXIFStandardAttributeEntity.CAMARA_MODEL.getId()));
		System.out.println(EXIFStandardAttributeEntity.METERING_MODE.getName()+" = "+exifExtractor.getAttributeValue(EXIFStandardAttributeEntity.METERING_MODE.getId()));
		System.out.println(EXIFStandardAttributeEntity.EXPOSURE_MODE.getName()+" = "+exifExtractor.getAttributeValue(EXIFStandardAttributeEntity.EXPOSURE_MODE.getId()));
		System.out.println(EXIFStandardAttributeEntity.F_NUMBER.getName()+" = "+exifExtractor.getAttributeValue(EXIFStandardAttributeEntity.F_NUMBER.getId()));
		System.out.println(EXIFStandardAttributeEntity.FOCAL_LENGTH.getName()+" = "+exifExtractor.getAttributeValue(EXIFStandardAttributeEntity.FOCAL_LENGTH.getId()));
		System.out.println(EXIFStandardAttributeEntity.EXPOSURE_BIAS.getName()+" = "+exifExtractor.getAttributeValue(EXIFStandardAttributeEntity.EXPOSURE_BIAS.getId()));
		System.out.println(EXIFStandardAttributeEntity.WHITE_BALANCE.getName()+" = "+exifExtractor.getAttributeValue(EXIFStandardAttributeEntity.WHITE_BALANCE.getId()));
		System.out.println(EXIFStandardAttributeEntity.SATURATION.getName()+" = "+exifExtractor.getAttributeValue(EXIFStandardAttributeEntity.SATURATION.getId()));
		System.out.println(EXIFStandardAttributeEntity.FLASH.getName()+" = "+exifExtractor.getAttributeValue(EXIFStandardAttributeEntity.FLASH.getId()));
		System.out.println(EXIFStandardAttributeEntity.PIXELS_HEIGHT.getName()+" = "+exifExtractor.getAttributeValue(EXIFStandardAttributeEntity.PIXELS_HEIGHT.getId()));
		System.out.println(EXIFStandardAttributeEntity.PIXELS_WIDTH.getName()+" = "+exifExtractor.getAttributeValue(EXIFStandardAttributeEntity.PIXELS_WIDTH.getId()));
		System.out.println(EXIFStandardAttributeEntity.EXPOSURE_TIME.getName()+" = "+exifExtractor.getAttributeValue(EXIFStandardAttributeEntity.EXPOSURE_TIME.getId()));
		System.out.println(EXIFStandardAttributeEntity.PRODUCTION_DATE.getName()+" = "+exifExtractor.getAttributeValue(EXIFStandardAttributeEntity.PRODUCTION_DATE.getId()));
		System.out.println(EXIFStandardAttributeEntity.RESOLUTION.getName()+" = "+exifExtractor.getAttributeValue(EXIFStandardAttributeEntity.RESOLUTION.getId()));
		System.out.println(EXIFStandardAttributeEntity.LIGHT_SOURCE.getName()+" = "+exifExtractor.getAttributeValue(EXIFStandardAttributeEntity.LIGHT_SOURCE.getId()));
		
		
		assertTrue( true );
		
	}


}
