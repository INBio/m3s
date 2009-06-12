package org.inbio.m3s.dao;

import java.util.List;

import org.inbio.m3s.dao.core.PersonDAO;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.agent.PersonLiteDTOFactory;
import org.inbio.m3s.model.atta.INBioPerson;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class INBioPersonDAOTest extends AbstractDependencyInjectionSpringContextTests{

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
	/*
	public void testFindAll(){
		
		PersonDAO INBioPersonDAO = (PersonDAO) this.applicationContext.getBean("INBioPersonDAO");
		PersonLiteDTOFactory pLiteDTOFactory = (PersonLiteDTOFactory) this.applicationContext.getBean("personLiteDTOFactory");
		 
    List<PersonLiteDTO> pLiteList;
		try {
			pLiteList =  pLiteDTOFactory.createDTOList(INBioPersonDAO.findAll(INBioPerson.class));
			if(pLiteList == null){
		   	
		   	 System.out.println("No hay personas para esos paremetros");
		    
		    } else { 
			     //logger.debug("con el id["+kl.getKeywordId()+"] y el nombre ="+kl.getName() );
			     System.out.println("total de elementos: " + pLiteList.size());
			     System.out.println("con el id["+pLiteList.get(0).getPersonKey()+"] y el nombre ="+pLiteList.get(0).getName());
			     
			     //for(PersonDTOLite kl : pliteList){
			     //	 System.out.println("con el id["+kl.getPersonId()+"] y el nombre ="+kl.getDisplayName() );
			     //}
		    }
		    
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			assertTrue( false );
		}
    assertTrue( true );
		
	}
	*/
	
	/*
	public void testListAllGatheringResponsiblePersonLite(){
		
	PersonDAO INBioPersonDAO = (PersonDAO) this.applicationContext.getBean("INBioPersonDAO");
		 
   List<PersonDTOLite> pLiteList;
		try {
			pLiteList = INBioPersonDAO.listAllGatheringResponsiblePersonLite();
			if(pLiteList == null){
		   	
		   	 System.out.println("No hay responsables de colecta");
		    
		    } else { 
			     //logger.debug("con el id["+kl.getKeywordId()+"] y el nombre ="+kl.getName() );
			     System.out.println("\ttotal de personas: " + pLiteList.size()+"\n");
			     
			     for(PersonDTOLite kl : pLiteList){
			     	 System.out.println("\tcon el id["+kl.getPersonId()+"] y el nombre ="+kl.getDisplayName() );
			     }
		    }
		    
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			assertTrue( false );
		}
   
   
   assertTrue( true );
		
	}
*/

}
