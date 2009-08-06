/**
 * 
 */
package org.inbio.m3s.service.impl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.service.AbstractServiceTest;
import org.inbio.m3s.service.AgentManager;

/**
 * @author jgutierrez
 *
 */
public class AgentManagerTest extends AbstractServiceTest{
	
	protected static Log logger = LogFactory.getLog(AgentManagerTest.class);
	
	
	public void testGetPersonLiteByName(){
		AgentManager am = (AgentManager) getBean(Properties.AGENT_MANAGER);
		PersonLiteDTO pDTO = am.getPersonLiteByName("Eduardo Boza");
		logger.info("Usando el AgentManger: "+Properties.AGENT_MANAGER);
		logger.info(pDTO.toString());
	}
	
	
	/*
	public void testGetAllM3SPersonLite(){
		AgentManager am = (AgentManager) getBean(Properties.AGENT_MANAGER);
		List<PersonLiteDTO> iList = am.getAllPersonLite();
		logger.info("Usando el AgentManger: "+Properties.AGENT_MANAGER);
		
		logger.info("Total de personas: "+iList.size());
		
		//for(PersonLiteDTO p: iList){
		//	logger.info(p.toString());
		//}
		
		logger.info("La primera:");
		logger.info(iList.get(0).toString());
	}
	*/
	
}
