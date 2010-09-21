package org.inbio.m3s.dao;

import org.inbio.m3s.dao.multimedia.MetadataExtractorDAO;
import org.inbio.m3s.dto.metadata.util.EXIFStandardAttributeEntity;
import org.inbio.m3s.dto.metadata.util.MediaAttributeEntity;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

@SuppressWarnings("unused")
public class ExifGpsMetadataExtractorDAOImplTest extends AbstractDependencyInjectionSpringContextTests{

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
		
		MetadataExtractorDAO exifExtractor = (MetadataExtractorDAO) this.applicationContext.getBean("ExifGpsMetadataExtractorDAO");

		//exifExtractor.init("/home/james/Desktop/Foto.jpg");
		exifExtractor.init("/home/jgutierrez/Desktop/FujiFilm FinePixS1Pro.jpg");
		
		System.out.println(MediaAttributeEntity.GPS_LATITUDE.getNamekey()+" = "+exifExtractor.getAttributeValue(MediaAttributeEntity.GPS_LATITUDE.getMediaAtributeId()));		
		System.out.println(MediaAttributeEntity.GPS_LONGITUDE.getNamekey()+" = "+exifExtractor.getAttributeValue(MediaAttributeEntity.GPS_LONGITUDE.getMediaAtributeId()));
		
		assertTrue( true );
		
	}


}
