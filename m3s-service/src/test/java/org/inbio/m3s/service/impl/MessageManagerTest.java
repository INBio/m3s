/**
 * 
 */
package org.inbio.m3s.service.impl;


import java.util.List;

import org.inbio.m3s.config.Properties;
import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.dto.metadata.UsePolicyDTO;
import org.inbio.m3s.service.AbstractServiceTest;
import org.inbio.m3s.service.MessageManager;

/**
 * @author jgutierrez
 *
 */
public class MessageManagerTest extends AbstractServiceTest{

	
	public void testSpanishKeywords(){
		
		MessageManager sm = (MessageManager) getBean(Properties.MESSAGE_MANAGER);	
		List<KeywordDTO> klList = sm.getAllKeywordLite(MessageManager.ESPANYOL);
		if(klList == null){
    	
   	 System.out.println("No hay keyword para esos paremetros");
    
    } else { 
	     //logger.debug("con el id["+kl.getKeywordId()+"] y el nombre ="+kl.getName() );
	     System.out.println("total de elementos: " + klList.size());
	     
	     for(KeywordDTO kl : klList){
	    	 System.out.println("con el id["+kl.getKeywordKey()+"] y el nombre ="+kl.getName() );
	     }
    }
		
	}
	
	public void testGetAllUsePolicies(){
		
		MessageManager sm = (MessageManager) getBean(Properties.MESSAGE_MANAGER);	
		List<UsePolicyDTO> upList = sm.getAllUsePolicies();
		if(upList == null){
    	
   	 System.out.println("No hay UsePolicyDTO para esos paremetros");
    
    } else { 
	     //logger.debug("con el id["+kl.getKeywordId()+"] y el nombre ="+kl.getName() );
	     System.out.println("total de elementos: " + upList.size());
	     
	     for(UsePolicyDTO kl : upList){
	    	 System.out.println("con el id["+kl.getUsePolicyKey()+"] y el nombre ="+kl.getName() );
	     }
    }
		
	}


}
