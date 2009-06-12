package org.inbio.m3s.dao;

import org.inbio.m3s.dao.core.SecurityUserDAO;
import org.inbio.m3s.model.core.SecurityUsers;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class SecurityUserDAOTest extends AbstractDependencyInjectionSpringContextTests{

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
		
		SecurityUserDAO securityUserDAO = (SecurityUserDAO) this.applicationContext.getBean("securityUserDAO");

		SecurityUsers su = (SecurityUsers) securityUserDAO.findById(SecurityUsers.class, "jgutierrez");
		System.out.println(su.toString());
		
		
    assertTrue( true );
		
	}


}
