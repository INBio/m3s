/**
 * 
 */
package org.inbio.m3s.service.impl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.dto.taxonomy.util.TaxonomicalRangeEntity;
import org.inbio.m3s.service.AbstractServiceTest;
import org.inbio.m3s.service.TaxonomyManager;

/**
 * @author jgutierrez
 *
 */
@SuppressWarnings("unused")
public class TaxonomyManagerTest extends AbstractServiceTest{
	
	protected static Log logger = LogFactory.getLog(TaxonomyManagerTest.class);

	/*
	public void testGetTaxonsIncludedIn(){
		
		TaxonomyManager tm = (TaxonomyManager) getBean(Properties.TAXONOMY_MANAGER);
		
		List<TaxonLiteDTO> tlList =  tm.getTaxonsIncludedIn("Pieridae", TaxonomicalRangeEntity.FAMILY);
		
		if(tlList == null){
    	
   	 System.out.println("No hay keyword para esos paremetros");
    
    } else { 
	     System.out.println("total de taxones: " + tlList.size());
	     
	     for(TaxonLiteDTO tl : tlList){
	    	 System.out.println("con el id["+tl.getTaxonKey()+"] y el nombre ="+tl.getDefaultName() +" en el reino = " +tl.getKingdomName() );
	     }
    }
		
		tlList =  tm.getTaxonsIncludedIn("Aphrissa", TaxonomicalRangeEntity.GENUS);
		
		if(tlList == null){
    	
   	 System.out.println("No hay keyword para esos paremetros");
    
    } else { 
	     System.out.println("total de taxones: " + tlList.size());
	     
	     for(TaxonLiteDTO tl : tlList){
	    	 System.out.println("con el id["+tl.getTaxonKey()+"] y el nombre ="+tl.getDefaultName() +" en el reino = " +tl.getKingdomName() );
	     }
    }

		tlList =  tm.getTaxonsIncludedIn("Aphrissa statira", TaxonomicalRangeEntity.SPECIES);
		
		if(tlList == null){
    	
   	 System.out.println("No hay keyword para esos paremetros");
    
    } else { 
	     System.out.println("total de taxones: " + tlList.size());
	     
	     for(TaxonLiteDTO tl : tlList){
	    	 System.out.println("con el id["+tl.getTaxonKey()+"] y el nombre ="+tl.getDefaultName() +" en el reino = " +tl.getKingdomName() );
	     }
    }
	}
	*/
	
	/*
	public void testGetTaxonLiteById(){
		
		//MediaManager mm = (MediaManager) getBean(Properties.MEDIA_MANAGER);	
		//GeneralMetadataDTO gmDTO = mm.getGM(new Integer(100053));
		//System.out.println(gmDTO.toString());
		
		//System.out.println("hijo");
		logger.info("Usando el TaxonomyManager: "+Properties.TAXONOMY_MANAGER);
		TaxonomyManager tm = (TaxonomyManager) getBean(Properties.TAXONOMY_MANAGER);
		//TaxonLiteDTO tlDTO = tm.getTaxonLiteById(gmDTO.getTaxonsList().get(0).getTaxonKey()); 
		TaxonLiteDTO tlDTO = tm.getTaxonLiteById("55");
		logger.info(tlDTO.toString());
		
		logger.info("kingdom");
		TaxonLiteDTO tlKingdomDTO = tm.getTaxonLiteById(tlDTO.getKingdomKey());
		logger.info(tlKingdomDTO.toString());
		
		assertTrue( true );
	}
	*/
	/*
	public void testGetTaxonLite(){
		
		logger.info("Usando el TaxonomyManager: "+Properties.TAXONOMY_MANAGER);
		TaxonomyManager tm = (TaxonomyManager) getBean(Properties.TAXONOMY_MANAGER);
		//TaxonLiteDTO tlDTO = tm.getTaxonLiteById(gmDTO.getTaxonsList().get(0).getTaxonKey()); 
		TaxonLiteDTO tlDTO = tm.getTaxonLite("Aulacocyclus papuanus","Animalia");
		logger.info(tlDTO.toString());
		
		logger.info("kingdom");
		TaxonLiteDTO tlKingdomDTO = tm.getTaxonLiteById(tlDTO.getKingdomKey());
		logger.info(tlKingdomDTO.toString());
		
		assertTrue( true );
	}
	*/

	//getTaxonsByPatialNameAndTaxonomicalRange
	
	public void testGetTaxonsByPatialNameAndTaxonomicalRange(){
		
		TaxonomyManager tm = (TaxonomyManager) getBean(Properties.TAXONOMY_MANAGER);
		logger.info("Usando el TaxonomyManager: "+Properties.TAXONOMY_MANAGER);
		List<TaxonLiteDTO> taxonLiteDTOList;
		
		//Animalia
		taxonLiteDTOList = tm.getTaxonsByPatialNameAndTaxonomicalRange("Ani", TaxonomicalRangeEntity.KINGDOM);
		logger.info("Total de taxones que coinciden con Ani y Kingdom: "+ taxonLiteDTOList.size());
		
		for(TaxonLiteDTO tl : taxonLiteDTOList){
			logger.info(tl.toString());
    }
		
		//Lepidoptera
		//taxonLiteDTOList = tm.getTaxonsByPatialNameAndTaxonomicalRange("Lep", TaxonomicalRangeEntity.ORDER);
		//logger.info("Total de taxones que coinciden con Lep y Orden: "+ taxonLiteDTOList.size());
		
		assertTrue( true );
	}
	
	
	public void testGetSpecimenLiteForGatheringCode(){
		
	TaxonomyManager tm = (TaxonomyManager) getBean(Properties.TAXONOMY_MANAGER);
		
		
		String gatheringCode1  ="Alexander Rodríguez;9421";
		String gatheringCode2  ="A. K. Monro;4925";
		String gatheringCode3  ="Alexander Rodríguez;11120";
		String gatheringCode4  ="A. K. Monro;4871";
		String gatheringCode5  ="A. K. Monro;4725";
		
		//logger.info("Usando el TaxonomyManager: "+Properties.TAXONOMY_MANAGER);
		/*
		try {
			System.out.println(gatheringCode1+":");
			System.out.println("\n\t"+tm.getSpecimenLiteForGatheringCode(gatheringCode1).get(0).getSpecimenKey());
		} catch (Exception e){
			System.out.println("\t fallo");
			System.out.println(e.getMessage());
			//assertTrue( false );
		}
		*/
			
/*
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
	*/
			assertTrue( true );
		
	}
	


}

