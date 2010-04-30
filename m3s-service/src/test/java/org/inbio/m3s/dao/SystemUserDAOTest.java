/**
 * 
 */
package org.inbio.m3s.dao;

import org.inbio.m3s.dao.core.SystemUserDAO;
import org.inbio.m3s.model.core.SystemUser;
import org.springframework.security.GrantedAuthority;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 * @author jgutierrez
 *
 */
public class SystemUserDAOTest extends
		AbstractDependencyInjectionSpringContextTests {

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
		
		SystemUserDAO suDAO = (SystemUserDAO) this.applicationContext.getBean("systemUserDAO");
		
		SystemUser su = suDAO.findByUsername("jgutierrez");
		System.out.println(su.toString());
		
		System.out.println("Authorities:");
		for(GrantedAuthority ga: su.getAuthorities()){
			System.out.println(ga.getAuthority());	
		}
		
		
		
    assertTrue( true );
		
	}


}
