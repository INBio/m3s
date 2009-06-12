package org.inbio.m3s.dao;

import java.util.List;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class GenericSearchDAOTest extends AbstractDependencyInjectionSpringContextTests{

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

	String HSQL = "select m.mediaId" +
	" from Media as m" +
	" where m.mediaId " +
	"in (select tm.id.mediaId" +
	" from TaxonMedia as tm" +
	" where tm.id.taxonId = "+63799 + "" +
			"or tm.id.taxonId = "+881+")";
	
	String HSQL2 = "select m.mediaId" +
	" from Media as m" +
	" where m.mediaId > 1";
		
	public void testGetTotalResults(){
	
		GenericSearchDAO gsDAO = (GenericSearchDAO) this.applicationContext.getBean("genericSearchDAO");
		
		String theHSQL = "select count(m.mediaId)"
			+ HSQL2.substring(("select m.mediaId").length());
				
		Integer resTotal = gsDAO.getTotalResults(theHSQL);
		
		System.out.println("\tTotal de resultados: " + resTotal.toString());		
    assertTrue( true );

	}
	
	
	public void testGetResults(){
		
		GenericSearchDAO gsDAO = (GenericSearchDAO) this.applicationContext.getBean("genericSearchDAO");
		
		List<Integer> results = gsDAO.getResults(HSQL2, 6, 10);
		
		System.out.println("\nResultados: ");
		for(Integer i : results)
			System.out.println("\t" + i);
		
    assertTrue( true );
		
	}



}
