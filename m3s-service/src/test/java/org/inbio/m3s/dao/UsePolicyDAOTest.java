package org.inbio.m3s.dao;

import org.inbio.m3s.dao.core.UsePolicyDAO;
import org.inbio.m3s.model.core.UsePolicy;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class UsePolicyDAOTest extends
		AbstractDependencyInjectionSpringContextTests {

	@Override
	protected String[] getConfigLocations() {
		return new String[] {
				// "classpath*:/**/applicationContext-*.xml",
				// "classpath*:**/applicationContext-*.xml",
				// "classpath*:org/inbio/m3s/**/applicationContext-*.xml",
				// "classpath*:/org/inbio/m3s/**/impl/applicationContext-*-test.xml",
				"classpath*:org/inbio/m3s/dao/applicationContext-dao.xml",
				"classpath*:org/inbio/m3s/dao/applicationContext-factories.xml",
		// "classpath*:/org/inbio/m3s/service/impl/applicationContext-service-test.xml"
		};
	}


	public void testIfEqual() {

		UsePolicyDAO upDAO = (UsePolicyDAO) this.applicationContext
				.getBean("usePolicyDAO");

		Integer usePolicyId = new Integer("2");
		String usePolicyText = "Uso institucional";

		UsePolicy up = upDAO.findByName(usePolicyText);
		logger.info("Use Policy:");
		logger.info(up.toString());
		
			assertTrue(usePolicyId.equals(up.getUsePolicyId()));

	}

}
