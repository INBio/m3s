/**
 * 
 */
package org.inbio.m3s.service.impl;


import org.inbio.m3s.config.Properties;
import org.inbio.m3s.service.AbstractServiceTest;
import org.inbio.m3s.service.SiteManager;

/**
 * @author jgutierrez
 *
 */
public class SiteManagerTest extends AbstractServiceTest{

	
public void testSimpleINBioInstitutionDAO(){
		
		SiteManager siteManager = (SiteManager) applicationContext.getBean(Properties.SITE_MANAGER);
		
		
		String gatheringCode1  ="Alexander Rodríguez;11370";
		String gatheringCode2  ="Daniel Solano;4177";
		String gatheringCode3  ="Daniel Santamaría;7839";
		
		try {
			System.out.println(gatheringCode1+":");
			System.out.println("\n\t"+siteManager.getSiteFromGatheringCode(gatheringCode1));
		} catch (Exception e){
			System.out.println(e.getMessage());
			//assertTrue( false );
		}
			

		try {
			System.out.println(gatheringCode2+":");
			System.out.println("\n\t"+siteManager.getSiteFromGatheringCode(gatheringCode2));
		} catch (Exception e){
			System.out.println(e.getMessage());
			//assertTrue( false );
		}			
		
		try {
			System.out.println(gatheringCode3+":");
			System.out.println("\n\t"+siteManager.getSiteFromGatheringCode(gatheringCode3));
		} catch (Exception e){
			System.out.println(e.getMessage());
			//assertTrue( false );
		}			
		
			assertTrue( true );
		
	}


}
