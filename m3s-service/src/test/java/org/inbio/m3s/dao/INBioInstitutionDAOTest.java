package org.inbio.m3s.dao;

import java.util.List;

import org.inbio.m3s.dao.core.InstitutionDAO;
import org.inbio.m3s.dto.agent.InstitutionLiteDTO;
import org.inbio.m3s.dto.agent.InstitutionLiteDTOFactory;
import org.inbio.m3s.model.atta.INBioInstitution;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class INBioInstitutionDAOTest extends AbstractDependencyInjectionSpringContextTests{

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
	@SuppressWarnings("unchecked")
	public void testSimpleINBioInstitutionDAO(){
		
		 InstitutionDAO INBioInstitutionDAO = (InstitutionDAO) this.applicationContext.getBean("INBioInstitutionDAO");
		 InstitutionLiteDTOFactory institionLiteDTOFactory = (InstitutionLiteDTOFactory) this.applicationContext.getBean("institutionLiteDTOFactory");
		 
    List<InstitutionLiteDTO> instliteList;
		try {
			instliteList =  institionLiteDTOFactory.createDTOList(INBioInstitutionDAO.findAll(Institution.class));
			if(instliteList == null){
		   	
		   	 System.out.println("No hay instituciones para esos paremetros");
		    
		    } else { 
			     //logger.debug("con el id["+kl.getKeywordId()+"] y el nombre ="+kl.getName() );
			     System.out.println("total de elementos: " + instliteList.size());
			     
			     //for(PersonDTOLite kl : pliteList){
			     //	 System.out.println("con el id["+kl.getPersonId()+"] y el nombre ="+kl.getDisplayName() );
			     //}
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue( false );
		}
    
    
    assertTrue( true );
		
	}
*/

}
