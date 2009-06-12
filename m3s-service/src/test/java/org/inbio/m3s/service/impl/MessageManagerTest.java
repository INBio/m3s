/**
 * 
 */
package org.inbio.m3s.service.impl;


import java.util.List;

import org.inbio.m3s.config.Properties;
import org.inbio.m3s.dto.message.KeywordLiteDTO;
import org.inbio.m3s.service.AbstractServiceTest;
import org.inbio.m3s.service.MessageManager;

/**
 * @author jgutierrez
 *
 */
public class MessageManagerTest extends AbstractServiceTest{

	
	public void testSpanishKeywords(){
		
		MessageManager sm = (MessageManager) getBean(Properties.MESSAGE_MANAGER);	
		List<KeywordLiteDTO> klList = sm.getAllKeywordLite(MessageManager.ESPANYOL);
		if(klList == null){
    	
   	 System.out.println("No hay keyword para esos paremetros");
    
    } else { 
	     //logger.debug("con el id["+kl.getKeywordId()+"] y el nombre ="+kl.getName() );
	     System.out.println("total de elementos: " + klList.size());
	     
	     for(KeywordLiteDTO kl : klList){
	    	 System.out.println("con el id["+kl.getKeywordKey()+"] y el nombre ="+kl.getName() );
	     }
    }
		
	}


}
