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
/*
	public void testGetInstitutionLite(){
		AgentManager am = (AgentManager) getBean(Properties.AGENT_MANAGER);	
		InstitutionLiteDTO iLiteDTO = am.getInstitutionLite("1");
		System.out.println(iLiteDTO.toString());
	}

	public void testGetAllInstitutionLite(){
		AgentManager am = (AgentManager) getBean(Properties.AGENT_MANAGER);	
		List<InstitutionLiteDTO> iList = am.getAllInstitutionLite();
		
		System.out.println("Total de instituciones: "+iList.size());
		
		System.out.println("La primera:");
		System.out.println(iList.get(0).toString());
	}
*/	
	/*
	public void testGetAllINBioPersonLite(){
		AgentManager am = (AgentManager) getBean(Properties.AGENT_MANAGER);	
		List<PersonLiteDTO> iList = am.getAllPersonLite();
		
		System.out.println("Total de persona en el INBio: "+iList.size());
		
		System.out.println("La primera:");
		System.out.println(iList.get(0).toString());
	}
	*/
	
	public void testGetAllM3SPersonLite(){
		AgentManager am = (AgentManager) getBean(Properties.AGENT_MANAGER);
		List<PersonLiteDTO> iList = am.getAllPersonLite();
		logger.info("Usando el AgentManger: "+Properties.AGENT_MANAGER);
		
		logger.info("Total de persona en el M3S: "+iList.size());
		
		logger.info("La primera:");
		logger.info(iList.get(0).toString());
	}
	
	/*
	public void testGetAllARAPersonLite(){
		AgentManager am = (AgentManager) getBean("ARAAgentManager");	
		List<PersonLiteDTO> iList = am.getAllPersonLite();
		
		System.out.println("Total de persona en el INBio: "+iList.size());
		
		System.out.println("La primera:");
		System.out.println(iList.get(0).toString());
	}
	*/
	
	/*
	public void testGetPersonLiteByName(){
		AgentManager am = (AgentManager) getBean(Properties.AGENT_MANAGER);	
		PersonLiteDTO plDTO = am.getPersonLiteByName("José Montero");
		System.out.println(plDTO.toString());
	}
	
	public void testGetAllGatheringResponsibleLite(){
		AgentManager am = (AgentManager) getBean(Properties.AGENT_MANAGER);	
		List<PersonLiteDTO> iList = am.getAllGatheringResponsibleLite();
		
		System.out.println("Total de responsables de colectas: "+iList.size());
		
		System.out.println("El primero:");
		System.out.println(iList.get(0).toString());
		
		for(PersonLiteDTO plDTO : iList){
			System.out.println(plDTO.toString());
		}
	}
	
	public void testGatheringResposibleLiteByName(){
		AgentManager am = (AgentManager) getBean(Properties.AGENT_MANAGER);	
		PersonLiteDTO plDTO = am.getGatheringResposibleLiteByName("Daniel Santamaría");
		System.out.println(plDTO.toString());
	}
	*/
}
