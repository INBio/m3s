/**
 * 
 */
package org.inbio.m3s.service.impl;


import org.inbio.m3s.config.Properties;
import org.inbio.m3s.dto.GeneralMetadataDTO;
import org.inbio.m3s.dto.TechnicalMetadataDTO;
import org.inbio.m3s.dto.UsesAndCopyrightsDTO;
import org.inbio.m3s.service.AbstractServiceTest;
import org.inbio.m3s.service.MediaManager;

/**
 * @author jgutierrez
 *
 */
public class MediaManagerTest extends AbstractServiceTest{

	public void testGetGM(){
		MediaManager mm = (MediaManager) getBean(Properties.MEDIA_MANAGER);	
		GeneralMetadataDTO gmDTO = mm.getGM(new Integer(100053));
		System.out.println(gmDTO.toString());
	}
	
	public void testGetUACM(){
		MediaManager mm = (MediaManager) getBean(Properties.MEDIA_MANAGER);	
		UsesAndCopyrightsDTO uacDTO = mm.getUACM(new Integer(100053));
		System.out.println(uacDTO.toString());
	}
	
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

}
