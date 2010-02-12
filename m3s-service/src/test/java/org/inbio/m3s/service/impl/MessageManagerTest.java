/**
 * 
 */
package org.inbio.m3s.service.impl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.dto.metadata.UsePolicyDTO;
import org.inbio.m3s.dto.util.KeyValueDTO;
import org.inbio.m3s.exception.UsePolicyNotFoundException;
import org.inbio.m3s.model.core.UsePolicy;
import org.inbio.m3s.service.AbstractServiceTest;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.MessageManager;

/**
 * @author jgutierrez
 *
 */
@SuppressWarnings("unused")
public class MessageManagerTest extends AbstractServiceTest{

	protected static Log logger = LogFactory.getLog(MessageManagerTest.class);
	
	/*
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
	*/
	
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
	
public void testGetUsePolicyByName(){
		try {
		MessageManager sm = (MessageManager) getBean(Properties.MESSAGE_MANAGER);	
		UsePolicyDTO kl = sm.getUsePolicyByName("Consultar con el autor");
		if(kl == null){
    	
   	 System.out.println("No hay UsePolicyDTO para esos paremetros");
    
    } else { 

	    	 System.out.println("con el id["+kl.getUsePolicyKey()+"] y el nombre ="+kl.getName() );
    }
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	
	//public UsePolicyDTO getUsePolicyByName(String usePolicyName) throws UsePolicyNotFoundException {

	
	/*
	public void testGetAllAssociatedToValues(){
		logger.info("testGetAllAssociatedToValues");
		MessageManager sm = (MessageManager) getBean(Properties.MESSAGE_MANAGER);
		logger.info("Usando el MessageManager: "+Properties.AGENT_MANAGER);
		List<KeyValueDTO> atDTOList = sm.getAllAssociatedToValues();
		for(KeyValueDTO atDTO : atDTOList){
			logger.info(atDTO.getKey()+" "+atDTO.getNameKey());
		}
		
	}
*/

}
