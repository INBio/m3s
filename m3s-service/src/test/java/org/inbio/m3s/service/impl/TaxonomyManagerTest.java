/**
 * 
 */
package org.inbio.m3s.service.impl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.dto.GeneralMetadataDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.service.AbstractServiceTest;
import org.inbio.m3s.service.MediaManager;
import org.inbio.m3s.service.TaxonomyManager;

/**
 * @author jgutierrez
 *
 */
public class TaxonomyManagerTest extends AbstractServiceTest{
	
	protected static Log logger = LogFactory.getLog(TaxonomyManagerTest.class);

	/*
	public void testGetTaxonsIncludedIn(){
		
		TaxonomyManager tm = (TaxonomyManager) getBean(Properties.TAXONOMY_MANAGER);
		
		List<TaxonLite> tlList =  tm.getTaxonsIncludedIn("Pieridae", TaxonomicalRangeEntity.FAMILY);
		
		if(tlList == null){
    	
   	 System.out.println("No hay keyword para esos paremetros");
    
    } else { 
	     System.out.println("total de taxones: " + tlList.size());
	     
	     for(TaxonLite tl : tlList){
	    	 System.out.println("con el id["+tl.getTaxonId()+"] y el nombre ="+tl.getDefaultName() +" en el reino = " +tl.getKingdomName() );
	     }
    }
		
		tlList =  tm.getTaxonsIncludedIn("Aphrissa", TaxonomicalRangeEntity.GENUS);
		
		if(tlList == null){
    	
   	 System.out.println("No hay keyword para esos paremetros");
    
    } else { 
	     System.out.println("total de taxones: " + tlList.size());
	     
	     for(TaxonLite tl : tlList){
	    	 System.out.println("con el id["+tl.getTaxonId()+"] y el nombre ="+tl.getDefaultName() +" en el reino = " +tl.getKingdomName() );
	     }
    }

		tlList =  tm.getTaxonsIncludedIn("Aphrissa statira", TaxonomicalRangeEntity.SPECIES);
		
		if(tlList == null){
    	
   	 System.out.println("No hay keyword para esos paremetros");
    
    } else { 
	     System.out.println("total de taxones: " + tlList.size());
	     
	     for(TaxonLite tl : tlList){
	    	 System.out.println("con el id["+tl.getTaxonId()+"] y el nombre ="+tl.getDefaultName() +" en el reino = " +tl.getKingdomName() );
	     }
    }
	}
	*/
	
	public void testGetTaxonLiteById(){
		
		MediaManager mm = (MediaManager) getBean(Properties.MEDIA_MANAGER);	
		GeneralMetadataDTO gmDTO = mm.getGM(new Integer(100053));
		System.out.println(gmDTO.toString());
		
		System.out.println("hijo");
		TaxonomyManager tm = (TaxonomyManager) getBean(Properties.TAXONOMY_MANAGER);
		TaxonLiteDTO tlDTO = tm.getTaxonLiteById(gmDTO.getTaxonsList().get(0).getTaxonKey()); 
		System.out.println(tlDTO.toString());
		
		System.out.println("kingdom");
		TaxonLiteDTO tlKingdomDTO = tm.getTaxonLiteById(tlDTO.getKingdomKey());
		System.out.println(tlKingdomDTO.toString());
		
		assertTrue( true );
	}
	
	
	/*
	public void testGetSpecimenLiteForGatheringCode(){
		
	TaxonomyManager tm = (TaxonomyManager) getBean(Properties.TAXONOMY_MANAGER);
		
		
		String gatheringCode1  ="Alexander Rodríguez;11370";
		String gatheringCode2  ="Daniel Solano;4177";
		String gatheringCode3  ="Daniel Santamaría;7839";
		String gatheringCode4  ="A. K. Monro;6380";
		String gatheringCode5  ="Laurencio Martínez;341";
		
		try {
			System.out.println(gatheringCode1+":");
			System.out.println("\n\t"+tm.getSpecimenLiteForGatheringCode(gatheringCode1).get(0).getSpecimenKey());
		} catch (Exception e){
			System.out.println("\t fallo");
			System.out.println(e.getMessage());
			//assertTrue( false );
		}
			

		try {
			System.out.println(gatheringCode2+":");
			System.out.println("\n\t"+tm.getSpecimenLiteForGatheringCode(gatheringCode2).get(0).getSpecimenKey());
		} catch (Exception e){
			System.out.println("\t fallo");
			
			System.out.println(e.getMessage());
			//assertTrue( false );
		}			
		
		try {
			System.out.println(gatheringCode3+":");
			System.out.println("\n\t"+tm.getSpecimenLiteForGatheringCode(gatheringCode3).get(0).getSpecimenKey());
		} catch (Exception e){
			System.out.println("\t fallo");
			System.out.println(e.getMessage());
			//assertTrue( false );
		}			

		try {
			System.out.println(gatheringCode4+":");
			System.out.println("\n\t"+tm.getSpecimenLiteForGatheringCode(gatheringCode4).get(0).getSpecimenKey());
		} catch (Exception e){
			System.out.println("\t fallo");
			System.out.println(e.getMessage());
			//assertTrue( false );
		}			

		try {
			System.out.println(gatheringCode5+":");
			System.out.println("\n\t"+tm.getSpecimenLiteForGatheringCode(gatheringCode5).get(0).getSpecimenKey());
		} catch (Exception e){
			System.out.println("\t fallo");
			System.out.println(e.getMessage());
			//assertTrue( false );
		}			

		
			assertTrue( true );
		
	}
	*/


}
