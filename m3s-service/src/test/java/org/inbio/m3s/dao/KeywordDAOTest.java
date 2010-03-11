package org.inbio.m3s.dao;

import java.util.ArrayList;
import java.util.List;

import org.inbio.m3s.dao.core.KeywordDAO;
import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.model.core.Keyword;
import org.inbio.m3s.model.core.TextTranslation;
import org.inbio.m3s.service.MessageManager;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class KeywordDAOTest extends
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

	/*
	 * public void testSimple(){
	 * 
	 * KeywordDAO kDAO = (KeywordDAO)
	 * this.applicationContext.getBean("keywordDAO");
	 * 
	 * List<Keyword> lk =
	 * kDAO.findAllByPartialNamePaginatedUncaseSensitive("%lepidoptera%", 10);
	 * 
	 * for(Keyword k : lk){ logger.info(k.toString()); }
	 * 
	 * assertTrue( true );
	 * 
	 * }
	 */

	public void testIfEqual() {

		KeywordDAO kDAO = (KeywordDAO) this.applicationContext
				.getBean("keywordDAO");

		Integer mediaId = new Integer(10);

		List<KeywordDTO> kDTOList = kDAO.getAllKeywordLiteForMedia(mediaId,
				MessageManager.ESPANYOL);

		List<Keyword> kList = kDAO.findAllByMedia(mediaId);

		if (!kDTOList.isEmpty() && !kList.isEmpty()) {

			if (kDTOList.size() != kList.size())
				assertTrue(false);

			logger.info("Lista de DTO's");
			for (KeywordDTO kDTO : kDTOList)
				logger.info(kDTO.toString());

			logger.info("Lista de Entities convertida a DTO	");
			List<KeywordDTO> kDTOList2 = new ArrayList<KeywordDTO>();
			for (Keyword k : kList) {
				for (TextTranslation tt : k.getTranslations())
					kDTOList2.add(new KeywordDTO(k.getKeywordId(), tt.getName()));
			}
			for (KeywordDTO kDTO : kDTOList2)
				logger.info(kDTO.toString());

			assertTrue(true);
		}

	}

	public void testIfEqual2() {

		KeywordDAO kDAO = (KeywordDAO) this.applicationContext
				.getBean("keywordDAO");

		String keywordName = "epidoptera";

		KeywordDTO kDTO = kDAO.getKeywordLite(keywordName, MessageManager.ESPANYOL);
		Keyword k = kDAO.findByName(keywordName);

		logger.info("Keywrod DTO");
		if (kDTO != null)
			logger.info(kDTO.toString());

		logger.info("Entity");
		if (k != null) {
			logger.info(k.toString());

			logger.info("Entity convertido a DTO");
			List<KeywordDTO> kDTOList2 = new ArrayList<KeywordDTO>();
			for (TextTranslation tt : k.getTranslations())
				kDTOList2.add(new KeywordDTO(k.getKeywordId(), tt.getName()));

			for (KeywordDTO kDTO2 : kDTOList2)
				logger.info(kDTO2.toString());
		}

		assertTrue(true);
	}

}
