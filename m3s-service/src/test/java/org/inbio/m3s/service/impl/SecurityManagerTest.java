/**
 * 
 */
package org.inbio.m3s.service.impl;


import org.inbio.m3s.config.Properties;
import org.inbio.m3s.service.AbstractServiceTest;
import org.inbio.m3s.service.SecurityManager;

/**
 * @author jgutierrez
 *
 */
public class SecurityManagerTest extends AbstractServiceTest{

	
	public void testValidUser(){
		
		SecurityManager sm = (SecurityManager) getBean(Properties.SECURITY_MANAGER);	
		System.out.println("valido jgutierrez: "+sm.isValidUser("jgutierrez", "inbiom3s"));
		logger.info("valido jgutierrez: "+sm.isValidUser("jgutierrez", "inbiom3s"));	
		
	}


}
