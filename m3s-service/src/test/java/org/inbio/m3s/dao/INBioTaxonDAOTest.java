package org.inbio.m3s.dao;



import org.inbio.m3s.dao.core.TaxonDAO;
import org.inbio.m3s.model.atta.Taxon;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class INBioTaxonDAOTest extends AbstractDependencyInjectionSpringContextTests{

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
	
	
	public void testFindById(){

		TaxonDAO taxonDAO = (TaxonDAO) applicationContext.getBean("INBioTaxonDAO");
		
		Taxon t = (Taxon) taxonDAO.findById(Taxon.class, new Integer(63799));
		
		System.out.println(t.toString());

		t = (Taxon) taxonDAO.findById(Taxon.class, new Integer(2));
		System.out.println(t.toString());

		t = (Taxon) taxonDAO.findById(Taxon.class, new Integer(5));
		System.out.println(t.toString());

		t = (Taxon) taxonDAO.findById(Taxon.class, new Integer(119));
		System.out.println(t.toString());

		t = (Taxon) taxonDAO.findById(Taxon.class, new Integer(29));
		System.out.println(t.toString());

		
		t = (Taxon) taxonDAO.findById(Taxon.class, new Integer(881));
		System.out.println(t.toString());
		
		t = (Taxon) taxonDAO.findById(Taxon.class, new Integer(3474));
		System.out.println(t.toString());
		
		t = (Taxon) taxonDAO.findById(Taxon.class, new Integer(13024));
		System.out.println(t.toString());
		
		assertTrue( true );
	}
	
	
	/*
	public void testGetTaxonLiteFromGatheringCode(){
		
		TaxonDAO taxonDAO = (TaxonDAO) applicationContext.getBean("INBioTaxonDAO");
		
		
		String gatheringCode1  ="Alexander Rodríguez;11370";
		String gatheringCode2  ="Daniel Solano;4177";
		String gatheringCode3  ="Daniel Santamaría;7839";
		String gatheringCode4  ="Daniel Solano;2989";
		List<TaxonLite> taxonsList;
		
		try {
			System.out.println(gatheringCode1+":");
			taxonsList = taxonDAO.getTaxonLiteFromGatheringCode(gatheringCode1);
			for (TaxonLite tl : taxonsList)
				System.out.println("\n\t"+ tl.getDefaultName());
		} catch (Exception e){
			System.out.println(e.getMessage());
			//assertTrue( false );
		}
			

		try {
			System.out.println(gatheringCode2+":");
			taxonsList = taxonDAO.getTaxonLiteFromGatheringCode(gatheringCode2);
			for (TaxonLite tl : taxonsList)
				System.out.println("\n\t"+ tl.getDefaultName());
		} catch (Exception e){
			System.out.println(e.getMessage());
			//assertTrue( false );
		}			
		
		try {
			System.out.println(gatheringCode3+":");
			taxonsList = taxonDAO.getTaxonLiteFromGatheringCode(gatheringCode3);
			for (TaxonLite tl : taxonsList)
				System.out.println("\n\t"+ tl.getDefaultName());
		} catch (Exception e){
			System.out.println(e.getMessage());
			//assertTrue( false );
		}			
		
		try {
			System.out.println(gatheringCode4+":");
			taxonsList = taxonDAO.getTaxonLiteFromGatheringCode(gatheringCode4);
			for (TaxonLite tl : taxonsList)
				System.out.println("\n\t"+ tl.getDefaultName());
		} catch (Exception e){
			System.out.println(e.getMessage());
			//assertTrue( false );
		}			
		
			assertTrue( true );
		
	}
*/

}

