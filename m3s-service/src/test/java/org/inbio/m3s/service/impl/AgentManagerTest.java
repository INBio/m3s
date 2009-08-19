/**
 * 
 */
package org.inbio.m3s.service.impl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.dto.agent.InstitutionLiteDTO;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.service.AbstractServiceTest;
import org.inbio.m3s.service.AgentManager;

/**
 * @author jgutierrez
 *
 */
public class AgentManagerTest extends AbstractServiceTest{
	
	protected static Log logger = LogFactory.getLog(AgentManagerTest.class);
	
	protected static String testInstitutionName = "Museo Entomológico de León";
	protected static String testInstitutionKey = "1";
	//protected static String testPersonName = "Eduardo Boza";
	protected static String testPersonName = "Jaime Gutiérrez";
	protected static String testPersonKey = "640";
	
	public void testGetInstitutionLiteByName(){
		logger.info("testGetInstitutionLiteByName");
		AgentManager am = (AgentManager) getBean(Properties.AGENT_MANAGER);
		InstitutionLiteDTO iDTO = am.getInstitutionLiteByName(testInstitutionName);
		logger.info("Usando el AgentManger: "+Properties.AGENT_MANAGER);
		logger.info(iDTO.toString());
	}
	
	public void testGetInstitutionLite(){
		logger.info("testGetInstitutionLite");
		AgentManager am = (AgentManager) getBean(Properties.AGENT_MANAGER);
		InstitutionLiteDTO iDTO = am.getInstitutionLite(testInstitutionKey);
		logger.info("Usando el AgentManger: "+Properties.AGENT_MANAGER);
		logger.info(iDTO.toString());
	}
	
	public void testGetAllInstitutionLite(){
		AgentManager am = (AgentManager) getBean(Properties.AGENT_MANAGER);
		List<InstitutionLiteDTO> iList = am.getAllInstitutionLite();
		logger.info("Usando el AgentManger: "+Properties.AGENT_MANAGER);
		
		logger.info("Total de instituciones: "+iList.size());
		
		//for(PersonLiteDTO p: iList){
		//	logger.info(p.toString());
		//}
		if(iList.size() > 1){
			logger.info("La primera:");
			logger.info(iList.get(0).toString());
		}
	}	

	public void testGetPersonLiteByName(){
		AgentManager am = (AgentManager) getBean(Properties.AGENT_MANAGER);
		PersonLiteDTO pDTO = am.getPersonLiteByName(testPersonName);
		logger.info("Usando el AgentManger: "+Properties.AGENT_MANAGER);
		logger.info(pDTO.toString());
	}	
	
	public void testGetPersonLite(){
		logger.info("testGetPersonLite");
		AgentManager am = (AgentManager) getBean(Properties.AGENT_MANAGER);
		PersonLiteDTO pDTO = am.getPersonLite(testPersonKey);
		logger.info("Usando el AgentManger: "+Properties.AGENT_MANAGER);
		logger.info(pDTO.toString());
	}	
	
	public void testGetAllPersonLite(){
		AgentManager am = (AgentManager) getBean(Properties.AGENT_MANAGER);
		List<PersonLiteDTO> pList = am.getAllPersonLite();
		logger.info("Usando el AgentManger: "+Properties.AGENT_MANAGER);
		
		logger.info("Total de personas: "+pList.size());
		
		//for(PersonLiteDTO p: iList){
		//	logger.info(p.toString());
		//}
		
		if(pList.size() > 1){
			logger.info("La primera:");
			logger.info(pList.get(0).toString());
		}
	}		
	
	/*		
	public PersonLiteDTO getGatheringResposibleLiteByName(String personName)	throws IllegalArgumentException;	
	public PersonLiteDTO getGatheringResposiblePersonLite(String personKey)	throws IllegalArgumentException;	
	public List<PersonLiteDTO> getAllGatheringResponsibleLite() throws IllegalArgumentException;
	 */
	

	
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
